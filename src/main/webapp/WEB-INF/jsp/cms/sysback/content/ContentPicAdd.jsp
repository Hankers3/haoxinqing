<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/basebusiness/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/basebusiness/ueditor/ueditor.all.js"></script>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/bootstrap.min.css ">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js "></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>今日推荐编辑</h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span>运营系统</i></li>
				<li><span>轮播图管理</i></li>
				<li><span><c:if test="${m.type==1}">医生</c:if><c:if test="${m.type==2}">患者</c:if>端轮播图管理</span></li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/content/addContent" method="post" class='form-horizontal form-bordered form-validate' enctype="multipart/form-data">						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">标题</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="text" name="contentTitle" class="form-control" data-rule-required="true" data-rule-maxlength="20">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">简介</label>
						<div class="col-sm-10">
							<div class="col-sm-12">
								<textarea rows="5" name="contentNote" class="form-control" data-rule-maxlength="40"></textarea>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">标题图片</label>
						<div class="col-sm-10">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 150px; height: 150px;"></div>
								<div>
									<span class="btn btn-default btn-file">
										<span class="fileinput-new">上传</span>
										<span class="fileinput-exists">Change</span>
										<input type="file" name="files" id="filecheck">
									</span>
									<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
					</div>
						
							<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">外链地址</label>
						<div class="col-sm-10">
							<div class="col-sm-12">
								<input type="text" name="contentURL" class="form-control" data-rule-required="true" data-rule-maxlength="20">
							</div>
						</div>
					</div>
					
					
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="content.m.introduction"/></label>
						<div class="col-sm-10">
							<div class="col-sm-12">
								<textarea name="introduction"  id="introduction"></textarea>
							</div>
						</div>
					</div>
					
					
				
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='提交'>
						<button type="button" class="btn cancel">返回</button>
					</div>						
				</form>
			</div>
		</div>
	</div>

<script>
    $(document).ready(function() {
    	var ue = UE.getEditor('introduction');  //description 为域id
		$(".cancel").click(function(){
			history.go(-1) ;
		});
    });
</script>
</body>
</html>
<aebiz:showErrorMsg></aebiz:showErrorMsg>