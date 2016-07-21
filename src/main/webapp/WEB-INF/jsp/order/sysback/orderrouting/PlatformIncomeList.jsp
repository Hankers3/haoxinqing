<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script>   
</head>

<body>
	<div class="container-fluid">	
				<div class="page-header">
					<div class="pull-left">
						<h1>平台收益统计</h1>
					</div>
				</div>
				
				<div class="breadcrumbs">
					<ul>
						<li>
							<span>财务管理</span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span>平台收益统计</span>
							<i class="fa fa-angle-right"></i>							
						</li>							
										
					</ul>				
				</div>		
						
									
				<div class="row">
						<div class="col-sm-12">
					<div class="box">
								<div class="box-content nopadding y_tableser">
									<div class="y_clear">
										<div class="form-inline table_formnew">
												<!--收益的全部类型 -->
											<div class="form-group">
											<label class="control-label">收益类型：</label> 
											<select name="incomeType" id="incomeType" class='form-control'>
											<option value="">全部</option>
											<option value="0">电话咨询</option>
											<option value="1">私人医生</option>
											</select>	
											<input type="hidden" name="incomeType_q" id="incomeType_q" class="form-control" value="EQ">
											</div>
										
											
											<!--医生真实姓名 -->
											<div  class="form-group">
												<label class="control-label">医生姓名：</label>
												<input type="text" name="doctorRealName" id="doctorRealName" class="form-control">
											
							     		</div>
											<div  class="form-group">
												<label class="control-label">医生手机号：</label>
												<input type="text" name="doctorMobile" id="doctorMobile" class="form-control">
											    <!--这个值为like就是模糊查询，EQ就是查询 -->
												
											</div>

											
											<div class="form-group">
												<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
												<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>											
											</div>
										</div>
								</div>
								<div class="pull-left" style="padding: 0px 0 5px 14px">
								  <span>收益总额（元） ：${totalMoney}</span>
					     	</div>
							<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
								<thead>		
									<tr>

										<th>收益类型</th>
										<th>收益来源（医生姓名）</th>
										<th>金额</th>
										<th>收益时间</th>
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

	searchParam.push({"name" : "incomeType","value" : $.trim($("#incomeType").val())});
	searchParam.push({"name" : "incomeType_q","value" : $.trim($("#incomeType_q").val())}); 
		
	searchParam.push({"name" : "doctorRealName","value" : $.trim($("#doctorRealName").val())});
	searchParam.push({"name" : "doctorMobile","value" : $.trim($("#doctorMobile").val())});

 
	
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


											{"mDataProp":"incomeType" ,"sWidth":"5%","sDefaultContent":"","sTitle": "收益类型",
												"fnRender" : function(obj) {
													var sReturn = ""
													if(obj.aData.incomeType=="0"){
														sReturn="电话咨询"
													}else if(obj.aData.incomeType=="1"){
														sReturn="私人医生"
													}
													return sReturn;
												}	
												},
											{"mDataProp":"serviceStaffName" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "收益来源"},
											{"mDataProp":"routPrice" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "金额"},
										  {"mDataProp":"routTime" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "收益时间"},  
										],
									
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
		oTable.fnDraw(false); 	
	}  
	 	  
}

$(".search").click(function(){	
	doSearch('search') ;  
})



function clearSearch() {
	$("#doctorRealName").val("") ;
	$("#doctorMobile").val("") ;
	$("#incomeType").val("") ;

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