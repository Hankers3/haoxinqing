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
				<h1><aebiz:showTitle titleId="messageremind.title.menuTwo"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="messageremind.title.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="messageremind.title.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="messageremind.title.menuTwo"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
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
	<label class="control-label"><aebiz:showTitle titleId="messageremind.moduleName_CN"/>：</label>
	<input type="text" name="" id="" class="form-control">
	<input type="hidden" name="_q" id="_q" class="form-control" value="">
	</div>
	
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
								</div>
								
								<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/messageremind/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="messageremind.m.mobileName"/></th>
									<th><aebiz:showTitle titleId="messageremind.m.emailName"/></th>
									<th><aebiz:showTitle titleId="messageremind.m.innerMessageName"/></th>
									<th><aebiz:showTitle titleId="messageremind.m.remindName"/></th>
									<th><aebiz:showTitle titleId="messageremind.m.accountType"/></th>
									<th><aebiz:showTitle titleId="messageremind.m.status"/></th>
									<th><aebiz:showTitle titleId="messageremind.m.mobileCheck"/></th>
									<th><aebiz:showTitle titleId="messageremind.m.emailCheck"/></th>
									<th><aebiz:showTitle titleId="messageremind.m.innerMessageCheck"/></th>
									<th><aebiz:showTitle titleId="messageremind.m.type"/></th>
	
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
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.m.mobileName"/></label>
								<input type="text" name="mobileName_s" id="mobileName_s" class="form-control">
								<input type="hidden" name="mobileName_q" id="mobileName_q" value="Like">
							 </div>
							</div>
							<div class="col-sm-6">
	 							<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.m.emailName"/></label>
									<input type="text" name="emailName_s" id="emailName_s" class="form-control">
									<input type="hidden" name="emailName_q" id="emailName_q" value="Like">
								</div>
							</div>
							<div class="col-sm-6">
	 							<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.m.innerMessageName"/></label>
									<input type="text" name="innerMessageName_s" id="innerMessageName_s" class="form-control">
									<input type="hidden" name="innerMessageName_q" id="innerMessageName_q" value="Like">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
	 							<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.m.remindName"/></label>
									<input type="text" name="remindName_s" id="remindName_s" class="form-control">
									<input type="hidden" name="remindName_q" id="remindName_q" value="Like">
								</div>
							</div>
							<div class="col-sm-6">
	 							<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.m.accountType"/></label>
									<input type="text" name="accountType_s" id="accountType_s" class="form-control">
									<input type="hidden" name="accountType_q" id="accountType_q" value="Like">
								</div>
							</div>
							<div class="col-sm-6">
	 							<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.m.remindType"/></label>
									<input type="text" name="remindType_s" id="remindType_s" class="form-control">
									<input type="hidden" name="remindType_q" id="remindType_q" value="Like">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
	 							<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.m.status"/></label>
									<input type="text" name="status_s" id="status_s" class="form-control">
									<input type="hidden" name="status_q" id="status_q" value="Like">
								</div>
							</div>
							<div class="col-sm-6">
	 							<div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.m.mobileCheck"/></label>
	<input type="text" name="mobileCheck_s" id="mobileCheck_s" class="form-control">
	<input type="hidden" name="mobileCheck_q" id="mobileCheck_q" value="Like">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.m.emailCheck"/></label>
	<input type="text" name="emailCheck_s" id="emailCheck_s" class="form-control">
	<input type="hidden" name="emailCheck_q" id="emailCheck_q" value="Like">
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.m.innerMessageCheck"/></label>
	<input type="text" name="innerMessageCheck_s" id="innerMessageCheck_s" class="form-control">
	<input type="hidden" name="innerMessageCheck_q" id="innerMessageCheck_q" value="Like">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.m.type"/></label>
	<input type="text" name="type_s" id="type_s" class="form-control">
	<input type="hidden" name="type_q" id="type_q" value="Like">
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.qm.minTime"/></label>
	<input type="text" name="minTime_s" id="minTime_s" class="form-control">
	<input type="hidden" name="minTime_q" id="minTime_q" value="GT">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="messageremind.qm.maxTime"/></label>
	<input type="text" name="maxTime_s" id="maxTime_s" class="form-control">
	<input type="hidden" name="maxTime_q" id="maxTime_q" value="LT">
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
	searchParam.push({ "name": "mobileName_s", "value": $.trim($("#mobileName_s").val())});
	searchParam.push({ "name": "mobileName_q", "value": $.trim($("#mobileName_q").val())});
	searchParam.push({ "name": "emailName_s", "value": $.trim($("#emailName_s").val())});
	searchParam.push({ "name": "emailName_q", "value": $.trim($("#emailName_q").val())});
	searchParam.push({ "name": "innerMessageName_s", "value": $.trim($("#innerMessageName_s").val())});
	searchParam.push({ "name": "innerMessageName_q", "value": $.trim($("#innerMessageName_q").val())});
	searchParam.push({ "name": "remindName_s", "value": $.trim($("#remindName_s").val())});
	searchParam.push({ "name": "remindName_q", "value": $.trim($("#remindName_q").val())});
	searchParam.push({ "name": "accountType_s", "value": $.trim($("#accountType_s").val())});
	searchParam.push({ "name": "accountType_q", "value": $.trim($("#accountType_q").val())});
	searchParam.push({ "name": "status_s", "value": $.trim($("#status_s").val())});
	searchParam.push({ "name": "status_q", "value": $.trim($("#status_q").val())});
	searchParam.push({ "name": "mobileCheck_s", "value": $.trim($("#mobileCheck_s").val())});
	searchParam.push({ "name": "mobileCheck_q", "value": $.trim($("#mobileCheck_q").val())});
	searchParam.push({ "name": "emailCheck_s", "value": $.trim($("#emailCheck_s").val())});
	searchParam.push({ "name": "emailCheck_q", "value": $.trim($("#emailCheck_q").val())});
	searchParam.push({ "name": "innerMessageCheck_s", "value": $.trim($("#innerMessageCheck_s").val())});
	searchParam.push({ "name": "innerMessageCheck_q", "value": $.trim($("#innerMessageCheck_q").val())});
	searchParam.push({ "name": "type_s", "value": $.trim($("#type_s").val())});
	searchParam.push({ "name": "type_q", "value": $.trim($("#type_q").val())});
	searchParam.push({ "name": "minTime_s", "value": $.trim($("#minTime_s").val())});
	searchParam.push({ "name": "minTime_q", "value": $.trim($("#minTime_q").val())});
	searchParam.push({ "name": "maxTime_s", "value": $.trim($("#maxTime_s").val())});
	searchParam.push({ "name": "maxTime_q", "value": $.trim($("#maxTime_q").val())});
	searchParam.push({ "name": "", "value": $.trim($("#").val())});
			
	
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
				                'aTargets': [0, 1,2,3,4,5,6,7,8,9,10]                
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
											{"mDataProp":"mobileName" ,"sTitle": "<aebiz:showTitle titleId="messageremind.m.mobileName"/>"},
											{"mDataProp":"emailName" ,"sTitle": "<aebiz:showTitle titleId="messageremind.m.emailName"/>"},
											{"mDataProp":"innerMessageName" ,"sTitle": "<aebiz:showTitle titleId="messageremind.m.innerMessageName"/>"},
											{"mDataProp":"remindName" ,"sTitle": "<aebiz:showTitle titleId="messageremind.m.remindName"/>"},
											{"mDataProp":"accountTypeName" ,"sTitle": "<aebiz:showTitle titleId="messageremind.m.accountType"/>"},
											{"mDataProp":"stateName" ,"sTitle": "<aebiz:showTitle titleId="messageremind.m.status"/>"},
											{"mDataProp":"mobileCheckName" ,"sTitle": "<aebiz:showTitle titleId="messageremind.m.mobileCheck"/>"},
											{"mDataProp":"emailCheckName" ,"sTitle": "<aebiz:showTitle titleId="messageremind.m.emailCheck"/>"},
											{"mDataProp":"innerMessageCheckName" ,"sTitle": "<aebiz:showTitle titleId="messageremind.m.innerMessageCheck"/>"},
											{"mDataProp":"typeName" ,"sTitle": "<aebiz:showTitle titleId="messageremind.m.type"/>"},
		

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/messageremind/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/messageremind/queryList" ,
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
	$("#").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#mobileName_s").val("") ;
	$("#emailName_s").val("") ;
	$("#innerMessageName_s").val("") ;
	$("#remindName_s").val("") ;
	$("#accountType_s").val("") ;
	$("#remindType_s").val("") ;
	$("#status_s").val("") ;
	$("#mobileCheck_s").val("") ;
	$("#emailCheck_s").val("") ;
	$("#innerMessageCheck_s").val("") ;
	$("#type_s").val("") ;
	$("#minTime_s").val("") ;
	$("#maxTime_s").val("") ;
	
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
			    	"${pageContext.servletContext.contextPath}/sysback/messageremind/deletes",
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