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
				<h1><aebiz:showTitle titleId="customerloginlog.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customerloginlog.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="customerloginlog.menuTwo"/></span><i class="fa fa-angle-right"></i></li>							
				<li><span><aebiz:showTitle titleId="customerloginlog.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span></li>						
			</ul>				
		</div>				
							
		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<div  class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="customerloginlog.qmf.custoemrUuid"/>：</label>
									<input type="text" name="custoemrUuid" id="custoemrUuid" class="form-control">
									<input type="hidden" name="custoemrUuid_q" id="custoemrUuid_q" class="form-control" value="Like">
								</div>
								
								<div class="form-group">
									<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
									<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
									<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
								</div>
							</div>
							
							<div class="y_tablebtn">
								<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/customerloginlog/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
								<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
							</div>
						</div>
					
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="customerloginlog.m.custoemrUuid"/></th>
									<th><aebiz:showTitle titleId="customerloginlog.m.loginTime"/></th>
									<th><aebiz:showTitle titleId="customerloginlog.m.IPName"/></th>
									<th><aebiz:showTitle titleId="customerloginlog.m.macName"/></th>
									<th><aebiz:showTitle titleId="customerloginlog.m.clientType"/></th>
									<th><aebiz:showTitle titleId="customerloginlog.m.pcName"/></th>
									<th><aebiz:showTitle titleId="customerloginlog.m.description"/></th>
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
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerloginlog.m.custoemrUuid"/></label>
						<input type="text" name="custoemrUuid_s" id="custoemrUuid_s" class="form-control">
						<input type="hidden" name="custoemrUuid_q" id="custoemrUuid_q" value="Like">
						</div>
						</div>
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerloginlog.m.loginTime"/></label>
						<input type="text" name="loginTime_s" id="loginTime_s" class="form-control">
						<input type="hidden" name="loginTime_q" id="loginTime_q" value="EQ">
						</div>
						</div>
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerloginlog.m.IPName"/></label>
						<input type="text" name="IPName_s" id="IPName_s" class="form-control">
						<input type="hidden" name="IPName_q" id="IPName_q" value="EQ">
						</div>
						</div>
						</div>
						<div class="row">
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerloginlog.m.macName"/></label>
						<input type="text" name="macName_s" id="macName_s" class="form-control">
						<input type="hidden" name="macName_q" id="macName_q" value="EQ">
						</div>
						</div>
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerloginlog.m.clientType"/></label>
						<input type="text" name="clientType_s" id="clientType_s" class="form-control">
						<input type="hidden" name="clientType_q" id="clientType_q" value="EQ">
						</div>
						</div>
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerloginlog.m.pcName"/></label>
						<input type="text" name="pcName_s" id="pcName_s" class="form-control">
						<input type="hidden" name="pcName_q" id="pcName_q" value="EQ">
						</div>
						</div>
						</div>
						<div class="row">
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerloginlog.m.description"/></label>
						<input type="text" name="description_s" id="description_s" class="form-control">
						<input type="hidden" name="description_q" id="description_q" value="EQ">
						</div>
						</div>
						</div>
						<div class="row">
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerloginlog.qm.createTime2"/></label>
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
	searchParam.push({ "name": "custoemrUuid_s", "value": $.trim($("#custoemrUuid_s").val())});
	searchParam.push({ "name": "custoemrUuid_q", "value": $.trim($("#custoemrUuid_q").val())});
	searchParam.push({ "name": "loginTime_s", "value": $.trim($("#loginTime_s").val())});
	searchParam.push({ "name": "loginTime_q", "value": $.trim($("#loginTime_q").val())});
	searchParam.push({ "name": "IPName_s", "value": $.trim($("#IPName_s").val())});
	searchParam.push({ "name": "IPName_q", "value": $.trim($("#IPName_q").val())});
	searchParam.push({ "name": "macName_s", "value": $.trim($("#macName_s").val())});
	searchParam.push({ "name": "macName_q", "value": $.trim($("#macName_q").val())});
	searchParam.push({ "name": "clientType_s", "value": $.trim($("#clientType_s").val())});
	searchParam.push({ "name": "clientType_q", "value": $.trim($("#clientType_q").val())});
	searchParam.push({ "name": "pcName_s", "value": $.trim($("#pcName_s").val())});
	searchParam.push({ "name": "pcName_q", "value": $.trim($("#pcName_q").val())});
	searchParam.push({ "name": "description_s", "value": $.trim($("#description_s").val())});
	searchParam.push({ "name": "description_q", "value": $.trim($("#description_q").val())});
	searchParam.push({ "name": "createTime2_s", "value": $.trim($("#createTime2_s").val())});
	searchParam.push({ "name": "createTime2_q", "value": $.trim($("#createTime2_q").val())});
	searchParam.push({ "name": "custoemrUuid", "value": $.trim($("#custoemrUuid").val())});
			
	
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
											{"mDataProp":"custoemrUuid" ,"sTitle": "<aebiz:showTitle titleId="customerloginlog.m.custoemrUuid"/>"},
	{"mDataProp":"loginTime" ,"sTitle": "<aebiz:showTitle titleId="customerloginlog.m.loginTime"/>"},
	{"mDataProp":"IPName" ,"sTitle": "<aebiz:showTitle titleId="customerloginlog.m.IPName"/>"},
	{"mDataProp":"macName" ,"sTitle": "<aebiz:showTitle titleId="customerloginlog.m.macName"/>"},
	{"mDataProp":"clientType" ,"sTitle": "<aebiz:showTitle titleId="customerloginlog.m.clientType"/>"},
	{"mDataProp":"pcName" ,"sTitle": "<aebiz:showTitle titleId="customerloginlog.m.pcName"/>"},
	{"mDataProp":"description" ,"sTitle": "<aebiz:showTitle titleId="customerloginlog.m.description"/>"},
		

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/customerloginlog/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
														sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
											      return sReturn;
											 }			
										  }
										],
										
				            //'oColVis': {
				               // "buttonText": "Change columns <i class='icon-angle-down'></i>"
				            //},
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/customerloginlog/queryList" ,
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
	$("#custoemrUuid").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#custoemrUuid_s").val("") ;
	$("#loginTime_s").val("") ;
	$("#IPName_s").val("") ;
	$("#macName_s").val("") ;
	$("#clientType_s").val("") ;
	$("#pcName_s").val("") ;
	$("#description_s").val("") ;
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
			    	"${pageContext.servletContext.contextPath}/sysback/customerloginlog/deletes",
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
	
	
	//高级搜索弹出框
	$("#searchshow").click(function(e) {    	
		e.preventDefault();
	  $("#modal-user").modal("show");
	});
	
	
	$(".clearMoreSearch").click(function(e) {    	
		clearMoreSearch() ;
	});
}) 
</script>