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
				<h1><aebiz:showTitle titleId="bankrelation.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="bankrelation.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="bankrelation.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="bankrelation.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
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
	<label class="control-label"><aebiz:showTitle titleId="bankrelation.qmf.bankCode"/>：</label>
	<input type="text" name="bankCode" id="bankCode" class="form-control">
	<input type="hidden" name="bankCode_q" id="bankCode_q" class="form-control" value="EQ">
	</div>
	<div  class="form-group">
	<label class="control-label"><aebiz:showTitle titleId="bankrelation.qmf.bankName"/>：</label>
	<input type="text" name="bankName" id="bankName" class="form-control">
	<input type="hidden" name="bankName_q" id="bankName_q" class="form-control" value="EQ">
	</div>
	<div  class="form-group">
	<label class="control-label"><aebiz:showTitle titleId="bankrelation.qmf.realName"/>：</label>
	<input type="text" name="realName" id="realName" class="form-control">
	<input type="hidden" name="realName_q" id="realName_q" class="form-control" value="EQ">
	</div>
	
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
									</div>
								</div>
								
<!-- 								<div class="y_tablebtn"> -->
<%-- 									<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/bankrelation/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a> --%>
<%-- 									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button> --%>
<!-- 								</div> -->
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
<!-- 									<th class='with-checkbox'> -->
<!-- 										<input type="checkbox" name="check_all" id="check_all"> -->
<!-- 									</th> -->
									<th><aebiz:showTitle titleId="bankrelation.m.doctorUuid"/></th>
	<th><aebiz:showTitle titleId="bankrelation.m.bankCode"/></th>
	<th><aebiz:showTitle titleId="bankrelation.m.bankName"/></th>
	<th><aebiz:showTitle titleId="bankrelation.m.realName"/></th>
<%-- 	<th><aebiz:showTitle titleId="bankrelation.m.createTime"/></th> --%>
	<th><aebiz:showTitle titleId="bankrelation.m.note"/></th>
	
<%-- 									<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th> --%>
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
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="bankrelation.m.doctorUuid"/></label>
	<input type="text" name="doctorUuid_s" id="doctorUuid_s" class="form-control">
	<input type="hidden" name="doctorUuid_q" id="doctorUuid_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="bankrelation.m.bankCode"/></label>
	<input type="text" name="bankCode_s" id="bankCode_s" class="form-control">
	<input type="hidden" name="bankCode_q" id="bankCode_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="bankrelation.m.bankName"/></label>
	<input type="text" name="bankName_s" id="bankName_s" class="form-control">
	<input type="hidden" name="bankName_q" id="bankName_q" value="EQ">
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="bankrelation.m.realName"/></label>
	<input type="text" name="realName_s" id="realName_s" class="form-control">
	<input type="hidden" name="realName_q" id="realName_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="bankrelation.m.createTime"/></label>
	<input type="text" name="createTime_s" id="createTime_s" class="form-control">
	<input type="hidden" name="createTime_q" id="createTime_q" value="Like">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="bankrelation.m.note"/></label>
	<input type="text" name="note_s" id="note_s" class="form-control">
	<input type="hidden" name="note_q" id="note_q" value="EQ">
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
	searchParam.push({ "name": "doctorUuid_s", "value": $.trim($("#doctorUuid_s").val())});
	searchParam.push({ "name": "doctorUuid_q", "value": $.trim($("#doctorUuid_q").val())});
	searchParam.push({ "name": "bankCode_s", "value": $.trim($("#bankCode_s").val())});
	searchParam.push({ "name": "bankCode_q", "value": $.trim($("#bankCode_q").val())});
	searchParam.push({ "name": "bankName_s", "value": $.trim($("#bankName_s").val())});
	searchParam.push({ "name": "bankName_q", "value": $.trim($("#bankName_q").val())});
	searchParam.push({ "name": "realName_s", "value": $.trim($("#realName_s").val())});
	searchParam.push({ "name": "realName_q", "value": $.trim($("#realName_q").val())});
	searchParam.push({ "name": "createTime_s", "value": $.trim($("#createTime_s").val())});
	searchParam.push({ "name": "createTime_q", "value": $.trim($("#createTime_q").val())});
	searchParam.push({ "name": "note_s", "value": $.trim($("#note_s").val())});
	searchParam.push({ "name": "note_q", "value": $.trim($("#note_q").val())});
	searchParam.push({ "name": "bankCode", "value": $.trim($("#bankCode").val())});
	searchParam.push({ "name": "bankName", "value": $.trim($("#bankName").val())});
	searchParam.push({ "name": "realName", "value": $.trim($("#realName").val())});
	searchParam.push({ "name": "createTime", "value": $.trim($("#createTime").val())});
			
	
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
// 										  {
// 										  	"mDataProp":"checkbox" ,
// 										  	"sDefaultContent":"",
// 										  	"fnRender": function(obj) {
// 										      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
// 										      return sReturn;
// 										 		}
// 										  },
											{"mDataProp":"doctorUuid" ,"sTitle": "<aebiz:showTitle titleId="bankrelation.m.doctorUuid"/>"},
	{"mDataProp":"bankCode" ,"sTitle": "<aebiz:showTitle titleId="bankrelation.m.bankCode"/>"},
	{"mDataProp":"bankName" ,"sTitle": "<aebiz:showTitle titleId="bankrelation.m.bankName"/>"},
	{"mDataProp":"realName" ,"sTitle": "<aebiz:showTitle titleId="bankrelation.m.realName"/>"},
// 	{"mDataProp":"createTime" ,"sTitle": "<aebiz:showTitle titleId="bankrelation.m.createTime"/>"},
	{"mDataProp":"note" ,"sTitle": "<aebiz:showTitle titleId="bankrelation.m.note"/>"},
		

// 										  {
// 										  	"mDataProp":"operate",
// 										  	"sDefaultContent":"",
// 										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
// 										 	"fnRender": function(obj) {	
// 											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/bankrelation/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
// 														sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
// 											      return sReturn;
// 											 }			
// 										  }
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/bankrelation/queryList" ,
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
	$("#bankCode").val("") ;
	$("#bankName").val("") ;
	$("#realName").val("") ;
	$("#createTime").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#doctorUuid_s").val("") ;
	$("#bankCode_s").val("") ;
	$("#bankName_s").val("") ;
	$("#realName_s").val("") ;
	$("#createTime_s").val("") ;
	$("#note_s").val("") ;
	$("#_s").val("") ;
	
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
			    	"${pageContext.servletContext.contextPath}/sysback/bankrelation/deletes",
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