<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp"%>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/bootstrap.min.css ">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js "></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<c:if test="${integralStatModel.userType=='1'}" >
							<aebiz:showTitle titleId="vipclubintegrallog.moduleName_CN" />
					</c:if>
					
					<c:if test="${integralStatModel.userType=='2'}" >
							医生积分管理
					</c:if>
					<aebiz:showTitle titleId="basebusiness.showmessage.edit" />
				</h1>
			</div>
		</div>

		<div class="breadcrumbs">
			<ul>
				<c:if test="${integralStatModel.userType=='1'}" >
						<li><span><aebiz:showTitle titleId="vipclubintegrallog.menuOne" /></span> <i class="fa fa-angle-right"></i></li>
						<li><span><aebiz:showTitle titleId="vipclubintegrallog.menuTwo" /></span> <i class="fa fa-angle-right"></i></li>
						<li><span><aebiz:showTitle titleId="vipclubintegrallog.moduleName_CN" />
				</c:if>
				<c:if test="${integralStatModel.userType=='2'}" >
						<li><span>医生管理</span> <i class="fa fa-angle-right"></i></li>
						<li><span>医生积分管理</span> <i class="fa fa-angle-right"></i></li>
						<li><span>医生积分管理
				</c:if>
				 <aebiz:showTitle titleId="basebusiness.showmessage.edit" /></span></li>
			</ul>
		</div>

		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/vipclubintegrallog/save" method="post" class='form-horizontal form-validate form-bordered'>
					<input type="hidden" name="customerUuid" value="${integralStatModel.customerUuid}" />
					<input type="hidden" name="userType" value="${integralStatModel.userType}" />

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="vipclubintegrallog.m.customerName" /></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<input name="customerName" class='form-control' value="${integralStatModel.customerName}" readonly="true" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="vipclubintegralstat.intergralType.usable" /></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<input name="intergralType" class='form-control' value="${integralStatModel.intergralCount}" readonly="true" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="vipclubintegrallog.message.adaptType" /></label>
						<div class="col-sm-10">
							<input type="radio" class='' name="adaptType" value="add" checked="checked"/>
							<aebiz:showTitle titleId="vipclubintegrallog.message.addIntegral" />
							<input type="radio" class='' name="adaptType" value="reduce" />
							<aebiz:showTitle titleId="vipclubintegrallog.message.reduceIntegral" />
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="vipclubintegrallog.message.changgeIntegralNum" /></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<input type="text" name="changgeIntegralNum" class='form-control' data-rule-number="true" data-rule-required="true" data-rule-positivenum="true" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="vipclubintegrallog.m.description" /></label>
						<div class="col-sm-10">
							<input type="text" name="description" class='form-control' data-rule-required="true" data-rule-maxlength="100"  />
						</div>
					</div>
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel"> <aebiz:showTitle titleId="basebusiness.showmessage.cancel" /></button>
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