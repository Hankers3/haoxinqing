package com.hxq.mobile.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.hxq.mobile.entity.common.AppPublish;
import com.hxq.mobile.manage.service.AppManageService;
import com.hxq.mobile.util.UploadFileUtils;
import com.wxcommon.Constants;
import com.wxcommon.pagination.MapWrapper;
import com.wxcommon.pagination.Pagination;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.BoolResult;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Controller
@RequestMapping("/app/manage/publish")
public class AppManageController {
	Logger log = LoggerFactory.getLogger(AppManageController.class);

    final String identy = "/app/manage/publish";// session identy for search condition
	private String viewList = "app/manage/publishList";
	private String viewEdit = "app/manage/publishEdit";

	@Resource(name="com.hxq.mobile.manage.service.AppManageService")
	private AppManageService service;

    /**
     * 进入页面
     */
    @RequestMapping()
    public String index(NativeWebRequest request, Model model) {
        return doSearch(new MapWrapper(new HashMap<String, Object>()), request, model);
    }

    /**
     * 查询
     */
    @RequestMapping(value="search", method=RequestMethod.POST)
    public String search(NativeWebRequest request, Model model,
    		@Pagination(value=identy, newSearch=true) MapWrapper conditions) {
        return doSearch(conditions, request, model);
    }

    /**
	 * 分页跳转
	 */
	@RequestMapping(value="search", method = RequestMethod.GET)
	public String searchByGet(NativeWebRequest request, Model model,
			@Pagination(identy) MapWrapper conditions) {
		return doSearch(conditions, request, model);
	}

	/**
	 * 新增
	 */
	@RequestMapping(value="add")
	public String add() {
		return viewEdit;
	}

    /**
     * 新增保存
     */
    @RequestMapping(value="save", method=RequestMethod.POST)
    public String post(AppPublish bean, WebRequest request, Model model,
			@Pagination(value=identy, history=true) MapWrapper conditions) {
    	String strError = null;
        try {
            BeanUtils.trimStringField(bean);
            strError = verifySave(bean, true);
            if (ObjectUtils.isEmpty(strError)) {
            	bean.setId(IdentityHelper.uuid2());
            	service.insert(bean);
            }
        } catch (Exception e) {
            log.error("", e);
            strError = "保存出错，请稍后再试！";
        }
		return ObjectUtils.isEmpty(strError) ? doSearch(conditions, request, model)
				: doErrorResponse(bean, model, strError, true);
    }

    /**
     * 修改
     */
    @RequestMapping(value="edit/{id}", method=RequestMethod.GET)
    public String edit(@PathVariable("id") String id, Model model) {
        try {
            model.addAttribute("po", service.select(new AppPublish(id)));
        } catch (Exception e) {
        	log.error(id, e);
        }
        return viewEdit;
    }

    /**
     * 修改保存
     */
    @RequestMapping(value="save", method=RequestMethod.PUT)
    public String put(AppPublish bean, WebRequest request, Model model,
    		@Pagination(value=identy, history=true) MapWrapper conditions) {
    	String strError = null;
        try {
            BeanUtils.trimStringField(bean);
            strError = verifySave(bean, false);
            if(ObjectUtils.isEmpty(strError)) service.update(bean);
        } catch (Exception e) {
            log.error(bean.getId(), e);
            strError = "修改出错，请稍后再试！";
        }
        return ObjectUtils.isEmpty(strError) ? doSearch(conditions, request, model)
        		: doErrorResponse(bean, model, strError, false);
    }

    /**
     * 删除
     */
    @RequestMapping(value="delete", method=RequestMethod.POST)
    public String delete(@RequestParam("pk") String pk, WebRequest request, Model model,
			@Pagination(value=identy, history=true) MapWrapper conditions) {
    	String strError = null;
        try {
            service.delete(new AppPublish(pk));
        } catch (Exception e) {
        	log.error(pk, e);
			strError = "删除出错，请稍后再试！";
        }
		if (!ObjectUtils.isEmpty(strError))
			model.addAttribute("message", strError);
		return this.doSearch(conditions, request, model);
    }

    /**
     * 上传
     */
    @RequestMapping(value="upload")
    public @ResponseBody BoolResult uploadfile(@RequestParam MultipartFile file) {
    	BoolResult br = null;
        try {
        	if(file.isEmpty()) {
            	br = BoolResult.error("上传文件为空，请重新选择文件！");
            } else {
            	br = BoolResult.right(UploadFileUtils.uploadFileToRemort(file));
            }
        } catch (Exception e) {
        	log.error("", e);
        	br = BoolResult.error("上传出错，请稍后再试！");
        }
        return br;
    }

    /**
     * 处理查询请求
     * @param request
     * @param model
     * @return
     */
    private String doSearch(MapWrapper conditions, WebRequest request, Model model) {
    	Map<String, Object> form = conditions.getMap();
		try {
			List<Map<String, Object>> searchResult = service.selectList(form, true);
			PaginationHelper.saveServerCondition(request, identy, form);
			model.addAttribute(Constants.SEARCH_RESULT_LIST, searchResult);
			model.addAllAttributes(form);
		} catch (Exception e) {
			log.error("", e);
		}
        return viewList;
    }

	/**
	 * 保存出错时返回处理
	 */
	private String doErrorResponse(AppPublish bean, Model model, String error, boolean isNew) {
		if(isNew == true) bean.setId(null);
		model.addAttribute("po", bean);
		model.addAttribute("message", error);
		return viewEdit;
	}

    /**
     * 保存校验
     *
     * @param bean  保存对象
     * @param isNew true:新增;false:修改.
     * @return
     */
    private String verifySave(AppPublish bean, boolean isNew) {
        StringBuffer sbf = new StringBuffer(1000);
        if (isNew == true && !ObjectUtils.isEmpty(bean.getId()))
            StringUtils.appendBuffer(sbf, "不能添加已存在发布版本！", sbf.length() > 0 ? "\r\n" : null);
        if (isNew == false && ObjectUtils.isEmpty(bean.getId()))
            StringUtils.appendBuffer(sbf, "无法修改无主键的发布版本！", sbf.length() > 0 ? "\r\n" : null);
        if (ObjectUtils.isEmpty(bean.getVersion()))
            StringUtils.appendBuffer(sbf, "版本号不能为空！", sbf.length() > 0 ? "\r\n" : null);
        if (ObjectUtils.isEmpty(bean.getUrl()))
            StringUtils.appendBuffer(sbf, "下载地址不能为空！", sbf.length() > 0 ? "\r\n" : null);
        return sbf.toString();
    }
}
