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
			<h1>医生收入明细</h1>
		</div>
	</div>
	
	<div class="breadcrumbs">
		<ul>
			<li><span>订单管理</span><i class="fa fa-angle-right"></i></li>
			<li><span>收入管理</span><i class="fa fa-angle-right"></i></li>
			<li><span>医生收入明细</span></li>
		</ul>
	</div>
	
	<div class="box box-bordered bordered-top">	
		<div class="box-content nopadding" id="Product">
				<form  action="" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">		
					<!--医生基本信息-->
					<div class="form-group">
						<div class="col-sm-12">
							<div class="col-sm-3">
								<i class="fa fa-user"></i>医生基本信息
							</div>
						</div>
					</div>
						<!--流水号-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">医生ID</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${servicestaffModel.doctorNo}
							</div>
						</div>
					</div>
					
					<!--真实姓名-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">医生姓名</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${servicestaffModel.realName}
							</div>
						</div>
					</div>
					
					
					<!--医院-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">所在医院</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${servicestaffModel.hospitalName}
							</div>
						</div>
					</div>
					
					<!--科室-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">科室</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${servicestaffModel.departmentName}
							</div>
						</div>
					</div>
					
					<!--当前余额-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">当前余额</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${servicestaffModel.accountAmount}
							</div>
						</div>
					</div>	
				</form>				
			</div>
		</div>
			
		<!-- 分账start -->
		<div id="Product1">

			<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
			<!--埋藏医生主键-->
			<input type='hidden' name='doctorUuid' id='doctorUuid' value='${doctorUuid}' >
				<thead>		
					<tr>
						<th>收入类型</th>
						<th>收入时间</th>
						<th>收入金额</th>
						<th>平台金额</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- 分账end -->
						
		<div class="y_fixedbtn">
		 		<button type="button" class="btn btn-primary cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
	   </div>
	</div>        
</body>
</html>

<script>

$(".cancel").click(function(){
				history.go(-1) ;
			});	


function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	//searchParam.push({ "name": "incomeType", "value": $.trim($("#incomeType").val())});
	//searchParam.push({ "name": "incomeType_q", "value": $.trim($("#incomeType_q").val())});

	// 查看该医生的收入
	searchParam.push({ "name": "doctorUuid", "value": $.trim($("#doctorUuid").val())});	
	
	

	
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
											{"mDataProp":"incomeType",
											 "sTitle": "收入类型" ,
											 "fnRender": function(obj) {	
											  		var sReturn ="";
											  		if(obj.aData.incomeType==0){
											  			sReturn+="电话咨询";
											  		}else if(obj.aData.incomeType==1){
											  			sReturn+="私人医生";
											  		}else if(obj.aData.incomeType==2){
											  			sReturn+="加号";
											  		}else if(obj.aData.incomeType==3){
											  			sReturn+="图文咨询";
											  		}
											      return sReturn;
												 }			
											},
											{"mDataProp":"routTime" ,"sTitle": "收入时间"},
											{"mDataProp":"routPrice" ,
											"sTitle": "收入金额",
											 "fnRender": function(obj) {
												 var Price =obj.aData.routPrice;
												 sReturn = Math.round(Price*100)/100;
												 return sReturn;
											 	}
											},
											{
											"mDataProp":"routPrice" ,"sTitle": "平台金额",
											"fnRender": function(obj) {
												 var Price =obj.aData.routPrice;
												 sReturn = Math.round(Price*100)/100;
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
		
	

}) 

  function clearSearch() {
	$("#incomeType").val("") ;
	}

</script>


