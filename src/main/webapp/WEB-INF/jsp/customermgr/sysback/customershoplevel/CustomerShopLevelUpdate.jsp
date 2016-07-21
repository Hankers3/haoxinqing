<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left"><h1><aebiz:showTitle titleId="basebusiness.showmessage.edit"/><aebiz:showTitle titleId="customershoplevel.moduleName_CN"/></h1></div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customershoplevel.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="customershoplevel.menuTwo"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.edit"/><aebiz:showTitle titleId="customershoplevel.moduleName_CN"/></span></li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top mt_10">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/customershoplevel/doUpdate" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">							
					<form:hidden path="uuid"/>	
					<form:hidden path="levelIcon"/>	
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customershoplevel.m.levelName"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input path="levelName" class='form-control'  data-rule-required="true" data-rule-minlength="1" data-rule-maxlength="10"/>
								<input type="hidden" name="levelNameHidden" value="true">
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customershoplevel.m.levelIcon" /></label>
						<div class="col-sm-10">
							<div class="fileinput <c:choose><c:when test="${empty m.levelIcon}">fileinput-new</c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
								<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 75px; height: 75px;">
									<c:if test="${!empty m.levelIcon}"><img src="${m.imgUrl}" /></c:if>
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
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customershoplevel.m.integralRange"/></label>
						<div class="col-sm-10">
							<div class="col-xs-2">
								<form:input path="minIntegral" class='form-control'  data-rule-required="true" data-rule-number="true" data-rule-minlength="1" data-rule-maxlength="10"/>
							</div>
							<div class="y_sjjjd">-</div>
							<div class="col-xs-2">
								<form:input path="maxIntegral" class='form-control'  data-rule-required="true" data-rule-number="true" data-rule-minlength="1" data-rule-maxlength="10"/>
								<input type="hidden" name="maxIntegralHidden" value="true">
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customershoplevel.m.position"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input path="position" class='form-control'  data-rule-number="true" data-rule-required="true" data-rule-minlength="1" data-rule-maxlength="3"/>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customershoplevel.m.description"/></label>
						<div class="col-sm-10">
							<form:textarea path="description" class='form-control' rows="5"/>
						</div>
					</div>
						
					<div class="y_fixedbtn">
						<input type="submit" class="btn btn-primary btn-large a_size_1 submit" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	
<script>
$(document).ready(function() {

	
	//验证等级名称是否重复
	$("input[name='levelName']").blur(function(){
		$("#levelNameSpan").remove();
		var levelName=$.trim($("input[name='levelName']").val());
		if(levelName!=""){
			var uuid=$("#uuid").val();
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
	//如果等级名称数据位空，就将错误提示信息清除
	$("input[name='levelName']").change(function(){
		if($.trim($("input[name='levelName']").val())==""){
			$("#levelNameSpan").remove();
		}
	});
	
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