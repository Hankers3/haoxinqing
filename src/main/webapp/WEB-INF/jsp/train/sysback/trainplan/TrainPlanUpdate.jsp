<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css"><!--日历插件的的css-->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script><!-- 日历插件的js -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script><!-- 调用日历插件的js -->	

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="trainplan.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="trainplan.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="trainplan.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="trainplan.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/trainplan/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
							
					<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="trainplan.m.trainPlanNo"/></label>
	<div class="col-sm-10">
	<form:input path="trainPlanNo" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="trainplan.m.trainStartTime"/></label>
	<div class="col-sm-3">
	<form:input path="trainStartTime" class='form-control datepick'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="trainplan.m.trainEndTime"/></label>
	<div class="col-sm-3">
	<form:input path="trainEndTime" class='form-control datepick'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="trainplan.m.syllabusUuid"/></label>
	<div class="col-sm-3">
	<select name="syllabusUuid" class='select2-me form-control' data-rule-required="true">								
		<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
		<c:forEach items="${sysllabus}" var="sysllabus">
			<option value="${sysllabus.uuid}"  <c:if test="${m.syllabusUuid == sysllabus.uuid }">selected="selected"</c:if> >${sysllabus.syllabusName}</option>
		</c:forEach>										
	</select>
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="trainplan.m.trainAddress"/></label>
	<div class="col-sm-10">
	<form:input path="trainAddress" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="trainplan.m.managerUuid"/></label>
	<div class="col-sm-2">
	<select name="managerUuid" class='select2-me form-control' data-rule-required="true">								
		<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
		<c:forEach items="${managerList}" var="manager">
			<option  value="${manager.uuid}" <c:if test="${m.managerUuid == manager.uuid }">selected="selected"</c:if> >${manager.name}</option>
		</c:forEach>										
	</select>
	</div>
	</div>
	
	
							
							
							
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>
							
				</form:form>
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