<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>雇我吧 -申请成功</title>
  <meta name="description" content="aebiz b2b2c index">
  <!--[if IE]> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <![endif]-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/font-awesome.min.css">
  <!-- 字体图标ie7兼容性处理 -->
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="css/font-awesome-ie7.min.css">
	<![endif]-->
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/global.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/channel.css">
  <!--- jQuery -->
  <script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery-1.11.1.min.js"></script>

  <!--[if lt IE 9]>
    <script src="js/respond.min.js"></script>
  <![endif]-->
  
  <!-- Favicon and Apple Icons -->
  <link rel="icon" type="image/png" href="${pageContext.servletContext.contextPath}/static/usercenter/img/icon.png">
</head>
<body>
	<!---头部--->
    <%@ include file="/WEB-INF/jsp/basebusiness/userfront/common/userfronTop.jsp" %>
  <!-----End----->
  <!-----雇我吧-加入我们介绍----->
  <div class="container m_join m_apply_success">
  	<h2><span>您已申请成功！</span></h2>
  	<p>请您耐心等候我们的审核，审核结果将会通过短信通知给您。</p>
  	<div class="m_joinin">
      <div class="m_jo_pic m_jo_pic_2"><img src="${pageContext.servletContext.contextPath}/static/usercenter/img/adimg/m_jr2.jpg"></div>
  	</div>
  </div>
  <!-----雇我吧-加入我们介绍 end----->

 
  <!-----雇我吧首页底部----->
  <div class="m_foot">
  	<p><a href="##">关于雇我吧</a>|<a href="##">联系我们</a>|<a href="##">加入我们</a>|<a href="##">常见问题</a>|<a href="##">意见反馈</a></p>
  	<div class="m_footin">Copyright@2015 雇我吧 版权所有 京ICP备12345678号</div>
  </div>
  <!-----雇我吧首页底部 end----->
  <!-----雇我吧首页右侧浮动----->
  <div class="m_float">
  	<ul>
  		<li class="li">
  			<a href="###">
  			<i class="m_fa"></i>
  			 优惠活动
  			</a>
  		</li>
  		<li>
  			<a href="###">
  			<i class="m_fa m_fa1"></i>
  			 在线交流
  			</a>
  		</li>
  	</ul>
  </div>
  <!-----雇我吧首页右侧浮动 end----->
</body>
</html>