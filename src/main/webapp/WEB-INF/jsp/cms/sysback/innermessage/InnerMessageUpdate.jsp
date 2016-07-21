<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/basebusiness/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/basebusiness/ueditor/ueditor.all.js"></script>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/bootstrap.min.css ">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js "></script>
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="innermessage.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="innermessage.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="innermessage.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="innermessage.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/innermessage/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">							
					<form:hidden path="uuid"/>	
					<form:hidden path="sendUser"/>	
					<form:hidden path="sendTime"/>		
					<form:hidden path="readStatus"/>	
					<form:hidden path="userImage"/>	
					<form:hidden path="sendStatus"/>
					<form:hidden path="image"/>	
					
						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="innermessage.m.title"/></label>
						<div class="col-sm-10">
							<form:input path="title" class='form-control'  />
						</div>
					</div>
					
					<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="innermessage.m.accountType"/></label>
							<div class="col-sm-10">
								<%-- <div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="accountType" id="c5" value="0" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c5">所有</label>
									</div>
								</div> --%>
								
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="accountType" id="c6" value="1" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c6">家政员</label>
									</div>
								</div>

																
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="accountType" id="c7" value="2" class="icheck-me"  data-skin="square" data-color="blue"/>
										<label class='inline' for="c7">会员</label>
									</div>
								</div>
							</div>
					</div>
		
		
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="innermessage.m.image"/></label>
						<div class="col-sm-10">
							<div class="fileinput fileinput-exists " data-provides="fileinput">
								<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 100px; height: 100px;">
									<c:if test="${!empty m.image}"><img src="${m.imgUrl}" /></c:if>
								</div>
								<div>
									<span class="btn btn-default btn-file">
										<span class="fileinput-new">Select image</span>
										<span class="fileinput-exists">Change</span>
										<input type="file" name="files"/>
									</span>
									<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="innermessage.m.content"/></label>
						<div class="col-sm-10">
							<form:textarea path="content"  id="content"  />
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
    	var ue = UE.getEditor('content');  //description 为域id
			$(".cancel").click(function(){
				history.go(-1) ;
			});	    	
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>