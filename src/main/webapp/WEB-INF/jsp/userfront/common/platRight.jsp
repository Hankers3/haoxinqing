<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.aebiz.b2b2c.baseframework.servicelocator.ServiceLocator" %>
<%@page import="java.util.*" %>

<script src="${pageContext.servletContext.contextPath}/static/userfront/js/ajaxcart.js"></script>
<!-- Validation 表单验证插件-->
<script src="${pageContext.servletContext.contextPath}/static/frontpage/js/validation/jquery.validate.min.js"></script>
<script src="http://kefu.qycn.com/vclient/state.php?webid=94570"  type="text/javascript"></script>
<div class="y_rtfixbx">
	<div class="y_ifixed y_nologin">
		<div class="y_ifixbox">
			<div class="y_userbox">
				<div class="y_rtbtn y_userbtn" ><i class="fa fa-user"></i></div>
				<!--会员已登陆状态-->
				<!--
				<div class="y_rtbtn y_userbtn add-tooltip"><a class="y_pic add-tooltip" href="#" data-toggle="tooltip" data-placement="left" title="全网会员名"><img src="img/adimg/user2.png"></a></div>
				-->
			</div>
			<div class="y_rtbtn y_rtlove add-tooltip" data-toggle="tooltip" data-placement="left" title="我的收藏"></div>
			<a class="y_rtbtn y_sugges add-tooltip" href="${pageContext.servletContext.contextPath}/questionnairesurvey/toQuestionnaireSurvey" data-toggle="tooltip" data-placement="left" title="调查问卷"></a>
		</div>
		<div class="y_rtcart" id="w_cart">
			<b></b>
			<p class="y_rcatnm">购物车</p>
			<span class="y_rcatsize" id="cartsTotal">0</span>
			<div class="y_rtline"></div>
		</div>
		<p class="y_rtbtn y_totop add-tooltip" data-toggle="tooltip" data-placement="left" title="返回顶部"></p>
	<!--右侧登录  -->
		<div class="y_rtdlbox">
			<form id="domainForm" action="" method="get" class='form-horizontal form-bordered form-validate3' >
				<input type="hidden" id="hasVilidateCode" name="hasVilidateCode" value="false">
				<div class="alert alert-danger" style="display:none;"></div>
				<span class="y_rdltit">帐号</span>
				<input type="text" class="form-control" id="loginName" name="loginName" placeholder="手机号/邮箱">
				<span class="y_rdltit">密码</span>
				<input type="password" class="form-control" id="pwd" name="pwd">
				<div id="code"></div>
				<label class="y_remeve"><input type="checkbox" name="remember" id="remember" >记住我</label>
				<button class="btn btn-custom">登 录</button>
				<p class="clearfix"><a class="fl" href="${pageContext.servletContext.contextPath}/customerfindpwd/toFindPwdStep1">忘记密码?</a><a class="fr" href="${pageContext.servletContext.contextPath}/customerregist/toRegister">免费注册</a></p>
				<b class="y_closedlbx"><i class="fa fa-times"></i></b>
			</form>
		</div>

	</div>
	<div class="y_rtmsbx">
		<form action="${pageContext.servletContext.contextPath}/order/toBalance" id="cartForm" method="post">
			<div id="shoppingCart" class="y_rtctbx">
				
			</div>
		</form>
		<div class="y_rtlovebx">
			<h4>我的收藏</h4>	
			<div class="y_lovescroll">
				<ul class="y_lvlist">
				<c:forEach items="${tagList}" var="favoriteProduct" varStatus="num" >
					<li>
						<div class="y_lovpic">
							<a href="${pageContext.servletContext.contextPath}/front/product/toProduct/${favoriteProduct.productUuid}">
								<img src="${pageContext.servletContext.contextPath}/${favoriteProduct.storeLogo}">
								<span>${favoriteProduct.storeUuid}</span>
							</a>
						</div>
						￥${favoriteProduct.favoritePrice}
					</li>
				</c:forEach>
				</ul>
				<div class="y_rtlvbtn">
				<c:forEach items="${tagList}" var="favoriteProduct" begin="1" end="1" step="1" varStatus="num" >
					<c:if test="${num.index>0}">
					<a href="${pageContext.servletContext.contextPath}/usercenter/productfavorite/toPage?init=true" class="btn btn-custom">查看全部</a>
					 </c:if>
					 <c:if test="${num.index<=0}">
					<a href="#" class="btn btn-custom">收藏夹是空的</a>
					 </c:if>
				</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<span class="y_closertms"></span>
	</div>
<script>
		$(function(){
			
			if ($('.form-validate3').length > 0) {
		        $('.form-validate3').each(function() {
		            var id = $(this).attr('id');  //获取表单的id
		            $("#" + id).validate({   //验证表单
		                errorElement: 'span',  //输入错误时的提示标签
		                errorClass: 'help-block has-error',  //输入错误时的提示标签类名
		                errorPlacement: function(error, element) {  //输入错误时的提示标签显示的位置
		                    if(element.parents(".input-group").length > 0){
		                    		element.parents(".input-group").after(error);
		                    }else if(element.parents(".y_validatainput").length > 0){
		                    		element.parents(".y_validatainput").after(error);
		                    }
		                    else if(element.parents("label").length > 0) {
		                        element.parents("label").after(error);
		                    }else {
		                        element.after(error);
		                    }
		                },
		                highlight: function(label) {   //输入错误时执行的事件
		                    $(label).closest('.form-group').removeClass('has-error has-success').addClass('has-error');
		                },
		                success: function(label) {   //输入正确时执行的事件
		                    label.addClass('valid').closest('.form-group').removeClass('has-error has-success').addClass('has-success');
		                },
		                onkeyup: function(element) {   //验证元素输入值时按钮松开执行的事件
		                    $(element).valid();
		                },
		                onfocusout: function(element) {   //验证元素失去焦点时进行验证
		                    $(element).valid();
		                },                
		                submitHandler: function(form){
		                	login();
		                	//$("#modal-domain").modal('hide');//关闭模态框
						        //    form.submit(); //没有这一句表单不会提交
						        }
		            });
		        });
		    }	
			
			loadFavoteProduct()
		  });
		  
		//加载会员收藏的商品	 
		function loadFavoteProduct(){
			var url = "${pageContext.servletContext.contextPath}/usercenter/productfavorite/platRightFavoriteProduct";
			$.post(url,{ranNum:Math.random()},
					function(data){
					}
			);
		}
			
		function login(){
			var loginName = $("#loginName").val();
			var pwd = $("#pwd").val();
			var hasVilidateCode =  $("#hasVilidateCode").val();
			var validateCode =  $("#validateCode").val();
			$.get("${pageContext.request.contextPath }/customer/quickRightLogin",
				{
					"loginName":loginName,
					"pwd":pwd,
					"hasVilidateCode":hasVilidateCode,
					"validateCode":validateCode,
					"type":"customer",
					ranNum : Math.random()
				},
				function(data) {
					if(data == "codeNull"){
						$(".alert").show();
						$(".alert").html("验证码为空!");
					}else if(data == "codeError"){
						$(".alert").show();
						$(".alert").html("验证码错误!");
					}else if(data == "errorToMuch"){
						$(".alert").show();
						$(".alert").html("您已累计登录错误10次!请稍后在登陆或找回密码!");
					}else if(data == "loginError"){
						$(".alert").show();
						$(".alert").html("用户名或者密码错误!");
					}else if(data == "hasCode"){
						$(".alert").show();
						$(".alert").html("用户名或者密码错误!");
						$("#hasVilidateCode").val("true");
						$("#code").html('<span class="y_rdltit">验证码</span><div class="y_yzgrup"><input type="text" class="form-control" id="validateCode" name="validateCode" value="${validateCode }"><img id="validateImg" src="${pageContext.servletContext.contextPath}/getCustomerLoginCode" onClick="javascript:flushValidateCode();"/><span><span>看不清？<a href="javascript:flushValidateCode();" class="f_color2">换一张</a></span></span></div>');
					}else if(data == "success"){
						$.getJSON("${pageContext.servletContext.contextPath}/customer/isLogin",
								function(data) {
									if(data.nickName != null){
										var customerName = data.nickName;
										var head = data.head;
										$("#isLogin").html('HI，<a href="${pageContext.servletContext.contextPath}/customer/toIndex">'+customerName+'</a>&nbsp;<a href="${pageContext.servletContext.contextPath}/customer/logout">退出</a>');
										$(".y_userbox").html('<div class="y_rtbtn y_userbtn add-tooltip" data-toggle="tooltip" data-placement="left" title="'+customerName+'"><a class="y_pic" href="${pageContext.servletContext.contextPath}/customer/toIndex"><img src="'+head+'"></a></div>');
										$(".y_ifixed").addClass("y_haslogoin").removeClass("y_nologin");
									}else{
										$("#isLogin").html('您好，欢迎来到<aebiz:platConfig codeName="platName"/>！ <a class="y_mlr5" href="${pageContext.servletContext.contextPath}/customer/toLogin">请登录</a> <a class="y_mlr5" href="${pageContext.servletContext.contextPath}/customerregist/toRegister">免费注册</a>');
									}
								});
						$(".y_rtdlbox").hide(); 
						
					}
				});
		}
		
		/* 刷新验证码 */
		function flushValidateCode(){
			 var validateImgObject = document.getElementById("validateImg");
			 validateImgObject.src = "${pageContext.servletContext.contextPath}/getCustomerLoginCode?time=" + new Date();
		}
 	</script>