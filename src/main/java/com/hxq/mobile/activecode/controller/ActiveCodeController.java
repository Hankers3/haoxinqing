package com.hxq.mobile.activecode.controller;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.hxq.mobile.activecode.service.ActiveCodeService;
import com.hxq.mobile.entity.common.ActiveCode;
import com.hxq.mobile.util.page.QueryListHelper;
import com.wxcommon.util.ExcelUtil;

@Controller("com.hxq.mobile.activecode.controller.ActiveCodeController")
@RequestMapping("/sysback/activecode/")
public class ActiveCodeController {
	@Resource(name = "com.hxq.mobile.activecode.service.ActiveCodeService")
	private ActiveCodeService activeCodeService;

	/**
	 * 跳转邀请码管理页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toList", method = RequestMethod.GET)
	public String toActiveList(Model model, HttpServletRequest request) {
		return "activecode/sysback/deputyList";
	}
	
	@RequestMapping(value = "toImportactivecode", method = RequestMethod.GET)
	public String toImportactivecode(Model model, HttpServletRequest request) {
		return "activecode/sysback/Importactivecode";
	}

	@RequestMapping(value = "toDetailList", method = RequestMethod.POST)
	public String toDetailList(HttpServletRequest request) {
		 request.setAttribute("usr", (String) request.getParameter("usrs"));
		 request.setAttribute("activeCode",(String) request.getParameter("codes"));
		 request.setAttribute("allnum",(String) request.getParameter("allnums"));
		
		return "activecode/sysback/visitList";
	}

	@RequestMapping(value = "queryList", method = RequestMethod.GET)
	public String queryList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String, Object> requests = QueryListHelper.prepareQueryParam(request);
		Object sEcho = requests.get("sEcho");
		Object[] objects = activeCodeService.selectActiveCodeInfo(requests);
		List<Map<String, Object>> lst = objects[0]==null?new ArrayList<Map<String, Object>>():(List<Map<String, Object>>) objects[0];
		int totalRows = (int) objects[3];
		Map<String, Object> result = QueryListHelper.prepareQueryResult(sEcho, lst, totalRows);
		
		response.setContentType("charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(result));
		return null;
		
	}

	@RequestMapping(value = "details/{activeCode}",method = { RequestMethod.POST })
	public String details(HttpServletResponse response, HttpServletRequest request,@PathVariable("activeCode") String activeCode) throws Exception {
		Map<String, Object> requests= QueryListHelper.prepareQueryParam(request);
		requests.put("activeCode", activeCode);
		Object sEcho=requests.get("sEcho");
		Object[] objects = activeCodeService.selectDetails(requests);
		List<Map<String, Object>> lst = objects[0]==null?new ArrayList<Map<String, Object>>():(List<Map<String, Object>>) objects[0];
		int totalRows = (int) objects[3];
		Map<String, Object> result = QueryListHelper.prepareQueryResult(sEcho, lst, totalRows);
		response.setContentType("charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(result));
		return null;
	}

	@RequestMapping(value = "importData", method = { RequestMethod.POST })
    public void importData(HttpServletResponse response,
    		@RequestParam(value = "myExcelFile") MultipartFile file) throws Exception {		
	
		String fileName = file.getOriginalFilename();
	
		int index = fileName.lastIndexOf(".");
		String suffix = fileName.substring(index + 1, fileName.length());
		
		List<String[]> dataList = ExcelUtil.rosolveFile(file.getInputStream(), suffix, 1);
		int intTotal = saveExcel(dataList);

		
		
		OutputStream out = null;
		StringBuffer  erroMessage = new StringBuffer();
			try {
				out = response.getOutputStream();
				erroMessage.append("<script>alert(\"导入"+intTotal+"数据成功！\");window.location.href=\"/sysback/activecode/toList\";</script>");
				byte[] datas = erroMessage.toString().getBytes("utf-8");
				out.write(datas, 0, datas.length);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace(System.out);				
			} finally {
				if (out != null)
					try {
						out.close();
					} catch (Exception f) {}
			}
	}

	private int saveExcel(String[][] data) throws Exception{
		if(data.length < 1) return 0;
		List<ActiveCode> list = new ArrayList<ActiveCode>();
		//循环取出每一行，放到新的对象里面
		for(int m = 0; m < data.length ;m++){
			if(data[m][0] == null || data[m][0].equals("")) continue;
			if(data[m][1] == null || data[m][1].equals("")) continue;
			ActiveCode po = new ActiveCode();
			po.setUsr(data[m][0]);
			po.setCode(data[m][1]);
			//放入list
			list.add(po);
		}
		
		//执行相关操作
		if(list.size() > 0) insertToDB(list);
		return list.size();
	}
	
	private int saveExcel(List<String[]> data) throws Exception{
		if(data.size() < 1) return 0;
		List<ActiveCode> list = new ArrayList<ActiveCode>();
		//循环取出每一行，放到新的对象里面
		for(int m = 0; m < data.size() ;m++){
			String[] tempData = data.get(m);
			if(tempData[0] == null || tempData[0].equals("")) continue;
			if(tempData[1] == null || tempData[1].equals("")) continue;
			ActiveCode po = new ActiveCode();
			po.setUsr(tempData[0]);
			po.setCode(tempData[1]);
			//放入list
			list.add(po);
		}
		
		//执行相关操作
		if(list.size() > 0) insertToDB(list);
		return list.size();
	}

	private void insertToDB(List<ActiveCode> list) throws Exception{
		ActiveCode pojo = null;
		ActiveCode tmp = null;
		Iterator<ActiveCode> iter = list.iterator();
		while(iter.hasNext()){
			//判断导入日数据是否存在
			pojo = (ActiveCode) iter.next();
			tmp = (ActiveCode) activeCodeService.select(new ActiveCode(pojo.getCode()));
			//如果不存在导入 存在执行更新 
			if(tmp != null)
				activeCodeService.update(pojo);
			else
				activeCodeService.insert(pojo);	
		}
	}
}
