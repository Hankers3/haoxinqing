<%@page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

</head>

<body>
	<div class="container-fluid" >	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="orderreturnmoneymain.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="orderreturnmoneymain.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="orderreturnmoneymain.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="orderreturnmoneymain.moduleName_CN"/></span>
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
										<label class="control-label"><aebiz:showTitle titleId="orderreturnmoneymain.m.orderMainUuid"/>：</label>
										<input type="text" name="orderMainUuid" id="orderMainUuid" class="form-control">
										<input type="hidden" name="orderMainUuid_q" id="orderMainUuid_q" class="form-control" value="EQ">
									</div>
									
									<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="orderreturnmoneymain.m.state" />：</label>
									 <select name="state" id="state" class='form-control' >
										<option value=""><aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose" /></option>
										<c:forEach items="${orderreturnmoneymainState}" var="item">
											<option value="${item.key}">${item.value}</option>
										</c:forEach>
									 </select>
									 <input type="hidden" name="state_q" id="state_q" class="form-control" value="EQ">
									</div>
									
									<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="orderreturnmoneymain.m.returnType" />：</label>
									 <select name="returnType" id="returnType" class='form-control' >
										<option value=""><aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose" /></option>
										<c:forEach items="${orderreturnmoneymainReturnType}" var="item">
											<option value="${item.key}">${item.value}</option>
										</c:forEach>
									 </select>
									 <input type="hidden" name="returnType_q" id="returnType_q" class="form-control" value="EQ">
									</div>
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip">
											<aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip">							<aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
									</div>
								</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="orderreturnmoneymain.m.orderMainUuid"/></th>
									<th><aebiz:showTitle titleId="orderreturnmoneymain.m.customerName"/></th>
									<th><aebiz:showTitle titleId="orderreturnmoneymain.m.applyTime"/></th>
									<th><aebiz:showTitle titleId="orderreturnmoneymain.m.backMoney"/></th>
									<th><aebiz:showTitle titleId="orderreturnmoneymain.m.reason"/></th>
									<th><aebiz:showTitle titleId="orderreturnmoneymain.m.stateName"/></th>
									<th><aebiz:showTitle titleId="orderreturnmoneymain.m.returnTypeName"/></th>									
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
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;	
	searchParam.push({"name" : "orderMainUuid",	"value" : $.trim($("#orderMainUuid").val())	});
	searchParam.push({"name" : "orderMainUuid_q",	"value" : $.trim($("#orderMainUuid_q").val())	});
	searchParam.push({"name" : "state",	"value" : $.trim($("#state").val())	});
	searchParam.push({"name" : "state_q",	"value" : $.trim($("#state_q").val())	});
	searchParam.push({"name" : "returnType",	"value" : $.trim($("#returnType").val())	});
	searchParam.push({"name" : "returnType_q",	"value" : $.trim($("#returnType_q").val())	});
	
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
				                'aTargets': [0,1,2,5,6,7,8]                
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
											{"mDataProp":"orderMainUuid" ,"sTitle": "<aebiz:showTitle titleId="orderreturnmoneymain.m.orderMainUuid"/>"},
											{"mDataProp":"customerName" ,"sTitle": "<aebiz:showTitle titleId="orderreturnmoneymain.m.customerName"/>"},
											{"mDataProp":"applyTime" ,"sTitle": "<aebiz:showTitle titleId="orderreturnmoneymain.m.applyTime"/>"},
											{"mDataProp":"backMoney" ,"sWidth":"10%" ,"sTitle": "<aebiz:showTitle titleId="orderreturnmoneymain.m.backMoney"/>"},
											{"mDataProp":"reason" ,"sWidth":"20%" ,"sTitle": "<aebiz:showTitle titleId="orderreturnmoneymain.m.reason"/>"},
											{"mDataProp":"stateName" ,"sWidth":"15%" ,"sTitle": "<aebiz:showTitle titleId="orderreturnmoneymain.m.stateName"/>"},
											{"mDataProp":"returnTypeName" ,"sTitle": "<aebiz:showTitle titleId="orderreturnmoneymain.m.returnTypeName"/>"},											
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
										 				var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/orderreturnmoneymain/toInfo/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.view"/>'><aebiz:showTitle titleId="basebusiness.showmessage.view"/></a>" ;
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/orderreturnmoneymain/queryList" ,
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

$(".moresearch").click(function(){
	doSearch('search') ;
})

function clearSearch() {
	$("#orderMainUuid").val("") ;
	$("#returnType").val("") ;
	$("#state").val("") ;
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