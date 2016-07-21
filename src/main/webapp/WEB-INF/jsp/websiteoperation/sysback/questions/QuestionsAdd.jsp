<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!doctype html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp"%>
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
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd" /><aebiz:showTitle titleId="questions.moduleName_CN" /></h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="questions.menuOne" /></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="questions.menuTwo" /></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd" /><aebiz:showTitle titleId="questions.moduleName_CN"/></span></li>
			</ul>
		</div>

		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/questions/addquestion" method="post" class='form-horizontal form-bordered form-validate'  enctype="multipart/form-data" >
					<input type="hidden" name="quizCategoryUuid" value="${quizCategoryUuid}">
					<input type="hidden" name="state"  value="1"  >
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="questions.m.title" /></label>
						<div class="col-sm-10">
							<input type="text" name="title" class="form-control" data-rule-required="true" data-rule-maxlength='100'>
						</div>
					</div>
					<!--
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="questions.m.state" /></label>
						<div class="col-sm-10">
							<div class="y_validatainput y_clear">
								<div class="check-line col-xs-4 col-sm-1">
									<input type="radio" id="c1" class='icheck-me' name="state"  value="1" checked data-skin="square" data-color="blue" >
									<label class='inline' for="c1"><aebiz:showTitle titleId="basebusiness.showmessage.yes"></aebiz:showTitle></label>
								</div>
								<div class="check-line col-xs-4 col-sm-1">
									<input type="radio" id="c2" class='icheck-me' name="state" value="0" data-skin="square" data-color="blue" data-rule-required="true">
									<label class='inline' for="c2"><aebiz:showTitle titleId="basebusiness.showmessage.no"></aebiz:showTitle></label>
								</div>
							</div>
						</div>
					</div>
			
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="questions.m.questionType" /></label>
						<div class="col-sm-10">
							<div class="col-xs-2">
								<select name="quizCategoryUuid" id="quizCategoryUuid" class='form-control' data-rule-required="true" onchange="selectType();">
									<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"></aebiz:showTitle>-</option>	
									<c:forEach items="${questionTypeList}" var="dpp">	
										<option value="${dpp.uuid}">${dpp.categoryName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>-->
					<!--		
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
										<input type="file" name="imgFile"   data-rule-required="true" />
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
									<textarea  name="remark" class="form-control" data-rule-required="true" data-rule-maxlength='200' maxlength='200'></textarea>
								</div>
							</div>
						</div>
					</div>
			
					<div class="form-group">
					<label for="textfield" class="control-label col-sm-2">创建时间</label>
						<div class="col-sm-10">
							<div class="y_validatainput y_clear">
								<input  name="createTime" class="form-control datepick " data-rule-required="true" ></input>
							</div>
						</div>
					</div>
					-->
					<div class="form-group" id="questionList" >
						<input id ="temp" name="temp" type="hidden" value="1">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="questions.m.questionOptions"/></label>
						<div class="col-sm-10" >
							<div class="col-xs-10 y_formminhei" id="addDiv">
								<a class="btn addQuestion input-group mb_10" title='<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>' rel="tooltip">
									<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>
								</a>
							</div>
						</div>
					</div>

					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
						<button type="button" class="btn cancel">
							<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

<script>

	
	$(".addQuestion").click(function(){
		var tempId = "newDiv"+$("#temp").val() ;
		var tempval = "score"+$("#temp").val() ;

		var appendDiv = "<div id='"+tempId+"'><div class='input-group mb_10 exam_block'>" ;
		appendDiv += "<label class='fl' style=' padding: 6px 10px;'>选项</label><div class='col-sm-8'><input type='text' name='"+tempId+"' placeholder='enter option' class='form-control' data-rule-required='true' data-rule-maxlength='100'></div>" ;
		appendDiv += "<label class='fl' style=' padding: 6px 10px;'>分值</label><div class='col-sm-2'><input type='text' name='"+tempval+"' placeholder='enter option' class='form-control' data-rule-required='true' data-rule-maxlength='50'  ></div>" ;
		appendDiv += "<span class='input-group-btn'>" ;

		
		appendDiv += "<a class='btn' onclick='javascript:removeQuestion(\""+tempId+"\");' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete" />' rel='tooltip'><i class='fa fa-trash-o'></i></a>" ;
		appendDiv += "</span></div>" ;
		
		$("#addDiv").append(appendDiv) ;
		
		$("#temp").val((parseInt($("#temp").val())+1)) ;
	});
	
	function removeQuestion(id){		
		$("#"+id).remove() ;		
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