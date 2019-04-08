package com.dai.gallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dai.gallery.R;
import com.dai.gallery.bean.HomeListBean;
import com.dai.mylibrary.adapter.base.BaseRecyclerViewAdapter;
import com.dai.mylibrary.adapter.base.BaseRecyclerViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeListAdapter extends BaseRecyclerViewAdapter {

    private String fromSource;

    public HomeListAdapter(Context _ctx, Class cls, String fromSource) {
        super(_ctx, cls);
        this.fromSource = fromSource;
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

//        viewHolder.imgIcon.setRadios(DpPxSpUtils.dip2px(3));
//        ImageUtils.loadImage(ctx, bean.getLogo(), viewHolder.imgIcon);
//        viewHolder.tvProductName.setText(bean.getProductName());
//        viewHolder.tvAmount.setText(bean.getShowAmount());
//        viewHolder.tvAmountLabel.setText("额度");
//        viewHolder.tvDailyRate.setText(bean.getInterestRate() + "%");
//        if (bean.getDateType() == 1) {
//            viewHolder.tvDailyRateLabel.setText("日利率");
//        } else if (bean.getDateType() == 2) {
//            viewHolder.tvDailyRateLabel.setText("月利率");
//        }
//        viewHolder.tvApplyLabel.setText("已申请：" + bean.getApplyNumber());
//
//        List<ProLabelBean> labelBeans = new ArrayList<>();
//        if (!TextUtils.isEmpty(bean.getMinDate() + "") && !TextUtils.isEmpty(bean.getMaxDate() + "")) {
//            String dateStr = bean.getMinDate() + "-" + bean.getMaxDate() + "";
//            if (bean.getMaxDate() == bean.getMinDate()) {
//                dateStr = bean.getMinDate() + "";
//            }
//            String color = bean.getDateType() == 1 ? "#6A9EF3" : bean.getDateType() == 2 ? "#FBD36D" : "#6A9EF3";
//            dateStr += bean.getDateType() == 1 ? "天" : bean.getDateType() == 2 ? "月" : "天";
//            labelBeans.add(new ProLabelBean(dateStr, color));
//        }
//        labelBeans.add(new ProLabelBean(bean.getLabel(), "#5BDCAA"));
//        viewHolder.tagFlowLayut.setAdapter(new TagAdapter(labelBeans) {
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public View getView(FlowLayout parent, int position, Object o) {
//                TextView tvTag = (TextView) mInflater.inflate(R.layout.item_flow_tag, parent, false);
//                if (o instanceof ProLabelBean) {
//                    ProLabelBean proLabelBean = (ProLabelBean) o;
//                    tvTag.setText(((ProLabelBean) o).getLabel());
//                    tvTag.setTextColor(Color.parseColor(proLabelBean.getColor()));
//                    GradientDrawable shape = new GradientDrawable();
//                    shape.setGradientType(GradientDrawable.RECTANGLE);
//                    shape.setCornerRadius(DpPxSpUtils.dip2px(4));
//                    shape.setStroke(DpPxSpUtils.dip2px(1), Color.parseColor(proLabelBean.getColor()));
//                    tvTag.setBackground(shape);
//                }
//                return tvTag;
//            }
//        });
//
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (TextUtils.isEmpty(Configure.TOKEN)) {
//                    LoginActivity.start(ctx, null);
//                } else {
//                    if (!TextUtils.isEmpty(bean.getInvolucrumUrl())) {
//                        H5Activity.start(ctx, bean.getInvolucrumUrl());
//                    } else if (!TextUtils.isEmpty(bean.getPopularizeUrl())) {
//                        H5Activity.start(ctx, bean.getPopularizeUrl());
//                    }
//                    ApiPostRequest.viewRecode(ChannelUtils.CHANNLE_MAIN, fromSource, bean.getCode(), new NetWorkCallBack() {
//                        @Override
//                        public void onResponse(boolean isSucceed, int status, String response) {
//                        }
//                    });
//                }
//            }
//        });
//        viewHolder.btnApply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewHolder.itemView.callOnClick();
//            }
//        });
    }

    static class ViewHolder extends BaseRecyclerViewHolder {

        @Bind(R.id.tvType)
        TextView tvType;
        @Bind(R.id.recyclerView)
        RecyclerView recyclerView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
