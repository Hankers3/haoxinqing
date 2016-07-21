package com.aebiz.b2b2c.websiteoperation.websiteoperate.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.dao.WebsiteOperateDAO;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.service.WebsiteOperateService;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateQueryModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
@Transactional
public class WebsiteOperateServiceImpl extends
		BaseServiceImpl<WebsiteOperateModel, WebsiteOperateQueryModel>
		implements WebsiteOperateService {

	private WebsiteOperateDAO myDao = null;

	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(WebsiteOperateDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(WebsiteOperateModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(WebsiteOperateModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(WebsiteOperateModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过paramContent获取参数值展示数据
	 */
	@Override
	public List<DataTablesPageParam> getParamValueShowNameByParamContent(
			String paramContent) {
		List<DataTablesPageParam> showNameList = new ArrayList<DataTablesPageParam>();
		if (!StringUtil.isEmpty(paramContent)) {
			JSONArray jsonArr = JSONArray.parseArray(paramContent);
			for (Iterator<Object> it = jsonArr.iterator(); it.hasNext();) {
				Object o = it.next();
				JSONObject jo = (JSONObject) o;
				DataTablesPageParam dtpp = (DataTablesPageParam) JSON
						.parseObject(jo.toJSONString(),
								DataTablesPageParam.class);
				showNameList.add(dtpp);
			}
			return showNameList;
		}
		return null;
	}

	/**
	 * 校验编号是否重复
	 */
	@Override
	public boolean checkKeyExist(String resourceKey) {
		return this.myDao.checkKeyExist(resourceKey);
	}
}