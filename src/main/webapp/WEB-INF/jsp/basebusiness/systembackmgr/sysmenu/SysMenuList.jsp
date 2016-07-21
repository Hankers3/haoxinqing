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
				<h1><aebiz:showTitle titleId="sysmenu.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="sysmenu.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>					
				<li>
					<span><aebiz:showTitle titleId="sysmenu.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
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
										<label class="control-label"><aebiz:showTitle titleId="sysmenu.qmf.name"/>：</label>
										<input type="text" name="name" id="name" class="form-control">
										<input type="hidden" name="name_q" id="name_q" class="form-control" value="Like">
									</div>
									
									<div class="form-group">										
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId='basebusiness.showmessage.query'/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId='basebusiness.showmessage.clear'/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>										
									</div>
								</div>
								
								<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/sysmenu/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="sysmenu.m.id"/></th>
									<th><aebiz:showTitle titleId="sysmenu.m.name"/></th>
									<th><aebiz:showTitle titleId="sysmenu.m.toUrl"/></th>									
									<th><aebiz:showTitle titleId="sysmenu.m.showIndex"/></th>
									<th><aebiz:showTitle titleId="sysmenu.m.parentMenuUuid"/></th>
									<th><aebiz:showTitle titleId="sysmenu.m.state"/></th>	
									<th><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
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
	searchParam.push({ "name": "name", "value": $.trim($("#name").val())});
	searchParam.push({ "name": "name_q", "value": $.trim($("#name_q").val())});		
	
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
		                'aTargets': [0, 1]                
		            }],				            
		            "bSort": true, //排序功能            
			         	"aoColumns": [
								  {
								  	"mDataProp":"checkbox" ,
								  	"sWidth":"3%",
								  	"sDefaultContent":"",
								  	"fnRender": function(obj) {
								      var sReturn = "&nbsp;<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
								      return sReturn;
								 		}
								  },
									{"mDataProp":"id" ,"sWidth":"5%","sTitle": "<aebiz:showTitle titleId="sysmenu.m.id"/>"},
									{"mDataProp":"name" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="sysmenu.m.name"/>"},
									{"mDataProp":"toUrl" ,"sWidth":"20%","sTitle": "<aebiz:showTitle titleId="sysmenu.m.toUrl"/>"},											
									{"mDataProp":"showIndex" ,"sWidth":"5%","sTitle": "<aebiz:showTitle titleId="sysmenu.m.showIndex"/>"},
									{
										"mDataProp":"parentMenuName" ,
										'bSortable': false,
										"sWidth":"20%" ,
										"sTitle": "<aebiz:showTitle titleId="sysmenu.m.parentMenuUuid"/>"},
									{
										"mDataProp":"state" ,
										"sWidth":"5%",
										"sTitle": "<aebiz:showTitle titleId="sysmenu.m.state"/>" ,
										"fnRender": function(obj) {	
											if(obj.aData.state==1) {return "<aebiz:showTitle titleId='basebusiness.showmessage.yes'/>" ;}else{return "<aebiz:showTitle titleId='basebusiness.showmessage.no'/>" ;}									  	
									 	}			
									},								  
								  {
								  	"mDataProp":"operate",
								  	"sWidth":"12%" ,
								  	"sDefaultContent":"",
								  	'bSortable': false,
								  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
								 		"fnRender": function(obj) {	
									  	var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/sysmenu/toMultiUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
											sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
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
		            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/sysmenu/queryList" ,
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

function clearSearch() {
	$("#name").val("") ;
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
			    	"${pageContext.servletContext.contextPath}/sysback/sysmenu/deletes",
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
}) 
</script>