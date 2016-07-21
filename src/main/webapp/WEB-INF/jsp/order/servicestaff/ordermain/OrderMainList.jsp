<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp"%>	
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>	
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script><!-- 调用日历插件的js -->	
  <title>雇我吧 -我的订单</title>
  <meta name="description" content="aebiz b2b2c index">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<!-- 家政员-个人中心top-->
	<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterHead.jsp"%>
	<!-- 家政员-个人中心top end-->
	<!-- 家政员-个人中心主体 -->
	<div class="container member_main">
		<div class="row ">
			<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterLeft.jsp"%> 
			
			<!-- 雇我吧-我的订单主体右侧 -->
			<div class="col-mi-9 m_order">
        <div class="member_tab">
					<span <c:if test="${orderState == '0'}">class="m_cur"</c:if>><a href="javascript:window.location.href='${pageContext.servletContext.contextPath}/servicestaff/order/list/0/1/10?init=true'">所有订单</a> <label>(${totalOrder})</label></span>
					<b>|</b>
					<span <c:if test="${orderState == '1'}">class="m_cur"</c:if>><a href="javascript:window.location.href='${pageContext.servletContext.contextPath}/servicestaff/order/list/1/1/10?init=true'">待付款</a> <label>(${toPay })</label></span>
					<b>|</b>
					<span <c:if test="${orderState == '3'}">class="m_cur"</c:if>><a href="javascript:window.location.href='${pageContext.servletContext.contextPath}/servicestaff/order/list/3/1/10?init=true'">待确认</a><label>(${toConfirm })</label></span>
					<b>|</b>
					<span <c:if test="${orderState == '11'}">class="m_cur"</c:if>><a href="javascript:window.location.href='${pageContext.servletContext.contextPath}/servicestaff/order/list/11/1/10?init=true'">待评价</a><label>(${toAppl })</label></span>
				</div>
				<!-- 雇我吧-我的订单主体右侧筛选模块 -->
				<form class="form-inline2" id="searchForm" action="${pageContext.servletContext.contextPath}/servicestaff/order/query" method="post">
					<input type="hidden" name="pageShow" value="${wm.pageShow}">
					<input type="hidden" name="orderState" value="${orderState}">
					<div class="m_screening">
						<div class="m_conditions clearfix">
							<div class="clearfix fl m_cond">
								<label class="fl form_left">订单类型：</label>
								<div class="fl">
									<select name="state" id="state" class='form-control'>
										<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.all" />--</option>
										<c:forEach items="${orderStateList}" var="type">
											<option value="${type.value }" <c:if test="${qm.state==type.value}">selected</c:if> >${type.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						
							<div class="clearfix fl m_condin">
								<label class="fl form_left">下单时间：</label>
								<div class="fl form_right">
									<input type="text" name="createStartTime" id="createStartTime" class="form-control m_input m_gold_input datepick" value="${qm.createStartTime }">
									<span class="fl m_span">-</span>
									<input type="text" name="createEndTime" id="createEndTime" class="form-control m_input m_gold_input datepick" value="${qm.createEndTime }">
								</div>
							</div>
							<div class="clearfix fl m_cond2">
								<label class="fl form_left">评价状态：</label>
								<div class="fl">
										<select name="muState" id="mutualState" class='form-control'>
											<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.all" />--</option>
											<option value="1" <c:if test="${qm.muState=='1'}">selected</c:if>>已评价</option>
											<option value="0" <c:if test="${qm.muState=='0'}">selected</c:if>>未评价</option>
										</select>
								</div>
							</div>
							<button class="btn btn-custom fl y_mlr5" onclick="javascript:checkTime();" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip" type="submit" ><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
							<a class="btn btn-custom fl y_mlr5" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></a>
						</div>
					</div>
					<script>
						//下拉菜单
					$('.m_conditions .m_form').click(function(){
						$(this).next('.form_xia').toggle();
					});
					$('.form_xia p').click(function(){
						$(this).parent().hide();
				    $(this).parent().prev('input').val($(this).text());
					});
						
					$(".m_xia").hover(function () {
	            $(".form_xia p").parent().hide();
	         });
					</script>
				
				</form>
				<!-- 雇我吧-我的订单主体右侧筛选模块 end -->
				
				
        <!-- 家政员-个人中心主体右侧订单列表 -->
        <div class="m_list m_my">
        	<ul class="m_table">
        	  <li style="width:20%">服务类型</li>
        	  <li style="width:14%">服务时间</li>
        	  <li style="width:25%">订单信息</li>
        	  <li style="width:13%">订单金额</li>
        	  <li style="width:13%">订单状态</li>
        	  <li style="width:14.3%; border-right:0px;" >交易操作</li>
        	</ul>
        
        	<c:forEach items="${wm.rows}" var="order">
	        	<table class="m_list_table">
	        		<tr>
	        			<th colspan="6" style="padding:17px 10px;">
	        				<label class='inline' for="c1">下单时间：${order.orderTime}&nbsp;&nbsp;订单编号号：${order.orderId}</label>
	        			</th>
	        		</tr>
	        		<tr>
	        			<td width="20%">
	        				<div class="fl m_list_table_pic"><img src="${pageContext.request.contextPath }/static/usercenter/img/m_hb.jpg"></div>
	        				<div class="fl m_list_table_tit">家庭保洁</div>
	        			</td>
	        			<td width="14%">${order.addressModel.serviceTime}<br/></td>
	        			<td width="25%">
	      					<c:forEach items="${order.detailList}" var="detail" varStatus="det" begin="0" end="1">
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
	        			<td width="13%"><label>￥${order.totalMoney}</label></td>
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
	        				<a href="${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/toOrderDetail/${order.uuid}" class="m_xq">订单详情</a>
	        			</td>
	        			<td width="14.3%">
	        				<c:choose> 
		        				<c:when test="${order.state=='11' && order.mutualState !='1'}"> 
		        					<a href="###" class="color_a">评价</a></br>
										</c:when>
		        				<c:when test="${order.mutualState=='1'}"> 
		        					已评价</br>
										</c:when>
										<c:otherwise>
											--</br>
										</c:otherwise>
									</c:choose>
	        			</td>
	        		</tr>
	        	</table>
        	</c:forEach>
        	<c:if test="${empty wm.rows}">
	        	<table class="m_list_table">
	        		<tr>
	        			<td colspan="3" align="left">暂无数据</td>
	        		</tr>
	        	</table>	
        	</c:if>
        	<!--翻页-->
         	 <aebiz:userCenterPage listPath="servicestaff/order/list/${orderState}" />
					<!--翻页 end-->
        	
        </div>
        <!-- 家政员-个人中心主体右侧订单列表end -->

		  </div>
		  <!-- 雇我吧-我的订单主体右侧 end -->
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
	
 <script>
	function clearSearch(){
		$("#state").val("");
		$("#createStartTime").val("");
		$("#createEndTime").val("");
		$("#mutualState").val("");
			
	}
	function checkTime(){
		var begin = $("#createStartTime").val();
		var end = $("#createEndTime").val();
		if(end<begin){
			alert("下单时间输入有误！");
			return;
		}
	}
	function toWuxiao(uuid){
		if(confirm("确认要取消订单吗？")) {
    		//删除
			$.post(
					"${pageContext.servletContext.contextPath}/usercenter/customer/toInvalid",
		    	{"uuid":uuid,ranNum:Math.random()},	
			    function(data) {	       
			       if(data =="success") {
			  			//刷新
						//bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
			  			alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>');
						location.reload();     	
			       }else{
			       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
			       }
			    }
			)	
    	}
		
	}	
	</script>
</body>
</html>