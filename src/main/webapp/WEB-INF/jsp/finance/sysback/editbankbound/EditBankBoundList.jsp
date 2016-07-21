<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="editbankbound.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="editbankbound.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="editbankbound.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="editbankbound.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
				</li>						
			</ul>				
		</div>				
							
		<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding y_tableser">
							<div class="y_clear">
								<div class="form-inline table_formnew">
								
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="editbankbound.qmf.openAccountName"/>：</label>
										<input type="text" name="openAccountName" id="openAccountName" class="form-control">
										<input type="hidden" name="openAccountName_q" id="openAccountName_q" class="form-control" value="Like">
									</div>
	
										<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
								
								</div>
						</div>
						<div class="table-responsive2">

							<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
								<thead>		
									<tr>
										<th class='with-checkbox'>
											<input type="checkbox" name="check_all" id="check_all">
										</th>
										<th><aebiz:showTitle titleId="editbankbound.m.storeUuid"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.openAccountName"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.companyAccountNo"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.bankNo"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.openAccountBankName"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.bankArea"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.mobile"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.vilidateCode"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.vilidateMount"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.vilidateMount"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.mobileVilidateState"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.failTimes"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.applyTime"/></th>
										<th><aebiz:showTitle titleId="editbankbound.m.vilidateTime"/></th>
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
	</div>
	
	<!--高级搜索-->
		<div id="modal-user" class="modal fade y_highserch">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 id="user-infos"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.storeUuid"/></label>
									<input type="text" name="storeUuid_s" id="storeUuid_s" class="form-control">
									<input type="hidden" name="storeUuid_q" id="storeUuid_q" value="Like">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.openAccountName"/></label>
									<input type="text" name="openAccountName_s" id="openAccountName_s" class="form-control">
									<input type="hidden" name="openAccountName_q" id="openAccountName_q" value="EQ">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.companyAccountNo"/></label>
									<input type="text" name="companyAccountNo_s" id="companyAccountNo_s" class="form-control">
									<input type="hidden" name="companyAccountNo_q" id="companyAccountNo_q" value="EQ">
								</div>
							</div>
						
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.bankNo"/></label>
									<input type="text" name="bankNo_s" id="bankNo_s" class="form-control">
									<input type="hidden" name="bankNo_q" id="bankNo_q" value="EQ">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.openAccountBankName"/></label>
									<input type="text" name="openAccountBankName_s" id="openAccountBankName_s" class="form-control">
									<input type="hidden" name="openAccountBankName_q" id="openAccountBankName_q" value="EQ">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.bankArea"/></label>
									<input type="text" name="bankArea_s" id="bankArea_s" class="form-control">
									<input type="hidden" name="bankArea_q" id="bankArea_q" value="EQ">
								</div>
							</div>
						
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.cardValidateState"/></label>
									<select name="cardValidateState_s" id="cardValidateState_s" class='form-control'>
										<option value=""><aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/></option>
										<c:forEach items="${validateSateList}" var="validateSate" >
												<option value="${validateSate.value}">${validateSate.name}</option>
										</c:forEach>				
									</select>									
									<input type="hidden" name="cardValidateState_q" id="cardValidateState_q" value="EQ">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.mobile"/></label>
									<input type="text" name="mobile_s" id="mobile_s" class="form-control">
									<input type="hidden" name="mobile_q" id="mobile_q" value="EQ">
								</div>
							</div>
					
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.mobileVilidateState"/></label>
									<input type="text" name="mobileVilidateState_s" id="mobileVilidateState_s" class="form-control">
									<input type="hidden" name="mobileVilidateState_q" id="mobileVilidateState_q" value="EQ">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.failTimes"/></label>
									<input type="text" name="failTimes_s" id="failTimes_s" class="form-control">
									<input type="hidden" name="failTimes_q" id="failTimes_q" value="EQ">
								</div>
							</div>
					
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.applyTime"/></label>
									<input type="text" name="applyTime_s" id="applyTime_s" class="form-control">
									<input type="hidden" name="applyTime_q" id="applyTime_q" value="EQ">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.vilidateTime"/></label>
									<input type="text" name="vilidateTime_s" id="vilidateTime_s" class="form-control">
									<input type="hidden" name="vilidateTime_q" id="vilidateTime_q" value="EQ">
								</div>
							</div>
						</div>
					</div>													
					<div class="modal-footer">
						<button class="btn moresearch btn-primary" data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
						<button class="btn clearMoreSearch" ><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
					</div>
				</div>
			</div>
		</div>
		<!--高级搜索-->
		
		<!-- 验证金额  -->
		<div id="modal-bohui" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3 id="user-infos"><aebiz:showTitle titleId="editbankbound.m.vilidateMount"/></h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<div class="box">	
								<div class="box-content">									
										<div class="row">
											<div class="col-sm-12">
												<div class="form-group">
													<label for="textfield" class="control-label"><aebiz:showTitle titleId="editbankbound.m.vilidateMount"/></label>
													<input type="text" name="textarea" id="textarea" class="form-control" data-rule-required="true" data-rule-number="true" />
												</div>											
											</div>
										</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn moresearch" data-dismiss="modal" id="butText"><aebiz:showTitle titleId="basebusiness.showmessage.save"/></button>
					<button class="btn clearMoreSearch" id="clearText"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
					<button class="btn" data-dismiss="modal"><aebiz:showTitle titleId="withdrawapply.qm.close"/></button>
				</div>
			</div>
		</div>
	</div>
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "storeUuid_s", "value": $.trim($("#storeUuid_s").val())});
	searchParam.push({ "name": "storeUuid_q", "value": $.trim($("#storeUuid_q").val())});
	searchParam.push({ "name": "openAccountName_s", "value": $.trim($("#openAccountName_s").val())});
	searchParam.push({ "name": "openAccountName_q", "value": $.trim($("#openAccountName_q").val())});
	searchParam.push({ "name": "companyAccountNo_s", "value": $.trim($("#companyAccountNo_s").val())});
	searchParam.push({ "name": "companyAccountNo_q", "value": $.trim($("#companyAccountNo_q").val())});
	searchParam.push({ "name": "bankNo_s", "value": $.trim($("#bankNo_s").val())});
	searchParam.push({ "name": "bankNo_q", "value": $.trim($("#bankNo_q").val())});
	searchParam.push({ "name": "openAccountBankName_s", "value": $.trim($("#openAccountBankName_s").val())});
	searchParam.push({ "name": "openAccountBankName_q", "value": $.trim($("#openAccountBankName_q").val())});
	searchParam.push({ "name": "bankArea_s", "value": $.trim($("#bankArea_s").val())});
	searchParam.push({ "name": "bankArea_q", "value": $.trim($("#bankArea_q").val())});
	searchParam.push({ "name": "cardValidateState_s", "value": $.trim($("#cardValidateState_s").val())});
	searchParam.push({ "name": "cardValidateState_q", "value": $.trim($("#cardValidateState_q").val())});
	searchParam.push({ "name": "mobile_s", "value": $.trim($("#mobile_s").val())});
	searchParam.push({ "name": "mobile_q", "value": $.trim($("#mobile_q").val())});
	searchParam.push({ "name": "mobileVilidateState_s", "value": $.trim($("#mobileVilidateState_s").val())});
	searchParam.push({ "name": "mobileVilidateState_q", "value": $.trim($("#mobileVilidateState_q").val())});
	searchParam.push({ "name": "failTimes_s", "value": $.trim($("#failTimes_s").val())});
	searchParam.push({ "name": "failTimes_q", "value": $.trim($("#failTimes_q").val())});
	searchParam.push({ "name": "applyTime_s", "value": $.trim($("#applyTime_s").val())});
	searchParam.push({ "name": "applyTime_q", "value": $.trim($("#applyTime_q").val())});
	searchParam.push({ "name": "vilidateTime_s", "value": $.trim($("#vilidateTime_s").val())});
	searchParam.push({ "name": "vilidateTime_q", "value": $.trim($("#vilidateTime_q").val())});
	searchParam.push({ "name": "openAccountName", "value": $.trim($("#openAccountName").val())});
			
	
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
				                'aTargets': [0, 5,7,9,13]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
										  {
										  	"mDataProp":"checkbox" ,
										  	"sDefaultContent":"",
										  	"fnRender": function(obj) {
										      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
										      return sReturn;
										 		}
										  },
											{"mDataProp":"storeUuid" ,"sTitle": "<aebiz:showTitle titleId="editbankbound.m.storeUuid"/>"},
											{"mDataProp":"openAccountName" ,"sTitle": "<aebiz:showTitle titleId="editbankbound.m.openAccountName"/>"},
											{"mDataProp":"companyAccountNo" ,"sTitle": "<aebiz:showTitle titleId="editbankbound.m.companyAccountNo"/>"},
											{"mDataProp":"bankNo" ,"sTitle": "<aebiz:showTitle titleId="editbankbound.m.bankNo"/>"},
											{"mDataProp":"openAccountBankName" ,"sTitle": "<aebiz:showTitle titleId="editbankbound.m.openAccountBankName"/>"},
											{"mDataProp":"bankArea" ,"sTitle": "<aebiz:showTitle titleId="editbankbound.m.bankArea"/>"},
											{"mDataProp":"mobile" ,"sTitle": "<aebiz:showTitle titleId="editbankbound.m.mobile"/>"},
											{"mDataProp":"vilidateMount" ,"sTitle": "<aebiz:showTitle titleId="editbankbound.m.vilidateMount"/>"},
											{
										  	"mDataProp":"cardValidateState1",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="editbankbound.m.cardValidateState"/>" ,
										 	  "fnRender": function(obj) {
											 		var sReturn = ""
											 		if(obj.aData.cardValidateState == 0){//0未验证
											 			sReturn +="<aebiz:showTitle titleId="editbankbound.m.Untreated"/>";
											 		}else if(obj.aData.cardValidateState == 1){//1验证成功
											 			sReturn +="<aebiz:showTitle titleId="editbankbound.m.vilidateSuccess"/>"; 
											 		}
											 		else if(obj.aData.cardValidateState == 2){//2验证失败
											 			sReturn +="<aebiz:showTitle titleId="editbankbound.m.vilidateFail"/>";
											 		}	
											 		else if(obj.aData.cardValidateState == 3){//2验证失败
											 			sReturn +="<aebiz:showTitle titleId="editbankbound.m.vilidateWait"/>";
											 		}
												  return sReturn;	
												}		
										  },
											
											{"mDataProp":"mobileVilidateState" ,"sTitle": "<aebiz:showTitle titleId="editbankbound.m.mobileVilidateState"/>"},
											{"mDataProp":"failTimes" ,"sTitle": "<aebiz:showTitle titleId="editbankbound.m.failTimes"/>"},
											{"mDataProp":"applyTime" ,"sTitle": "<aebiz:showTitle titleId="editbankbound.m.applyTime"/>"},
											{"mDataProp":"vilidateTime" ,"sTitle": "<aebiz:showTitle titleId="editbankbound.m.vilidateTime"/>"},
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 		"fnRender": function(obj) {
										 	
											 		var sReturn = ""
											 		if(obj.aData.cardValidateState == 0){//0未验证
											 			sReturn +="<div id="+ obj.aData.uuid +" ><a href='javascript:cardValidateState(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="editbankbound.m.Untreated"/>'><aebiz:showTitle titleId="editbankbound.m.Untreated"/></a></div>";
											 		}
												  return sReturn;
											 }			
										  }
										],
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/editbankbound/queryList" ,
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
	//普通搜索需要清空高级搜索的选项
	clearMoreSearch();
	doSearch('search') ;  
})

$(".moresearch").click(function(){
	//高级搜索需要清空普通搜索的选项
	clearSearch() ;
	doSearch('search') ;
})

function clearSearch() {
	$("#openAccountName").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#storeUuid_s").val("") ;
	$("#openAccountName_s").val("") ;
	$("#companyAccountNo_s").val("") ;
	$("#bankNo_s").val("") ;
	$("#openAccountBankName_s").val("") ;
	$("#bankArea_s").val("") ;
	$("#cardValidateState_s").val("") ;
	$("#mobile_s").val("") ;
	$("#vilidateCode_s").val("") ;
	$("#mobileVilidateState_s").val("") ;
	$("#failTimes_s").val("") ;
	$("#applyTime_s").val("") ;
	$("#vilidateTime_s").val("") ;
	$("#_s").val("") ;
	
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

//验证金额
function cardValidateState(uuid){
	$("#modal-bohui").modal("show");
	$("#textarea").val("");
	$("#butText").click(function(){
		var value = $("#textarea").val();
		if(value.isNaN){
			alert("不是数字");
			return;
		}
		var reUrl = "${pageContext.servletContext.contextPath}/sysback/editbankbound/toUpdateVilidateMount"
		$.get(
			reUrl,
			{
				"uuid" : uuid,
				"vilidateMount":value,
				ranNum : Math.random()
			},
			function(data) {
				if(data == "success"){
					doSearch('search') ;
				}
		});
	});
	
	$("#clearText").click(function(){
			$("#textarea").val("");
	});
	
}

</script>
<aebiz:showErrorMsg></aebiz:showErrorMsg>