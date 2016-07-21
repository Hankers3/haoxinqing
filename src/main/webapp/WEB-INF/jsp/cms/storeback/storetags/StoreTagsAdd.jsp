<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/AddImport.jsp" %>

</head>

<body>
	<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp"%>
	<div class="container-fluid" id="content">
	<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp"%>
		<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="storetags.moduleName_CN"/></h1>
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
							<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="storetags.moduleName_CN"/></span>
						</li>
					</ul>
				</div>		
						
				<div class="box box-bordered bordered-top">
					<div class="box-content nopadding">																
						<form id="mainForm" action="${pageContext.servletContext.contextPath}/store/storetags/add" method="post" class='form-horizontal form-bordered form-validate'>						
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="storetags.m.tagName"/></label>
								<div class="col-sm-10">
									<input type="text" name="tagName" value="${m.tagName}" class="form-control" data-rule-required="true" data-rule-maxlength="40">
								</div>
							</div>
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="storetags.m.tagType"/></label>
								<div class="col-sm-10">
									<div class="col-xs-2">
										<select name="tagType" class="form-control" data-rule-required="true">
											<option value=""><aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/></option>
											<option value="1" <c:if test="${m.tagType==1}">selected</c:if> ><aebiz:showTitle titleId="storetags.m.customerType"/></option>	
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