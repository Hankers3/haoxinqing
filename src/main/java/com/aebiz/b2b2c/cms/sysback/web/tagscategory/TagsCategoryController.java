package com.aebiz.b2b2c.cms.sysback.web.tagscategory;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.tagscategoy.service.TagsCategoryService;
import com.aebiz.b2b2c.cms.tagscategoy.vo.TagsCategoryModel;
import com.aebiz.b2b2c.cms.tagscategoy.vo.TagsCategoryQueryModel;
import com.aebiz.b2b2c.cms.tagscategoy.vo.TagsType;

@Controller
@RequestMapping("/tagscategory")
public class TagsCategoryController extends
		BaseController<TagsCategoryModel, TagsCategoryQueryModel> {
	private TagsCategoryService myService;

	@Autowired
	public void setMyService(TagsCategoryService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public TagsCategoryController() {
		super("cms/sysback/tagscategory", "TagsCategory",
				TagsCategoryController.class);
	}

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
					MessageHelper.getMessage("tagscategory." + name + ".empty"));
		} else {
			if (maxLength == 0) {// 不校验长度
				return;
			}
			if (value.length() > maxLength) {
				this.putErrorMsg(
						name,
						MessageHelper.getMessage("tagscategory." + name
								+ ".maxlength")
								+ maxLength);
			}

			if (minLength == 0) {// 不校验最小长度
				return;
			}
			if (value.length() < minLength) {
				this.putErrorMsg(
						name,
						MessageHelper.getMessage("tagscategory." + name
								+ ".maxlength")
								+ minLength);
			}
		}
	}

	@Override
	protected boolean checkAdd(Model model, TagsCategoryModel m,
			HttpServletRequest request) {
		/* 校验标签名称 */
		checkFieldName("categoryName", m.getCategoryName(), 1, 100);
		/* 校验标签备注 */
		checkFieldName("note", m.getNote(), 1, 200);

		model.addAttribute("m", m);
		preparedAddData(model, request);
		// 跳转页面，并返回错误
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"cms/sysback/tagscategory/tagscategoryAdd");
			return false;
		}
		return true;
	}

	@Override
	protected boolean checkUpdate(Model model, TagsCategoryModel m,
			HttpServletRequest request) {
		/* 校验标签名称 */
		checkFieldName("categoryName", m.getCategoryName(), 1, 100);
		/* 校验标签备注 */
		checkFieldName("note", m.getNote(), 1, 200);

		model.addAttribute("m", m);
		preparedUpdateData(model, request);

		// 跳转页面，并返回错误
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"cms/sysback/tagscategory/tagscategoryUpdate");
			return false;
		}
		return true;
	}

	@Override
	protected void preparedAddData(Model model, HttpServletRequest request) {
		/* 标签类型 */
		List<DataTablesPageParam> types = new ArrayList<DataTablesPageParam>();
		for (TagsType q : TagsType.values()) {
			System.out.println(q.getValue() + ", " + q.getName());
			DataTablesPageParam data = new DataTablesPageParam();
			data.setValue(q.getValue());
			data.setName(q.getName());
			types.add(data);
		}

		model.addAttribute("tagsList", types);
	}

	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		/* 标签类型 */
		List<DataTablesPageParam> types = new ArrayList<DataTablesPageParam>();
		for (TagsType q : TagsType.values()) {
			System.out.println(q.getValue() + ", " + q.getName());
			DataTablesPageParam data = new DataTablesPageParam();
			data.setValue(q.getValue());
			data.setName(q.getName());
			types.add(data);
		}

		model.addAttribute("tagsList", types);
	}

	@Override
	protected void preparedUpdateData(Model model, HttpServletRequest request) {
		/* 标签类型 */
		List<DataTablesPageParam> types = new ArrayList<DataTablesPageParam>();
		for (TagsType q : TagsType.values()) {
			System.out.println(q.getValue() + ", " + q.getName());
			DataTablesPageParam data = new DataTablesPageParam();
			data.setValue(q.getValue());
			data.setName(q.getName());
			types.add(data);
		}

		model.addAttribute("tagsList", types);
	}

}