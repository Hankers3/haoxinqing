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
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 


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
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/questions/updatequestion" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">
					<form:hidden path="uuid" />
					<form:hidden path="quizCategoryUuid" />
					<form:hidden path="image"/>
					<form:hidden path="createTime"/>
					<form:hidden path="state"/>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">
							<aebiz:showTitle titleId="questions.m.title" /></label>
						<div class="col-sm-10">
							<form:input path="title" class='form-control' data-rule-required="true" data-rule-maxlength="100"/>
						</div>
					</div>
					
					<!--	
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
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="questions.m.questionType" /></label>
						<div class="col-sm-10">
							<div class="col-xs-2">
								<form:select path="quizCategoryUuid" id="quizCategoryUuid" class="form-control" data-rule-required="true">
									<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"></aebiz:showTitle>-</form:option>
									<c:forEach items="${questionTypeList}" var="dpp">	
										<form:option value="${dpp.uuid}" >${dpp.categoryName}</form:option>
									</c:forEach>	
								</form:select>
							</div>
						</div>
					</div>
					
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">&nbsp;缩略图</label>
						<div class="col-sm-10">
							<div class="fileinput <c:choose><c:when test="${empty m.image}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
								<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 150px; height: 150px;">
									<c:if test="${!empty m.image}"><img src="${m.imageUrl}" /></c:if>
								</div>
								<div>
									<span class="btn btn-default btn-file">
										<span class="fileinput-new">Select image</span>
										<span class="fileinput-exists">Change</span>
										<input type="file" name="imgFile" />
									</span>
									<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">测试简介</label>
						<div class="col-sm-10">
							<div class="y_validatainput y_clear">
							<div class="col-sm-10">
									<form:textarea  path="remark" class="form-control" data-rule-required="true" data-rule-maxlength='200' maxlength='200'></form:textarea>
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
					-->
					
					
					<div class="form-group" id="questionList" >
						<input name="temp" id="temp" type="hidden" value="${num}">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="questions.m.questionOptions"/></label>
						<div class="col-sm-10" >
							<div class="col-xs-10 y_formminhei" id="addDiv">
								<a class="btn addQuestion input-group mb_10" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip">
									<aebiz:showTitle titleId="basebusiness.showmessage.newAdd" />
								</a>
								<c:if test="${!empty optionsList}">
									<c:forEach items="${optionsList}" var="option" varStatus="s">
										<div id="newDiv${s.index}">
											<div class="input-group mb_10">
												<label class='fl' style=' padding: 6px 10px;'>选项</label>
												<div class='col-sm-8'>
													<input type="hidden" name="uuid${s.index}" class="form-control" value="${option.uuid}" >	
													<input type="text" name="newDiv${s.index}" class="form-control" value="${option.optionTitle}" data-rule-required='true' data-rule-maxlength='50'>	
												</div>
												<label class='fl' style=' padding: 6px 10px;'>分值</label>
												<div class='col-sm-2'>
													<input type="text" name="score${s.index}" class="form-control" value="${option.optionValue}" data-rule-required='true' data-rule-maxlength='50'>	
												</div>
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
	
	$("#questionType").change(function(){
		var selectIndex= $("#questionType").val();
		if(selectIndex==3){			
			$("#questionList").hide() ;
		}else{
			$("#questionList").show() ;			
		}
	});
	
	$(".addQuestion").click(function(){
		var tempId = "newDiv"+$("#temp").val() ;
		var tempval = "score"+$("#temp").val() ;
		var appendDiv = "<div id='"+tempId+"'><div class='input-group mb_10 exam_block'>" ;
		appendDiv += "<label class='fl' style=' padding: 6px 10px;'>选项</label><div class='col-sm-8'><input type='text' name='"+tempId+"' placeholder='enter option' class='form-control' data-rule-required='true' data-rule-maxlength='100'></div>" ;
		appendDiv += "<label class='fl' style=' padding: 6px 10px;'>分值</label><div class='col-sm-2'><input type='text' name='"+tempval+"' placeholder='enter option' class='form-control' data-rule-required='true' data-rule-maxlength='50'></div>" ;
		appendDiv += "<span class='input-group-btn'>" ;
		
		appendDiv += "<a class='btn' onclick='javascript:removeQuestion(\""+tempId+"\");' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete" />' rel='tooltip'><i class='fa fa-trash-o'></i></a>" ;
		appendDiv += "</span></div>" ;
		
		$("#addDiv").append(appendDiv) ;
		$("#temp").val((parseInt($("#temp").val())+1)) ;			
	});

	function removeQuestion(id){		
		$("#"+$.trim(id)).remove() ;		
	}
	
</script>


<script>

	
	$(document).ready(function() {
		$(".cancel").click(function() {
			history.go(-1);
		});
	});
</script>
	
</body>
</html>
<aebiz:showErrorMsg></aebiz:showErrorMsg>