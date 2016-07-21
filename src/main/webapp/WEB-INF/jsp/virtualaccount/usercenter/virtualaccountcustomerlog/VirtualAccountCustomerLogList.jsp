<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<!-- 日期插件 -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
</head>
<body>

<div class="container-fluid">
	<div class="page-header">
		<div class="pull-left">
			<h1>患者消费纪录</h1>
		</div>
		
		<!--<div class="pull-right">
			<ul class="stats">
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
				<span>财务管理</span> 
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span>患者财务管理</span> 
				<i class="fa fa-angle-right"></i>
			</li>
			
		</ul>
	</div>
	<div class="y_clear">
		
		<input type="hidden" name="pageShow" value="${wm.pageShow}">
			<div class="form-inline table_formnew">
				<div class="form-group">
					<label class="control-label">患者姓名:</label>
					<input type="text" name="realName" id="realName" class="form-control" value="${qm.realName}" >
				</div>
				<div class="form-group">
					<label class="control-label">患者用户名:</label>
					<input type="text" name="nickName" id="nickName" class="form-control" value="${qm.nickName}" >
				</div>
				
				<div class="form-group">
					<label class="control-label">患者手机号:</label>
					<input type="text" name="customerMobile" id="customerMobile" class="form-control" value="${qm.customerMobile}" >
				</div>
				
					
					<div class="form-group">	
						<label class="control-label">消费类型：</label>									
						<select name="payTypeq" id="payTypeq" class='form-control'>
							<option value="">--全部--</option>
							<option value="1">电话咨询</option>
							<option value="2">私人医生</option>
						</select>
					</div>
			
				,
				
	
				
				
					<div class="form-group">
						<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
					<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>											
					</div>
			</div>
	
	
	</div>
	<div class="table-responsive2">
		<table id="user" class="table table-bordered table-hover table-striped dataTable dataTable-nosort dataTable-reorder" data-nosort="7">
			<thead>		
				<tr>
						<th>患者姓名</th>
						<th>用户名</th>
						<th>消费金额</th>
						<th>消费类型</th>
						<th>支付方式</th>
						<th>消费时间</th>
						<th>操作</th>
				</tr>
			</thead>
			<tbody>
		
			</tbody>
		</table>
	</div>

</div>
	

<!--明细查看end-->
<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({"name" : "realName","value" : $.trim($("#realName").val())});
  searchParam.push({"name" : "nickName","value" : $.trim($("#nickName").val())});
	searchParam.push({"name" : "customerMobile","value" : $.trim($("#customerMobile").val())});
	searchParam.push({"name" : "payTypeq","value" : $.trim($("#payTypeq").val())});
	
	
   $.ajax({    	        
  	"dataType": 'json', 
	  "type": "GET", 
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
				                'aTargets': [0, 2]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
											{"mDataProp":"customerRealName" ,"sDefaultContent":"","sTitle": "患者姓名"},
											{"mDataProp":"customerNickName" ,"sDefaultContent":"","sTitle": "用户名"},
											{"mDataProp":"operAmount" ,"sDefaultContent":"","sTitle": "消费金额"},
											{"mDataProp":"payType" ,
												"sDefaultContent":"",
												"sTitle": "消费类型",
												"fnRender" : function(obj) {
													var sReturn = ""
													if(obj.aData.payType=="1"){
														sReturn="电话咨询"
													}else if(obj.aData.payType=="2"){
														sReturn="私人医生"
													}
													return sReturn;
												}
												},
											{"mDataProp":"orderType" ,"sDefaultContent":"","sTitle": "支付方式",
													"fnRender" : function(obj) {
													var sReturn = ""
													if(obj.aData.orderType=="1"){
														sReturn="线上支付"
													}else if(obj.aData.orderType=="2"){
														sReturn="线下支付"
													}
													return sReturn;
												}	
												},
											{"mDataProp":"orderTime" ,"sDefaultContent":"","sWidth":"3%","sDefaultContent":"","sTitle": "消费时间"},
										  {
										  	"mDataProp":"operate",
										  	"sWidth":"2%",
										  	"sDefaultContent":"",
										  	"sTitle": "操作" ,
										 		"fnRender": function(obj) {	
										 		   var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/virtualaccountlog/toLogView/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.view"/>'><aebiz:showTitle titleId="basebusiness.showmessage.view"/></a>" ;
											  	 return sReturn;
											 }			
										  },
										  
										],
									
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/virtualaccountlog/queryList" ,
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

$(".search").click(function(){	
	doSearch('search') ;  
})



function clearSearch() {
	$("#realName").val("") ;
	$("#nickName").val("") ;
	$("#mobile").val("") ;

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

$(document).ready(function() {	
	 //初始化表格
   doSearch('inittable') ;
   
 
	 
	// Validation 调用表单验证插件的js
  $(function(){
    if ($('.form-validate1').length > 0) {
        $('.form-validate1').each(function() {
            var id = $(this).attr('id');  //获取表单的id
            $("#" + id).validate({   //验证表单
                errorElement: 'span',  //输入错误时的提示标签
                errorClass: 'help-block has-error',  //输入错误时的提示标签类名
                errorPlacement: function(error, element) {  //输入错误时的提示标签显示的位置
                    if(element.parents(".input-group").length > 0){
                    		element.parents(".input-group").after(error);
                    }else if(element.parents(".y_validatainput").length > 0){
                    		element.parents(".y_validatainput").after(error);
                    }
                    else if(element.parents("label").length > 0) {
                        element.parents("label").after(error);
                    }else {
                        element.after(error);
                    }
                },
                highlight: function(label) {   //输入错误时执行的事件
                    $(label).closest('.form-group').removeClass('has-error has-success').addClass('has-error');
                },
                success: function(label) {   //输入正确时执行的事件
                    label.addClass('valid').closest('.form-group').removeClass('has-error has-success').addClass('has-success');
                },
                onkeyup: function(element) {   //验证元素输入值时按钮松开执行的事件
                    $(element).valid();
                },
                onfocusout: function(element) {   //验证元素失去焦点时进行验证
                    $(element).valid();
                },                
                submitHandler: function(form){
                	doPwdReset() ;  
				        //    form.submit(); //没有这一句表单不会提交
				        }
            });
        });
    }	
  })
}) 



</script>
</body>
</html>