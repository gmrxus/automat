package com.zzrh.automat.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kongqw.serialportlibrary.Device;
import com.kongqw.serialportlibrary.SerialPortFinder;
import com.kongqw.serialportlibrary.SerialPortManager;
import com.zzrh.automat.R;
import com.zzrh.automat.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Gmrxus on 2018/3/26.
 */

public class TestSerialPortActivity extends BaseActivity {

    public static final String DEVICE = "device";
    @BindView(R.id.rv_test)
    RecyclerView rvTest;
    private SerialPortManager manager;
    private ArrayList<Device> devices;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sp);
        initView();
        doBusiness();

    }

    private void initView() {
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        rvTest.setLayoutManager(layout);
        SerialPortFinder finder = new SerialPortFinder();
        TestSerialPortAdapter adapter = new TestSerialPortAdapter(this, finder.getDevices());
        rvTest.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(this,TestSerialPortActivity2.class);
            intent.putExtra(DEVICE, devices.get(position));
            startActivity(intent);
        });
    }

    private void doBusiness() {
        SerialPortFinder finder = new SerialPortFinder();
        devices = finder.getDevices();
        manager = new SerialPortManager();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
