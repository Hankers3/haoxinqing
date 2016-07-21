<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=4YoG8bce6ygQp3lgXz9U7xaO"></script>


<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div  class="form-horizontal y_setpassd form-validate" >
	 <!-- <form id="address-form1" class="form-horizontal y_setpassd form-validate" action="${pageContext.servletContext.contextPath}/orderfront/order/updateAddress1" method="post">-->
			<input type="hidden" id="longitude" name="longitude" value="${cm.longitude }">
			<input type="hidden" id="latitude" name="latitude" value="${cm.latitude }">
			<input type="hidden" id="uuid" name="uuid" value="${cm.uuid }">
			<input type="hidden" name='city' id='cityId' value="${cm.city }">
			<!--新加项 by hdf  2015.06.18-->
			<input type="hidden" id="regionname" name="region" value="${cm.region}">
	     <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3>修改服务地址</h3>
			 </div>
		
		
			
			
			<div class="form-group">
				<label for="area_alias" class="col-mi-2 control-label">联系人姓名：</label>
				<div class="col-mi-4">
					<input type="text" id="linkName" name="linkName" value="${cm.linkName }" class="form-control">
				</div>
			</div>
			<div class="form-group" >
				<label for="detial_area" class="col-mi-2 control-label"><span class="text2-color">*</span> 详细地址：</label>
				<div class="y_areashow"></div>
				<div class="col-mi-6" id="w_bdapi">
					<div id="l-map" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;" ></div>
					<input type="text" readonly="readonly" size="20" value="${cm.community }" style="width:200px;" class="form-control"/>
					<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
					<span class="w_xiugai" style="color:#3C9DD5;cursor:pointer">修改</span>
					<script>
						$(".w_xiugai").click(function(){
							$("#w_bdapi2").show();
							
						})
					</script>
				</div>
				<div class="col-mi-2" id="w_bdapi2" style="display:none;">
					<input type="hidden" id="add_detail" name="community" value="${cm.community }">
					<input type="text" id="suggestId1"  size="20"  style="width:150px;" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label for="mobel_phone1" class="col-mi-2 control-label"><span class="text2-color">*</span> 手机号码：</label>
				<div class="col-mi-4">
					<input type="text" id="mobel_phone1" name="mobile" value="${cm.mobile }" maxlength="11" class="form-control" placeholder="手机号码必须填写" data-rule-required="true">
				</div>
				
			</div>
			<div class="form-group">
				<label for="area_alias" class="col-mi-2 control-label">地址别名：</label>
				<div class="col-mi-4">
					<input type="text" id="area_alias" name="address" value="${cm.address }" class="form-control">
				</div>
				<p class="fl control-label text-999">设置一个易记的名称，如：“小区名称”,“所在区域名称”</p>
			</div>
			<div class="form-group">
				<div class="col-mi-offset-2 col-mi-10">
					<div class="simple-checkbox"></DIV>
						<input type="checkbox" <c:if test="${cm.isDefault==1 }">  checked="checked" </c:if> class='icheck-me' name="isDefault" id="isDefault" id="c1" data-skin="minimal">
      				<label class="m_inline_in" for="c1">设为默认服务地址</label>
      		
					</div>
				</div>
						
				</div>
	
		<div class="modal-footer">
			<button type="button" class="btn btn-custom update_address">保存服务地址</button>
		</div>
	  <!--</form>-->
	   		</div>
			</div>
</div>
		
	  <!--<script>
	   $(".update_address").click(function(){

			var city = $("#cityId").val();
			var mobel_phone =$("#mobel_phone1").val();
			//alert(mobel_phone);
			
			//var telephone =$("#telephone").val();
			var isAddress = $("#add_detail").val();
			var linkName = $("#linkName").val();
			if(city == ""){
				alert("请选择城市");
				return ;
			}
			if(linkName == ""){
				alert("请填写联系人姓名");
				return ;
			}
			if( isAddress == ""){
				alert("请选择详细地址");
				return ;
			}
			/*
			if(mobel_phone == "" && telephone==""){
				alert("请填写手机号码或者固定号码");
				return ;
			}*/
			if(mobel_phone == "" ){
				alert("请填写手机号码");
				return ;
			}
			var mobile = new RegExp("/^(((1[3-9][0-9]))+\d{8})$/");
			var myreg = /^(((1[3-9][0-9]))+\d{8})$/;
			if(mobel_phone!= ""){
				if(!myreg.test(mobel_phone)){
 	 			 	alert("手机号码不正确");
		 	        return ;
 	 			}
			}
			
			$("#address-form1").submit();
				})

	   
	   </script>-->
	   
	  <script>
	   $(".update_address").click(function(){
     
			var longitude =$("#longitude").val();
			var latitude =$("#latitude").val();
			var uuid = $("#uuid").val();
			var city = $("#cityId").val();
			var linkName = $("#linkName").val();
      var isAddress = $("#add_detail").val();
      var suggestId1 = $("#suggestId1").val();
			var mobel_phone =$("#mobel_phone1").val();	
			var area_alias =$("#area_alias").val();	
      var isDefault =$("#isDefault").val();
      var region_name =$("#regionname").val(); 
       
       

			if(city == ""){
				alert("请选择城市");
				return ;
			}
			if(linkName == ""){
				alert("请填写联系人姓名");
				return ;
			}
			if( isAddress == ""){
				alert("请选择详细地址");
				return ;
			}
		
			if(mobel_phone == "" ){
				alert("请填写手机号码");
				return ;
			}
			var mobile = new RegExp("/^(((1[3-9][0-9]))+\d{8})$/");
			var myreg = /^(((1[3-9][0-9]))+\d{8})$/;
			if(mobel_phone!= ""){
				if(!myreg.test(mobel_phone)){
 	 			 	alert("手机号码不正确");
		 	        return ;
 	 			}
			}
			
      
	   	$.post("${pageContext.request.contextPath }/orderfront/order/updateAddressNEW",
							{
								"region":region_name,
								"community":isAddress,
								"cityId":city,
								"address":area_alias,
								"mobile":mobel_phone,
								"isDefault":isDefault,
								"longitude":longitude,
								"latitude":latitude,
								"link_name":linkName,
								"uuid":uuid,
								ranNum : Math.random()
							},
							function(data) {
								$('#address-update').modal('hide');
							 	$('#address-update').on('hidden.bs.modal',function(){
							 	 backdrop:false
							   });
								$("#address_show").html(data);
							});
					})
	   </script>
	   
	   
	   <script type="text/javascript">
	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}

	var map = new BMap.Map("l-map");
	map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。

	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId1"
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
		$("#regionname").val(_value.district);
		//$("#add_detail").val(myValue);
		$("#add_detail").val(_value.business);
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
<style>
.tangram-suggestion-main{z-index:99999}
</style>

 