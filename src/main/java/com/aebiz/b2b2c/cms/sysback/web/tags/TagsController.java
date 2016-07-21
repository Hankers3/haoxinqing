package com.aebiz.b2b2c.cms.sysback.web.tags;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.tags.service.TagsService;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.cms.tags.vo.TagsQueryModel;
import com.aebiz.b2b2c.cms.tagscategoy.service.TagsCategoryService;
import com.aebiz.b2b2c.cms.tagscategoy.vo.TagsCategoryModel;
import com.aebiz.b2b2c.cms.tagscategoyrel.service.TagsCategoyRelService;
import com.aebiz.b2b2c.cms.tagscategoyrel.vo.TagsCategoyRelModel;
import com.aebiz.b2b2c.customermgr.consultrecord.service.ConsultRecordService;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.alibaba.fastjson.JSON;

/**
 * 标签控制器 主要是标签的增删查改调转
 * 
 * @author huangpinpin
 * 
 */
@Controller
@RequestMapping("/sysback/tags")
public class TagsController extends BaseController<TagsModel, TagsQueryModel> {
	private TagsService myService;

	@Autowired
	private TagsCategoryService tagesCategoryService;

	@Autowired
	private TagsCategoyRelService tagsCategoryRelService;

	@Autowired
	public void setMyService(TagsService bs) {
		this.myService = bs;
		super.setBs(bs);
	}
	
	public TagsController() {
		super("cms/sysback/tags", "Tags", TagsController.class);
	}
	
	
        /*注入图文咨询信息*/
        @Autowired
        private  ConsultRecordService consultRecordService;

	/**
	 * 校验字段是否为空和长度
	 * 
	 * @param name
	 *            字段名
	 * @param value
	 *            字段值
	 * @param minLength
	 *            最小长度
	 * @param maxLength
	 *            最大长度
	 */
	private void checkFieldName(String name, String value, int minLength,
			int maxLength) {
		if (StringUtil.isEmpty(value)) {
			this.putErrorMsg(name,
					MessageHelper.getMessage("tags." + name + ".empty"));
		} else {
			if (maxLength == 0) {// 不校验长度
				return;
			}
			if (value.length() > maxLength) {
				this.putErrorMsg(name,
						MessageHelper.getMessage("tags." + name + ".maxlength")
								+ maxLength);
			}

			if (minLength == 0) {// 不校验最小长度
				return;
			}
			if (value.length() < minLength) {
				this.putErrorMsg(name,
						MessageHelper.getMessage("tags." + name + ".maxlength")
								+ minLength);
			}
		}
	}

	@Override
	protected boolean checkAdd(Model model, TagsModel m,
			HttpServletRequest request) {

		checkFieldName("tagName", m.getTagName(), 1, 100);

		model.addAttribute("m", m);
		preparedAddData(model, request);
		// 跳转到页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL, "cms/sysback/tags/TagsAdd");
			return false;
		}
		return true;
	}

	/**
	 * 校验该标签分类uuid是否存在
	 * 
	 * @param categoryUuid
	 *            标签分类uuid
	 * @return
	 */
	protected boolean checkCategory(String categoryUuid) {

		if (StringUtil.isEmpty(categoryUuid)) {
			return false;
		}
		// 根据id查找标签分类
		TagsCategoryModel category = tagesCategoryService
				.getByUuid(categoryUuid);
		if (category == null) {// 分类不存在
			return false;
		}
		return true;
	}

	@Override
	protected boolean checkUpdate(Model model, TagsModel m,
			HttpServletRequest request) {
		checkFieldName("tagName", m.getTagName(), 1, 100);

		model.addAttribute("m", m);
		preparedUpdateData(model, request);
		// 跳转到页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"cms/sysback/tags/TagsUpdate");
			return false;
		}

		return true;
	}
	
	/**
	 * 标签分类列表添加标签
	 * 
	 * @param categoryId
	 *            当前标签分类uuid
	 * @param m
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addRel", method = RequestMethod.GET)
	public String addRel(@RequestParam("categoryUuid") String categoryUuid,
			@RequestParam("tagName") List<String> tagNames,
			HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// 校验标签分类是否存在
		if (!checkCategory(categoryUuid)) {
			jsonMap.put("message", "noexist");// 标签分类不存在
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(JSON.toJSONString(jsonMap));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		// 保存标签数据
		for (int i = 0; i < tagNames.size(); i++) {
			String tagName = tagNames.get(i);
			if (StringUtil.isEmpty(tagName)) {
				continue;
			}
			TagsModel m = new TagsModel();
			m.setTagName(tagName);
			myService.create(m);

			// 保存标签和标签分类关系数据
			TagsCategoyRelModel rel = new TagsCategoyRelModel();
			rel.setCategoryUuid(categoryUuid);
			rel.setTagUuid(m.getUuid());
			tagsCategoryRelService.create(rel);
		}

		jsonMap.put("rsp", Boolean.valueOf(true));
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(jsonMap));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 查询没有关联当前分类的标签
	 * 
	 * @param categoryUuid
	 *            当前分类uuid
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/noExistList/{categoryUuid}")
	public String noExistList(
			@PathVariable("categoryUuid") String categoryUuid,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		// 获取已经关联当前分类标签的uuid
		List<String> tagsUuids = tagsCategoryRelService
				.existTagesUuids(categoryUuid);
		String searchParam = request.getParameter("searchParam");
		List showList = new ArrayList();
		Map pageParamMap = parsePageParam(request);

		TagsQueryModel qm = super.parseQueryModel(request);
		qm.setStagsUuid(tagsUuids);

		int iDisplayStart = ((Integer) pageParamMap.get("iDisplayStart"))
				.intValue();

		int iDisplayLength = ((Integer) pageParamMap.get("iDisplayLength"))
				.intValue();

		int iSortCol_0 = ((Integer) pageParamMap.get("iSortCol_0")).intValue();

		String sortFieldName = (String) pageParamMap.get("mDataProp_"
				+ iSortCol_0);

		String sortTypeString = (String) pageParamMap.get("sSortDir_0");

		Boolean needSort = (Boolean) pageParamMap
				.get("bSortable_" + iSortCol_0);

		if (needSort.booleanValue()) {
			qm.setSortName(sortFieldName);
			qm.setSortType(sortTypeString);
		}

		List listData = this.myService.getByCondition(qm, iDisplayStart,
				iDisplayLength);

		int totalCount = this.myService.getCount(qm);

		for (int i = 0; i < listData.size(); i++) {
			TagsModel m = (TagsModel) listData.get(i);
			showList.add(m);
		}

		Map jsonMap = new HashMap();

		jsonMap.put("sEcho", pageParamMap.get("sEcho"));
		jsonMap.put("iTotalRecords", Integer.valueOf(totalCount));
		jsonMap.put("iTotalDisplayRecords", Integer.valueOf(totalCount));
		jsonMap.put("aaData", showList);

		response.setContentType("charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		out.print(JSON.toJSONString(jsonMap));

		return null;
	}
	
	       

 
 
 /**
  * 重新绑定
  * 
  * @param model
  * @param request
  */
 @RequestMapping(value = { "/toUpdateBindServicestaff/{uuid}" }, method = { RequestMethod.GET })
 public String toUpdateBindServicestaff(Model model,
                 @PathVariable("uuid") String uuid, 
                 @RequestParam("consultUuid") String consultUuid,
                 HttpServletRequest request) {
         if(!StringUtil.isEmpty(consultUuid)){
                 ConsultRecordModel cm = consultRecordService.getByUuid(consultUuid);
                 if(cm !=null){
                         cm.setTagsUuid(uuid);
                         consultRecordService.update(cm);
                 }
         }

         return "redirect:/sysback/consultrecord/toDetail/"+consultUuid;
 }


}