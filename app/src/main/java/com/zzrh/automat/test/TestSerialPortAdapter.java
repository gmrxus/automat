package com.zzrh.automat.test;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kongqw.serialportlibrary.Device;
import com.zzrh.automat.R;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gmrxus on 2018/3/26.
 */

public class TestSerialPortAdapter extends RecyclerView.Adapter {
    private static final String TAG = "TestSerialPortAdapter";
    private Context mContext;
    private List<Device> devices;

    public TestSerialPortAdapter(Context mContext, List<Device> devices) {
        this.mContext = mContext;
        this.devices = devices;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_test_serialport, parent, false);
        Log.d(TAG, "onCreateViewHolder: " + devices.size());

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder h = (ViewHolder) holder;
        File file = devices.get(position).getFile();
        h.tvTestDevicePath.setText(file.getPath());
        StringBuffer buffer = new StringBuffer();
        String canRead = file.canRead() ? "可读" : "不可读";
        String canWrite = file.canWrite() ? "可写" : "不可写";
        String canExecute = file.canExecute() ? "可执行" : "不可执行";
        buffer.append(canRead + ", " + canWrite + ", " + canExecute);
        h.tvLimits.setText(buffer);
        h.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.click(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cv_item_goods)
        CardView cv;
        @BindView(R.id.tv_test_device_path)
        TextView tvTestDevicePath;
        @BindView(R.id.tv_test_device_limits)
        TextView tvLimits;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void click(int position);
    }
}
