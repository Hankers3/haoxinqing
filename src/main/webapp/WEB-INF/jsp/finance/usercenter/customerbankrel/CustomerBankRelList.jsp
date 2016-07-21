<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="customerbankrel.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="customerbankrel.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="customerbankrel.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="customerbankrel.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
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
	<label class="control-label"><aebiz:showTitle titleId="customerbankrel.qmf.customerUuid"/>：</label>
	<input type="text" name="customerUuid" id="customerUuid" class="form-control">
	<input type="hidden" name="customerUuid_q" id="customerUuid_q" class="form-control" value="EQ">
	</div>
	<div  class="form-group">
	<label class="control-label"><aebiz:showTitle titleId="customerbankrel.qmf.cardNo"/>：</label>
	<input type="text" name="cardNo" id="cardNo" class="form-control">
	<input type="hidden" name="cardNo_q" id="cardNo_q" class="form-control" value="EQ">
	</div>
	
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
								</div>
								
								<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/customerbankrel/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="customerbankrel.m.customerUuid"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.cardNo"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.openAccountBank"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.openAccountName"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.cardValidateState"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.mobile"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.vilidateCode"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.vilidateMount"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.mobileVilidateState"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.failTimes"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.idCard1"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.idcard2"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.idcardNo"/></th>
	<th><aebiz:showTitle titleId="customerbankrel.m.createTime"/></th>
	
									<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
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
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.customerUuid"/></label>
	<input type="text" name="customerUuid_s" id="customerUuid_s" class="form-control">
	<input type="hidden" name="customerUuid_q" id="customerUuid_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.cardNo"/></label>
	<input type="text" name="cardNo_s" id="cardNo_s" class="form-control">
	<input type="hidden" name="cardNo_q" id="cardNo_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.openAccountBank"/></label>
	<input type="text" name="openAccountBank_s" id="openAccountBank_s" class="form-control">
	<input type="hidden" name="openAccountBank_q" id="openAccountBank_q" value="Like">
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.openAccountName"/></label>
	<input type="text" name="openAccountName_s" id="openAccountName_s" class="form-control">
	<input type="hidden" name="openAccountName_q" id="openAccountName_q" value="Like">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.cardValidateState"/></label>
	<input type="text" name="cardValidateState_s" id="cardValidateState_s" class="form-control">
	<input type="hidden" name="cardValidateState_q" id="cardValidateState_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.mobile"/></label>
	<input type="text" name="mobile_s" id="mobile_s" class="form-control">
	<input type="hidden" name="mobile_q" id="mobile_q" value="EQ">
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.vilidateCode"/></label>
	<input type="text" name="vilidateCode_s" id="vilidateCode_s" class="form-control">
	<input type="hidden" name="vilidateCode_q" id="vilidateCode_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.vilidateMount"/></label>
	<input type="text" name="vilidateMount_s" id="vilidateMount_s" class="form-control">
	<input type="hidden" name="vilidateMount_q" id="vilidateMount_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.mobileVilidateState"/></label>
	<input type="text" name="mobileVilidateState_s" id="mobileVilidateState_s" class="form-control">
	<input type="hidden" name="mobileVilidateState_q" id="mobileVilidateState_q" value="EQ">
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.failTimes"/></label>
	<input type="text" name="failTimes_s" id="failTimes_s" class="form-control">
	<input type="hidden" name="failTimes_q" id="failTimes_q" value="GE">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.idCard1"/></label>
	<input type="text" name="idCard1_s" id="idCard1_s" class="form-control">
	<input type="hidden" name="idCard1_q" id="idCard1_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.idcard2"/></label>
	<input type="text" name="idcard2_s" id="idcard2_s" class="form-control">
	<input type="hidden" name="idcard2_q" id="idcard2_q" value="EQ">
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.idcardNo"/></label>
	<input type="text" name="idcardNo_s" id="idcardNo_s" class="form-control">
	<input type="hidden" name="idcardNo_q" id="idcardNo_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.createTime"/></label>
	<input type="text" name="createTime_s" id="createTime_s" class="form-control">
	<input type="hidden" name="createTime_q" id="createTime_q" value="GE">
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.qm.failTimes2"/></label>
	<input type="text" name="failTimes2_s" id="failTimes2_s" class="form-control">
	<input type="hidden" name="failTimes2_q" id="failTimes2_q" value="LT">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.qm.createTime2"/></label>
	<input type="text" name="createTime2_s" id="createTime2_s" class="form-control">
	<input type="hidden" name="createTime2_q" id="createTime2_q" value="LT">
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
	
	
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "customerUuid_s", "value": $.trim($("#customerUuid_s").val())});
	searchParam.push({ "name": "customerUuid_q", "value": $.trim($("#customerUuid_q").val())});
	searchParam.push({ "name": "cardNo_s", "value": $.trim($("#cardNo_s").val())});
	searchParam.push({ "name": "cardNo_q", "value": $.trim($("#cardNo_q").val())});
	searchParam.push({ "name": "openAccountBank_s", "value": $.trim($("#openAccountBank_s").val())});
	searchParam.push({ "name": "openAccountBank_q", "value": $.trim($("#openAccountBank_q").val())});
	searchParam.push({ "name": "openAccountName_s", "value": $.trim($("#openAccountName_s").val())});
	searchParam.push({ "name": "openAccountName_q", "value": $.trim($("#openAccountName_q").val())});
	searchParam.push({ "name": "cardValidateState_s", "value": $.trim($("#cardValidateState_s").val())});
	searchParam.push({ "name": "cardValidateState_q", "value": $.trim($("#cardValidateState_q").val())});
	searchParam.push({ "name": "mobile_s", "value": $.trim($("#mobile_s").val())});
	searchParam.push({ "name": "mobile_q", "value": $.trim($("#mobile_q").val())});
	searchParam.push({ "name": "vilidateCode_s", "value": $.trim($("#vilidateCode_s").val())});
	searchParam.push({ "name": "vilidateCode_q", "value": $.trim($("#vilidateCode_q").val())});
	searchParam.push({ "name": "vilidateMount_s", "value": $.trim($("#vilidateMount_s").val())});
	searchParam.push({ "name": "vilidateMount_q", "value": $.trim($("#vilidateMount_q").val())});
	searchParam.push({ "name": "mobileVilidateState_s", "value": $.trim($("#mobileVilidateState_s").val())});
	searchParam.push({ "name": "mobileVilidateState_q", "value": $.trim($("#mobileVilidateState_q").val())});
	searchParam.push({ "name": "failTimes_s", "value": $.trim($("#failTimes_s").val())});
	searchParam.push({ "name": "failTimes_q", "value": $.trim($("#failTimes_q").val())});
	searchParam.push({ "name": "idCard1_s", "value": $.trim($("#idCard1_s").val())});
	searchParam.push({ "name": "idCard1_q", "value": $.trim($("#idCard1_q").val())});
	searchParam.push({ "name": "idcard2_s", "value": $.trim($("#idcard2_s").val())});
	searchParam.push({ "name": "idcard2_q", "value": $.trim($("#idcard2_q").val())});
	searchParam.push({ "name": "idcardNo_s", "value": $.trim($("#idcardNo_s").val())});
	searchParam.push({ "name": "idcardNo_q", "value": $.trim($("#idcardNo_q").val())});
	searchParam.push({ "name": "createTime_s", "value": $.trim($("#createTime_s").val())});
	searchParam.push({ "name": "createTime_q", "value": $.trim($("#createTime_q").val())});
	searchParam.push({ "name": "failTimes2_s", "value": $.trim($("#failTimes2_s").val())});
	searchParam.push({ "name": "failTimes2_q", "value": $.trim($("#failTimes2_q").val())});
	searchParam.push({ "name": "createTime2_s", "value": $.trim($("#createTime2_s").val())});
	searchParam.push({ "name": "createTime2_q", "value": $.trim($("#createTime2_q").val())});
	searchParam.push({ "name": "customerUuid", "value": $.trim($("#customerUuid").val())});
	searchParam.push({ "name": "cardNo", "value": $.trim($("#cardNo").val())});
			
	
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
				                'aTargets': [0, 1]                
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
											{"mDataProp":"customerUuid" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.customerUuid"/>"},
	{"mDataProp":"cardNo" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.cardNo"/>"},
	{"mDataProp":"openAccountBank" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.openAccountBank"/>"},
	{"mDataProp":"openAccountName" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.openAccountName"/>"},
	{"mDataProp":"cardValidateState" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.cardValidateState"/>"},
	{"mDataProp":"mobile" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.mobile"/>"},
	{"mDataProp":"vilidateCode" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.vilidateCode"/>"},
	{"mDataProp":"vilidateMount" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.vilidateMount"/>"},
	{"mDataProp":"mobileVilidateState" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.mobileVilidateState"/>"},
	{"mDataProp":"failTimes" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.failTimes"/>"},
	{"mDataProp":"idCard1" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.idCard1"/>"},
	{"mDataProp":"idcard2" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.idcard2"/>"},
	{"mDataProp":"idcardNo" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.idcardNo"/>"},
	{"mDataProp":"createTime" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.createTime"/>"},
		

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/customerbankrel/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
														sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/customerbankrel/queryList" ,
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
	$("#customerUuid").val("") ;
	$("#cardNo").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#customerUuid_s").val("") ;
	$("#cardNo_s").val("") ;
	$("#openAccountBank_s").val("") ;
	$("#openAccountName_s").val("") ;
	$("#cardValidateState_s").val("") ;
	$("#mobile_s").val("") ;
	$("#vilidateCode_s").val("") ;
	$("#vilidateMount_s").val("") ;
	$("#mobileVilidateState_s").val("") ;
	$("#failTimes_s").val("") ;
	$("#idCard1_s").val("") ;
	$("#idcard2_s").val("") ;
	$("#idcardNo_s").val("") ;
	$("#createTime_s").val("") ;
	$("#failTimes2_s").val("") ;
	$("#createTime2_s").val("") ;
	
}


//删除
function removes(delId) {
		var checkIds="";
		if( delId.trim() != "") {
			checkIds = delId ;
		}else{
			$("input[name='check']:checkbox").each(function(){				
        if($(this).is(":checked")){        	
            checkIds += $(this).val()+"," ;  
        }
    	})	
		}
		
		if(checkIds.trim() == "") {
				//提示为空
				bootbox.alert("<aebiz:showTitle titleId="basebusiness.showmessage.chooseDeleteData"/>") ;
				return ;
		} 
		
		bootbox.confirm("<aebiz:showTitle titleId="basebusiness.showmessage.confirmDelete"/>", function(r){
        	if(r) {
        		//删除
				$.post(
			    	"${pageContext.servletContext.contextPath}/customerbankrel/deletes",
			    	{"selectOne":checkIds,ranNum:Math.random()},	
				    function(data) {	       
				       var result = eval("("+data+")") ;	       
				       if(result.rsp) {
				  			//刷新
							//bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
							doSearch('search') ;     	
				       }else{
				       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }
				    }
				);		
        	}   
    });   
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