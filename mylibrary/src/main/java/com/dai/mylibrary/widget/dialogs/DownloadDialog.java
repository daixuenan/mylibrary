package com.dai.mylibrary.widget.dialogs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dai.mylibrary.R;
import com.dai.plugin.basedialog.base.BaseDialog;
import com.dai.plugin.basedialog.bean.DialogBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadDialog extends BaseDialog<DialogBean> {

    public DownloadDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getDialogLayout() {
        return R.layout.dialog_download;
    }

    //正在下载
    private static final int DOWNLOADING = 1;

    //下载完成
    private static final int DOWNLOAD_FINISH = 2;

    private String mSavePath;
    private int progress;
    private boolean cancelUpdate = false;

    private TextView tvTitle;
    private ProgressBar progressBar;
    private TextView tvUpdateProgress;
    private Button btnCancel;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOADING:
                    progressBar.setProgress(progress);
                    tvUpdateProgress.setText("当前下载进度：" + progress + "%");
                    break;
                case DOWNLOAD_FINISH:
                    installApk();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void init() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvUpdateProgress = (TextView) findViewById(R.id.tvUpdateProgress);
        btnCancel = (Button) findViewById(R.id.btnCancel);
    }

    @Override
    public void showDialog() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                cancelUpdate = true;
            }
        });
        show();

        new DownloadApkThread().start();
    }

    private class DownloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdpath + "ypy/download";
                    URL url = new URL(dialogBean.getContent());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Accept-Encoding", "identity");
                    conn.connect();
                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();
                    File file = new File(mSavePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File apkFile = new File(mSavePath, "ypy.apk");
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    byte buf[] = new byte[1024];
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int) (((float) count / length) * 100);
                        mHandler.sendEmptyMessage(DOWNLOADING);
                        if (numread <= 0) {
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);
                    fos.close();
                    is.close();
                    cancelUpdate = false;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dismiss();
        }
    }

    public void installApk() {
        File apkfile = new File(mSavePath, "ypy.apk");
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        getContext().startActivity(i);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}