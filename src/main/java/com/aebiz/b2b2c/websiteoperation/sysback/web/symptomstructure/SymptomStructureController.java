package com.aebiz.b2b2c.websiteoperation.sysback.web.symptomstructure;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.product.utils.ExcelUtils;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseModel;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.service.SymptomStructureService;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.vo.SymptomStructureImportConst;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.vo.SymptomStructureModel;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.vo.SymptomStructureQueryModel;

@Controller
@RequestMapping("/sysback/symptomstructure")
public class SymptomStructureController extends BaseController<SymptomStructureModel,SymptomStructureQueryModel>{
	private SymptomStructureService myService;
	@Autowired
	public void  setMyService(SymptomStructureService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public SymptomStructureController(){
		super("websiteoperation/sysback/symptomstructure","SymptomStructure",SymptomStructureController.class);
	}
	

        /**
        * 跳转到症状导入页面
        * 
        * @param model
        * @param request
        * @return
        */
       @RequestMapping(value = "/symptomStructureImport", method = RequestMethod.GET)
       public String symptomStructureImport(Model model, HttpServletRequest request) {
               return "websiteoperation/sysback/symptomstructure/SymptomStructureImport";

       }
       
       
       /**
        * 
        * @Description: (跳转到view页面)    
        * @author XP  
        * @param model
        * @param m
        * @param request
        * @return
        * @date 2016-1-9 下午3:40:53
        */
       @RequestMapping(value = "/toSymptomStructureView/{uuid}", method = RequestMethod.GET)
       public String toSymptomStructureView(Model model, @PathVariable("uuid")
       String uuid, HttpServletRequest request) {
           SymptomStructureModel  m =myService.getByUuid(uuid);
           if(null!=m){
           model.addAttribute("m", m);
           }
       return "websiteoperation/sysback/symptomstructure/SymptomStructureView";
       }
       
       /**
        * 导出症状模板
        * 
        * @param response
        * @param request
        * @return
        * @throws Exception
        */
       @ResponseBody
       @RequestMapping(value = "/productTemplateExport", method = RequestMethod.GET)
       public String productTemplateExport(HttpServletResponse response,
                       HttpServletRequest request) throws Exception {
               String tableName = "症状模板";
               String[] headr = new String[SymptomStructureImportConst.PARAMKEYLIST.size()];
               String[] columns = new String[SymptomStructureImportConst.PARAMKEYLIST.size()];
               for (int i = 0; i < SymptomStructureImportConst.PARAMKEYLIST.size(); i++) {
                       headr[i] = MessageHelper.getMessage(SymptomStructureImportConst.PARAMKEYLIST
                                       .get(i));
               }

               List<Object> tmList = new ArrayList<Object>();
               ExcelUtils.writeExcelByObject(tmList, response, tableName, headr,
                               columns);
               return null;
       }

       
       
       /**
        * 上传图片包括主图和多角度视图
        * 
        * @param myfiles
        * @param model
        * @param request
        * @param response
        * @return
        * @throws Exception
        */
       @RequestMapping(value = "/importData", method = { RequestMethod.POST })
       public String importData(
                       @RequestParam(value = "myExcelFile") MultipartFile[] files,
                       Model model, HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
               // 调用上传excel的方法
               List<Object> list = myService.updloadExcel(files);
               StringBuffer erroMessage = new StringBuffer("");
               if (list != null && list.size() > 0) {
                       int i = 2;
                       for (Object object : list) {
                               if (object != null) {
                                       try {
                                               SymptomStructureModel pmm = (SymptomStructureModel) object;
                                               myService.create(pmm);
                                       } catch (Exception e) {
                                               erroMessage.append("第" + i + "条数据问题;");
                                       }
                                       i++;
                               }
                       }
               }
               model.addAttribute("erroMessage", erroMessage);

               return "websiteoperation/sysback/symptomstructure/SymptomStructureList";
       }

       
	
	
	
}