<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="doctornotice.moduleName_CN"/>详细信息<!--<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>--></h1>
			</div>
		</div>	
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="doctornotice.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="doctornotice.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="doctornotice.moduleName_CN"/>详细信息<!--<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>--></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">		
								
				<form:form id="mainForm" action="#" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>
						
							<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">公告详情</label>
							
						</div>			
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">公告标题</label>
							<div class="col-sm-10">
							&nbsp;${m.line}
							</div>
					
						</div>
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">医生姓名</label>
							
							<div class="col-sm-10">
							&nbsp;${m.doctorName}
							</div>
						</div>
						
						<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">发布时间</label>
							<div class="col-sm-10">
						&nbsp;${m.createTime}
						</div>
						
						</div>
						<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">内容</label>
					 	<div class="col-sm-10">
						&nbsp;${m.content}
						</div>
						</div>
						</div>							
									
							
										<div class="y_fixedbtn">
										<button id="deleteById" type="button" class="btn btn-primary btn-large a_size_1">删除</button>
										<button type="button" class="btn cancel btn-large a_size_1">返回</button>
									</div>							
									</form:form>
								</div>
							</div>
						</div>
					</body>
					</html>
					<script>
					  
					  
					  
					  
					  
		 $(document).ready(function() {
    	
    	$("#deleteById").click(function(){
    		
    	
    			$.get(
			    	"${pageContext.servletContext.contextPath}/sysback/doctornotice/deleteById/${m.uuid}",
				    function(data) {  
				    	
				       if(data=="success") {
				       		
				       		location.href="${pageContext.servletContext.contextPath}/sysback/doctornotice/toList";	
				       }else if(data=="fail"){
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }
				    }
				);
			
    	});
    	});
						   
							
    	
    	
		$(".cancel").click(function(){
			history.go(-1) ;
		});	    	
   
</script>
<aebiz:showErrorMsg></aebiz:showErrorMsg>