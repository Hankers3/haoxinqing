<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>家政员 -个人中心</title>
  <meta name="description" content="aebiz b2b2c index">
  <!--[if IE]> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <![endif]-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/import/ListImport.jsp"%>	
	<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/import/ListImportJs.jsp"%>	
</head>
<body>
	<!-- 家政员-个人中心top-->
	<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterHead.jsp"%>
	<!-- 家政员-个人中心top end-->
	
	<!-- 家政员-个人中心主体 -->
	<div class="container member_main">
		<div class="row ">
			
			<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterLeft.jsp"%>

			<!-- 家政员-个人中心主体右侧 -->
			<div class="col-mi-9">
        <div class="m_center clearfix">
        	<div class="fl m_center_image">
        		<div class="m_center_pic">
        		  <a href="##"><img src="${pageContext.servletContext.contextPath}/static/usercenter/img/m_zx.jpg" class="img-circle"></a>
        		</div>
        		<p>${servicestaffModel.mobile}</p>
        	</div>
        	<div class="fl m_center_right">
        		<div class="m_post">
        			岗位：<label>保洁员</label><span>|</span>岗位：<label>高级</label><span>|</span>服务次数：<label>${serveNumber}次</label><span>|</span>评价：<b class="m_b1"><b class="m_b2" style="width:80%"></b></b><label>（60条）</label>
        		</div>
        		<div class="m_center_tit">
        			<a href="${pageContext.servletContext.contextPath}/servicestaff/order/list/4/1/10?init=true" target="_Blank">待上门 <label></label></a>
        			<a href="${pageContext.servletContext.contextPath}/servicestaff/order/list/11/1/10?init=true" target="_Blank">待评价 <label></label></a>
        			<a href="${pageContext.servletContext.contextPath}/servicestaff/orderrouting/getServicestaffIncome/1/10?init=true"><i class="fa fa-credit-card"></i>我的收入<i class="fa fa-angle-down"></i></a>
        		</div>
        	</div>
        </div>
        <!-- 家政员-个人中心主体右侧订单列表 -->
        <div class="m_list">
        	<div class="m_list_tit"><span class="fl">最新订单</span><a href="${pageContext.servletContext.contextPath}/servicestaff/order/list/0/1/10?init=true" class="fr">查看更多 ></a></div>
        	<ul class="m_table">
        	  <li style="width:20%">服务类型</li>
        	  <li style="width:14%">服务时间</li>
        	  <li style="width:25%">订单信息</li>
        	  <li style="width:13%">订单金额</li>
        	  <li style="width:13%">订单状态</li>
        	</ul>
        <c:forEach items="${order}" var="order">
        <table class="m_list_table">
        		<tr>
        			<th colspan="6" style="padding:17px 10px;">
        				<label class='inline' for="c1">下单时间：${order.orderTime}&nbsp;&nbsp;订单号：${order.orderId}</label>
        			</th>
        		</tr>
        		<tr>
        			<td width="20%">
        				<div class="fl m_list_table_pic"><img src="${pageContext.request.contextPath }/static/usercenter/img/m_hb.jpg"></div>
        				<div class="fl m_list_table_tit">家庭保洁</div>
        			</td>
        			<td width="14%">${order.addressModel.serviceTime}<br/></td>
        			<td width="25%">
      					<c:forEach items="${order.detailList}" var="detail" varStatus="det">
      					<c:if test="${det.index >='1'}">
	      							,
	      				</c:if>
      						${detail.productName}：${detail.buyNum }
    							<c:if test="${detail.chargetype=='1'}">
      							平米
      						</c:if>
      						<c:if test="${detail.chargetype=='0'}">
      							台
      						</c:if>
	      				<c:if test="${det.count>2}">……</c:if>
      					</c:forEach>
        			</td>
        			<td width="13%"><label>￥${order.payMoney}</label></td>
        			<td width="13%">
      				 	<p> 
	        				<c:choose>
										<c:when test="${order.payType=='0'}">
											 <span > 
											 	<img  src="${pageContext.servletContext.contextPath}/static/sysback/img/up.png">
											 </span><br />
										</c:when>
										<c:otherwise>
											<span><img  src="${pageContext.servletContext.contextPath}/static/sysback/img/down.png"></span><br />
										</c:otherwise>
									</c:choose>
	        			</p>
        				<p>
        			<c:if test="${order.state=='1'}">
								待支付
							</c:if>
							<c:if test="${order.state=='2'}">
								订单取消
							</c:if>
							<c:if test="${order.state=='3'}">
								<c:if test="${order.payType=='1'}" >
									待接单
								</c:if>
								<c:if test="${order.payType=='0'}" >
									<aebiz:showTitle titleId="ordermain.m.paid"/>
								</c:if>
							</c:if>
							<c:if test="${order.state=='4'}">
								已确认,待上门
							</c:if>
							<c:if test="${order.state=='5'}">
								上门中
							</c:if>
							<c:if test="${order.state=='6'}">
								上门完成
							</c:if>
							<c:if test="${order.state=='7'}">
								丈量
							</c:if>
							<c:if test="${order.state=='8'}">
								工作中
							</c:if>
							<c:if test="${order.state=='9'}">
								工作完成
							</c:if>
							<c:if test="${order.state=='10'}">
								等待客户确认
							</c:if>
							<c:if test="${order.state=='11'}">
								已完成
						
							</c:if>
							<c:if test="${order.state=='12'}">
								无效订单
							</c:if>
						</p>
						
					<a href="${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/toOrderDetail/${order.uuid}" target="_Blank">订单详情</a>
					
					</td>
				</tr>
        </table>
        </c:forEach>  
        </div>
        <!-- 家政员-个人中心主体右侧订单列表end -->
        <!-- 家政员-个人中心主体右侧最新消息 -->
        <div class="m_newest">
        	<div class="m_list_tit"><span class="fl">最新消息</span><a href="##" class="fr">查看更多 ></a></div>
      		<div class="m_newest_train">
	      		<ul>
	      			
	      			<c:forEach items="${msgList}" var="aa" varStatus="det">
      						<li class="m_li1">
			      				${aa.innerMessageModel.content}
			      				<div class="m_train_li"></div>
	      					</li>
      				</c:forEach>
	      			
	      		</ul>
      		</div>
        </div>
       
        <!-- 家政员-个人中心主体右侧我的评价 end-->
		  </div>
		  <!-- 家政员-个人中心主体右侧 end -->
	  </div>
  </div>
	
	<!-----雇我吧首页底部----->

   <%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterBottom.jsp"%>

  <!-----雇我吧首页底部 end----->
  <script>
  $(document).ready(function(){
  $('input').iCheck({
    checkboxClass: 'icheckbox_square-blue',  // 注意square和blue的对应关系,用于type=checkbox
    radioClass: 'iradio_square-blue', // 用于type=radio
    increaseArea: '20%' // 增大可以点击的区域
  });
});</script>
</body>
</html>