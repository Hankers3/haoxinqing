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
									
				<div class="row">
						<div class="col-sm-12">
					<div class="box">
								<div class="box-content nopadding y_tableser">
									<div class="y_clear">
										<!--关闭按钮 -->
											  <div class="">
       								 <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      									</div>	
										<div class="form-inline table_formnew" style="width: 100%;">
											<!--名医状态 -->
											<input type="hidden" name="doctorType" id="doctorType12"  value="2" class="form-control" >
												<!--审核的状态 -->
											<input type="hidden" name="status" id="status"  value="1" class="form-control" >	
											<!--医生真实姓名 -->
											<div  class="form-group">
												<label class="control-label">医生姓名：</label>
												<input type="text" name="realName" id="realName" class="form-control">
												<input type="hidden" name="realName_q" id="realName_q" class="form-control" value="Like">
							     		</div>

											<!--医院 -->
											<div class="form-group form-group-nopadding">
												<label class="control-label" >&nbsp; 地区</label>	
												<aebiz:seachArea checkProvince="${m.servicestaffinfoModel.province}" checkCity="${m.servicestaffinfoModel.city}"
												 checkRegion="${m.servicestaffinfoModel.region}" hospitalId="#hospital" checkHospital="m.servicestaffinfoModel.hospital"></aebiz:seachArea>
											</div>
											<div  class="form-group">
												<label class="control-label">医院名称：</label>
												<select name="hospital" id="hospital" class="form-control">
													<option value="">--请选择--</option>
												</select>
												<input type="hidden" name="hospital_q" id="hospital_q" class="form-control" value="Like">
											</div>
											<div class="h_label_box">
												<!--标签 -->
												<div  class="form-group table_formnew_label">
													<label class="control-label fl">标签：</label>
													<c:forEach items="${list}" var="list"> 
	                        	<div class="mr10 fl">
															<input type="checkbox" name="doctorTag" class='icheck-me' data-skin="square" data-color="blue" id="doctorTag" value="${list.uuid}">
															<label for="y1" class="fl">${list.tagName}</label>
														</div>
													</c:forEach>
												</div>
												
												<div class="more">
													<span class="down">更多<i class="fa fa-angle-down"></i></span>
													<span class="up" style="display:none;">收起<i class="fa fa-angle-up"></i></span>	
												</div>
											</div>
										</div>
										
										<div class="y_tablebtn clear_both">
											<button class="btn" onclick="javascript:removes('');" title="批量管理" rel="tooltip">批量管理</button>
											<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>											
											<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										</div>
											
								</div>
							<table class="table table-bordered table-hover table-nomargin table-striped dataTable1 dataTable-reorder">
								<thead>		
									<tr>
									   <th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									  </th>
										<th>医生Id</th>
										<th>医生姓名</th>
										<th>手机号码</th>
										<th>医院</th>
										<th>科室</th>
										<th>科室电话</th>
										<th>注册时间</th>
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
	searchParam.push({ "name": "realName_q", "value": $.trim($("#realName_q").val())});

	searchParam.push({ "name": "doctorType", "value":$.trim($("#doctorType12").val())});
	searchParam.push({ "name": "hospital", "value":$.trim($("#hospital").val())});
  	searchParam.push({ "name": "hospital_q", "value":$.trim($("#hospital_q").val())});
 	searchParam.push({ "name": "province", "value":$.trim($("#provinceId").val())});
  	searchParam.push({ "name": "city", "value": $.trim($("#cityId").val())});
  	searchParam.push({ "name": "status", "value":$.trim($("#status").val())});
  	var checkTags="";
  
  	$("input[name='doctorTag']:checkbox").each(function(){				
        if($(this).is(":checked")){        	
            checkTags += $(this).val()+"," ;  
        }
    	})	
	searchParam.push({ "name": "checkTags", "value":checkTags});

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
					         	 {
										  	"mDataProp":"checkbox" ,
										  	"sDefaultContent":"",
										  	"sWidth":"1%",
										  	"fnRender": function(obj) {
										      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
										      return sReturn;
										 		}
										  },
										  {"mDataProp":"doctorNo" ,"sWidth":"5%", "sDefaultContent":"","sTitle": "医生ID"},
											{"mDataProp":"realName" ,"sWidth":"5%","sDefaultContent":"","sTitle": "医生姓名"},
											{"mDataProp":"mobile" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "手机号码"},
											{"mDataProp":"hospitalName" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "医院"},
											{"mDataProp":"departmentName" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "科室"},
											{"mDataProp":"departmentLine" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "科室电话"},
											{"mDataProp":"createTime" ,"sWidth":"10%", "sDefaultContent":"","sTitle": "注册时间"},
										  {
										  	"mDataProp":"operate",
										  	"sWidth":"8%",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  	 var	sReturn ="<a href='${pageContext.servletContext.contextPath}/sysback/servicestaff/toUpdateFamousServicestaff/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='操作'>选择</a>" ;
											      return sReturn;
											 }			
										  },
										  
										],
									
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable1/swf/copy_csv_xls_pdf.swf"
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
	$('#frozenSate option:eq("")').attr('selected','selected');
	$('#hospital option:eq("")').attr('selected','selected');
}

//修改名医风采状态
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
		//修改名医风采状态确认按钮
		bootbox.confirm("确认添加吗？", function(r){
        	if(r) {
        		//删除
				$.post(
			    	"${pageContext.servletContext.contextPath}/sysback/servicestaff/updates",
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

	//更多条件的选择效果
	$('.h_label_box .down').click( function(){
		$(this).hide();
		$(this).next('.up').show();
		$(this).next('.up').addClass('active');
		$(this).parent('.more').prev('.form-group').show();
	})

	$('.h_label_box .up').click( function(){
		$(this).hide();
		$(this).prev('.down').show();
		$(this).parent('.more').prev('.form-group').hide();
	})

</script>