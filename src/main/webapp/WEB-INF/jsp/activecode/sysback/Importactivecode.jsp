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
					批量导入邀请码
				</h1>
			</div>
		</div>
		<form action="${pageContext.servletContext.contextPath}/sysback/activecode/importData" method="POST" enctype="multipart/form-data" class='form-bordered form-validate' id="importForm">
		
			<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding leadbox">
							
							<div  class="upload_con y_clear">
								<h3>&diams;&nbsp;选择需要导入的邀请码Excel文件</h3>
								<div class="form-inline down_btn">	
									<div class="form-group" >
										<label class="control-label">选择Excel文件:</label>
										<div class="col-sm-6">	
											<div class="fileinput-new" data-provides="fileinput">
												<div class="input-group">
													<div class="form-control" data-trigger="fileinput">
														<i class="glyphicon glyphicon-file fileinput-exists"></i>
														<span class="fileinput-filename"></span>
													</div>
													<span class="input-group-addon btn btn-default btn-file">
													<span class="fileinput-new">Select file</span>
													<span class="fileinput-exists">Change</span>
													<input type="file" name="myExcelFile" data-rule-required="true" id="myExcelFile">
													</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<!--导出-->	
							<div class="y_fixbtnbox">
								<div class="y_fixedbtn">		
									<button type="button" onclick="javascript:submitImportDataForm()" class="btn btn-primary btn-large a_size_1" id="import1" ><aebiz:showTitle titleId="basebusiness.showmessage.import"/></button>	
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
	</form>
 </div>

    
</body>
<script type="text/javascript">
	$("#myExcelFile").change(function () {
		$(this).parent("span").siblings(".form-control").children(".fileinput-filename").text($(this).val());
	});
	
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
	$("#importForm").submit();
 }
 
</script>

</html>