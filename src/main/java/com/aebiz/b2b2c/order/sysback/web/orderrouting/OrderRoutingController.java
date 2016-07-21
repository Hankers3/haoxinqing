package com.aebiz.b2b2c.order.sysback.web.orderrouting;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.orderrouting.service.OrderRoutingService;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingModel;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;

@Controller
@RequestMapping("/sysback/orderrouting")
public class OrderRoutingController extends BaseController<OrderRoutingModel, OrderRoutingQueryModel> {
    /* 获取订单相关信息 */
    @Transient
    private static OrderMainService orderService;

    @Autowired
    public void setOrderMainService(OrderMainService os) {
        this.orderService = os;
    }

    private OrderRoutingService myService;

    @Autowired
    public void setMyService(OrderRoutingService bs) {
        this.myService = bs;
        super.setBs(bs);
    }

    public OrderRoutingController() {
        super("order/sysback/orderrouting", "orderrouting", OrderRoutingController.class);
    }

    @Autowired
    private ServicestaffService servicestaffService;

    /**
     * 日分账详细查询
     * 
     * @param serviceStaffUuid 家政员编号
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/routingdetails/{serviceStaffUuid}/{routTime}", method = { RequestMethod.POST })
    public String routingdetails(@PathVariable("serviceStaffUuid")
    String serviceStaffUuid, @PathVariable("routTime")
    String routTime, @RequestParam("orderMainUuid")
    String orderMainUuid, Model model, HttpServletRequest request) {

        double routPrice = myService.getStaffTotalRouting(orderMainUuid, "1");

        List<OrderMainModel> orderList = orderService.getOrderListByServiceStaffUuid(serviceStaffUuid,
                routTime);
        model.addAttribute("details", orderList);
        model.addAttribute("routPrice", routPrice);
        return "order/sysback/orderrouting/showRoutingDetail";
    }

    @RequestMapping(value = "/wagesList/{nowPage}/{pageShow}", method = { RequestMethod.GET })
    public String wagesList(@ModelAttribute("wm")
    BaseWebModel wm, Model model, HttpServletRequest request) {

        OrderRoutingQueryModel qm = this.getQueryModel();

        // 判断是否需要初始化查询
        String init = request.getParameter("init");
        if (!"true".equalsIgnoreCase(init)) {
            Object obj = SecurityUtils.getSubject().getSession().getAttribute("OrderRoutingIsQueryQs");
            if ((obj != null) && (obj.toString().equals("true"))) {
                qm = (OrderRoutingQueryModel) SecurityUtils.getSubject().getSession()
                        .getAttribute("OrderRoutingQueryModel");
            }
        }
        qm = preparedQMFixValue(qm);
        // 按月分账
        qm.setFzType(qm.MONTH_ROUTING);
        // 只查询家政员的
        qm.setRoutType("0");
        wm.setTotalNum(myService.getAllChargeCount(qm));
        List<OrderRoutingModel> list = myService.getByCondition(qm, wm.getFromNum(), wm.getPageShow());
        String time = DateFormatHelper.getNowTimeStr(DateFormatHelper.FORMAT_DATE_STR).substring(0, 7);
        if (!StringUtil.isEmpty(qm.getRoutYear()) && !StringUtil.isEmpty(qm.getRoutMonth())) {
            time = qm.getRoutYear() + "-" + qm.getRoutMonth();
        }
        double totalRouting = myService.getTotalRoutingMoneyByTime(time, "0");
        wm.setRows(list);
        model.addAttribute("totalRouting", totalRouting);
        request.setAttribute("qm", qm);
        request.setAttribute("routYear", qm.getRoutYear());
        request.setAttribute("routMonth", qm.getRoutMonth());
        this.preparedListData(model, request);
        return "order/sysback/orderrouting/wagesList";
    }

    /**
     * 查询工资发放记录
     * 
     * @param qm
     * @param pageShow
     * @param request
     * @return
     */
    @RequestMapping(value = "/query", method = { RequestMethod.POST })
    public String query(OrderRoutingQueryModel qm, @RequestParam("pageShow")
    String pageShow, HttpServletRequest request) {

        // 辅助设置要查询的条件的比较方式
        Map<String, String[]> map = request.getParameterMap();
        for (String key : map.keySet()) {
            if (key.endsWith("_q")) {
                String v = map.get(key)[0];
                ((OrderRoutingQueryModel) qm).getMapCondition().put(key.substring(0, key.length() - 2),
                        ConditionOpTypeEnum.valueOf(v).getCode());
            }
        }

        SecurityUtils.getSubject().getSession().setAttribute("OrderRoutingIsQueryQs", "true");
        SecurityUtils.getSubject().getSession().setAttribute("OrderRoutingQueryModel", qm);

        return "redirect:/sysback/orderrouting/wagesList/1/" + pageShow;
    }

    /**
     * 工资发放-查看明细
     * 
     * @param customerUuid
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/searchWages/{customerUuid}/{time}")
    public String searchcusstomercharges(@ModelAttribute("customerUuid")
    String customerUuid, @ModelAttribute("time")
    String time, Model model, HttpServletRequest request) {
        String routTime = DateFormatHelper.getNowTimeStr(DateFormatHelper.FORMAT_DATE_STR).substring(0, 7);
        if (!StringUtil.isEmpty(time)) {
            routTime = time;
        }
        List<OrderRoutingModel> list = myService.getWagesByserviceStaffUuid(customerUuid, routTime);
        model.addAttribute("details", list);
        request.setAttribute("routMonth", time.substring(6, 7));
        return "order/sysback/orderrouting/showWagesList";
    }

    /**
     * 医生收入明细 by szr
     */
    @RequestMapping(value = "/getDoctorIncomeList", method = { RequestMethod.GET })
    public String getDoctorIncomeList(Model m, HttpServletRequest request) {
        List<String> uuids = new ArrayList<String>();
        uuids = myService.getUuids();
        if (uuids != null && uuids.size() > 0) {
            m.addAttribute("doctorUuids", uuids);
        }
        return "order/servicestaff/ordermain/ServicestaffList";

    }

    @Override
    protected void preparedListData(Model model, HttpServletRequest request) {

    }

    /**
     * 医生收入查看详细 by szr
     */
    @RequestMapping(value = "/getDoctorIncomeView/{doctorUuid}", method = { RequestMethod.GET })
    public String getDoctorIncomeView(@PathVariable("doctorUuid")
    String doctorUuid, Model m, HttpServletRequest request) {

        m.addAttribute("doctorUuid", doctorUuid);
        // 根据uuid查出医生信息，存入
        ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
        double accountAmount = servicestaffModel.getAccountAmount();
        BigDecimal  d   =   new   BigDecimal(accountAmount); 
    	double   f1   =   d.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
    	servicestaffModel.setAccountAmount(f1);
        
        if (servicestaffModel != null) {
            m.addAttribute("servicestaffModel", servicestaffModel);
            
        }
        /*
         * // 根据uuid查出医生收入列表 List<OrderRoutingModel> orderRoutingModelList = myService
         * .getByDoctorUuid(doctorUuid); if (orderRoutingModelList != null &&
         * orderRoutingModelList.size() > 0) { m.addAttribute("orderRoutingList",
         * orderRoutingModelList); }
         */
        return "order/sysback/orderrouting/orderroutingList";

    }

    /**
     * 跳转到平台收益页面
     * 
     * @param m
     * @param request
     * @author XP
     * @return
     */
    @RequestMapping(value = "/toPlatformIncomeList", method = { RequestMethod.GET })
    public String toPlatformIncomeList(Model m, HttpServletRequest request) {
        //获取平台收益的总和
         
         Number totalMoney = myService.getAllPlatformIncome();
         if(null==totalMoney){
           totalMoney =0;
         }
         m.addAttribute("totalMoney", totalMoney);
        return "order/sysback/orderrouting/PlatformIncomeList";

    }
}