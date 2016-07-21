<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="syllabus.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="syllabus.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="syllabus.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="syllabus.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/syllabus/doUpdate" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">							
					<form:hidden path="uuid"/>
					<form:hidden path="syllabusHandouts"/>
						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="syllabus.m.syllabusNo"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
								<form:input path="syllabusNo" class='form-control'  />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="syllabus.m.syllabusName"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
								<form:input path="syllabusName" class='form-control'  />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="syllabus.m.syllabusNote"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
								<form:input path="syllabusNote" class='form-control'  />
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="syllabus.m.syllabusHandouts"/></label>
						<div class="col-sm-10">
							<div class="col-sm-6">
								<div class="fileinput <c:choose><c:when test="${empty m.syllabusHandouts}"> fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
									<div class="input-group">
										<div class="form-control" data-trigger="fileinput">
											<i class="glyphicon glyphicon-file fileinput-exists"></i>
											<span class="fileinput-filename"></span>
										</div>
										<span class="input-group-addon btn btn-default btn-file">
											<span class="fileinput-new">选择附件</span>
										<span class="fileinput-exists">Change</span>
											<input type="file" name="files">
										</span>
										<a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
									</div>
								</div>
							</div>	
						</div>
					</div>
					
					
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="syllabus.m.managerUuid"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
								<form:select path="managerUuid" class='select2-me form-control' data-rule-required="true">								
										<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
										<c:forEach items="${managerList}" var="manager">
											<option  value="${manager.uuid}" <c:if test="${m.managerUuid == manager.uuid }">selected="selected"</c:if> >${manager.name}</option>
										</c:forEach>										
								</form:select>
							</div>
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