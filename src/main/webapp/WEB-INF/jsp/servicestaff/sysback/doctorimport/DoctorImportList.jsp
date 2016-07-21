<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="doctorimport.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="doctorimport.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="doctorimport.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="doctorimport.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
				</li>						
			</ul>				
		</div>				
							
		<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding y_tableser">
							<div class="y_clear">
								<div class="form-inline table_formnew">
								
											<!--医生真实姓名 -->
											<div  class="form-group">
												<label class="control-label">医生姓名：</label>
												<input type="text" name="realName" id="realName" class="form-control">
												<input type="hidden" name="realName_q" id="realName_q" class="form-control" value="Like">
							     		</div>
											<div  class="form-group">
												<label class="control-label">手机号码：</label>
												<input type="text" name="mobile" id="mobile" class="form-control">
												<input type="hidden" name="mobile_q" id="mobile_q" class="form-control" value="Like">     <!--这个值为like就是模糊查询，EQ就是查询 -->
											</div>
											<div  class="form-group">
												<label class="control-label"><aebiz:showTitle titleId="servicestaff.m.email"/>：</label>
												<input type="text" name="email" id="email" class="form-control">
												<input type="hidden" name="email_q" id="email_q" class="form-control" value="Like">
											</div>
											<!--医院 -->
											<div  class="form-group">
												<label class="control-label">医院名称：</label>
												<select name="hospitalUuid" id="hospitalUuid" class="form-control">
													<option value="">请选择</option>
													<c:forEach items="${hospitalList}" var="dpp">	
														<option value="${dpp.uuid}">${dpp.hospitalName}</option>
													</c:forEach>
												</select>

												<input type="hidden" name="hospitalUuid_q" id="hospitalUuid_q" class="form-control" value="EQ">
											</div>
											

									
																	
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
									</div>
								</div>

						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>

									<th>流水号</th>
									<th>医生姓名</th>
									<th>手机号码</th>
									<th>邮箱</th>
									<th>职称</th>
									<th>职务</th>
									<th>医院名称</th>
									<th>科室名称</th>
									<th>个人简介</th>
									<!--
									<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
									-->
								</tr>
							</thead>
							
							<tbody>
							</tbody>
						</table>
					</div>		
				</div>
			</div>	
		</div>
	</div>
	
	
	
	
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;

	searchParam.push({ "name": "realName", "value": $.trim($("#realName").val())});
	searchParam.push({ "name": "realName_q", "value": $.trim($("#realName_q").val())});
	searchParam.push({ "name": "mobile", "value": $.trim($("#mobile").val())});
	searchParam.push({ "name": "mobile_q", "value": $.trim($("#mobile_q").val())});
	searchParam.push({ "name": "email", "value": $.trim($("#email").val())});
	searchParam.push({ "name": "email_q", "value": $.trim($("#email_q").val())});
	
	searchParam.push({ "name": "hospitalUuid", "value": $.trim($("#hospitalUuid").val())});
	searchParam.push({ "name": "hospitalUuid_q", "value": $.trim($("#hospitalUuid_q").val())});
	

   $.ajax({    	        
  	"dataType": 'json', 
	  "type": "POST", 
	  "url": sSource, 
	  "data": {aoData:JSON.stringify(aoData),searchParam:JSON.stringify(searchParam)}, 
	  "success": fnCallback
  });    
} 
    
var oTable = null; 
function doSearch(operact) {	
	if(oTable == null) {		 
    if ($('.dataTable').length > 0) {
        $('.dataTable').each(function() {            
                var opt = {
				            "sPaginationType": "full_numbers",
				            "oLanguage": {
				            		"sProcessing": "<aebiz:showTitle titleId="basebusiness.showmessage.loading"/>",
				                "sSearch": "<span><aebiz:showTitle titleId="basebusiness.showmessage.query"/>:</span> ",
				                "sInfo": "<aebiz:showTitle titleId="basebusiness.showmessage.pageFrom"/> <span>_START_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageTo"/> <span>_END_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>，<aebiz:showTitle titleId="basebusiness.showmessage.totalCount"/>： <span>_TOTAL_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>",
				                "sLengthMenu": "_MENU_ <span><aebiz:showTitle titleId="basebusiness.showmessage.pageShow"/></span>" ,
				                "oPaginate": {
				                 	"sFirst": "<aebiz:showTitle titleId="basebusiness.showmessage.firstPage"/>",
				                  "sPrevious": "<aebiz:showTitle titleId="basebusiness.showmessage.prePage"/>",
				                  "sNext": "<aebiz:showTitle titleId="basebusiness.showmessage.nextPage"/>",
				                  "sLast": "<aebiz:showTitle titleId="basebusiness.showmessage.lastPage"/>"
				                }
				            },
				            
				            //'sDom': "lfrtip",
				            'sDom': "rtlip",
				            //"sDom": '<"top"l>rt<"bottom"ip><"clear">',            
				            'aoColumnDefs': [{
				                'bSortable': false,
				                'aTargets': [1,2,3,4,5,6,7,8]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
										  
										  {"mDataProp":"doctorId" ,"sDefaultContent":"","sTitle": "流水号（ID）"},	
											{"mDataProp":"realName" ,	"sDefaultContent":"","sTitle": "医生姓名"},
											{"mDataProp":"mobile" ,"sDefaultContent":"","sTitle": "手机号码"},
											{"mDataProp":"email" ,"sDefaultContent":"","sTitle": "邮箱"},
											{
										  	"mDataProp":"professional1",
										  	"sDefaultContent":"",
										  	"sTitle": "职称" ,
										 		"fnRender": function(obj) {	
											  		var sReturn ="";
											  		if(obj.aData.professional==1){
											  			sReturn+="主任医师";
											  		}else if(obj.aData.professional==2){
											  			sReturn+="副主任医师";
											  		}else if(obj.aData.professional==3){
											  			sReturn+="主治医师";
											  		}else if(obj.aData.professional==4){
											  			sReturn+="住院医师";
											  		}else if(obj.aData.professional==5){
											  			sReturn+="助理医师";
											  		}else if(obj.aData.professional==6){
											  			sReturn+="实习医师";
											  		}else if(obj.aData.professional==7){
											  			sReturn+="护师（士）";
											  		}else if(obj.aData.professional==8){
											  			sReturn+="心理咨询师";
											  		}else if(obj.aData.professional==9){
											  			sReturn+="社工师";
											  		}else if(obj.aData.professional==10){
											  			sReturn+="药剂师";
											  		}else {
											  			sReturn+="";
											  		}
											      return sReturn;
											 }			
											},
											{"mDataProp":"doctorClassify" ,"sDefaultContent":"","sTitle": "职务"},
											{"mDataProp":"hospitalName" ,"sDefaultContent":"","sTitle": "医院名称"},
											{"mDataProp":"departmentName" ,"sDefaultContent":"","sTitle": "科室名称"},
											{"mDataProp":"synopsis" ,"sDefaultContent":"","sTitle": "个人简介"}
											/*
											,
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/doctorimport/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;															
											      return sReturn;
											 }			
										  }
										  */ 
										],
										
				            //'oColVis': {
				               // "buttonText": "Change columns <i class='icon-angle-down'></i>"
				            //},
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/doctorimport/queryList" ,
										"fnServerData":retrieveData,           //获取数据的处理函数  
				        };
				        
                		if ($(this).hasClass("dataTable-reorder")) {
		                    opt.sDom = "R" + opt.sDom;
		                }
		               
		                oTable = $(this).dataTable(opt);
                
        	});
   		}         
	}
		
	//刷新Datatable，会自动激发retrieveData  		
  	if('inittable' != operact) {  			
		oTable.fnDraw(); 	
	}  
	 	  
}

$(".search").click(function(){	
	doSearch('search') ;  
})



function clearSearch() {
	$("#realName").val("") ;
	$("#mobile").val("") ;
	$("#email").val("") ;
	$("#hospitalUuid").val("") ;
}



$(document).ready(function() {
	
	 //初始化表格
   doSearch('inittable') ;
   
   //复选框全选
	 $("#check_all").click(function(e) {
	 		$('input', oTable.fnGetNodes()).prop('checked', this.checked);
	 });       
		
	
	$(".clearMoreSearch").click(function(e) {    	
		clearMoreSearch() ;
	});
}) 
</script>