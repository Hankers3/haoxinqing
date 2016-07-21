<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/ListImport.jsp" %>

</head>

<body>
<!-- 头部 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp"%>
<div class="container-fluid" id="content">
<!-- 左侧 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp"%>
	<div id="main">	
		<div class="container-fluid">
			<div class="page-header">
				<div class="pull-left">
					<h1><aebiz:showTitle titleId="withdrawapply.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
				</div>
			</div>
			<div class="breadcrumbs">
				<ul>
					<li>
						<span><aebiz:showTitle titleId="withdrawapply.menuOne"/></span>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<span><aebiz:showTitle titleId="withdrawapply.menuTwo2"/></span>
						<i class="fa fa-angle-right"></i>							
					</li>							
					<li>
						<span><aebiz:showTitle titleId="withdrawapply.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
					</li>						
				</ul>				
			</div>				
			<div class="row">
				<div class="col-sm-12">
					<div class="box">
							<div class="box-content nopadding y_tableser">
								<div class="y_clear">
									<div class="y_tablebtn">
										<a class="btn" href="" title="<aebiz:showTitle titleId="withdrawapply.qm.eaport"/>" rel="tooltip"><i class="fa"><aebiz:showTitle titleId="withdrawapply.qm.eaport"/></i></a>
									</div>
									
									<div class="form-inline table_formnew">
										<div  class="form-group">
											
											<label class="control-label"><aebiz:showTitle titleId="withdrawapply.m.bankNo"/>：</label>
											<input type="text" name="bankNo" id="bankNo" class="form-control">
											<input type="hidden" name="bankNo_q" id="bankNo_q" class="form-control" value="Like">
											
											<label class="control-label"><aebiz:showTitle titleId="withdrawapply.qmf.moneyType"/>：</label>
											<select name="moneyType" id="moneyType" class='form-control'>
												<option value=""><aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/></option>
												<c:forEach items="${applyTypesList}" var="applyType" >
														<option value="${applyType.value}">${applyType.name}</option>
												</c:forEach>				
											</select>
											<input type="hidden" name="moneyType_q" id="moneyType_q" class="form-control" value="Like">
											
											<label class="control-label"><aebiz:showTitle titleId="withdrawapply.qm.state"/>：</label>
											<select name="state" id="state" class='form-control'>
												<option value=""><aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/></option>
												<c:forEach items="${stateList}" var="state" >
														<option value="${state.value}">${state.name}</option>
												</c:forEach>			
											</select>
											<input type="hidden" name="state_q" id="state_q" class="form-control" value="Like">
										</div>
										
										<div class="form-group">
											<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
											<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
											<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
										</div>
				</div>
			</div>
							
							<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder" id="tablePending">
								<thead>		
									<tr>
										<th class='with-checkbox'>
											<input type="checkbox" name="check_all" id="check_all">
										</th>
										<th><aebiz:showTitle titleId="withdrawapply.m.moneyType"/></th>
										<th><aebiz:showTitle titleId="withdrawapply.qm.applyMoney"/></th> 
										<th><aebiz:showTitle titleId="withdrawapply.qm.applyType"/></th>
										<th><aebiz:showTitle titleId="withdrawapply.qm.successTime"/></th>
										<th><aebiz:showTitle titleId="withdrawapply.m.bankName"/></th>
										<th><aebiz:showTitle titleId="withdrawapply.m.bankNo"/></th>
										<th><aebiz:showTitle titleId="withdrawapply.m.state"/></th>
										<th><aebiz:showTitle titleId="withdrawapply.m.note"/></th>
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
									<label class="control-label"><aebiz:showTitle titleId="withdrawapply.qmf.moneyType"/>：</label>
									<select name="moneyType_s" id="moneyType_s" class='form-control'>
										<option value=""><aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/></option>
										<c:forEach items="${applyTypesList}" var="applyType" >
												<option value="${applyType.value}">${applyType.name}</option>
										</c:forEach>				
									</select>
									<input type="hidden" name="moneyType_q" id="moneyType_q" class="form-control" value="Like">
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="withdrawapply.qm.state"/>：</label>
									<select name="state_s" id="state_s" class='form-control'>
										<option value=""><aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/></option>
											<c:forEach items="${stateList}" var="state" >
													<option value="${state.value}">${state.name}</option>
											</c:forEach>					
									</select>
									<input type="hidden" name="state_q" id="state_q" class="form-control" value="Like">
								</div>
							</div>
		
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="withdrawapply.qm.applyType"/></label>
									<input type="text" name="applyTime_s" id="applyTime_s" class="form-control">
									<input type="hidden" name="applyTime_q" id="applyTime_q" value="Like">
								</div>
							</div>
		
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="withdrawapply.qm.successTime"/></label>
									<input type="text" name="successTime_s" id="successTime_s" class="form-control">
									<input type="hidden" name="successTime_q" id="successTime_q" value="Like">
								</div>
							</div>
					
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="withdrawapply.m.bankName"/></label>
									<input type="text" name="bankName_s" id="bankName_s" class="form-control">
									<input type="hidden" name="bankName_q" id="bankName_q" value="Like">
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="withdrawapply.m.bankNo"/></label>
									<input type="text" name="bankNo_s" id="bankNo_s" class="form-control">
									<input type="hidden" name="bankNo_q" id="bankNo_q" value="Like">
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
	</div>
	
	<!-- 驳回原因-->	
	<div id="modal-bohui" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3 id="user-infos"><aebiz:showTitle titleId="withdrawapply.qm.mesg"/></h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<div class="box">	
								<div class="box-content">									
										<div class="row">
											<div class="col-sm-12">
												<div class="form-group">
													<label for="textfield" class="control-label"><aebiz:showTitle titleId="withdrawapply.qm.mesg"/></label>
													<textarea name="textarea" id="textarea" class="form-control" readonly="readonly"></textarea>
												</div>											
											</div>
										</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
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
	searchParam.push({ "name": "moneyType", "value": $.trim($("#moneyType").val())});
	searchParam.push({ "name": "moneyType_s", "value": $.trim($("#moneyType_s").val())});
	searchParam.push({ "name": "moneyType_q", "value": $.trim($("#moneyType_q").val())});
	
	searchParam.push({ "name": "applyTime_s", "value": $.trim($("#applyTime_s").val())});
	searchParam.push({ "name": "applyTime_q", "value": $.trim($("#applyTime_q").val())});
	
	searchParam.push({ "name": "successTime_s", "value": $.trim($("#successTime_s").val())});
	searchParam.push({ "name": "successTime_q", "value": $.trim($("#successTime_q").val())});
	
	searchParam.push({ "name": "bankName_s", "value": $.trim($("#bankName_s").val())});
	searchParam.push({ "name": "bankName_q", "value": $.trim($("#bankName_q").val())});
	
	searchParam.push({ "name": "bankNo", "value": $.trim($("#bankNo").val())});
	searchParam.push({ "name": "bankNo_s", "value": $.trim($("#bankNo_s").val())});
	searchParam.push({ "name": "bankNo_q", "value": $.trim($("#bankNo_q").val())});
	
	searchParam.push({ "name": "state", "value": $.trim($("#state").val())});
	searchParam.push({ "name": "state_s", "value": $.trim($("#state_s").val())});
	searchParam.push({ "name": "state_q", "value": $.trim($("#state_q").val())});
	
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
                'aTargets': [0,7,8]                
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
							{
						  	"mDataProp":"moneyType",
						  	"sDefaultContent":"",
						  	"sTitle": "<aebiz:showTitle titleId="withdrawapply.m.moneyType"/>" ,
						 	"fnRender": function(obj) {
							 		var sReturn = ""
							 		if(obj.aData.moneyType == 1){//商户余额
							 			sReturn +="<aebiz:showTitle titleId="withdrawapply.qm.merchantBalance"/>"; 
							 		}else if(obj.aData.moneyType == 2){//会员余额
							 			sReturn +="<aebiz:showTitle titleId="withdrawapply.qm.memberBalance"/>";
							 		}else{//商户保证金
							 			sReturn +="<aebiz:showTitle titleId="withdrawapply.qm.merchantMargin"/>";
							 		}	
								  return sReturn;
								 }			
						  },
						  {"mDataProp":"applyMoney" ,"sTitle": "<aebiz:showTitle titleId="withdrawapply.qm.applyMoney"/>"},
						  {"mDataProp":"applyTime" ,"sTitle": "<aebiz:showTitle titleId="withdrawapply.qm.applyType"/>"},
							{"mDataProp":"successTime" ,"sTitle": "<aebiz:showTitle titleId="withdrawapply.qm.successTime"/>"},
							
							{"mDataProp":"bankNo" ,"sTitle": "<aebiz:showTitle titleId="withdrawapply.m.bankNo"/>"},
							{"mDataProp":"bankName" ,"sTitle": "<aebiz:showTitle titleId="withdrawapply.m.bankName"/>"},
							
							{"mDataProp":"note" ,"sTitle": "<aebiz:showTitle titleId="withdrawapply.m.note"/>"},
						  {
						  	"mDataProp":"operate",
						  	"sDefaultContent":"",
						  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
						 	"fnRender": function(obj) {
							 		var sReturn =""
							 		if(obj.aData.state == 0){
							 			sReturn = "<aebiz:showTitle titleId="withdrawapply.qm.untreated"/>";
							 		}else if(obj.aData.state == 1){
							 				sReturn = "<aebiz:showTitle titleId="withdrawapply.qm.withdrawSuccess"/>";
							 		}else if(obj.aData.state == 2){
							 			sReturn ="<a href='javascript:aReject(\""+ obj.aData.failReason + "\");' rel='tooltip' title='<aebiz:showTitle titleId="withdrawapply.qm.withdrawFail"/>'><aebiz:showTitle titleId="withdrawapply.qm.withdrawFail"/></a>";
							 		}	
								  return sReturn;
								}			
						  }
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
            "sAjaxSource":"${pageContext.servletContext.contextPath}/storeback/storewithdrawapply/queryList" ,
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
	$("#bankNo").val("") ;
	$("#moneyType").val("") ;
	$("#state").val("") ;
}

//清空高级搜索的<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>选项
function clearMoreSearch() {
	$("#moneyType_s").val("") ;
	$("#successTime_s").val("") ;
	$("#bankName_s").val("") ;
	$("#bankNo_s").val("") ;
	$("#state_s").val("") ;
	$("#applyTime_s").val("") ;
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

//驳回
function aReject(failReason){
	$("#textarea").val(failReason);
	$("#modal-bohui").modal("show");
}
</script>