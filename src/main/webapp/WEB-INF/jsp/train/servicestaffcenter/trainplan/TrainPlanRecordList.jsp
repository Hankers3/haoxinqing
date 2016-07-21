<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
  <meta charset="utf-8">
  <title>我的培训</title>
  <meta name="description" content="aebiz b2b2c index">
  <!--[if IE]> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <![endif]-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/servicestaffcenter/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/servicestaffcenter/css/font-awesome.min.css">
  <!-- 字体图标ie7兼容性处理 -->
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/servicestaffcenter/css/font-awesome-ie7.min.css">
	<![endif]-->
	<!-- icheck -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/servicestaffcenter/css/plugins/icheck/all.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/servicestaffcenter/css/global.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/servicestaffcenter/css/member.css">
  
  <!--- jQuery -->
  <script src="${pageContext.servletContext.contextPath}/static/servicestaffcenter/js/jquery-1.11.1.min.js"></script>
   <!-- icheck -->
	<script src="${pageContext.servletContext.contextPath}/static/servicestaffcenter/js/plugins/icheck/jquery.icheck.min.js"></script>

  <!--[if lt IE 9]>
    <script src="${pageContext.servletContext.contextPath}/static/servicestaffcenter/js/respond.min.js"></script>
  <![endif]-->
  
  <!-- Favicon and Apple Icons -->
  <link rel="icon" type="image/png" href="${pageContext.servletContext.contextPath}/static/servicestaffcenter/img/icon.png">
	 <%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/import/ListImport.jsp"%>	
	<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/import/ListImportJs.jsp"%>
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
        <div class="m_income_tit m_coupon_tit"><span>培训记录</span></div>
        <c:choose>
        <c:when test="${trainStaffModels==null||trainStaffModels.size()<=0}">
        	暂无培训！
        </c:when>
        <c:otherwise>
        <table class="m_detailed_list">
        	<tbody>
        		<tr>
        			<th width="33%">日期</th>
        			<th width="33%">名称</th>
        			<th width="33%">结果</th>
        		</tr>
			<c:forEach items="${trainStaffModels}" var="trainStaffModel">
				<c:if test="${trainStaffModel.trainPlanModel!=null}">
				<tr>
        			<th width="33%">${trainStaffModel.trainPlanModel.trainStartTime}</th>
        			<th width="33%">${trainStaffModel.trainPlanModel.syllabusName}</th>
        			<th width="33%">${trainStaffModel.state}</th>
        		</tr>
        		</c:if>
			</c:forEach>
          </tbody>
        </table>
         <!--翻页-->
         	 <aebiz:userCenterPage listPath="/servicestaffcenter/trainplan/toTrainPlanRecordList" />
			<!--翻页 end-->
        </c:otherwise>
        </c:choose>
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
