<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<!--设置浮动按钮-->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>收藏内容详情</h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>运营系统</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>收藏管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>收藏内容详情</span>
					<i class="fa fa-angle-right"></i>
				</li>
			</ul>
		</div>	
		
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">					
				<form:form id="" class='form-horizontal form-validate form-bordered' >			
					<div class="form-group">
						<div class="col-sm-12">
							<i class="fa fa-user"></i>收藏内容详情
						</div>
					</div>
					
					<table class="table table-bordered mb_0">
		      	<tbody>
							<tr>
								<th width="15%">收藏内容标题</th>
								<td width="85%">${m.contentTitle}</td>
							</tr>
							<tr>	
								<th>收藏介绍</th>
								<td>
								${m.introduction}
								</td>
							</tr>
							<tr>
								<th>收藏关键字</th>
								<td>
								${m.keyword}
								</td>
							</tr>
							<tr>
								<th>收藏目录名</th>
								<td>
									${m.categoryName}
								</td>
							</tr>
							
							<tr>
								<th>收藏备注</th>
								<td>
								${m.contentNote}
								</td>
							</tr>
						</tbody>
					</table>		
				</form:form>	
			</div>
		</div>
		
		<div class="y_fixedbtn">
			<button type="button" class="btn btn-primary cancel btn-large a_size_1">返回</button>
		</div>
	</div>	

</body>
					
</html>
<script>
    $(document).ready(function() {
			$(".cancel").click(function(){
				history.go(-1) ;
			});	    	
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>