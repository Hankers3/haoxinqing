<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>  
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>  

</head>

<body>
	
<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/storeplatrolemenurel/tosaverel/${m.uuid}" method="post" class='form-horizontal form-bordered form-validate'>				
		<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1><aebiz:showTitle titleId="systempub.dept.choosemenu"/></h1>
					</div>
				</div>
				<div class="breadcrumbs">
					<ul>
					<li>
							<span><aebiz:showTitle titleId="sysmenu.menuOne"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="syspermit.menuTwo"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="syspermit.m.choosepermit"/></span>
						</li>
					</ul>
				</div>
				<!--关联权限模块-->
				
				<div class="y_allauth">
					<div class="check-line">
						<input type="checkbox" id="all" name="all" class='icheck-me' data-skin="square" data-color="blue" index="all">
						<label class='inline' for="all"><aebiz:showTitle titleId="sysdeptmenurel.chooseall"/></label>
					</div>
				</div>
				
				<!--一级分类-->
				
				<c:forEach items="${topList}" var="smmOne">	
					<div class="y_smmobox y_firstbox" id="y_smbox${smmOne.uuid}"><!--第一个权限系统增加样式 .y_firstbox-->
						<div class="y_sxmotit">
							<span class="y_sxtitbtn y_hover"></span><!--第一个权限系统增加样式 .y_hover-->
							<div class="check-line">
								<input type="checkbox" value="${smmOne.uuid}" id="${smmOne.uuid}" name="choosemenus" class='icheck-me' data-skin="square" data-color="blue" index="all_${smmOne.uuid}" <c:if test="${smmOne.checked=='1'}"> checked </c:if> >
								<label class='inline' for="${smmOne.uuid}">${smmOne.name}</label>
							</div>
						</div>
						
						<div class="y_sxmdxlit">							
								<c:forEach items="${mapOneTwo[smmOne]}" var="smmTwo">	
									<dl class="y_clear">
										<!--二级分类-->
										<dt>
											<div class="check-line">
												<input type="checkbox" id="${smmTwo.uuid}" value="${smmTwo.uuid}" name="choosemenus" class='icheck-me' data-skin="square" data-color="blue" index="all_${smmOne.uuid}_${smmTwo.uuid}" <c:if test="${smmTwo.checked=='1'}"> checked </c:if>>
												<label class='inline' for="${smmTwo.uuid}">${smmTwo.name}</label>
											</div>
											<span class="y_sxtitbtn"></span>
										</dt>
										<dd>
											
										<c:forEach items="${mapTwoThree[smmTwo]}" var="smmThree">				
												<!--三级分类-->
												<div class="y_qxerjbx y_clear">
													<div class="y_sanjcd check-line">
														<input type="checkbox" id="${smmThree.uuid}" value="${smmThree.uuid}" name="choosemenus" class='icheck-me' data-skin="square" data-color="blue" index="all_${smmOne.uuid}_${smmTwo.uuid}_${smmThree.uuid}" <c:if test="${smmThree.checked=='1'}"> checked </c:if>>
														<label class='inline' for="${smmThree.uuid}">${smmThree.name}</label>
													</div>
													
													<ul class="y_clear">	
														<c:choose> 
														  <c:when test="${null == mapThreeFour[smmThree]}"> 
														  	
														  	<c:forEach items="${mapTwoThreePermit[smmThree]}" var="permitThree">	
																	<li class="check-line">
																		<!--四级分类-->
																		<input type="checkbox" id="${permitThree.uuid}" value="${permitThree.uuid}" name="choosepermits" class='icheck-me' data-skin="square" data-color="blue" index="all_${smmOne.uuid}_${smmTwo.uuid}_${smmThree.uuid}_${permitThree.uuid}" <c:if test="${permitThree.checked=='1'}"> checked </c:if> >
																		<label class='inline' for="${permitThree.uuid}">${permitThree.name}</label>
																	</li>
																</c:forEach>		
																														       
														  </c:when> 														 
														  <c:otherwise> 								
																<c:forEach items="${mapThreeFour[smmThree]}" var="smmFour">	
																	<li class="check-line text-green">
																		<!--四级分类-->
																		<input type="checkbox" id="${smmFour.uuid}" value="${smmFour.uuid}" name="choosemenus" class='icheck-me' data-skin="square" data-color="blue" index="all_${smmOne.uuid}_${smmTwo.uuid}_${smmThree.uuid}_${smmFour.uuid}" <c:if test="${smmFour.checked=='1'}"> checked </c:if> >
																		<label class='inline' for="${smmFour.uuid}">${smmFour.name}</label>
																		
																			<c:if test="${null != mapThreeFourPermit[smmFour]}"> 																																					       
																				<ul class="y_sxmdthrel y_clear">
																					<li class="y_sxmst"></li>
																						<!--四级分类下五级分类-->
																						<c:forEach items="${mapThreeFourPermit[smmFour]}" var="permitFour">				
																							<li class="check-line">
																								<input type="checkbox" id="${permitFour.uuid}" value="${permitFour.uuid}" name="choosepermits" class='icheck-me' data-skin="square" data-color="blue" index="all_${smmOne.uuid}_${smmTwo.uuid}_${smmThree.uuid}_${smmFour.uuid}_${permitFour.uuid}" <c:if test="${permitFour.checked=='1'}"> checked </c:if> >
																								<label class='inline' for="${permitFour.uuid}">${permitFour.name}</label>	
																							</li>
																						</c:forEach>																						
																				</ul>																		
																		 </c:if> 
																			 
																	</li>
																</c:forEach>		    
														  </c:otherwise> 
														</c:choose> 
													</ul>
												</div>
											</c:forEach>			
										</dd>
									</dl>
								</c:forEach>					
												
						</div>
					</div>
				
			</c:forEach>
		</div>
		
		<!--右侧浮动定位-->
		<div class="navbar-right y_authright y_authhover">
			<ul>
				<c:forEach items="${topList}" var="smmOne">	
					<li><a href="#y_smbox${smmOne.uuid}"><span>${smmOne.name}</span></a></li>	
				</c:forEach>
			</ul>
		</div>
		<!--浮动按钮-->
		<div class="y_fixedbtn">
			<input type="submit" class="btn btn-primary a_size_1 btn-large" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
			<button type="button" class="btn a_size_1 btn-large cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
		</div>

	</div>
	
</form>	
	<!--后台权限设置页面js-->
	<script>
		$(function(){
			function y_smsliDown(ei){
				$(ei).siblings(".y_smmobox").find(".y_sxmotit .y_sxtitbtn").removeClass("y_hover").parent().next(".y_sxmdxlit").slideUp();
				$(ei).find(".y_sxmotit .y_sxtitbtn").addClass("y_hover").parent().next(".y_sxmdxlit").slideDown();
			};
			
			function y_smsliUp(ei){
				$(ei).find(".y_sxmotit .y_sxtitbtn").removeClass("y_hover").parent().next(".y_sxmdxlit").slideUp();
			};
			
			//权限的收缩隐藏
			$(".y_sxmotit .y_sxtitbtn").click(function(){
				var atid="#"+$(this).parents(".y_smmobox").attr("id");
				if(!$(this).is(".y_hover")){
					y_smsliDown(atid);
				}else{
					y_smsliUp(atid);
				}
			});
			
			$(".y_sxmdxlit dt .y_sxtitbtn").click(function(){
				$(this).toggleClass("y_hover").parent("dt").next("dd").slideToggle();	
			});
			
			//5级菜单的显示隐藏
			$(".y_sxmdxlit dd .y_qxerjbx > ul > li").hover(function(){
	    	$(this).addClass("y_hover");
	    },function(){
	    	$(this).removeClass("y_hover");
	    });
	    
	    /*页面右侧随屏滚动和滚动定位*/
	    function getTargetTop(elem){
				var id = elem.attr("href");
				var offset = 186;
				return $(id).offset().top - offset;
			}
			
			var elemHref = $('.navbar-right li a[href^="#"]');
			elemHref.click(function(event) {
				var target = getTargetTop($(this));
				$('html, body').animate({scrollTop:target}, 500);
				var id = $(this).attr("href");
				y_smsliDown(id);
				event.preventDefault();
			});
			
			var sections = $('.navbar-right > li > a[href^="#"]');
			function checkSectionSelected(scrolledTo){
				var threshold = 100;
				var i;
				for (i = 0; i < sections.length; i++) {
					var section = $(sections[i]);
					var target = getTargetTop(section);
					if (scrolledTo > target - threshold && scrolledTo < target + threshold) {
						sections.removeClass("active");
						section.addClass("active");
					}
				}
			}
			
			checkSectionSelected($(window).scrollTop());
			$(window).scroll(function(){
				checkSectionSelected($(window).scrollTop());
			});
    });
	</script>
	
	
		<script>
		$(document).ready(function() {		
				$(".cancel").click(function(){
					history.go(-1) ;
				});
				
				$("input[name='choosemenus']:checkbox").on('ifChanged', function(event){
		      //获得自己的index值
		      var index = $(this).attr("index");
          
          //获得自己的选中状态，如果选中，则把子都选中，如果没选中，则子都不选中
          var selfChecked = $(this).is(":checked") ;
          if(selfChecked){   	          	
	          $(":checkbox[index^='"+index+"']").each(function(i){
	          	$(this).iCheck('check');
	          });
          }else{
          	$(":checkbox[index^='"+index+"']").each(function(i){
	          	$(this).iCheck('uncheck');    	
	          });
          }          
          
          //获得父的index值
		      var parent_index = index.substring(0,index.length - 18);
		      var parentIsCheck = false;
		      
		      //循环以父编号开头的所有对象，如果有选中的，则父选中状态为true
		      $(":checkbox[index^='"+parent_index+"']").each(function(i){
          	if($(this).is(":checked")){
            	parentIsCheck = true;
            }
          });
          
          //如果为true，则父以上级别应该选中，如果为false，父应该取消
          if(parentIsCheck) {
          	 //得到父编号
          	 //var parentStr = parent_index ;
          	 //while(parentStr != "all_") {
          	 //		$(":checkbox[index='"+parentStr+"']").iCheck('check');
          	 //		parentStr = parentStr.substring(0,parentStr.length - 18);
          	 //		if("all_"==parentStr) {
          	 //			 break ;
          	 //		}
          	 //}
          }else{
          	$(":checkbox[index='"+parent_index+"']").iCheck('uncheck');
          }
          event.preventDefault();
		  	})	
		  	
		  	
		  	$("input[name='choosepermits']:checkbox").on('ifChanged', function(event){
		      //获得自己的index值
		      var index = $(this).attr("index");        
                    
          //获得父的index值
		      var parent_index = index.substring(0,index.length - 20);
		      var parentIsCheck = false;
		      	
		      //循环以父编号开头的所有对象，如果有选中的，则父选中状态为true
		      $(":checkbox[index^='"+parent_index+"_']").each(function(i){
          	if($(this).is(":checked")){
            	parentIsCheck = true;
            }
          });                    
          
          //如果为true，则父以上级别应该选中，如果为false，父应该取消
          if(parentIsCheck) {
          	 //得到父编号
          	 //var parentStr = parent_index ;
          	 //while(parentStr != "all_") {
          	 //		$(":checkbox[index="+parentStr+"]").iCheck('check');
          	 //		parentStr = parentStr.substring(0,parentStr.length - 18);
          	 //		if("all_"==parentStr) {
          	 //			 break ;
          	 //		}
          	 //}
          }else{
          	$(":checkbox[index='"+parent_index+"']").iCheck('uncheck');
          }
          event.preventDefault();
		  	})
		  			  	
		  	$("input[name='all']:checkbox").on('ifChanged', function(event){
		      //获得自己是否选中
		      var ischecked = $(this).is(":checked") ;      
		      
      		//循环以父编号开头的所有对象，如果有选中的，则所有子全部选中
		      $(":checkbox[index^='all_']").each(function(i){
		      	if(ischecked) {
          		$(this).iCheck('check');
          	}else{	
          		$(this).iCheck('uncheck');
          	}
          }); 		               
		  	})			
    }); 
	</script>	
</body>
</html>
