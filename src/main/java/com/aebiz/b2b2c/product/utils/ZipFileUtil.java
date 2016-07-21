package com.aebiz.b2b2c.product.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


@SuppressWarnings("unchecked")
public class ZipFileUtil {
	static final int BUFFER = 2048;

	public static void main(String args[]) {
		System.out.println("start zip File ....");
		// zip("E:/CVSROOT/COMMONSHOP/SHOP_ENTERPRISE/web_source",
		// "E:/CVSROOT/COMMONSHOP/SHOP_ENTERPRISE/web_source.zip");
		// unzip("E:/CVSROOT/COMMONSHOP/SHOP_ENTERPRISE/web_source.zip",
		// "E:/CVSROOT/COMMONSHOP/SHOP_ENTERPRISE/web_source111");
		System.out.println("End zip File ....");
	}

	public static void zip(String sourceFile, String targetFile) {
		ArrayList fileNames = new ArrayList(); // 存放文件名,并非含有路径的名字
		ArrayList files = new ArrayList(); // 存放文件对象
		try {
			FileOutputStream fileOut = new FileOutputStream(targetFile);

			ZipOutputStream outputStream = new ZipOutputStream(fileOut);
			File rootFile = new File(sourceFile);
			listFile(rootFile, fileNames, files);

			for (int loop = 0; loop < files.size(); loop++) {
				File file = (File) files.get(loop);
				System.out.println("zip file >>>" + file.getAbsolutePath());

				FileInputStream fileIn = new FileInputStream(file);

				outputStream.putNextEntry(new ZipEntry(getAbsFileName(sourceFile, file)));
				byte[] buffer = new byte[1024];
				while (fileIn.read(buffer) != -1) {
					outputStream.write(buffer);
				}

				outputStream.closeEntry();
				fileIn.close();
			}

			outputStream.close();
		} catch (IOException ioe) {
			{
				ioe.printStackTrace();
			}
		}
	}

	/**
	 * 给定根目录，返回另一个文件名的相对路径，用于zip文件中的路径.
	 * 
	 * @param baseDir
	 *            java.lang.String 根目录
	 * @param realFileName
	 *            java.io.File 实际的文件名
	 * @return 相对文件名
	 */
	private static String getAbsFileName(String baseDir, File realFileName) {
		File real = realFileName;
		File base = new File(baseDir);
		String ret = real.getName();
		while (true) {
			real = real.getParentFile();
			if (real == null) {
				break;
			}
			if (real.equals(base)) {
				break;
			} else {
				ret = real.getName() + "/" + ret;
			}
		}
		return ret;
	}

	static void listFile(File parentFile, List nameList, List fileList) {
		if (parentFile.isDirectory()) {
			File[] files = parentFile.listFiles();
			for (int loop = 0; loop < files.length; loop++) {
				listFile(files[loop], nameList, fileList);
			}
		} else {
			fileList.add(parentFile);
			nameList.add(parentFile.getName());
		}
	}

	public static void unzip(InputStream ins, String targetFile) {
		BufferedOutputStream dest = null;
		ZipEntry entry;
		ZipInputStream zin = null;
		FileOutputStream fos = null;
		try {
			zin = new ZipInputStream(new BufferedInputStream(ins));
			while ((entry = zin.getNextEntry()) != null) {
				System.out.println("Extracting: " + entry);
				int count;
				byte data[] = new byte[BUFFER];
				String filePath = targetFile + "/" + entry.getName();
				String path = filePath.substring(0, filePath.lastIndexOf("/"));
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				System.out.println("图片名称=entry===" + entry);
				System.out.println("图片名称=entry.getName()===" + entry.getName());
				System.out.println("图片名称=entry.getName().toLowerCase()===" + entry.getName().toLowerCase());
				// fos = new FileOutputStream(targetFile + "/"
				// + entry.getName().toLowerCase());
				fos = new FileOutputStream(targetFile + "/" + entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = zin.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}
			zin.close();

			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (dest != null) {
					dest.close();
				}
				if (zin != null) {
					zin.close();
				}
				if (fos != null) {
					fos.close();
				}

			} catch (Exception e) {

			}

		}
	}
}
