<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<!-- 日期插件 -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.dataTable.js"></script>
</head>
<body>

<div class="container-fluid">
	<div class="page-header">
		<div class="pull-left">
			<h1>退款管理</h1>
		</div>
	</div>
	<div class="breadcrumbs">
		<ul>
			<li>
				<span><aebiz:showTitle titleId="orderrefund.menuOne"/></span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span><aebiz:showTitle titleId="orderrefund.menuTwo2"/></span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span><aebiz:showTitle titleId="orderrefund.menuThree"/></span>							
			</li>
		</ul>
	</div>
	<ul class="tabs tabs-inline tabs-top border1-top">
		
		<li  <c:if test="${qm.refundSerachtype=='0'}"> class='active' </c:if>>
			<a href="javascript:window.location.href='${pageContext.servletContext.contextPath}/sysback/order/refundlist/0/1/20'">应退订单</a>
		</li>
		<li  <c:if test="${qm.refundSerachtype=='1'}"> class='active' </c:if> >
			<a href="javascript:window.location.href='${pageContext.servletContext.contextPath}/sysback/order/refundlist/1/1/20'" >已退订单</a>
		</li>
	</ul>			
	<div class="tab-content y_tabdatable">
		<div class="tab-pane active" id="Product1">
		<!--订单搜索-->
			<form class="form-inline2" id="searchForm" action="${pageContext.servletContext.contextPath}/sysback/order/queryrefund/" method="post">
				<input type="hidden" name="pageShow" value="${wm.pageShow}">
				<input type="hidden" name="refundSerachtype" value="${qm.refundSerachtype}">
				<div class="y_clear">
		<div class="form-inline table_formnew" style="width:98%;margin:0 auto;float:none">
			<div class="row">
				<div class="col-sm-3 nopadding">
					<div class="form-group">	
						<label class="control-label"><aebiz:showTitle titleId="ordermain.m.orderId"/></label>
							<input type="text" name="orderId" id="orderId" class="form-control" value="${qm.orderId}">
							<input type="hidden" name="orderId_q" id="orderId_q" class="form-control" value="Like" >
					</div>
				</div>
				
	
				<!--<div class="col-sm-3 nopadding" >
					<div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="ordermain.m.startTime"/>：</label>
						<input type="text" name="opeTime" id="opeTime__more" class="form-control datepick" value="${qm.opeTime}" >
						<input type="hidden" name="opeTime_q" id="opeTime_q" class="form-control" value="GE">											
					</div>											
				</div>
				<div class="col-sm-3 nopadding" >
					<div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="ordermain.m.endTime"/>：</label>
						<input type="text" name="opeTime2" id="opeTime2__more" class="form-control datepick"  >
						<input type="hidden" name="opeTime2_q" id="opeTime2_q" class="form-control" value="LT">		
					</div>
				</div>-->
				
				<div class="col-sm-3 nopadding" >
					<div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="ordermain.m.startTime"/>：</label>
						<input type="text" name="orderTime" id="orderTime__more" class="form-control datepick" value="${qm.orderTime}" >
						<input type="hidden" name="orderTime_q" id="orderTime_q" class="form-control" value="GE">											
					</div>											
				</div>
				<div class="col-sm-3 nopadding" >
					<div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="ordermain.m.endTime"/>：</label>
						<input type="text" name="orderTime2" id="orderTime2__more" class="form-control datepick" value="${qm.orderTime2}" >
						<input type="hidden" name="orderTime2_q" id="orderTime2_q" class="form-control" value="LT">		
				</div>
			</div>
				
				
			</div>
			
			
			
			<div class="row">
				<div class="col-sm-3 nopadding">
					<div class="form-group">	
						<label class="control-label"><aebiz:showTitle titleId="ordermain.m.customerName"/>：</label>
						<input type="text" name="customerName" id="customerName" class="form-control" value="${qm.customerName}">
						<input type="hidden" name="customerName_q" id="customerName_q" value="Like"/>
					</div>
				</div>
				<div class="col-sm-3 nopadding">
					<div class="form-group">	
						<label class="control-label"><aebiz:showTitle titleId="ordermain.m.mobile"/>：</label>
						<input type="text" name="mobile" id="mobile" class="form-control " value="${qm.mobile}">
					</div>
				</div>
				<div class="col-sm-3 nopadding">
					<div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="ordermain.m.serviceTime"/>：</label>
						<input type="text" name="serviceTime" id="serviceTime" class="form-control datepick" value="${qm.serviceTime}">

					</div>
				</div>
				<div class="col-sm-3 nopadding">
					<div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="ordermain.m.alternativeName"/>：</label>
						<input type="text" name="alternative" id="alternative" class="form-control" value="${qm.alternative}">

					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3 nopadding">
					<div class="form-group">	
						<label class="control-label"><aebiz:showTitle titleId="ordermain.m.alternativeMobile"/>：</label>
						<input type="text" name="alternativePhone" id="alternativePhone" class="form-control" value="${qm.alternativePhone}">
					</div>
				</div>
				<div class="col-sm-3 nopadding">
					<div class="form-group">	
						<label class="control-label"><aebiz:showTitle titleId="ordermain.m.serviceName"/>：</label>
						<input type="text" name="serviceStaffName" id="serviceStaffName" class="form-control" value="${qm.serviceStaffName}">
					</div>
				</div>
				<div class="col-sm-3 nopadding">
					<div class="form-group">	
						<label class="control-label"><aebiz:showTitle titleId="ordermain.m.serviceMobile"/>：</label>
						<input type="text" name="serviceStaffMobile" id="serviceStaffMobile" class="form-control" value="${qm.serviceStaffMobile}">
					</div>
				</div>
				
				
				<!--备注-->
				<div class="col-sm-3 nopadding">
					<div class="form-group">	
						<label class="control-label">备注：</label>
						<input type="text" name="note" id="note" class="form-control" value="${qm.note}">
						<input type="hidden" name="note_q" id="note_q" value="Like"/>
					</div>
				</div>
				
				
					<!--地区-->
				<!--<div class="col-sm-9 nopadding">
					<div class="form-group col-sm-12">	
						<label class="control-label fl pt_5"><aebiz:showTitle titleId="ordermain.m.area"/></label>									
						<aebiz:area checkProvince="${qm.province}" checkCity="${qm.city}" checkRegion="${qm.region}"></aebiz:area>	
					</div>
				</div>-->
				
					<!--地区-->
				<div class="col-sm-9 nopadding">
					<div class="form-group col-sm-12">	
						<label class="control-label fl pt_5"><aebiz:showTitle titleId="ordermain.m.area"/></label>									
						<!--<aebiz:area checkProvince="${qm.province}" checkCity="${qm.city}" checkRegion="${qm.region}"></aebiz:area>-->	
						<select  name='city' id='cityId' class='form-control'>
								<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
								<c:forEach items="${city}" var="city">
										<option value="${city.uuid}">${city.cityName}</option>
								</c:forEach>										
						</select>					
					</div>
				</div>
				<!--地区  end-->
				
				
				
			</div>
			<div class="row">
				<div class="form-group">
					<a class="btn btn-primary search"  onclick="submitForm()" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip" type="submit" ><aebiz:showTitle titleId="basebusiness.showmessage.query"/></a>
					<a class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></a>
				<!--	<a class="btn" onclick="javascript:void(0);" title="导出所有" rel="tooltip">导出</a>		-->
			</div>
		</div>
	</div>
	</form>
	
			<div class="tab-content y_tabdatable">
				<table class="y_storeodlist">
					<thead>
						<tr>
							<th><aebiz:showTitle titleId="order.ucenter.title.ordermessage"/></th>
							<th width="18%"><aebiz:showTitle titleId="ordermain.m.orderMoney"/></th>
							<th width="14%"><aebiz:showTitle titleId="ordermain.m.refoundState"/></th>
							<th width="27%"><aebiz:showTitle titleId="ordermain.m.refoundMessage"/></th>
							<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${wm.rows}" var="orderMain">
							<input type="hidden" value="${orderMain.orderTime}" id="${orderMain.uuid}"/>
							<tr>
								<th colspan="5">
									<aebiz:showTitle titleId="ordermain.m.orderTime"/>:${orderMain.orderTime}<span class="y_line">|</span><aebiz:showTitle titleId="ordermain.m.orderId"/>${orderMain.orderId}<span class="y_line">|</span><aebiz:showTitle titleId="ordermain.m.customerName"/>:${orderMain.addressModel.name}
									<span class="y_line">|</span><aebiz:showTitle titleId="ordermain.m.mobile"/>:${orderMain.addressModel.mobile}
									<span class="y_line">|</span><aebiz:showTitle titleId="ordermain.m.city"/>:<aebiz:showTitle titleId="ordermain.m.are1"/>
									<span class="y_line">|</span>退款金额:<span style="color:red;font-size:120%;">${orderMain.refundMoney}元</span>
								</th>	
							</tr>
							<tr>
								<td>
									<img class="y_imgbx" src="${pageContext.servletContext.contextPath}/static/sysback/img/product.png">
									<div class="y_prdms">
										<b class="text-primary"><aebiz:showTitle titleId="ordermain.m.housekeeping"/></b><br />
										<aebiz:showTitle titleId="ordermain.m.serviceTime"/>:${orderMain.addressModel.serviceTime}<br />
										<aebiz:showTitle titleId="ordermain.m.regionName"/>:${orderMain.addressModel.addressDetail}
									</div>
								</td>	
								<td>
									<span class="y_orderprc"><aebiz:showTitle titleId="ordermain.m.CNY"/>${orderMain.totalMoney}</span>
									<c:choose>
										<c:when test="${orderMain.payType=='0'}">
											 <span > 
											 	<img  src="${pageContext.servletContext.contextPath}/static/sysback/img/up.png">
											 	支付
											 </span><br />
										</c:when>
										<c:otherwise>
											<span><img  src="${pageContext.servletContext.contextPath}/static/sysback/img/down.png"> 支付</span><br />
										</c:otherwise>
									</c:choose>
									<span class="y_waporder"><i class="fa fa-mobile-phone"></i>
										<c:choose>
											<c:when test="${orderMain.orderFromType=='1'}">
												pc订单
											</c:when>
											<c:otherwise>
													手机订单
											</c:otherwise>
										</c:choose>
									
									</span>
								</td>
								<td class="text-danger">
									
									<b>
										<c:if test="${orderMain.refoundState=='0'}">
											<aebiz:showTitle titleId="ordermain.m.refoundState0"/>
										</c:if>
										<c:if test="${orderMain.refoundState=='1'}">
											<aebiz:showTitle titleId="ordermain.m.refoundState1"/>
										</c:if>
										<c:if test="${orderMain.refoundState=='2'}">
											<aebiz:showTitle titleId="ordermain.m.refoundState2"/>
										</c:if>
									</b>
								</td>
								<td class="text-danger">
									<div class="y_prdms">
										申请人类型:
										<c:if test="${orderMain.logmodel.orderOperType=='1'}">
											客服
										</c:if>
										<c:if test="${orderMain.logmodel.orderOperType=='2'}">
											家政员
										</c:if>
										<c:if test="${orderMain.logmodel.orderOperType=='3'}">
											会员
										</c:if><br />
										申请人:${orderMain.logmodel.orderOperName}<br />
										申请时间:${orderMain.refundApplyTime}
									</div>
								</td>
								<td class="y_odbtnbx">
									<a href="${pageContext.servletContext.contextPath}/sysback/order/view/${orderMain.uuid}" class='btn' rel='tooltip' title='<aebiz:showTitle titleId="ordermain.m.lookOver"/>'><aebiz:showTitle titleId="ordermain.m.lookOver"/></a>
								</td>
							</tr>
							
						</c:forEach>
					</tbody>
				</table>
			</div>
			<aebiz:storePage listPath="sysback/order/refundlist/${qm.refundSerachtype}" />					
		</div>

</body>
</html>
<script>
	

function clearSearch(){
		$("#orderId").val("");
			$("#customerName").val("");
			$("#state").val("");
			$("#opeTime__more").val("");
			$("#opeTime2__more").val("");
			$("#mobile").val("");
			$("#provinceId").val("");
			$("#cityId").val("");
			$("#regionId").val("");
			$("#serviceTime").val("");
			$("#alternative").val("");
			$("#alternativePhone").val("");
			$("#serviceStaffName").val("");
			$("#serviceStaffMobile").val("");
			$("#note").val("");
			
			$("#orderTime__more").val("");
			$("#orderTime2__more").val("");
	}
	
	function submitForm(){
		var province=$("#province").val();
		var city=$("#city").val();
		if(province=='true'&&city=="false"){
			alert("查询地区至少选择第二级查询");
		}else{
			$("#searchForm").submit();
		}
	}
</script>