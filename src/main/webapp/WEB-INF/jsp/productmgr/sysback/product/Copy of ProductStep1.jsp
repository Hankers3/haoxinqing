<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<%@include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script> 

</head>
<body>
	

			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1>发布商品</h1>
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
							<span>发布商品</span>
						</li>
					</ul>
				</div>
				
				<div class="row">
					<div class="col-sm-12">
						<form action="${pageContext.servletContext.contextPath}/sysback/product/toStep2" method="POST" class='form-horizontal form-bordered' id="mainForm">
						 	<input type="hidden" name="updateProductType" id="updateProductType" value="${web.type}" >
						 	<input type="hidden" name="productUuid" id="productUuid" value="${web.productUuid}" >	
						 	<input type="hidden" name="categoryUuid" id="categoryUuid"/>
						 	<input type="hidden" name="isRoot" id="isRoot" />
							
							<div class=select_lx nopadding ">
									<label class="bt">请选择商品类型<i class="glyphicon-chevron-right"></i></label>
									<div class="bg">
										<span>
											<div class="check-line">
												<input type="radio" id="1" class='icheck-me' name="productType" data-skin="flat" data-color="blue" value="01" checked onclick="javascript:changeBox(this.value)">
												<label class='inline' for="1">家政商品</label>
											</div>
										</span>		
										<!-- 
										<span>
											<div class="check-line">
												<input type="radio" id="2" class='icheck-me' name="productType" data-skin="flat" data-color="blue"  value="02"  onclick="javascript:changeBox(this.value)">
												<label class='inline' for="2">月嫂商品</label>
											</div>
										</span>		 -->
									</div>
							</div>	
								
							<div class="box">
								<div class="box-title mb_20"><h3><aebiz:showTitle titleId="productmain.m.categoryUuid" /> </h3></div>
								<div class="box-content nopadding">
									<div class="y_chogoodclass">
										<!--
										<div class="box y_serprdbx">
											<input type="text" class="form-control" placeholder="<aebiz:showTitle titleId="productmain.m.pleaseCategoryUuid" />"><a class="btn btn-primary" href="#"><aebiz:showTitle titleId="productmain.m.searchCategoryUuid" /></a>
										</div>-->
										<div class="y_serxzd">
											<aebiz:showTitle titleId="productmain.m.nowCategory" /><span id="fullCategoryName"> </span>
										</div>
										<div class="y_milipdfl  row">
											<div class="col-sm-3">
												<ul id="firstUl">
													
												</ul >
											</div>
											<div class="col-sm-3">
												<ul id="secondUl">
													 
												</ul>
											</div>
											<div class="col-sm-3">
												<ul id="thirdUl">
												 
												</ul>
											</div>
											<div class="col-sm-3">
												<ul id="fourthUl">
													 
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="y_fixedbtn">
								<a class="btn btn-primary btn-large a_size_1" href="javascript:submitForm();">下一步</a>													
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>


	<script>
		
		
		
		function selectCategory(){
			if("true"!=$("#isRoot").val()){
				bootbox.alert("请选择分类到最后级别");
				return;
			}
			var uuid=$("#categoryUuid").val();
			$.getJSON(
				"${pageContext.servletContext.contextPath}/sysback/product/getAllPathName",
				{
					"uuid" : uuid,
					ranNum : Math.random()
				},
				function(data) {
					  $("#fullCategoryName").html(data.pathName);
			  }
				 
			);
			
			$("#attribute").load("${pageContext.servletContext.contextPath}/sysback/product/toAttribute");
			
			$(".y_choselbbx").hide();	
		}
		
		function initCategory(search,parentUuuid,id,obj){
				$(obj).addClass("cur").siblings().removeClass("cur");
				$("#categoryUuid").val(parentUuuid);
				$.getJSON(
				"${pageContext.servletContext.contextPath}/sysback/product/categoryList",
				{
					"parentUuuid" : parentUuuid,
					"grade": id,
					ranNum : Math.random()
				},
				function(data) {
				 var grade =data.grade;
				 var arryList =data.categoryList;
				 if(data.isRoot){
				 	$("#isRoot").val("true")
				 }else{
					$("#isRoot").val("false")
				 }
				 returnStr="";
				 for(var i=0;i<arryList.length;i++){  
						returnStr+="<li style='cursor:pointer' onclick='javascript:initCategory(\"\",\""+arryList[i].uuid+"\",\""+grade+"\",this);'>"+arryList[i].categoryName+"</li>";
			   }
			   $("#"+id).html(returnStr);
				 
				});
				

				$("#categoryUuid").val(parentUuuid);
				$.getJSON(
					"${pageContext.servletContext.contextPath}/sysback/product/getAllPathName",
					{
						"uuid" : parentUuuid,
						ranNum : Math.random()
					},
					function(data) {
							
						  $("#fullCategoryName").html(data.pathName)
				  }
					 
				);
		
		}
		
		function changeBox(val){
			if("2"==val){
				$("#bendi").removeClass("y_prdxxgl");
				$("#price").addClass("y_prdxxgl");
			}else if("1"==val){
				$("#price").removeClass("y_prdxxgl");
				$("#bendi").addClass("y_prdxxgl");
			}else{
				$("#bendi").addClass("y_prdxxgl");
				$("#price").addClass("y_prdxxgl");
			}
		}
		
		function submitForm(){
			
			var productType=$("input[name='productType']").val();
			
			if(""==productType){
				bootbox.alert("请选择商品类型!");
				return;
			}
			var isRoot =  $("#isRoot").val();
			if("false"==isRoot){
				bootbox.alert("请选择分类到最后一级!");
				return;
			}
		 
			$("#mainForm").submit();
		} 
		
		
		$(function(){
		
			initCategory("init","","firstUl");
			//搜索结果点击返回到分类
			$(".y_closeserbx").click(function(){
				$(this).parents(".y_miliserch").hide().prev(".y_milipdfl").show();
				return false;
			});
			//选择商品类型切换的js
			$(".y_chotypes .y_chobox .btn").click(function(){
				$(this).parent().addClass("active").siblings().removeClass("active");
			});
			$(".y_typesbox label").click(function(){
				$(this).addClass("active").siblings().removeClass("active");
			});
		});
	</script>
</body>

</html>
