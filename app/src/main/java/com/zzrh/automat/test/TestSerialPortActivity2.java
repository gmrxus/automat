package com.zzrh.automat.test;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kongqw.serialportlibrary.Device;
import com.zzrh.automat.base.BaseActivity;

/**
 * Created by Gmrxus on 2018/3/26.
 */

class TestSerialPortActivity2 extends BaseActivity {

    private Device device;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device = (Device) getIntent().getSerializableExtra(TestSerialPortActivity.DEVICE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
