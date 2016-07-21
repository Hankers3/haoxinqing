package com.aebiz.b2b2c.websiteoperation.sysback.web.customerdisease;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productmain.vo.ProductImportConst;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionModel;
import com.aebiz.b2b2c.product.utils.ExcelUtils;
import com.aebiz.b2b2c.websiteoperation.customerdisease.service.CustomerDiseaseService;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseImportConst;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseModel;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseQueryModel;
import com.aebiz.b2b2c.websiteoperation.customerdiseasere.service.CustomerDiseaseReService;
import com.aebiz.b2b2c.websiteoperation.customerdiseasere.vo.CustomerDiseaseReModel;

@Controller
@RequestMapping("/sysback/customerdisease")
public class CustomerDiseaseController extends BaseController<CustomerDiseaseModel,CustomerDiseaseQueryModel>{
	private CustomerDiseaseService myService;
	@Autowired
	public void  setMyService(CustomerDiseaseService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public CustomerDiseaseController(){
		super("websiteoperation/sysback/customerdisease","CustomerDisease",CustomerDiseaseController.class);
	}
	@Autowired
	private CustomerDiseaseReService cdrService;

	 /**
     * 跳转到药品导入页面
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/customerDiseaseImport", method = RequestMethod.GET)
    public String customerDiseaseImport(Model model, HttpServletRequest request) {
            return "websiteoperation/sysback/customerdisease/CustomerDiseaseImport";

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
    @RequestMapping(value = "/toCustomerDiseaseView/{uuid}", method = RequestMethod.GET)
    public String toCustomerDiseaseView(Model model, @PathVariable("uuid")
    String uuid, HttpServletRequest request) {
        CustomerDiseaseModel  m =myService.getByUuid(uuid);
       
        CustomerDiseaseReModel cdr = cdrService.getCustomerDiseaseReModel(uuid);
        if(null!=m){
        	if(cdr !=null){
        		m.setClinicalFeature(cdr.getClinicalFeature());
        		m.setRemedy(cdr.getRemedy());
        		m.setMorbidity(cdr.getMorbidity());
        		m.setPrecaution(cdr.getPrecaution());
        	}
        	model.addAttribute("m", m);
        }
       return "websiteoperation/sysback/customerdisease/CustomerDiseaseView";
    }
	
	/**
     * 导出疾病模板
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
            String tableName = "疾病模板";
            String[] headr = new String[CustomerDiseaseImportConst.PARAMKEYLIST.size()];
            String[] columns = new String[CustomerDiseaseImportConst.PARAMKEYLIST.size()];
            for (int i = 0; i < CustomerDiseaseImportConst.PARAMKEYLIST.size(); i++) {
                    headr[i] = MessageHelper.getMessage(CustomerDiseaseImportConst.PARAMKEYLIST
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
                                            CustomerDiseaseModel pmm = (CustomerDiseaseModel) object;
                                            CustomerDiseaseReModel cdr = new CustomerDiseaseReModel();
                                            myService.create(pmm);
                                            if(pmm !=null){
                                            	cdr.setDiseaseUuid(pmm.getUuid());
                                            }
                                            cdr.setClinicalFeature(pmm.getClinicalFeature());
                                            cdr.setMorbidity(pmm.getMorbidity());
                                            cdr.setRemedy(pmm.getRemedy());
                                            cdr.setPrecaution(pmm.getPrecaution());
                                            cdrService.create(cdr);
                                    } catch (Exception e) {
                                            erroMessage.append("第" + i + "条数据问题;");
                                    }
                                    i++;
                            }
                    }
            }
            model.addAttribute("erroMessage", erroMessage);
            return "websiteoperation/sysback/customerdisease/CustomerDiseaseList";
    }

	
	
}