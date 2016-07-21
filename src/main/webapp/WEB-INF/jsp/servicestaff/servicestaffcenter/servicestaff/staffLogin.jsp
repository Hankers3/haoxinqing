<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp"%>	
<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>	
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js"></script>
  <title>雇我吧 -登录</title>
  <meta name="description" content="aebiz b2b2c index">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath }/static/usercenter/img/icon.png">
</head>
<body>
	<!---头部--->
 <%@ include file="/WEB-INF/jsp/basebusiness/userfront/common/userSimplefronTop.jsp" %>
  <!-----End----->
  <div class="container m_login">
  	<div class="row">
  		<div class="col-mi-7"><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_login.jpg"></div>
  		<div class="col-mi-5">
  			<h2><a href="###"><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/login1.jpg"></a></h2>
  			<div class="m_login_mina">
  				<div class="m_tab clearfix">
  					<div class="m_tab clearfix">
  					<span class="span1">会员登录</span>
  					<span class="span2  cur">家政员登录</span>
  				</div>
  				</div>
  				
  				<!---密码登录--->
  				<div class="m_tabin" style="display:block;">
  					<form class="form-inline2" id="mainForm" action="${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/login" method="post">
  					<input type="hidden" id="hasVilidateCode" name="hasVilidateCode" value="${hasVilidateCode }">
  					<input type="hidden" id="type" name="type" value="serviceStaff">
  					<div class="m_ysts"></div>
  					<ul>
  						<c:if test="${!empty Error_Msg}">
						<div class="form-group">
							<div class="alert alert-danger" id=>${Error_Msg }</div>
						</div>
						</c:if>
						<c:if test="${!empty More_Error_Msg}">
						<div class="form-group">
							<div class="alert alert-danger" id=>${More_Error_Msg }</div>
						</div>
						</c:if>
  						<li class="m_login_li">
  							<div class="fl m_div">登录名：</div>
  							<div class="fl m_input"><input placeholder="请输入邮箱或手机号码" name="loginName" id="loginName" value="${loginName}" class="form-control" data-rule-required="true" data-rule-maxlength="20"></div>
  						</li>
  						<li class="m_login_li">
  							<div class="fl m_div">密&nbsp;&nbsp;&nbsp;码：</div>
  							<div class="fl m_input"><input placeholder="请输入密码" type="password" name="pwd" id="pwd" value="" class="form-control" data-rule-required="true" data-rule-maxlength="20"></div>
  						</li>
  						<c:if test="${hasVilidateCode == 'true'  }">
  						<li>
  							<div class="fl m_div">验证码：</div>
  							<div class="fl m_input"><input type="text" class="form-control1" id="validateCode" name="validateCode" value="${validateCode }" >
							<img id="validateImg" src="${pageContext.servletContext.contextPath}/getCustomerLoginCode" onClick="javascript:flushValidateCode();"/>
							<span>看不清？<a class="f_color2" href="javascript:flushValidateCode();">换一张</a></span></div>
  						</li>
  						</c:if>
  					</ul>
  					<!-- <p class="m_login_p"><a href="javascript:void(0);">忘记密码？</a></p> -->
  					<div class="m_login_btn"><input type="submit" value="登&nbsp;&nbsp;&nbsp;录" class="btn btn-custom"></div>
  					</form>
  				</div>
  				<!---密码登录 end--->
  			</div>
  		</div>
  	</div>
  </div>
   <script type="text/javascript"> 
   $(".span1").click(function(){
	   window.location.href="${pageContext.servletContext.contextPath}/usercenter/customer/toLogin";
   })
   	/* 刷新验证码 */
	function flushValidateCode(){
		 var validateImgObject = document.getElementById("validateImg");
		 validateImgObject.src = "${pageContext.servletContext.contextPath}/getCustomerLoginCode?time=" + new Date();
	}
  </script>
  <!-----雇我吧首页底部----->
  <%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
  <!-----雇我吧首页底部 end----->
</body>
</html>