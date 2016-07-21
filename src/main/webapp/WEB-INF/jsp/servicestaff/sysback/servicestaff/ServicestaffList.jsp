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
						<h1><aebiz:showTitle titleId="servicestaff.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
					</div>
				</div>
				
				<div class="breadcrumbs">
					<ul>
						<li>
							<span><aebiz:showTitle titleId="servicestaff.menuOne"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="servicestaff.menuTwo"/></span>
							<i class="fa fa-angle-right"></i>							
						</li>							
						<li>
							<span><aebiz:showTitle titleId="Servicestaffsource.menuTwo"/></span>
						</li>						
					</ul>				
				</div>		
						
									
				<div class="row">
						<div class="col-sm-12">
					<div class="box">
								<div class="box-content nopadding y_tableser">
									<div class="y_clear">
										<div class="form-inline table_formnew">
											<!--审核状态 -->
											<input type="hidden" name="sate" id="sate"  value="1" class="form-control" >	
											<input type="hidden" name="sate_q" id="sate_q" value="EQ">
											<!--医生真实姓名 -->
											<div  class="form-group">
												<label class="control-label">医生姓名：</label>
												<input type="text" name="realName" id="realName" class="form-control">
												<input type="hidden" name="realName_q" id="realName_q" class="form-control" value="Like">
							     		</div>
											<div  class="form-group">
												<label class="control-label">手机号码：</label>
												<input type="text" name="mobile" id="mobile" class="form-control">
												<input type="hidden" name="mobile_q" id="mobile_q" class="form-control" value="Like">     <!--这个值为like就是模糊查询，EQ就是查询 -->
											</div>
											<div  class="form-group">
												<label class="control-label"><aebiz:showTitle titleId="servicestaff.m.email"/>：</label>
												<input type="text" name="email" id="email" class="form-control">
												<input type="hidden" name="email_q" id="email_q" class="form-control" value="Like">
											</div>
										
											
											<div class="form-group">
												<label class="control-label" >&nbsp; 地区</label>	
												<aebiz:seachArea checkProvince="${m.servicestaffinfoModel.province}" 
												checkCity="${m.servicestaffinfoModel.city}" 
												checkRegion="${m.servicestaffinfoModel.region}"
												hospitalId="#hospital" checkHospital="m.servicestaffinfoModel.hospital"
												></aebiz:seachArea>
											</div>
											
												<!--医院 -->
											<div  class="form-group">
												<label class="control-label">医院名称：</label>
												<select name="hospital" id="hospital" class="form-control">
													<option value="">请选择</option>
													<c:forEach items="${hospitalList}" var="dpp">	
														<option value="${dpp.uuid}">${dpp.hospitalName}</option>
													</c:forEach>
												</select>
												<input type="hidden" name="hospital_q" id="hospital_q" class="form-control" value="Like">
											</div>
											
											<!--冻结状态-->
												<div  class="form-group">
												<label class="control-label"><aebiz:showTitle titleId="servicestaff.m.frozenSate"/>：</label>
												<select name="frozenSate" id="frozenSate" class="form-control">
													<option value="">请选择</option>
													<option value="1">冻结</option>
													<option value="0">未冻结</option>
												</select>
												<input type="hidden" name="frozenSate_q" id="frozenSate_q" class="form-control" value="Like">
							     		</div>
											<div class="form-group">
												<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
												<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>											
											</div>
										</div>
										<div class="y_tablebtn">
											<!--<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toAddServicestaff" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
											-->
											<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toAddserviceStaff" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
											<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
											
											<button class="btn" onclick="javascript:toSortServicestaff();" title="排序" rel="tooltip">排序</button>
											
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
 </div>
	
    <!--密码修改-->
		<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/servicestaff/toList" method="get" class='form-validate1'>						
			<div id="modal-user1" class="modal fade">
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
										</div>
									</div>
								</div>	
						</div>		
																	
						<div class="modal-footer" style="text-align:left;">							
							<input type="submit" class="btn btn-primary" value='保存'>						
							<button class="btn clearMoreSearch" data-dismiss="modal" aria-hidden="true"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
						</div>
					</div>
				</div>
			</div>
		</form>	
		<input type="hidden" id="noServiceStaffUuid" />
		<!--密码修改-->
	
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "realName", "value": $.trim($("#realName").val())});
	searchParam.push({ "name": "realName_q", "value": $.trim($("#realName_q").val())});
	searchParam.push({ "name": "mobile", "value": $.trim($("#mobile").val())});
	searchParam.push({ "name": "mobile_q", "value": $.trim($("#mobile_q").val())});
	searchParam.push({ "name": "email", "value": $.trim($("#email").val()) });
  searchParam.push({ "name": "email_q", "value": $.trim($("#email_q").val())});
	searchParam.push({ "name": "frozenSate", "value": $.trim($("#frozenSate").val()) });
  searchParam.push({ "name": "frozenSate_q", "value": $.trim($("#frozenSate_q").val())});
	searchParam.push({ "name": "sate", "value":$.trim($("#sate").val())});
  searchParam.push({ "name": "sate_q", "value": $.trim($("#sate_q").val())});
  searchParam.push({ "name": "hospital", "value":$.trim($("#hospital").val())});
  searchParam.push({ "name": "hospital_q", "value": $.trim($("#hospital_q").val())});
 	searchParam.push({ "name": "province", "value":$.trim($("#provinceId").val())});
  searchParam.push({ "name": "city", "value": $.trim($("#cityId").val())});
  
	
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
				                'aTargets': [4,8]                
				            }],
				            "aaSorting": [[ 7, "asc" ]],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
										/*   {"mDataProp":"doctorNo" ,"sWidth":"5%", "sDefaultContent":"","sTitle": "医生ID"}, */
											{"mDataProp":"realName" ,"sWidth":"5%","sDefaultContent":"","sTitle": "医生姓名"},
											{"mDataProp":"mobile" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "手机号码"},
											{"mDataProp":"hospitalName" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "医院名称"},
											{"mDataProp":"departmentName" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "科室名称"},
											{"mDataProp":"departmentLine" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.departmentLine"/>"},
											
											{
										  	"mDataProp":"frozenSate1",
										  	"sDefaultContent":"",
										  	"sWidth":"5%",
										  	"sTitle": "<aebiz:showTitle titleId="servicestaff.m.frozenTyp"/>" ,
										 		"fnRender": function(obj) {	
											  		var sReturn ="";
											  		if(obj.aData.frozenSate==0){
											  			sReturn+="未冻结";
											  		}else if(obj.aData.frozenSate==1){
											  			sReturn+="冻结";
											  		}
											      return sReturn;
											 }			
											},
											
											
											{"mDataProp":"createTime" ,"sWidth":"10%", "sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.createTime"/>"},
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
										  	"sWidth":"8%",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 		"fnRender": function(obj) {	
										 		   var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toServicestaffView/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.view"/>'><aebiz:showTitle titleId="basebusiness.showmessage.view"/></a>" ;
											  		sReturn +="<a href='${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toUpdateServicestaff/"+obj.aData.uuid+"?type=1' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
														if(obj.aData.frozenSate=="0"){
															sReturn +="<a  onclick='updateFrozen(\""+obj.aData.uuid+"\",\""+1+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="servicestaff.m.frozened1"/>'><aebiz:showTitle titleId="servicestaff.m.frozened1"/></a>" ;
														}else if(obj.aData.frozenSate=="1"){
															sReturn += "<a onclick='updateFrozen(\""+obj.aData.uuid+"\",0);'   class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.unfreeze"/>'  data-toggle='modal' data-target='#modal-freeze'><aebiz:showTitle titleId="basebusiness.showmessage.unfreeze"/></a>" ;														
														}
														sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
											      sReturn += "<button class='btn' id='searchshow' onClick='javascript:getNowServiceStaff(\""+obj.aData.uuid+"\")' title='<aebiz:showTitle titleId="servicestaff.m.pwdreset"/>' rel='tooltip' data-toggle='modal' data-target='#modal-user1'><aebiz:showTitle titleId="servicestaff.m.pwdreset"/></button>" ;
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
			 window.location.href="${pageContext.servletContext.contextPath}/sysback/servicestaff/toList";
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
		
		
		bootbox.confirm("确定要执行此操作？", function(r){
			if(r) {
				//删除
				$.get("${pageContext.servletContext.contextPath}/sysback/servicestaff/unfrozen",
			    	{"selectOne":checkIds,frozenSate:frozenSate,ranNum:Math.random()},	
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
</script>