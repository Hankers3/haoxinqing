<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script>

</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>发布药品</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>基础数据库</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>药品管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>发布药品</span>
				</li>
			</ul>
		</div>
		
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">	
				<form action="${pageContext.servletContext.contextPath}/sysback/product/add" method="POST"  class='form-horizontal form-bordered form-validate' id="mainForm">
						<div class="form-group">
							<div class="col-sm-12">
								<i class="fa fa-list-ul"></i>药品基础信息
							</div>
						</div>
						
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">药品类别</label>
							<div class="col-sm-10">
								<input type="text" name="productMain.productType"  data-rule-required="true" data-rule-maxlength="1500" class="form-control">
							</div>
						</div>
						
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">药品名称（通用名称）</label>
							<div class="col-sm-10">
								<input type="text" name="productMain.productName"  data-rule-required="true" data-rule-maxlength="1500" class="form-control">
							</div>
						</div>
						
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">药品名称（英文名称）</label>
							<div class="col-sm-10">
								<input type="text" name="productMain.productEnglishName"  data-rule-required="true" data-rule-maxlength="1500" class="form-control">
							</div>
						</div>
						
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">常见治疗适应症</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.commonremedy" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">作用机制</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.mechanismAction" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">应该做的化验检查</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productDescription.laboratorExamination" class="form-control" data-rule-maxlength="1500"></textarea>	
							</div>
						</div>
						
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">用法</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.direction" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>		
						
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">用量</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.dosage" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">药物导致不良反应的机制</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.drugReaction" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>	

						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">值得注意的不良反应</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.mildDrugReaction" class="form-control" data-rule-maxlength="1500"></textarea>	
							</div>
						</div>
						
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">危及生命或危险的不良反应</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.dangerousDrugReaction" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">禁用</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.forbidden" class="form-control" data-rule-maxlength="1500"></textarea>	
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">注意事项</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productDescription.attention" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">肾功能损害患者</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.renalFunctionDamage" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">肝功能损害患者</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.liverFunctionDamage" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>
							<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">心功能损害患者</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.cardiacDysfunction" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">老年人</label>
							<div class="col-sm-10">	
								<textarea rows="5" name="productMain.oldPeople" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
							</div>
						
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">儿童和青少年</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.youngsters" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
							</div>
						
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">妊娠</label>
							<div class="col-sm-10">	
								<textarea rows="5" name="productMain.conception" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
							</div>
						
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">哺乳</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.suckle" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">药物相互作用</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productDescription.drugInteractio" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
							</div>
						

						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">药物过量</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.overDose" class="form-control" data-rule-maxlength="1500"></textarea>		
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">主要靶症状</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.targets" class="form-control" data-rule-maxlength="1500"></textarea>			
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">长期使用</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.longRun" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">成瘾性</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.addiction" class="form-control" data-rule-maxlength="1500"></textarea>	
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">如何停药</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.stopMedicine" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>


						<div class="form-group">
							<label for="firstname" class="control-label col-sm-2">药代动力学</label>
							<div class="col-sm-10">
								<textarea rows="5" name="productMain.pharmacokinetics" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>
					</div>
						<div class="y_fixedbtn">
							<a class="btn btn-primary btn-large a_size_1" href="javascript:submitForm();"><aebiz:showTitle titleId="basebusiness.showmessage.add"/></a>													
						</div>
				</form>
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
