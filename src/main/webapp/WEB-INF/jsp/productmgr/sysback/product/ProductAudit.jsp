<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

	<%@include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp"%>
	<!-- Custom file upload   -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/bootbox/jquery.bootbox.js"></script>
</head>

<body>
	<div class="container-fluid" >
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="productManagerList.productNote.audit" />
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="productbrand.menuOne" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="productbrand.menuTwo" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd" /><aebiz:showTitle titleId="productbrand.moduleName_CN" /></span></li>
			</ul>
		</div>
		<ul class="tabs tabs-inline tabs-top border1-top mb_20">
			<li class='active'>
				<a href="#ProductBase" data-toggle='tab'>基础信息</a>
			</li>
			<li>
				<a href="#ProductPrice" data-toggle='tab'>价格信息</a>
			</li>
			<li>
				<a href="#ProductImage" data-toggle='tab'>图片信息</a>
			</li>
			<li>
				<a href="#ProductTransfer" data-toggle='tab'>配送信息</a>
			</li>
			<li>
				<a href="#ProductNote" data-toggle='tab'>描述信息</a>
			</li>
		</ul>
		
		<div class="tab-content">
			<div class="tab-pane active" id="ProductBase">
				<form action="#" method="POST" class='form-horizontal form-bordered form-validate y_borderddd'>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.productName" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
							 	<p class="mb_10">${m.productMain.productName}&nbsp;</p>
							 	<p class="text-danger">${reason.productName}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('productName','01');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.adviceNote" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
							 	<p class="mb_10">${m.productMain.adviceNote}&nbsp;</p>
							 	<p class="text-danger">${reason.adviceNote}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('adviceNote','01');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.productNo" /></label>
						<div class="col-sm-10">
							<div class="col-xs-10">
							 	<p class="mb_10">${m.productMain.productNo}&nbsp;</p>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.categoryUuid" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
								<p class="mb_10">${m.productMain.categoryName}&nbsp;</p>
							 	<p class="text-danger">${reason.categoryName}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('categoryName','01');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.productType" /></label>
						<div class="col-sm-10">
							<div class="col-xs-10">
							 	<p class="mb_10">${m.productMain.productTypeName}&nbsp;</p>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.storeUuid" /></label>
						<div class="col-sm-10">
							<div class="col-xs-10">
							 	<p class="mb_10">${m.productMain.storeUuid}&nbsp;</p>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.auditState" /></label>
						<div class="col-sm-10">
							<div class="col-xs-10">
							 	<p class="mb_10">${m.productMain.auditName}&nbsp;</p>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.state" /></label>
						<div class="col-sm-10">
							<div class="col-xs-10">
							 	<p class="mb_10">${m.productMain.stateName}&nbsp;</p>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.createTime" /></label>
						<div class="col-sm-10">
							<div class="col-xs-10">
							 	<p class="mb_10">${m.productMain.createTime}&nbsp;</p>
							</div>
						</div>
					</div>
					
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.integral" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
								<p class="mb_10">${m.productMain.integral}</p>
							 	<p class="text-danger">${reason.integral}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('integral','01');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.leastIntegral" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
								<p class="mb_10">${m.productMain.leastIntegral}</p>
							 	<p class="text-danger">${reason.leastIntegral}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('leastIntegral','01');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					
					<!-- 普通商品 -->
					<c:if test="${'01' eq m.productMain.productType}">
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productManagerList.m.productattr" /></label>
							<div class="col-sm-10">
								<div class="col-xs-8">
								 	<p class="mb_10">&nbsp;</p>
								 	<p class="text-danger">${reason.productAttr}</p>
								</div>
								 
								<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
									<button type="button" class="btn btn-primary">查看</button>
									<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1">
											<aebiz:showTitle titleId="productManagerList.button.select" />
									</a>
								</label>
							</div>
						</div>
					</c:if>
					<div class="y_fixedbtn">
						<button type="button" class="btn btn-primary btn-large a_size_1" onclick="javascript:batchAudit('01','${m.productMain.uuid}');"><aebiz:showTitle titleId="productmainaudit.m.auditBatchPass" /></button>	
						<button type="button" class="btn btn-primary btn-large a_size_1"><aebiz:showTitle titleId="productManagerList.m.goback" /></button>								
					</div>
				</form>
			</div>
			<div class="tab-pane " id="ProductPrice">
				<form action="#" method="POST" class='form-horizontal form-bordered form-validate y_borderddd'>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmainbaseprice.m.marketPrice" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
								<p class="mb_10">${m.productPrice.marketPrice}</p>
							 	<p class="text-danger">${reason.marketPrice}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('marketPrice','02');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div> 
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmainbaseprice.m.shopPrice" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
								<p class="mb_10">${m.productPrice.shopPrice}</p>
							 	<p class="text-danger">${reason.shopPrice}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('shopPrice','02');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					
					<!-- 本地生活 -->
					<c:if test="${'04' eq m.productMain.productType}">
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmainbaseprice.m.time" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
								<p class="mb_10">${m.productPrice.startTime}-${m.productPrice.endTime}</p>
							 	<p class="text-danger">${reason.availableTime}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('availableTime','02');">
									<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					</c:if>
					
					<!-- 本地生活 -->
					<c:if test="${'05' eq m.productMain.productType}">
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmainpresale.m.firstPay" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
								<p class="mb_10">${m.productPreSale.firstPay}</p>
							 	<p class="text-danger">${reason.firstPay}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('firstPay','02');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmainpresale.m.lastPay" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
								<p class="mb_10">${m.productPreSale.lastPay}</p>
							 	<p class="text-danger">${reason.lastPay}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('lastPay','02');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmainpresale.m.lastPayTime" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
								<p class="mb_10">${m.productPreSale.lastPayTime}</p>
							 	<p class="text-danger">${reason.lastPayTime}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('lastPayTime','02');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					</c:if>
					<div class="y_fixedbtn">
						<button type="button" class="btn btn-primary btn-large a_size_1" onclick="javascript:batchAudit('02','${m.productMain.uuid}');"><aebiz:showTitle titleId="productmainaudit.m.auditBatchPass" /></button>	
						<button type="button" class="btn btn-primary btn-large a_size_1"><aebiz:showTitle titleId="productManagerList.m.goback" /></button>								
					</div>
				</form>
			</div>
			
			<div class="tab-pane " id="ProductImage">
				<form action="#" method="POST" class='form-horizontal form-bordered form-validate y_borderddd'>
					 
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmainimage.m.mainImage" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
								<p class="mb_10">  </p>
							 	<p class="text-danger">${reason.productImage}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('productImage','03');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					
					<div class="y_fixedbtn">
						<button type="button" class="btn btn-primary btn-large a_size_1" onclick="javascript:batchAudit('03','${m.productMain.uuid}');"><aebiz:showTitle titleId="productmainaudit.m.auditBatchPass" /></button>	
						<button type="button" class="btn btn-primary btn-large a_size_1"><aebiz:showTitle titleId="productManagerList.m.goback" /></button>								
					</div>
				</form> 
			</div>
			
			<div class="tab-pane " id="ProductTransfer">
				<form action="#" method="POST" class='form-horizontal form-bordered form-validate y_borderddd'>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaintransfer.m.weight" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
							 	<p class="mb_10"> ${m.productTransfer.weight}&nbsp;</p>
							 	<p class="text-danger"> </p>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaintransfer.m.length" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
							 	<p class="mb_10"> ${m.productTransfer.length}&nbsp;</p>
							 	<p class="text-danger"> </p>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaintransfer.m.width" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
							 	<p class="mb_10"> ${m.productTransfer.width}&nbsp;</p>
							 	<p class="text-danger"> </p>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaintransfer.m.height" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
							 	<p class="mb_10"> ${m.productTransfer.height}&nbsp;</p>
							 	<p class="text-danger"> </p>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaintransfer.m.transferTemplate" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
							 	<p class="mb_10"> ${m.productTransfer.transferTemplate}&nbsp;</p>
							 	<p class="text-danger"> </p>
							</div>
						</div>
					</div>
					
					<div class="y_fixedbtn">
						<button type="button" class="btn btn-primary btn-large a_size_1" onclick="javascript:batchAudit('04','${m.productMain.uuid}');"><aebiz:showTitle titleId="productmainaudit.m.auditBatchPass" /></button>	
						<button type="button" class="btn btn-primary btn-large a_size_1"><aebiz:showTitle titleId="productManagerList.m.goback" /></button>								
					</div>
				</form>
			</div>
			
			<div class="tab-pane " id="ProductNote">
				<form action="#" method="POST" class='form-horizontal form-bordered form-validate y_borderddd'>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaindescription.m.note" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
							 	<p class="mb_10"> ${m.productDescription.note}&nbsp;</p>
							 	<p class="text-danger">${reason.note}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('note','05');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaindescription.m.description" /></label>
						<div class="col-sm-10">
							<div class="col-xs-8">
							 	<p class="mb_10"> ${m.productDescription.description}&nbsp;</p>
							 	<p class="text-danger">${reason.description}</p>
							</div>
							<label for="textfield" class="control-label col-xs-4 y_formgpbtn">
								<button type="button" class="btn btn-primary">查看</button>
								<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="javascript:getReasonList('productImage','05');">
										<aebiz:showTitle titleId="productManagerList.button.select" />
								</a>
							</label>
						</div>
					</div>
					
					<div class="y_fixedbtn">
						<button type="button" class="btn btn-primary btn-large a_size_1" onclick="javascript:batchAudit('05','${m.productMain.uuid}');"><aebiz:showTitle titleId="productmainaudit.m.auditBatchPass" /></button>	
						<button type="button" class="btn btn-primary btn-large a_size_1"><aebiz:showTitle titleId="productManagerList.m.goback" /></button>								
					</div>
			 	</form>
			</div>
			
		</div>
	</div>
	
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel"><aebiz:showTitle titleId="productManagerList.m.reasonlist" /></h4>
	      </div>
	      <div class="modal-body" id="reasonList">
					
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-primary" data-dismiss="modal"><aebiz:showTitle titleId="productManagerList.m.goback" /></button>
	      	<button type="button" class="btn btn-primary"   onclick="javascript:selectShow();"><aebiz:showTitle titleId="productManagerList.m.noPass" /></button>	
	      	<button type="button" class="btn btn-primary"   onclick="javascript:audit('${m.productMain.uuid}');"><aebiz:showTitle titleId="productManagerList.m.pass" /></button>	
	      </div>
	    </div>
	  </div>
	</div>
	
</body>

</html>
<script>
	function batchAudit(auditType,productUuid){
		
		$.getJSON("${pageContext.servletContext.contextPath}/productmainaudit/batchAudit",
								{"auditType" : auditType,"productUuid" : productUuid},
								function(data) {
									if (data.rsp) {
										bootbox.alert("<aebiz:showTitle titleId="productcategoryplatform.alert.success" />");
										location.reload();
									} else {
										bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.fail"/>');
									}
								});
	
	}
	
	function audit(productUuid){
		var auditField = $("#fieldName").val()
		$.getJSON("${pageContext.servletContext.contextPath}/productmainaudit/audit",
								{"auditField" : auditField,"productUuid" : productUuid},
								function(data) {
									if (data.rsp) {
										bootbox.alert("<aebiz:showTitle titleId="productcategoryplatform.alert.success" />");
										location.reload();
									} else {
										bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.fail"/>');
									}
								});
	
	}
	
	function selectShow(){
		var checkIds="";
		$("input[name='check']:checkbox").each(function() {
				if ($(this).is(":checked")) {
					checkIds += $(this).val() + ";";
				}
		})
		if(checkIds==""){
			bootbox.alert("请选择不通过的原因！");
			return;
		}
		$("#auditReason").val(checkIds);
		
		var dataParam = new Array();
		dataParam.push({"name" : "productUuid","value" : "${m.productMain.uuid}"});
		dataParam.push({"name" : "auditField","value" : $("#fieldName").val()});
		dataParam.push({"name" : "auditType","value" : $("#auditType").val()});
		dataParam.push({"name" : "auditReason","value" : $("#auditReason").val()});
		alert("${m.productMain.uuid}"+$("#fieldName").val()+ $("#auditType").val()+$("#auditReason").val());
		$.getJSON("${pageContext.servletContext.contextPath}/productmainaudit/save",
								{aoData : JSON.stringify(dataParam)},
								function(data) {
									if (data.rsp) {
										bootbox.alert("<aebiz:showTitle titleId="productcategoryplatform.alert.success" />");
										location.reload();
									} else {
										bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.fail"/>');
									}
								});
	}	
	//弹出框原因重新加载
	function getReasonList(fieldValue,auditType){
		$("#reasonList").load("${pageContext.servletContext.contextPath}/productmainaudit/toReason",{"productUuid":"${m.productMain.uuid}","auditField":fieldValue,"auditType":auditType,"ranNum":Math.random()});
	}
</script>