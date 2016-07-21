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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="quizcategory.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="quizcategory.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="quizcategory.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="quizcategory.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/quizcategory/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">							
					<form:hidden path="uuid"/>	
					<form:hidden path="image"/>	
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="quizcategory.m.categoryName"/></label>
						<div class="col-sm-10">
							<form:input path="categoryName" class='form-control'  />
							<input type="hidden" name="categoryNameHidden" value="true">
							<span name='categoryNamespan' ></span>
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle
								titleId="questions.m.state" /></label>
						<div class="col-sm-10">
							<div class="col-sm-1">
								<div class="check-line">
									<form:radiobutton path="state" id="c1" value="1" class="icheck-me" data-skin="square" data-color="blue"/>
									<label class='inline' for="c1"><aebiz:showTitle titleId="basebusiness.showmessage.yes" /></label>
								</div>
							</div>
							
							<div class="col-sm-1">
								<div class="check-line">
									<form:radiobutton path="state" id="c2" value="0" class="icheck-me" data-skin="square" data-color="blue"/>
									<label class='inline' for="c2"><aebiz:showTitle titleId="basebusiness.showmessage.no" /></label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">量表介绍</label>
						<div class="col-sm-10">
							<form:textarea path="note" rows="6" class='form-control' data-rule-maxlength="1000" maxlength="1000" />
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">填写说明</label>
						<div class="col-sm-10">
							<form:textarea path="fillExplain" rows="6" class='form-control' data-rule-maxlength="1000" maxlength="1000" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2">导航图片：</label>
						<div class="col-sm-10">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 100px; height: 100px; line-height: 100px;">
									<img src="${m.imgUrl}"/>
								</div>
								<div>
									<span class="btn btn-default btn-file">
										<span class="fileinput-new">选择上传图片</span>
										<span class="fileinput-exists">Change</span>
										<input type="file" name="files" class="imgReg imgSize " accept="image/*"/>
									</span>
									<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">创建时间</label>
						<div class="col-sm-10">
							<div class="y_validatainput y_clear">
							<div class="col-sm-10">
									<form:input  path="createTime" class="form-control datepick" data-rule-required="true" ></form:input>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="button" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>' onclick="tj();">
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>
							
				</form:form>
			</div>
		</div>
	</div>
</body>

</html>


<script>
		var categoryNameJUDGE=$("input[name='categoryName']").val();// 判断用
		var flag = 1; // flag为1时，可以提交， 为0不能提交
    $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});	    	
    });
    
     $(document).ready(function(){   	
     	//验证分类名称是否存在
			$("input[name='categoryName']").blur(function(){
				$("span[name='servicestaffModel.categoryNamespan']").remove();
					var categoryName=$("input[name='categoryName']").val();
					if(categoryName!=''){
						var url="${pageContext.servletContext.contextPath}/sysback/quizcategory/checkName";
						$.get(url,{"categoryName":categoryName,ranNum:Math.random()},function(data){
							if(data=="true"){
								if(categoryNameJUDGE==categoryName){
									flag = 1;
									$("span[name='categoryNamespan']").html(""); 	 //清空消息
								}else{
									$("span[name='categoryNamespan']").html("<font color='red'>分类名已存在</font>"); 	
									$("input[name='categoryNameHidden']").val("false");
									flag = 0;
								}
							}else{
										$("span[name='categoryNamespan']").html(""); 	 //清空消息
								flag = 1;
							}
						});
					}
				});
    });
    function tj(){
    	if(flag==1){
    		$("#mainForm").submit();
    		}
			
    	}
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>