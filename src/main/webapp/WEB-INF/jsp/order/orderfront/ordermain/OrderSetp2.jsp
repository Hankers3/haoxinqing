<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>雇我吧 -pc在线下单联系方式</title>
  <meta name="description" content="aebiz b2b2c index">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/font-awesome.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/plugins/icheck/all.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/channel.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/usercenter/css/datepicker.css">
  <%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>	
  <link rel="icon" type="${pageContext.request.contextPath }/static/usercenter/image/png" href="img/icon.png">
</head>
<body>
	<!---头部--->
   <%@ include file="/WEB-INF/jsp/basebusiness/userfront/common/userfronTop.jsp" %>
  <!-----End----->
  <!-----雇我吧-联系方式----->
  <div class="container m_online m_methods">
  	<div class="m_online_pic"></div>
  	<ul class="m_online_tit">
  		<li class="m_color">填写房屋状况</li>
  		<li class="m_li1">联系方式</li>
  		<li class="m_li2">成功提交订单</li>
  	</ul>
  </div>
  <div class="container m_contactin">
  <form class="form-inline2" id="mainForm" action="${pageContext.servletContext.contextPath}/orderfront/order/saveOrder" method="post">
  <input type="hidden" id="address_is_choose" value="false"/>
  <input type="hidden" id="payType_is_choose" value="false"/>
  	<div class="m_service clearfix">
  		<div class="fl m_service_left"><span>服务时间：</span></div>
  		<div class="fl m_service_right">
  			<!--<span>
  			<input style="cursor: pointer;" readonly="readonly" type="text" name="createStartTime" id="createStartTime" class="form-control m_gold_input datepick" value="日期" >
  			</span>-->
  			<span>
  				
          <select    name="createStartTime" id="createStartTime" style="height:35px;width:180px" >
						
					</select>
					
						<script>
					$(function(){
             var date=new Date();
             var daysInMonth = new Array([0],[31],[28],[31],[30],[31],[30],[31],[31],[30],[31],[30],[31]);  
					   var strYear = date.getFullYear();    
					   var strDay = date.getDate();    
					   var strMonth = date.getMonth()+1; 
					   if(strYear%4 == 0 && strYear%100 != 0){  
					      daysInMonth[2] = 29;  
					   }  
					   strDay = daysInMonth[strMonth] >= strDay ? strDay : daysInMonth[strMonth];  
					  
					   if(strMonth<10)    
					   {    
					      strMonth="0"+strMonth;    
					   }  
					   if(strDay<10)    
					   {    
					      strDay="0"+strDay;    
					   }  
					   datastr = strYear+"-"+strMonth+"-"+strDay;  
					  
					  
            $("select").append($("<option value="+datastr+">"+datastr+"</option>"));
           
            var strdaymore=date.getDate(); 
              
            var strdaymore1=1;
            
            var strdaymore2=1;
            
            var strMonthmore1=1;
                        
            var strMonthmore = date.getMonth()+1;
          
                  	
            for(var i=1;i<=6;i++){
            	strdaymore=strdaymore+1;
            	
             	if(strdaymore<10){
	            	  strdaymore2="0"+strdaymore; 
	            	  datastr = strYear+"-"+strMonth+"-"+strdaymore2;
            	 }else if(strdaymore>daysInMonth[strMonthmore]){
            	  	//日期
            	  	strdaymore1=Math.abs(daysInMonth[strMonthmore]-strdaymore);
            	  
				            if(strdaymore1<10)    
									   {    
									      strdaymore1="0"+strdaymore1;    
									   }  
            	  	//月份
                  strMonthmore1=strMonthmore+1;
									   if(strMonthmore1<10){    
									      strMonthmore1="0"+strMonthmore1;    
									   }  
									datastr = strYear+"-"+strMonthmore1+"-"+strdaymore1;   
            	 	}else{
            	 		 datastr = strYear+"-"+strMonth+"-"+strdaymore;
            	 		
            	 		}
           
            
             $("select").append($("<option value="+datastr+">"+datastr+"</option>"));
             
              }
          }) 
                                
          </script>
				</span>
  			
  			
  			
  			
  			
  			<span class="m_xia m_rel">
  				<input style="cursor: pointer;" value="小时" class="form-control m_form" name="timeHour" id="timeHour" readonly="readonly">
					<ul class="m_abs m_inthe_xia" id="hour_id">
				  </ul>
  				
			</span>
			<span class="m_xia m_rel">
				<input style="cursor: pointer;" value="分钟" class="form-control m_form" name="timeMinute" id="timeMinute" readonly="readonly">
						<ul class="m_abs m_inthe_xia" id="minute_id">
					  </ul>
			</span>		  
  		</div>
  	</div>
  	<!-----雇我吧-联系人信息 ---------->
  	<div class="m_lx clearfix" id="address">
  		<%@include file="address.jsp" %>
  	</div>
  	<!-----雇我吧-联系人信息 end---------->
  	<!-----雇我吧-备选联系人start---------->
  	<div class="m_special clearfix">
  		<div class="fl m_service_left"><span>备选联系人信息：</span></div>
  		<div class="fl m_service_right">
  			<input type="text" value="备选联系人姓名"  onclick="if(this.value == '备选联系人姓名' || this.value == '备选联系人姓名') {this.value = ''} " id="alternative" name="alternative" class="form-control y_mb10" maxlength="20">
  			<input type="text" value="备选联系人电话"  onclick="if(this.value == '备选联系人电话' || this.value == '备选联系人电话') {this.value = ''} " id="alternativePhone" name="alternativePhone" class="form-control" maxlength="11" onblur="javascript:CheckMobile();">
  		</div>
  	</div>
  	<!-----雇我吧-备选联系人 end---------->
  	<!-----雇我吧-其它特殊要求---------->
  	<div class="m_special clearfix">
  		<div class="fl m_service_left"><span>其他特殊要求：</span></div>
  		<div class="fl m_service_right">
  			<textarea id="tbContent" name="tbContent" maxlength="100"></textarea>
  		</div>
  	</div>
  	<!-----雇我吧-其它特殊要求 end---------->
  	<!-----雇我吧-支付方式---------->
  	<div class="m_ment clearfix">
  		<div class="fl m_service_left"><span>支付方式：</span></div>
  		<div class="fl m_service_right" id="choosePayType" style="display:none">
	  		<ul>
				<li id="payTypeId"></li>
	  		</ul>
	  		<a href="javascript:choosePayType();" class="btn btn-custom">选择支付方式</a>
	  	</div>
  		<div class="fl m_service_right" id="showPayType">
	  		<ul>
					<li><input type="radio" id="c3" class='icheck-me' name="same_payType" data-skin="minimal" value="0"><label class='inline' for="c">线上支付</label> <strong>(在线支付有机会赢取红包)</strong></li>
					<li><input type="radio" id="c3" class='icheck-me' name="same_payType" data-skin="minimal" value="1"><label class='inline' for="c">线下支付</label></li>
	  		</ul>
	  		<a href="javascript:savePayType();" class="btn btn-custom">保存支付方式</a>
	  	</div>
  	</div>
  	<!-----雇我吧-支付方式 end---------->
  	<!-----雇我吧-使用积分 优惠劵 ---------->
  		<%@include file="payTypes.jsp" %>
  	<!-----雇我吧-使用积分 优惠劵  end---------->
  	</form>
  </div>
  <div class="container m_fkje">
  	应付金额：<label>￥<span id="total_amount">${qm.cartManagerModel.totalMoney }</span></label>
  	<a href="javascript:void(0);" class="btn btn-custom2">提交订单</a>
  </div>
  <!-----雇我吧-联系方式 end---------->
  <!-----雇我吧首页底部----->
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
  <!-----雇我吧首页底部 end----->
  <script>
  function removeAddress(addressId){
		if(confirm("确认要删除该地址吗")){
			$.post(
					"${pageContext.servletContext.contextPath}/orderfront/order/removeAddress",
					{
						"addressId":addressId,"customerId":"${customer.uuid}","cityId":"${qm.cartManagerModel.cityId }",
						ranNum : Math.random()
					},
					function(data) {
						$("#address").html(data);
					});
		}
	}
  //保存支付方式
  	function savePayType(){
  		var checkIds="";
		$("input[name='same_payType']:radio").each(function(){				
	        if($(this).is(":checked")){        	
	            checkIds = $(this).val() ;  
	            $("#payType_is_choose").val("true");
	            $("#showPayType").hide();
	            $("#choosePayType").show();
	            if("0"==checkIds){
	            	$("#payTypeId").html("<label class='inline' for='c'>线上支付</label> <strong>(在线支付有机会赢取红包)</strong>");
	            }else if("1"==checkIds){
	            	$("#payTypeId").html("<label class='inline' for='c'>线下支付</label>");
	            }
	            
	        }
	    	})
  	}
  	//选择支付方式
  	function choosePayType(){
  		$("#choosePayType").hide();
  		$("#showPayType").show();
  		$("#payType_is_choose").val("false");
  	}
	
	function checkCanPush1(){
			var  addressId = $("#addressId").val();
			var createStartTime = $("#createStartTime").val();
			var createStartTime = $("#createStartTime").val();
  		var timeHour = $("#timeHour").val();
			var timeMinute =$("#timeMinute").val();
  		$.post("${pageContext.servletContext.contextPath}/orderfront/order/checkCanPush",
  			{
  				"addressId":addressId,
  				"createStartTime":createStartTime,
  				"timeHour":timeHour,
  				"timeMinute":timeMinute,
  				ranNum:Math.random()
  			},	
			  	function(data) {
			  		if(data=="true"){
			  			mainForm.submit();
			  		}else{
			  				alert("附近家政员不满足订单的人数需求，请重新下单");
			  		}
		    		
			    }
			);
  	}
  	
  	
  	
  	
  	$(".btn-custom2").click(function (){
  		var address = $("#address_is_choose").val();
  		var payType = $("#payType_is_choose").val();
  		var timeHour = $("#timeHour").val();
			var timeMinute =$("#timeMinute").val();
			
			if(""==timeHour ||"小时"==timeHour){
				alert("请选择服务时间");
				return ;
			}
			if(""==timeMinute ||"分钟"==timeMinute){
				alert("请选择服务时间");
				return ;
			}
  		if("false"==address){
  			alert("请选择联系人信息");
  			return ;
  		}
  		if("false"==payType){
  			alert("请选择支付方式");
  			return ;
  		}
  		var tc = $("#tbContent").val();
  		if("其他特殊要求请备注"==tc){
  			$("#tbContent").val("");
  		}
  		var alternative = $("#alternative").val();
  		if("备选联系人姓名"==alternative){
  			$("#alternative").val("");
  		}
  		var alternativePhone = $("#alternativePhone").val();
  		if("备选联系人电话"==alternativePhone){
  			$("#alternativePhone").val("");
  		}
      checkCanPush1();
  	
  	})
  </script>
  <script>
		//下拉菜单
	$('.m_gold_input').click(function(){
		$("#timeHour").val("小时");
		$("#timeMinute").val("分钟");
	})
	$('.m_xia .m_form').click(function(){
		 var time = $("#createStartTime").val();
		  if("日期"==time || ""==time){
			  alert("请选择日期");
			  return ;
		  }
		  $.post(
		    	"${pageContext.servletContext.contextPath}/orderfront/order/showTime",
		    	{"time":time,ranNum:Math.random()},	
			    function(data) {	       
		    		var result = eval("("+data+")") ;
		    		var hour = result.service_h ;
		    		var minute = result.service_m ;
		    		var beginTime="-1";
		    		if("30"==minute){
		    			beginTime=hour;
		    		}
		    		if("false" == hour || "false" == minute){
		    			$("#timeHour").val("小时");
		    			$("#timeMinute").val("分钟");
		    			alert("无法下当前时间订单");
		    		}else{
		    			$('#hour_id').html("");
		    			for(var i=parseInt(hour);i<=18;i++){
		    				$('#hour_id').append('<li onclick="showTwo('+i+','+beginTime+')">'+i+'</li>')
		    		     }
		    		}
			    }
			);
		  
		$(this).next('.m_inthe_xia').toggle();
	});
	
	$('.m_inthe_xia li').click(function(){
		$(this).parent().hide();
    $(this).parent().prev('input').val($(this).text());
		});
		$(".m_xia").hover(function () {
      $(".m_inthe_xia li").parent().hide();
   });
		
   //文本域提示字
   $("#tbContent").val("其他特殊要求请备注");
      $("#tbContent").bind("focus",function(){
       $("#tbContent").val("");
     });
     //使用优惠劵
     $(".m_use_p").click(function(){
     	 $(this).parent().next().slideToggle(300);
     	 $(this).children(".fa").toggleClass("fa-minus-circle").addClass("fa-plus-circle");
     	});
     	
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
  	$(document).ready(function() {
  		$("#address_is_choose").val("false");
  		$("#payType_is_choose").val("false");
  		$("#intergral_value").val("0");
  		$("#coupon_value").val("0");
  		$("#gift_value").val("0");
  		$("#balance_value1").val("0");
  		var msg = "${error_msg}";
  		if("address_null" == msg){
  			alert("请选择服务地址");
  		}else if("pay_null" == msg){
  			alert("请选择支付方式");
  		}else if("product_null" == msg){
  			alert("请填写房屋状况");
  			window.location.href="${pageContext.servletContext.contextPath}/orderfront/order/toCreateOrder/110100";
  		}
  	})
  	
  	
  	function showTwo(cur,min){
		$("#timeHour").val(cur);
		$(".m_inthe_xia li").parent().hide();
		$('#minute_id').html("");
		$("#timeMinute").val("分钟");
		if(cur!=min){
			$('#minute_id').append('<li onclick="choosemi(00)">00</li>');
		}
		$('#minute_id').append('<li onclick="choosemi(30)">30</li>');
  	}
  	function choosemi(m){
  		if(0==m){
  			$("#timeMinute").val("00");
  		}else{
  			$("#timeMinute").val(m);
  		}
  		$(".m_inthe_xia li").parent().hide();
  	}
	</script>
	
	<script>
		 function CheckMobile() { 
			var mail=$("#alternativePhone").val();
			//alert("111");
			var filter  = /^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$/; 
			if (filter.test(mail)){ 
				 return true; 
			}else {
				 alert('您填写的手机号不正确！'); 
				 return false;
				 }
			}					
	</script>
	
</body>
</html>