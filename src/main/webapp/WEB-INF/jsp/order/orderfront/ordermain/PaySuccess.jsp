<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp"%>	
<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>	
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script><!-- 调用日历插件的js -->	
  <title>雇我吧 -订单支付成功</title>
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
  		<c:choose>
  			<c:when test="${'ca72f4c70ca' == pay_status && !empty orderId}">
  				<h3><i class="fa fa-check"></i>订单已支付成功</h3>
  				<p>订单号：${orderId }</p>
  			</c:when>
  			<c:when test="${'87cd314ew1' == pay_status && !empty orderId}">
  				<h3><i class="fa fa-check"></i>订单支付失败，请稍后再试</h3>
  				<p>订单号：${orderId }</p>
  			</c:when>
  			<c:when test="${'3ebd59a27a' == pay_status && !empty orderId}">
  				<h3><i class="fa fa-check"></i>支付验证失败，请稍后在试</h3>
  				<p>订单号：${orderId }</p>
  			</c:when>
  			<c:otherwise>
  				<h3>订单不存在,<a href="javascript:toindex();">返回首页</a></h3>
  			</c:otherwise>
  		</c:choose>
  	</div>
   
    <!--支付方式 end-->
  </div>
  <!-----雇我吧首页底部----->
  <%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
  <!-----雇我吧首页底部 end----->
  <!-----雇我吧首页右侧浮动----->
 <%@ include file="/WEB-INF/jsp/basebusiness/userfront/common/userfronRight.jsp" %>
  <!-----雇我吧首页右侧浮动 end----->
  <script>
  	function toindex(){
  		window.location.href="${pageContext.servletContext.contextPath}/userfront/platDecorate/index";
  	}
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