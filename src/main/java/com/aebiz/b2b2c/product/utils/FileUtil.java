package com.aebiz.b2b2c.product.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {
	
	
	/**
	 * 取得文件名得前缀
	 * 
	 * @param fPath
	 *            String
	 * @return String
	 */
	public static String getFilePrefixName(String fPath) {
		String fileName = getFileName(fPath);
		int pos = fileName.lastIndexOf(".");

		if (pos < 0) {
			return fileName;
		} else {
			return fileName.substring(0, pos);
		}
	}
	
	
	/**
	 * Get file name from absolute path
	 * 
	 * @param fPath
	 *            absolute path
	 * @return file name
	 */
	public static String getFileName(String fPath) {
		int pos1 = fPath.lastIndexOf(File.separator);
		int pos2 = fPath.lastIndexOf("/");
		if (pos1 < pos2) {
			pos1 = pos2;
		}

		if (pos1 < 0) {
			return fPath;
		}

		if (pos1 == fPath.length() - 1) {
			return "";
		}

		return fPath.substring(pos1 + 1, fPath.length());
	}
	
	
	/**
	 * Copy file from source to destination
	 * 
	 * @param source
	 *            source directory
	 * @param dest
	 *            destination directory
	 * @return true if copy sucessfully; return false when error
	 */
	public static boolean copyFile(String source, String dest) {

		// check if they are the same file
		File fSource = new File(source);
		File fDest = new File(dest);
		String path = dest.substring(0, dest.lastIndexOf("/"));
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		if (fSource.equals(fDest)) {
			return true;
		}
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(source);
			fos = new FileOutputStream(dest);

			byte[] buf = new byte[10 * 1024];
			int size = 0;

			while ((size = fis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}

			fos.flush();

			return true;
		} catch (Exception ex) {
			return false;
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (Exception ex) {
			}
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * Get file extended name from absolute path
	 * 
	 * @param fPath
	 *            file absolute name
	 * @return file extended name
	 */
	public static String getFileExtName(String fPath) {
		String fileName = getFileName(fPath);
		int pos = fileName.lastIndexOf(".");

		if (pos == -1 && fileName.lastIndexOf("jpg") != -1) {
			pos = fileName.lastIndexOf("jpg") - 1;
		}

		if (pos == -1 && fileName.lastIndexOf("jpeg") != -1) {
			pos = fileName.lastIndexOf("jpeg") - 1;
		}

		if (pos == -1 && fileName.lastIndexOf("png") != -1) {
			pos = fileName.lastIndexOf("png") - 1;
		}

		if (pos == -1 && fileName.lastIndexOf("png") != -1) {
			pos = fileName.lastIndexOf("png") - 1;
		}

		if (pos < 0 || pos == fileName.length() - 1) {
			return "";
		}
		return fileName.substring(pos + 1);
	}
	
	
	/**
	 * 取得文件名得前缀
	 * 
	 * @param fPath
	 *            String
	 * @return String
	 */
	public static String getFilePath(String filePath) {
		int pos = filePath.lastIndexOf(".");

		if (pos < 0) {
			return filePath;
		} else {
			return filePath.substring(0, pos);
		}
	}
}
