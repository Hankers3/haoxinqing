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
						<h1>待审核医生管理</h1>
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
											<input type="hidden" name="sate" id="sate"  value="0,3" class="form-control" >		<!--0：待审核 -->
											<input type="hidden" name="sate_q" id="sate_q" value="IN">

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

											<!--医院 -->
											<div class="form-group">
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

											<div class="form-group">
												<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
												<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
											</div>
										</div>
								</div>
							<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
								<thead>
									<tr>
										<th>医生姓名</th>
										<th>手机号码</th>
										<th>医院名称</th>
										<th><aebiz:showTitle titleId="servicestaff.m.auditTyp"/></th>
										<th>城市名</th>
										<th><aebiz:showTitle titleId="servicestaff.m.createTime"/></th>
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
		<form id="mainForm" action="" method="get" class='form-horizontal form-bordered form-validate1'>
			<div id="modal-user1" class="modal fade y_highserch">
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
								</div>
								<div class="row">
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
							<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'>
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
	searchParam.push({ "name": "sate", "value":$.trim($("#sate").val())});
	searchParam.push({ "name": "sate_q", "value": $.trim($("#sate_q").val())});
	// 注册状态 0：未成功，1：成功
	searchParam.push({ "name": "regState", "value":"1"});
	searchParam.push({ "name": "regState_q", "value": "EQ"});

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
						'aTargets': [0, 2,3,4,6]
					}],
					"aaSorting": [[ 5, "desc" ]],
					"bSort": true, //排序功能
						"aoColumns": [

									{"mDataProp":"realName" ,"sWidth":"5%","sDefaultContent":"","sTitle": "医生姓名"},
									{"mDataProp":"mobile" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "手机号码"},
									{"mDataProp":"hospitalName" ,"sWidth":"8%", "sDefaultContent":"","sTitle": "医院名称"},
									{"mDataProp":"address" ,"sWidth":"5%", "sDefaultContent":"","sTitle": "城市名"},
									{
										"mDataProp":"sate",
										"sDefaultContent":"",
										"sWidth":"8%",
										"sTitle": "<aebiz:showTitle titleId="servicestaff.m.auditTyp"/>" ,
											"fnRender": function(obj) {
												var sReturn ="";
												if(obj.aData.sate==1){
													sReturn+="<aebiz:showTitle titleId="doctorinfo.m.auditsuccess"/>";
												}else if(obj.aData.sate==0){
													sReturn+="<aebiz:showTitle titleId="doctorinfo.m.auditwait"/>";
												}else if(obj.aData.sate==2){
													sReturn+="<aebiz:showTitle titleId="doctorinfo.m.auditfailure"/>";
												}else if(obj.aData.sate==3){
													sReturn+="<aebiz:showTitle titleId="doctorinfo.m.auditargumentation"/>";
												}else{
													sReturn+="";
												}
											  return sReturn;
										 }
									},


									{"mDataProp":"createTime" ,"sWidth":"10%", "sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.createTime"/>"},
								  {
									"mDataProp":"operate",
									"sWidth":"8%",
									"sDefaultContent":"",
									"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										"fnRender": function(obj) {

										   var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toView/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="doctorinfo.m.audit"/>'><aebiz:showTitle titleId="doctorinfo.m.audit"/></a>" ;
									   sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;

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
	$('#frozenSate option:eq("")').attr('selected','selected');
	$('#hospital option:eq("")').attr('selected','selected');
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
</script>