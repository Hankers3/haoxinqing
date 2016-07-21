<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/basebusiness/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/basebusiness/ueditor/ueditor.all.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="paltcongfig.moduleName_CN"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="paltcongfig.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="paltcongfig.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="paltcongfig.moduleName_CN"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/paltcongfig/updatePlatConfig" method="post" enctype="multipart/form-data" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
							
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="paltcongfig.m.platName"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
							<form:input path="platName" class='form-control'  />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="paltcongfig.m.platDomain"/></label>
						<div class="col-sm-10">
						<div class="col-sm-4">
							<form:input path="platDomain" class='form-control'  />
							</div>
						</div>
					</div>
					<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="paltcongfig.m.platLogo" /></label>
							<div class="col-sm-10">
								<form:hidden path="platLogo"/>
								<div class="fileinput fileinput-new" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;">
									<img alt="" src="${m.platLogoPath }">
									</div>
									
									<div>
										<c:if test="${empty m.platLogoPath }">
											<span class="fileinput-new">Select image</span>
										</c:if>
										<c:if test="${not empty m.platLogoPath }">
											<span class="btn btn-default btn-file"> 
											 	<span class="">Change</span>
												<input type="file" name="myfiles" >
											</span>
										 <a href="#" class="btn btn-default" data-dismiss="fileinput">Remove</a>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="paltcongfig.m.servicePhone"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
							<form:input path="servicePhone" class='form-control'  />
							</div>
						</div>
						
					</div>
					<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="paltcongfig.m.weixinImage" /></label>
							<div class="col-sm-10">
								<form:hidden path="weixinImage"/>
								<div class="fileinput fileinput-new" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;">
									<img alt="" src="${m.weixinImagePath }">
									</div>
									
									<div>
										<c:if test="${empty m.weixinImagePath }">
											<span class="fileinput-new">Select image</span>
										</c:if>
										<c:if test="${not empty m.weixinImagePath }">
											<span class="btn btn-default btn-file"> 
											 	<span class="">Change</span>
												<input type="file" name="myfiles" >
											</span>
										 <a href="#" class="btn btn-default" data-dismiss="fileinput">Remove</a>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="paltcongfig.m.topAd" /></label>
							<div class="col-sm-10">
								<form:hidden path="topAd"/>
								<div class="fileinput fileinput-new" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;">
									<img alt="" src="${m.topAdPath }">
									</div>
									
									<div>
										<c:if test="${empty m.topAdPath }">
											<span class="fileinput-new">Select image</span>
										</c:if>
										<c:if test="${not empty m.topAdPath }">
											<span class="btn btn-default btn-file"> 
											 	<span class="">Change</span>
												<input type="file" name="myfiles" >
											</span>
										 <a href="#" class="btn btn-default" data-dismiss="fileinput">Remove</a>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="paltcongfig.m.topAdUrl"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
							<form:input path="topAdUrl" class='form-control'  />
							</div>
						</div>
						
					</div>
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="paltcongfig.m.bottomAd" /></label>
							<div class="col-sm-10">
								<form:hidden path="bottomAd"/>
								<div class="fileinput fileinput-new" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;">
									<img alt="" src="${m.bottomAdPath }">
									</div>
									
									<div>
										<c:if test="${empty m.bottomAdPath }">
											<span class="fileinput-new">Select image</span>
										</c:if>
										<c:if test="${not empty m.bottomAdPath }">
											<span class="btn btn-default btn-file"> 
											 	<span class="">Change</span>
												<input type="file" name="myfiles" >
											</span>
										 <a href="#" class="btn btn-default" data-dismiss="fileinput">Remove</a>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="paltcongfig.m.bottomAdUrl"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
							<form:input path="bottomAdUrl" class='form-control'  />
							</div>
						</div>
						
					</div>	
					<div class="form-group" >
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="paltcongfig.m.bottomInfo"/></label>
						<div class="col-sm-10" >
						<form:textarea path="bottomInfo" id="bottomInfoedit" class='form-control' />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="paltcongfig.m.customerReg"/></label>
						<div class="col-sm-10">
						<form:textarea path="customerReg"   id="customerRegedit" class='form-control' />
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="paltcongfig.m.storeReg"/></label>
						<div class="col-sm-10">
						<form:textarea path="storeReg"   id="storeRegedit" class='form-control' />
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

<script>
    $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});	   
		var ue = UE.getEditor('bottomInfoedit'); 
		var ue1 = UE.getEditor('customerRegedit'); 
		var ue2 = UE.getEditor('storeRegedit'); 
    });
</script>
</html>



<aebiz:showErrorMsg></aebiz:showErrorMsg>