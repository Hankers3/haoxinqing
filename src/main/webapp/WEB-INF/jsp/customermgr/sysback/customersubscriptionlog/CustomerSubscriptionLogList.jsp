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
				<h1><aebiz:showTitle titleId="customersubscriptionlog.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customersubscriptionlog.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="customersubscriptionlog.menuTwo"/></span><i class="fa fa-angle-right"></i></li>							
				<li><span><aebiz:showTitle titleId="customersubscriptionlog.moduleName_CN"/></span></li>						
			</ul>				
		</div>				
							
		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<input type="hidden" name="customerUuid" id="customerUuid" value="${wm.customerUuid}" class="form-control">
						<input type="hidden" name="customerUuid_q" id="customerUuid_q" class="form-control" value="EQ">
					
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th><aebiz:showTitle titleId="customersubscriptionlog.m.customerName"/></th>
									<th><aebiz:showTitle titleId="customersubscriptionlog.m.subscriptionUuid"/></th>
									<th><aebiz:showTitle titleId="customersubscriptionlog.m.subscriptionTitle"/></th>
									<th><aebiz:showTitle titleId="customersubscriptionlog.m.sendTime"/></th>
									<th><aebiz:showTitle titleId="customersubscriptionlog.m.content"/></th>
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
	
<script>
	function retrieveData(sSource,aoData,fnCallback) { 
		var searchParam = new Array() ;
		searchParam.push({ "name": "customerUuid", "value": $.trim($("#customerUuid").val())});
		searchParam.push({ "name": "customerUuid_q", "value": $.trim($("#customerUuid_q").val())});
		
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
					            
					            'sDom': "rtlip",
					            'aoColumnDefs': [{
					                'bSortable': false,
					                'aTargets': [0, 5]                
					            }],
					            "bSort": true, //排序功能            
						         	"aoColumns": [
												{"mDataProp":"customerName" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customersubscriptionlog.m.customerName"/>"},
												{"mDataProp":"subscriptionUuid" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customersubscriptionlog.m.subscriptionUuid"/>"},
												{"mDataProp":"subscriptionTitle" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customersubscriptionlog.m.subscriptionTitle"/>"},
												{"mDataProp":"sendTime" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customersubscriptionlog.m.sendTime"/>"},
												{"mDataProp":"content" ,"sWidth":"30%","sTitle": "<aebiz:showTitle titleId="customersubscriptionlog.m.content"/>"},
											  {
											  	"mDataProp":"operate",
											  	"sWidth":"10%",
											  	"sDefaultContent":"",
											  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
											 	"fnRender": function(obj) {	
												  		var sReturn = "<a href='#' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'>预览</a>" ;
												      return sReturn;
												 }			
											  }
											],
											
					            'oTableTools': {
					                "sSwfPath": "${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
					            },
					            "bRetrieve":true ,
					            "bProcessing":true,
					           	"bServerSide":true,                    //指定从服务器端获取数据
					            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/customersubscriptionlog/queryList" ,
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
	
	$(document).ready(function() {
		 //初始化表格
	  	 doSearch('inittable') ;
	   	 //复选框全选
		 $("#check_all").click(function(e) {
		 	$('input', oTable.fnGetNodes()).prop('checked', this.checked);
		 });       
	}) 
</script>
</body>

</html>