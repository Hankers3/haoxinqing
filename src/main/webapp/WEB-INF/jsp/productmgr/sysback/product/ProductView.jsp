<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

	<%@include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp"%>
	<!-- Custom file upload   -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>
</head>

<body>
	<div class="container-fluid" >
		<div class="page-header">
			<div class="pull-left">
				<h1>
					药品详细信息
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span>商品系统</span> <i class="fa fa-angle-right"></i></li>
				<li><span>普通商品管理</span> <i class="fa fa-angle-right"></i></li>
			
			</ul>
		</div>
		
		<div class="box box-bordered  bordered-top">
				<div class="box-content nopadding">
					<form action="#" method="POST" class='form-horizontal form-bordered form-validate'  >
						<div class="form-group">
									<div class="form-group" style="border-right: 1px solid #ddd;">
				    <div class="col-sm-12">
					   <i class="fa fa-user"></i>基本信息
				   </div>
		     		</div>
    			<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">药品名称(通用名)</label>
				<div class="col-sm-10">
				${m.productName}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">药品名称(英文名称)</label>
				<div class="col-sm-10">
				${m.productEnglishName}
				</div>
				</div>
	  			<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">药品类别</label>
				<div class="col-sm-10">
				${m.productType}
				</div>
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">常见治疗适应症</label>
				<div class="col-sm-10">
				${m.commonremedy}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">作用机制</label>
				<div class="col-sm-10">
				${m.mechanismAction}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">应该做的化验检查</label>
				<div class="col-sm-10">
				${pdm.laboratorExamination}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">用法</label>
				<div class="col-sm-10">
				${m.direction}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">用量</label>
				<div class="col-sm-10">
				${m.dosage}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">药物导致的不良反应机制</label>
				<div class="col-sm-10">
				${m.drugReaction}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">值得注意的不良反应</label>
				<div class="col-sm-10">
				${m.mildDrugReaction}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">危及生命或者危险的不良反应</label>
				<div class="col-sm-10">
				${m.dangerousDrugReaction}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">禁用</label>
				<div class="col-sm-10">
				${m.forbidden}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">注意事项</label>
				<div class="col-sm-10">
				${pdm.attention}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">肾功能损害患者</label>
				<div class="col-sm-10">
				${m.renalFunctionDamage}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">肝功能损害患者</label>
				<div class="col-sm-10">
				${m.liverFunctionDamage}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">心功能损害患者</label>
				<div class="col-sm-10">
				${m.cardiacDysfunction}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">老年人</label>
				<div class="col-sm-10">
				${m.oldPeople}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">儿童和青少年</label>
				<div class="col-sm-10">
				${m.youngsters}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">妊娠</label>
				<div class="col-sm-10">
				${m.conception}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">哺乳</label>
				<div class="col-sm-10">
				${m.suckle}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">药物相互作用</label>
				<div class="col-sm-10">
				${pdm.drugInteractio}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">药物过量</label>
				<div class="col-sm-10">
				${m.overDose}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">主要靶症状</label>
				<div class="col-sm-10">
				${m.targets}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">长期使用</label>
				<div class="col-sm-10">
				${m.longRun}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">成瘾性</label>
				<div class="col-sm-10">
				${m.addiction}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">如何停药</label>
				<div class="col-sm-10">
				${m.stopMedicine}
				</div>
				</div>
				<div class="form-group">
				<label for="textfield" class="control-label col-sm-2">药代动力学</label>
				<div class="col-sm-10">
				${m.pharmacokinetics}
				</div>
				</div>
				</div>
				</div>
		</div>
 
	</div>
	</form>
		    
			  <div class="y_fixedbtn">
				 	<button type="button" class="btn btn-primary cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
			  </div>	
				
	
</body>

</html>
	<script>
	$(".cancel").click(function(){
				history.go(-1) ;
			});	
</script>