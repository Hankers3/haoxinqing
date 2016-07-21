<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!doctype html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/frontpage/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/frontpage/css/font-awesome.min.css">
	<!-- 字体图标ie7兼容性处理 -->
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/frontpage/css/font-awesome-ie7.min.css">
	<![endif]-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/frontpage/css/global.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/frontpage/css/login.css">
	
	<!--- jQuery -->
	<script src="${pageContext.servletContext.contextPath}/static/frontpage/js/jquery-1.11.1.min.js"></script>
</head>

<body class="bg_f5">

<div class="header-top">
    <div class="container">
        <div class="fl">
        	<a class="y_sc" href="#"><b></b>收藏全网</a>
        	您好，欢迎来到全网商城！ <a href="#" class="y_mlr5">请登录</a> <a href="#" class="y_mlr5">免费注册</a>
        </div><!-- End .header-top-left -->
        <ul class="fr">
      		<li class="y_headrli"><a href="#" class="y_plr10">我的订单</a></li>
      		<li class="y_headrli">
      			<span data-toggle="dropdown" class="dropdown-toggle"><i class="fa fa-mobile"></i> 手机全网 <i class="fa fa-angle-down"></i></span>
      			<s></s>
            <div role="menu" class="dropdown-menu">
            	<img width="150" src="img/y_wx.jpg">
            	<p class="y_smewm">扫一扫，有惊喜！</p>
          	</div>
      		</li>
      		<li class="y_headrli">
      			<span data-toggle="dropdown" class="dropdown-toggle">收藏夹 <i class="fa fa-angle-down"></i></span>
      			<s></s>
            <ul role="menu" class="dropdown-menu">
              <li><a href="#">收藏的商品</a></li>
              <li><a href="#">收藏的店铺</a></li>
          	</ul>
      		</li>
      		<li class="y_headrli">
      			<span data-toggle="dropdown" class="dropdown-toggle">商家支持 <i class="fa fa-angle-down"></i></span>
      			<s></s>
            <div role="menu" class="dropdown-menu y_stodrop">
              <dl class="y_topdropdl">
              	<dt>商家</dt>
              	<dd><a href="#">商家中心</a></dd>
              	<dd><a href="#">商家入驻</a></dd>
              	<dd><a href="#">商家工具</a></dd>
              	<dd><a href="#">商家规则</a></dd>
              </dl>
              <p class="y_topline"></p>
              <dl class="y_topdropdl">
              	<dt>帮助</dt>
              	<dd><a href="#">帮助中心</a></dd>
              </dl>
          	</div>
      		</li>
      		<li class="y_headrli"><a href="#" class="y_plr10">会员俱乐部</a></li>
      		<li class="y_headrli">
      			<span data-toggle="dropdown" class="dropdown-toggle"><i class="fa fa-th-list"></i> 网站导航 <i class="fa fa-angle-down"></i></span>
      			<s></s>
            <div role="menu" class="dropdown-menu y_wzdhdrop">
              <p class="y_drophot"><a href="#">月饼</a><a href="#">秋装</a><a href="#">团购</a><a href="#">母婴</a><a href="#">0元乐购</a></p>
              <p class="y_topline"></p>
              <dl>
              	<dt>购物</dt>
              	<dd><a href="#">服饰</a></dd>	
              	<dd><a href="#">图书</a></dd>	
              	<dd><a href="#">办公直通车</a></dd>	
              	<dd><a href="#">视频购物</a></dd>	
              	<dd><a href="#">品牌街</a></dd>	
              	<dd><a href="#">礼品卡</a></dd>	
              	<dd><a href="#">电子书</a></dd>	
              </dl>
              <p class="y_topline"></p>
              <dl>
              	<dt>生活</dt>
              	<dd><a href="#">商旅</a></dd>	
              	<dd><a href="#">保险</a></dd>	
              	<dd><a href="#">彩票</a></dd>	
              	<dd><a href="#">水电煤</a></dd>	
              	<dd><a href="#">游戏</a></dd>	
              	<dd><a href="#">PPTV下载</a></dd>
              </dl>
              <p class="y_topline"></p>
              <dl>
              	<dt>服务</dt>
              	<dd><a href="#">应用商店</a></dd>	
              	<dd><a href="#">全网云</a></dd>	
              	<dd><a href="#">社区</a></dd>	
              	<dd><a href="#">客户端</a></dd>	
              	<dd><a href="#">对公销售</a></dd>	
              	<dd><a href="#">会员联盟</a></dd>	
              	<dd><a href="#">商家入驻</a></dd>	
              </dl>
              <p class="y_topline"></p>
              <p>服务热线：<span class="f_color1">400-6060-980</span></p>
          	</div>
      		</li>
        </ul>
    </div>
</div>

<div class="header-container">
	<div class="container">
		<h1 class="y_logo">
          <a title="aebiz" href="index.html"><img src="../static/frontpage/img/adimg/logo.png"></a>
        </h1>
		<div class="w_page-title">
		  <h2>${questionnaire.title}</h2>
		</div>
	</div>
</div>

<div class="questionnaire-container">
	<div class="container">
		<form id="mainForm" action="${pageContext.servletContext.contextPath}/questionnairesurvey/doQuestionnaireSurvey" method="post">
			<input type="hidden" name="customerUuid" value="${customerUuid}">
			<input type="hidden" name="questionnaireUuid" value="${questionnaire.uuid}">
			
			<div class="questionnaire-wrapper">
			    <div class="qu-guide-words">
					<p>${questionnaire.introduce}</p>
			    </div>
			  	
			  	<c:forEach items="${questions}" var="question" varStatus="s">
			  		<c:choose>
			  			<c:when test="${question.questionType=='1'}">
			  				<div class="ff-wrapper">
								<div class="tr-title">
								    <h3><b class="icon-import">*</b>${question.title}</h3>
								</div>
								<div class="ff-con">
								   <ul>
								   	  <c:if test="${!empty question.optionsModels}">
							   	  		 <c:forEach items="${question.optionsModels}" var="option">
							   	  		 	<li>
								   	  		 	<input type="radio" class="li-input" id="${option.uuid}" name="${question.uuid}" value="${option.optionValue}">
								   	  		 	<label class="li-label" for="${option.uuid}">${option.optionTitle}</label>
							   	  		 	</li>
							   	 		 </c:forEach>
								   	  </c:if>
								   </ul>
								</div>
							 </div>
			  			</c:when>
			  			
			  			<c:when test="${question.questionType=='2'}">
			  				<div class="ff-wrapper">
								<div class="tr-title has-will">
									<h3><b class="icon-import">*</b>${question.title}</h3>
								</div>
								<div class="ff-con">
								   <ul>
								   	  <c:if test="${!empty question.optionsModels}">
							   	  		 <c:forEach items="${question.optionsModels}" var="option">
							   	  		 	<li>
								   	  		 	<input type="checkbox" class="li-input" id="${option.uuid}" name="${question.uuid}" value="${option.optionValue}">
								   	  		 	<label class="li-label" for="${option.uuid}">${option.optionTitle}</label>
							   	  		 	</li>
							   	 		 </c:forEach>
								   	  </c:if>
								   </ul>
								</div>
							</div>
			  			</c:when>
			  			
			  			<c:when test="${question.questionType=='3'}">
			  				<div class="ff-wrapper">
							   <div class="tr-title">
							     <h3>${question.title}</h3>
							   </div>
							   <div class="ff-con">
								   <div><textarea class="textarea" id="${question.uuid}" name="${question.uuid}"></textarea></div>
							   </div>
							 </div>
			  			</c:when>
			  			
			  			<c:otherwise>
			  				<div class="ff-wrapper">
								<div class="tr-title has-will">
									<h3><b class="icon-import">*</b>${question.title}</h3>
								</div>
								<div class="ff-con">
								   <ul>
								   	  <c:if test="${!empty question.optionsModels}">
							   	  		 <c:forEach items="${question.optionsModels}" var="option">
							   	  		 	<li>
								   	  		 	<input type="checkbox" class="li-input" id="${option.uuid}" name="${question.uuid}" value="${option.optionValue}">
								   	  		 	<label class="li-label" for="${option.uuid}">${option.optionTitle}</label>
							   	  		 	</li>
							   	 		 </c:forEach>
								   	  </c:if>
									  <li>
										  <input type="checkbox" class="li-input" id="${s.count}">
										  <label class="li-label" for="${s.count}">其他，请填写:</label>
										  <input type="text" class="li-input" name="${question.uuid}">
									  </li>
								   </ul>
								</div>
						    </div>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:forEach>	 
			  
			  	<div class="row row-submit">
					<button type="submit" class="btn btn-warning">提交</button>
			    </div>
			</div>
		</form>
   </div>
</div>   

<div class="footer simple_footer">
	<div class="footer-bottom">
	  <div class="container">
	    <a href="#">全网简介</a>|<a href="#">全网公告</a>|<a href="#">招纳贤士</a>|<a href="#">联系我们</a>|<span class="y_kfrx">客服热线：400-828-1878</span>
			<p>Copyright © 2005 - 2014 全网版权所有   <span>京ICP备06024200号</span><span>沪ICP证B2-20130044号</span>   北京市公安局宝山分局备案编号：3101130646</p>
	  </div>
	</div>
</div>

<script src="${pageContext.servletContext.contextPath}/static/frontpage/js/bootstrap.js"></script>

</body>
</html>