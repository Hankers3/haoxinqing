<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>雇我吧 -pc在线下单</title>
  <meta name="description" content="aebiz b2b2c index">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/plugins/icheck/all.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/global.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/channel.css">
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>	
	<link rel="icon" type="${pageContext.request.contextPath }/static/usercenter/img/image/png" href="img/icon.png">
</head>
<body>
	<!---头部--->
	<%@ include file="/WEB-INF/jsp/basebusiness/userfront/common/userfronTop.jsp" %>
  <!-----End----->
  <!-----雇我吧-在线下单----->
  <div class="container m_online">
  	<div class="m_online_pic"></div>
  	<ul class="m_online_tit">
  		<li class="m_color">填写房屋状况</li>
  		<li class="m_li1">联系方式</li>
  		<li class="m_li2">成功提交订单</li>
  	</ul>
  </div>
  <div class="container m_inthe">
  	<div class="row">
  		<div class="col-mi-6">
  			<div class="m_bor"><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_xd.jpg"></div>
  		</div>
  		<div class="col-mi-6">
  		<form class="form-inline2" id="mainForm" action="${pageContext.servletContext.contextPath}/orderfront/order/addOrders" method="post">
  			<div class="m_inthe_tit">告诉我们您的房屋情况</div>
  			<ul class="m_ul">
  			<input type="hidden" name="cityId" value="${cityId}">
  				<c:forEach items="${proCityList}" var="pro">
  				<input type="hidden" name="productName" value="${pro.productName }">
  				<input type="hidden" name="productUuid" value="${pro.productUuid }">
  				<input type="hidden" name="chargetype" value="${pro.chargetype }">
  				<input type="hidden" id="pricevalue${pro.productUuid }" name="basePrice" value="${pro.shopPrice }">
  				<li>
  					<span>${pro.productName }</span>
						<span class="m_span_right">
							<!--<input type="text" id="number${pro.productUuid }" name="buyNum" value="0" class="form-control" onblur="javascript:countPrice(this.value,'number${pro.productUuid }');" data-rule-number="true">-->
							<input type="text" id="number${pro.productUuid }" name="buyNum" placeholder="0" class="form-control" onblur="javascript:countPrice(this.value,'number${pro.productUuid }');" onclick="javascript:deletesome(this.id);"data-rule-number="true">
							
							<script>
								 function deletesome(uuid) { 
								 	//alert("111");
									$("#"+uuid).val("");
									}
	            </script>
						
						</span>
						<b>
						<c:if test="${pro.chargetype ==1}">m<sup>2</sup></c:if>
						<c:if test="${pro.chargetype ==0}">个</c:if>
						</b>
						<label><strong>${pro.shopPrice }元
						</strong>/
						<c:if test="${pro.chargetype ==1}">m<sup>2</sup></c:if>
						<c:if test="${pro.chargetype ==0}">个</c:if>
						</label>
  				</li>
  				</c:forEach>
  			</ul>
  			<div class="m_xd_jg">
  				<label>* 实际价格以现场测量为准</label>
  				<span>预计总价</span>
  				<input type="hidden" id="totalMoney" name="totalMoney" value="0"/>
  				<strong id="totalCost">￥0</strong>
  			</div>
  			<p class="m_inline_p">
  				<input type="checkbox" checked="checked" name="check" class='icheck-me' id="c" data-skin="minimal">
        	<label class='inline' for="c">我已阅读并同意《雇我吧服务标准》</label>
  			</p>
  			<div class="m_xd_btn">
  				<button class="btn btn-custom2" title="下一步" rel="tooltip" type="button" >下一步</button>
  			</div>
  			</form>
  		</div>
  	</div>
  	<!-- 引入登录弹出框 -->
   <%@ include file="/WEB-INF/jsp/common/quickLogin.jsp"%>
  </div>
  <script>
 	 $(".btn-custom2").click(function(){
 		 var totalCost = $("#totalCost").html()
 		 totalCost = totalCost.substr(1,totalCost.length);
 		 if(totalCost<=40){
 			 alert("订单总额不能低于40元");
 			 return ;
 		 }
 		if($("input[name='check']:checkbox").is(":checked")){
 	  		 $.get(
 	 		    	"${pageContext.servletContext.contextPath}/usercenter/customer/isLogin",
 	 		    	{ranNum:Math.random()},	
 	 			    function(data) {	       
 	 		    		if("0"==data) {
 							//弹出登录框
 							$("#modal-login-form").modal();
 						}else{
 							mainForm.submit();
 						}
 	 			    }
 	 			);
        }else{
        	alert("请阅读雇我吧服务标准，并在前面打上勾");
        	return ;
        }
	})
	
	
  	function countPrice(act,id){
 		var reg = new RegExp("^[0-9]*$");
 		if(!reg.test(act)){
 			$("#"+id).val(0);
 	        alert("请输入数字!");
 	        return ;
 	    }
  		var totalMoney=0;
  	  $("input[name='productUuid']:input").each(function(){				
  		  var productUuid =$(this).val();
  		  var price = $("#pricevalue"+productUuid).val();
  		  var number = $("#number"+productUuid).val();
  		  totalMoney= totalMoney +(price*number);
  	})
  	$("#totalCost").html("￥"+parseFloat(totalMoney).toFixed(2));
  	$("#totalMoney").val(totalMoney);  
  	}
		//下拉菜单
	$('.m_xia .m_form').click(function(){
		$(this).next('.m_inthe_xia').toggle();
	});
	$('.m_inthe_xia li').click(function(){
		$(this).parent().hide();
    $(this).parent().prev('input').val($(this).text());
		});
		$(".m_xia").hover(function () {
      $(".m_inthe_xia li").parent().hide();
   });
	</script>
  <!-----雇我吧-在线下单 end----->
  <!-----雇我吧-在线下单为什么选择雇我吧----->
  <div class="container m_choicein">
  	<div class="m_choicein_tit"><span>为什么选择雇我吧？</span></div>
  	<h2 class="m_cleaning_tit"><span>优质保洁员准入</span></h2>
  	<p>【 引领行业新标准 】</p>
  	<div class="m_choicein_pic"><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_xd2.jpg"></div>
  </div>
  <!-----雇我吧-在线下单为什么选择雇我吧 end----->
  <!-----雇我吧-在线下单保洁工具----->
  <div class="container m_choicein">
  	<h2 class="m_cleaning_tit"><span>专业保洁工具</span></h2>
  	<p>【 统一着装，专业有效 】</p>
  	<div class="m_choicein_pic"><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_xd3.jpg"></div>
  </div>
  <!-----雇我吧-在线下单保洁工具end----->
  <!-----雇我吧-标准化服务----->
  <div class="container m_standard">
  	<h2 class="m_cleaning_tit"><span>标准化服务</span></h2>
  	<p class="m_standard_p">【 统一着装，专业有效 】</p>
  	<div class="row">
  		<ul class="m_cleaning_ul">
  			<li>
  				<div class="col-mi-6">
  					<h4>卧室，客厅和公共区域清洁</h4>
  					<p>The bedroom, sitting room and public area clean.</p>
  					<div class="m_cleaning_titin">清除所有物件表面灰尘；<br/>擦镜子和玻璃夹具；<br/>清理打扫所有的地板表面；垃圾清倒。</div>
  				</div>
  				<div class="col-mi-6" ><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_zs.jpg"></div>
  			</li>
  			<li>
  				<div class="col-mi-6 "><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_zs.jpg"></div>
  				<div class="col-mi-6 ">
  					<h4>卧室，客厅和公共区域清洁</h4>
  					<p>The bedroom, sitting room and public area clean.</p>
  					<div class="m_cleaning_titin">清除所有物件表面灰尘；<br/>擦镜子和玻璃夹具；<br/>清理打扫所有的地板表面；垃圾清倒。</div>
  				</div>
  			</li>
  			<li>
  				<div class="col-mi-6">
  					<h4>卧室，客厅和公共区域清洁</h4>
  					<p>The bedroom, sitting room and public area clean.</p>
  					<div class="m_cleaning_titin">清除所有物件表面灰尘；<br/>擦镜子和玻璃夹具；<br/>清理打扫所有的地板表面；垃圾清倒。</div>
  				</div>
  				<div class="col-mi-6"><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_zs.jpg"></div>
  			</li>
  			<li class="ul_li">
  				<div class="col-mi-6"><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_zs.jpg"></div>
  				<div class="col-mi-6">
  					<h4>卧室，客厅和公共区域清洁</h4>
  					<p>The bedroom, sitting room and public area clean.</p>
  					<div class="m_cleaning_titin">清除所有物件表面灰尘；<br/>擦镜子和玻璃夹具；<br/>清理打扫所有的地板表面；垃圾清倒。</div>
  				</div>
  			</li>
  		</ul>
  	</div>
  </div>
  <!-----雇我吧-标准化服务 end----->
  <!-----雇我吧-用户保障----->
  <div class="container m_security">
  	<h2 class="m_cleaning_tit"><span>用户保障</span></h2>
  	<p class="m_standard_p">【 四大保障 — 为您保驾护航 】</p>
    <div class="row m_securityin">
    	<div class="col-mi-3">
    		<div class="m_security_pic"><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_s1.jpg"></div>
    		<h3>诚信保障</h3>
    		<div class="m_security_tit">爽约/私自调价/私自带人：<br/>免费赠送2小时服务。</div>
    	</div>
    	<div class="col-mi-3">
    		<div class="m_security_pic"><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_s2.jpg"></div>
    		<h3>安全保障</h3>
    		<div class="m_security_tit">通过雇我吧预约的服务将同在免费享有《家政责任险》，全程保障雇主、服务人员及第三者的人身安全。</div>
    	</div>
    	<div class="col-mi-3">
    		<div class="m_security_pic"><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_s3.jpg"></div>
    		<h3>财产保障</h3>
    		<div class="m_security_tit">服务过程中证实出现因服务人员造成的财产损失，客户提供发票，阿姨帮先行协商赔付。</div>
    	</div>
    	<div class="col-mi-3">
    		<div class="m_security_pic"><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_s4.jpg"></div>
    		<h3>品质保障</h3>
    		<div class="m_security_tit">未按雇我吧保洁规范服务，磨洋工或打扫不干净的，可申请订单赔付。</div>
    	</div>
    </div>
  </div>
  <!-----雇我吧-用户保障 end----->
  <!-----雇我吧-在线下单注意事项----->
  <div class="container m_choicein m_matter">
  	<h2 class="m_cleaning_tit"><span>注意事项</span></h2>
  	<p>【 确保保洁人员人身安全的前提下进行 】</p>
  	<div class="m_choicein_pic"><img src="${pageContext.request.contextPath }/static/usercenter/img/ucimg/m_xd4.jpg"></div>
  </div>
  <!-----雇我吧-在线下单注意事项 end----->
  <!-----雇我吧首页底部----->
 <%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
  <!-----雇我吧首页底部 end----->
  <script>
   //单选框复选框样式重置
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
	$(document).ready(function() {
  		var msg = "${err_msg}";
  		var cityId = "${cityId}" ;
  		if("product_null" == msg){
  			alert("房屋状况为空，请重新下单");
  			if(cityId == ""){
  				window.location.href="${pageContext.servletContext.contextPath}/userfront/platDecorate/index";
  			}
  		}
  	})
  </script>
</body>
</html>