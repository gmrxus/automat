package com.zzrh.automat.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zzrh.automat.common.Keys;
import com.zzrh.automat.util.LogUtil;

/**
 * Created by Administrator on 2018/3/8.
 */

public class MyReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Keys.Action.PIC)) {
			LogUtil.logD("接收广播");
		}
	}
}
