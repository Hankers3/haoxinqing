package com.aebiz.b2b2c.basebusiness.ueditor.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.basebusiness.ueditor.PathFormat;
import com.aebiz.b2b2c.basebusiness.ueditor.define.AppInfo;
import com.aebiz.b2b2c.basebusiness.ueditor.define.BaseState;
import com.aebiz.b2b2c.basebusiness.ueditor.define.FileType;
import com.aebiz.b2b2c.basebusiness.ueditor.define.State;
import com.aebiz.b2b2c.baseframework.exception.LogException;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;

import fm.last.moji.MojiFile;
import fm.last.moji.spring.SpringMojiBean;

@Component
public class BinaryUploader {
	private static Logger log = LoggerFactory.getLogger(BinaryUploader.class);
	
	@Autowired
	private static SpringMojiBean moji = null;
	@Autowired
	public void setMoji(SpringMojiBean moji) {
		this.moji = moji;
	}
	
	private static FileUploadHelper fileUpload ;
	@Autowired
	public void setFileUpload(FileUploadHelper fileUpload) {
		this.fileUpload = fileUpload;
	}
	
	private static FileService fileService;
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());

        if ( isAjaxUpload ) {
            upload.setHeaderEncoding( "UTF-8" );
        }

		try {
			FileItemIterator iterator = upload.getItemIterator(request);
			
			
			while (iterator.hasNext()) {
				fileStream = iterator.next();

				if (!fileStream.isFormField())
					break;
				fileStream = null;
			}

			if (fileStream == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}

			String savePath = (String) conf.get("savePath");
			String originFileName = fileStream.getName();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,
					originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			String physicalPath = (String) conf.get("rootPath") + savePath;

			InputStream is = fileStream.openStream();
			
			FileModel filemodel=uploadFiles(is, originFileName + suffix);
			System.out.println(filemodel.toString());
			
			State storageState = new BaseState(true);
			String temp=filemodel.getRemotePaths();
			String temp2=temp.substring(getCharacterPosition(temp), temp.length());
			System.out.println(temp2);
			storageState.putInfo( "title", filemodel.getFileName());
			
			if (storageState.isSuccess()) {
				storageState.putInfo("url", temp2);//PathFormat.format(savePath)
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;
		} catch (FileUploadException e) {
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	public static FileModel uploadFiles(InputStream in, String fileName) {
		List<FileModel> retList = new ArrayList<FileModel>();
		try {
			 
				FileModel fm = fileService.getOneFileModel(fileName);

				List<URL> list = uploadToMogilefs(fm.getUuid(), in);

				fm.setRemotePaths(list.get(0).toString());
				fileService.update(fm);
				return fm;
		} catch (Exception e) {
			throw new LogException("file.upload", "uploadFiless error", e);
		}
	}
	
	private static List<URL> uploadToMogilefs(String uuid, InputStream in)
			throws Exception {
		MojiFile mf = moji.getFile("MyFileKey" + uuid);

		OutputStream out = null;
		try {
			out = mf.getOutputStream();

			byte[] bs = new byte['?'];
			int bytesRead = 0;
			while (-1 != (bytesRead = in.read(bs, 0, bs.length))) {
				out.write(bs, 0, bytesRead);
			}
			out.flush();
		} catch (Exception err) {
			throw new LogException("file.upload",
					"uploadToMogilefs error,file==", err);
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				log.error("uploadToMogilefs in.close error", e);
			}
			try {
				out.close();
			} catch (Exception e) {
				log.error("uploadToMogilefs out.close error", e);
			}
		}
		return mf.getPaths();
	}
	
	
	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
	
	public static int getCharacterPosition(String string){
        
        Matcher slashMatcher = Pattern.compile("/").matcher(string);
        int mIdx = 0;
        while(slashMatcher.find()) {
           mIdx++;
           //当"/"符号第二次出现的位置
           if(mIdx == 3){
              break;
           }
        }
        return slashMatcher.start();
    }
}
