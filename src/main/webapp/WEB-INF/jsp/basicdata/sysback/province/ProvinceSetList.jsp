<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>

</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="city.business_moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="basicdata.title"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basicdata.area.title"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="city.business_moduleName_CN"/></span>
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
										<label class="control-label"><aebiz:showTitle titleId="province.m.provinceName"/>：</label>
										<input type="text" name="provinceName" id="provinceName" class="form-control">
										<input type="hidden" name="provinceName_q" id="provinceName_q" class="form-control" value="Like">
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
									<th><aebiz:showTitle titleId="province.m.uuid"/></th>
									<th><aebiz:showTitle titleId="province.m.provinceName"/></th>	
									<th><aebiz:showTitle titleId="pc.m.state"/></th>					
									<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate" /></th>						
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
	searchParam.push({ "name": "provinceName", "value": $.trim($("#provinceName").val())});
	searchParam.push({ "name": "provinceName_q", "value": $.trim($("#provinceName_q").val())});
	
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
						  	"sDefaultContent":"",
						  	"fnRender": function(obj) {
						      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
						      return sReturn;
						 		}
						  },
							{"mDataProp":"uuid" ,"sTitle": "<aebiz:showTitle titleId="province.m.uuid"/>"},
							{"mDataProp":"provinceName" ,"sTitle": "<aebiz:showTitle titleId="province.m.provinceName"/>"},
							{
								"mDataProp":"state1" ,
								"sDefaultContent" : "",
								"sTitle": "<aebiz:showTitle titleId="pc.m.state"/>",
								"fnRender" : function(obj) {
									var sReturn ="";
									if(obj.aData.state=="0"){
										sReturn+="未启用";
									}else{
										sReturn+="已启用";
									}
									return sReturn;
								}
							},
							
							{
								"mDataProp" : "operate",
								"sDefaultContent" : "",
								"sWidth":"25%",
								"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
								"fnRender" : function(obj) {
									var sReturn = "";
											if(obj.aData.state=="0"){
												sReturn +="<a onclick='javascript:setMinitab(\""+obj.aData.uuid+"\",\""+1+"\")' class='btn' rel='tooltip' data-toggle='modal' data-target='#modal-unfrozen' title='<aebiz:showTitle titleId="area.state.setminitab"/>'><aebiz:showTitle titleId="area.state.setminitab"/></a>";

											}else{
												sReturn += "<a onclick='javascript:setUnMinitab(\""+obj.aData.uuid+"\",\""+0+"\")' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="area.state.setunminitab"/>'><aebiz:showTitle titleId="area.state.setunminitab"/></a>";
											
												sReturn += "<a href='${pageContext.servletContext.contextPath}/sysback/city/cList?uuid="+ obj.aData.uuid
											+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="area.state.setcity.minitab"/>'><aebiz:showTitle titleId="area.state.setcity.minitab"/></a>";
								
											}
										
									return sReturn;
								},
							} ,
						],
										         
            'oTableTools': {
                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
            },
            "bRetrieve":true ,
            "bProcessing":true,
           	"bServerSide":true,                    //指定从服务器端获取数据
            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/province/queryList" ,
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
		oTable.fnDraw(false); 	
	}   
}

function clearSearch() {
	$("#provinceName").val("") ;
}

$(document).ready(function() {
	 //初始化表格
   doSearch('inittable') ;
   
   //复选框全选
	 $("#check_all").click(function(e) {
	 		$('input', oTable.fnGetNodes()).prop('checked', this.checked);
	 }); 
	 
	 $(".search").click(function(){		
		doSearch('search') ;  
	}) 
}) 



//城市启用
function setMinitab(provinceUuid,state){
	$.get("${pageContext.servletContext.contextPath}/sysback/province/setminitab",{"provinceUuid":provinceUuid,"state":state,ranNum:Math.random()},function(data) {	
	    	if(data == "true") {
		  			//刷新
					doSearch('search') ;     	
		     }else{
		       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
		      }
	    });		
}	

//城市启用取消
function setUnMinitab(provinceUuid,state){

	$.get("${pageContext.servletContext.contextPath}/sysback/province/setUnMinitab",{"provinceUuid":provinceUuid,"state":state,ranNum:Math.random()},function(data) {	
		  if(data == "true") {
	  			//刷新
				doSearch('search') ;     	
	      }else{
	       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
	      }
	});	
}
</script>