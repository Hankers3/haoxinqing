<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<link  rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/plugins/icheck/all.css ">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js "></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js "></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js "></script>
</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="messageremind.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="messageremind.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="messageremind.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="messageremind.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/messageremind/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.mobileName"/></label>
						<div class="col-sm-5">
							<input type="text" name="mobileName" class="form-control" data-rule-required="true" data-rule-maxlength="20">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.emailName"/></label>
						<div class="col-sm-5">
							<input type="text" name="emailName" class="form-control" data-rule-required="true" data-rule-maxlength="20">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.innerMessageName"/></label>
						<div class="col-sm-5">
							<input type="text" name="innerMessageName" class="form-control" data-rule-required="true" data-rule-maxlength="20">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.remindName"/></label>
						<div class="col-sm-5">
							<input type="text" name="remindName" class="form-control" data-rule-required="true" data-rule-maxlength="50">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.accountType"/></label>
						<div class="col-sm-10">
							<c:forEach items="${accountTypes}" var="item" varStatus="i">
								<span class="y_icheckbox"><div><input type="radio" name="accountType" id="accountType${i.index}" value="${item.key}" <c:if test="${i.index==0}"> checked="checked" </c:if>  data-skin="square" data-color="blue" class='icheck-me'></div><label for="accountType${i.index}">${item.value}</label></span>
							</c:forEach>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.type"/></label>
						<div class="col-sm-10">
							<c:forEach items="${types}" var="item" varStatus="i">
								<span class="y_icheckbox"><div><input type="radio" name="type" id="remindType${i.index}" value="${item.key}" <c:if test="${i.index==0}"> checked="checked" </c:if> data-skin="square" data-color="blue" class='icheck-me'></div><label for="accountType${i.index}"></label>${item.value}</span>
							</c:forEach>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.status"/></label>
						<div class="col-sm-10">
						
							<span class="y_icheckbox"><div><input type="radio" name="status" id="status1" value="1" checked="checked" data-skin="square" data-color="blue" class='icheck-me'></div><label for="status1"></label><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></span>
							<span class="y_icheckbox"><div><input type="radio" name="status" id="status2" value="0" data-skin="square" data-color="blue" class='icheck-me'></div><label for="status2"></label><aebiz:showTitle titleId="basebusiness.showmessage.no"/></span>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.mobileCheck"/></label>
						<div class="col-sm-10">
							
							<span class="y_icheckbox"><div><input type="radio" name="mobileCheck" id="mobileCheck1" value="1" checked="checked" data-skin="square" data-color="blue" class='icheck-me'></div><label for="mobileCheck1"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></label></span>
							<span class="y_icheckbox"><div><input type="radio" name="mobileCheck" id="mobileCheck2" value="0" data-skin="square" data-color="blue" class='icheck-me'></div><label for="mobileCheck2"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></label></span>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.emailCheck"/></label>
						<div class="col-sm-10">
							
							<span class="y_icheckbox"><div><input type="radio" name="emailCheck" id="emailCheck1" value="1" checked="checked" data-skin="square" data-color="blue" class='icheck-me'></div><label for="emailCheck1"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></label></span>
							<span class="y_icheckbox"><div><input type="radio" name="emailCheck" id="emailCheck2" value="0" data-skin="square" data-color="blue" class='icheck-me'></div><label for="emailCheck2"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></label></span>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.innerMessageCheck"/></label>
						<div class="col-sm-10">
							
							<span class="y_icheckbox"><div><input type="radio" name="innerMessageCheck" id="innerMessageCheck1" value="1" checked="checked" data-skin="square" data-color="blue" class='icheck-me'></div><label for="innerMessageCheck1"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></label></span>
							<span class="y_icheckbox"><div><input type="radio" name="innerMessageCheck" id="innerMessageCheck2" value="0" data-skin="square" data-color="blue" class='icheck-me'></div><label for="innerMessageCheck2"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></label></span>
						</div>
					</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>						
				</form>
			</div>
		</div>
	</div>
</body>

</html>


<script>
    $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>