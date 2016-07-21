<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp"%>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/chosen/chosen.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/chosen/chosen.jquery.min.js"></script>
</head>

<body>

	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="basebusiness.showmessage.newAdd" /><aebiz:showTitle titleId="tagscategory.moduleName_CN" />
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="tagscategory.menuOne" /></span>
					<i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="tagscategory.menuTwo" /></span>
					<i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd" /><aebiz:showTitle titleId="tagscategory.moduleName_CN" /></span></li>
			</ul>
		</div>

		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/tagscategory/add" method="post" class='form-horizontal form-bordered form-validate'>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="tagscategory.m.categoryName" /></label>
						<div class="col-sm-10">
							<input type="text" name="categoryName" class="form-control" data-rule-required="true" data-rule-maxlength="20" value="${m.categoryName}">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="tagscategory.m.categoryType" /></label>
						<div class="col-sm-10">
							<select name="categoryType" id="select" class='chosen-select inline-form' data-rule-required="true" >
								<c:forEach items="${tagsList}" var="item">
									<option value="${item.value }">${item.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="tagscategory.m.note" /></label>
						<div class="col-sm-10">
							<textarea name="note" class="form-control" data-rule-required="true" data-rule-maxlength="200">${m.categoryName}</textarea>
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
</body>

</html>


<script>
	$(document).ready(function() {
		$(".cancel").click(function() {
			history.go(-1);
		});
	});

	$(function(){
		// Chosen (chosen)
    if ($('.chosen-select').length > 0) {  
       $('.chosen-select').each(function() {
         var $el = $(this);
         var search = ($el.attr("data-nosearch") === "true") ? true : false,  //判断元素是否设置了data-nosearch="true"属性
           opt = {};
         if (search) opt.disable_search_threshold = 9999999;
         $el.chosen(opt);  //调用js
       });
    }	
	})	
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>