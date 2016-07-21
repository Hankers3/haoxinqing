<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/UpdateImport.jsp" %>

</head>

<body>
	<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp"%>
	<div class="container-fluid" id="content">
	<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp"%>
		<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1><aebiz:showTitle titleId="storetags.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
					</div>
				</div>
						
				<div class="breadcrumbs">
					<ul>
						<li>
							<span><aebiz:showTitle titleId="storetags.menuOne"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="storetags.menuTwo"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="storetags.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
						</li>
					</ul>
				</div>
						
				<div class="box box-bordered bordered-top">
					<div class="box-content nopadding">								
						<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/store/storetags/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
							<form:hidden path="uuid"/>	
									
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="storetags.m.tagName"/></label>
								<div class="col-sm-10">
									<form:input path="tagName" class='form-control' data-rule-required="true" data-rule-maxlength="40" />
								</div>
							</div>
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="storetags.m.tagType"/></label>
								<div class="col-sm-10">
									<div class="col-xs-2">
										<form:select path="tagType" class="form-control" data-rule-required="true">
											<form:option value=""><aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/></form:option>
											<form:option value="1"><aebiz:showTitle titleId="storetags.m.customerType"/></form:option>	
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