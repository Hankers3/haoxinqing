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
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.list"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="orderrevisit.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="orderrevisit.menuTwo-1"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="orderrevisit.moduleName_CN"/></span>
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
									<label class="control-label"><aebiz:showTitle titleId="orderrevisit.qmf.orderMainUuid"/>：</label>
									<input type="text" name="orderMainUuid" id="orderMainUuid" class="form-control">
									<input type="hidden" name="orderMainUuid_q" id="orderMainUuid_q" class="form-control" value="Like">
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
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="orderrevisit.m.orderMainUuid"/></th>
									<th><aebiz:showTitle titleId="orderrevisit.m.managerUuid"/></th>
									<th><aebiz:showTitle titleId="orderrevisit.m.customerName"/></th>
									<th><aebiz:showTitle titleId="orderrevisit.m.commentTime"/></th>
									<th><aebiz:showTitle titleId="orderrevisit.m.content"/></th>
									<th><aebiz:showTitle titleId="orderrevisit.m.revistScore"/></th>
	
									
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
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="orderrevisit.m.orderMainUuid"/></label>
						<input type="text" name="orderMainUuid_s" id="orderMainUuid_s" class="form-control">
						<input type="hidden" name="orderMainUuid_q" id="orderMainUuid_q" value="EQ">
						</div>
						</div>
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="orderrevisit.m.managerUuid"/></label>
						<input type="text" name="managerUuid_s" id="managerUuid_s" class="form-control">
						<input type="hidden" name="managerUuid_q" id="managerUuid_q" value="EQ">
						</div>
						</div>
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="orderrevisit.m.commentTime"/></label>
						<input type="text" name="commentTime_s" id="commentTime_s" class="form-control">
						<input type="hidden" name="commentTime_q" id="commentTime_q" value="EQ">
						</div>
						</div>
						</div>
						<div class="row">
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="orderrevisit.m.content"/></label>
						<input type="text" name="content_s" id="content_s" class="form-control">
						<input type="hidden" name="content_q" id="content_q" value="EQ">
						</div>
						</div>
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="orderrevisit.m.revistScore"/></label>
						<input type="text" name="revistScore_s" id="revistScore_s" class="form-control">
						<input type="hidden" name="revistScore_q" id="revistScore_q" value="EQ">
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
	searchParam.push({ "name": "orderMainUuid_s", "value": $.trim($("#orderMainUuid_s").val())});
	searchParam.push({ "name": "orderMainUuid_q", "value": $.trim($("#orderMainUuid_q").val())});
	searchParam.push({ "name": "managerUuid_s", "value": $.trim($("#managerUuid_s").val())});
	searchParam.push({ "name": "managerUuid_q", "value": $.trim($("#managerUuid_q").val())});
	searchParam.push({ "name": "commentTime_s", "value": $.trim($("#commentTime_s").val())});
	searchParam.push({ "name": "commentTime_q", "value": $.trim($("#commentTime_q").val())});
	searchParam.push({ "name": "content_s", "value": $.trim($("#content_s").val())});
	searchParam.push({ "name": "content_q", "value": $.trim($("#content_q").val())});
	searchParam.push({ "name": "revistScore_s", "value": $.trim($("#revistScore_s").val())});
	searchParam.push({ "name": "revistScore_q", "value": $.trim($("#revistScore_q").val())});
	searchParam.push({ "name": "orderMainUuid", "value": $.trim($("#orderMainUuid").val())});
			
	
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

											{"mDataProp":"orderId" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="orderrevisit.m.orderMainUuid"/>"},
											{"mDataProp":"managerName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="orderrevisit.m.managerUuid"/>"},
											{"mDataProp":"customerName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="orderrevisit.m.customerName"/>"},
											{"mDataProp":"commentTime" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="orderrevisit.m.commentTime"/>"},
											{"mDataProp":"content" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="orderrevisit.m.content"/>"},
											{"mDataProp":"revistScore" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="orderrevisit.m.revistScore"/>"},
		

										
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/orderrevisit/queryList" ,
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
	$("#orderMainUuid").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#orderMainUuid_s").val("") ;
	$("#managerUuid_s").val("") ;
	$("#commentTime_s").val("") ;
	$("#content_s").val("") ;
	$("#revistScore_s").val("") ;
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
			    	"${pageContext.servletContext.contextPath}/sysback/orderrevisit/deletes",
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