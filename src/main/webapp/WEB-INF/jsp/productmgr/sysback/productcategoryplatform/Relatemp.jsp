<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!-- icheck -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
	<!-- Theme CSS FLAT模版基本样式，每个页面都引 -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/style.css">
	<!-- 字体图标ie7兼容性处理 -->
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/font-awesome-ie7.min.css">
	<![endif]-->
	<!-- Color CSS FLAT模版皮肤样式，每个页面都引-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/themes.css">
	<!-- aebiz CSS 产品自定义样式，每个页面都引-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/aebiz-0.1.css">

	<!-- jQuery 库 ， 每个页面都引-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/bootstrap.min.js"></script>
	<!-- icheck -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
	<!-- 单选复选框美化样式的调用js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
	<!-- 右侧主体页面的公共css 公共js，都引 -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz_main-0.1.js"></script>
	
	 <!--[if lte IE 8]>
    <script src="${pageContext.servletContext.contextPath}/static/sysback/js/respond.min.js"></script>
  <![endif]-->	

	<!--关联权限模块-->
 	<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title" id="myModalLabel">规格属性列表</h4>
  </div>
  <div class="modal-body">
  	<dl class="y_chosmdl">
  		<dt>选择模版：</dt>
  		<dd>
  			<c:forEach items="${templates}" var="item">
  			<div class="check-line">
					<input type="checkbox"  name="templateUuid" class='icheck-me' data-skin="square" data-color="blue" value="${item.uuid}">
					<label class='inline'  >${item.name}</label>
				</div>
				</c:forEach>
  		</dd>
  	</dl>
  	
  	<!--服装分类模版-->
		<div id="showList">
			
		</div>
	</div>
		 
	</div>
 	<div class="modal-footer">
 		<a class="btn btn-primary" href="javascript:test();">保存</a>
  </div>
	<!--关联权限模块end-->
	<!--后台权限设置页面js-->
	<script>
		
		var checkIds="";
		$("input[name='templateUuid']:checkbox").each(function() {
			$(this).on('ifChecked', function(event){
				checkIds += $(this).val() + ",";
		  	showList();
			});
			
			$(this).on('ifUnchecked', function(event){
				checkIds=checkIds.replace($(this).val() + ",","");
		  	showList();
			});
		})
		
		
		function showList(){
			$.ajax({
				"dataType" : 'html',
				"type" : "GET",
				"url" : "${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/attributeList",
				"data" : {
					templateUuids :checkIds,
					time:Math.random()
				},
				"success" : function(html){
					$("#showList").html(html);
				}
			});
		}
	
		
		
		
		$(function(){
			//鼠标移上展示4级分类
			$(".y_flmodalbx dd > ul > li").hover(function(){
	    	$(this).addClass("y_hover");
	    },function(){
	    	$(this).removeClass("y_hover");
	    });
	    
		  //选择模版事件
		  $(".y_chosmdl input").each(function() {
		  	$(this).iCheck('uncheck'); 
				var y_index=$(this).parents(".check-line").index();
				var y_chose=$(".y_flmodalbx").eq(y_index);
				$(this).on('ifChecked', function(){
          y_chose.show();
        });
        $(this).on('ifUnchecked', function(){
          y_chose.hide();
        });
			});
	  
	    //分类关联模版，属性值收缩、隐藏
		  $(".y_flmodalbx dt > .check-line input").each(function() {
				$(this).iCheck('uncheck');
				var y_cgbox=$(this).parents("dt").next("dd");
				$(this).on('ifChecked', function(){
          y_cgbox.slideDown();
        });
        $(this).on('ifUnchecked', function(){
          y_cgbox.slideUp();
        });
			});
		})
	</script>
	

