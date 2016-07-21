<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script><!-- ��������js -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>
</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="trainplan.moduleName_CN"/></h1>
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
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="trainplan.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/trainplan/add" method="post" class='form-horizontal form-bordered form-validate'>						
						<input type="hidden" name="trainPlanNo" class="form-control">
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="trainplan.m.trainPlanNo"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
								<input type="text" name="trainPlanNo" class="form-control">
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">培训时间</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="text" id="textfield10" name="trainStartTime" class="datepick form-control">
							</div>
							<div class="fl pt_5">&nbsp;&nbsp;-&nbsp;&nbsp;</div>
							<div class="col-sm-3">
								<input type="text" id="textfield10" name="trainEndTime" class="datepick form-control">
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="trainplan.m.syllabusUuid"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
									<select name="syllabusUuid" class='select2-me form-control' data-rule-required="true">								
										<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
										<c:forEach items="${sysllabus}" var="sysllabus">
											<option value="${sysllabus.uuid}">${sysllabus.syllabusName}</option>
										</c:forEach>										
									</select>	
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="trainplan.m.trainAddress"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
								<input type="text" name="trainAddress" class="form-control">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="trainplan.m.managerUuid"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
								<select name="managerUuid" class='select2-me form-control' data-rule-required="true">								
										<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
										<c:forEach items="${managerList}" var="manager">
											<option value="${manager.uuid}">${manager.name}</option>
										</c:forEach>										
									</select>	
							</div>
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