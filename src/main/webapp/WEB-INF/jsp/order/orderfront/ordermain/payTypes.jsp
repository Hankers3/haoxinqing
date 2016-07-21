<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>                        
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>       
<div class="m_integral clearfix">
  	  <!--使用积分-->
  		<div class="m_integralin" >
  		<input type="hidden" id="intergral_value" value="0">
  			<p><span class="m_use_p"><i class="fa fa-minus-circle"></i>使用积分</span></p>
  			<div class="m_use">
  				<div class="m_usein" id="change_intergral">本次使用<span><input value="" id="intergralCount" name="intergralCount" class="form-control" onblur="checkNum();"></span>积分支付。<a href="javascript:void(0);" class="btn btn-custom use_intergral">使用</a>(您有积分<label>${customer.intergralCount}</label>，可使用积分<label>${customer.availableIntegral}</label>)</div>
  				<div class="m_usein" style="display:none" id="used_intergral">本次已使用<span><input id="usedInId" readonly="readonly" value="0"  class="form-control"></span>积分支付。<a href="javascript:changeintergral();" class="btn btn-custom">取消</a>(您有积分<label>${customer.intergralCount}</label>，可使用积分<label>${customer.availableIntegral}</label>)</div>
  			</div>
  		</div>
  		<!--优惠劵-->
  		<div class="m_integralin m_onsale">
  		<input type="hidden" id="coupon_value" value="0">
  			<p><span class="m_use_p"><i class="fa fa-minus-circle"></i>使用优惠券</span></p>
  			<div class="m_onsale_hi">
	  			<div class="m_use">
	  				<p>可用优惠券 <label>( ${couponCount } )</label> 张。</p>
	  				您可以 <a ${pageContext.request.contextPath }/usercenter/coupondetails/toList" target="_blank">查看所有优惠券</a> 了解使用限制。
	  			</div>
	  			<div class="m_use">
	  				<p>可用优惠券 <label>( ${couponCount } )</label> 张。</p>
	  				<div class="m_usein" id="show_coupon">输入优惠券兑换码<span><input value="" id="couponId" class="form-control"></span><a href="javascript:useCoupon();" class="btn btn-custom">使用</a></div>
	  				<div class="m_usein" id="show_couponAmount" style="display:none">已使用优惠券<span><input readonly="readonly" value="" id="use_couponId" name="use_couponId" class="form-control"></span>,<span id="couponAmount">抵用金额为：0元</span><a href="javascript:chooseCoupon();" class="btn btn-custom">取消</a></div>
	  			</div>
  			</div>
  		</div>
  		<!--使用储值卡-->
  		<div class="m_card">
  		<input type="hidden" id="gift_value" value="0">
  			<p><span class="m_use_p"><i class="fa fa-minus-circle"></i>使用礼品卡</span></p>
  			<div class="m_use">
  				<div class="m_cardin" id="show_giftId">输入您的礼品卡号<span><input id="giftCardValue" value="" class="form-control"></span><a href="javascript:useGift();" class="btn btn-custom">使用</a></div>
  				<div class="m_cardin" id="show_gift_detail" style="display:none">已使用礼品卡<span><input id="giftId" name="giftId" value="" class="form-control" readonly="readonly"></span>，<span id="giftCardAmount">抵用金额为：0元</span><a href="javascript:chooseGiftCard();" class="btn btn-custom">取消</a></div>
  		  </div>
  		</div>
  		<!--账号余额-->
  		<div class="m_integralin m_Balance">
  		<input type="hidden" id="balance_value1" value="0">
  			<p><span class="m_use_p"><i class="fa fa-minus-circle"></i>使用账户余额</span></p>
  			<div class="m_use">
  				<div class="m_usein" id="show_balance">本次使用余额<span><input id="balance_value" value="" class="form-control"></span>进行支付。<a href="javascript:void(0);" class="btn btn-custom account_balance">使用</a>(可使用余额<label>￥${vac.accountBalance }元</label>)</div>
  				<div class="m_usein" id="show_balance_use" style="display:none">本次已使用金额<span><input id="balance_value_use" name="balance_value_use" value="" readonly="readonly" class="form-control"></span>进行支付。<a href="javascript:void(0);" class="btn btn-custom quxiao_balance">取消</a>(可使用余额<label>￥${vac.accountBalance }元</label>)</div>
  			</div>
  		</div>
  	</div>
  	
  	<script>
  	function getTotalMoney(){
  		var totalMoney= 0;
  		var jf= $("#intergral_value").val()*1;
  		var yh= $("#coupon_value").val()*1;
  		var lp= $("#gift_value").val()*1;
  		var ye= $("#balance_value1").val()*1;
  		return totalMoney = (jf+yh+lp+ye)*1 ;
  	}
  	//使用余额
  	$(".account_balance").click(function(){
  		var balance_value = $("#balance_value").val()*1;
  		var total_balance = ${vac.accountBalance };
  		var totoalCost = ${qm.cartManagerModel.totalMoney }*1;
  		var tc = $("#total_amount").html()*1;
  		if(balance_value <=0){
  			alert("请输入要使用账户余额的数量");
  			return ;
  		}
  		var tm = getTotalMoney();
  		if(tm+balance_value>totoalCost){
  			alert("支付金额超出订单总金额");
  			return;
  		}
  		if(balance_value>total_balance){
  			alert("账户余额不足");
  		}else if(total_balance>=balance_value){
  			$("#total_amount").html(tc-balance_value);
  			$("#balance_value_use").val(balance_value);
  			$("#balance_value1").val(balance_value);
  			$("#show_balance").hide();
  			$("#show_balance_use").show();
  		}else{
  			alert("请正确输入");
  		}
  	})
  	//取消余额
  	$(".quxiao_balance").click(function(){
  		var bal = $("#balance_value_use").val(); 
  		var amount = showcost(bal*1);
  		$("#total_amount").html(amount);
  		$("#show_balance_use").hide();
		$("#show_balance").show();
		$("#balance_value_use").val("0");
		$("#balance_value1").val("0");
  	})
	//使用积分
  	$(".use_intergral").click(function (){
  		var canUse = ${customer.availableIntegral};
  		var totoalCost = ${qm.cartManagerModel.totalMoney }*1;
  		var nowUse = $("#intergralCount").val();
  		if(nowUse ==""){
  			alert("请输入要使用的积分数量");
  			return ;
  		}
  		var tm = getTotalMoney();
  		if(tm+nowUse/100>totoalCost){
  			alert("支付金额超出订单总金额");
  			return;
  		}
  		if(nowUse >canUse){
  			alert("您的积分不足");
  		}else{
  			$.post(
		    	"${pageContext.servletContext.contextPath}/orderfront/order/useIntergral",
		    	{"intergral":nowUse,ranNum:Math.random()},	
			    function(data) {
		    		if("success"==data){
		    			var tc = $("#total_amount").html()*1;
		    			$("#total_amount").html(tc-nowUse/100);
		    			$("#usedInId").val(nowUse);
			  			$("#intergral_value").val(nowUse/100);
			  			$("#change_intergral").hide();
			  			$("#used_intergral").show();
		    		}
		    	})
  			
  		}
  	})
  	//修改积分
  	function changeintergral(){
  		var nowUse = $("#intergralCount").val(); 
  		$.post(
		    	"${pageContext.servletContext.contextPath}/orderfront/order/useIntergral",
		    	{"intergral":-1,ranNum:Math.random()},	
			    function(data) {
		    		if("success"==data){
		    			var amount = showcost(nowUse/100);
		    	  		$("#total_amount").html(amount);
		    			$("#usedInId").val("");
		    	  		$("#intergral_value").val("");
		    	  		$("#change_intergral").show();
		    			$("#used_intergral").hide();
		    		}
		    	})
  	}
  	//使用优惠券
  	function useCoupon(){
  		var coupon = $("#couponId").val();
  		var totoalCost = ${qm.cartManagerModel.totalMoney }*1;
  		if(coupon ==""||coupon == null){
  			alert("请输入优惠券兑换码");
  		}else{
  			var tm = getTotalMoney();
  			$.post(
		    	"${pageContext.servletContext.contextPath}/orderfront/order/useCoupon",
		    	{"couponId":coupon,ranNum:Math.random()},	
			    function(data) {
		    		var result = eval("("+data+")") ;
		    		var state = result.couponStat ;
		    		if("state_success"==state){
		    			if(tm>=totoalCost){
		    	  			alert("支付金额超出订单总金额");
		    	  			return;
		    	  		}else{
		    	  			if(tm+result.couponAmount-totoalCost<0){
		    	  				$("#total_amount").html(totoalCost-(tm+result.couponAmount));
		    	  			}else{
		    	  				$("#total_amount").html(0);
		    	  			}
		    	  			$("#show_coupon").hide();
			    			$("#show_couponAmount").show();
			    			$("#use_couponId").val(coupon);
			    			$("#coupon_value").val(result.couponAmount);
			    			$("#couponAmount").html("抵用金额为："+result.couponAmount+"元");
		    	  		}
		    		}else {
		    			alert(state);
		    		}
			    }
			)
  		}
  	}
  	//取消使用优惠券
  	function chooseCoupon(){
  		var cou = $("#coupon_value").val(); 
  		var amount = showcost(cou*1);
  		$("#total_amount").html(amount);
  		$("#couponId").val("");
  		$("#show_coupon").show();
		$("#show_couponAmount").hide();
		$("#use_couponId").val("");
		$("#coupon_value").val("0");
  	}
  	
  //使用礼品卡
  	function useGift(){
  		var gift = $("#giftCardValue").val();
  		var totoalCost = ${qm.cartManagerModel.totalMoney }*1;
  		if(gift ==""||gift == null){
  			alert("请输入礼品卡号");
  		}else{
  			var tm = getTotalMoney();
  			$.post(
		    	"${pageContext.servletContext.contextPath}/orderfront/order/useGift",
		    	{"giftId":gift,ranNum:Math.random()},	
			    function(data) {
		    		var result = eval("("+data+")") ;
		    		var state = result.giftTip ;
		    		if("use_success"==state){
		    			if(tm>=totoalCost){
		    	  			alert("支付金额超出订单总金额");
		    	  			return;
		    	  		}else{
		    	  			if(tm+result.giftAmount<totoalCost){
		    	  				$("#total_amount").html(totoalCost-(tm+result.giftAmount));
		    	  			}else{
		    	  				$("#total_amount").html(0);
		    	  			}
		    	  			$("#show_giftId").hide();
			    			$("#show_gift_detail").show();
			    			$("#giftId").val(gift);
			    			$("#gift_value").val(result.giftAmount);
			    			$("#giftCardAmount").html("抵用金额为："+result.giftAmount+"元");
		    	  		}
		    		}else {
		    			alert(state);
		    		}
			    }
			)
  		}
  	}
  //取消礼品卡
  function chooseGiftCard(){
	var gif = $("#gift_value").val();
	var amount = showcost(gif*1);
	$("#total_amount").html(amount);
	$("#giftId").val("");
	$("#gift_value").val("0");
	$("#show_giftId").show();
	$("#show_gift_detail").hide();
	$("#giftCardValue").val("");
  }
  
  function showcost(act){
	  var t = getTotalMoney();
	  var e = ${qm.cartManagerModel.totalMoney };
	  if(t-act>=e){
		  return 0;
	  }else{
		  return e-t+act;
	  }
	  
  }
  
  function checkNum(){
		var reg = new RegExp("^[0-9]*$");
		var nowUse = $("#intergralCount").val();
		if(!reg.test(nowUse)){
			$("#intergralCount").val(0);
	        alert("请输入数字!");
	        return ;
	    }
	}
  	</script>