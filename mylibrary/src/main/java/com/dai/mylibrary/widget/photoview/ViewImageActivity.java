package com.dai.mylibrary.widget.photoview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TextView;

import com.dai.mylibrary.R;
import com.dai.mylibrary.bean.FileBean;

import java.io.Serializable;
import java.util.List;

/*
 * 查看大图
 */
public class ViewImageActivity extends FragmentActivity {

    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";

    private HackyViewPager mPager;
    private int pagerPosition;
    private TextView indicator;

    private TextView btnDelete;

    private List<FileBean> images;

    private static OnDeleteFileListener mOnDeleteFileListener;

    public static void start(Context context, List<FileBean> dataList, int position, OnDeleteFileListener onDeleteFileListener) {
        mOnDeleteFileListener = onDeleteFileListener;
        Intent intent = new Intent(context, ViewImageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXTRA_IMAGE_URLS, (Serializable) dataList);
        intent.putExtra(EXTRA_IMAGE_INDEX, position);
        context.startActivity(intent);
    }

    public static void start(Context context, List<FileBean> dataList, int position) {
        start(context, dataList, position, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        images = (List<FileBean>) getIntent().getSerializableExtra(EXTRA_IMAGE_URLS);

        btnDelete = findViewById(R.id.btn_delete);
        //是否有删除功能
        btnDelete.setVisibility(mOnDeleteFileListener == null ? View.GONE : View.VISIBLE);

        mPager = (HackyViewPager) findViewById(R.id.pager);
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), images);
        mPager.setAdapter(mAdapter);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ApiRequest.DeleteFile(deleteBean.getKey(), UploadUtils.PHOTO_BUCKET_TYPE, new
////                final int position = mPager.getCurrentItem();
////                final FileBean deleteBean = images.get(position);NetWorkCallBack() {
//                    @Override
//                    public void onResponse(boolean isSucceed, String status, String response) {
//                        if (isSucceed) {
//                            if (images.size() == 1) {
//                                finish();
//                            } else {
//                                images.remove(position);
//                                ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), images);
//                                mPager.setAdapter(mAdapter);
//                            }
//                            updateIndicator();
//                            if (mOnDeleteFileListener != null) {
//                                mOnDeleteFileListener.onFileDelete(deleteBean, position);
//                            }
//                        } else {
//                            Toast.makeText(ViewImageActivity.this, response, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
            }
        });

        indicator = (TextView) findViewById(R.id.indicator);
        CharSequence text = getString(R.string.viewpager_indicator, 1, mPager.getAdapter().getCount());
        indicator.setText(text);
        // 更新下标
        mPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                CharSequence text = getString(R.string.viewpager_indicator, arg0 + 1, mPager.getAdapter().getCount());
                indicator.setText(text);
            }
        });
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }

        mPager.setCurrentItem(pagerPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public List<FileBean> fileList;

        public ImagePagerAdapter(FragmentManager fm, List<FileBean> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return ImageDetailFragment.newInstance(fileList.get(position));
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.image_close_enter, R.anim.image_close_exit);
    }

    public interface OnDeleteFileListener {
        void onFileDelete(FileBean bean, int position);
    }

    private void updateIndicator() {
        CharSequence text = getString(R.string.viewpager_indicator, mPager.getCurrentItem() + 1, mPager.getAdapter().getCount());
        indicator.setText(text);
    }

}
