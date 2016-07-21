<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp"%>	
<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>	
  <title>雇我吧 -我的账户</title>
  <meta name="description" content="aebiz b2b2c index">
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath }/static/usercenter/img/icon.png">
</head>
<body>
	<!-- 家政员-个人中心top-->
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterHead.jsp"%>
	<!-- 家政员-个人中心top end-->
	<!-- 家政员-个人中心主体 -->
	<div class="container member_main">
		<div class="row ">
			<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterLeft.jsp"%>		
			<!-- 雇我吧-我的账户主体右侧 -->
			<div class="col-mi-9">
        <div class="m_income">
        	账户金额： <label class="m_color">￥${chargeTotal }</label>
        	<b>|</b>
        	可用优惠券：<label class="m_color">${coupcount }</label> 张
        </div>
        <!-- 雇我吧-我的账户-账号明细记录 -->
        <div class="m_income_tit"><span>账户明细记录</span></div>
        <div class="m_detailed">
        	<div class="m_detailed_zc clearfix">
        		<div class="fl m_detailed_left">
	        		<span>最近一个月<i class="fa fa-angle-down"></i></span>
	        		<b>|</b>
	        		<span>支出：<label class="color_a">${out_charge }</label>  元</span>
            </div>
          </div>
        <!-- 雇我吧-我的收入-账号明细列表 -->
        <table class="m_detailed_list">
        	<tbody>
        		<tr>
        			<th width="26%">时间</th>
        			<th width="16%">类型</th>
        			<th width="17%">金额</th>
        			<th width="16%">状态</th>
        			<th width="27%">备注</th>
        		</tr>
        		<c:forEach items="${wm.rows }" var="charge">
        		<tr>
        			<td>${charge.opeTime }</td>
        			<td>
        				<c:if test="${charge.operType==0 }">收入</c:if>
        				<c:if test="${charge.operType==1 }">支出</c:if>
        			</td>
        			<td><label class="color_a">￥${charge.operAmount }</label></td>
        			<td>
        				成功
        			</td>
        			<td>${charge.description }</td>
        		</tr>
        		</c:forEach>
          </tbody>
        </table>
       <!--翻页-->
        <aebiz:userCenterPage listPath="usercenter/customer/toMycharges" />
		<!--翻页 end-->
        <!-- 雇我吧-我的收入-账号明细列表end -->
		  </div>
		  <!-- 雇我吧-我的收入主体右侧 end -->
	  </div>
  </div>
	
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
</body>
</html>