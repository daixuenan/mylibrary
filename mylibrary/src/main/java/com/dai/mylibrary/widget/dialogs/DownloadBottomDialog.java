package com.dai.mylibrary.widget.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.dai.mylibrary.R;
import com.dai.mylibrary.utils.ActivityStackManager;
import com.dai.mylibrary.utils.DpPxSpUtils;
import com.dai.mylibrary.widget.dialogs.base.BaseDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadBottomDialog extends BaseDialog {

    public DownloadBottomDialog(@NonNull Context context) {
        super(context);
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().getDecorView().setPadding(0, 0, 0, DpPxSpUtils.dip2px(61));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = screenWidth;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.dimAmount = 0;
        this.getWindow().setAttributes(params);

        setCancelable(false);
    }

    @Override
    public int getDialogLayout() {
        return R.layout.dialog_download_bottom;
    }

    //正在下载
    private static final int DOWNLOADING = 1;

    //下载完成
    private static final int DOWNLOAD_FINISH = 2;

    private String mSavePath;
    private int progress;

    private ProgressBar progressBar;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOADING:
                    progressBar.setProgress(progress);
                    break;
                case DOWNLOAD_FINISH:
                    ActivityStackManager.getInstance().popAllActivityExceptOne(null);
                    installApk();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void init() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (onDialogClosedListener != null) {
                    onDialogClosedListener.onClosed();
                }
            }
        });
    }

    @Override
    public void showDialog() {
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
                    URL url = new URL(dialogBean.getUrl());
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
                    } while (true);
                    fos.close();
                    is.close();
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