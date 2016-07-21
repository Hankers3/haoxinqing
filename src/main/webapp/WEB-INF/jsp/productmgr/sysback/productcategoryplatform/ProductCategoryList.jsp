<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<!-- Apple devices fullscreen -->
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<!-- Apple devices fullscreen -->
	<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />

	<title>FLAT - Dynamic tables</title>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/bootstrap.min.css">
	<!-- dynatree CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/dynatree/ui.dynatree.css">
	<!-- Theme CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/style.css">
	<!-- 美化单选、复选框样式 -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
	<!-- 字体图标ie7兼容性处理 -->
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/font-awesome-ie7.min.css">
	<![endif]-->
	<!-- Color CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/themes.css">
	<!-- aebiz CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/aebiz-0.1.css">
	
	<!-- jQuery -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/bootstrap.min.js"></script>
		<!-- Bootbox -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/bootbox/jquery.bootbox.js"></script><!-- 点击删除表数据时的弹出提示框 -->
	<!-- jQuery UI -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.widget.min.js"></script>
  <!-- Filetree -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/dynatree/jquery.dynatree.js"></script>
	<!-- 单选、复选框的美化js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
	<!-- 模拟上传按钮插件js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
	
	<!-- 单选复选框美化样式的调用js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
	<!-- 右侧主体页面的公共css 公共js，都引 -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz_main-0.1.js"></script>

	<!--[if lte IE 9]>
		<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/placeholder/jquery.placeholder.min.js"></script>
		<script>
			$(document).ready(function() {
				$('input, textarea').placeholder();
			});
		</script>
	<![endif]-->
	
	<!--[if lte IE 8]>
    <script src="${pageContext.servletContext.contextPath}/static/sysback/js/respond.min.js"></script>
  <![endif]-->	

	<!-- Favicon -->
	<link rel="shortcut icon" href="img/favicon.ico" />
	<!-- Apple devices Homescreen icon -->
	<link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-precomposed.png" />

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="productcategoryplatform.menuTwo"/></h1>
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
					<span><aebiz:showTitle titleId="productcategoryplatform.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="productcategoryplatform.mgr"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="productcategoryplatform.menuTwo"/></span>
				</li>
			</ul>
			
		</div>
		<!--商品分类管理-->
		<div class="row y_classmage">
			<div class="col-sm-3 y_classtre">
				<div class="box">
					<div class="box-title"><h3><i class="fa fa-file"></i><aebiz:showTitle titleId="productcategoryplatform.title.category"/></h3></div>
					<div class="box-content">
						<p class="y_prdcumanp">
							<a class="btn btn-primary mb_10" id="categoryAdd"><aebiz:showTitle titleId="productcategoryplatform.title.addCategory"/></a>
							<a class="btn btn-primary mb_10" id="categoryBatchAdd"><aebiz:showTitle titleId="productcategoryplatform.title.batchAddCategory"/></a>
						</p>
						 <div id="tree"> </div>
					</div>
				</div>
				<input type="hidden" name="selectUuid" id="selectUuid" />
				
			</div>
			<div class="col-sm-9 y_classedit" id="editPage">
				<div class="fl_ts_pic"></div>
			</div>
		</div>
		<!--商品分类管理 end -->
	</div>
	
	
	<!--选择关联品牌弹出框-->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel">品牌列表</h4>
	      </div>
	      <div class="modal-body relation_brand">
					<div class="brand_search">
						<div class="fl">品牌：</div><div class="col-sm-3"><input type="text" class="form-control " id="mBrandSK" placeholder="可搜拼音、汉字查找品牌"/></div>
						<button type="button" class="btn btn-primary">搜索</button>
					</div>
					<div class="box-title">
						<ul class="tabs">
							<li class="active">
								<a href="#all" data-toggle="tab">所有品牌</a>
							</li>
							<li>
								<a href="#aaa" data-toggle="tab">A</a>
							</li>
							<li>
								<a href="#bbb" data-toggle="tab">B</a>
							</li>
						</ul>
					</div>
					<div class="box-content">
						<div class="tab-content">
							<div class="tab-pane active brand_show" id="all">
								<ul class="list">
		            	<li >
		            		<a href="#" title="" class="condition-name-in">
				              <input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt="">
				            </a>
				            <div>
				            	<b></b>
					            <dl>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            </dl>
				            </div>
		            	</li>
		            	<li>
		            		<a href="#" title="" class="condition-name-in">
				              <input type="checkbox" class="" /><span>苹果(Apple)</span>
				            </a>
				            
				            <div>
				            	<b></b>
					            <dl>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            </dl>
				            </div>
		            	</li>
		            	<li>
		            		<a href="#" title="" class="condition-name-in">
				              <input type="checkbox" class="" /><span>苹果(Apple)</span>
				            </a>
				            <div>
				            	<b></b>
					            <dl>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            </dl>
				            </div>
		            	</li>
		            	<li>
		            		<a href="#" title="" class="condition-name-in">
				              <input type="checkbox" class="" /><span>苹果(Apple)</span>
				            </a>
		            	</li>
		            	<li>
		            		<a href="#" title="" class="condition-name-in">
				              <input type="checkbox" class="" /><span>苹果(Apple)</span>
				            </a>
		            	</li>
		            	<li>
		            		<a href="#" title="" class="condition-name-in">
				              <input type="checkbox" class="" /><span>苹果(Apple)</span>
				            </a>
		            	</li>
		            	<li>
		            		<a href="#" title="" class="condition-name-in">
				              <input type="checkbox" class="" /><span>苹果(Apple)</span>
				            </a>
		            	</li>
		            	<li>
		            		<a href="#" title="" class="condition-name-in">
				              <input type="checkbox" class="" /><span>苹果(Apple)</span>
				            </a>
		            	</li>
		            </ul>
								<div class="btn-group brand_page">
									<ul class="pagination pagination-custom">
										<li><a href="#"><i class="fa fa-angle-double-left"></i></a></li>
										<li><a href="#">1</a></li>
										<li class='active'><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
										<li><a href="#"><i class="fa fa-angle-double-right"></i></a></li>
									</ul>
								</div>
							</div>
							<div class="tab-pane brand_show" id="aaa">
								<ul class="list">
		            	<li >
		            		<a href="#" title="" class="condition-name-in">
				              <input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt="">
				            </a>
				            <div>
				            	<b></b>
					            <dl>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            	<dd><input type="checkbox" class="" /><img src="../img/y_brd1.gif" alt=""></dd>
					            </dl>
				            </div>
		            	</li>
		            	<li>
		            		<a href="#" title="" class="condition-name-in">
				              <input type="checkbox" class="" /><span>苹果(Apple)</span>
				            </a>
		            	</li>
		            </ul>
								<div class="btn-group brand_page">
									<ul class="pagination pagination-custom">
										<li><a href="#"><i class="fa fa-angle-double-left"></i></a></li>
										<li><a href="#">1</a></li>
										<li class='active'><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
										<li><a href="#"><i class="fa fa-angle-double-right"></i></a></li>
									</ul>
								</div>
							</div>
							<div class="tab-pane brand_show" id="bbb">
								bbbbb
							</div>
						</div>
					</div>
					
					
  			</div>
			  <div class="modal-footer">
			  	<button type="button" class="btn btn-primary" data-dismiss="modal">关联</button>
			  </div>
			</div>
		</div>
	</div><!--选择关联品牌弹出框 end-->

	<!--选择规格属性弹出框-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
			  
			</div>
		</div>
	</div><!--选择规格属性弹出框 end-->
	
	<script>
		
		$("#categoryBatchAdd").click(function(){
			if($("#selectUuid").val()==""){
				bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.selectCategory"/>');
				return;
			}	
			$("#editPage").load("${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/toBatchAdd?time="+Math.random());
			var tree=$("#tree").dynatree("getTree");
			node=tree.getActiveNode();
			node.deactivate();
		})
		
		$("#categoryAdd").click(function(){
			
			if($("#selectUuid").val()==""){
				bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.selectCategory"/>');
				return;
			}
			
			$("#editPage").load("${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/toAdd?time="+Math.random());
			
			var tree=$("#tree").dynatree("getTree");
			node=tree.getActiveNode();
			node.deactivate();
		
		});
		
	 
		
		
		$(function(){
			//关联品牌弹出框里的js
			$(".list li").hover(function(){
					$(this).addClass("li_poti");	
					$(this).children("div").show();	
					$(this).children("div").find("dd").fadeIn(500);	
				},function(){
					$(this).removeClass("li_poti");	
					$(this).children("div").hide();	
					$(this).children("div").find("dd").fadeOut(500);
			});
		})
		
		 $(function(){
    
    $("#tree").dynatree({
			title: "Lazy Tree",
			rootVisible: true,
			fx: { height: "toggle", duration: 300 },
			initAjax: {
				type: 'GET',
				url: "${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/getNodes",
				data: {key:""},
				dataType: "json",
				contentType: 'application/json; charset=utf-8'
			
			},
			
			onActivate: function (dtnode) {
				$("#selectUuid").val(dtnode.data.key);
				
				$("#editPage").load("${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/toUpdate/"+dtnode.data.key+"?time="+Math.random());
			}
			
			, onLazyRead: function (dtnode) {
				var key=dtnode.data.key;
				dtnode.appendAjax({
					type: 'GET',
					url: "${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/getNodes",
					data: {key:key},
					dataType: "json",
					contentType: 'application/json; charset=utf-8'
				});
			}
		
			
			
		});
    
    
    $("#tree1").dynatree({
      // Pass an array of nodes (and child nodes)
      children: [
        {title: "Folder 2", key: "folder2" ,isFolder: true,children:[{}]}
      ],
      onActivate: function(dtnode) {
        // This function is called, when a node is clicked
        // A DynaTreeNode object is passed as argument.
        alert("You activated " + dtnode.data.title);
      }
    });
  });
	</script>
</body>
</html>