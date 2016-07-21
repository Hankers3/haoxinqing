<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="versionupdate.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="versionupdate.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="versionupdate.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="versionupdate.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/versionupdate/doAdd" method="post" class='form-horizontal form-bordered form-validate' enctype="multipart/form-data">						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="versionupdate.m.versionNo"/></label>
						<div class="col-sm-10">
						<input type="text" name="versionNo" class="form-control" onkeyup="value=value.replace(/[^\d.]/g,'')">
						</div>
					</div>
					
					<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">发布端</label>
							<div class="col-sm-10">
								<div class="col-sm-1">
									<div class="check-line">
										<input type="radio" name="versionType" id="c5" value="1" class="icheck-me" data-skin="square" data-color="blue" checked="checked"/>
										<label class='inline' for="c5">患者端</label>
									</div>
								</div>
								
								<div class="col-sm-1">
									<div class="check-line">
										<input type="radio" id="c6" name="versionType" value="0" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c6">医生端</label>
									</div>
								</div>
							</div>
						</div>
					
					<div class="form-group none" id="fileShow" >
						<label for="textfield" class="control-label col-sm-2">附件</label>
						<div class="col-sm-10">
							<div class="fileinput fileinput-new r_fileinput" data-provides="fileinput">
								<div class="input-group">
									<div class="form-control" data-trigger="fileinput">
										<i class="glyphicon glyphicon-file fileinput-exists"></i>
										<span class="fileinput-filename"></span>
									</div>
									<span class="input-group-addon btn btn-default btn-file">
									<span class="fileinput-new">选择文件</span>
									<span class="fileinput-exists">更换</span>
									<input type="file" name="files" >
									</span>
									<a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">删除</a>
								</div>
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