<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp"%>	
<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>	
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script><!-- 调用日历插件的js -->	
  <title>雇我吧 -订单提交成功</title>
  <meta name="description" content="aebiz b2b2c index">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<!---头部--->
 <%@ include file="/WEB-INF/jsp/basebusiness/userfront/common/userfronTop.jsp" %>
  <!-----End----->
  <!-----雇我吧-订单提交成功----->
  <div id="to_alipay"></div>
  <div class="container m_online m_success">
  	<div class="m_online_pic"></div>
  	<ul class="m_online_tit">
  		<li class="m_color">填写房屋状况</li>
  		<li class="m_li1">联系方式</li>
  		<li class="m_li2">成功提交订单</li>
  	</ul>
  </div>
  <div class="container m_successin">
  	<div class="m_successin_tit">
  		<h3><i class="fa fa-check"></i>
  			<c:choose>
  				<c:when test="${order.payMoney == 0 && order.payType == 0}">订单已提交，并支付成功！</c:when>
  				<c:when test="${order.payMoney > 0 && order.payType == 0}">订单提交成功，请您尽快付款！</c:when>
  				<c:when test="${order.payType == 1}">订单提交成功，我们会尽快上门为您服务！</c:when>
  			</c:choose>
  		</h3>
  		
  		<p>订单号：${order.orderId }<c:if test="${order.payMoney > 0 && order.payType == 0}"><br/>请您在提交订单后尽快完成支付，以免耽误保洁员上门服务时间。</c:if></p>
  		
  	</div>
    <ul class="m_success_ul">
  		<li style="width:14%">服务时间</li>
  		<li style="width:18.2%">服务地址</li>
  		<li style="width:14%">联系方式</li>
  		<li style="width:14.2%">备选联系人</li>
  		<li style="width:14%">其他服务要求</li>
  		<li style="width:25%" class="m_li6">订单详情</li>
  	</ul>
  	<ul class="m_success_td">
  		<li style="width:14%"><div>${address.serviceTime}</div></li>
  		<li style="width:18%"><div>${address.addressDetail}</div></li>
  		<li style="width:14%"><div>${address.name }<br/>${address.mobile }</div></li>
  		<li style="width:14%"><div><c:if test="${empty address.alternative }">无</c:if>${address.alternative }<br/><c:if test="${empty address.alternativePhone }">无</c:if>${address.alternativePhone }</div></li>
  		<li style="width:14%"><div><c:if test="${empty order.note }">无</c:if>${order.note }</div></li>
  		<li style="width:25%" class="m_li6"><div class="td_div">
	  		<c:forEach items="${order.detailList}" var="detail" varStatus="det">
				${detail.productName}：${detail.buyNum }
				<c:if test="${detail.chargetype ==1}">m<sup>2</sup></c:if>
				<c:if test="${detail.chargetype ==0}">个</c:if>
				<c:if test="${!det.last }">、</c:if>
			</c:forEach>
  		</div></li>
  	</ul>
  	 <c:if test="${order.payMoney > 0}">
    <div class="m_success_je">应付金额：<label>￥${order.payMoney}</label></div>
    </c:if>
    <c:if test="${order.payMoney > 0 && order.payType == 0}">
    <%----支付方式--%>
    <div class="m_payment">
    	<h4><span>支付方式</span></h4>
    	<div class="m_bank">
    		<ul class="m_bank_ul">
    			<li><img src="${pageContext.request.contextPath }/static/usercenter/img/adimg/m_yh.jpg"></li>
    			<%--<li class="m_bank_li">更多银行<i class="fa fa-angle-down"></i></li> --%>-
    		</ul>
    		<div class="m_bank_btn"><a href="javascript:void(0);" class="btn btn-custom2">立即支付</a></div>
    	</div>
    </div> 
    </c:if>
    <!--支付方式 end-->
  </div>
  <!-----雇我吧首页底部----->
  <%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
  <!-----雇我吧首页底部 end----->
  <!-----雇我吧首页右侧浮动----->
 <%@ include file="/WEB-INF/jsp/basebusiness/userfront/common/userfronRight.jsp" %>
  <!-----雇我吧首页右侧浮动 end----->
  <script>
	$(".btn-custom2").click(function(){
		$.post(
			"${pageContext.servletContext.contextPath}/alipcpay/toPay",
			{"uuid":"${order.uuid}",ranNum:Math.random()},
			function(data){
				$("#to_alipay").html(data);
			}
		)
	})
  	$('.m_bank_ul li').click(function(){
  		$(this).addClass('cur').siblings('li').removeClass('cur');
  	});
  	//单选框复选框样式重置
  	if ($(".icheck-me").length > 0) {
      $(".icheck-me").each(function() {
        var $el = $(this);
        var skin = ($el.attr('data-skin') !== undefined) ? "_" + $el.attr('data-skin') : "",  //将data-skin属性的值 传递到参数中
          color = ($el.attr('data-color') !== undefined) ? "-" + $el.attr('data-color') : ""; //将data-color属性的值 传递到参数中

        var opt = {  //设置参数
          checkboxClass: 'icheckbox' + skin + color,
          radioClass: 'iradio' + skin + color,
          increaseArea: "10%"
        }

        $el.iCheck(opt);  //带参数调用js
      });
    }	
  </script>
 
</body>
</html>