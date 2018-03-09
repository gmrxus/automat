package com.zzrh.automat.util;

import android.app.DownloadManager;
import android.net.Uri;

/**
 * Created by Administrator on 2018/3/2.
 */

public class DownloadUtil {
	public static void downloadData(String url, String path, String fileName) {
		DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
		request.setDestinationInExternalPublicDir(path, fileName);
	}
}
