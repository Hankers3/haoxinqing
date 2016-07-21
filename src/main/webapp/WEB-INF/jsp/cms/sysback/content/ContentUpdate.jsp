<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/basebusiness/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/basebusiness/ueditor/ueditor.all.js"></script>
</head>
	<style>
		#edui1_iframeholder{ height:450px !important;}	
	</style>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="content.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="content.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="content.menuTwo"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="content.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span></li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/content/doUpdate" method="post" commandName="m" class='form-horizontal form-validate form-bordered'  enctype="multipart/form-data">							
					<form:hidden path="uuid"/>	
					<form:hidden path="image"/>
					<form:hidden path="contentType"/>
				  	<form:hidden path="createTime"/>
 					<form:hidden path="url"/>
 					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="content.m.contentTitle"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input path="contentTitle" class='form-control' data-rule-required="true" data-rule-maxlength="50" maxlength="50"/>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="content.m.categoryName"/></label>
						<div class="col-sm-10">
								<aebiz:contentCategory inputName="contentCategoryUuid"  categoryUuid="${m.contentCategoryUuid}" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">作者</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input  path="author" class="form-control"  data-rule-maxlength="20" maxlength="20"/>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">出处</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input  path="provenance" class="form-control" data-rule-maxlength="30" maxlength="30"/>
							</div>
						</div>
					</div>
					<div id="illId" style="display:none">
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">疾病</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:select  path="illnessId" class='form-control' data-rule-required="true">								
										<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
										<c:forEach items="${illness}" var="illne">	
											<form:option value="${illne.value}" >${illne.name}</form:option>
										</c:forEach>											
									</form:select>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">国家</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:select  path="country" class='form-control' data-rule-required="true">								
										<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
										<c:forEach items="${countrys}" var="country">	
											<form:option value="${country.value}" >${country.name}</form:option>
										</c:forEach>											
									</form:select>
								</div>
							</div>
						</div>
					</div>
					
					<div id="entityId" style="display:none">
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">诊断标准</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:select id="entity" path="entity" class='form-control' data-rule-required="true">								
										<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
										<form:option value="1" >DSM-5</form:option>
										<form:option value="2" >ICD-10</form:option>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					
					<div id="sympId" style="display:none">
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">类别</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:select id="symptomId" path="symptomId" class='form-control' data-rule-required="true">								
										<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
										<c:forEach items="${sorts}" var="sort">	
											<form:option value="${sort.value}" >${sort.name}</form:option>
										</c:forEach>	
									</form:select>
								</div>
							</div>
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
					
					<div class="form-group none" id="fileShow" >
						<label for="textfield" class="control-label col-sm-2">附件</label>
						<div class="col-sm-10">
							<div class="fileinput fileinput-new r_fileinput" data-provides="fileinput">
								<div class="input-group">
									<div class="form-control" data-trigger="fileinput">
										<i class="glyphicon glyphicon-file <c:if test='${empty m.fileName}'>fileinput-exists</c:if>">${m.fileName}</i>
										<span class="fileinput-filename"></span>
									</div>
									<span class="input-group-addon btn btn-default btn-file">
										<span class="fileinput-new">Select file</span>
									<span class="fileinput-exists">Change</span>
									<input type="file" name="files" >
									</span>
									<a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">发布状态</label>
						<div class="col-sm-10">
							<div class="y_validatainput y_clear">
								<div class="check-line col-xs-4 col-sm-2">
									<form:radiobutton path="state" id="c56" class='icheck-me' data-skin="square" data-color="blue" value="1" />
									<label class='inline' for="c56">已发布</label>
								</div>
								<div class="check-line col-xs-4 col-sm-2">
									<form:radiobutton path="state" id="c66" class='icheck-me' data-skin="square" data-color="blue" value="0" />
									<label class='inline' for="c66">未发布</label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2"><aebiz:showTitle titleId="content.m.note" />：</label>
						<div class="col-sm-10">
							 <form:textarea path="contentNote" id="note" rows="5" class="form-control"  data-rule-maxlength="1000"/>
							 <span class="star-red">最多输入1000字</span>
						</div>
					</div>
					
					<div class="form-group none" id="textShow">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="content.m.introduction"/></label>
						<div class="col-sm-10">
							<div class="col-sm-11">
								<form:textarea path="introduction"  id="introduction"/>
							</div>
						</div>
					</div>
							
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="hidden" name="categoryType" value="${categoryType}">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>
				</form:form>
			</div>
		</div>
	</div>

<script>
	function changeShow(val){
		if(val=="1"){
			$("#fileShow").hide();
			$("#urlShow").hide();
			$("#textShow").show();
		}
		if(val=="2"){
			$("#fileShow").hide();
			$("#urlShow").show();
			$("#textShow").hide();
		}
		if(val=="3"){
			$("#fileShow").show();
			$("#urlShow").hide();
			$("#textShow").show();
		}
		
	}
	
    $(document).ready(function() {
    	changeShow("${m.contentType}");
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
    	
    	var ue = UE.getEditor('introduction');  //description 为域id
			$(".cancel").click(function(){
				history.go(-1) ;
			});   	
    });
</script>
</body>
</html>
<aebiz:showErrorMsg></aebiz:showErrorMsg>