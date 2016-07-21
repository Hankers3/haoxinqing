<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<c:forEach items="${webList}" var="item">
	<div class="y_flmodalbx box box-color box-bordered">
		<div class="box-title">
			<h3 class="check-line">
				<input type="checkbox" id="cqxa" name="cqxa" class='icheck-me' data-skin="square" data-color="blue">
				<label class='inline' for="cqxa">${item.productTemplate.name}</label>
			</h3>
			<div class="actions">
				<a class="btn btn-mini content-slideUp"><i class="fa fa-angle-down"></i></a>	
			</div>
		</div>
		<div class="box-content nopadding">
			<div class="y_chsmtit">
				<span class="text-left">属性</span>
				<span>英文名</span>
				<span>是否属性值</span>
				<span>下拉框</span>
				<span>位置</span>
			</div>
			<!--二级分类-->
			<c:forEach items="${item.productAttributs}" var="productAttributs">
			<dl>
				<dt class="y_clear">
					<div class="check-line">
						<input type="checkbox" id="cqx1" name="cqx1" class='icheck-me' data-skin="square" data-color="blue">
						<label class='inline' for="cqx1">颜色</label>
					</div>
					<div class="y_attr1">color</div>
					<div class="y_attr2">
						<div class="check-line">
							<input type="radio" id="xyes1" name="xyes1" class='icheck-me' data-skin="square" data-color="blue">
							<label class='inline' for="xyes1">是</label>
						</div>
						<div class="check-line">
							<input type="radio" id="xno1" name="xyes1" class='icheck-me' data-skin="square" data-color="blue">
							<label class='inline' for="xno1">否</label>
						</div>
					</div>
					<div class="y_attr3"><select class="form-control"><option>文本框</option></select></div>
					<div class="y_attr4"><input class="form-control" type="text" value="1"></div>
				</dt>
				<!--三级分类-->
				<dd class="y_clear">
					<ul class="y_clear">
						<li class="check-line">
							<input type="checkbox" id="cqxa1111" name="cqx1" class='icheck-me' data-skin="square" data-color="blue">
							<label class='inline' for="cqxa1111">红色</label>
							<!--四级分类-->
							<ul class="y_sxmdthrel y_clear">
								<li class="y_sxmst"></li>
								<li class="check-line">
									<input type="checkbox" id="cqx211" name="cqx1" class='icheck-me' data-skin="square" data-color="blue">
									<label class='inline' for="cqx211">淡红色</label>	
								</li>
								<li class="check-line">
									<input type="checkbox" id="cqx212" name="cqx1" class='icheck-me' data-skin="square" data-color="blue">
									<label class='inline' for="cqx212">深红色</label>	
								</li>
							</ul>
						</li>
						<li class="check-line">
							<input type="checkbox" id="cqxa1112" name="cqx1" class='icheck-me' data-skin="square" data-color="blue">
							<label class='inline' for="cqxa1112">绿色</label>	
						</li>
						<li class="check-line">
							<input type="checkbox" id="cqxa1113" name="cqx1" class='icheck-me' data-skin="square" data-color="blue">
							<label class='inline' for="cqxa1113">蓝色</label>	
						</li>
						<li class="check-line">
							<input type="checkbox" id="cqxa1114" name="cqx1" class='icheck-me' data-skin="square" data-color="blue">
							<label class='inline' for="cqxa1114">黄色</label>	
						</li>
					</ul>
				</dd>
			</dl>
			</c:forEach>
		</div>
</c:forEach>

<script>
	$(function(){
			//鼠标移上展示4级分类
			$(".y_flmodalbx dd > ul > li").hover(function(){
	    	$(this).addClass("y_hover");
	    },function(){
	    	$(this).removeClass("y_hover");
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