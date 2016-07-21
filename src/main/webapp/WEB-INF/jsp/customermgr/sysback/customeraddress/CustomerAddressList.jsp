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
				<h1><aebiz:showTitle titleId="customeraddress.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customeraddress.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="customeraddress.menuTwo"/></span><i class="fa fa-angle-right"></i></li>							
				<li><span><aebiz:showTitle titleId="customeraddress.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span></li>						
			</ul>				
		</div>				
							
		<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding y_tableser">
							<div class="y_clear">
								<div class="form-inline table_formnew">
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="customeraddress.qmf.customerUuid"/>：</label>
										<input type="text" name="customerUuid" id="customerUuid" class="form-control">
										<input type="hidden" name="customerUuid_q" id="customerUuid_q" class="form-control" value="Like">
									</div>
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
								</div>
								
								<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/customeraddress/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
							</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="customeraddress.m.customerUuid"/></th>
									<th><aebiz:showTitle titleId="customeraddress.m.province"/></th>
									<th><aebiz:showTitle titleId="customeraddress.m.city"/></th>
									<th><aebiz:showTitle titleId="customeraddress.m.region"/></th>
									<th><aebiz:showTitle titleId="customeraddress.m.address"/></th>
									<th><aebiz:showTitle titleId="customeraddress.m.zipcode"/></th>
									<th><aebiz:showTitle titleId="customeraddress.m.mobile"/></th>
									<th><aebiz:showTitle titleId="customeraddress.m.telephone"/></th>
									<th><aebiz:showTitle titleId="customeraddress.m.candefault"/></th>
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
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="customeraddress.m.customerUuid"/></label>
									<input type="text" name="customerUuid_s" id="customerUuid_s" class="form-control">
									<input type="hidden" name="customerUuid_q" id="customerUuid_q" value="Like">
								</div>
							</div>
							
							<div class="col-sm-6">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="customeraddress.m.province"/></label>
									<input type="text" name="province_s" id="province_s" class="form-control">
									<input type="hidden" name="province_q" id="province_q" value="EQ">
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm-6">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="customeraddress.m.city"/></label>
									<input type="text" name="city_s" id="city_s" class="form-control">
									<input type="hidden" name="city_q" id="city_q" value="EQ">
								</div>
							</div>
							
							<div class="col-sm-6">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="customeraddress.m.region"/></label>
									<input type="text" name="region_s" id="region_s" class="form-control">
									<input type="hidden" name="region_q" id="region_q" value="EQ">
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm-6">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="customeraddress.m.address"/></label>
									<input type="text" name="address_s" id="address_s" class="form-control">
									<input type="hidden" name="address_q" id="address_q" value="EQ">
								</div>
							</div>
							
							<div class="col-sm-6">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="customeraddress.m.zipcode"/></label>
									<input type="text" name="zipcode_s" id="zipcode_s" class="form-control">
									<input type="hidden" name="zipcode_q" id="zipcode_q" value="EQ">
								</div>
							</div>
						</div>
							
						<div class="row">
							<div class="col-sm-6">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="customeraddress.m.mobile"/></label>
									<input type="text" name="mobile_s" id="mobile_s" class="form-control">
									<input type="hidden" name="mobile_q" id="mobile_q" value="EQ">
								</div>
							</div>
							
							<div class="col-sm-6">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="customeraddress.m.telephone"/></label>
									<input type="text" name="telephone_s" id="telephone_s" class="form-control">
									<input type="hidden" name="telephone_q" id="telephone_q" value="EQ">
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm-6">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="customeraddress.m.candefault"/></label>
									<input type="text" name="candefault_s" id="candefault_s" class="form-control">
									<input type="hidden" name="candefault_q" id="candefault_q" value="EQ">
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
	searchParam.push({ "name": "customerUuid_s", "value": $.trim($("#customerUuid_s").val())});
	searchParam.push({ "name": "customerUuid_q", "value": $.trim($("#customerUuid_q").val())});
	searchParam.push({ "name": "province_s", "value": $.trim($("#province_s").val())});
	searchParam.push({ "name": "province_q", "value": $.trim($("#province_q").val())});
	searchParam.push({ "name": "city_s", "value": $.trim($("#city_s").val())});
	searchParam.push({ "name": "city_q", "value": $.trim($("#city_q").val())});
	searchParam.push({ "name": "region_s", "value": $.trim($("#region_s").val())});
	searchParam.push({ "name": "region_q", "value": $.trim($("#region_q").val())});
	searchParam.push({ "name": "address_s", "value": $.trim($("#address_s").val())});
	searchParam.push({ "name": "address_q", "value": $.trim($("#address_q").val())});
	searchParam.push({ "name": "zipcode_s", "value": $.trim($("#zipcode_s").val())});
	searchParam.push({ "name": "zipcode_q", "value": $.trim($("#zipcode_q").val())});
	searchParam.push({ "name": "mobile_s", "value": $.trim($("#mobile_s").val())});
	searchParam.push({ "name": "mobile_q", "value": $.trim($("#mobile_q").val())});
	searchParam.push({ "name": "telephone_s", "value": $.trim($("#telephone_s").val())});
	searchParam.push({ "name": "telephone_q", "value": $.trim($("#telephone_q").val())});
	searchParam.push({ "name": "candefault_s", "value": $.trim($("#candefault_s").val())});
	searchParam.push({ "name": "candefault_q", "value": $.trim($("#candefault_q").val())});
	searchParam.push({ "name": "customerUuid", "value": $.trim($("#customerUuid").val())});
			
	
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
											{"mDataProp":"customerUuid" ,"sTitle": "<aebiz:showTitle titleId="customeraddress.m.customerUuid"/>"},
											{"mDataProp":"province" ,"sTitle": "<aebiz:showTitle titleId="customeraddress.m.province"/>"},
											{"mDataProp":"city" ,"sTitle": "<aebiz:showTitle titleId="customeraddress.m.city"/>"},
											{"mDataProp":"region" ,"sTitle": "<aebiz:showTitle titleId="customeraddress.m.region"/>"},
											{"mDataProp":"address" ,"sTitle": "<aebiz:showTitle titleId="customeraddress.m.address"/>"},
											{"mDataProp":"zipcode" ,"sTitle": "<aebiz:showTitle titleId="customeraddress.m.zipcode"/>"},
											{"mDataProp":"mobile" ,"sTitle": "<aebiz:showTitle titleId="customeraddress.m.mobile"/>"},
											{"mDataProp":"telephone" ,"sTitle": "<aebiz:showTitle titleId="customeraddress.m.telephone"/>"},
											{"mDataProp":"candefault" ,"sTitle": "<aebiz:showTitle titleId="customeraddress.m.candefault"/>"},
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/customeraddress/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/customeraddress/queryList" ,
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
	$("#customerUuid").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#customerUuid_s").val("") ;
	$("#province_s").val("") ;
	$("#city_s").val("") ;
	$("#region_s").val("") ;
	$("#address_s").val("") ;
	$("#zipcode_s").val("") ;
	$("#mobile_s").val("") ;
	$("#telephone_s").val("") ;
	$("#candefault_s").val("") ;
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
			    	"${pageContext.servletContext.contextPath}/customeraddress/deletes",
			    	{"selectOne":checkIds,ranNum:Math.random()},	
				    function(data) {	       
				       var result = eval("("+data+")") ;	       
				       if(result.rsp) {
				  			//刷新
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
	
	//高级搜索弹出框
	$("#searchshow").click(function(e) {    	
		e.preventDefault();
	  $("#modal-user").modal("show");
	});
	
	
	$(".clearMoreSearch").click(function(e) {    	
		clearMoreSearch() ;
	});
}) 
</script>