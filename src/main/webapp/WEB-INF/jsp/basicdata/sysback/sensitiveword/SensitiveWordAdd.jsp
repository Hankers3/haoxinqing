<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>

</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="sensitiveword.moduleName_CN"/></h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="basicdata.title"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="sensitiveword.moduleName_CN"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="sensitiveword.m.sensitiveName"/></span></li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/sensitiveword/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sensitiveword.m.sensitiveName"/></label>
						<div class="col-sm-10">
							<input type="text" name="sensitiveName" class="form-control" data-rule-required="true" data-rule-minlength="2" data-rule-maxlength="40">
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sensitiveword.m.sensitiveLevel"/></label>
						<div class="col-sm-10">
							<div class="col-sm-2">
								<select name="sensitiveLevel" id="sensitiveLevel" class='select2-me form-control' data-rule-required="true">								
									<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
										<c:forEach items="${sensitiveLevelList}" var="dpp">	
											<option value="${dpp.value}" <c:if test="${m.sensitiveLevel==dpp.value}"> selected </c:if> >${dpp.name}</option>
										</c:forEach>											
								</select>	
							</div>								
						</div>
					</div>
				
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sensitiveword.m.replaceContent"/></label>
						<div class="col-sm-10">
							<input type="text" name="replaceContent" class="form-control" data-rule-maxlength="40">
						</div>
					</div>
				
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sensitiveword.m.description" /></label>
						<div class="col-sm-10">
							<input type="text" name="description" class="form-control" data-rule-maxlength="100">
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