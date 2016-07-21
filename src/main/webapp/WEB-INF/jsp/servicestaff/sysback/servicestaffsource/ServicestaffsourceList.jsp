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
				<h1><aebiz:showTitle titleId="Servicestaffsource.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="Servicestaffsource.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="Servicestaffsource.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="Servicestaffsource.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
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
	<label class="control-label"><aebiz:showTitle titleId="Servicestaffsource.qmf.serviceStaffName"/>：</label>
	<input type="text" name="serviceStaffName" id="serviceStaffName" class="form-control">
	<input type="hidden" name="serviceStaffName_q" id="serviceStaffName_q" class="form-control" value="EQ">
	</div>
	<div  class="form-group">
	<label class="control-label"><aebiz:showTitle titleId="Servicestaffsource.qmf.mobile"/>：</label>
	<input type="text" name="mobile" id="mobile" class="form-control">
	<input type="hidden" name="mobile_q" id="mobile_q" class="form-control" value="EQ">
	</div>
	
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
								</div>
								
								<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/Servicestaffsource/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="Servicestaffsource.m.clientType"/></th>
	<th><aebiz:showTitle titleId="Servicestaffsource.m.siteType"/></th>
	<th><aebiz:showTitle titleId="Servicestaffsource.m.thirdPlatType"/></th>
	<th><aebiz:showTitle titleId="Servicestaffsource.m.formUrl"/></th>
	<th><aebiz:showTitle titleId="Servicestaffsource.m.introducer"/></th>
	<th><aebiz:showTitle titleId="Servicestaffsource.m.serviceStaffUuid"/></th>
	
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
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="Servicestaffsource.m.clientType"/></label>
	<input type="text" name="clientType_s" id="clientType_s" class="form-control">
	<input type="hidden" name="clientType_q" id="clientType_q" value="Like">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="Servicestaffsource.m.siteType"/></label>
	<input type="text" name="siteType_s" id="siteType_s" class="form-control">
	<input type="hidden" name="siteType_q" id="siteType_q" value="Like">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="Servicestaffsource.m.thirdPlatType"/></label>
	<input type="text" name="thirdPlatType_s" id="thirdPlatType_s" class="form-control">
	<input type="hidden" name="thirdPlatType_q" id="thirdPlatType_q" value="Like">
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="Servicestaffsource.m.formUrl"/></label>
	<input type="text" name="formUrl_s" id="formUrl_s" class="form-control">
	<input type="hidden" name="formUrl_q" id="formUrl_q" value="Like">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="Servicestaffsource.m.introducer"/></label>
	<input type="text" name="introducer_s" id="introducer_s" class="form-control">
	<input type="hidden" name="introducer_q" id="introducer_q" value="Like">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="Servicestaffsource.m.serviceStaffUuid"/></label>
	<input type="text" name="serviceStaffUuid_s" id="serviceStaffUuid_s" class="form-control">
	<input type="hidden" name="serviceStaffUuid_q" id="serviceStaffUuid_q" value="EQ">
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="Servicestaffsource.qm.id2"/></label>
	<input type="text" name="id2_s" id="id2_s" class="form-control">
	<input type="hidden" name="id2_q" id="id2_q" value="LT">
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
	searchParam.push({ "name": "clientType_s", "value": $.trim($("#clientType_s").val())});
	searchParam.push({ "name": "clientType_q", "value": $.trim($("#clientType_q").val())});
	searchParam.push({ "name": "siteType_s", "value": $.trim($("#siteType_s").val())});
	searchParam.push({ "name": "siteType_q", "value": $.trim($("#siteType_q").val())});
	searchParam.push({ "name": "thirdPlatType_s", "value": $.trim($("#thirdPlatType_s").val())});
	searchParam.push({ "name": "thirdPlatType_q", "value": $.trim($("#thirdPlatType_q").val())});
	searchParam.push({ "name": "formUrl_s", "value": $.trim($("#formUrl_s").val())});
	searchParam.push({ "name": "formUrl_q", "value": $.trim($("#formUrl_q").val())});
	searchParam.push({ "name": "introducer_s", "value": $.trim($("#introducer_s").val())});
	searchParam.push({ "name": "introducer_q", "value": $.trim($("#introducer_q").val())});
	searchParam.push({ "name": "serviceStaffUuid_s", "value": $.trim($("#serviceStaffUuid_s").val())});
	searchParam.push({ "name": "serviceStaffUuid_q", "value": $.trim($("#serviceStaffUuid_q").val())});
	searchParam.push({ "name": "id2_s", "value": $.trim($("#id2_s").val())});
	searchParam.push({ "name": "id2_q", "value": $.trim($("#id2_q").val())});
	searchParam.push({ "name": "serviceStaffName", "value": $.trim($("#serviceStaffName").val())});
	searchParam.push({ "name": "mobile", "value": $.trim($("#mobile").val())});
			
	
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
											{"mDataProp":"clientType" ,"sTitle": "<aebiz:showTitle titleId="Servicestaffsource.m.clientType"/>"},
	{"mDataProp":"siteType" ,"sTitle": "<aebiz:showTitle titleId="Servicestaffsource.m.siteType"/>"},
	{"mDataProp":"thirdPlatType" ,"sTitle": "<aebiz:showTitle titleId="Servicestaffsource.m.thirdPlatType"/>"},
	{"mDataProp":"formUrl" ,"sTitle": "<aebiz:showTitle titleId="Servicestaffsource.m.formUrl"/>"},
	{"mDataProp":"introducer" ,"sTitle": "<aebiz:showTitle titleId="Servicestaffsource.m.introducer"/>"},
	{"mDataProp":"serviceStaffUuid" ,"sTitle": "<aebiz:showTitle titleId="Servicestaffsource.m.serviceStaffUuid"/>"},
		

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/Servicestaffsource/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/Servicestaffsource/queryList" ,
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
	$("#serviceStaffName").val("") ;
	$("#mobile").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#clientType_s").val("") ;
	$("#siteType_s").val("") ;
	$("#thirdPlatType_s").val("") ;
	$("#formUrl_s").val("") ;
	$("#introducer_s").val("") ;
	$("#serviceStaffUuid_s").val("") ;
	$("#id2_s").val("") ;
	
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
			    	"${pageContext.servletContext.contextPath}/sysback/Servicestaffsource/deletes",
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