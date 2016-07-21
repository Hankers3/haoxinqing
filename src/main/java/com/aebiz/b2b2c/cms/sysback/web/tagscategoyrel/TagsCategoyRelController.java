package com.aebiz.b2b2c.cms.sysback.web.tagscategoyrel;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.cms.sysback.web.tagscategoyrel.vo.TagsCategoyRelWebModel;
import com.aebiz.b2b2c.cms.tagscategoy.service.TagsCategoryService;
import com.aebiz.b2b2c.cms.tagscategoy.vo.TagsCategoryModel;
import com.aebiz.b2b2c.cms.tagscategoyrel.service.TagsCategoyRelService;
import com.aebiz.b2b2c.cms.tagscategoyrel.vo.TagsCategoyRelModel;
import com.aebiz.b2b2c.cms.tagscategoyrel.vo.TagsCategoyRelQueryModel;

@Controller
@RequestMapping("/tagscategoyrel")
public class TagsCategoyRelController extends
		BaseController<TagsCategoyRelModel, TagsCategoyRelQueryModel> {
	private TagsCategoyRelService myService;

	@Autowired
	public void setMyService(TagsCategoyRelService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	@Autowired
	private TagsCategoryService tagsCategoryService;

	public TagsCategoyRelController() {
		super("cms/sysback/tagscategoyrel", "TagsCategoyRel",
				TagsCategoyRelController.class);
	}

	@Override
	protected boolean checkUpdate(Model model, TagsCategoyRelModel m,
			HttpServletRequest request) {
		return super.checkUpdate(model, m, request);
	}

	/**
	 * 关联标签到当前分类
	 * 
	 * @param needRelUuids
	 *            所要关联的标签uuid
	 * @param categoryUuid
	 *            当前标签分类uuid
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/relation", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> doRelation(
			@RequestParam("selectOne") List<String> needRelUuids,
			@RequestParam("categoryUuid") String categoryUuid,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		TagsCategoryModel tagsCategory = tagsCategoryService
				.getByUuid(categoryUuid);
		if (tagsCategory == null) {
			// 不存在的分类
		}
		// 批量关联
		this.myService.relation(needRelUuids, categoryUuid);

		jsonMap.put("rsp", Boolean.valueOf(true));
		return jsonMap;
	}

	/**
	 * 批量更新
	 * 
	 * @param selectRelUuids
	 *            所要更新uuid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/batchUpdate", method = RequestMethod.GET)
	public Map<String, Object> batchUpdate(
			@RequestParam("selectOne") List<String> selectRelUuids,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// 批量更新
		this.myService.batchUpdate(selectRelUuids);

		jsonMap.put("rsp", Boolean.valueOf(true));

		return jsonMap;
	}

	/**
	 * 查询列表
	 * 
	 * @param categoryUuid
	 *            所在标签分类Uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toRelList/{categoryUuid}", method = RequestMethod.GET)
	public String toRelList(@PathVariable("categoryUuid") String categoryUuid,
			Model model, HttpServletRequest request) {
		TagsCategoyRelWebModel webModel = new TagsCategoyRelWebModel();
		webModel.setCategoryUuid(categoryUuid);
		model.addAttribute("web", webModel);
		return super.toList(model, request);
	}

}