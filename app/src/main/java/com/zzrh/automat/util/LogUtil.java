package com.zzrh.automat.util;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2018/3/2.
 */

public class LogUtil {
	public static boolean isLog = true;

	public static void logD(Object msg) {
		if (isLog) {
			Logger.d(msg);
		}
	}

	public static void logE(String msg, Object o) {
		if (isLog) {
			Logger.e(msg, o);
		}
	}
}
