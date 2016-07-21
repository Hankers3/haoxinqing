package com.hxq.mobile.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.jcabi.log.Logger;
import com.jcabi.ssh.SSH;
import com.jcabi.ssh.Shell;
import com.wxcommon.file.FileUtils;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

import fm.last.moji.MojiFile;
import fm.last.moji.spring.SpringMojiBean;

public class UploadFileUtils {

	/**
	 * 将上传文件保存到另一台服务器，并由返回http访问地址.
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String uploadFileToRemort(MultipartFile file) throws Exception {

		OutputStream stdout = null;//执行结果返回
		OutputStream stderr = null;//错误异常返回
		StringBuffer sbf = new StringBuffer(1000);
		String fileName = file.getOriginalFilename();

		// 通过SSH上传文件
		Shell shell = new SSH(
				MessageHelper.getMessage("upload.remort_host"),//172.168.1.20
				MathUtils.toInt(MessageHelper.getMessage("remort_port"),22),//22
				MessageHelper.getMessage("upload.remort_user"),//root
				MessageHelper.getMessage("upload.rsa_file"));// /root/.ssh/id_rsa.pub
		sbf.append("cat > ").append(fileName).append(" && mv -f ").append(fileName)
		.append(MessageHelper.getMessage("upload.remort_path"));
		new Shell.Safe(shell).exec(sbf.toString(), file.getInputStream(),
				Logger.stream(Level.INFO, stdout), Logger.stream(Level.WARNING, stderr));

		//生成下载地址
    	String downloadurl = MessageHelper.getMessage("upload.download_url");
    	sbf.delete(0, sbf.length());
    	sbf.append(downloadurl)
    	.append(downloadurl.endsWith("/")?"":"/")
    	.append(fileName);
		return sbf.toString();
	}

	/**
     * 将上传文件保存到 本地，并由返回本地http访问地址.
     * @param file
     * @return
     * @throws Exception
     */
    public static String uploadFileToLocal(MultipartFile file) throws Exception {
    	if(file.isEmpty()) return "";

    	String fileName = file.getOriginalFilename();
    	String uploadpath = MessageHelper.getMessage("upload.upload_path");
    	StringBuffer sbf = new StringBuffer(1000);
    	sbf.append(uploadpath)
    	.append(uploadpath.endsWith(File.separator)?"":File.separator)
    	.append(fileName);

    	//保存本地
    	InputStream is = null;
    	try {
   			is = file.getInputStream();
   			FileUtils.writeBytes(is, sbf.toString(), false);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(is != null) is.close();
			} catch (Exception e) {}
		}

		//生成下载地址
    	String downloadurl = MessageHelper.getMessage("upload.download_url");
    	sbf.delete(0, sbf.length());
    	sbf.append(downloadurl)
    	.append(downloadurl.endsWith("/")?"":"/")
    	.append(fileName);
		return sbf.toString();
    }

    /**
     * 将上传文件保存到文件服务器，并由文件服务器返回http访问地址.
     * @param file
     * @param fileUpload
     * @param fileService
     * @return {文件id,文件url地址,文件类型(后缀)}
     * @throws Exception
     */
    public static String[] uploadFileToServer(MultipartFile file, SpringMojiBean moji) throws Exception {
    	InputStream is = null;
    	try {
   			is = file.getInputStream();
   			return uploadFileToServer(is, file.getOriginalFilename(), moji);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(is != null) is.close();
			} catch (Exception e) {}
		}
    }

    /**
     * 上传本地文件至文件服务器
     * @param filePath
     * @param fileUpload
     * @param fileService
     * @return {文件id,文件url地址,文件类型(后缀)}
     * @throws Exception
     */
    public static String[] uploadFileToServer(String filePath, SpringMojiBean moji) throws Exception {
   		File file = new File(filePath);
   		if(!file.exists()) return null;
   		InputStream is = null;
   		try {
   			is = new FileInputStream(file);
   			return uploadFileToServer(is, file.getName(), moji);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(is != null) is.close();
			} catch (Exception e) {}
		}
	}

    /**
     * 通过InputStream流和文件名称上传至文件服务器
     * @param is
     * @param fileName
     * @param fileType
     * @param fileUpload
     * @param fileService
     * @return {文件id,文件url地址,文件类型(后缀)}
     */
    public static String[] uploadFileToServer(InputStream is, String fileName, SpringMojiBean moji) throws Exception {
    	String suffix = StringUtils.trimToEmpty(FileUtils.getExtension(fileName));
    	String id = IdentityHelper.uuid2();
    	StringBuffer sbf = new StringBuffer(100);
    	sbf.append(id);
    	if(suffix.length() > 0) {
    		sbf.append(".").append(suffix);
    	}
    	fileName = sbf.toString();
    	List<URL> lst = uploadToMogilefs(id, is, moji);
    	return ObjectUtils.isEmpty(lst) ? null
    			: new String[]{id, formatUrl(lst.get(0).toString()), suffix};
    }

	private static List<URL> uploadToMogilefs(String uuid, InputStream in, SpringMojiBean moji) throws Exception {
		OutputStream out = null;
		MojiFile mf = moji.getFile("MyFileKey" + uuid);
		try {
			out = mf.getOutputStream();
			byte[] bs = new byte[1024];
			int bytesRead = 0;
			while (-1 != (bytesRead = in.read(bs, 0, bs.length))) {
				out.write(bs, 0, bytesRead);
			}
			out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(out != null) out.close();
			} catch (Exception e) {}
		}
		return mf.getPaths();
	}

    private static String formatUrl(String url) {
      if(ObjectUtils.isEmpty(url) || "null".equalsIgnoreCase(url)) return "";

      String imageUrl = MessageHelper.getMessage("imageUrl");
      if(ObjectUtils.isEmpty(imageUrl) || "null".equalsIgnoreCase(imageUrl)) return url;

      if((url.startsWith("http://")) || (url.startsWith("https://"))) {
        url = url.substring(url.indexOf("://") + 3);
        url = url.substring(url.indexOf("/") + 1);
      }

      if(url.startsWith("/")) {
        url = url.substring(url.indexOf("/") + 1);
      }

      StringBuffer sbf = new StringBuffer(1000);
      sbf.append(imageUrl).append(imageUrl.startsWith("/") ? "" : "/").append(url);
      return sbf.toString();
    }
}
