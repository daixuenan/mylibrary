package com.dai.gallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.dai.gallery.R;
import com.dai.gallery.network.ApiGetRequest;
import com.dai.gallery.network.interfaces.NetWorkCallBack;
import com.dai.mylibrary.bean.FileBean;
import com.dai.gallery.utils.Configure;
import com.dai.mylibrary.adapter.base.BaseRecyclerViewAdapter;
import com.dai.mylibrary.adapter.base.BaseRecyclerViewHolder;
import com.dai.mylibrary.utils.DpPxSpUtils;
import com.dai.mylibrary.utils.image.ImageUtils;
import com.dai.plugin.imageutils.PhotoView;
import com.dai.plugin.imageutils.ViewImageActivity;
import com.dai.plugin.imageutils.interfaces.OnLoadImageListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MMAdapter extends BaseRecyclerViewAdapter {

    public MMAdapter(Context _ctx, Class cls) {
        super(_ctx, cls);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateView(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_mm, parent, false));
    }

    @Override
    protected void onBindView(BaseRecyclerViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;

        final FileBean bean = (FileBean) dataList.get(position);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHolder.img.getLayoutParams();
        layoutParams.height = (int) ((Configure.SCREEN_WIDTH - DpPxSpUtils.dip2px(30)) * 0.618);
        viewHolder.img.setLayoutParams(layoutParams);

        ImageUtils.loadMMImage(ctx, bean.getDownloadurl(), bean.getRefer(), bean.getUseragent(), viewHolder.img);
        viewHolder.tvTitle.setText(bean.getDescription());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiGetRequest.getMMDetailList(bean.getFoldername(), new NetWorkCallBack() {
                    @Override
                    public void onResponse(boolean isSucceed, int status, String response) {
                        if (isSucceed) {
                            List<FileBean> detailList = JSON.parseArray(response, FileBean.class);
                            ViewImageActivity.start(ctx, detailList, new OnLoadImageListener() {
                                @Override
                                public void onLoadImage(Context context, Object bean, PhotoView imageView) {
                                    if (bean instanceof FileBean) {
                                        ImageUtils.loadMMImage(context, ((FileBean) bean).getDownloadurl(), ((FileBean) bean).getRefer(), ((FileBean) bean).getUseragent(), imageView);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    static class ViewHolder extends BaseRecyclerViewHolder {

        @Bind(R.id.img)
        ImageView img;
        @Bind(R.id.tvTitle)
        TextView tvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
