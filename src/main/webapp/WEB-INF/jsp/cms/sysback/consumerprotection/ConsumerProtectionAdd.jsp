<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="consumerprotection.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="consumerprotection.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="consumerprotection.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="consumerprotection.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/consumerprotection/adds" method="post" class='form-horizontal form-bordered form-validate' enctype="multipart/form-data">						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">
							<aebiz:showTitle titleId="consumerprotection.m.name"/>
						</label>
						<div class="col-sm-10">
							<input type="text" name="name" class="form-control" data-rule-required="true" data-rule-maxlength="100">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="consumerprotection.m.icon"/></label>
						<div class="col-sm-10">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 75px; height: 75px;"></div>
								<div>
									<span class="btn btn-default btn-file">
										<span class="fileinput-new">Select image</span>
										<span class="fileinput-exists">Change</span>
										<input type="file" name="files" data-rule-required="true">
									</span>
									<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="consumerprotection.m.needChooseProduct"/></label>
						<div class="col-sm-10">
							<div class="check-line col-xs-4 col-sm-2">
								<input type="radio" id="8" name="needChooseProduct" class='icheck-me' data-skin="square" data-color="blue" value="0" />
								<label class='inline' for="c8"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></label>
							</div>
							<div class="check-line col-xs-4 col-sm-2">
								<input type="radio" id="9" name="needChooseProduct" class='icheck-me' data-skin="square" data-color="blue" value="1" checked/>
								<label class='inline' for="c9"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></label>
							</div>
						</div>
					</div>


					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="consumerprotection.m.needAgreeProtocol"/></label>
						<div class="col-sm-10">
							<div class="check-line col-xs-4 col-sm-2">
								<input type="radio" id="8" name="needAgreeProtocol" class='icheck-me' data-skin="square" data-color="blue" value="0" />
								<label class='inline' for="c8"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></label>
							</div>
							<div class="check-line col-xs-4 col-sm-2">
								<input type="radio" id="9" name="needAgreeProtocol" class='icheck-me' data-skin="square" data-color="blue" value="1" checked/>
								<label class='inline' for="c9"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></label>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="consumerprotection.m.protocolContent"/></label>
						<div class="col-sm-10">
							<input type="text" name="protocolContent" class="form-control" data-rule-required="true" data-rule-maxlength="1000">
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="consumerprotection.m.note"/></label>
						<div class="col-sm-10">
							<input type="text" name="note" class="form-control" data-rule-maxlength="200">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="consumerprotection.m.state"/></label>
						<div class="col-sm-10">
							<div class="check-line col-xs-4 col-sm-2">
								<input type="radio" id="8" name="state" class='icheck-me' data-skin="square" data-color="blue" value="0" />
								<label class='inline' for="c8"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></label>
							</div>
							<div class="check-line col-xs-4 col-sm-2">
								<input type="radio" id="9" name="state" class='icheck-me' data-skin="square" data-color="blue" value="1" checked/>
								<label class='inline' for="c9"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></label>
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

		$("input[name=needAgreeProtocol]").click(function(){
			if($(this).val() == 0){
				$("input[name=protocolContent]").removeAttr("data-rule-required");
			}else{
				$("input[name=protocolContent]").attr("data-rule-required","true");
			}
		});
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>