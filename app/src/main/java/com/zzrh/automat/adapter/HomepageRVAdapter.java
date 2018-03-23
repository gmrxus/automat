package com.zzrh.automat.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zzrh.automat.R;
import com.zzrh.automat.bean.Goods;
import com.zzrh.automat.common.Keys;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gmrxus on 2018/3/9.
 */

public class HomepageRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "HomepageRVAdapter";

    private Context mContext;
    private List<Goods> goodsList;
    private int pagePosition;

    public HomepageRVAdapter(Context mContext, List<Goods> goodsList, int position) {
        this.mContext = mContext;
        this.pagePosition = position;
        setGoodsList(goodsList);
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_homepage_goods, parent, false);
        return new GoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        GoodsViewHolder viewHolder = (GoodsViewHolder) holder;
        Goods goods = goodsList.get(position);
        String hdId = goods.getHdId();
        String goodsStorage = goods.getGoodsStorage();
        String goodsName = goods.getGoodsName();
        String goodsPrice = goods.getGoodsPrice();
        viewHolder.tvHuodao.setText(hdId);
        viewHolder.tvKucun.setText(goodsStorage);
        viewHolder.tvTitle.setText(goodsName);
        viewHolder.tvPrice.setText(goodsPrice);

        String goodsImgUrl = goodsList.get(position).getGoodsUrl();
        if ("0".equals(goodsStorage) || TextUtils.isEmpty(goodsStorage) || TextUtils.isEmpty(goodsName)) {
            viewHolder.tvKucun.setText("无货");
            viewHolder.rlTitle
                    .setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_tiem_bg_long_graycolor));
        }

        if (!TextUtils.isEmpty(goodsImgUrl)) {
            String str = Keys.Config.GOODS_IMG_PATH + goodsImgUrl;

            Glide.with(mContext)
                    .load(str)
                    .into(viewHolder.ivImg);
        } else {
            Glide.with(mContext)
                    .load(R.drawable.ic_error)
                    .into(viewHolder.ivImg);
        }
        viewHolder.cvP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int rPosition = pagePosition * Keys.Config.PAGE_COLUMN_V * Keys.Config.PAGE_COLUMN_H;
                    listener.onClick(v, position + rPosition);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }


    static class GoodsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cv_item_goods)
        CardView cvP;
        @BindView(R.id.tv_item_homepage_goods_huodao)
        TextView tvHuodao;
        @BindView(R.id.tv_item_homepage_goods_kucun)
        TextView tvKucun;
        @BindView(R.id.iv_item_homepage_goods_img)
        ImageView ivImg;
        @BindView(R.id.tv_item_homepage_goods_title)
        TextView tvTitle;
        @BindView(R.id.tv_item_homepage_goods_price)
        TextView tvPrice;
        @BindView(R.id.rl_item_goods_title)
        RelativeLayout rlTitle;

        GoodsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
