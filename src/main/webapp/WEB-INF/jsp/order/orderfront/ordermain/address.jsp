<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>                        
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=4YoG8bce6ygQp3lgXz9U7xaO"></script>    
<style>
.y_areashow{float:left;margin:0 -5px 0 15px;line-height:34px;}
.modal-dialog{width:670px;}
.y_setpassd{font-family:"Microsoft Yahei";}
.y_setpassd h3{font-weight:normal; font-size: 18px;}
.y_setpassd label{font-weight:normal;  }
.y_setpassd .form-control{width:90%;}
.y_setpassd .simple-checkbox input ,.simple-checkbox label{vertical-align: middle; margin:0px;}
.y_setpassd .btn{border-radius:3px;}
.tangram-suggestion-main{z-index:99999}
</style>
<div class="fl m_service_left"><span>联系人信息：</span></div>
<div class="fl m_service_right" id="addId" style="display:none">
	<ul>
		<li id="showAdd"></li>
	</ul>
	<a href="javascript:chooseAddress();" class="btn btn-custom">选择联系人</a>
</div>
<div class="fl m_service_right" id="addListId">
	<ul id="address_show">
		<c:forEach items="${addressList }" var="address">
		<li><input type="radio" name="check_address" value="${address.uuid}"  <c:if test="${address.isDefault == '1'}">checked="checked"</c:if>  id="c3" class='icheck-me' name="same" data-skin="minimal"><label class='inline' for="c"> 
		 	<c:choose>
		 		<c:when test="${empty address.linkName }">${address.customerName }</c:when>
		 		<c:otherwise>${address.linkName }</c:otherwise>
		 	</c:choose>
		  </label>
		  <b>|</b>${address.mobile }<b>|</b>${address.cityName }${address.region }${address.community }${address.address }
		    <a href="javascript:removeAddress('${address.uuid }');" class="m_hide">删除</a>
		  	<b>|</b>
			  <a  href="#address-update" data-toggle="modal" onclick="javascript:toUpdateAddress('${address.uuid }');" class="m_hide">编辑</a>
			  </li>
		</c:forEach>
	</ul>
	 <a  href="#address-add" data-toggle="modal"class="btn btn-custom">新增联系人</a>
	<a href="javascript:save();" class="btn btn-custom">保存联系人信息</a>
</div>
 <!--新增服务地址弹出框-->
	<div id="address-add" class="modal fade">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div  class="form-horizontal y_setpassd form-validate" >
					<input type="hidden" id="longitude" name="longitude" value="0">
					
					<input type="hidden" id="addressId" name="addressId" >
					
					<input type="hidden" id="latitude" name="latitude" value="0">
					<input type="hidden" id="region_name" name="region_name">
					<input type="hidden" id="community_name" name="community_name">
					<input type="hidden" id="cityId" value="${qm.cartManagerModel.cityId }"/>
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3>新增联系人信息</h3>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="area_alias" class="col-mi-2 control-label"><span class="text2-color">*</span>联系人姓名：</label>
							<div class="col-mi-4">
								<input type="text" id="link_name" name="link_name" class="form-control" maxlength="20">
							</div>
						</div>
						<div class="form-group" >
							<label for="detial_area" class="col-mi-2 control-label"><span class="text2-color">*</span> 详细地址：</label>
							<div class="y_areashow"></div>
							<div class="col-mi-6" id="w_bdapi">
								<div id="l-map" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;" ></div>
								<input type="text" id="suggestId" name="address" size="20" value="" style="width:250px;" class="form-control"/>
								<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="area_alias" class="col-mi-2 control-label">具体名称：</label>
							<div class="col-mi-4">
								<input type="text" id="address_id" name="address" class="form-control">
							</div>
							<p class="fl control-label text-999">设置一个易记的名称，如：“小区名称”，“所在区域名称”</p>
						</div>
						<div class="form-group">
							<label for="mobel_phone" class="col-mi-2 control-label"><span class="text2-color">*</span> 手机号码：</label>
							<div class="col-mi-4">
								<input type="text" id="mobel_phone" name="mobile"  maxlength="11" class="form-control" placeholder="手机号码必须填写" data-rule-required="true" >
							</div>
							<!--
							<p class="fl control-label">或 固定电话</p>
							<div class="col-mi-4">
								<input type="text" id="telephone" name="telephone" class="form-control" placeholder="区号-电话号码" maxlength="20">
							</div>-->
						</div>
						<div class="form-group">
							<div class="col-mi-offset-2 col-mi-10">
								<div class="simple-checkbox">
									<input type="checkbox" class='icheck-me' name="isDefault" id="isDefault" id="c1" data-skin="minimal">
	        				<label class="m_inline_in" for="c1">设为默认服务地址</label>
								</div>	
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-custom save_address">保存服务地址</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--新增服务地址弹出框 end-->
	
	
	<!--编辑服务地址弹出框-->
	<div id="address-update" class="modal fade">
		<div id="showAU"></div>
	</div>
	
<script>
$('#address-add .save_address').on("click",function(){
	var region_name = $("#region_name").val();
	var community_name = $("#community_name").val();
	var cityId = $("#cityId").val();
	var address =$("#suggestId").val();
	var mobel_phone =$("#mobel_phone").val();
	//Var telephone =$("#telephone").val();
	var address_id =$("#address_id").val();
	var isDefault =$("#isDefault").val();
	var longitude =$("#longitude").val();
	var latitude =$("#latitude").val();
	var link_name = $("#link_name").val();
	if(link_name == ""){
		alert("请填写联系人姓名");
		return ;
	}
	if(address == ""){
		alert("请选择详细地址");
		return ;
	}
	/*
	if(mobel_phone == "" && telephone==""){
		alert("请填写手机号码或者固定号码");
		return ;
	}*/
	
	if(mobel_phone == ""){
		alert("请填写手机号码");
		return ;
	}
	var tel = new RegExp("^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$");
	var myreg = /^(((1[3-9][0-9]))+\d{8})$/;
		if(mobel_phone != ""){
			if(!myreg.test(mobel_phone)){
 			 	alert("手机号码不正确");
	 	        return ;
 			}
		}
	
	/*if(telephone != ""){
		if(!tel.test(telephone)){
 	        alert("座机号码不正确");
 	        return ;
 	    }
	}*/
	
	$.post("${pageContext.request.contextPath }/usercenter/customer/checkCount",
			{"cityId":cityId,
				ranNum : Math.random()
			},
			function(data) {
				if("already_five" == data){
					alert("您在同一城市最多只能添加5条地址");
					return ;
				}else{
					$.post("${pageContext.request.contextPath }/orderfront/order/addAddress",
							{
								"region":region_name,
								"community":community_name,
								"cityId":cityId,
								"address":address_id,
								"mobile":mobel_phone,
								"isDefault":isDefault,
								"longitude":longitude,
								"latitude":latitude,
								"link_name":link_name,
								ranNum : Math.random()
							},
							function(data) {
								$('#address-add').modal('hide');
							 	$('#address-add').on('hidden.bs.modal',function(){
							 	 backdrop:false
							   });
								$("#address_show").html(data);
							});
				}
			});
   });
 
 


	function save(){
		var checkIds="";
		$("input[name='check_address']:radio").each(function(){				
	        if($(this).is(":checked")){        	
	            checkIds = $(this).val() ;  
	        }
	    	})
	    if(checkIds ==""){
	    	alert("请选择联系人地址");
	    	return ;
	    }else{
	    	$.post(
			    	"${pageContext.servletContext.contextPath}/orderfront/order/getAddress",
			    	{"addressId":checkIds,ranNum:Math.random()},	
				    function(data) {
			    		var result = eval("("+data+")") ;
			    		var linkName = result.linkName;
			    		if(linkName == ""){
			    			linkName =result.customerName;
			    		}
			    		$("#address_is_choose").val("true");
			    		$("#addListId").hide();
			    		$("#addId").show();
			    		$("#addressId").val(checkIds);
			    		$("#showAdd").html("<li><label class='inline' for='c'>"+linkName +"</label><b>|</b>"+result.mobile+"<b>|</b>"+result.cityName+result.region+ result.community+ result.address +"</li>");
				    }
				)
	    }
		
		
	}
	//选择联系人
	function chooseAddress(){
		$("#addListId").show();
		$("#addId").hide();
		$("#address_is_choose").val("false");
		
	}
	
	
	 function toUpdateAddress(uuid) {
			$.get(
				"${pageContext.servletContext.contextPath}/orderfront/order/updateAddress",
			{
				"addressId":uuid,
				ranNum : Math.random()
			},
			function(data) {
				$("#showAU").html(data);
		});
		}
	
</script>
 <script type="text/javascript">
	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}

	var map = new BMap.Map("l-map");
	map.centerAndZoom("${cityName}",12);                   // 初始化地图,设置城市和地图级别。

	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
		,"location" : map
	});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		$("#region_name").val(_value.district);
		$("#community_name").val(_value.business);
		setPlace();
	});

	function setPlace(){
		map.clearOverlays(); 
   //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
			map.panTo(pp);
			$("#longitude").val(pp.lng);
			$("#latitude").val(pp.lat);
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		
		local.search(myValue);
	}
</script>