<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>

</head>

<body>

		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="user-infos">退款审核</h3>
			</div>
			<div class="modal-body">
				<div class="box">	
					<div class="box-content nopadding">									
						<table class="table table-bordered mb_10">
							<tbody>
								<tr>
									<th width="12%">订单号</th>
									<td width="21%">${orderMain.orderId}</td>
									<th width="12%">状态</th>
									<td width="21%" class="font-red">
										<c:if test="${orderMain.state=='1'}">
											待支付
										</c:if>
										<c:if test="${orderMain.state=='2'}">
											订单取消
										</c:if>
										<c:if test="${orderMain.state=='3'}">
											已支付
										</c:if>
										<c:if test="${orderMain.state=='4'}">
											已确认,待上门
										</c:if>
										<c:if test="${orderMain.state=='5'}">
											上门中
										</c:if>
										<c:if test="${orderMain.state=='6'}">
											上门完成
										</c:if>
										<c:if test="${orderMain.state=='7'}">
											丈量
										</c:if>
										<c:if test="${orderMain.state=='8'}">
											工作中
										</c:if>
										<c:if test="${orderMain.state=='9'}">
											工作完成
										</c:if>
										<c:if test="${orderMain.state=='10'}">
											等待客户确认
										</c:if>
										<c:if test="${orderMain.state=='11'}">
											已完成
										</c:if>
										<c:if test="${orderMain.state=='12'}">
											无效订单
										</c:if>
									</td>
									<th width="12%">退款原因</th>
									<td width="21%" class="font-red">${orderMain.refundReson}</td>
								</tr>
								<tr>
									<th>申请人</th>
									<td>${orderMain.customerName}</td>
									<th>申请日期</th>
									<td>${orderMain.refundApplyTime}</td>
									<th>退款金额</th>
									<td class="font-red" style="font-size:150%;">${orderMain.refundMoney}元</td>
								</tr>
								<tr>
									<th>订购人</th>
									<td>${orderMain.customerName}</td>
									<th>订购人电话</th>
									<td>${m.mobile}</td>
									<th>订单来源</th>
									<td>app</td>
								</tr>
								<tr>
									<th>备选人</th>
									<td>${m.alternative}</td>
									<th>备选人电话</th>
									<td colspan="3">${m.alternativePhone}</td>
								</tr>
								<tr>	
									<th>服务时间</th>
									<td>${m.serviceTime}</td>
									<th>服务地点</th>
									<td colspan="3">${m.addressDetail}</td>
								</tr>
							</tbody>
						</table>
						
						<!--订单商品明细 开始-->
				    <div class="y_orderbox">
							<h4 class="y_qdh4"><aebiz:showTitle titleId="ordermain.submodel.detail"/></h4>
							<div class="table-responsive2 nopadding mb_10">
								<table class="table table-bordered table-hover table-striped table-product">
									<thead>
										<tr>
											<th width="15%">城市</th>
											<th width="45%"><aebiz:showTitle titleId="orderdetail.m.productName"/></th>
											<th width="10%">平米/个数</th>
											<th width="10%"><aebiz:showTitle titleId="orderdetail.m.finalprice"/></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${t}" var="orderDetail" varStatus="orderD">	
											<tr>
											<c:if test="${orderD.index+1=='1'}">
												<td rowspan="${fn:length(t)+1}" >${orderMain.addressModel.cityName}</td>
											</c:if>
												<td class="y_left">
													<div class="y_produms">
														<p>${orderDetail.productName}</p>
													</div>
												</td>
												<td>
													${orderDetail.buyNum}/
														<c:choose>
															<c:when test="${orderDetail.chargetype=='0'}">
																平米
															</c:when>
															<c:otherwise>
																个
															</c:otherwise>
														</c:choose>
												</td>
												<td><span class="font-red"><aebiz:showTitle titleId="pub.moneytype"/>${orderDetail.unitPrice}</span></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="text-right">
								<span class="y_plr20">
								<span class="y_plr20">
									 <aebiz:showTitle titleId="ordermain.m.totalMoney"/><b class="font-red">(¥${orderMain.totalMoney})</b> -
									 积分兑换金额<b class="font-red">(¥${orderMain.integralMoney})</b> - 
									 优惠券金额<b class="font-red">(¥${orderMain.couponMoney})</b> - 
									 礼品卡金额<b class="font-red">(¥${orderMain.giftcm})</b> -
									 账户余额<b class="font-red">(¥${orderMain.balanceMoney})</b> 
									 		= <b class="font-red">¥${orderMain.needPayMoney}</b>
								</span>
							</div>
						</div>
					     </div>
					  </div>
						<!--订单商品明细 结束-->
						<div class="row">	
								<div class="col-sm-12">
									<div class="form-group">
										<label for="textfield" class="control-label fl pt_5">审核意见：</label>
										<div class="col-sm-8">
										
											<input type="text" name="refundAuditOpinion" id="refundAuditOpinion" class="form-control"  rows="5"  >								
										</div>
									</div>
								</div>
						</div>
					</div>
					<div class="modal-footer">
						<a class="btn btn-primary " data-dismiss="modal" onclick="javascript:agree('${orderMain.uuid}');" >同意用户申请</a>
						<a class="btn btn-primary " data-dismiss="modal" onclick="javascript:refuse('${orderMain.uuid}');" >拒绝用户申请</a>
						<button class="btn moresearch" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>


</body>

</html>
<script>
	


	
</script>

