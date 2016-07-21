package com.aebiz.b2b2c.cms.contentlist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.cms.contentlist.service.ContentListService;
import com.aebiz.b2b2c.cms.contentlist.dao.ContentListDAO;
import com.aebiz.b2b2c.cms.contentlist.vo.ContentListModel;
import com.aebiz.b2b2c.cms.contentlist.vo.ContentListQueryModel;

@Service
@Transactional
public class ContentListServiceImpl extends BaseServiceImpl<ContentListModel,ContentListQueryModel> implements ContentListService {
	private ContentListDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ContentListDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ContentListModel m) {
		m.setUuid(us.getNextUuid("ContentList",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ContentListModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ContentListModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	   /**
	     * 
	     * @Description: (多条件查询索取列表)    
	     * @author XP  
	     * @param doctorUuid
	     * @param contentUuid
	     * @param doctorEmail
	     * @return
	     * @date 2016-1-20 下午12:46:51
	     */
        @Override
        public ContentListModel getContentlistByConditions(String doctorUuid, String contentUuid,
                String doctorEmail) {
            return myDao.getContentlistByConditions(doctorUuid,contentUuid,doctorEmail);
        }
}