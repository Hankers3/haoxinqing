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
				<c:choose>
					<c:when test="${templateType=='mobile'}">
						<h1><aebiz:showTitle titleId="thirdInterfaceTemplate.m.smsMgr"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
					</c:when>
					<c:when test="${templateType=='email'}">
						<h1><aebiz:showTitle titleId="thirdInterfaceTemplate.m.emailMgr"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
					</c:when>
					<c:otherwise>
						<h1>消息模板<aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
					</c:otherwise>				
				</c:choose>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="thirdInterfaceTemplate.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="thirdInterfaceTemplate.menuTwo"/></span><i class="fa fa-angle-right"></i></li>							
				<li>
					<span>
						<c:choose>
							<c:when test="${templateType=='mobile'}">
								<aebiz:showTitle titleId="thirdInterfaceTemplate.m.smsMgr"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/>
							</c:when>
							<c:when test="${templateType=='email'}">
								<aebiz:showTitle titleId="thirdInterfaceTemplate.m.emailMgr"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/>
							</c:when>
							<c:otherwise>
								消息模板<aebiz:showTitle titleId="basebusiness.showmessage.manager"/>
							</c:otherwise>				
						</c:choose>
					</span>
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
									<label class="control-label"><aebiz:showTitle titleId="thirdInterfaceTemplate.qmf.templateId"/>：</label>
									<input type="text" name="templateId" id="templateId" class="form-control">
									<input type="hidden" name="templateId_q" id="templateId_q" class="form-control" value="Like">
								</div>
								
								<div class="form-group">
									<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
									<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
								</div>
							</div>
							
							<div class="y_tablebtn">
								<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/thirdInterfaceTemplate/toAdd?templateType=${templateType}" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
								<!--<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>-->
							</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateId"/></th>
									<!--<th><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateName"/></th>-->
									<th><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateTitle"/></th>
									<th><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateState"/></th>
									<th><aebiz:showTitle titleId="thirdInterfaceTemplate.m.position"/></th>
									<th><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateContent"/></th>
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
	searchParam.push({ "name": "templateId", "value": $.trim($("#templateId").val())});
	searchParam.push({ "name": "templateId_q", "value": $.trim($("#templateId_q").val())});
	
	searchParam.push({ "name": "templateType", "value": "${templateType}"});
	searchParam.push({ "name": "templateType_q", "value": "EQ"});
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
				                'aTargets': [0, 1,2,3,4,5]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
											{"mDataProp":"templateId" ,"sTitle": "<aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateId"/>"},
											//{"mDataProp":"templateName" ,"sTitle": "<aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateName"/>"},
											{"mDataProp":"templateTitle" ,"sTitle": "<aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateTitle"/>"},
											{
											  	"mDataProp":"templateState",
											  	"sDefaultContent":"",
											  	"sTitle": "<aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateState"/>" ,
											 	"fnRender": function(obj) {	
												  		var sReturn ="";
												  		if(obj.aData.templateState==1){
												  			sReturn="<aebiz:showTitle titleId="thirdInterfaceTemplate.m.used"/>";
												  		}else{
												  			sReturn="<aebiz:showTitle titleId="thirdInterfaceTemplate.m.notUsed"/>";
												  		}
												      return sReturn;
												 }			
											 },
											{"mDataProp":"position" ,"sTitle": "<aebiz:showTitle titleId="thirdInterfaceTemplate.m.position"/>"},
											{"mDataProp":"templateContent" ,"sTitle": "<aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateContent"/>"},
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/thirdInterfaceTemplate/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
														//sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
											      return sReturn;
											 }			
										  }
										],
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/thirdInterfaceTemplate/queryList" ,
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
	$("#templateId").val("") ;
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#templateId_s").val("") ;
	$("#templateName_s").val("") ;
	$("#templateState_s").val("") ;
	$("#templateTitle_s").val("") ;
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
			    	"${pageContext.servletContext.contextPath}/sysback/thirdInterfaceTemplate/deletes",
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
	
	$(".clearMoreSearch").click(function(e) {    	
		clearMoreSearch() ;
	});
}) 
</script>