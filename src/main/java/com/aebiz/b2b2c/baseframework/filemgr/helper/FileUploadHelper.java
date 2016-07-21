package com.aebiz.b2b2c.baseframework.filemgr.helper;

import com.aebiz.b2b2c.baseframework.exception.LogException;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import fm.last.moji.MojiFile;
import fm.last.moji.spring.SpringMojiBean;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper
{
  private static Logger log = LoggerFactory.getLogger(FileUploadHelper.class);

  @Autowired
  private SpringMojiBean moji = null;

  private List<String> needTransCharacterEncoding = new ArrayList();

  @Autowired
  private FileService fileService = null;

  public void setNeedTransCharacterEncoding(List<String> needTransCharacterEncoding)
  {
    this.needTransCharacterEncoding = needTransCharacterEncoding;
  }

  public FileModel uploadFile(String fileName, String content)
  {
    FileModel fm = null;
    try
    {
      fm = this.fileService.getOneFileModel(fileName);

      List list = uploadToMogilefs(fm.getUuid(), content);

      fm.setRemotePaths(formatUrl("" + list.get(0)));
      this.fileService.update(fm);
    } catch (Exception e) {
      e.printStackTrace();

      throw new LogException("file.upload", "uploadFile error", e);
    }
    return fm;
  }

  public FileModel uploadFiles(InputStream in, String fileName) {
    FileModel fm = null;
    try {
      fm = this.fileService.getOneFileModel(fileName);
      List list = uploadToMogilefs(fm.getUuid(), in);
      fm.setRemotePaths(formatUrl(((URL)list.get(0)).toString()));
      this.fileService.update(fm);
    } catch (Exception e) {
      throw new LogException("file.upload", "uploadFiless error", e);
    }
    return fm;
  }

  public List<URL> uploadToMogilefs(String uuid, InputStream in) throws Exception
  {
    MojiFile mf = this.moji.getFile("MyFileKey" + uuid);

    OutputStream out = null;
    try {
      out = mf.getOutputStream();

      byte[] bs = new byte[1024];
      int bytesRead = 0;
      while (-1 != (bytesRead = in.read(bs, 0, bs.length))) {
        out.write(bs, 0, bytesRead);
      }
      out.flush();
    } catch (Exception err) {
      throw new LogException("file.upload", "uploadToMogilefs error,file==", err);
    }
    finally {
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

  public List<FileModel> uploadFiles(MultipartFile[] files, String[] fileNames)
  {
    List retList = new ArrayList();
    try {
      for (int i = 0; i < files.length; i++)
      {
        String fileName = fileNames[i];

        FileModel fm = this.fileService.getOneFileModel(fileName);

        List list = uploadToMogilefs(fm.getUuid(), files[i]);

        fm.setRemotePaths(formatUrl("" + list.get(0)));

        this.fileService.update(fm);

        retList.add(fm);
      }
    }
    catch (Exception e) {
      throw new LogException("file.upload", "uploadFiless error", e);
    }
    return retList;
  }

  public void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName)
  {
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try
    {
      response.setContentType("charset=UTF-8");
      response.setCharacterEncoding("UTF-8");
      request.setCharacterEncoding("UTF-8");

      FileModel fm = this.fileService.getOneFileModel(fileName);
      String mogilefsPath = fm.getRemotePaths();

      response.setHeader("Content-disposition", "attachment; filename=" + new String(fm.getFileName().getBytes("gb2312"), "ISO8859-1"));

      URL url = new URL(mogilefsPath);

      bis = new BufferedInputStream(url.openStream());
      bos = new BufferedOutputStream(response.getOutputStream());
      byte[] buff = new byte[1024];
      int bytesRead = 0;

      int lastPointSite = fm.getFileName().lastIndexOf(".");
      String fileType = fm.getFileName().substring(lastPointSite);
      boolean needTrans = this.needTransCharacterEncoding.contains(fileType);

      while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
        if (needTrans) {
          String tempS = new String(buff, "utf-8");
          bos.write(tempS.getBytes());
          buff = new byte[1024];
          continue;
        }bos.write(buff, 0, bytesRead);
      }
    }
    catch (Exception err)
    {
      throw new LogException("file.downLoad", "downloadFile error，fileName=" + fileName, err);
    }
    finally {
      try {
        bis.close();
      }
      catch (Exception err) {
        log.error("downloadFile bis.close error", err);
      }
      try {
        bos.close();
      }
      catch (Exception err) {
        log.error("downloadFile bos.close error", err);
      }
    }
  }

  public String getFileName(MultipartFile file)
    throws Exception
  {
    String filename = file.getOriginalFilename();
    int slashIndex = filename.indexOf("/");
    if (slashIndex >= 0) {
      filename = filename.substring(slashIndex + 1);
    }
    return filename;
  }

  private List<URL> uploadToMogilefs(String uuid, MultipartFile file)
    throws Exception
  {
    MojiFile mf = this.moji.getFile("MyFileKey" + uuid);

    OutputStream out = null;
    InputStream in = null;
    try {
      out = mf.getOutputStream();
      in = file.getInputStream();

      byte[] bs = new byte[1024];
      int bytesRead = 0;
      while (-1 != (bytesRead = in.read(bs, 0, bs.length))) {
        out.write(bs, 0, bytesRead);
      }
      out.flush();
    }
    catch (Exception err) {
      throw new LogException("file.upload", "uploadToMogilefs error,file==" + file, err);
    }
    finally {
      try {
        in.close();
      }
      catch (Exception e) {
        log.error("uploadToMogilefs in.close error", e);
      }
      try {
        out.close();
      }
      catch (Exception e) {
        log.error("uploadToMogilefs out.close error", e);
      }
    }
    return mf.getPaths();
  }

  private List<URL> uploadToMogilefs(String uuid, String content)
    throws Exception
  {
    MojiFile mf = this.moji.getFile("MyFileKey" + uuid);

    OutputStream out = null;
    try {
      out = mf.getOutputStream();
      out.write(content.getBytes());
      out.flush();
    }
    catch (Exception err) {
      throw new LogException("file.upload", "uploadToMogilefs error,uuid==" + uuid, err);
    }
    finally {
      try {
        out.close();
      }
      catch (Exception e) {
        log.error("uploadToMogilefs out.close error", e);
      }
    }
    return mf.getPaths();
  }

  public String formatUrl(String url)
  {
    if (url==null || "".equals(url) || "null".equals(url)) {
      return "";
    }

    String imageUrl = MessageHelper.getMessage("imageUrl");
    if (imageUrl==null || "".equals(imageUrl) || "null".equals(imageUrl)) {
      return url;
    }

    if ((url.startsWith("http://")) || (url.startsWith("https://"))) {
      int index = url.indexOf("://");
      url = url.substring(index + 3);

      int index2 = url.indexOf("/");
      url = url.substring(index2 + 1);
    }

    if (url.startsWith("/")) {
      int index = url.indexOf("/");
      url = url.substring(index + 1);
    }

    if (imageUrl.startsWith("/"))
      url = imageUrl + url;
    else {
      url = imageUrl + "/" + url;
    }

    return url;
  }
}