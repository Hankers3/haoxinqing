<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>

<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>  

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="websiteoperate.moduleName_CN"/><aebiz:showTitle titleId="websiteoperate.param.setting"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="websiteoperate.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="websiteoperate.menuTwo"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="websiteoperate.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span></li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/websiteoperate/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
					<form:hidden path="moduleName"/>	
					<form:hidden path="resourceName"/>	
					<form:hidden path="resourceKey"/>
					<form:hidden path="paramType"/>
					<form:hidden path="paramContent"/>
					<input type="hidden" id="valueHidden" value="${m.paramValue}">
							
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="websiteoperate.m.moduleName"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<c:forEach items="${moduleList}" var="dpp">
									<c:if test="${m.moduleName==dpp.name}">
										<c:out value="${dpp.value}"></c:out>
									</c:if>
								</c:forEach>
							</div>	
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="websiteoperate.m.resourceName"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<c:out value="${m.resourceName}"></c:out>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="websiteoperate.m.paramValue"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4" id="paramContentDiv">
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="websiteoperate.m.note"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<form:textarea path="note" rows="4" class="form-control"/>
							</div>
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
	
<script>
    $(document).ready(function() {
		var content=$("#paramContent").val();
		var json=eval(content);  
		//参数类型分两种类型文本型（值用1表示）和单选型（值用2表示）
		//参数类型paramType为2表示为单选，参数值paramValue为参数内容paramContent中json字符串的value值
		if($("#paramType").val()=="2"){
			$.each(json,function(index,item){
				var id="c"+index;
				var appendDiv="<div class='check-line'>";
				if($("#valueHidden").val()==item.value){
					appendDiv +="<input type='radio' name='paramValue' id='"+id+"' value='"+item.value+"' checked class='icheck-me' data-skin='square' data-color='blue'>";
				}else{
					appendDiv +="<input type='radio' name='paramValue' id='"+id+"' value='"+item.value+"' class='icheck-me' data-skin='square' data-color='blue'>";
				}
				appendDiv +="<label class='inline' for='"+id+"'>";
				appendDiv +=item.name;
				appendDiv +="</label></div>";
				$("#paramContentDiv").append(appendDiv);
			});
		}else{
			//若参数类型paramType为1,表示参数类型为文本型，此时参数值paramValue的值和参数内容paramContent的值相同
			$("#paramContentDiv").append("<input type='text' name='paramValue' class='form-control' value='${m.paramValue}' data-rule-maxlength='40'/>");
		}
	
		
		$(".cancel").click(function(){
			history.go(-1) ;
		});	    	
    });
</script>

<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>

</body>
</html>
<aebiz:showErrorMsg></aebiz:showErrorMsg>