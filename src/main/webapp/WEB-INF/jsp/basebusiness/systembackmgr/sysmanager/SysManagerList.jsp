<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>

</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="sysmanager.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="sysmanager.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>					
				<li>
					<span><aebiz:showTitle titleId="sysmanager.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
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
										<label class="control-label"><aebiz:showTitle titleId="sysmanager.qmf.name"/>：</label>
										<input type="text" name="name" id="name" class="form-control">
										<input type="hidden" name="name_q" id="name_q" class="form-control" value="Like">
									</div>
									
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="sysmanager.qmf.departmentUuid"/>：</label>
										<select name="departmentUuid" id="departmentUuid" class='select2-me form-control'>								
											<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
												<c:if test="${!empty returnDept}">	
													<c:forEach items="${returnDept}" var="dept">	
														<option value="${dept.uuid}" >${dept.departmentName}</option>
													</c:forEach>		
											</c:if>									
										</select>											
										<input type="hidden" name="departmentUuid_q" id="departmentUuid_q" class="form-control" value="EQ">
									</div>
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>										
									</div>
								</div>
								
								<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/sysmanager/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="sysmanager.m.id"/></th>
									<th><aebiz:showTitle titleId="sysmanager.m.name"/></th>									
									<th><aebiz:showTitle titleId="sysmanager.m.departmentUuid"/></th>
									<th><aebiz:showTitle titleId="sysmanager.m.createTime"/></th>				
									<th><aebiz:showTitle titleId="sysmanager.m.state"/></th>	
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
	
	
		<!--密码修改-->
		<form id="mainForm" action="" method="get" class='form-validate1'>						
			<div id="modal-user" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 id="user-infos"><aebiz:showTitle titleId="sysmanager.m.pwdreset"/></h4>
						</div>
						
						<div class="modal-body">
								<div class="row">
									<div class="col-sm-6">
										<div id="pwdError" class="form-group">
											<label for="textfield" class="control-label"><aebiz:showTitle titleId="sysmanager.m.pwd"/></label>
											<input type="password" name="pwd" id="pwd" class="form-control" data-rule-required="true" data-rule-minlength="6" data-rule-maxlength="20">	
										</div>
									</div>
									<div class="col-sm-6">
										<div  id="pwdpower" class="paddword_leve paddword_leve_3" style="margin-top:27px;"> 
											<span id="pweak" style=""><aebiz:showTitle titleId="system.m.pweak"/></span>
											<span id="pmedium" style=""><aebiz:showTitle titleId="system.m.pmedium"/></span>
											<span id="pstrong" style=""><aebiz:showTitle titleId="system.m.pstrong"/></span>
										</div> 	
									</div>	
								</div>
																
								<div class="row">
									<div class="col-sm-6">
										<div id="confirmPwdError" class="form-group">
											<label for="textfield" class="control-label"><aebiz:showTitle titleId="sysmanager.m.confirmpwd"/></label>
											<input type="password" name="confirmPwd" id="confirmPwd" class="form-control">				
											
											<span for='confirmPwd' class='help-block' style='color:red' id="confirmPwdMessage"></span>										
										</div>
									</div>
								</div>	
						</div>		
																	
						<div class="modal-footer" style="text-align:left;">							
							<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>						
							<button class="btn clearMoreSearch" data-dismiss="modal" aria-hidden="true"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
						</div>
					</div>
				</div>
			</div>
		</form>		
		<input type="hidden" id="noManagerUuid" />
		<!--密码修改-->
		
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "name", "value": $.trim($("#name").val())});
	searchParam.push({ "name": "name_q", "value": $.trim($("#name_q").val())});
	searchParam.push({ "name": "departmentUuid", "value": $.trim($("#departmentUuid").val())});
	searchParam.push({ "name": "departmentUuid_q", "value": $.trim($("#departmentUuid_q").val())});
		
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
				                'aTargets': [0, 1]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
										  {
										  	"mDataProp":"checkbox" ,
										  	"sWidth":"3%" ,
										  	"sDefaultContent":"",
										  	"fnRender": function(obj) {
										      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
										      return sReturn;
										 		}
										  },
											{"mDataProp":"id" ,"sWidth":"10%" ,"sTitle": "<aebiz:showTitle titleId="sysmanager.m.id"/>"},
											{"mDataProp":"name" ,"sWidth":"10%" ,"sTitle": "<aebiz:showTitle titleId="sysmanager.m.name"/>"},											
											{"mDataProp":"deptName" ,"sWidth":"15%" ,"sTitle": "<aebiz:showTitle titleId="sysmanager.m.departmentUuid"/>"},
											{"mDataProp":"createTime" ,"sWidth":"15%" ,"sTitle": "<aebiz:showTitle titleId="sysmanager.m.createTime"/>"},																				
											{
												"mDataProp":"state" ,
												"sWidth":"3%" ,
												"sTitle": "<aebiz:showTitle titleId="sysmanager.m.state"/>" ,
												"fnRender": function(obj) {	
														if(obj.aData.state=="1") {return "<aebiz:showTitle titleId='basebusiness.showmessage.yes'/>" ;}else{return "<aebiz:showTitle titleId='basebusiness.showmessage.no'/>" ;}									  	
									 			}
													
											},		
										  {
										  	"mDataProp":"operate",
										  	'bSortable': false,
										  	"sWidth":"20%" ,
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										  	"sWidth":"23%" ,
										 		"fnRender": function(obj) {	
											  	var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/sysmanager/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
													sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
													sReturn += "<a href='${pageContext.servletContext.contextPath}/sysback/sysmanagerrolerel/toRelatedRole/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="sysmanager.m.releatrole"/>'><aebiz:showTitle titleId="sysmanager.m.releatrole"/></a>" ;															
													sReturn += "<button class='btn' id='searchshow' onClick='javascript:getNowManager(\""+obj.aData.uuid+"\")' title='<aebiz:showTitle titleId="sysmanager.m.pwdreset"/>' rel='tooltip' data-toggle='modal' data-target='#modal-user'><aebiz:showTitle titleId="sysmanager.m.pwdreset"/></button>" ;													
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/sysmanager/queryList" ,
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
	$("#name").val("") ;
	$("#departmentUuid").val("") ;	
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
			    	"${pageContext.servletContext.contextPath}/sysback/sysmanager/deletes",
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
	//1.获得密码和确认密码
	var pwd = $("#pwd").val() ;
	var confirmPwd = $("#confirmPwd").val() ;
	
	//2.匹配密码和确认密码是否一致
	if(pwd != confirmPwd){		
			$("#confirmPwdError	").addClass("has-error") ;
			//$("#confirmPwd").after("<span for='confirmPwd' class='help-block has-error'><aebiz:showTitle titleId='sysmanager.m.confirmpwdnoequal'/></span>") ;
			$("#confirmPwdMessage").html("<aebiz:showTitle titleId='sysmanager.m.confirmpwdnoequal'/>") ;
			return ;
	}else{
			$("#confirmPwdError	").removeClass("has-error") ;
			$("#confirmPwd").after("") ;
	}
	
	var requestUrl = "${pageContext.servletContext.contextPath}/sysback/sysmanager/pwdreset" ;
	$.getJSON(requestUrl ,{pwd:pwd ,confirmPwd:confirmPwd,uuid:$("#noManagerUuid").val() ,ranNum:Math.random()} ,function(data){
		if("success"==data.message) {
			//$("#confirmPwd").after("<span for='confirmPwd' class='help-block' style='color:red'><aebiz:showTitle titleId='sysmanager.m.resetsuccess'/></span>") ;
			
			$("#confirmPwdMessage").html("<aebiz:showTitle titleId='sysmanager.m.resetsuccess'/>") ;
		}else if("pwd_empty"==data.message) {
			$("#pwdError	").addClass("has-error") ;
			$("#pwd").after("<span for='confirmPwd' class='help-block has-error'><aebiz:showTitle titleId='sysmanager.m.confirmpwdnoequal'/></span>") ;
			$("#confirmPwdMessage").html("") ;
			return ;				
		}else if("no_equal"==data.message) {	
			$("#confirmPwdError	").addClass("has-error") ;
			//$("#confirmPwd").after("<span for='confirmPwd' class='help-block has-error'><aebiz:showTitle titleId='sysmanager.m.confirmpwdnoequal'/></span>") ;
			$("#confirmPwdMessage").html("<aebiz:showTitle titleId='sysmanager.m.confirmpwdnoequal'/>") ;
			return ;			
		}
	})
}

function getNowManager(managerUuid) {	
	$("#noManagerUuid").val(managerUuid) ;
	$("#pwd").val("") ;
	$("#confirmPwd").val("") ;
	$("#confirmPwdMessage").html("") ;
}
</script>