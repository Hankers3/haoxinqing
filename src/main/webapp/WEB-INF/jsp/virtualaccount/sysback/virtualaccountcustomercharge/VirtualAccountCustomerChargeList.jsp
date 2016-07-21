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
						<h1>患者充值记录</h1>
					</div>
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
						<li>
							<span>患者充值记录</span>
						</li>						
					</ul>				
				</div>		
						
									
				<div class="row">
						<div class="col-sm-12">
					<div class="box">
								<div class="box-content nopadding y_tableser">
									<div class="y_clear">
										<div class="form-inline table_formnew">
											<!--真实姓名 -->
											<div  class="form-group">
												<label class="control-label">患者姓名：</label>
												<input type="text" name="realName" id="realName" class="form-control">
							     		</div>
							     		
							     		<div  class="form-group">
												<label class="control-label">患者用户名：</label>
												<input type="text" name="customerName" id="customerName" class="form-control">
							     		</div>
							     		
											<div  class="form-group">
												<label class="control-label">患者手机号：</label>
												<input type="text" name="mobile" id="mobile" class="form-control">												
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

										<th>患者姓名</th>
										<th>患者用户名</th>
										<th>充值金额</th>
										<th>充值方式</th>
										<th>充值时间</th>
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
 </div>
	

	
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;

	searchParam.push({ "name": "realName", "value": $.trim($("#realName").val())});
	searchParam.push({ "name": "mobile", "value": $.trim($("#mobile").val())});
	searchParam.push({ "name": "customerName", "value": $.trim($("#customerName").val())});
  
	
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
				                'aTargets': [0,1,3,5]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [


											{"mDataProp":"customerRealName" ,"sDefaultContent":"","sWidth":"5%","sDefaultContent":"","sTitle": "患者姓名"},
											{"mDataProp":"customerName" ,"sDefaultContent":"","sWidth":"5%","sDefaultContent":"","sTitle": "患者用户名"},
											{"mDataProp":"operAmount" ,"sDefaultContent":"","sWidth":"5%","sDefaultContent":"","sTitle": "充值金额"},
											{
												"mDataProp":"payType" ,
												"sDefaultContent":"",
												"sWidth":"5%",
												"sDefaultContent":"",
												"sTitle": "充值方式",
												"fnRender": function(obj) {	
											  		var sReturn ="";
											  		if(obj.aData.payType==1){
											  			sReturn+="微信";
											  		}else if(obj.aData.payType==2){
											  			sReturn+="支付宝";
											  		}else{
											  			sReturn+="其他";
											  			}
											      return sReturn;
											 }	
												
											},
											{"mDataProp":"createTime" ,"sDefaultContent":"","sWidth":"5%","sDefaultContent":"","sTitle": "充值时间"},
										  {
										  	"mDataProp":"operate",
										  	"sWidth":"8%",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 		"fnRender": function(obj) {	
										 		   var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/virtualaccountcharge/toView/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.view"/>'><aebiz:showTitle titleId="basebusiness.showmessage.view"/></a>" ;
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/virtualaccountcharge/queryList" ,
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
	$("#customerName").val("") ;
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