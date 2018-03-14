package com.zzrh.automat.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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

    private Context mContext;
    private List<Goods> goodsList;

    public HomepageRVAdapter(Context mContext, List<Goods> goodsList) {
        this.mContext = mContext;
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
        viewHolder.tvHuodao.setText(goods.getHdId());
        viewHolder.tvKucun.setText(goods.getGoodsStorage());
        viewHolder.tvTitle.setText(goods.getGoodsName());
        viewHolder.tvPrice.setText(goods.getGoodsPrice());
        Glide.with(mContext)
                .load(Keys.Conifg.GOODS_IMG_PATH +goodsList.get(position).getGoodsUrl())
                .into(viewHolder.ivImg);
        viewHolder.cvP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onClick(v, position);
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
