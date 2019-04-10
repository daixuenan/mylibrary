package com.dai.mylibrary.widget.photoview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dai.mylibrary.R;
import com.dai.mylibrary.bean.FileBean;
import com.dai.mylibrary.utils.image.ImageUtils;

/**
 * 单张图片显示Fragment
 */
public class ImageDetailFragment extends Fragment {

    public PhotoView mImageView;
    private ProgressBar progressBar;
    public PhotoViewAttacher mAttacher;

    private FileBean bean;

    public static ImageDetailFragment newInstance(FileBean fileBean) {
        final ImageDetailFragment f = new ImageDetailFragment();
        final Bundle args = new Bundle();
        args.putSerializable("bean", fileBean);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bean = getArguments() != null ? (FileBean) getArguments().getSerializable("bean") : new FileBean();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.frame_image_detail, container, false);
        mImageView = (PhotoView) v.findViewById(R.id.image);
        mAttacher = new PhotoViewAttacher(mImageView);
        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                getActivity().finish();
            }
        });
        progressBar = (ProgressBar) v.findViewById(R.id.loading);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageUtils.loadMMImage(getActivity(), bean.getDownloadurl(), bean.getRefer(), bean.getUseragent(), mImageView);
    }
}
