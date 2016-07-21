<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ViewImport.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>  
</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>订单查看</h1>
			</div>
			
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="ordermain.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="ordermain.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="ordermain.action.orderview"/></span>
				</li>
			</ul>
		</div>
		
		
		<div class="box box-color box-bordered blue y_orderbx">
			<div class="box-title">
				<h3>订单信息</h3>
				<div class="actions"><a class="btn btn-mini content-slideUp" href="#"><i class="fa fa-angle-down"></i></a></div>
			</div>
			<div class="box-content">
				<div class="order_step"><!--接单order_step_1、上门order_step_3、完成order_step_3-->
					<span <c:if test="${!empty m.addressModel.commitTime}">class="cur" </c:if>>
						<label>提交<br />订单</label><br />
						<c:if test="${!empty m.addressModel.commitTime}">
								${fn:substring(m.addressModel.commitTime,0, 10)} <br />${fn:substring(m.addressModel.commitTime,11, fn:length(m.addressModel.commitTime))} 
						</c:if>
					</span>
					<span class="Arrow">--></span>	
					<span <c:if test="${!empty m.addressModel.startTime}">class="cur" </c:if> >
						<label class="padd_top">抢单</label><br />
						<c:if test="${!empty m.addressModel.startTime}">
								${fn:substring(m.addressModel.startTime,0, 10)} <br />${fn:substring(m.addressModel.startTime,11, fn:length(m.addressModel.commitTime))} 
						</c:if>
					</span>
					<span class="Arrow">--></span>
					<span <c:if test="${!empty m.addressModel.affirmTime}">class="cur" </c:if> >
						<label>订单<br />已确认</label><br />
						<c:if test="${!empty m.addressModel.affirmTime}">
								${fn:substring(m.addressModel.affirmTime,0, 10)} <br />${fn:substring(m.addressModel.affirmTime,11, fn:length(m.addressModel.affirmTime))} 
						</c:if>
					</span>
					<span class="Arrow">--></span>
					<span <c:if test="${!empty m.addressModel.dropInTime}">class="cur" </c:if> >
						<label class="padd_top">上门</label><br />
						<c:if test="${!empty m.addressModel.dropInTime}">
								${fn:substring(m.addressModel.dropInTime,0, 10)} <br />${fn:substring(m.addressModel.dropInTime,11, fn:length(m.addressModel.commitTime))} 
						</c:if>
					</span>
					<span class="Arrow">--></span>
					<span <c:if test="${!empty m.addressModel.measureTime}">class="cur" </c:if>>
						<label  class="padd_top"  >丈量</label><br />
						<c:if test="${!empty m.addressModel.measureTime}">
								${fn:substring(m.addressModel.measureTime,0, 10)} <br />${fn:substring(m.addressModel.measureTime,11, fn:length(m.addressModel.measureTime))} 
						</c:if>
					</span>
					<span class="Arrow">--></span>
					<span <c:if test="${!empty m.addressModel.workTime}">class="cur" </c:if>  >
						<label  class="padd_top" >工作中</label><br />
						<c:if test="${!empty m.addressModel.workTime}">
								${fn:substring(m.addressModel.workTime,0, 10)} <br />${fn:substring(m.addressModel.workTime,11, fn:length(m.addressModel.workTime))} 
						</c:if>
					</span>
					<span class="Arrow">--></span>
					<span <c:if test="${!empty m.addressModel.completeTime}">class="cur" </c:if>>
						<label >工作<br />完成</label><br />
						<c:if test="${!empty m.addressModel.completeTime}">
								${fn:substring(m.addressModel.completeTime,0, 10)} <br />${fn:substring(m.addressModel.completeTime,11, fn:length(m.addressModel.commitTime))} 
						</c:if>
					</span>
					 <c:if test="${!empty m.addressModel.agentTime}">
						<span class="Arrow">--></span>
						<span <c:if test="${!empty m.addressModel.agentTime}">class="cur" </c:if> >
							<label class="padd_top" >付款</label><br />
							<c:if test="${!empty m.addressModel.agentTime}">
									${fn:substring(m.addressModel.agentTime,0, 10)} <br />${fn:substring(m.addressModel.agentTime,11, fn:length(m.addressModel.commitTime))} 
							</c:if>
						</span>
					 </c:if> 
				</div>
				<div class="table-responsive2 nopadding mb_20">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th width="15%">下单客户</th>
								<td width="20%">${m.customerName}</td>
								<th width="15">联系电话</th>
								<td width="20%">${m.customerMobile}</td>
								<th width="15%">备选联系人</th>
								<td width="20%">${m.addressModel.alternative}</td>
							</tr>
							<tr>
								<th>服务时间</th>
								<td>
									${m.addressModel.serviceTime}
								</td>
								<th>服务地址</th>
								<td>
									${m.addressModel.addressDetail}
								</td>
								<th>备选人电话</th>
								<td>
									${m.addressModel.alternativePhone}
								</td>
							</tr>
							<tr>
								<th rowspan="2">服务人员</th>
								<th colspan="5">
									<c:if test="{m.state=='3'}">
										时间已走过 <span class="font-red mr10" ><b id="show"></b></span>
									</c:if>
									订单需求${m.needNumbers}人，已抢${m.hasNumbers}人，尚缺${m.needNumbers-m.hasNumbers}人
								</th>
							</tr>
							<tr>
								<td colspan="5" class="ts_userList">
									<c:forEach items="${osm}" var="ostaff">
									<span>${ostaff.serviceStaffName}&nbsp;&nbsp;&nbsp;${ostaff.mobile}
										<c:choose>
											<c:when test="${ostaff.grade=='1'}">
												(领队)
												<a href="javascript:void(0);" onclick="mapview('${m.uuid}');" ><i class="glyphicon-google_maps"></i></a> 
												<c:choose>
													<c:when test="${fn:length(osm)>=1}">
														<c:if test="${m.state=='3'||m.state=='4'}">
															<a href="#" onclick="removeStaff('${m.uuid}','${ostaff.serviceStaffUuid}')"><i class="glyphicon-remove_2"></i></a>
														</c:if>
													</c:when>
													<c:otherwise>
														<c:if test="${m.state=='3'||m.state=='4'}">
																<a href="#" onclick="toChangeOrderStaff('${m.uuid}','${ostaff.serviceStaffUuid}')" data-toggle="modal" data-target="#modal-user"  title="更改人员" rel="tooltip"><i class="glyphicon-edit"></i></a>
														</c:if>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<c:if test="${(m.state=='3'||m.state=='4') && isExit !='true'}">
													<a href="#" onclick="toChangeOrderStaff('${m.uuid}','${ostaff.serviceStaffUuid}')" data-toggle="modal" data-target="#modal-user"  title="更改人员" rel="tooltip"><i class="glyphicon-edit"></i></a>
												</c:if>
											</c:otherwise>
										</c:choose>
									</span>
									</c:forEach>
									<c:if test="${m.urgencyStatus=='1'}">
												 <c:if test="${(m.needNumbers-m.hasNumbers)>0 &&isExit !='true'}">
												 		<a class="btn btn-primary"  onclick="toPushOrderStaff('${m.uuid}')" id="searchshow" title="订单推送" rel="tooltip" data-toggle="modal" data-target="#modal-push">订单推送</a>
												 </c:if>
									</c:if>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="table-responsive2 nopadding mb_20">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th width="15%">订单号</th>
								<td width="20%">${m.orderId}</td>
								<th width="15">商品类型</th>
								<td width="20%">家政商品</td>
								<th width="15%">订单来源</th>
								<td width="20%">
									<c:choose>
										<c:when test="${m.orderFromType=='0'}">
											手机订单
										</c:when>
										<c:otherwise>
											pc订单
										</c:otherwise>
									</c:choose>
							  </td>
							</tr>
							<tr>
								<th>下单时间</th>
								<td>${m.orderTime}</td>
								<th>订单状态</th>
								<td class="font-red">
									<c:if test="${m.state=='1'}">
									待支付
									</c:if>
									<c:if test="${m.state=='2'}">
										订单取消
									</c:if>
									<c:if test="${m.state=='3'}">
										
									<c:if test="${m.payType=='1'}" >
										待接单
									</c:if>
									<c:if test="${m.payType=='0'}" >
										<aebiz:showTitle titleId="ordermain.m.paid"/>
									</c:if>
										
									</c:if>
									<c:if test="${m.state=='4'}">
										已确认,待上门
									</c:if>
									<c:if test="${m.state=='5'}">
										上门中
									</c:if>
									<c:if test="${m.state=='6'}">
										上门完成
									</c:if>
									<c:if test="${m.state=='7'}">
										丈量
									</c:if>
									<c:if test="${m.state=='8'}">
										工作中
									</c:if>
									<c:if test="${m.state=='9'}">
										工作完成
									</c:if>
									<c:if test="${m.state=='10'}">
										等待客户确认
									</c:if>
									<c:if test="${m.state=='11'}">
										已完成
									</c:if>
									<c:if test="${m.state=='12'}">
										无效订单
									</c:if>
								</td>
								<th>付款方式</th>
								<td class="font-red">
									<c:choose>
										<c:when test="${m.payType=='0'}">
											 线上支付<br />
										</c:when>
										<c:otherwise>
											 线下支付<br />
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				

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
							<c:forEach items="${detailList}" var="orderDetail" varStatus="orderD">	
								<tr>
								<c:if test="${orderD.index+1=='1'}">
									<td rowspan="${fn:length(detailList)+1}" >${m.addressModel.cityName}</td>
								</c:if>
									<td class="y_left">
										<div class="y_produms">
											<p><a href="#">${orderDetail.productName}</a></p>
										</div>
									</td>
									<td>
										${orderDetail.buyNum}/
											<c:choose>
												<c:when test="${orderDetail.chargetype=='0'}">
													个
												</c:when>
												<c:otherwise>
													平米
												</c:otherwise>
											</c:choose>
									</td>
									<td><span class="font-red"><aebiz:showTitle titleId="pub.moneytype"/>${orderDetail.unitPrice}</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="fl pt_5">订单备注：${m.note}</div>
				<div class="text-right">
					<span class="y_plr20">
					<span class="y_plr20">
						 <aebiz:showTitle titleId="ordermain.m.totalMoney"/><b class="font-red">(¥${m.totalMoney})</b> -
						 积分兑换金额<b class="font-red">(¥${m.integralMoney})</b> - 
						 优惠券金额<b class="font-red">(¥${m.couponMoney})</b> - 
						 礼品卡金额<b class="font-red">(¥${m.giftcm})</b> -
						 账户余额<b class="font-red">(¥${m.balanceMoney})</b> 
						 	= <strong class="font-red tot_price">¥${m.needPayMoney}</strong>
					</span>
				</div>
			</div>
		<!--订单商品明细 结束-->
		<!--订单记录 开始-->
		<div class="box box-color lightgrey box-bordered">
			<div class="box-title">
				<h3>订单记录</h3>
				<div class="actions"><a class="btn btn-mini content-slideUp" href="#"><i class="fa fa-angle-down"></i></a></div>
			</div>
			<div class="box-content nopadding">
				<table class="table table-bordered table-hover table-product">
					<thead>
						<tr>
							<th width="18%"><aebiz:showTitle titleId="orderdetail.m.oper"/></th>
							<th width="21%"><aebiz:showTitle titleId="orderdetail.m.opertime"/></th>
							<th width="25%"><aebiz:showTitle titleId="orderdetail.m.operContent"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${LogList}" var="orderLog">	
						<tr class="warning">
							<td>${orderLog.orderOperName}</td>
							<td>${orderLog.orderOpeTime}</td>
							<td>${orderLog.note}</td>
						</tr>
						</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>
		<!--订单记录 结束-->
		
		<!--订单日志 start-->
		<div class="box box-color box-bordered lightgrey">
			<div class="box-title">
				<h3><aebiz:showTitle titleId="ordermain.submodel.orderlog"/></h3>
				<div class="actions"><a class="btn btn-mini content-slideUp" href="#"><i class="fa fa-angle-down"></i></a></div>
			</div>
			<div class="box-content nopadding">
				<table class="table table-bordered table-hover table-product">
					<thead>
						<tr>
							<th width="15%"><aebiz:showTitle titleId="orderdetail.m.oper"/></th>
							<th width="15%"><aebiz:showTitle titleId="orderdetail.m.operType"/></th>
							<th width="15%">操作</th>
							<th width="15%">订单状态</th>
							<th width="15%"><aebiz:showTitle titleId="orderdetail.m.opertime"/></th>
							<th width="25%"><aebiz:showTitle titleId="orderdetail.m.operContent"/></th>	
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderLogList}" var="orderLog">	
							<tr class="warning">
								<td>${orderLog.orderOperName}</td>
								<td>
									<c:if test="${orderLog.orderOperType=='1'}">
										平台客服
									</c:if>
									<c:if test="${orderLog.orderOperType=='2'}">
										家政员
									</c:if>
									<c:if test="${orderLog.orderOperType=='3'}">
										会员
									</c:if>
								</td>
								
								<td>
									<c:if test="${orderLog.operateType=='1'}">
										提交订单
									</c:if>
									<c:if test="${orderLog.operateType=='2'}">
										取消订单
									</c:if>
									<c:if test="${orderLog.operateType=='3'}">
										支付完成
									</c:if>
									<c:if test="${orderLog.operateType=='4'}">
										接单完成
									</c:if>
									<c:if test="${orderLog.operateType=='5'}">
										上门 
									</c:if>
									<c:if test="${orderLog.operateType=='6'}">
										已到达  
									</c:if>
									<c:if test="${orderLog.operateType=='7'}">
										家政员丈量
									</c:if>
									<c:if test="${orderLog.operateType=='8'}">
										开始工作
									</c:if>
									<c:if test="${orderLog.operateType=='9'}">
										工作完成
									</c:if>
									<c:if test="${orderLog.operateType=='10'}">
										家政员代付完成
									</c:if>
									<c:if test="${orderLog.operateType=='11'}">
										会员确认完成   
									</c:if>
									<c:if test="${orderLog.operateType=='12'}">
										无效订单
									</c:if>
									<c:if test="${orderLog.operateType=='13'}">
										客服添加订单记录 
									</c:if>
									<c:if test="${orderLog.operateType=='14'}">
										客服推送订单 
									</c:if>
									<c:if test="${orderLog.operateType=='15'}">
										客服删除队长
									</c:if>
									<c:if test="${orderLog.operateType=='16'}">
										客服下单 
									</c:if>
									<c:if test="${orderLog.operateType=='17'}">
										申请退款
									</c:if>
									<c:if test="${orderLog.operateType=='18'}">
										审核退款  
									</c:if>
									<c:if test="${orderLog.operateType=='19'}">
										退款 
									</c:if>
									<c:if test="${orderLog.operateType=='20'}">
										订单互评
									</c:if>
									<c:if test="${orderLog.operateType=='21'}">
										会员评价
									</c:if>
									<c:if test="${orderLog.operateType=='22'}">
										添加投诉
									</c:if>
								</td>
								
								<td>
									<c:if test="${orderLog.orderState=='1'}">
									待支付
									</c:if>
									<c:if test="${orderLog.orderState=='2'}">
										订单取消
									</c:if>
									<c:if test="${orderLog.orderState=='3'}">
										
									<c:if test="${orderLog.payType=='1'}" >
										待接单
									</c:if>
									<c:if test="${orderLog.payType=='0'}" >
										<aebiz:showTitle titleId="ordermain.m.paid"/>
									</c:if>
										
									</c:if>
									<c:if test="${orderLog.orderState=='4'}">
										已确认,待上门
									</c:if>
									<c:if test="${orderLog.orderState=='5'}">
										上门中
									</c:if>
									<c:if test="${orderLog.orderState=='6'}">
										上门完成
									</c:if>
									<c:if test="${orderLog.orderState=='7'}">
										丈量
									</c:if>
									<c:if test="${orderLog.orderState=='8'}">
										工作中
									</c:if>
									<c:if test="${orderLog.orderState=='9'}">
										工作完成
									</c:if>
									<c:if test="${orderLog.orderState=='10'}">
										等待客户确认
									</c:if>
									<c:if test="${orderLog.orderState=='11'}">
										已完成
									</c:if>
									<c:if test="${orderLog.orderState=='12'}">
										无效订单
									</c:if>
									<c:if test="${orderLog.orderState=='13'}">
										申请退款
									</c:if>
									<c:if test="${orderLog.orderState=='14'}">
										退款成功
									</c:if>
									<c:if test="${orderLog.orderState=='15'}">
										退款失败
									</c:if>
								</td>
								<td>${orderLog.orderOpeTime}</td>
								<td>${orderLog.note}</td>
							</tr>																			
						</c:forEach>				
					</tbody>
				</table>
			</div>
		</div>
		<!--订单日志 end-->
		<div class="y_fixbtnbox">
			<div class="y_fixedbtn">		
				<button class="btn btn-primary btn-large a_size_1" title="添加记录" rel="tooltip" data-toggle="modal" data-target="#Remarks">添加记录</button>
				<button type="submit" class="btn btn-primary btn-large a_size_1">订单打印</button>
				
				<c:if test="${m.state=='1' ||m.state=='3'||m.state=='4'||m.state=='5'||m.state=='6' }">
					<c:if test="${empty m.refoundState }">
					<button type="submit" class="btn btn-primary btn-large a_size_1"  data-toggle="modal" data-target="#RemarkRefoud">取消订单</button>
					</c:if>
				</c:if>
	
				<c:if test="${m.state=='11'}">
					<c:if test="${!empty m.orderComplain && m.orderComplain =='0'}">
							<button type="submit" onclick="toUpdateOrderStaff('${m.uuid}')" class="btn btn-primary btn-large a_size_1" rel="tooltip" data-toggle="modal" data-target="#chuli_tousu">处理投诉</button>
					</c:if>
					<c:if test="${empty m.orderComplain}">
							<button type="submit"  class="btn btn-primary btn-large a_size_1" title="接投诉" rel="tooltip" data-toggle="modal" data-target="#tousu">接投诉</button>
					</c:if>
				</c:if>
				
				<button type="button" id="returenId" class="btn btn-default btn-large a_size_1">返回</button>
			</div>
		</div>
	</div>
<!--推送人员-->
<div id="modal-push" class="modal fade">
	<div id="pushOrderStaff"></div>
</div>
<!--推送人员 end-->

<!--更改人员-->
<div id="modal-user" class="modal fade">
	<div id="showChange"></div>
</div>
<!--更改人员 end-->	

<!--添加订单备注-->
<input type="hidden" name="orderMainUuid" id="orderMainUuid" value="${m.uuid}">
<div id="Remarks" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="user-infos">添加订单记录</h3>
			</div>
			<div class="modal-body">
				<div class="box">	
					<textarea name="note" id="note1" class="form-control" rows="5"></textarea>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn moresearch" data-dismiss="modal">关闭</button>
				<button onclick="javascript:addLog('${m.uuid}');" class="btn btn-primary moresearch" data-dismiss="modal">提交</button>
			</div>
		</div>
	</div>
</div>
<!--添加订单备注 end-->

<!--取消订单备注 end-->
<div id="RemarkRefoud" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="user-infos">取消原因</h3>
			</div>
			<div class="modal-body">
				<div class="box">	
					<textarea name="note" id="note" class="form-control" rows="5"></textarea>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn moresearch" data-dismiss="modal">关闭</button>
				<button onclick="javascript:orderRefound('${m.uuid}','${m.payType}');" class="btn btn-primary" data-dismiss="modal">提交</button>
			</div>
		</div>
	</div>
</div>
<!--取消订单备注 end-->


<!--添加投诉记录-->
<div id="tousu" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="user-infos">添加投诉记录</h3>
			</div>
			<div class="modal-body">
				<div class="box">	
					<textarea name="content" id="content" class="form-control" rows="5"></textarea>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn moresearch" data-dismiss="modal">关闭</button>
				<button class="btn btn-primary moresearch" data-dismiss="modal" onclick="javascript:addComplain('${m.uuid}','${m.customerUuid}');" >提交</button>
			</div>
		</div>
	</div>
</div>
<!--添加投诉记录 end-->
<!--投诉处理-->
<div id="chuli_tousu" class="modal fade">
	<div id="showTousu"></div>
</div>
<!--投诉处理 end-->
<script>
  $(document).ready(function() {
  	var returnType="${returnType}";
		$("#returenId").click(function(){
			if(returnType ==null || returnType==""){
				history.go(-1) ;
			}else if(returnType=="servicemanagelist"){
				location.href="${pageContext.servletContext.contextPath}/sysback/order/servicemanagelist/1/1/20?init=true";
			}else if(returnType=="list"){
				location.href="${pageContext.servletContext.contextPath}/sysback/order/list/0/1/20?init=true";
			}else if(returnType=="mainlist"){
				location.href="${pageContext.servletContext.contextPath}/sysback/order/list/1/20?init=true";
			}
		});
  });
    	
	//订单添加记录
function addLog(uuid) {
	var note = $("#note1").val();
	//重置密码
	$.get("${pageContext.servletContext.contextPath}/sysback/order/addLog",
	{
		"uuid":uuid,
		"note":note,
		"operateType":"13",
		ranNum : Math.random()
	},
	function(data) {
		if (data == "success") {
			//刷新 当前页面
			location.reload();
		} else {
			bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
		}
	});
}

var orderTime="${m.orderTime}";
orderTime=orderTime.replace(/-/g,"/");
var now = new Date(orderTime);
var timeShow = function(){
    var t = new Date();
    t.setFullYear(t.getFullYear()-now.getFullYear());
    t.setMonth(t.getMonth()-now.getMonth());
    t.setDate(t.getDate()-now.getDate());
    t.setHours(t.getHours()-now.getHours());
    t.setMinutes(t.getMinutes()-now.getMinutes());
    t.setSeconds(t.getSeconds()-now.getSeconds());
    if(t.getDate()>=31){
    	showhtml = t.getHours() + '小时' + t.getMinutes()+ '分钟' + t.getSeconds()+ '秒';
    }else{
    	showhtml = t.getDate() +'天' +t.getHours() + '小时' + t.getMinutes()+ '分钟' + t.getSeconds()+ '秒';
    }
    $("#show").html(showhtml);
}
timeShow();
setInterval(timeShow,1000);

function addComplain(uuid,customerUuid) {
	var content = $("#content").val();
	$.get("${pageContext.servletContext.contextPath}/sysback/order/addComplain",
	{
		"uuid":uuid,
		"customerUuid":customerUuid,
		"content":content,
		"updateStype":"0",
		ranNum : Math.random()
	},
	function(data) {
		if (data == "success") {
			//刷新
			location.reload();
		} else {
			bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
		}
	});
}

function toUpdateOrderStaff(uuid){
	var url="${pageContext.servletContext.contextPath}/sysback/order/toUpdateStaff/"+uuid;
	$.post(url,
	{
		ranNum : Math.random()
	},
	function(data){
			$("#showTousu").html(data);
	}
	);
}
//订单推送家政员
function toPushOrderStaff(uuid){
	var url="${pageContext.servletContext.contextPath}/sysback/order/toPushStaff/"+uuid;
		$.post(url,
		{
			ranNum : Math.random()
		},
		function(data){
				$("#pushOrderStaff").html(data);
		}
	);
}

//订单修改家政员
function toChangeOrderStaff(uuid,osUuid){
	var url="${pageContext.servletContext.contextPath}/sysback/order/toChangeStaff/"+uuid;
	$.post(url,
	{
		"osUuid":osUuid,
		ranNum : Math.random()
	},
	function(data){
			$("#showChange").html(data);
	}
	);
}


$(document).ready(function() {
		isExit();
	
});

var dd="${isExit}";
var username="${name}";
function isExit(){
	
	if(dd=="true"){
		alert("该订单已被客服"+username+"处理了.");
	}
	
}


//客服更改家政员
function bgChangePush(uuid,staffuuids,osUuid){
		var checkIds="";
			if(staffuuids.trim() != "") {
				checkIds = staffuuids ;
			}else{
				$("input[name='check']:checkbox").each(function(){				
	        if($(this).is(":checked")){        	
	            checkIds += $(this).val()+"," ;  
	        }
	    	})	
			}
			
			if(checkIds.trim() == "") {
				//提示为空
				bootbox.alert("请选择推送的家政员") ;
				return ;
			} 
		
		var url="${pageContext.servletContext.contextPath}/app/customer/order/appOrderAction/bgPush";
		$.post(url,
			{
				"orderId":uuid,
				"osUuid":osUuid,
				"selectOne":checkIds,
				ranNum : Math.random()
			},
			function(data){
				//alert(data);
					if(data=="push_success"){
						bootbox.alert("订单推送成功，请等待家政员接单");
						location.reload();
					}else{
						bootbox.alert("订单推送失败，请重新推送订单");
					}
			}
		);
	
}


//获得
function getCheckBox(needNum,uuid){
	var cl = $("input[name='check']:checked").length;
	if(cl>needNum){
		$("#"+uuid).attr("checked",false)
		alert("该订单只需要选择"+needNum+"人");
	}
}

//客服订单推送
function bgToPush(uuid,staffuuids,osUuid){
		var checkIds="";
			if(staffuuids.trim() != "") {
				checkIds = staffuuids ;
			}else{
				$("input[name='check']:checkbox").each(function(){				
	        if($(this).is(":checked")){        	
	            checkIds += $(this).val()+"," ;  
	        }
	    	})	
			}
			
			if(checkIds.trim() == "") {
				//提示为空
				bootbox.alert("请选择推送的家政员") ;
				return ;
			} 
		
		var url="${pageContext.servletContext.contextPath}/app/customer/order/appOrderAction/bgServicePush";
		$.post(url,
			{
				"orderId":uuid,
				"osUuid":osUuid,
				"selectOne":checkIds,
				ranNum : Math.random()
			},
			function(data){
					if(data=="push_success"){
						bootbox.alert("订单推送成功");
						location.reload();
					}else if(data=="1011"){
						bootbox.alert("由于家政员未登录,订单推送失败，请选择重新推送订单");
					}else{
						bootbox.alert("订单推送失败，请重新推送订单");
					}
			}
		);
	
}

//删除队长
function removeStaff(orderId,osUuid){
		var url="${pageContext.servletContext.contextPath}/sysback/order/deleteOrderStaff";
		$.post(url,
			{
				"orderId":orderId,
				"osUuid":osUuid,
				ranNum : Math.random()
			},
			function(data){
				if(data=="success"){
					location.reload();
					alert("删除成功！");
				}
			}
		);
}
//取消订单
function orderRefound(orderUuid,payType){
	
		var note = $("#note").val();
		if(note=="" ||note==''){
			alert("请填写取消原因！");
			return;
		}
		var url="${pageContext.servletContext.contextPath}/sysback/order/orderRefound";
		$.post(url,
			{ 
				"note":note,
				"orderId":orderUuid,
				"payType":payType,
				ranNum : Math.random()
			},
			function(data){
				if(data=="success"){
					location.reload();
				}
			}
		);
}


</script>
</body>
</html>


