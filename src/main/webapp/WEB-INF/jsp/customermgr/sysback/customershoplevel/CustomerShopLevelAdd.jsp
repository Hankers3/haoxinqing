 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>

<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left"><h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="customershoplevel.moduleName_CN"/></h1></div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customershoplevel.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="customershoplevel.menuTwo"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="customershoplevel.moduleName_CN"/></span></li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top mt_10">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/customershoplevel/doAdd" method="post" class='form-horizontal form-bordered form-validate' enctype="multipart/form-data">		
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customershoplevel.m.levelName"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="hidden" name="levelNameHidden" value="true">
								<input type="text" name="levelName" class="form-control" data-rule-required="true" data-rule-minlength="1" data-rule-maxlength="10">
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customershoplevel.m.levelIcon"/></label>
						<div class="col-sm-10">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 75px; height: 75px;"></div>
								<div>
									<span class="btn btn-default btn-file">
										<span class="fileinput-new">Select image</span>
										<span class="fileinput-exists">Change</span>
										<input type="file" name="files">
									</span>
									<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customershoplevel.m.integralRange"/></label>
						<div class="col-sm-10">
							<div class="col-xs-2">
								<input type="text" name="minIntegral" class="form-control" data-rule-required="true" data-rule-number="true" data-rule-minlength="1" data-rule-maxlength="10">
							</div>
							<div class="y_sjjjd">-</div>
							<div class="col-xs-2">
								<input type="hidden" name="maxIntegralHidden" value="true">
								<input type="text" name="maxIntegral" class="form-control" data-rule-required="true" data-rule-number="true" data-rule-minlength="1" data-rule-maxlength="10">
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customershoplevel.m.position"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="text" name="position" class="form-control"  data-rule-required="true" data-rule-number="true" data-rule-minlength="1" data-rule-maxlength="3">
							</div>
						</div>
					</div>	
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customershoplevel.m.description"/></label>
						<div class="col-sm-10">
							<textarea rows="5" name="description" class="form-control" data-rule-maxlength="250"></textarea>
						</div>
					</div>
					
					<div class="y_fixedbtn">
						<input type="button" class="btn btn-primary submit btn-large a_size_1" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
						<button type="button" class="btn cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
					</div>					
				</form>
			</div>
		</div>
	</div>
	
<script>
    $(document).ready(function() {
    	$(".submit").click(function(){
    		var levelNameError=$.trim($("input[name='levelNameHidden']").val());
    		var maxIntegralError=$.trim($("input[name='maxIntegralHidden']").val());
    		if(levelNameError=="true" && maxIntegralError=="true"){
    			$("#mainForm").submit();
    		}
    	})
    	
    	//验证等级名称是否重复
    	$("input[name='levelName']").blur(function(){
    		$("#levelNameSpan").remove();
    		var levelName=$.trim($("input[name='levelName']").val());
    		if(levelName!=""){
    			var uuid="";
    			var url="${pageContext.servletContext.contextPath}/sysback/customershoplevel/checkLevelName";
    			$.get(url,{levelName:levelName,uuid:uuid,ranNum:Math.random()},function(data){
    				if(data=="true"){
    					$("input[name='levelName']").after("<span id='levelNameSpan'><font color='red'><aebiz:showTitle titleId="customershoplevel.levelName.existed"/></font></span>");
    					$("input[name='levelNameHidden']").val("false");
    				}else{
    					$("#levelNameSpan").remove();
    					$("input[name='levelNameHidden']").val("true");
    				}
    			});
    		}
    	})
    	
    	//验证积分范围结束值必须大于初始值
    	$("input[name='maxIntegral']").blur(function(){
    		var minIntegral=parseInt($.trim($("input[name='minIntegral']").val()));
    		var maxIntegral=parseInt($.trim($("input[name='maxIntegral']").val()));
    		if(maxIntegral!=""&&minIntegral!=""){
    			if(minIntegral>=maxIntegral){
    				$("input[name='maxIntegral']").after("<span id='maxIntegralSpan'><font color='red'><aebiz:showTitle titleId="customershoplevel.maxIntegral.lessthanMinIntegral"/></font></span>");
    				$("input[name='maxIntegralHidden']").val("false");
    			}else{
    				$("input[name='maxIntegralHidden']").val("true");
    				$("#maxIntegralSpan").remove();
    			}
    		}
    	})
    	
		$(".cancel").click(function(){
			history.go(-1) ;
		});
    });
    
</script>
</body>

</html>
<aebiz:showErrorMsg></aebiz:showErrorMsg>