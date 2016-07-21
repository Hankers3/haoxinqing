<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	#level1,#level2,#level3{float:left;}
</style>

</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="content.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="content.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="content.menuTwo"/></span><i class="fa fa-angle-right"></i></li>							
				<li><span><aebiz:showTitle titleId="content.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span></li>						
			</ul>				
		</div>				
							
		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<div  class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="content.qmf.contentTitle"/>：</label>
									<input type="text" name="contentTitle" id="contentTitle" class="form-control">
									<input type="hidden" name="contentTitle_q" id="contentTitle_q" class="form-control" value="Like">
								</div>
								
								<div  class="form-group">
									<label class="control-label fl mt_5"><aebiz:showTitle titleId="content.m.categoryName"/>：</label>
									<aebiz:contentCategorySearch inputName="contentCategoryUuid" categoryUuid=""  />
								</div>
								<input type="hidden" name="contentCategoryUuid_q" id="contentCategoryUuid_q" value="EQ">
								<div  class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="content.m.state"/>：</label>
									<select name="state" id="state" class='select2-me form-control' >								
										<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
										<option value="0">未发布</option>
										<option value="1">已发布</option>
									</select>
									<input type="hidden" name="state_q" id="state_q" class="form-control" value="EQ">
								</div>
								
								
								<div class="form-group">
									<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
									<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>									
								</div>
							</div>
			
							<div class="y_tablebtn">
								<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/content/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
								<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
							</div>
						</div>
					
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="content.m.contentTitle"/></th>
									<th><aebiz:showTitle titleId="content.m.categoryName"/></th>
									<th><aebiz:showTitle titleId="content.m.contentType"/></th>
									<th><aebiz:showTitle titleId="content.m.createTime"/></th>		
									<th><aebiz:showTitle titleId="content.m.state"/></th>			
									<th>URL</th>	
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
	searchParam.push({ "name": "contentTitle", "value": $.trim($("#contentTitle").val())});
	searchParam.push({ "name": "contentTitle_q", "value": $.trim($("#contentTitle_q").val())});
	searchParam.push({ "name": "contentCategoryUuid", "value": $.trim($("#selectCategoryUuid").val())});
	searchParam.push({ "name": "contentCategoryUuid_q", "value": $.trim($("#contentCategoryUuid_q").val())});
	searchParam.push({ "name": "state", "value": $.trim($("#state").val())});
	searchParam.push({ "name": "state_q", "value": $.trim($("#state_q").val())});
	searchParam.push({ "name": "state_s", "value": $.trim($("#state_s").val())});
	searchParam.push({ "name": "contentType_s", "value": $.trim($("#state_s").val())});
	searchParam.push({ "name": "categoryTypeq", "value": "2"});

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
				                'aTargets': [0,1,2,3,5,6]                
				            }],
				            "aaSorting": [[ 4, "desc" ]],
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
											{"mDataProp":"contentTitle","sTitle": "<aebiz:showTitle titleId="content.m.contentTitle"/>"},
											{"mDataProp":"categoryName","sTitle": "<aebiz:showTitle titleId="content.m.categoryName"/>"},
										  {
										  	"mDataProp":"contentType",
										  	"sWidth":"12%",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="content.m.contentType"/>" ,
											 	"fnRender": function(obj) {	
						  								if(obj.aData.contentType=="1"){
						  									return "文本类型";
						  								}			
						  								if(obj.aData.contentType=="2"){
						  									return "连接类型 ";
						  								}if(obj.aData.contentType=="3"){
						  									return "医脉通 ";
						  								}
												  									
												      return "";
												 }			
											  },
										  {"mDataProp":"createTime","sTitle": "<aebiz:showTitle titleId="content.m.createTime"/>"},
										  {
										  	"mDataProp":"state",
										  	"sWidth":"12%",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="content.m.state"/>" ,
										 	"fnRender": function(obj) {	
					  								if(obj.aData.state=="1"){
					  									return "已发布";
					  								}			
					  								if(obj.aData.state=="0"){
					  									return "未发布 ";
					  								}
											  									
											      return "";
											 }			
										  },
										  {
											  	"mDataProp":"url",
											  	"sWidth":"12%",
											  	"sDefaultContent":"",
											  	"sTitle": "url" ,
											 	"fnRender": function(obj) {	
												  		var sReturn = "<input type='text'  value='"+obj.aData.url+"'　readonly='readonly' />" ;															
												      return sReturn;
												 }			
											  },
										  {
										  	"mDataProp":"operate",
										  	"sWidth":"12%",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/content/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
														sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
																								
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/content/queryList" ,
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
	$("#contentTitle").val("") ;
	$("#state").val("") ;
	$('#selectlevel0 option:eq("")').attr('selected','selected');
	selectChange("level1","");
}
function clearMoreSearch(){
	$("#contentTitle_s").val("") ;
	$("#contentType_s").val("") ;
	$("#state_s").val("") ;
	
	showSelect();
}
 

$(".clearMoreSearch").click(function(){	
	clearMoreSearch();
})

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
    		});	
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
			    	"${pageContext.servletContext.contextPath}/sysback/content/deletes",
			    	{"selectOne":checkIds,ranNum:Math.random()},	
				    function(data) {	       
				       var result = eval("("+data+")") ;	       
				       if(result.rsp) {
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
	
</body>
</html>