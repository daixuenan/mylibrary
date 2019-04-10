package com.dai.gallery.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dai.gallery.R;
import com.dai.gallery.bean.HomeListBean;
import com.dai.gallery.ui.mm.MMTypeActivity;
import com.dai.mylibrary.adapter.base.BaseRecyclerViewAdapter;
import com.dai.mylibrary.adapter.base.BaseRecyclerViewHolder;
import com.dai.mylibrary.utils.DpPxSpUtils;
import com.dai.mylibrary.widget.RecyclerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeListAdapter extends BaseRecyclerViewAdapter {

    public HomeListAdapter(Context _ctx, Class cls) {
        super(_ctx, cls);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateView(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_home_list, parent, false));
    }

    @Override
    protected void onBindView(BaseRecyclerViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;

        final HomeListBean bean = (HomeListBean) dataList.get(position);

        viewHolder.tvType.setText(bean.getType().getDescription());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ctx, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.recyclerView.setLayoutManager(gridLayoutManager);
        viewHolder.recyclerView.addItemDecoration(new RecyclerItemDecoration(DpPxSpUtils.dip2px(15), 2));

        viewHolder.homeMMAdapter = new HomeMMAdapter(ctx, HomeListBean.class);
        viewHolder.recyclerView.setAdapter(viewHolder.homeMMAdapter);
        viewHolder.homeMMAdapter.refresh(bean.getList());

        viewHolder.rlType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MMTypeActivity.start(ctx, bean.getType());
            }
        });
    }

    static class ViewHolder extends BaseRecyclerViewHolder {

        @Bind(R.id.tvType)
        TextView tvType;
        @Bind(R.id.recyclerView)
        RecyclerView recyclerView;
        @Bind(R.id.rlType)
        RelativeLayout rlType;

        private HomeMMAdapter homeMMAdapter;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
