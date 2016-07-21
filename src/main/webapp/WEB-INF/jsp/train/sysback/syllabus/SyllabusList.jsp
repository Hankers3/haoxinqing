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
				<h1><aebiz:showTitle titleId="syllabus.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="syllabus.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="syllabus.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="syllabus.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
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
										<label class="control-label"><aebiz:showTitle titleId="syllabus.qmf.syllabusNo"/>：</label>
										<input type="text" name="syllabusNo" id="syllabusNo" class="form-control">
										<input type="hidden" name="syllabusNo_q" id="syllabusNo_q" class="form-control" value="Like">
									</div>
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
								</div>
								
								<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/syllabus/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="syllabus.m.syllabusNo"/></th>
									<th><aebiz:showTitle titleId="syllabus.m.syllabusName"/></th>
									<th><aebiz:showTitle titleId="syllabus.m.syllabusNote"/></th>
									<th><aebiz:showTitle titleId="syllabus.m.syllabusHandouts"/></th>
									<th><aebiz:showTitle titleId="syllabus.m.managerUuid"/></th>
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
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="syllabus.m.syllabusNo"/></label>
	<input type="text" name="syllabusNo_s" id="syllabusNo_s" class="form-control">
	<input type="hidden" name="syllabusNo_q" id="syllabusNo_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="syllabus.m.syllabusName"/></label>
	<input type="text" name="syllabusName_s" id="syllabusName_s" class="form-control">
	<input type="hidden" name="syllabusName_q" id="syllabusName_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="syllabus.m.syllabusNote"/></label>
	<input type="text" name="syllabusNote_s" id="syllabusNote_s" class="form-control">
	<input type="hidden" name="syllabusNote_q" id="syllabusNote_q" value="Like">
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="syllabus.m.syllabusHandouts"/></label>
	<input type="text" name="syllabusHandouts_s" id="syllabusHandouts_s" class="form-control">
	<input type="hidden" name="syllabusHandouts_q" id="syllabusHandouts_q" value="EQ">
	</div>
	</div>
	<div class="col-sm-6">
	 <div class="form-group">
	<label for="textfield" class="control-label"><aebiz:showTitle titleId="syllabus.m.managerUuid"/></label>
	<input type="text" name="managerUuid_s" id="managerUuid_s" class="form-control">
	<input type="hidden" name="managerUuid_q" id="managerUuid_q" value="EQ">
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
	searchParam.push({ "name": "syllabusNo_s", "value": $.trim($("#syllabusNo_s").val())});
	searchParam.push({ "name": "syllabusNo_q", "value": $.trim($("#syllabusNo_q").val())});
	searchParam.push({ "name": "syllabusName_s", "value": $.trim($("#syllabusName_s").val())});
	searchParam.push({ "name": "syllabusName_q", "value": $.trim($("#syllabusName_q").val())});
	searchParam.push({ "name": "syllabusNote_s", "value": $.trim($("#syllabusNote_s").val())});
	searchParam.push({ "name": "syllabusNote_q", "value": $.trim($("#syllabusNote_q").val())});
	searchParam.push({ "name": "syllabusHandouts_s", "value": $.trim($("#syllabusHandouts_s").val())});
	searchParam.push({ "name": "syllabusHandouts_q", "value": $.trim($("#syllabusHandouts_q").val())});
	searchParam.push({ "name": "managerUuid_s", "value": $.trim($("#managerUuid_s").val())});
	searchParam.push({ "name": "managerUuid_q", "value": $.trim($("#managerUuid_q").val())});
	searchParam.push({ "name": "syllabusNo", "value": $.trim($("#syllabusNo").val())});
			
	
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
											{"mDataProp":"syllabusNo" ,"sTitle": "<aebiz:showTitle titleId="syllabus.m.syllabusNo"/>"},
	{"mDataProp":"syllabusName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="syllabus.m.syllabusName"/>"},
	{"mDataProp":"syllabusNote" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="syllabus.m.syllabusNote"/>"},
	{"mDataProp":"syllabusHandouts" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="syllabus.m.syllabusHandouts"/>"},
	{"mDataProp":"managerName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="syllabus.m.managerUuid"/>"},
		

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/syllabus/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/syllabus/queryList" ,
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
	$("#syllabusNo").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#syllabusNo_s").val("") ;
	$("#syllabusName_s").val("") ;
	$("#syllabusNote_s").val("") ;
	$("#syllabusHandouts_s").val("") ;
	$("#managerUuid_s").val("") ;
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
			    	"${pageContext.servletContext.contextPath}/sysback/syllabus/deletes",
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