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
				<h1><aebiz:showTitle titleId="consultrecord.AppointmentList"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="consultrecord.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="consultrecord.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="consultrecord.AppointmentList"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
				</li>						
			</ul>				
		</div>				
							
		<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding y_tableser">
							<div class="y_clear">
								<div class="form-inline table_formnew">
									<input type="hidden" name="consultType" id="consultType" value="2">
									<input type="hidden" name="consultType_q" id="consultType_q" value="EQ">

									<div  class="form-group">
										<label class="control-label">患者姓名：</label>
										<input type="text" name="customerName" id="customerName" class="form-control">
										<label class="control-label">患者电话：</label>
										<input type="text" name="customerMobileq" id="customerMobileq" class="form-control">
										<label class="control-label">医生姓名：</label>
										<input type="text" name="doctorName" id="doctorName" class="form-control">
										<label class="control-label">医生电话：</label>
										<input type="text" name="doctorMobileq" id="doctorMobileq" class="form-control">
									</div>
									
									<div  class="form-group">
										<label class="control-label">审核状态：</label>
										<select name="state" id="state" class="form-control">
											<option value="">请选择</option>
											<option value="0">待审核</option>
											<option value="1">已审核</option>
											<option value="2">审核未通过</option>
										</select>
										<input type="hidden" name="state_q" id="state_q" value="EQ">
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
									<!--  更改表的表头名-->
									
										<th>患者姓名</th>
										<th>患者手机号</th>
										<th>医生姓名</th>
										<th>所在医院</th>										
										<th>就诊时间</th>
										<th>疾病</th>
										<th>预约目的</th>
										<th>创建时间</th>
										<th>审核状态</th>
										<th>审核类型</th>
									<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/>
									
									</th>
								</tr>
							</thead>
							
							<tbody>
							</tbody>
						</table>
					</div>		
				</div>
			</div>	
		</div>

</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
		searchParam.push({ "name": "customerName", "value": $.trim($("#customerName").val())});
		
		searchParam.push({ "name": "customerMobileq", "value": $.trim($("#customerMobileq").val())});
		
		searchParam.push({ "name": "doctorName", "value": $.trim($("#doctorName").val())});
		
		searchParam.push({ "name": "doctorMobileq", "value": $.trim($("#doctorMobileq").val())});
		searchParam.push({ "name": "consultType", "value": $.trim("2")});	
		searchParam.push({ "name": "consultType_q", "value": $.trim("EQ")});
	
		searchParam.push({ "name": "consultType", "value": $.trim("2")});	
		searchParam.push({ "name": "consultType_q", "value": $.trim("EQ")});
		
		searchParam.push({ "name": "state", "value": $.trim($("#state").val())});
		searchParam.push({ "name": "state_q", "value": $.trim($("#state_q").val())});
	
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
				                'aTargets': [0,1,2,3,4,6,7,9,10,11]                
				            }],
				            "bSort": true, //排序功能            
				            "aoColumns": [
										  {
										  	"mDataProp":"checkbox" ,
										  	"sDefaultContent":"",
										  	"sDefaultContent":"",
										  	"fnRender": function(obj) {
										      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
										      return sReturn;
										 		}
										  },
											{"mDataProp":"customerName" ,
											  "sDefaultContent":"",
											  "sTitle": "<aebiz:showTitle titleId="consultrecord.m.customerUuid"/>"},
											  {"mDataProp":"customerMobile" ,
											  "sDefaultContent":"",
											  "sTitle": "患者手机号"},
											{"mDataProp":"docoterName" ,
												"sDefaultContent":"",
												"sTitle": "医生姓名"},
												{"mDataProp":"hospitalName" ,
												"sDefaultContent":"",
												"sTitle": "医院名称"},
											{"mDataProp":"seeDoctorTime" ,
												"sDefaultContent":"",
												"sTitle": "就诊时间"},
											{"mDataProp":"orderIllness" ,
												"sDefaultContent":"",
												"sTitle": "疾病"},
											{
												"mDataProp":"subOrderReason" ,
												"sDefaultContent":"",
												"sTitle": "预约目的"
											},
											{"mDataProp":"createTime" ,
												"sDefaultContent":"",
												"sTitle": "创建时间"
											},	
											{
												"mDataProp":"state" ,
												"sDefaultContent":"",
												"sTitle": "审核状态",
												"fnRender" : function(obj) {
													var sReturn = ""
													if(obj.aData.state=="0"){
														sReturn="待审核"
													}else if(obj.aData.state=="1"){
														sReturn="已审核"
													}else if(obj.aData.state=="2"){
														sReturn="审核未通过"
													}
													return sReturn;
												}
											},
											{
												"mDataProp":"exam" ,
												"sDefaultContent":"",
												"sTitle": "审核类型",
												"fnRender" : function(obj) {
													var sReturn = ""
													if(obj.aData.exam=="1"){
													 sReturn="待平台审核"
													}else if(obj.aData.exam=="2"){
													 sReturn="待医生审核"
													}
													return sReturn;
												}
											},

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										  	
										  	"fnRender" : function(obj) {
												var sReturn = "";
												sReturn += "<a href='${pageContext.servletContext.contextPath}/sysback/consultrecord/toDetailAppointment/"
														+ obj.aData.uuid
														+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.check"/>'>查看</a>";								
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/consultrecord/queryList" ,
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
	$("#customerName").val("") ;
	$("#doctorName").val("") ;
	$("#doctorMobileq").val("") ;
	$("#customerMobileq").val("") ;
	$("#state").val("") ;
	$("#examq").val("") ;
	
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
			    	"${pageContext.servletContext.contextPath}/sysback/consultrecord/deletes",
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