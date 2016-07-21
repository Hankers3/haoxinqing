package com.aebiz.b2b2c.cms.sysback.web.contentlist;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.contentcategory.service.ContentCategoryService;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;
import com.aebiz.b2b2c.cms.contentlist.service.ContentListService;
import com.aebiz.b2b2c.cms.contentlist.vo.ContentListModel;
import com.aebiz.b2b2c.cms.contentlist.vo.ContentListQueryModel;
import com.hxq.mobile.util.email.EmailUtil;
import com.hxq.mobile.util.email.Mail;

@Controller
@RequestMapping("/sysback/contentlist")
public class ContentListController extends BaseController<ContentListModel, ContentListQueryModel> {
    private ContentListService myService;

    @Autowired
    private ContentCategoryService contentCategoryService;

    @Autowired
    private ContentService contentService;

    @Autowired
    public void setMyService(ContentListService bs) {
        this.myService = bs;
        super.setBs(bs);
    }


    public ContentListController() {
        super("cms/sysback/contentlist", "ContentList", ContentListController.class);
    }


    //向页面传递所有的分类的数据
    @Override
    protected void preparedListData(Model model, HttpServletRequest request) {
        List<ContentCategoryModel> contentList = contentCategoryService.getAll();
        model.addAttribute("contentList", contentList);

    }


    /**
     * 修改邮件发送的状态
     *
     * @param request
     * @param uuid
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateState/{uuid}", method = RequestMethod.GET)
    public String updateState(HttpServletRequest request, @PathVariable("uuid") String uuid, Model model) {
        ContentListModel cm = myService.getByUuid(uuid);

        String contentUuid = cm.getContentUuid();
        if (TextUtils.isEmpty(contentUuid)) return super.toList(model, request);
        String sendEmail = cm.getEmail();
        if (TextUtils.isEmpty(sendEmail)) return super.toList(model,request);

        Mail user = new Mail();
      
       
        ContentModel contentModel = contentService.getByUuid(contentUuid);
        String contentUrl = contentModel.getUrl();
        user.setTitle(contentModel.getContentTitle());
      
      
      
        String content = "";
        if (!TextUtils.isEmpty(contentUrl)){
        	content = new StringBuffer("请点击:").append(contentUrl).toString();
        }else {
        	content = contentModel.getIntroduction();
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("content", content);
        map.put("title", contentModel.getContentTitle());
        
        user.setMap(map);
        user.setTo(sendEmail);
        try {
			user.setSubject(MimeUtility.encodeText("好心情推荐文章提示邮件", "utf-8", "B"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        
        boolean isSendSuc = EmailUtil.sendTemplate(user);
        if (!isSendSuc) return super.toList(model, request);
        cm.setState("1");
        myService.update(cm);
        return super.toList(model, request);
    }


}