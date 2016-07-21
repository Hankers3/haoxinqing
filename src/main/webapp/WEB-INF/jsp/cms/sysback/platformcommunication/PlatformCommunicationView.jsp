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
				<h1><aebiz:showTitle titleId="ordershow.m.orderShowReview"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="ordershow.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="ordershow.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="ordershow.m.orderShowReview"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-color box-bordered blue y_orderbx">
			<div class="box-title">
				<h3><aebiz:showTitle titleId="platformcommunication.m.responseMsg"/></h3>
				<div class="actions"><a class="btn btn-mini content-slideUp" href="#"><i class="fa fa-angle-down"></i></a></div>
			</div>
		<%-- 	<div class="box-content">
				<div class="shenhe-state">
				   <h2>
				   	<c:if test="${pam.state==1}">
				   		<aebiz:showTitle titleId="basebusiness.showmessage.uncheck"/>
				   	</c:if>
				   	<c:if test="${pam.state==2}">
				   		<aebiz:showTitle titleId="basebusiness.showmessage.checkPass"/>
				   	</c:if>
				   	<c:if test="${pam.state==3}">
				   		<aebiz:showTitle titleId="basebusiness.showmessage.checkUnPass"/>
				   	</c:if>
				   </h2>
				</div> --%>
				<div class="row y_orderbox">
					<div class="col-sm-12">
						<div class="y_odxxbx">
							<h4><aebiz:showTitle titleId="platformcommunication.m.platformMsg"/></h4>
					    <table class="table table-bordered">
								<tbody>
									<tr>
										<th width="15%"><aebiz:showTitle titleId="platformcommunication.m.platformrName"/></th>
										<td width="85%">${m.platformrName}</td>
									</tr>
									<tr>	
										<th><aebiz:showTitle titleId="platformcommunication.m.createTime"/></th>
										<td>
										${m.createTime}
										</td>
									</tr>
									<tr>
										<th><aebiz:showTitle titleId="platformcommunication.m.problemDescription"/></th>
										<td>
										${m.problemDescription}
										</td>
									</tr>
								</tbody>
							</table>	
						</div>
					</div>
					<div class="col-sm-12">
						<div class="y_odxxbx">
							<h4><aebiz:showTitle titleId="platformcommunication.m.responseMsg"/></h4>
					    <table class="table table-bordered">
								<tbody>
									<tr>
										<th width="15%"><aebiz:showTitle titleId="platformcommunication.m.responseState"/></th>
										<td width="20%">
										 	<c:if test="${m.replyState==1}">
				   							<aebiz:showTitle titleId="platformcommunication.m.responsed"/>
				   						</c:if>
				   						<c:if test="${m.replyState==2}">
				   							<aebiz:showTitle titleId="platformcommunication.m.unresponsed"/>
				   						</c:if>
										</td>
										<th width="15%"><aebiz:showTitle titleId="platformcommunication.m.responsePeople"/></th>
										<td width="20%">${m.managerName}</td>
										<th width="15"><aebiz:showTitle titleId="platformcommunication.m.replyTime"/></th>
										<td width="20%">${m.replyTime}</td>
									</tr>
									<tr>
										<th><aebiz:showTitle titleId="platformcommunication.m.responseMsg"/></th>
										<td colspan="5"><textarea id="remark" name="remark" class="form-control">${m.replyMessage}</textarea></td>
									</tr>
								</tbody>
							</table>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="y_fixedbtn">
			<button id="response" type="submit" class="btn btn-primary btn-large a_size_1">保存</button>
			<button type="button" class="btn cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
		</div>	
	</div>
</body>
</html>
<script>
    $(document).ready(function() {
    	
    	$("#response").click(function(){
    		var remark = $("#remark").val()
    			if(remark==""){
    				alert("<aebiz:showTitle titleId="productappraise.m.pleaseInputReviewDesc"/>");
    				return;	
    			}
    			if(remark!="" && remark.length>250){
    				alert("<aebiz:showTitle titleId="productappraise.m.reviewDescOverLength"/>");
    				reutrn;
    			}
    			$.get(
			    	"${pageContext.servletContext.contextPath}/sysback/platformcommunication/updateReplyState/${m.uuid}",
			    	{"state":"1",remark:remark,ranNum:Math.random()},	
				    function(data) {  
				
				       if(data=="success") {
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
				       		location.href="${pageContext.servletContext.contextPath}/sysback/platformcommunication/toList";	
				       }else if(data=="fail"){
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }else{
				       		alert(data);	
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