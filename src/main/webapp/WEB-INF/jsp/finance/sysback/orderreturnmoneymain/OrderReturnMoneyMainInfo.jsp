<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<%@include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp"%>
	<%@include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>
	<!-- Custom file upload   -->
</head>

<body>
	<div class="container-fluid" >
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="orderreturnmoneymain.moduleName_CN" />
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="orderreturnmoneymain.menuOne" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="orderreturnmoneymain.menuTwo" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.view" /><aebiz:showTitle titleId="orderreturnmoneymain.menu" /></span></li>
			</ul>
		</div>
		
		<div class="box box-bordered  bordered-top">
				<div class="box-content nopadding">
					<form action="#" method="post" class='form-horizontal form-bordered form-validate'  >
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderreturnmoneymain.m.orderMainUuid" /></label>
							<div class="col-sm-10">
								<div class="col-xs-8">
									${m.orderMainUuid}
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderreturnmoneymain.m.customerUuid" /></label>
							<div class="col-sm-10">
								<div class="col-xs-8">
									${m.customerName}
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderreturnmoneymain.m.applyTime" /></label>
							<div class="col-sm-10">
								<div class="col-xs-8">
									${m.applyTime}
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderreturnmoneymain.m.backMoney" /></label>
							<div class="col-sm-10">
								<div class="col-xs-8">
									<aebiz:showTitle titleId="pub.moneytype" />${m.backMoney}
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderreturnmoneymain.m.reason" /></label>
							<div class="col-sm-10">
								<div class="col-xs-8">
									${m.reason}
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderreturnmoneymain.m.stateName" /></label>
							<div class="col-sm-10">
								<div class="col-xs-8">
									${m.stateName}
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderreturnmoneymain.m.returnTypeName" /></label>
							<div class="col-sm-10">
								<div class="col-xs-8">
									${m.returnTypeName}
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textarea" class="control-label col-sm-2"><aebiz:showTitle titleId="orderreturnmoneymain.m.description" /> </label>
							<div class="col-sm-10">
								<div class="col-xs-8">
									${m.description}
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderreturnmoneymain.m.remarks"/></label>
							<div class="col-sm-10">
								<input type="text" name="remarks" id="remarks" class="form-control" value="${m.remarks}">
							</div>
						</div>
						<div class="form-actions col-sm-offset-2 col-sm-10">
						<c:if test="${m.returnType == '3' && m.state =='2'}">
								<button type="button" class="btn btn-primary" id="returnSuccess" onclick="javascript:confirmReturn();"><aebiz:showTitle titleId="orderreturnmoneymain.message.returnSuccess"/></button>
						</c:if>
						<button type="button" class="btn btn-primary" onclick="javascript:window.location.href='${pageContext.servletContext.contextPath}/sysback/orderreturnmoneymain/toList'"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
					</div>	
					</form>
				</div>
		</div>
		
 
	</div>
	
</body>
<script>
	function confirmReturn() {
		var remarks=$("#remarks").val();
		if(remarks == null || remarks == ""){
				bootbox.alert('<aebiz:showTitle titleId="orderreturnmoneymain.message.remarksRequired"/>');
				return false;
		}
		else{
				bootbox.confirm('<aebiz:showTitle titleId="orderreturnmoneymain.message.confirm"/>',function(r){
					if(r){
							$.getJSON(
								"${pageContext.servletContext.contextPath}/sysback/orderreturnmoneymain/returnSuccess/${m.uuid}",
								{
									remarks : remarks,
									ranNum : Math.random()
								},
								function(data) {
									if (data.message) {
										bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>');
										$("#returnSuccess").hide();
									}
									else{
										bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
									}	
							})
					}
			});
		}
	}
</script>
</html>
