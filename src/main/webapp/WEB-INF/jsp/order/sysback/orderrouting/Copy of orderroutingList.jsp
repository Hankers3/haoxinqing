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
			<h1><aebiz:showTitle titleId="orderrouting.moduleName_CN"/></h1>
		</div>
		
		<!--<div class="pull-right">
			<ul class="minitiles">
				<li class='grey'>
					<a href="#">
						<i class="fa fa-cogs"></i>
					</a>
				</li>
				<li class='lightgrey'>
					<a href="#">
						<i class="fa fa-globe"></i>
					</a>
				</li>
			</ul>
			<ul class="stats">
				<li class='satgreen'>
					<i class="fa fa-money"></i>
					<div class="details">
						<span class="big">$324,12</span>
						<span>Balance</span>
					</div>
				</li>
				<li class='lightred'>
					<i class="fa fa-calendar"></i>
					<div class="details">
						<span class="big">February 22, 2013</span>
						<span>Wednesday, 13:56</span>
					</div>
				</li>
			</ul>
		</div>-->
		
	</div>
	<div class="breadcrumbs">
		<ul>
			<li>
				<span><aebiz:showTitle titleId="orderrouting.menuOne"/></span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span><aebiz:showTitle titleId="orderrouting.menuTwo"/></span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span><aebiz:showTitle titleId="orderrouting.moduleName_CN"/></span>
			</li>
		</ul>
	</div>
	<ul class="tabs tabs-inline tabs-top border1-top">
		<li class='active'>
			<a href="#Product1" data-toggle='tab' id='tab1'><aebiz:showTitle titleId="orderrouting.m.dayRoutingTable"/></a>
		</li>
		<li>
			<a href="#Product2" data-toggle='tab' id='tab2'><aebiz:showTitle titleId="orderrouting.m.monthRoutingTable"/></a>
		</li>
		<li>
			<a href="#Product3" data-toggle='tab' id='tab3'><aebiz:showTitle titleId="orderrouting.m.yearRoutingTable"/></a>
		</li>
	</ul>			
	<div class="tab-content y_tabdatable">
	<!-- 日分账start -->
		<div class="tab-pane active" id="Product1">
			<div class="y_clear">
				<div class="form-inline table_formnew">
					<div class="form-group">
						<label class="control-label"><aebiz:showTitle titleId="orderrouting.qmf.routTime"/>：</label>
						<input type="text" name="routTime" id="routTime" class="form-control datepick">
						<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
					</div>
				</div>
			</div>
			<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
				<thead>		
					<tr>
						<th><aebiz:showTitle titleId="orderrouting.m.staffName"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.routPrice"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.orderTotalAmount"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.freeTotalMoney"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.orderPaidAmount"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.routingTotalAmount"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.orderMainUuid"/></th>
						<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
					</tr>
				</thead>
				
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- 日分账end -->
		<!-- 月分账start -->
		<div class="tab-pane" id="Product2">
		<input type="hidden" name="fzType" id="fzType"  value="0" class="form-control" >
			<div class="y_clear">
				<div class="form-inline table_formnew">
					<div class="form-group">	
						<label class="control-label"><aebiz:showTitle titleId="orderrouting.m.chooseYear"/>:</label>
						<select name="routYear" id="routYear" class='form-control'>
						</select>
						<label class="control-label"><aebiz:showTitle titleId="orderrouting.m.chooseMonth"/>:</label>
						<select name="routMonth" id="routMonth" class='form-control'>
						</select>
					</div>
					<div class="form-group">
						<button class="btn btn-primary searchMonth1" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
					</div>
				</div>
			</div>
			<table id="user" class="table table-bordered table-hover table-striped dataTable1  dataTable-reorder">
				<thead>		
					<tr>
						<th><aebiz:showTitle titleId="orderrouting.m.staffName"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.monthRoutingTotalMoney"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.orderTotalAmount"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.orderFreeTotalMoney"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.orderPaidTotalMoney"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.orderRoutingTotalMoney"/></th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
		</div>
		<!-- 月分账end -->
		<!-- 年分账start -->
		<div class="tab-pane" id="Product3">
			<div class="y_clear">
				<div class="form-inline table_formnew">
					<div class="form-group">	
						<label class="control-label"><aebiz:showTitle titleId="orderrouting.m.chooseYear"/>:</label>
						<select name="routOnlyYear" id="routOnlyYear" class='form-control'>
							<option value="">--<aebiz:showTitle titleId="orderrouting.m.pleaseChoose"/>--</option>
						</select>
					</div>
					<div class="form-group">
						<button class="btn btn-primary searchYear" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
					</div>
				</div>
			</div>
			<table id="user1" class="table table-bordered table-hover table-striped dataTable2  dataTable-reorder">
				<thead>		
					<tr>
						<th><aebiz:showTitle titleId="orderrouting.m.staffName"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.yearRoutingTotalMoney"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.orderTotalAmount"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.orderFreeTotalMoney"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.orderPaidTotalMoney"/></th>
						<th><aebiz:showTitle titleId="orderrouting.m.orderRoutingTotalMoney"/></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- 年分账end -->
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
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="orderrouting.m.orderMainUuid"/></label>
					<input type="text" name="orderMainUuid_s" id="orderMainUuid_s" class="form-control">
					<input type="hidden" name="orderMainUuid_q" id="orderMainUuid_q" value="EQ">
					</div>
					</div>
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="orderrouting.m.serviceStaffUuid"/></label>
					<input type="text" name="serviceStaffUuid_s" id="serviceStaffUuid_s" class="form-control">
					<input type="hidden" name="serviceStaffUuid_q" id="serviceStaffUuid_q" value="EQ">
					</div>
					</div>
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="orderrouting.m.routTime"/></label>
					<input type="text" name="routTime_s" id="routTime_s" class="form-control">
					<input type="hidden" name="routTime_q" id="routTime_q" value="EQ">
					</div>
					</div>
					</div>
					<div class="row">
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="orderrouting.m.routPrice"/></label>
					<input type="text" name="routPrice_s" id="routPrice_s" class="form-control">
					<input type="hidden" name="routPrice_q" id="routPrice_q" value="GE">
					</div>
					</div>
					</div>
					<div class="row">
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="orderrouting.qm.routTime2"/></label>
					<input type="text" name="routTime2_s" id="routTime2_s" class="form-control">
					<input type="hidden" name="routTime2_q" id="routTime2_q" value="LT">
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
		<!--分账详细start  -->
		<div id="modal-user3" class="modal fade y_highserch">
			<div id="showroutingdetail"></div>
		</div>
		<!--分账详细end-->
</body>
</html>

<script>

$("#tab1").click(function(){
	$("#fzType").val("0");
	doSearch('search') ;
});


function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "routTime", "value": $.trim($("#routTime").val())});
			
	
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
											{"mDataProp":"serviceStaffName" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.staffName"/>"},
											{"mDataProp":"oneStaffRoutPrice" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.routPrice"/>"},
											{"mDataProp":"orderTotalAmount" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.orderTotalAmount"/>"},
											{"mDataProp":"freeMoney" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.freeTotalMoney"/>"},
											{"mDataProp":"paid" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.orderPaidAmount"/>"},
											{"mDataProp":"orderTotalAmount" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.routingTotalAmount"/>"},
											{"mDataProp":"orderId" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.orderMainUuid"/>"},
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a onclick='javascript:toView(\""+obj.aData.orderMainUuid+"\",\""+obj.aData.serviceStaffUuid+"\")' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="orderrouting.m.viewDetail"/>'  data-toggle='modal' data-target='#modal-user3'  ><aebiz:showTitle titleId="orderrouting.m.viewDetail"/></a>" ;															
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/orderrouting/queryList" ,
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
	doSearch('search') ;
})


//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#orderMainUuid_s").val("") ;
	$("#serviceStaffUuid_s").val("") ;
	$("#routTime_s").val("") ;
	$("#routPrice_s").val("") ;
	$("#routTime2_s").val("") ;
	
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
			    	"${pageContext.servletContext.contextPath}/sysback/orderrouting/deletes",
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

function toView(orderMainUuid,serviceStaffUuid){	
	var time = $.trim($("#routTime").val());
	if(time == ""){
		time = null ;
	}
	var url="${pageContext.servletContext.contextPath}/sysback/orderrouting/routingdetails/"+serviceStaffUuid+"/"+time;
	$.post(url,
	{
		"orderMainUuid" : orderMainUuid,
		ranNum : Math.random()
	},
	function(data){
		$("#showroutingdetail").html(data);
	}
	);
	
}
</script>
<script>
$(".searchMonth1").click(function(){	
	//普通搜索需要清空高级搜索的选项
	clearMoreSearch();
	$("#fzType").val("1");
	doSearchMonth('searchMonth1') ;  
})

function retrieveData1(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "routYear", "value": $.trim($("#routYear").val())});		
	searchParam.push({ "name": "routMonth", "value": $.trim($("#routMonth").val())});		
	searchParam.push({ "name": "fzType", "value": $.trim($("#fzType").val())});		
	
   $.ajax({    	        
  	"dataType": 'json', 
	  "type": "POST", 
	  "url": sSource, 
	  "data": {aoData:JSON.stringify(aoData),searchParam:JSON.stringify(searchParam)}, 
	  "success": fnCallback
  });    
} 


$("#tab2").click(function(){
	var myDate = new Date();
    var year = myDate.getFullYear();
    var month=myDate.getMonth()+1;
    if(month<10){
    	month = "0"+month;
    }
	$("#fzType").val("1");
	$("#routYear").val(year);
	$("#routMonth").val(month);
	doSearchMonth('search') ;
});

var oTableMonth = null; 
function doSearchMonth(operact) {	
	if(oTableMonth == null) {		 
    if ($('.dataTable1').length > 0) {
        $('.dataTable1').each(function() {
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
										  
											{"mDataProp":"serviceStaffName" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.staffName"/>"},
											{"mDataProp":"oneStaffRoutPrice" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.monthRoutingTotalMoney"/>"},
											{"mDataProp":"orderTotalAmount" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.orderTotalAmount"/>"},
											{"mDataProp":"freeMoney" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.orderFreeTotalMoney"/>"},
											{"mDataProp":"paid" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.orderPaidTotalMoney"/>"},
											{"mDataProp":"orderTotalAmount" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.orderRoutingTotalMoney"/>"},
												

										  
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/orderrouting/queryList" ,
										"fnServerData":retrieveData1,           //获取数据的处理函数  
				        };
				        
                		if ($(this).hasClass("dataTable-reorder")) {
		                    opt.sDom = "R" + opt.sDom;
		                }
		               
                		oTableMonth = $(this).dataTable(opt);
                
        	});
   		}         
	}
		
	//刷新Datatable，会自动激发retrieveData  		
  	if('inittable' != operact) {  			
  		oTableMonth.fnDraw(); 	
	}  
	 	  
}
</script>
<script>
$(".searchYear").click(function(){	
	//普通搜索需要清空高级搜索的选项
	clearMoreSearch();
	$("#fzType").val("2");
	doSearchYear('searchYear') ;  
})

function retrieveData3(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "routOnlyYear", "value": $.trim($("#routOnlyYear").val())});		
	searchParam.push({ "name": "fzType", "value": $.trim($("#fzType").val())});		
	
   $.ajax({    	        
  	"dataType": 'json', 
	  "type": "POST", 
	  "url": sSource, 
	  "data": {aoData:JSON.stringify(aoData),searchParam:JSON.stringify(searchParam)}, 
	  "success": fnCallback
  });    
} 


$("#tab3").click(function(){
	$("#fzType").val("2");
	var myDate = new Date();
    var year = myDate.getFullYear();
    $("#routOnlyYear").val(year);
	doSearchYear('search') ;
});

var oTableYear = null; 
function doSearchYear(operact) {	
	if(oTableYear == null) {		 
    if ($('.dataTable2').length > 0) {
        $('.dataTable2').each(function() {
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
										  
											{"mDataProp":"serviceStaffName" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.staffName"/>"},
											{"mDataProp":"oneStaffRoutPrice" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.yearRoutingTotalMoney"/>"},
											{"mDataProp":"orderTotalAmount" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.orderTotalAmount"/>"},
											{"mDataProp":"freeMoney" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.orderFreeTotalMoney"/>"},
											{"mDataProp":"paid" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.orderPaidTotalMoney"/>"},
											{"mDataProp":"orderTotalAmount" ,"sTitle": "<aebiz:showTitle titleId="orderrouting.m.orderRoutingTotalMoney"/>"},
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/orderrouting/queryList" ,
										"fnServerData":retrieveData3,           //获取数据的处理函数  
				        };
				        
                		if ($(this).hasClass("dataTable-reorder")) {
		                    opt.sDom = "R" + opt.sDom;
		                }
		               
                		oTableYear = $(this).dataTable(opt);
                
        	});
   		}         
	}
	//刷新Datatable，会自动激发retrieveData  		
  	if('inittable' != operact) {  			
  		oTableYear.fnDraw(); 	
	}  
	 	  
}
</script>
 <script type="text/javascript">
 $(function(){
     var yearid = $('#routYear')    //年所在的控件
     var myDate = new Date();
     var year = myDate.getFullYear();
     for(var i=2014;i<=(year+5);i++){
         yearid.append('<option value="'+i+'">'+i+'</option>')
     }
 })

 $(function(){
     var monthid = $('#routMonth')    //年所在的控件
     var myDate = new Date();
     var month=myDate.getMonth()+1;
     for(var i=1;i<=12;i++){
     	if(i<10){
     		monthid.append('<option value="'+'0'+i+'">0'+i+'</option>')
     	}else{
     		monthid.append('<option value="'+i+'">'+i+'</option>')
     	}
     }
 })
 
 
 
     
    </script>
 <script type="text/javascript">
        $(function(){
            var yearid = $('#routOnlyYear')    //年所在的控件
            var myDate = new Date();
            var year = myDate.getFullYear();
            for(var i=2014;i<=(year+5);i++){
                yearid.append('<option value="'+i+'">'+i+'</option>')
            }
        })
    </script>