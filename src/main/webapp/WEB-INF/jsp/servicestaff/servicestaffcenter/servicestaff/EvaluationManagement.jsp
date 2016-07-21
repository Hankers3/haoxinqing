<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>家政员 -评价管理</title>
  <meta name="description" content="aebiz b2b2c index">
  <!--[if IE]> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <![endif]-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/import/ListImport.jsp"%>	
	<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/import/ListImportJs.jsp"%>	
</head>
<body>
	<!-- 家政员-个人中心top-->
	<!--<div class="member_top">
	  <div class="container">
	  	<div class="fl member_logo"><a href="###"><img src="${pageContext.servletContext.contextPath}/static/usercenter/img/member_logo.png"></a></div>
	  	<div class="fl member_gu">我的雇我吧</div>
	  	<div class="fr member_right">消息<span>2</span><i class="fa fa-angle-down"></i><b>|</b> <a href="###">13163287627</a><a href="###" class="m_tcin">[退出]</a></div>
	  </div>
	</div>-->
		<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterHead.jsp"%>
	<!-- 家政员-个人中心top end-->
	<!-- 家政员-个人中心主体 -->
	<div class="container member_main">
		<div class="row ">
			<!--<div class="col-mi-3 member_relative">
				<ul class="member_left">
					<li><a href="##"><i class="fa fa-file-text"></i>我的订单</a></li>
					<li class="even member_cur"><a href="##"><i class="fa fa-credit-card"></i>我的收入</a></li>
					<li><a href="##"><i class="fa fa-calendar"></i>我的档期</a></li>
					<li class="even"><a href="##"><i class="fa fa-weixin"></i>评价管理</a></li>
					<li><a href="##"><i class="fa fa-pencil"></i>我的绩效</a></li>
					<li class="even"><a href="##"><i class="fa fa-star"></i>我的培训</a></li>
					<li><a href="##"><i class="fa fa-book"></i>我的考试</a></li>
					<li class="even"><a href="##"><i class="fa fa-cog"></i>个人设置</a></li>
				</ul>
			</div>-->
			
			<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterLeft.jsp"%>
			
			<!-- 雇我吧-我的积分主体右侧 -->
			<div class="col-mi-9">
        <div class="m_income_tit m_coupon_tit"><span>同伴评价</span></div>
        
        <!-- 如果没有数据 -->
        <c:if test="${empty order}">
        	<div>
        		<li>您还没有成功订单订单，不能进行评价！</li>
        	</div>
        </c:if>
       
       <c:forEach items="${order}" var="order">
        <table class="m_list_table">   	
      				<tr>
        			<th colspan="6">
        				<input type="checkbox" class='icheck-me' id="c1" data-skin="minimal">
        				<label class='inline' for="c1">下单时间：${order.orderTime}&nbsp;&nbsp;预约单号：${order.orderId}</label>
        			</th>
        		</tr>
        		<tr>
        			<td width="20%">
        				<div class="fl m_list_table_pic"><img src="${pageContext.request.contextPath }/static/usercenter/img/m_hb.jpg"></div>
        				<div class="fl m_list_table_tit">家庭保洁</div>
        			</td>
        			<td width="14%">${order.addressModel.serviceTime}<br/>下午15:00</td>
        			<td width="25%">
      					<c:forEach items="${order.detailList}" var="detail" varStatus="det">
      						${detail.productName}：${detail.buyNum }
      						<c:if test="${detail.chargetype ==1}">m<sup>2</sup></c:if>
							    <c:if test="${detail.chargetype ==0}">个</c:if>
      						<c:if test="${!det.last }">、</c:if>
      					</c:forEach>
       			</td>
        			<td width="13%"><label>￥${order.payMoney}</label></td>
      				<td width="13%">
      				<p>
      					
      			<c:choose>
							<c:when test="${empty order.osms}">
								待评价
							</c:when>
							<c:otherwise>
								已评价
							</c:otherwise>
						</c:choose>
      					
      					
      				</p>
      			</td>
      			<td width="14.3%"><a href="##" class="btn btn-custom m_toggle">
      			
      			<c:choose>
							<c:when test="${empty order.osms}">
								我要评价
							</c:when>
							<c:otherwise>
								查看评价
							</c:otherwise>
						</c:choose>
							
							</a></td>
      		</tr> 		
        </table>
        
        <div class="m_evaluation_content m_pj m_pj_fu clearfix">
        	<ul class="m_pj_ul">
        		
        			<c:forEach items="${order.serviceStaffs}" var="servicelist" varStatus="det">
			        		<li>
			        			<input type="hidden" name="servicestaffuuid" id="${order.uuid}${servicelist.serviceStaffUuid}servicestaffuuid"  value="" class="form-control" >
					        	<div class="m_pjr_rl">
						      		
						      		<c:choose>
													<c:when test="${empty order.osms}">
															<div class="m_tb_a m_tb_ain">
								      			     <div class="m_pj_left">同伴${servicelist.serviceStaffName}评分:</div><div class="m_pj_right"><ul class="w_xing" id="${order.uuid}${servicelist.serviceStaffUuid}"><li class="cur">1</li><li>2</li><li>3</li><li>4</li><li>5</li></ul></div>	
								      		    </div>
													</c:when>
													<c:otherwise>
														<c:forEach items="${order.osms}" var="osms" varStatus="det">
									      			<c:if test="${osms.byServiceStaffUuid==servicelist.serviceStaffUuid}">
											      		<div class="m_tb_a m_tb_ain">
											      			<div class="m_pj_left">同伴${servicelist.serviceStaffName}评分:</div><div class="m_pj_right"><b class="m_b1"><b class="m_b2" style="width:${osms.score*20}%"></b></b></div>
											      		</div>
									      			</c:if>
								      		</c:forEach>
													</c:otherwise>
											 </c:choose>
						      		
						      		
						      		
											<script>
						      			$(function(){
						      				$("#${order.uuid}${servicelist.serviceStaffUuid} li").click(function(){
						      						$("#${order.uuid}${servicelist.serviceStaffUuid} li").addClass("cur");
						      						$(this).nextAll("li").removeClass("cur");
						      						var szw2= $(this).html()
						      						
						      						$("#${order.uuid}${servicelist.serviceStaffUuid}servicestaffuuid").val("${order.uuid}-"+szw2+"-${servicelist.serviceStaffUuid}")
						      						//alert(	$("#${order.uuid}${servicelist.serviceStaffUuid}servicestaffuuid").val());
						      						var syntheticData=$("#${order.uuid}${servicelist.serviceStaffUuid}servicestaffuuid").val();
						      						//alert(syntheticData);
						      						
						      						
						      				});
						      			})	
						      		</script>
						      		<div class="m_nr_pic"></div>
					          </div>
				          </li>
	            </c:forEach>  
	          
	          
	          
	        
          </ul>
          <div class="m_pj_in m_pj_btn m_tbpj_btn">          
         	
      			 <c:if test="${empty order.osms}">
								<span class="btn btn-custom w_anniu" id="${order.uuid}">提交评价</span>
							</c:if>
      			
      		</div>
      		<script>
      			
      			$("#${order.uuid}").click(function(){
      			
      				var szw1 = $(this).parent().prev(".m_pj_ul").find("input");
      				var str=""
      				
      				for(var i = 0 ; i < szw1.length; i++){
      							str+= szw1.eq(i).val()+","
      					}
      				str = str.substr(0,str.length-1)
      			//alert(str);
      			
      			
      			toAddOrderstaffmutual(str);
      			
      			})
      			
    
    
     function toAddOrderstaffmutual(str) {
			//alert(str);
			
			$.get(
			"${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/toAddOrderstaffmutual",
			{
				"orderstaffmutuals":str,
				ranNum : Math.random()
			},
			function(data) {
						if(data=="success"){
						   alert("评价成功！");
						   location.reload();
					}else{
						   alert("评价失败！");
					}
			});
		}
      			
      			
      			
      		</script>
        </div>
        						  
     </c:forEach>   
        
        
        
       
        <script>
        	$('.m_toggle').click(function(){
        		$(this).parent().parent().parent().parent().next('.m_pj').slideToggle("5000");
        	});
        	
        </script>
        <!--翻页-->
       <aebiz:userCenterPage listPath="usercenter/servicestaffcomb/toEvaluationManagement" />
				<!--翻页 end-->
        <!-- 雇我吧-我的积分-积分明细列表end -->
		  </div>
		  <!-- 雇我吧-我的积分主体右侧 end -->
	  </div>
  </div>
	
	<!-----雇我吧首页底部----->
  <div class="m_foot">
  	<p><a href="##">关于雇我吧</a>|<a href="##">联系我们</a>|<a href="##">加入我们</a>|<a href="##">常见问题</a>|<a href="##">意见反馈</a></p>
  	<div class="m_footin">Copyright@2015 雇我吧 版权所有 京ICP备12345678号</div>
  </div>
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
  
  
  
  
  
  
  
</body>
</html>