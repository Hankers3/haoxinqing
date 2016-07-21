<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>家政员 -订单详情</title>
  <meta name="description" content="aebiz b2b2c index">
  <!--[if IE]> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <![endif]-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/import/ListImport.jsp"%>	
	<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/import/ListImportJs.jsp"%>	
</head>
<body>
	<!-- 家政员-个人中心top-->
	<!--<div class="member_top">
	  <div class="container">
	  	<div class="fl member_logo"><a href="###"><img src="${pageContext.servletContext.contextPath}/static/usercenter/img/member_logo.png"></a></div>
	  	<div class="fl member_gu">我的雇我吧</div>
	  	<div class="fr member_right">消息<span>2</span><i class="fa fa-angle-down"></i><b>|</b> <a href="###">13163287627</a><a href="###" class="m_tcin">[退出]</a></div>
	  </div>
	</div>-->
	<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterHead.jsp"%>
	<!-- 家政员-个人中心top end-->
	<!-- 家政员-个人中心主体 -->
	<div class="container member_main">
		<div class="row ">
			<!--<div class="col-mi-3">
				<ul class="member_left">
					<li class="member_cur"><a href="##"><i class="fa fa-file-text"></i>我的订单</a></li>
					<li class="even"><a href="##"><i class="fa fa-credit-card"></i>我的收入</a></li>
					<li><a href="##"><i class="fa fa-calendar"></i>我的档期</a></li>
					<li class="even"><a href="##"><i class="fa fa-weixin"></i>评价管理</a></li>
					<li><a href="##"><i class="fa fa-pencil"></i>我的绩效</a></li>
					<li class="even"><a href="##"><i class="fa fa-star"></i>我的培训</a></li>
					<li><a href="##"><i class="fa fa-book"></i>我的考试</a></li>
					<li class="even"><a href="##"><i class="fa fa-cog"></i>个人设置</a></li>
				</ul>s
			</div>-->
			<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterLeft.jsp"%>

			<!-- 雇我吧-订单详情主体右侧 -->
			<div class="col-mi-9 m_order">
				<div class="m_income_tit m_coupon_tit"><span>订单详情</span></div>
        <div class="m_details">
				 
				 <c:if test="${order.state=='4'}">
					  <div class="cart_step cart_step_1">
							<div></div>
							<ul>
								<li class="cur">接单</li>
								<li>上门</li>
								<li>完成</li>		
							</ul>
						</div>
				 </c:if>
					
				 
				 <c:if test="${order.state<=10&&order.state>4}">
					  <div class="cart_step cart_step_2">
							<div></div>
							<ul>
								<li class="cur">接单</li>
								<li>上门</li>
								<li>完成</li>		
							</ul>
						</div>
				 </c:if>
				 
				  
				 <c:if test="${order.state==11}">
					  <div class="cart_step cart_step_3">
							<div></div>
							<ul>
								<li class="cur">接单</li>
								<li>上门</li>
								<li>完成</li>		
							</ul>
						</div>
				 </c:if>	
					
					
							
					
					<div class="m_border">
	        	<dl>
	        		<dt>订单号</dt>
	        		<dd>${order.orderId}</dd>
	        	</dl>
	        	<dl>
	        		<dt>下单时间</dt>
	        		<dd>${order.orderTime}</dd>
	        	</dl>
	        	<dl>
	        		<dt>订单信息</dt>
	        		<dd>
	        			<!--<p><span>厨房：5㎡</span><label>￥80</label></p>
	        			<p><span>卫生间：20㎡</span><label>￥100</label></p>
	        			<p><span>其它房间：50㎡</span><label>￥150</label></p>
	        			<p><span>冰箱：1个</span><label>￥200</label></p>-->
	        			
	        			<c:forEach items="${order.detailList}" var="detail" varStatus="det">
      						<p><span>${detail.productName}：${detail.buyNum }
      							<c:choose>
												<c:when test="${detail.chargetype=='0'}">
													平米
												</c:when>
												<c:otherwise>
													个
												</c:otherwise>
										</c:choose>
											</span><label>￥${detail.unitPrice }</label></p>
      					</c:forEach>
      					
	        		</dd>
	        	</dl>
        	</div>
        	<div class="m_border m_border1">
	        	<dl>
	        		<dt>订单备注</dt>
	        		<dd>
	        			${order.note}
	        		</dd>
	        	</dl>
	        	<dl>
	        		<dt>服务时间</dt>
	        		<dd>
	        			${order.addressModel.serviceTime}
	        		</dd>
	        	</dl>
	        	
	        	<dl>
	        		<dt>服务地址</dt>
	        		<dd>
	        			${serviceAddress}
	        		</dd>
	        	</dl>
	        	<dl>
	        		<dt>服务人员</dt>
	        		<dd>
	        			
	        			<c:forEach items="${order.serviceStaffs}" var="servicelist" varStatus="det">
	        			<p>${servicelist.serviceStaffName}&nbsp;&nbsp;&nbsp;${servicelist.mobile}</p>
	        			</c:forEach>
	        			
	        		</dd>
	        	</dl>
	        </div>
	        	<dl>
        		<dt>联系人</dt>
        		<dd>
        			${order.addressModel.name}<span>${order.addressModel.mobile}</span>
        		</dd>
        	</dl>
        	<dl>
        		<dt>备选联系人</dt>
        		<dd>
        			${order.addressModel.alternative}<span>${order.addressModel.alternativePhone}</span>
        		</dd>
        	</dl>
        	<dl>
        		<dt>支付方式</dt>
        		<dd>
        			
        			<c:if test="${order.payType=='1'}">
								线下支付
							</c:if>
							<c:if test="${order.payType=='0'}">
								线上支付
							</c:if>
							
							   
        		</dd>
        	</dl>
        	
        	
        	
        	
        	<div class="m_details_b">预计订单金额：<label>￥${order.totalMoney}</label> - 优惠劵<label>￥${order.couponMoney}</label> - 积分<label>${order.integralMoney}</label> = 订单总金额<label class="m_label">￥${order.needPayMoney}</label></div>
        	<div class="m_details_btn">
	        	<!--<a href="##" class="btn btn-custom2">取消订单</a>
	        	<a href="##" class="btn btn-custom">立即支付</a>-->
        	</div>
        </div>
		  </div>
		  <!-- 雇我吧-订单详情主体右侧 end -->
	  </div>
  </div>
	
	<!-----雇我吧首页底部----->
  <div class="m_foot">
  	<p><a href="##">关于雇我吧</a>|<a href="##">联系我们</a>|<a href="##">加入我们</a>|<a href="##">常见问题</a>|<a href="##">意见反馈</a></p>
  	<div class="m_footin">Copyright@2015 雇我吧 版权所有 京ICP备12345678号</div>
  </div>
  <!-----雇我吧首页底部 end----->
<script>
		if ($(".icheck-me").length > 0) {
      $(".icheck-me").each(function() {
        var $el = $(this);
        var skin = ($el.attr('data-skin') !== undefined) ? "_" + $el.attr('data-skin') : "",  //将data-skin属性的值 传递到参数中
          color = ($el.attr('data-color') !== undefined) ? "-" + $el.attr('data-color') : ""; //将data-color属性的值 传递到参数中

        var opt = {  //设置参数
          checkboxClass: 'icheckbox' + skin + color,
          radioClass: 'iradio' + skin + color,
          increaseArea: "10%"
        }

        $el.iCheck(opt);  //带参数调用js
      });
    }	
	</script>

</body>
</html>