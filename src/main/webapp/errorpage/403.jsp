<%@ page session="false" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>前台403</title>
  <meta name="description" content="前台403">
  <!--[if IE]> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <![endif]-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/frontpage/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/frontpage/css/global.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/frontpage/css/login.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/frontpage/css/style.css">
  <!-- 字体图标ie7兼容性处理 -->
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/frontpage/css/font-awesome-ie7.min.css">
	<![endif]-->
	
  <!--- jQuery -->
  <script src="${pageContext.servletContext.contextPath}/static/frontpage/js/jquery-1.11.1.min.js"></script>

  <!--[if lt IE 9]>
    <script src="${pageContext.servletContext.contextPath}/static/frontpage/js/respond.min.js"></script>
  <![endif]-->
  
   <!-- Favicon and Apple Icons -->
  <link rel="icon" type="image/png" href="img/icon.png">
</head>

<body class="bg_f5"><!--body背景色与默认不一样，为f5f5f5-->
	
<!--登录页简单的头部-->
<%@ include file="/WEB-INF/jsp/userfront/common/platSimpleLogoTop.jsp" %>

<!--404-->
<div id="content" class="no-content">
	
	<div class="lg-margin"></div><!-- Space -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="no-content-comment">
	      	<h2>403</h2>
	        <h3>不是我的错，伙计! <br>我想你可能迷路了</h3>
	      </div><!-- End .no-content-comment -->
			</div><!-- End .col-md-12 -->
		</div><!-- End .row -->
	</div><!-- End .container -->
</div><!-- End #content -->

<!--简易底部-->
<%@ include file="/WEB-INF/jsp/userfront/common/platSimpleBottom.jsp" %>

<script src="${pageContext.servletContext.contextPath}/static/frontpage/js/bootstrap.js"></script>
<script>
	$(function() {

        // Simple Animation for 404 text

            var container = $('.no-content-comment'),
                title = container.find('h2'),
                titleText = title.text(),
                message = container.find('h3'),
                messageText = message.text(),
                titleTextLen = titleText.length,
                messageTextLen = messageText.length,
                titleNew = '',
                messageNew = '',
                time = 50;

           
           function iterate(len, text, newVar, target) {
                for (var i=0 ; i < len; i++) {
                    if (text[i] == '!') { // ! important for line breaks
                        newVar += '<span>'+text[i]+'<br></span>' 
                    } else {
                        newVar += '<span>'+text[i]+'</span>';
                    }
               }
               target.html(newVar);
           }


           iterate(titleTextLen, titleText, titleNew, title);

           iterate(messageTextLen, messageText, messageNew, message);


        $(window).on('load', function () {
            
            container.find('span').each(function () {
                time +=80;
                $(this).delay(200).animate({opacity: 1}, time);
            });
            
        });
	});
</script>
</body>
</html>