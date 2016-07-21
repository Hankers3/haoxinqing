<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>家政员 -我的收入</title>
  <meta name="description" content="aebiz b2b2c index">
  <!--[if IE]> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <![endif]-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/font-awesome.min.css">
  <!-- 字体图标ie7兼容性处理 -->
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/font-awesome-ie7.min.css">
	<![endif]-->
	<!-- icheck -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/plugins/icheck/all.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/member.css">
  
  <!--- jQuery -->
  <script src="${pageContext.request.contextPath }/static/usercenter/js/jquery-1.11.1.min.js"></script>
   <!-- icheck -->
	<script src="${pageContext.request.contextPath }/static/usercenter/js/plugins/icheck/jquery.icheck.min.js"></script>

  <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath }/static/usercenter/js/respond.min.js"></script>
  <![endif]-->
  
  <!-- Favicon and Apple Icons -->
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath }/static/usercenter/img/icon.png">
</head>
<body>
	<!-- 家政员-个人中心top-->
	<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterHead.jsp"%> 
	<!-- 家政员-个人中心top end-->
	<!-- 家政员-个人中心主体 -->
	<div class="container member_main">
		<div class="row ">
		  <%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterLeft.jsp"%>
		<!-- 雇我吧-我的收入主体右侧 -->
			<div class="col-mi-9">
       		 <div class="m_income">
        	账户金额： <label class="m_color">￥${myIncome.get("accountAmount")}</label>
        	<b>|</b>
        	账户可用余额： <label class="m_color">${myIncome.get("accountAmount")}</label>
        </div>
       			 <!-- 雇我吧-我的收入-账号明细记录 -->
        		<div class="m_income_tit"><span>账户明细记录</span></div>
        	<div class="m_detailed">
        	<div class="m_detailed_zc clearfix">
        		<div class="fl m_detailed_left">
	        		<span>最近一个月<i class="fa fa-angle-down"></i></span>
	        		<b>|</b>
	        		<span>收入：<label class="m_color">${myIncome.get("monthIncome")}</label> 元</span>
	        		<b>|</b>
            	</div>
           </div>
        <table class="m_detailed_list">
        	<tbody>
        		<tr>
        			<th width="39%">订单号</th>
        			<th width="34%">时间</th>
        			<th width="27%">收入</th>
        		</tr>
       			<c:if test="${orderRoutingModels!=null&&orderRoutingModels.size()>0}">
       				<c:forEach items="${orderRoutingModels}" var="orderRouting">
       			<tr>
        			<td>${orderRouting.orderId}</td>
        			<td>${orderRouting.routTime}</td>
        			<td><label class="m_color">￥${orderRouting.routPrice}</label></td>
        		</tr>
       				</c:forEach>
       			</c:if>
          </tbody>
        </table>
        <!--翻页-->
         	 <aebiz:userCenterPage listPath="servicestaff/orderrouting/getServicestaffIncome" />
			<!--翻页 end-->
        <!-- 雇我吧-我的收入-账号明细列表end -->
		  	</div>
		  <!-- 雇我吧-我的收入主体右侧 end -->
	  		</div>
  	</div>
	</div>
	<!-----雇我吧首页底部----->
  <div class="m_foot">
  	<p><a href="##">关于雇我吧</a>|<a href="##">联系我们</a>|<a href="##">加入我们</a>|<a href="##">常见问题</a>|<a href="##">意见反馈</a></p>
  	<div class="m_footin">Copyright@2015 雇我吧 版权所有 京ICP备12345678号</div>
  </div>
  <!-----雇我吧首页底部 end----->
  <script>
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