<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>
</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="livevideo.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="livevideo.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="livevideo.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="livevideo.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/livevideo/doAdd" method="post" class='form-horizontal form-bordered form-validate' enctype="multipart/form-data">						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="livevideo.m.videoName"/></label>
						<div class="col-sm-10">
						<input type="text" name="videoName" class="form-control" data-rule-maxlength="100" maxlength="100">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="livevideo.m.userName"/></label>
						<div class="col-sm-10">
						<input type="text" name="userName" class="form-control" data-rule-maxlength="50" maxlength="50">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2">主讲人简介：</label>
						<div class="col-sm-10">
							 <textarea name="userIntroduce" id="userIntroduce" rows="5" class="form-control" data-rule-maxlength="200" maxlength="200"></textarea>
								<span class="star-red">最多输入200字</span>						
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">导航图片：</label>
						<div class="col-sm-10">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 100px; height: 100px; line-height: 100px;"></div>
								<div>
									<span class="btn btn-default btn-file">
										<span class="fileinput-new">选择上传图片</span>
										<span class="fileinput-exists">更换</span>
										<input type="file" name="files" class="imgReg imgSize " accept="image/*"/>
									</span>
									<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">删除</a>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="livevideo.m.state"/></label>
						<div class="col-sm-10">
							<div class="col-sm-1">
								<div class="check-line">
									<input type="radio" name="state" id="c5" value="1" class="icheck-me" data-skin="square" data-color="blue" checked="checked"/>
									<label class='inline' for="c5">已发布</label>
								</div>
							</div>
							
							<div class="col-sm-1">
								<div class="check-line">
									<input type="radio" id="c6" name="state" value="0" class="icheck-me" data-skin="square" data-color="blue"/>
									<label class='inline' for="c6">未发布</label>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="livevideo.m.videoAddress"/></label>
						<div class="col-sm-10">
						<input type="text" name="videoAddress" class="form-control">
						</div>
					</div>
				
				
					
					
					<!--
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="livevideo.m.number"/></label>
						<div class="col-sm-10">
						<input type="text" name="number" class="form-control">
						</div>
					</div>-->
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="livevideo.m.startTime"/></label>
						<div class="col-sm-10">
						<input type="text" name="startTime" class="form-control datepick">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2"><aebiz:showTitle titleId="livevideo.m.videoIntroduction"/>：</label>
						<div class="col-sm-10">
							 <textarea name="videoIntroduction" id="videoIntroduction" rows="5" class="form-control" data-rule-maxlength="1000" maxlength="1000"></textarea>
								<span class="star-red">最多输入1000字</span>						
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
    	jQuery.validator.addMethod("imgReg", function(value, element) {  
    		if(value!=""){
    		    var suffix = value.substr(value.lastIndexOf(".")+1);
    		    return ("jpg"==suffix) || ("jpeg"==suffix) || ("bmp"==suffix) || ("png"==suffix);
    		}else{
    			return true;
    		}
    	}, "请上传jpg、jpeg、bmp,png格式文件");

    	jQuery.validator.addMethod("imgSize", function(value, element) { 
    		if(value!=""){
    			var size = Math.ceil(element.files[0].size/1024);
    		    return size <=1024;
    		} else{
    			return true;
    		}
    	}, "图片大小不能超过1M");
    	
    });
    
    
    $(".cancel").click(function(){
				history.go(-1) ;
		});
    
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>