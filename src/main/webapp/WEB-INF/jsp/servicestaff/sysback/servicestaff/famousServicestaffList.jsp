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
 <style>
	@media (max-width: 1200px){
		#modal-push .modal-dialog {
		    width: 90% !important;
		}	
	}
 </style>
</head>

<body>
	<div class="container-fluid">	
				<div class="page-header">
					<div class="pull-left">
						<h1>名医风采管理</h1>
					</div>
				</div>
				
				<div class="breadcrumbs">
					<ul>
						<li>
							<span>运营系统</span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span>运营内容管理</span>
							<i class="fa fa-angle-right"></i>							
						</li>							
						<li>
							<span>名医风采管理</span>
						</li>						
					</ul>				
				</div>		
						
									
				<div class="row">
						<div class="col-sm-12">
					<div class="box">
								<div class="box-content nopadding y_tableser">
									<div class="y_clear">
										<div class="form-inline table_formnew">
											<!--名医风采状态 -->
											<input type="hidden" name="doctorType" id="doctorType"  value="1" class="form-control" >
											<input type="hidden" name="doctorType_q" id="doctorType_q" value="EQ">	
											<input type="hidden" name="sate" id="sate"  value="1" class="form-control" >	
											<input type="hidden" name="sate_q" id="sate_q" value="EQ">
											
										</div>
										<div class="y_tablebtn">
											<a class="btn" onclick="toSortServicestaff()"  title="排序" rel="tooltip" data-toggle="modal" >排序</a>
										
											<a class="btn" onclick="toAddFamousServicestaff()"  title="新增" rel="tooltip" data-toggle="modal" data-target="#modal-push">新增</a>
									 </div>
								</div>
							<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
								<thead>		
									<tr>
										<th><aebiz:showTitle titleId="servicestaffinfo.m.realName"/></th>
										<th><aebiz:showTitle titleId="servicestaff.m.mobile"/></th>
										<th><aebiz:showTitle titleId="servicestaffinfo.m.hospital"/></th>
										<th><aebiz:showTitle titleId="servicestaffinfo.m.department"/></th>
										<th><aebiz:showTitle titleId="servicestaffinfo.m.departmentLine"/></th>
										<th><aebiz:showTitle titleId="servicestaff.m.frozenTyp"/></th>
										<th><aebiz:showTitle titleId="servicestaff.m.createTime"/></th>
										<th>排序</th>
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
 

	<!--名医风采弹出框-->
	<div id="modal-push" class="modal fade">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-body">
					<div id="pushFamousServicestaff" ></div>
				</div>													
			</div>
		</div>
	</div>
   
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "doctorType", "value":$.trim($("#doctorType").val())});
	searchParam.push({ "name": "doctorType_q", "value": $.trim($("#doctorType_q").val())});

	searchParam.push({ "name": "sate", "value":$.trim($("#sate").val())});
  	searchParam.push({ "name": "sate_q", "value": $.trim($("#sate_q").val())});
	
   $.ajax({    	        
  	"dataType": 'json', 
	  "type": "POST", 
	  "url": sSource, 
	  "data": {aoData:JSON.stringify(aoData),searchParam:JSON.stringify(searchParam)}, 
	  "success": fnCallback
  });    
} 


//跳转到添加名医页面
function toAddFamousServicestaff(){
	var url="${pageContext.servletContext.contextPath}/sysback/servicestaff/toAddFamousServicestaff";
		$.post(url,
		{
			ranNum : Math.random()
		},
		function(data){
				$("#pushFamousServicestaff").html(data);
		}
	);
}
    
//跳转到排序方法
function toSortServicestaff(){
	var str="";
	var flag = false;
	$("input[name^='sort_']").each(function(){

		var sort = $(this).val().trim();
		if(isNaN(sort)){
			alert("排序顺序只能为数据");
			$(this).focus();
			flag = true;
			return;
		}
		var id = $(this).attr("id");
		str += id+","+sort+";";
 	});
	
	if(flag){
		return;
	}
	var url="${pageContext.servletContext.contextPath}/sysback/servicestaff/updateSortServicestaff?sort="+str;
		$.post(url,
		//{	ranNum : Math.random()	},
		function(data){
			var result = eval("("+data+")") ;
		     if(result.rsp) {
				// alert("更新成功!");
				 bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
				 doSearch('search') ;    
			 }
		}
	);
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
				                'aTargets': [5,8]                
				            }],
				        	"aaSorting" : [ [ 7, "asc" ] ],
				            "bSort": true, //排序功能            
					         	"aoColumns": [

										/*   {"mDataProp":"doctorNo" ,"sWidth":"5%", "sDefaultContent":"","sTitle": "医生ID"}, */
											{"mDataProp":"realName" ,"sWidth":"5%","sDefaultContent":"","sTitle": "医生姓名"},
											{"mDataProp":"mobile" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "手机号码"},
											{"mDataProp":"hospitalName" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "医院名称"},
											{"mDataProp":"departmentName" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "科室名称"},
											{"mDataProp":"departmentLine" ,"sWidth":"5%", "sDefaultContent":"","sTitle": "科室电话"},
											
											{
										  	"mDataProp":"frozenSate1",
										  	"sDefaultContent":"",
										  	"sWidth":"5%",
										  	"sTitle": "是否冻结" ,
										 		"fnRender": function(obj) {	
											  		var sReturn ="";
											  		if(obj.aData.frozenSate==0){
											  			sReturn+="否";
											  		}else if(obj.aData.frozenSate==1){
											  			sReturn+="是";
											  		}
											      return sReturn;
											 }			
											},
											
											
											{"mDataProp":"createTime" ,"sWidth":"10%", "sDefaultContent":"","sTitle": "注册时间"},
											
											{"mDataProp":"sort" ,
												"sWidth":"10%", 
												"sDefaultContent":"",
												"sTitle": "排序",
												"fnRender": function(obj) {	
													var sReturn = "<input type='text' name='sort_"+obj.aData.uuid+"' id='"+obj.aData.uuid+"' value='"+obj.aData.sort+"' class='form-control' data-rule-maxlength='5'  /> ";
												    return sReturn;
												 }	
											},
											
						
										  {
										  	"mDataProp":"operate",
										  	"sWidth":"5%",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 		"fnRender": function(obj) {	
											  	 var	sReturn ="<a href='${pageContext.servletContext.contextPath}/sysback/servicestaff/toUpdateFamousServicestaff/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='操作'>删除</a>" ;
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/servicestaff/queryList" ,
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
	$("#mobile").val("") ;
	$("#email").val("") ;
	$("#provinceId").val("") ;
	$("#cityId").val("") ;
	$('#frozenSate option:eq("")').attr('selected','selected')
	$('#hospital option:eq("")').attr('selected','selected')
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
			    	"${pageContext.servletContext.contextPath}/sysback/servicestaff/deletes",
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

$(document).ready(function() {	
	 //初始化表格
   doSearch('inittable') ;
   
   //复选框全选
	 $("#check_all").click(function(e) {
	 		$('input', oTable.fnGetNodes()).prop('checked', this.checked);
	 });  
	 
	 //修改密码强弱校验  
	 $("#pwd").change(function(e) {
	 		EvalPwd($("#pwd").val()) ;
	 });  
	 
	 //修改密码强弱校验  
	 $("#pwd").keyup(function(e) {
	 		EvalPwd($("#pwd").val()) ;
	 });
	 
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

function  doPwdReset(){
	
	$("span[name='passwordspan']").remove();
	//1.获得密码和确认密码
	var pwd = $("#pwd").val() ;
	var confirmPwd = $("#confirmPwd").val() ;
	
	//2.匹配密码和确认密码是否一致
	if(pwd != confirmPwd){		
			$("#confirmPwdError	").addClass("has-error") ;
			$("#confirmPwd").after("<span for='confirmPwd' class='help-block has-error'><aebiz:showTitle titleId='sysmanager.m.confirmpwdnoequal'/></span>") ;
			return ;
	}else{
			$("#confirmPwdError	").removeClass("has-error") ;
			$("#confirmPwd").after("") ;
	}
	
	var requestUrl = "${pageContext.servletContext.contextPath}/sysback/servicestaff/pwdreset" ;
	$.getJSON(requestUrl ,{pwd:pwd ,confirmPwd:confirmPwd,uuid:$("#noServiceStaffUuid").val() ,ranNum:Math.random()} ,function(data){
		if("success"==data.message) {
			$("#confirmPwd").after("<span for='confirmPwd' name='passwordspan'  class='help-block' style='color:red'><aebiz:showTitle titleId='sysmanager.m.resetsuccess'/></span>") ;
		}else if("pwd_empty"==data.message) {
			$("#pwdError	").addClass("has-error") ;
			$("#pwd").after("<span for='confirmPwd' class='help-block has-error'><aebiz:showTitle titleId='sysmanager.m.confirmpwdnoequal'/></span>") ;
			return ;				
		}else if("no_equal"==data.message) {	
			$("#confirmPwdError	").addClass("has-error") ;
			$("#confirmPwd").after("<span for='confirmPwd' class='help-block has-error'><aebiz:showTitle titleId='sysmanager.m.confirmpwdnoequal'/></span>") ;
			return ;			
		}
	})
}
function getNowServiceStaff(ServiceStaffUuid) {	
	$("#noServiceStaffUuid").val(ServiceStaffUuid) ;
	$("#pwd").val("") ;
	$("#confirmPwd").val("") ;
}


function updateFrozen(uuid,frozenSate){
		var checkIds="";
		if( uuid.trim() != "") {
			checkIds = uuid ;
		}
		//删除
		$.get("${pageContext.servletContext.contextPath}/sysback/servicestaff/unfrozen",
	    	{"selectOne":checkIds,frozenSate:frozenSate,ranNum:Math.random()},	
		    function(data) {	
		       if(data=="true") {
		  			//刷新
						doSearch('search') ;     	
		       }else{
		       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
		       }
		    }
		);		
	 
}
</script>