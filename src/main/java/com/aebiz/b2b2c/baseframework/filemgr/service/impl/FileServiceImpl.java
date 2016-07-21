package com.aebiz.b2b2c.baseframework.filemgr.service.impl;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.filemgr.dao.FileDAO;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileQueryModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileServiceImpl extends BaseServiceImpl<FileModel, FileQueryModel>
  implements FileService
{
  private FileDAO dao = null;

  @Autowired
  private UuidService us;

  @Autowired
  private void setDao(FileDAO dao) { this.dao = dao;
    super.setDao(dao);
  }

  public String create(FileModel m)
  {
    m.setUuid(this.us.getNextUuid());
    m.setDelFlag("1");
    m.setOper(LoginUserHelper.getLoginUserUuid());
    m.setOpeTime(DateFormatHelper.getNowTimeStr());

    String ret = super.create(m);
    return ret;
  }

  public void update(FileModel m)
  {
    m.setDelFlag("1");
    m.setOper(LoginUserHelper.getLoginUserUuid());
    m.setOpeTime(DateFormatHelper.getNowTimeStr());
    super.update(m);
  }

  public void delete(FileModel m)
  {
    m.setDelFlag("2");
    m.setOper(LoginUserHelper.getLoginUserUuid());
    m.setOpeTime(DateFormatHelper.getNowTimeStr());
    super.update(m);
  }

  private FileModel getByFileName(String fileName)
  {
    return this.dao.getFileModelByFileName(fileName);
    // return null;
  }

  public FileModel getOneFileModel(String fileName)
  {
    FileModel fm = getByFileName(fileName);

    if (fm == null)
    {
      fm = new FileModel();
      fm.setFileName(fileName);

      create(fm);

      fm = getByFileName(fileName);
    }
    return fm;
  }

  public String getOneFileUrl(String fileName) {
	  String remotePath = getOneFileModel(fileName).getRemotePaths();
	  if(!StringUtil.isEmpty(remotePath)){
		  remotePath = formatUrl(remotePath);
	  }
    return remotePath;
  }
  
  
  public String formatUrl(String url) {
		if (("".equals(url)) || ("null".equals(url))) {
			return "";
		}

		String imageUrl = MessageHelper.getMessage("imageUrl");
		if (("".equals(imageUrl)) || ("null".equals(imageUrl))) {
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