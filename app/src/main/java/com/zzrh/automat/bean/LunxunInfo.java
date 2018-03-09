package com.zzrh.automat.bean;

/**
 * Created by Administrator on 2018/3/7.
 */

public class LunxunInfo {


	/**
	 * t : 3
	 * data : http://www.4fz3.com/filebase/upload/machine/img/1517213866079.jpg
	 * mid : 000BABC8D313
	 * version : 1520405148865
	 */

	private int t;
	private String data;
	private String mid;
	private String version;

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "LunxunInfo{" +
				"t=" + t +
				", data='" + data + '\'' +
				", mid='" + mid + '\'' +
				", version='" + version + '\'' +
				'}';
	}
}
