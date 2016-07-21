<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

<!--设置浮动按钮-->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.uncheck"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.check"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.uncheck"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.uncheck"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-color box-bordered blue y_orderbx">
			<div class="box-title">
				<h3><aebiz:showTitle titleId="basebusiness.showmessage.uncheck"/></h3>
				<div class="actions"><a class="btn btn-mini content-slideUp" href="#"><i class="fa fa-angle-down"></i></a></div>
			</div>
			<div class="box-content">
			<%-- 	<div class="shenhe-state">
				   <h2>
				   	<c:if test="${pam.state==0}">
				   		未审核
				   	</c:if>
				   	<c:if test="${pam.state==1}">
				   		已审核
				   	</c:if>
				   </h2>
				</div> --%>
				<div class="row y_orderbox">
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class="y_odxxbx">
							<h4>审核信息</h4>
					    <table class="table table-bordered">
								<tbody>
									<tr>
										<th width="15%">审核状态</th>
										<td width="20%">
										 	<c:if test="${m.state==0}">
				   							未审核
				   						</c:if>
				   						<c:if test="${m.state==1}">
				   							已审核
				   						</c:if>
										</td>
										<th width="15%">审核人</th>
										<td width="20%">${m.docoterName}</td>
										<th width="15">审核时间</th>
										<td width="20%"></td>
									</tr>
									<tr>
										<th>审核信息</th>
										<td colspan="5"><textarea id="remark" name="remark" class="form-control">${pam.reviewDesc}</textarea></td>
									</tr>
								</tbody>
							</table>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="y_fixedbtn">
			<button id="checkPass" type="submit" class="btn btn-primary btn-large a_size_1">通过</button>
			<button id="checkUnPass" type="submit" class="btn btn-default  btn-large a_size_1">不通过</button>
			<button type="button" class="btn cancel btn-large a_size_1">返回</button>
		</div>	
	</div>
</body>
</html>
<script>
    $(document).ready(function() {
    	//修改状态
    	$("#checkPass").click(function(){
    		var remark = $("#remark").val()
    			$.get(
			    	"${pageContext.servletContext.contextPath}/sysback/consultrecord/updateState/${m.uuid}",
			    	{"state":"1",remark:remark,ranNum:Math.random()},	
				    function(data) {  
				    	//alert(data);
				       if(data=="success") {
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
				       		location.href="${pageContext.servletContext.contextPath}/sysback/consultrecord/toOlineList";	
				       }else if(data=="fail"){
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }else{
				       		//alert(data);	
				       }
				    }
				);
    	});
    	//不修改状态
    	$("#checkUnPass").click(function(){
    		var remark = $("#remark").val()
    			$.get(
			    	"${pageContext.servletContext.contextPath}/sysback/consultrecord/updateState/${m.uuid}",
			    	{"state":"0",remark:remark,ranNum:Math.random()},	
				    function(data) {  
				       if(data=="success") {
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
				       		location.href="${pageContext.servletContext.contextPath}/sysback/consultrecord/toOlineList";	
				       }else if(data=="fail"){
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }else{
				       }
				    }
				);
    	});
    	
    	
    	
			$(".cancel").click(function(){
				history.go(-1) ;
			});	    	
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>