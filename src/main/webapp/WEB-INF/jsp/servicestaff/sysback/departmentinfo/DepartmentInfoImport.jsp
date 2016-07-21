<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					批量导入科室 
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span>基础数据</span><i class="fa fa-angle-right"></i></li>
				<li><span>科室管理</span><i class="fa fa-angle-right"></i></li>
				<li><span>批量导入科室</span></li>
			</ul>
		</div>
		<form action="${pageContext.servletContext.contextPath}/sysback/departmentinfo/importData" method="POST" enctype="multipart/form-data" class='form-bordered form-validate' id="importForm">
		
			<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding leadbox">
							<!--第一步-->
							<div>
								<h5>&diams;&nbsp;第一步：下载Excel数据制作演示包</h5>
								<div class="form-inline">
									<div class="form-group">
									
									</div>
									<div class="form-group mt10 down_btn">
										
										<button rel="tooltip" type="button" title="" onclick="javascript:downloadTemplate();" class="btn  btn-primary" data-original-title="下载商品模板">
											下载科室模板
										</button>
										<input style="display:none" mce_style="display:none">
										<input type="hidden" name="aaaa" id="aaaa" style="display:none" mce_style="display:none">
									</div>
								</div>
							</div>
							<!--第二步-->
							<div>
								<h5>&diams;&nbsp;第二步：填写Excel文件</h5>
								
							</div>
							<!--第三步-->
							<div  class="upload_con">
								<h5>&diams;&nbsp;第三步：选择填写好的Excel文件</h5>
								<div class="form-inline">	
									<div class="form-group" >
										<label class="control-label">选择Excel文件:</label>
										<div class="form-control" style="padding:0; border:none;">
								    	<div class="fileinput fileinput-new" data-provides="fileinput">
												<div class="input-group">
													<div class="form-control" data-trigger="fileinput " style="width:300px">
														<i class="glyphicon glyphicon-file fileinput-exists"></i>
														<span class="fileinput-filename" style="height: 20px;"></span>
													</div>
													<span class="input-group-addon btn btn-default btn-file">
														<span class="fileinput-new">Select file</span>
														<span class="fileinput-exists">Change</span>
														<input type="file" name="myExcelFile" id="myExcelFile" data-rule-required="true" >
													</span>
												</div>
											</div>
										</div> 
									</div>
						
								</div>
							</div>
							<!--第四步-->
							<div class="y_clear">
								<h5>&diams;&nbsp;第四步：确认无误，导入科室</h5>
							</div>
							<!--导出-->
							<div class="form-actions" style="text-align:center;">
								<button type="button" onclick="javascript:submitImportDataForm()" class="btn btn-primary btn-large a_size_1" id="import1" >导入</button>	
								<button type="button"  class="btn btn-primary btn-large a_size_1"  id="import2" style="display:none">导入请等待</button>	
							</div>
						</div>
					</div>
				</div>
			</div>
	</form>
			<c:if test="${!empty handleResult}">
			<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder" style="width:400px;margin-left:200px">
			<c:choose>
				<c:when test="${empty error}">
					<tr><th  colspan="3">导入成功！</th></tr>
				</c:when>
				<c:otherwise>
					<tr ><th colspan="3">导入失败！</th></tr>
					<tr>
						<th>行</th>
						<th>列</th>
						<th>原因</th>
			   	</tr>
			   	<tr>
						<td>${error.row}</td>
						<td>${error.column}</td>
						<td><aebiz:showTitle titleId="${error.causeKey}" /></td>
			   	</tr>
				</c:otherwise>
			</c:choose>
		</table>
		</c:if>
 </div>

    
</body>
<script type="text/javascript">
	$("#myExcelFile").change(function () {
		$(this).parent("span").siblings(".form-control").children(".fileinput-filename").text($(this).val());
	});
	
	function downloadTemplate () {
		if($('#templateUuid').val() == ''){
			bootbox.alert('请选择要下载的模板！') ;
			$('#templateUuid').focus();
			return ;
		}
		window.location.href="${pageContext.servletContext.contextPath}/sysback/departmentinfo/departmentinfoTemplateExport"; 
	}
	
	function downloadImageTemplate () {
		window.location.href="${pageContext.servletContext.contextPath}/sysback/productimport/exportImageTemplate"; 
	}
	
	function checkFileType(ctrl, ctrlname,fileNameType) {
 		var fileName =ctrl.value;
		if(fileName!=null && fileName!=""){
			if(fileName.lastIndexOf(".") != -1){
				var fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length);
				fileType = fileType.toLowerCase();
				var flag = 0;
				for(var i=0;i<fileNameType.length;i++){
					if(fileType == fileNameType[i]){	
						flag = 1;
					}
				}
				if(flag == 0){
					bootbox.alert(ctrlname);
					return false;
				}else{
					return true;
				}
			}
		}
		return false;
 	}
	
	function submitImportDataForm(){
		if($('#templateUuid').val() == ''){
			bootbox.alert('请选择要导入商品的模板！') ;
			$('#templateUuid').focus();
			return ;
		}

		if(document.getElementsByName('myExcelFile')[0].value==""){
			bootbox.alert("请选择要上传的excel数据文件。");
			document.getElementsByName('myExcelFile')[0].focus();
			return;
		}
		
		
	  
	  if(document.getElementsByName('myExcelFile')[0].value != ""){	
			if(!checkFileType(document.getElementsByName('myExcelFile')[0], "excel数据文件格式为:'xls'或'xlsx'",['xls','xlsx'])) {
				 return;
		  }
    }
   	

    $("#import1").hide();
    $("#import2").show();
		$("#importForm").submit();
 }
 
</script>

</html>