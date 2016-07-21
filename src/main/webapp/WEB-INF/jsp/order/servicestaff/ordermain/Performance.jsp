<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>家政员 -我的绩效</title>
  <meta name="description" content="aebiz b2b2c index">
  <!--[if IE]> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <![endif]-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/font-awesome.min.css">
  <!-- 字体图标ie7兼容性处理 -->
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/font-awesome-ie7.min.css">
	<![endif]-->
	<!-- icheck -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/plugins/icheck/all.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/global.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/usercenter/css/member.css">
  
  <!--- jQuery -->
  <script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery-1.11.1.min.js"></script>
   <!-- icheck -->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/plugins/icheck/jquery.icheck.min.js"></script>

  <!--[if lt IE 9]>
    <script src="${pageContext.servletContext.contextPath}/static/usercenter/js/respond.min.js"></script>
  <![endif]-->
  
  <!-- Favicon and Apple Icons -->
  <link rel="icon" type="image/png" href="${pageContext.servletContext.contextPath}/static/usercenter/img/icon.png">
</head>
<body>
	<!-- 家政员-个人中心top-->
	<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterHead.jsp"%>
	<!-- 家政员-个人中心top end-->
	<!-- 家政员-个人中心主体 -->
	<div class="container member_main">
		<div class="row ">
			 <%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterLeft.jsp"%> 
			<!-- 雇我吧-我的积分主体右侧 -->
			<div class="col-mi-9">
        <div class="m_income_tit m_coupon_tit"><span>我的绩效</span></div>
        <div class="member_tab member_jx_tab">
					<span class="m_cur">月</span>
					<b>|</b>
					<span>年</span>
				</div>
        <div class="m_jx_tabin">
        	<div id = "cutView" class="m_tab">
		        <table class="m_detailed_list">
		        	<tbody>
		        		<tr>
		        			<td width="33%" class="m_jx_td">接单率</td>
		        			<td width="33%">${performance.get("personalReceiveOrderPro")}</td>
		        			<td width="33%">平均${performance.get("avgReceiveOrderPro")}</td>
		        		</tr>
		        		<tr>
		        			<td class="m_jx_td">拒单率</td>
		        			<td>${performance.get("personalRefuseOrderPro")}</td>
		        			<td>平均${performance.get("avgRefuseOrderPro")}</td>
		        		</tr>
		        		<tr>
		        			<td class="m_jx_td">服务互评</td>
		        			<td>${performance.get("personalScore")}</td>
		        			<td>平均${performance.get("scoreByOthers")}</td>
		        		</tr>
		        		<tr>
		        			<td class="m_jx_td">服务评分</td>
		        			<td>${performance.get("personalServiceScore")}</td>
		        			<td>平均${performance.get("avgServiceScore")}</td>
		        		</tr>
		        		<tr>
		        			<td class="m_jx_td">空岗率</td>
		        			<td>${performance.get("personalNoWorkPro")}</td>
		        			<td>平均${performance.get("avgNoWorkPro")}</td>
		        		</tr>
		        		<tr>
		        			<td class="m_jx_td">抢单时间</td>
		        			<td>${performance.get("ordersTime")}</td>
		        			<td>排名${performance.get("orderRanking")}</td>
		        		</tr>
		          </tbody>
		        </table>
		        <div class="m_income m_incomein">
		        	接单总量： <label class="m_color">${performance.get("receiveOrderCount")}笔</label>
		        	<b>|</b>
		        	<label class="m_color">￥${performance.get("orderSum")}</label>
            </div>
	        </div>
        </div>
        <script>
        	 $(".member_jx_tab span").click(function () {
						var x= $(this).index(".member_jx_tab span");
						$(this).addClass("m_cur").siblings("span").removeClass("m_cur");
						$(".m_jx_tabin .m_tab").eq(x).show().siblings(".m_jx_tabin .m_tab").hide();
						var init = "false";
						var timeType = "0";
						if($(this).html()=="年"){
							timeType = "1";
			        	}	
						
        	 		var sendURL = "${pageContext.servletContext.contextPath}/servicestaff/order/getperformance";
        	 		$.post(sendURL,{"init":init,"timeType":timeType},
        	 				function(data){
        	 				$("#cutView").html(data);
        	 				}
        	 		);
        	 }); 
        </script>
        <!-- 雇我吧-我的绩效end -->
		  </div>
		  <!-- 雇我吧-我的绩效主体右侧 end -->
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