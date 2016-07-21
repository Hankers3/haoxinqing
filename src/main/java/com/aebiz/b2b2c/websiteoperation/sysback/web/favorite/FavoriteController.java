package com.aebiz.b2b2c.websiteoperation.sysback.web.favorite;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.websiteoperation.favorite.service.FavoriteService;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteModel;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteQueryModel;
import com.alibaba.fastjson.JSON;




@Controller
@RequestMapping("/sysback/favorite")
public class FavoriteController extends BaseController<FavoriteModel,FavoriteQueryModel>{
	
	@Autowired
	private ContentService contentService;
	
	private FavoriteService myService;
	@Autowired
	public void  setMyService(FavoriteService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public FavoriteController(){
		super("websiteoperation/sysback/favorite","Favorite",FavoriteController.class);
	}

	
	/**
	 * 跳转到详情页
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/toDetail/{contentUuid}" }, method = { RequestMethod.GET })
	public String toDetail(@PathVariable("contentUuid") String contentUuid,Model model,
			HttpServletRequest request) {
		 
		   ContentModel  m = contentService.getByUuid(contentUuid);
		   model.addAttribute("m", m);
		
		return "websiteoperation/sysback/favorite/FavoriteDetail";
	}
	
	
	
	/**
	 * 跳转到患者页面
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value={"/toCustomerList"},method={RequestMethod.GET})
	public String toCustomerList( Model model, HttpServletRequest request){
		
		return "websiteoperation/sysback/favorite/FavoriteCustomerList";
	}
	
	/**
	 * 
	 * @Description: (重写queryList方法)    
	 * @author XP  
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 * @date 2016-1-4 下午4:37:59
	 */
	@Override
	@RequestMapping("/queryList")
	public String queryList(HttpServletResponse response, HttpServletRequest request) throws Exception {
	    List<FavoriteModel> showList = new ArrayList<FavoriteModel>();

            Map pageParamMap = parsePageParam(request);

            FavoriteQueryModel qm = parseQueryModel(request);

            qm = preparedQMFixValue(qm);

            int iDisplayStart = ((Integer)pageParamMap.get("iDisplayStart")).intValue();

            int iDisplayLength = ((Integer)pageParamMap.get("iDisplayLength")).intValue();

            int iSortCol_0 = ((Integer)pageParamMap.get("iSortCol_0")).intValue();

            String sortFieldName = (String)pageParamMap.get("mDataProp_" + iSortCol_0);

            String sortTypeString = (String)pageParamMap.get("sSortDir_0");

            Boolean needSort = (Boolean)pageParamMap.get("bSortable_" + iSortCol_0);

            if (needSort.booleanValue()) {
              qm.setSortName(sortFieldName);
              qm.setSortType(sortTypeString);
            }

            List<FavoriteModel> listData = this.myService.getByConditionq(qm, iDisplayStart, iDisplayLength);
            
            int totalCount = this.myService.getCount(qm);
            
           /* for (int i = 0; i < listData.size(); ++i) {
               FavoriteModel m = (FavoriteModel)listData.get(i);
                if(!StringUtil.isEmpty(m.getContentUuid())){
                    ContentModel contentModel =  contentService.getByUuid(m.getContentUuid());
                   if(null!=contentModel){
                  showList.add(m);
                   }                }
           }*/
            if(listData != null && listData.size() > 0){
            	showList = listData;
            }else{
            	showList = new ArrayList<FavoriteModel>();
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
	
	
	
	
	
	
	
}