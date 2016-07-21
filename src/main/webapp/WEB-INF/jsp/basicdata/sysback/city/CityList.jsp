<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="city.moduleName_CN"/></h1>
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
					<span><aebiz:showTitle titleId="city.moduleName_CN"/></span>
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
										<label class="control-label"><aebiz:showTitle titleId="city.m.cityName"/>：</label>
										<input type="text" name="cityName" id="cityName" class="form-control">
										<input type="hidden" name="cityName_q" id="cityName_q" class="form-control" value="Like">
									</div>
									
									
									
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="city.qmf.province"/>：</label>
										<!--<select name="provinceUuid" id="provinceUuid" class='select2-me form-control'>-->
										<select name="provinceUuid" id="provinceUuid" class='select2-me form-control'>		
											<option value="" >--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
											<c:forEach items="${pmList}" var="province">	
													<option value="${province.uuid}" >${province.provinceName}</option>
											</c:forEach>	
										</select>											
									</div>
									
								
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<a class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></a>										
									</div>
								</div>
						</div>
	
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="city.m.uuid"/></th>
									<th><aebiz:showTitle titleId="province.m.provinceName"/></th>									
									<th><aebiz:showTitle titleId="city.m.cityName"/></th>
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
	searchParam.push({ "name": "cityName", "value": $.trim($("#cityName").val())});
	searchParam.push({ "name": "cityName_q", "value": $.trim($("#cityName_q").val())});	
	searchParam.push({ "name": "provinceUuid", "value": $.trim($("#provinceUuid").val())});	
	
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
											{"mDataProp":"uuid" ,"sTitle": "<aebiz:showTitle titleId="city.m.uuid"/>"},
											{"mDataProp":"provinceName" ,"sTitle": "<aebiz:showTitle titleId="province.m.provinceName"/>"},											
											{"mDataProp":"cityName" ,"sTitle": "<aebiz:showTitle titleId="city.m.cityName"/>"},									
										],
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/city/queryList" ,
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

function clearSearch() {
	$("#cityName").val("") ;
	$("#provinceUuid").val("") ;
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
</script>