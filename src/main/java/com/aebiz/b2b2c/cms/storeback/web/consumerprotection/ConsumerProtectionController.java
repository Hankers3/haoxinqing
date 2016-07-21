package com.aebiz.b2b2c.cms.storeback.web.consumerprotection;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionQueryModel;
import com.aebiz.b2b2c.cms.protectionproductrel.service.ProtectionProductRelService;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelModel;
import com.aebiz.b2b2c.cms.protectionstorerel.service.ProtectionStoreRelService;
import com.aebiz.b2b2c.cms.protectionstorerel.vo.ProtectionStoreRelModel;
import com.aebiz.b2b2c.cms.storeback.service.consumerprotection.StoreConsumerProtectionService;
import com.aebiz.b2b2c.cms.storeback.service.protectionproductrel.StoreProtectionProductRelService;
import com.aebiz.b2b2c.cms.storeback.web.consumerprotection.vo.ConsumerProductWebModel;
import com.aebiz.b2b2c.cms.storeback.web.consumerprotection.vo.ConsumerProtectionWebModel;
import com.alibaba.fastjson.JSON;

@Controller("StoreConsumerProtectionController")
@RequestMapping("/store/consumerprotection")
public class ConsumerProtectionController extends
		BaseController<ConsumerProtectionModel, ConsumerProtectionQueryModel> {

	private StoreConsumerProtectionService myService;

	@Autowired
	public void setMyService(StoreConsumerProtectionService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	
	String storeId="aebiz";
	
	//商品权益对象
	@Autowired
	private ProtectionStoreRelService psrService;
	
	//商户-权益-商品对象
	@Autowired
	private ProtectionProductRelService pprService;
	
	//商户后台的商品权益对象
	@Autowired
	private StoreProtectionProductRelService spprService;

	public ConsumerProtectionController() {
		super("cms/storeback/consumerprotection", "ConsumerProtection",ConsumerProtectionController.class);
	}

	/**
	 * 通过商户id来得到商户参与权益保障服务和没有参与权益保障服务的集合
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/searchStoreConProtect")
	public String searchStoreConProtect(Model model) {

		// TODO 临时的商户id用于测试
		/* 参与权益保障服务集合 */
		List<ConsumerProtectionModel> list = myService.getAttain(storeId);

		// TODO 临时的商户id用于测试
		/* 没有参与权益保障服务集合 */
		List<ConsumerProtectionModel> noList = myService.getNonparticipant(storeId);

		ConsumerProtectionWebModel webModel = new ConsumerProtectionWebModel();
		webModel.setList(list);
		webModel.setNoList(noList);
		model.addAttribute("m", webModel);
		return "cms/storeback/consumerprotection/ConsumerProtectionList";
	}

	/**
	 * 保存权益的id和商户id
	 * 
	 * @param protectionUuid
	 *            权益的uuid
	 */
	@RequestMapping("/saveProtectionStore")
	@ResponseBody
	public String saveProtectionStore(@RequestParam("protectionUuid") String protectionUuid) {
		ProtectionStoreRelModel pm = new ProtectionStoreRelModel();
		pm.setProtectionUuid(protectionUuid);
		// TODO 临时的商户id用于测试
		pm.setStoreUuid(storeId); 
		this.psrService.create(pm);
		String json = JSON.toJSONString("success");
		return json;
	}

	/**
	 * 保存商户选择的权益服务
	 * @param checkIds	权益的id
	 * @return
	 */
	@RequestMapping("/saveProtectionStores")
	@ResponseBody
	public String saveProtectionStores(@RequestParam("checkIds") List<String> checkIds){
	
//		for(String protectionUuid : checkIds){
//			ProtectionStoreRelModel pm = new ProtectionStoreRelModel();
//			pm.setProtectionUuid(protectionUuid);
//			// TODO 临时的商户id用于测试
//			pm.setStoreUuid(storeId); 
//			this.psrService.create(pm);
//		}
		
		psrService.saveProtectionStores(checkIds, storeId);
		String json = JSON.toJSONString("success");
		return json;
	}
	
	/**
	 * 移除商品关联
	 * 
	 * @param model
	 * @param protectionUuid
	 *            权益的uuid
	 * @return
	*/
	@RequestMapping("/ProtectionProductToDel/{protectionUuid}")
	public String viewProductDel(Model model, @PathVariable("protectionUuid") String protectionUuid) {
		model.addAttribute("protectionUuid", protectionUuid);
		return "cms/storeback/consumerprotection/ConsumerProtectionProductToDel";
	}
	
	/**
	 * 添加商品关联
	 * @param model
	 * @param protectionUuid
	 * @return
	 */
	@RequestMapping("/ProtectionProductToAdd/{protectionUuid}")
	public String viewProductAdd(Model model, @PathVariable("protectionUuid") String protectionUuid) {
		model.addAttribute("protectionUuid", protectionUuid);
		return "cms/storeback/consumerprotection/ConsumerProtectionProductToAdd";
	}
	
	/**
	 * 查询需要添加关联的商品
	 * @param String protectionUuid 权益的id
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/protectionProductAddQueryList/{protectionUuid}")
	public String protectionProductAddQueryList(HttpServletResponse response,HttpServletRequest request,@PathVariable("protectionUuid") String protectionUuid) throws IOException{
		
		List<ConsumerProductWebModel> showList = new ArrayList<ConsumerProductWebModel>();
		
		Map<String, Object> pageParamMap = parsePageParam(request);

		ConsumerProtectionQueryModel  qm = parseQueryModel(request);


		int iDisplayStart = ((Integer) pageParamMap.get("iDisplayStart")).intValue();

		int iDisplayLength = ((Integer) pageParamMap.get("iDisplayLength")).intValue();

		int iSortCol_0 = ((Integer) pageParamMap.get("iSortCol_0")).intValue();

		String sortFieldName = (String) pageParamMap.get("mDataProp_" + iSortCol_0);

		String sortTypeString = (String) pageParamMap.get("sSortDir_0");

		Boolean needSort = (Boolean) pageParamMap.get("bSortable_" + iSortCol_0);

		if (needSort.booleanValue()) {
			qm.setSortName(sortFieldName);
			qm.setSortType(sortTypeString);
		}

		//TODO 临时的商户id用于测试
		List<ConsumerProductWebModel> listData = this.myService.getProductAdds(qm, storeId,protectionUuid,iDisplayStart,iDisplayLength);

		//TODO 临时的商户id用于测试
		//商品的数量
		int totalCount = this.myService.getProductCount(qm, storeId,protectionUuid);

		for (int i = 0; i < listData.size(); i++) {
			ConsumerProductWebModel m = (ConsumerProductWebModel) listData.get(i);
			showList.add(m);
		}

		Map<String, Object> jsonMap = new HashMap<String, Object>();

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
	 * 保存关联的商品
	 * @param productUuid 商品id
	 * @param protectionUuid 权益id
	 * @return
	 */
	@RequestMapping("/consumerProtectionProductAdd")
	@ResponseBody
	public String ConsumerProtectionProductAdd(@RequestParam("productUuid") String productUuid, @RequestParam("protectionUuid") String protectionUuid){
		ProtectionProductRelModel pprModel = new ProtectionProductRelModel();
		//TODO 临时的商户id 用于测试
		pprModel.setStoreUuid(storeId);
		pprModel.setProductUuid(productUuid);
		pprModel.setProtectionUuid(protectionUuid);
		pprService.create(pprModel);
		String json = JSON.toJSONString("success");
		return json;
	}
	
	/**
	 * 移除关联商品
	 * @param productUuid 商品id
	 * @param protectionUuid 权益id
	 * @return
	 */
	@RequestMapping("/consumerProtectionProductDel")
	@ResponseBody
	public String ConsumerProtectionProductDel(@RequestParam("productUuid") String productUuid, @RequestParam("protectionUuid") String protectionUuid){
		spprService.deleteProtectionPrRel(storeId,productUuid,protectionUuid);
		String json = JSON.toJSONString("success");
		return json;
	}
	
	/**
	 * 查询需要移除的商品
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/protectionProductDelQueryList/{protectionUuid}")
	public String protectionProductDelQueryList(HttpServletResponse response,HttpServletRequest request,@PathVariable("protectionUuid") String protectionUuid) throws IOException{
		
		List<ConsumerProductWebModel> showList = new ArrayList<ConsumerProductWebModel>();
		
		Map<String, Object> pageParamMap = parsePageParam(request);

		ConsumerProtectionQueryModel  qm = parseQueryModel(request);


		int iDisplayStart = ((Integer) pageParamMap.get("iDisplayStart")).intValue();

		int iDisplayLength = ((Integer) pageParamMap.get("iDisplayLength")).intValue();

		int iSortCol_0 = ((Integer) pageParamMap.get("iSortCol_0")).intValue();

		String sortFieldName = (String) pageParamMap.get("mDataProp_" + iSortCol_0);

		String sortTypeString = (String) pageParamMap.get("sSortDir_0");

		Boolean needSort = (Boolean) pageParamMap.get("bSortable_" + iSortCol_0);

		if (needSort.booleanValue()) {
			qm.setSortName(sortFieldName);
			qm.setSortType(sortTypeString);
		}

		//TODO 临时的商户id用于测试
		List<ConsumerProductWebModel> listData = this.myService.getProductDels(qm, storeId,protectionUuid,iDisplayStart,iDisplayLength);

		//TODO 临时的商户id用于测试
		//商品的数量
		int totalCount = this.myService.getCountProducts(qm, storeId, protectionUuid);
		for (int i = 0; i < listData.size(); i++) {
			ConsumerProductWebModel m = (ConsumerProductWebModel) listData.get(i);
			showList.add(m);
		}

		Map<String, Object> jsonMap = new HashMap<String, Object>();

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