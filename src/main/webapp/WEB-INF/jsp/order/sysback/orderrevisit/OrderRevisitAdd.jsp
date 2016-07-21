<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>

<!doctype html>
<html>
</head>
<body>
	
		<!--静态页面-->
		<div id="modal-stars" >
			<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/orderrevisit/add" method="post" class='form-horizontal form-bordered form-validate'>
			<div class="modal-dialog modal_1000">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<!--订单回访-->
						<h3 id="user-infos"><aebiz:showTitle titleId="basebusiness.showmessage.orderRevisit"/></h3>
					</div>
					<div class="modal-body">
						<div class="box">	
							<div class="box-content nopadding">									
								<table class="table table-bordered mb_10">
									<tbody>
										<tr>
											<!--订单号-->
											<th width="13%"><aebiz:showTitle titleId="orderrevisit.m.orderMainUuid"/></th>
											<td width="20%" >
													<input type="hidden" id ="orderMainUuid" name="orderMainUuid" class="form-control" value="${w.uuid}" readonly="true">
												${w.orderId}
											</td>
											<!--订购人-->
											<th width="13%"><aebiz:showTitle titleId="orderrevisit.m.subscriber"/></th>
											<td width="20%">${w.addressModel.name}</td>
											<!--订购人电话-->
											<th width="13%"><aebiz:showTitle titleId="orderrevisit.m.subscribermobile"/></th>
											<td width="20%">${w.addressModel.mobile}</td>
										</tr>
										<tr>
											<!--备选人-->
											<th><aebiz:showTitle titleId="orderrevisit.m.AlternativePerson"/></th>
											<td>${w.addressModel.alternative}</td>
											<!--备选人电话-->
											<th><aebiz:showTitle titleId="orderrevisit.m.AlternativePersonMobile"/></th>
											<td colspan="3">${w.addressModel.alternativePhone}</td>
										</tr>
										<tr>	
											<!--服务时间-->
											<th><aebiz:showTitle titleId="orderrevisit.m.serviceTime"/></th>
											<td>${w.addressModel.serviceTime}</td>
											<!--服务地点-->
											<th><aebiz:showTitle titleId="orderrevisit.m.serviceaddress"/></th>
											<td colspan="3">${w.addressModel.addressDetail}</td>
										</tr>
										<tr>
											<!--上门时间-->
											<th><aebiz:showTitle titleId="orderrevisit.m.dropInTime"/></th>
											<td>${w.addressModel.dropInTime}</td>
											<!--丈量时间-->
											<th><aebiz:showTitle titleId="orderrevisit.m.measureTime"/></th>
											<td>${w.addressModel.measureTime}</td>
											<!--完成时间-->
											<th><aebiz:showTitle titleId="orderrevisit.m.completeTime"/></th>
											<td>${w.addressModel.completeTime}</td>
										</tr>
									</tbody>
								</table>
								<table class="table table-bordered">
									
									<tbody>
										
										<tr>
											<!--订购产品-->
											<th colspan="6"><aebiz:showTitle titleId="orderrevisit.m.orderGoods"/></th>
										</tr>
										<c:forEach items="${t}" var="orderDetail" varStatus="orderD">	
											<tr>
											<c:if test="${orderD.index+1=='1'}">
												<td rowspan="${fn:length(t)+1}" >${w.addressModel.cityName}</td>
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
									<div class="text-right">
										<span class="y_plr20">
										<span class="y_plr20">
											 <aebiz:showTitle titleId="ordermain.m.totalMoney"/><b class="font-red">(¥${w.totalMoney})</b> -
											 积分兑换金额<b class="font-red">(¥${w.integralMoney})</b> - 
											 优惠券金额<b class="font-red">(¥${w.couponMoney})</b> - 
											 礼品卡金额<b class="font-red">(¥${w.giftcm})</b> -
											 账户余额<b class="font-red">(¥${w.balanceMoney})</b> 
											 	= <strong class="font-red tot_price">¥${w.needPayMoney}</strong>
										</span>
									</div>
								</table>
								
								
								<div class="row">
									<div class="col-sm-12 mb_20">
										<div class="form-group">
											<!--您对本次服务的总体评价：-->
											<label for="textfield" class="control-label fl"><aebiz:showTitle titleId="orderrevisit.m.orderTotalcomment"/>:</label>
											<div class="col-sm-5">
												<ul class="rating-level" id="stars2">
													<input type="hidden" name="revistScore" id="revistScore" value="0" >
													<li><a class="one-star" star:value="1" href="#" >1</a></li>
													<li><a class="two-stars" star:value="2" href="#">2</a></li>
													<li><a class="three-stars" star:value="3" href="#"  >3</a></li>
													<li><a class="four-stars" star:value="4" href="#" >4</a></li>
													<li><a class="five-stars" star:value="5" href="#" >5</a></li>
												</ul>
												<span class="result" id="stars2-tips"></span>
												<input type="hidden" id="stars2-input" name="b" value="" size="2" />
											</div>
										</div>											
									</div>
								</div>
								<div class="row">	
										<div class="col-sm-12">
											<div class="form-group">
												<!--您对雇我吧有什么意见和建议：-->
												<label for="textfield" class="control-label fl pt_5"><aebiz:showTitle titleId="orderrevisit.m.orderTotalSuggestion"/>:</label>
												<div class="col-sm-8">
													<input type="text" name="content" class="form-control" id="content">								
												</div>
											</div>
										</div>
									</div>
								
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<!--<button class="btn btn-primary moresearch" data-dismiss="modal"><aebiz:showTitle titleId="orderrevisit.m.submitrevisit"/></button>-->
						<!--<input onclick="javascript:toUpdate();"  class="btn btn-primary moresearch" data-dismiss="modal" value='<aebiz:showTitle titleId="orderrevisit.m.submitrevisit"/>'>-->
						<input type="submit" class="btn btn-primary submit" value='<aebiz:showTitle titleId="orderrevisit.m.submitrevisit"/>'>
						<button class="btn moresearch" data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>
				</div>
			</div>
		</form>
		</div>

	
</body>

</html>


<script>
  $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});
  });
    
    // 星级打分
	var Class = {
		create: function() {
			return function() { this.initialize.apply(this, arguments); }
		}
	}
	var Extend = function(destination, source) {
		for (var property in source) {
			destination[property] = source[property];
		}
	}
	function stopDefault( e ) {
		 if ( e && e.preventDefault ){
			e.preventDefault();
		}else{
			window.event.returnValue = false;
		}
		return false;
	} 

	var Stars = Class.create();
	Stars.prototype = {
		initialize: function(star,options) {
			this.SetOptions(options); //默认属性
			var flag = 999; //定义全局指针
			var isIE = (document.all) ? true : false; //IE?
			var starlist = document.getElementById(star).getElementsByTagName('a'); //星星列表
			var input = document.getElementById(this.options.Input) || document.getElementById(star+"-input"); // 输出结果
			var tips = document.getElementById(this.options.Tips) || document.getElementById(star+"-tips"); // 打印提示
			var nowClass = " " + this.options.nowClass; // 定义选中星星样式名
			var tipsTxt = this.options.tipsTxt; // 定义提示文案
			var len = starlist.length; //星星数量
			var revistScore=$("#revistScore").val();

			for(i=0;i<len;i++){ // 绑定事件 点击 鼠标滑过
				starlist[i].value = i;
				starlist[i].onclick = function(e){
					stopDefault(e);
					this.className = this.className + nowClass;
					flag = this.value;
					input.value = this.getAttribute("star:value");
					tips.innerHTML = tipsTxt[this.value];
					revistScore=this.value+1;
					$("#revistScore").val(revistScore);
				
				}
				starlist[i].onmouseover = function(){
					if (flag< 999){
						var reg = RegExp(nowClass,"g");
						starlist[flag].className = starlist[flag].className.replace(reg,"")
					}
				}
				starlist[i].onmouseout = function(){
					if (flag< 999){
						starlist[flag].className = starlist[flag].className + nowClass;
					}
				}
			};
			if (isIE){ //FIX IE下样式错误
				var li = document.getElementById(star).getElementsByTagName('li');
				for (var i = 0, len = li.length; i < len; i++) {
					var c = li[i];
					if (c) {
						c.className = c.getElementsByTagName('a')[0].className;
					}
				}
			}
		},
		//设置默认属性
		SetOptions: function(options) {
			this.options = {//默认值
				Input:			"",//设置触保存分数的INPUT
				Tips:			"",//设置提示文案容器
				nowClass:	"current-rating",//选中的样式名
				tipsTxt:		["1<aebiz:showTitle titleId="orderrevisit.m.score1"/>","2<aebiz:showTitle titleId="orderrevisit.m.score2"/>","3<aebiz:showTitle titleId="orderrevisit.m.score3"/>","4<aebiz:showTitle titleId="orderrevisit.m.score4"/>","5<aebiz:showTitle titleId="orderrevisit.m.score5"/>"]//提示文案
			};
			Extend(this.options, options || {});
		}
	}
	var Stars2 = new Stars("stars2")
	

function toUpdate(){
			var content =$("#content").val();
		  $("#mainForm").submit();
		}
	
	
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>