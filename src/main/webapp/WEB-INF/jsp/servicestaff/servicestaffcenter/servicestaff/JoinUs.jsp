<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
  <meta charset="utf-8">
  <title>雇我吧 -加入我们</title>
  <meta name="description" content="aebiz b2b2c index">
  <!--[if IE]> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <![endif]-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
  <%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/import/ListImport.jsp"%>	
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/style.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/font-awesome.min.css">
  <link rel="icon" type="image/png" href="${pageContext.servletContext.contextPath}/static/usercenter/img/icon.png">
  <!-- 字体图标ie7兼容性处理 -->
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="css/font-awesome-ie7.min.css">
	<![endif]-->
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/global.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/channel.css">
  
  <%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/import/ListImportJs.jsp"%>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/eakroko.js"></script> 
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/jquery.dataTables.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script> 	
  <!--- jQuery 
  <script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery-1.11.1.min.js"></script>-->
 <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=4YoG8bce6ygQp3lgXz9U7xaO"></script>
 <script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>	
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 

</head>
<body>
	<!---头部--->
	<%@ include file="/WEB-INF/jsp/basebusiness/userfront/common/userfronTop.jsp" %>
  <!-----End----->
  
  <!-----雇我吧-加入我们介绍----->
  <div class="container m_join">
  	<h2>申请成为雇我吧保洁员</h2>
  	<div class="m_joinin">
  		<div class="m_joinin_pic"></div>
  		<ul>
  			<li>提交申请</li>
  			<li class="m_li2">现场面试</li>
  			<li class="m_li3">获得保洁员账号</li>
  			<li class="m_li4">完善资料</li>
  			<li class="m_li5">入职培训</li>
  			<li class="m_li6">开始接单工作</li>
  		</ul>
  		<div class="m_tit">我们是一家移动互联网创业公司，我们是一支拥有丰富的互联网行业经验的创业团队。我们崇尚自由、不拘小节，我们关注用户、追求极致，<br/>我们充满激情、快速迭代。在追逐梦想的路上，我们需要更多的兄弟姐妹加入，在移动互联网时代需要有我们刻下的烙印。<br/>
				目前公司正处于快速发展时期，在这里只要你愿意付出，公司一定会给予一个不错的回报。
         <p>我们将为您提供：1. 扁平的管理结构，谁都可以表达自己的看法；2. 和谐的同事关系，知识分享可以没有任何保留；3. 充满挑战的机会，能者多得。</p>
      </div>
      <div class="m_jo_pic"><img src="${pageContext.servletContext.contextPath}/static/usercenter/img/adimg/m_jr2.jpg"></div>
  	</div>
  </div>
  <!-----雇我吧-加入我们介绍 end----->
  <!-----雇我吧-加入我们填写信息模块----->
  <div class="container m_the">
  	<div class="m_the_tit">请认真填写以下信息申请成为雇我吧保洁员，<br/>资料审核通过后会有专人与您联系面试事宜，赶紧行动起来吧！</div>
  	<div class="row m_thein">
  		
  		<form id="mainForm" action="${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/addApplyInfo" method="post" commandName="m" class='form-horizontal form-bordered form-validate' enctype="multipart/form-data" >
  		<input type="hidden" name="servicestaffinfoModel.longitude" id ="longitude"/>
  		<input type="hidden" name="servicestaffinfoModel.latitude" id ="latitude"/>

  		<div class="col-mi-6" style="height:530px">
  			<ul>
  				<li>
		  			<div class="fl m_left"><span style="color:red">*</span><aebiz:showTitle titleId="servicestaff.m.password"/></div>
		  			<div class="fl m_right">
		  				  <div class="col-sm-10"  style="padding-left:0">
										<input type="password" id="password" name="servicestaffModel.password"  class="form-control" data-rule-required="true" data-rule-minlength="6" data-rule-maxlength="20">
								</div>
		  			</div>
	  		  </li>
  				<li>
		  			<div class="fl m_left"><span style="color:red">*</span>&nbsp;姓名：</div>
		  			<div class="fl m_right">
		  				  <!-- <input   value="请填写您的姓名，如：张三" class="form-control">-->

		  					<input name="servicestaffinfoModel.realName" placeholder="请填写您的姓名，如：张三" class='form-control' id="name1" maxlength="12">

		  					<input type="hidden" name="nameHidden" value="true">
		  			</div>
	  		  </li>
	  		  <li>
		  			<div class="fl m_left"><span style="color:red">*</span>籍贯：</div>
		  			<div class="fl m_right">
		  				  <input name="servicestaffinfoModel.nativePlace" placeholder="请填写您的籍贯，如：北京" class="form-control" id="nativePlace">
		  			</div>
	  		  </li>
	  		  <li>
		  			<div class="fl m_left"><span style="color:red">*</span>&nbsp;联系电话：</div>
		  			<div class="fl m_right">
		  				 <input name="servicestaffModel.mobile"  placeholder="请填写您的联系电话，如：13888888888" class="form-control" id="mobile">
		  				 <input type="hidden" name="mobileHidden" value="true">
		  			</div>
	  		  </li>
	  		  <li>
		  			<div class="fl m_left"><span style="color:red">*</span>婚育状况：</div>
		  			<div class="fl m_right">
		  				 <div class="col-sm-10">
		  				 	
			  				 	<label class="radio-inline">
									  	<input type="radio" name="servicestaffinfoModel.maritalStatus" id="c2" value="1" > 已婚
									</label>
									<label class="radio-inline">

									  <input type="radio" name="servicestaffinfoModel.maritalStatus" id="c2" value="2" class="icheck-me" data-skin="square" data-color="blue"> 未婚

									</label>
									<label class="radio-inline">

									  <input type="radio" name="servicestaffinfoModel.maritalStatus" id="c4" value="3" class="icheck-me"  data-skin="square" data-color="blue" checked/> 保密

									</label>
								
								<!--
								<div class="col-sm-3">
									<div class="check-line">
										<input type="radio" name="servicestaffinfoModel.maritalStatus" id="c2" value="1" class="icheck-me" data-skin="square" data-color="blue">
										<label class='inline' for="c5">已婚</label>
									</div>
								</div>
								
								<div class="col-sm-3">
									<div class="check-line">
										<input type="radio" name="servicestaffinfoModel.maritalStatus" id="c3" value="2" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c6">未婚</label>
									</div>
								</div>
								
								<div class="col-sm-3">
									<div class="check-line">
										<input type="radio" name="servicestaffinfoModel.maritalStatus" id="c4" value="3" class="icheck-me"  data-skin="square" data-color="blue"/>
										<label class='inline' for="c7"><aebiz:showTitle titleId="servicestaffinfo.m.secret"/></label>
									</div>
								</div>
								-->
							</div>
		  				 		  						  				 
		  			</div>
	  		  </li>
	  		 <li>
		  			<div class="fl m_left"><span style="color:red">*</span>健康状况：</div>
		  			<div class="fl m_right">
		  				 <input  name="servicestaffinfoModel.healthCondition" placeholder="请填写您的健康状况，如：良好" class="form-control" id="healthCondition">
		  			</div>
	  		  </li>
	  		  <li>
  			    <div class="fl m_left"><span style="color:red">*</span>现住地址：</div>
  			    <div class="fl m_right">
  			    	 <input  name="servicestaffinfoModel.address"  id="suggestId"  placeholder="请填写您目前的详细住址：如：北京市海淀区皂君东里5号楼2门301室" class="form-control" >
  			    </div>
  			  </li>
  			  
  			  <li class="m_li" style="margin-left: 30px;">
  			  	<span style="color:red">*</span>&nbsp;为必填项
  			  </li>
  			  <li class="m_li" style="margin-left: 30px;">
  			  	以上资料请放心填写，雇我吧将严格保护您的隐私。
  			  </li>
  			</ul>
  		</div>
  		<div class="col-mi-6" style="height:530px">
  			<ul> 
  				<li>
		  			<div class="fl m_left"><span style="color:red">*</span><aebiz:showTitle titleId="servicestaff.m.confirmPwd"/></div>
		  			<div class="fl m_right">
							<div class="col-sm-10" id="confirmPwdError" style="padding-left:0;">
								<input type="password"  id="confirmPwd" name="confirmPwd" class="form-control" data-rule-required="true">
							</div>
		  				
		  			</div>
	  		  </li>
  				<li>
		  			<div class="fl m_left"><span style="color:red">*</span>性别：</div>
		  			<div class="fl m_right">
							<div class="col-sm-10">
									<label class="radio-inline">
									  <input type="radio" name="servicestaffinfoModel.sex" id="c5" value="1" > 
									  <aebiz:showTitle titleId="servicestaffinfo.m.female" />
									</label>
									<label class="radio-inline">

									  <input type="radio" name="servicestaffinfoModel.sex" id="c5" value="2" class="icheck-me" data-skin="square" data-color="blue">

									  <aebiz:showTitle titleId="servicestaffinfo.m.male"/>
									</label>
									<label class="radio-inline">

									  <input type="radio" name="servicestaffinfoModel.sex" id="c6" value="2" class="icheck-me" data-skin="square" data-color="blue" checked/>

									  <aebiz:showTitle titleId="servicestaffinfo.m.secret"/>
									</label>
								<!--
								<div class="col-sm-3">
									<div class="check-line">
										<input type="radio" name="servicestaffinfoModel.sex" id="c5" value="1" class="icheck-me" data-skin="square" data-color="blue">
										<label class='inline' for="c5"><aebiz:showTitle titleId="servicestaffinfo.m.female" /></label>
									</div>
								</div>
								
								<div class="col-sm-3">
									<div class="check-line">
										<input type="radio" name="servicestaffinfoModel.sex" id="c6" value="2" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c6"><aebiz:showTitle titleId="servicestaffinfo.m.male"/></label>
									</div>
								</div>
								
								<div class="col-sm-3">
									<div class="check-line">
										<input type="radio" name="servicestaffinfoModel.sex" id="c7" value="3" class="icheck-me"  data-skin="square" data-color="blue"/>
										<label class='inline' for="c7"><aebiz:showTitle titleId="servicestaffinfo.m.secret"/></label>
									</div>
								</div>
								-->
							</div>
		  				
		  			</div>
	  		  </li>
	  		  <li>
		  			<div class="fl m_left"><span style="color:red">*</span>出生年月：</div>
		  			<div class="fl m_right">
		  				<input  name="servicestaffinfoModel.birthday"  class="form-control datepick" placeholder="请填写您的出生年月，如：1981年3月1日">
		  			</div>
	  		  </li>
	  		  <li>
		  			<div class="fl m_left"><span style="color:red">*</span>学历：</div>
		  			<div class="fl m_right">
		  				 
		  				 <div class="col-sm-4" style="padding-left:0;"> 
									<select name="servicestaffinfoModel.education" class='select2-me form-control' id="education">								
										<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
										<c:forEach items="${educationList}" var="dpp">	
											<option value="${dpp.name}">${dpp.value}</option>
										</c:forEach>										
									</select>
								</div>
		  				 
		  			</div>
	  		  </li>
	  		 
	  		  <li>
		  			<div class="fl m_left m_leftin"><span style="color:red">*</span>是否有职业病/<br/>精神病/传染病史：</div>
		  			<div class="fl m_right m_rightin">
		  				 <div class="col-sm-10">
								
								 <label class="radio-inline">

									  <input type="radio" name="servicestaffinfoModel.industrialDsease" id="c8" value="无" class="icheck-me" data-skin="square" data-color="blue" checked> 无

									</label>
									<label class="radio-inline">
									  <input type="radio" name="servicestaffinfoModel.industrialDsease" id="c9" value="有" /> 有
									</label>
									
									<!--
								<div class="col-sm-3">
									<div class="check-line">
										<input type="radio" name="servicestaffinfoModel.industrialDsease" id="c8" value="无" class="icheck-me" data-skin="square" data-color="blue">
										<label class='inline' for="c5">无</label>
									</div>
								</div>
								
								<div class="col-sm-3">
									<div class="check-line">
										<input type="radio" name="servicestaffinfoModel.industrialDsease" id="c9" value="有" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c6">有</label>
									</div>
								</div>
								-->
							</div>
		  				 
		  			</div>
	  		  </li>
	  		  
	  		  
	  		   <li>
				  	<!--会员头像-->
						<div class="member_tabin" style="border:none;background:none" >
								<div class="form-group" style="border:none;background:none">
									<div class="fl m_left "><span style="color:red">*</span>个人生活照：</div>
										<div class="col-sm-8" style="border:none;background:none">
											<div class="fileinput <c:choose><c:when test="${empty m.servicestaffinfoModel.image}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
												<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 150px; height: 150px;">
													<c:if test="${!empty m.servicestaffinfoModel.image}"><img src="${m.servicestaffinfoModel.imgUrl}" /></c:if>
												</div>
												<div>
													<span class="btn btn-default btn-file">
														<span class="fileinput-new">选择图片</span>
														<span class="fileinput-exists">更改图片</span>
														<input type="file" name="imgFile" id="image"/>
													</span>
													<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
												</div>
											</div>
										</div>
						</div>
						<!--会员头像 end-->
	  		  </li>
	  		 
	  		 
	  		  <li>
	  		  	<!--设置密码-->
	  		  	
	  		  	<!--设置密码 end-->
	  		  </li>
	  		  
	  		  
  			</ul>
  		</div>
  		
  		
  		<div class="col-mi-12 m_address">
  			<div id="l-map" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;" ></div>
				<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
  			<ul>
  				
  			  
  			</ul>
  	  </div>
  	  
  	  <div class="m_jr_btn">
  	  	<input type="button" onclick="javascript:toApply();" class="btn submit btn-primary" value="提交申请">
  	  </div>
  	  
  	  </form>
  	</div>
  </div>
  <!-----雇我吧-加入我们填写信息模块  end----->
  
 
  <!-----雇我吧首页底部----->
		<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
  <!-----雇我吧首页底部 end----->
  <script>
	  function toApply(){
	  	 
	  	  
	    	var nameError=$("input[name='nameHidden']").val();
				var mobileError=$("input[name='mobileHidden']").val();
				
				var mobile=$("#mobile").val();
				var name1=$("#name1").val();
				var address=$("#suggestId").val();
				//alert(address);
				
			
			 var cl = $("input[name='servicestaffinfoModel.maritalStatus']:checked").length;
			
					
				var b1 = $("input[name='servicestaffinfoModel.sex']:checked").length;
				
					
				var a1 = $("input[name='servicestaffinfoModel.industrialDsease']:checked").length;
				
			
			
			
			
				var image=$("#image").val();
				//alert(image);
				var nativePlace=$("#nativePlace").val();
				//alert(nativePlace);
				var healthCondition=$("#healthCondition").val();
				//alert(healthCondition);
				var education=$("#education").val();
				//alert(education);
				
			 
				

				if(nameError=="true"&&mobileError=="true"&&passwordError=="true"&&birthdayError=="true"&&image!=""&&address!=""&&nativePlace!=""&&healthCondition!=""&&education!=""){

						if(name1!=""&&mobile!=""&&address!=""){
	
							$("#mainForm").submit();
						}else{
							
							
							alert("请完善全部必填项！");
							}

				}else{
					
					alert("请完善并正确填写全部必填项！");
					
					}
			 
			}
			
			//验证家政员手机号是否存在
	$("input[name='servicestaffModel.mobile']").blur(function(){
			$("span[name='servicestaffModel.mobilespan']").remove();
			var mobile=$("input[name='servicestaffModel.mobile']").val();
			if(mobile!=''){
				var url="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/checkMobile";
				$.get(url,{mobile:mobile,ranNum:Math.random()},function(data){
					if(data=="true"){
						$("input[name='servicestaffModel.mobile']").after("<span name='mobilespan'><font color='red'><aebiz:showTitle titleId="ServicestaffModel.mobile.existed" /></font></span>");
						$("input[name='mobileHidden']").val("false");
					}else{
						$("input[name='mobileHidden']").val("true");
						$("span[name='mobilespan']").remove();
					}
				});
			}
		});
		
		
		
		 //验证家政人员名称是否存在
		$("input[name='servicestaffModel.serviceStaffName']").blur(function(){
			$("span[name='namespan']").remove();
			var serviceStaffName=$("input[name='servicestaffModel.serviceStaffName']").val();
			var nameReg=/^[\u4E00-\u9FA5\uF900-\uFA2D\w]{2,20}$/;
			if(serviceStaffName.replace(/([\u4E00-\u9FA5\uf900-\ufa2d])/g,'aa').length>20 ||serviceStaffName.replace(/([\u4E00-\u9FA5\uf900-\ufa2d])/g,'aa').length<4 ){
				$("input[name='nameHidden']").val("false");
				$("#nameTip").empty();
				$("#nameTip").append("<font color='red'><aebiz:showTitle titleId="servicestaff.add.nameTip"/></font>");
				return ;
			}else{
				if(serviceStaffName!=''&&nameReg.test(serviceStaffName)){
					$("#nameTip").empty();
					var url="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/checkServiceStaffName";
					$.get(url,{serviceStaffName:serviceStaffName,ranNum:Math.random()},function(data){
						if(data=="true"){
							$("input[name='servicestaffModel.serviceStaffName']").after("<span name='namespan'><font color='red'><aebiz:showTitle titleId="ServicestaffModel.serviceStaffName.existed" /></font></span>");
							$("input[name='nameHidden']").val("false");
						}else{
							$("input[name='nameHidden']").val("true");
							$("span[name='namespan']").remove();
						}
					});
				}else{
					$("input[name='nameHidden']").val("false");
				}
			}
		});
		
		
			//验证确认密码是否与登录密码一至
	$("input[name='confirmPwd']").blur(function(){

			
			var password=$("input[name='servicestaffModel.password']").val();
			var confirmPwd=$("input[name='confirmPwd']").val();
			if(password!=confirmPwd){
				
				alert("确认密码与登录密码不一致！");
				
			}
		});
    

    function Checkbirthday() { 
    	$("input[name='birthdayHidden']").val("true");
			var mail=$("#birthday").val();
			var filter  = /^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$/; 
			if (filter.test(mail)){ 
				 return true; 
				  $("input[name='birthdayHidden']").val("true");
			}else {
				 alert('您的日期格式不正确'); 
				$("#birthday").val("");
				 $("input[name='birthdayHidden']").val("false");
				 return false;
				 }
			}
			
			

    

	</script>
	
	
	
	
	
	
	<script type="text/javascript">
	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}

	var map = new BMap.Map("l-map");
	map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。

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

	
	
	
	
	
	
	
	
	
</body>
</html>