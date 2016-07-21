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
  
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/font-awesome.min.css">
  <!-- 字体图标ie7兼容性处理 -->
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="css/font-awesome-ie7.min.css">
	<![endif]-->
	<!-- icheck -->
	<link rel="stylesheet" href="css/plugins/icheck/all.css">
  <link rel="stylesheet" href="css/global.css">
  <link rel="stylesheet" href="css/member.css">
  
  <!--- jQuery -->
  <script src="js/jquery-1.11.1.min.js"></script>
   <!-- icheck -->
	<script src="js/plugins/icheck/jquery.icheck.min.js"></script>

  <!--[if lt IE 9]>
    <script src="js/respond.min.js"></script>
  <![endif]-->
  
  <!-- Favicon and Apple Icons -->
  <link rel="icon" type="image/png" href="img/icon.png">
</head>
<body>
	<!-- 家政员-个人中心top-->
	<div class="member_top">
	  <div class="container">
	  	<div class="fl member_logo"><a href="###"><img src="img/member_logo.png"></a></div>
	  	<div class="fl member_gu">我的雇我吧</div>
	  	<div class="fr member_right">消息<span>2</span><i class="fa fa-angle-down"></i><b>|</b> <a href="###">13163287627</a><a href="###" class="m_tcin">[退出]</a></div>
	  </div>
	</div>
	<!-- 家政员-个人中心top end-->
	<!-- 家政员-个人中心主体 -->
	<div class="container member_main">
		<div class="row ">
			<div class="col-mi-3 member_relative">
				<ul class="member_left">
					<li><a href="##"><i class="fa fa-file-text"></i>我的订单</a></li>
					<li class="even"><a href="##"><i class="fa fa-credit-card"></i>我的收入</a></li>
					<li><a href="##"><i class="fa fa-calendar"></i>我的档期</a></li>
					<li class="even"><a href="##"><i class="fa fa-weixin"></i>评价管理</a></li>
					<li><a href="##"><i class="fa fa-pencil"></i>我的绩效</a></li>
					<li class="even member_cur"><a href="##"><i class="fa fa-star"></i>我的培训</a></li>
					<li><a href="##"><i class="fa fa-book"></i>我的考试</a></li>
					<li class="even"><a href="##"><i class="fa fa-cog"></i>个人设置</a></li>
				</ul>
			</div>
			
			<!-- 雇我吧-我的积分主体右侧 -->
			<div class="col-mi-9">
        <div class="m_income_tit m_coupon_tit"><span>培训记录</span></div>
        <table class="m_detailed_list">
        	<tbody>
			<c:if test="${trainStaffModels!=null&&trainStaffModels.size()>0}">
			<c:forEach items="${trainStaffModels}" var="trainStaffModel">
				<c:if test="${trainStaffModel.trainPlanModel!=null}">
				<tr>
        			<th width="33%">${trainStaffModel.trainPlanModel.trainStartTime}</th>
        			<th width="33%">${trainStaffModel.trainPlanModel.syllabusName}</th>
        			<th width="33%">${trainStaffModel.state}</th>
        		</tr>
        		</c:if>
			</c:forEach>
        	</c:if>
        	<c:if test="${trainStaffModels==null||trainStaffModels.size()<=0}">
        		<tr>
        			暂时木有培训！
        		</tr>
        	</c:if>
          </tbody>
        </table>
        <!--翻页-->
        <div class="y_page">
					<ul class="pagination">
					  <!--<li class="y_pgbt"><a href="#" title="上一页">&lt;</a></li>--><!--上一页可点击时的状态-->
					  <li class="y_pgbt disabled"><span><i class="fa fa-caret-left"></i>上一页</span></li><!--上一页不可点击时的状态，多加了个类.disabled 并且将a标签换为span,下一页也是一样-->
					  <li class="active"><a href="#" title="第1页">1</a></li><!--li的类active 为选中当前页-->
					  <li><a href="#" title="第2页">2</a></li>
					  <li><a href="#" title="第3页">3</a></li>
					  <li><a href="#" title="第4页">4</a></li>
					  <li><a href="#" title="第5页">5</a></li>
					  <li class="y_pgbt"><a href="#" title="下一页">下一页<i class="fa fa-caret-right"></i></a></li><!--上一页、下一页按钮都有默认类 .y_pgbt-->
					</ul>
					共5页，到第<input class="form-control" type="text" value="2">页
					<a class="btn btn-primary" href="#" title="确定">确定</a>
				</div>
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
