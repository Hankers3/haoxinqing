<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
	<head>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<!-- Apple devices fullscreen -->
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<!-- Apple devices fullscreen -->
	<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
	<title>FLAT - Dynamic tables</title>
	
	
	<!-- Bootstrap 公共css，都引-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/bootstrap.min.css">
	<!-- chosen -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/chosen/chosen.css"><!--模拟下拉框的css-->
	<!-- datepicker -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css"><!--日历选择插件的css-->
	<!-- Theme CSS FLAT模版基本样式，公共css，都引 -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/style.css">
	<!-- 字体图标ie7兼容性处理 -->
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/font-awesome-ie7.min.css">
	<![endif]-->
	<!-- Color CSS  公共css，都引-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/themes.css">
	<!-- aebiz CSS  公共css，都引-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/aebiz-0.1.css">
	<!-- icheck -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
	<!-- XEditable -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/xeditable/bootstrap-editable.css">

	<!-- jQuery 库 ，公共js，都引-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/jquery.min.js"></script>
	<!-- Bootstrap 框架的js，公共js，都引-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/bootstrap.min.js"></script>
	<!-- icheck -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
	<!-- Bootbox -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/bootbox/jquery.bootbox.js"></script><!-- 点击删除表数据时的弹出提示框 -->
	<!-- dataTables -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/jquery.dataTables.min.js"></script><!--基本的动态数据表插件-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/ColReorderWithResize.js"></script><!--动态数据表格列可拖动的插件-->
	<!-- datepicker-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script><!-- 日历插件的js -->
	<!-- Chosen -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/chosen/chosen.jquery.min.js"></script><!--基本的模拟下拉框和动态表的选择显示条数下拉框插件-->
	<!-- dataTable use-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.dataTable.js"></script><!-- 调用动态表的js -->	
	<!-- datepicker use-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script><!-- 调用日历插件的js -->
	<!-- 直接修改 -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/momentjs/jquery.moment.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/mockjax/jquery.mockjax.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/xeditable/bootstrap-editable.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/xeditable/demo.js"></script>
	<!-- 右侧主体页面的公共css 公共js，都引-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz_main-0.1.js"></script>
	<!-- 浮动按钮的js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>
	

</head>
<body>
<div class="container-fluid">
	<div class="page-header">
		<div class="pull-left">
			<h1>经营城市</h1>
		</div>
		
		<!--<div class="pull-right">
			<ul class="minitiles">
				<li class='grey'>
					<a href="#">
						<i class="fa fa-cogs"></i>
					</a>
				</li>
				<li class='lightgrey'>
					<a href="#">
						<i class="fa fa-globe"></i>
					</a>
				</li>
			</ul>
			<ul class="stats">
				<li class='satgreen'>
					<i class="fa fa-money"></i>
					<div class="details">
						<span class="big">$324,12</span>
						<span>Balance</span>
					</div>
				</li>
				<li class='lightred'>
					<i class="fa fa-calendar"></i>
					<div class="details">
						<span class="big">February 22, 2013</span>
						<span>Wednesday, 13:56</span>
					</div>
				</li>
			</ul>
		</div>-->
		
		
	</div>
	<div class="breadcrumbs">
		<ul>
			<li>
				<span>商品系统</span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span>商品管理</span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span>经营城市</span>
			</li>
		</ul>
	</div>
	<ul class="tabs tabs-inline tabs-top border1-top">

		<!--<li class='active'>-->

	<!--
		<li class='active'>

			<a href="#Product1" data-toggle='tab'>家政商品</a>
		</li>-->
		
		<!--<li>
			<a href="#Product2" data-toggle='tab'>月嫂商品</a>
		</li>-->
	</ul>			
	<div class="tab-content y_tabdatable">
		<div class="tab-pane active" id="Product1">
			<form class="form-inline2" id="searchForm" action="${pageContext.servletContext.contextPath}/sysback/productcity/query" >
			<input type="hidden" name="pageShow" value="${wm.pageShow}">	
				<div class="y_clear">
					<div class="form-inline table_formnew">
						<div class="form-group">	
							<label class="control-label">城市名称：</label>
							<input type="text" name="cityName" id="cityName" class="form-control ">
						</div>
						<div class="form-group">
							<button class="btn btn-primary search" title="查询" rel="tooltip">查询</button>
						</div>
					</div>
				</div>
			</form>
				<table class="table table-bordered table-hover table-striped dataTable-nosort dataTable-reorder" data-nosort="1">
				<thead>		
					<tr>
						<th>城市</th>
						<th>商品</th>
						<th>价格</th>
					</tr>
				</thead>
				<c:forEach items="${wm.rows}" var="productcity" varStatus="status">
					<tbody>
						<c:forEach items="${productcity.productCityList}"  var="pc" varStatus="pcs" >
						<c:if test="${pcs.index+1=='1'}">
							<tr>
							<td rowspan="${fn:length(productcity.productCityList)+1}">${productcity.cityName}</td>
							</tr>
						</c:if>
						<tr>
							<td>${pc.productName}</td>
							<td>
								<div class="col-sm-2 nopadding">
								<input type="text"  onchange="javascript:updateShopPrice('${pc.uuid}',this.value)" class="form-control" value="${pc.shopPrice}"/>	
								</div>
								<div class="col-sm-2 pt_5">
									元/
									<c:choose>
										<c:when test="${pc.chargetype=='1'}"> 
											m<sup>2</sup>
	   								</c:when>
										<c:otherwise>
											台
	  							  </c:otherwise>
									</c:choose>
								</div>
							</td>
						</tr>
					</c:forEach>
					</tbody>	
				</c:forEach>
			</table>
			<aebiz:storePage listPath="sysback/productcity/toList/"/>
		</div>
	</div>
</div>
	<script>
   function updateShopPrice(uuid,value){
   	
   	var con= confirm("是否确认修改?");
    //alert(con);
   	
   	if(con==true){
   	  // alert(con);
   	
   		$.get("${pageContext.servletContext.contextPath}/sysback/productcity/updateShopPrice",{"uuid":uuid,"value":value,ranNum:Math.random()},function(data){
		  	if(data=="true" || data=='true') {
	  			//刷新
				 	bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOkTitle"/>') ;
				 	//doSearch() ;   
	      }else{
	       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
	      }
			});	
			}
   }
  function doSearch(){
  	location.href="${pageContext.servletContext.contextPath}/sysback/productcity/toList/1/20";
  }
  
	$(".search").click(function() {
		$("#searchForm").submit();
	})
	</script>
</body>
</html>
