<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>


<!-- 日期插件 -->
<link href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css" type="text/css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- 调用日期插件 -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>

</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="ordershow.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="ordershow.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="ordershow.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="ordershow.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
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
										<label class="control-label"><aebiz:showTitle titleId="ordershow.qmf.showTitle"/>：</label>
										<input type="text" name="showTitle" id="showTitle" class="form-control">
										<input type="hidden" name="showTitle_q" id="showTitle_q" class="form-control" value="Like">
									</div>
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="ordershow.qmf.customerName"/>：</label>
										<input type="text" name="customerName" id="customerName" class="form-control">
										<input type="hidden" name="customerName_q" id="customerName_q" class="form-control" value="Like">
									</div>
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="ordershow.qmf.productName"/>：</label>
										<input type="text" name="productName" id="productName" class="form-control">
									</div>
	
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
								</div>

						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="ordershow.m.orderUuid"/></th>
									<th><aebiz:showTitle titleId="ordershow.qmf.productName"/></th>
									<th><aebiz:showTitle titleId="ordershow.m.customerName"/></th>
									<th><aebiz:showTitle titleId="ordershow.m.showTitle"/></th>
									<th><aebiz:showTitle titleId="ordershow.m.showTime"/></th>
									<th><aebiz:showTitle titleId="ordershow.m.state"/></th>
	
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
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="ordershow.m.customerName"/></label>
									<input type="text" name="customerName_s" id="customerName_s" class="form-control">
									<input type="hidden" name="customerName_q" id="customerName_q" value="Like">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="ordershow.m.state"/></label>
									<select	name="state_s" id="state_s" class="form-control">
										<option value=""><aebiz:showTitle titleId='basebusiness.showmessage.all'/></option>
										<option value="1"><aebiz:showTitle titleId="basebusiness.showmessage.uncheck"/></option>
										<option value="2"><aebiz:showTitle titleId="basebusiness.showmessage.checkPass"/></option>
										<option value="3"><aebiz:showTitle titleId="basebusiness.showmessage.checkUnPass"/></option>
									</select>
									<input type="hidden" name="state_q" id="state_q" value="EQ">
								</div>
							</div>
						</div>
						<div class="row">	
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="ordershow.m.showTitle"/></label>
									<input type="text" name="showTitle_s" id="showTitle_s" class="form-control">
									<input type="hidden" name="showTitle_q" id="showTitle_q" value="Like">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="ordershow.qmf.productName"/></label>
									<input type="text" name="productName_s" id="productName_s" class="form-control">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="ordershow.m.showTime"/></label>
									<input type="text" name="showTime_s" id="showTime_s" class="form-control datepick">
									<input type="hidden" name="showTime_q" id="showTime_q" value="GE">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="ordershow.qm.showTime2"/></label>
									<input type="text" name="showTime2_s" id="showTime2_s" class="form-control datepick">
									<input type="hidden" name="showTime2_q" id="showTime2_q" value="LT">
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
	searchParam.push({ "name": "customerName_s", "value": $.trim($("#customerName_s").val())});
	searchParam.push({ "name": "customerName_q", "value": $.trim($("#customerName_q").val())});
	searchParam.push({ "name": "showTime_s", "value": $.trim($("#showTime_s").val())});
	searchParam.push({ "name": "showTime_q", "value": $.trim($("#showTime_q").val())});
	searchParam.push({ "name": "showTitle_s", "value": $.trim($("#showTitle_s").val())});
	searchParam.push({ "name": "showTitle_q", "value": $.trim($("#showTitle_q").val())});
	searchParam.push({ "name": "state_s", "value": $.trim($("#state_s").val())});
	searchParam.push({ "name": "state_q", "value": $.trim($("#state_q").val())});
	searchParam.push({ "name": "productName_s", "value": $.trim($("#productName_s").val())});
	searchParam.push({ "name": "showTime2_s", "value": $.trim($("#showTime2_s").val())});
	searchParam.push({ "name": "showTime2_q", "value": $.trim($("#showTime2_q").val())});
	searchParam.push({ "name": "showTitle", "value": $.trim($("#showTitle").val())});
	searchParam.push({ "name": "customerName", "value": $.trim($("#customerName").val())});
	searchParam.push({ "name": "productName", "value": $.trim($("#productName").val())});
			
	
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
				                'aTargets': [0,7]                
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
											{"mDataProp":"orderUuid" ,"sTitle": "<aebiz:showTitle titleId="ordershow.m.orderUuid"/>"},
											{"mDataProp":"productName" ,"sTitle": "<aebiz:showTitle titleId="ordershow.qmf.productName"/>"},
											{"mDataProp":"customerName" ,"sTitle": "<aebiz:showTitle titleId="ordershow.m.customerName"/>"},
											{"mDataProp":"showTitle" ,"sTitle": "<aebiz:showTitle titleId="ordershow.m.showTitle"/>"},
											{"mDataProp":"showTime" ,"sTitle": "<aebiz:showTitle titleId="ordershow.m.showTime"/>"},
											{
										  	"mDataProp":"state",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="ordershow.m.state"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "";
											  		if(obj.aData.state==1){
											  				sReturn = "<aebiz:showTitle titleId="basebusiness.showmessage.uncheck"/>";
											  		}else if(obj.aData.state==2){
											  				sReturn = "<aebiz:showTitle titleId="basebusiness.showmessage.checkPass"/>";
											  		}else if(obj.aData.state==3){
											  				sReturn = "<aebiz:showTitle titleId="basebusiness.showmessage.checkUnPass"/>";
											  		}
											  		return sReturn;
											 }			
										  },
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/ordershow/toReview/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.review"/>'><aebiz:showTitle titleId="basebusiness.showmessage.review"/></a>" ;
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/ordershow/queryList" ,
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
	$("#showTitle").val("") ;
	$("#customerName").val("") ;
	$("#productName").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#customerName_s").val("") ;
	$("#showTime_s").val("") ;
	$("#showTitle_s").val("") ;
	$("#state_s").val("") ;
	$("#productName_s").val("") ;
	$("#showTime2_s").val("") ;
	
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
			    	"${pageContext.servletContext.contextPath}/sysback/ordershow/deletes",
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