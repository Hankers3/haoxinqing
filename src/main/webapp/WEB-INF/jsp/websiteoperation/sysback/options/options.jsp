<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp"%>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
	 
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="questions.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.edit" /></h1>
			</div>
		</div>

		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="questions.menuOne" /></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="questions.menuTwo" /></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="questions.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.edit" /></span></li>
			</ul>
		</div>
		
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/questions/updatequestion" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>
					<form:hidden path="uuid" />
					<form:hidden path="state" />
					<form:hidden path="title" />
					<form:hidden path="questionType" />
					
					
					
					<div class="form-group" id="questionList" >
						<input name="temp" id="temp" type="hidden" value="${num}">  <!--  选项的数量-->
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="questions.m.questionOptions"/></label>
						<div class="col-sm-10" >
							<div class="col-xs-8 y_formminhei" id="addDiv">
								<a class="btn addQuestion input-group mb_10" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip">
									<aebiz:showTitle titleId="basebusiness.showmessage.newAdd" />
								</a>
								<c:if test="${!empty optionsList}">
									<c:forEach items="${optionsList}" var="option" varStatus="s">
										<div id="newDiv${s.index}">
											<div class="input-group mb_10">
												选项：<input type="text" name="newDiv${s.index}" class="form-control" value="${option.optionTitle}" data-rule-required='true' data-rule-maxlength='50'>
												分数：<input type="text" name="score${s.index}" class="form-control" value="${option.optionValue}" data-rule-required='true' data-rule-maxlength='50'>	
												<span class="input-group-btn">
													<a class="btn" onclick="javascript:removeQuestion('newDiv${s.index}');" title="<aebiz:showTitle titleId='questions.m.delOptions'/>" rel="tooltip"><i class="fa fa-trash-o"></i></a>
												</span>
											</div>
										</div>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>

					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel">
							<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
						</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>

<script>
	var parentDiv=document.getElementById("addDiv");
	var tempObj=document.getElementById("temp");
	var value=tempObj.value;
	
	
	$(".addQuestion").click(function(){
		var tempId = "newDiv"+$("#temp").val() ;  //设置选项标题id从几开始
		var optionValueId = "score"+$("#temp").val() ;  //设置选项分数id从几开始
		var appendDiv = "<div id='"+tempId+"'><div>" ; //<div class='input-group mb_10'>
		
		appendDiv += "选项：<input type='text' name='"+tempId+"' placeholder='enter option' class='form-control' data-rule-required='true' data-rule-maxlength='50'>分数：" ;	  // 分数加到下面怎么就不行
		appendDiv += "<span class='input-group-btn'>" ;
		appendDiv += "<input type='text' name='"+optionValueId+"' placeholder='enter option' class='form-control' data-rule-required='true' data-rule-maxlength='50'>" ;
		appendDiv += "<span class='input-group-btn'>" ;	
		appendDiv += "<a class='btn' onclick='javascript:removeQuestion(\""+tempId+"\");' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete" />' rel='tooltip'><i class='fa fa-trash-o'></i></a>" ;
		appendDiv += "</span></div>" ;
		
		$("#addDiv").append(appendDiv) ;
		$("#temp").val((parseInt($("#temp").val())+1)) ;			
	});

	function removeQuestion(id){		
		$("#"+$.trim(id)).remove() ;		
	}
	
	$(document).ready(function() {
		$(".cancel").click(function() {
			history.go(-1);
		});
	});
</script>
	
</body>
</html>
<aebiz:showErrorMsg></aebiz:showErrorMsg>