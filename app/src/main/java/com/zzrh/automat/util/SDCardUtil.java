package com.zzrh.automat.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/3/2.
 */

public class SDCardUtil {
	/**
	 * 判断是否有外部储存
	 *
	 * @return
	 */
	public static boolean isSDCardMounted() {
		return Environment.getExternalStorageState().endsWith(Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取SD卡的路径
	 *
	 * @return
	 */
	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}

	/**
	 * 获取SD卡的总容量，单位mb
	 *
	 * @return
	 */
	public static long getSDCardAllSize() {
		if (isSDCardMounted()) {
			StatFs statFs = new StatFs(getSDCardPath());
			long blockCountLong = statFs.getBlockCountLong();
			long blockSizeLong = statFs.getBlockSizeLong();
			return blockSizeLong * blockSizeLong / 1024 / 1024;
		}
		return 0L;
	}

	/**
	 * 获取sd卡的剩余空间大小
	 *
	 * @return
	 */
	public static long getSDCardFreeSize() {
		if (isSDCardMounted()) {
			StatFs fs = new StatFs(getSDCardPath());
			long freeBlocksLong = fs.getFreeBlocksLong();
			long blockSizeLong = fs.getBlockSizeLong();
			return freeBlocksLong * blockSizeLong / 1024 / 1024;
		}
		return 0;
	}

	public static boolean saveBitmapToSDCardPublicFilesDir(Bitmap bitmap, String type, String fileName) {
		if (isSDCardMounted()) {
			BufferedOutputStream bos = null;
			File file = Environment.getExternalStoragePublicDirectory(type);

			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
				if (fileName != null && (fileName.contains(".png") || fileName.contains("PNG"))) {
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
				} else {
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
				}
				bos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return true;
			}
		}
		return false;
	}

	public static boolean saveBitmapToSDCardPrivateFliesDir(Bitmap bitmap,
																													Context context,
																													String fileName,
																													String fileParentFileNaem) {
		if (isSDCardMounted()) {
			BufferedOutputStream bos = null;
			File file = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
			if (fileParentFileNaem != null) {
				file = new File(file.getPath() + "/" + fileParentFileNaem);
				if (!file.isDirectory()) {
					file.mkdirs();
				}
			}
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
				if (fileName != null && (fileName.contains(".png") || fileName.contains("PNG"))) {
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
				} else {
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
				}
				bos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return true;
			}
		}
		return false;
	}


	// 保存bitmap图片到SDCard的私有Cache目录
	public static boolean saveBitmapToSDCardPrivateCacheDir(Bitmap bitmap,
																													String fileName, Context context) {
		if (isSDCardMounted()) {
			BufferedOutputStream bos = null;
			// 获取私有的Cache缓存目录
			File file = context.getExternalCacheDir();

			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(
						file, fileName)));
				if (fileName != null
						&& (fileName.contains(".png") || fileName
						.contains(".PNG"))) {
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
				} else {
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
				}
				bos.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}

	// 从SD卡获取文件
	public static byte[] loadFileFromSDCard(String fileDir) {
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			bis = new BufferedInputStream(
					new FileInputStream(new File(fileDir)));
			byte[] buffer = new byte[8 * 1024];
			int c = 0;
			while ((c = bis.read(buffer)) != -1) {
				baos.write(buffer, 0, c);
				baos.flush();
			}
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 从SDCard中寻找指定目录下的文件，返回Bitmap
	public static Bitmap loadBitmapFromSDCard(String filePath) {
		byte[] data = loadFileFromSDCard(filePath);
		if (data != null) {
			Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
			if (bm != null) {
				return bm;
			}
		}
		return null;
	}

	// 获取SD卡公有目录的路径
	public static String getSDCardPublicDir(String type) {
		return Environment.getExternalStoragePublicDirectory(type).toString();
	}

	// 获取SD卡私有Cache目录的路径
	public static String getSDCardPrivateCacheDir(Context context) {
		return context.getExternalCacheDir().getAbsolutePath();
	}

	// 获取SD卡私有Files目录的路径
	public static String getSDCardPrivateFilesDir(Context context, String type) {
		return context.getExternalFilesDir(type).getAbsolutePath();
	}

	public static boolean isFileExist(String filePath) {
		File file = new File(filePath);
		return file.isFile();
	}

	// 删除sdcard中指定路径的文件和文件夹
	public static void removeFileFromSDCard(String filePath) {
		delFiles(new File(filePath));
	}

	private static void delFiles(File file) {
		if (file.isFile()) {
			file.delete();
		}
		if (file.getParentFile().listFiles().length == 0) {
			file.getParentFile().delete();
		}
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files.length == 0 || files == null) {
				file.delete();
			} else {
				for (File childFile : files) {
					delFiles(childFile);
				}
			}
		}
	}

	/**
	 * 获取SD卡的可用控件大小
	 *
	 * @return
	 */
	public static long getSDCardAvailableSize() {
		if (isSDCardMounted()) {
			StatFs fs = new StatFs(getSDCardPath());
			long count = fs.getAvailableBlocksLong();
			long size = fs.getBlockCountLong();
			return count * size / 1024 / 1024;
		}
		return 0;
	}

	// 往SD卡的公有目录下保存文件
	public static boolean saveFileToSDCardPublicDir(byte[] data, String type,
																									String fileName) {
		BufferedOutputStream bos = null;
		if (isSDCardMounted()) {
			File file = Environment.getExternalStoragePublicDirectory(type);
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(
						file, fileName)));
				bos.write(data);
				bos.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	// 往SD卡的自定义目录下保存文件
	public static boolean saveFileToSDCardCustomDir(byte[] data, String dir,
																									String fileName) {
		BufferedOutputStream bos = null;
		if (isSDCardMounted()) {
			File file = new File(getSDCardPath() + File.separator + dir);
			if (!file.exists()) {
				file.mkdirs();// 递归创建自定义目录
			}
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(
						file, fileName)));
				bos.write(data);
				bos.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	// 往SD卡的私有Files目录下保存文件
	public static boolean saveFileToSDCardPrivateFilesDir(byte[] data,
																												String type, String fileName, Context context) {
		BufferedOutputStream bos = null;
		if (isSDCardMounted()) {
			File file = context.getExternalFilesDir(type);
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(
						file, fileName)));
				bos.write(data);
				bos.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	// 往SD卡的私有Cache目录下保存文件
	public static boolean saveFileToSDCardPrivateCacheDir(byte[] data,
																												String fileName, Context context) {
		BufferedOutputStream bos = null;
		if (isSDCardMounted()) {
			File file = context.getExternalCacheDir();
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(
						file, fileName)));
				bos.write(data);
				bos.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}


