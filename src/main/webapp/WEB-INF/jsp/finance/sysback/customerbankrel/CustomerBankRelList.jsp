<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<!-- Validation 表单验证插件-->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
<!-- 调用validate表单验证的js -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.js"></script>
</head>
<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="customerbankrel.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="customerbankrel.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="customerbankrel.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="customerbankrel.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
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
										<label class="control-label"><aebiz:showTitle titleId="customerbankrel.qmf.customerUuid"/>：</label>
										<input type="text" name="customerUuid" id="customerUuid" class="form-control">
										<input type="hidden" name="customerUuid_q" id="customerUuid_q" value="IN">
									</div>
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="customerbankrel.qmf.cardNo"/>：</label>
										<input type="text" name="cardNo" id="cardNo" class="form-control">
										<input type="hidden" name="cardNo_q" id="cardNo_q" class="form-control" value="EQ">
									</div>
									<div class="form-group">
										<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.cardValidateState" /></label>
										<select name="cardValidateState" id="cardValidateState" class="form-control">
											<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.all" />--</option>
											<c:forEach items="${webModel.validateStateList}" var="state">
												<option value="${state.value }">${state.name }</option>
											</c:forEach>
										</select>
										<input type="hidden" name="cardValidateState_q" id="cardValidateState_q" value="EQ">
									</div>
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
								</div>
						</div>
						<div class="table-responsive2">
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="customerbankrel.m.customerUuid"/></th>
									<th><aebiz:showTitle titleId="customerbankrel.m.cardNo"/></th>
									<th><aebiz:showTitle titleId="customerbankrel.m.openAccountBank"/></th>
									<th><aebiz:showTitle titleId="customerbankrel.m.openAccountName"/></th>
									<th><aebiz:showTitle titleId="customerbankrel.m.cardValidateState"/></th>
									<th><aebiz:showTitle titleId="customerbankrel.m.mobile"/></th>
									<th><aebiz:showTitle titleId="customerbankrel.m.vilidateMount"/></th>
									<th><aebiz:showTitle titleId="customerbankrel.m.idcardNo"/></th>
									<th><aebiz:showTitle titleId="customerbankrel.m.createTime"/></th>
									<th><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
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
	<!--高级搜索-->
	<div id="modal-user" class="modal fade y_highserch">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 id="user-infos"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.customerUuid"/></label>
								<input type="text" name="customerUuid_s" id="customerUuid_s" class="form-control">
								<input type="hidden" name="customerUuid_q" id="customerUuid_q" value="IN">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.cardNo"/></label>
								<input type="text" name="cardNo_s" id="cardNo_s" class="form-control">
								<input type="hidden" name="cardNo_q" id="cardNo_q" value="EQ">
							</div>
						</div>
						<div class="col-sm-4">
							 <div class="form-group">
								 <label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.openAccountBank"/></label>
								 <input type="text" name="openAccountBank_s" id="openAccountBank_s" class="form-control">
								 <input type="hidden" name="openAccountBank_q" id="openAccountBank_q" value="Like">
							 </div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-4">
							 <div class="form-group">
								 <label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.openAccountName"/></label>
								 <input type="text" name="openAccountName_s" id="openAccountName_s" class="form-control">
								 <input type="hidden" name="openAccountName_q" id="openAccountName_q" value="Like">
							 </div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.cardValidateState" /></label>
								<select name="cardValidateState_s" id="cardValidateState_s" class="form-control">
									<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.all" />--</option>
									<c:forEach items="${webModel.validateStateList}" var="state">
										<option value="${state.value }">${state.name }</option>
									</c:forEach>
								</select>
								<input type="hidden" name="cardValidateState_q" id="cardValidateState_q" value="EQ">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.mobile"/></label>
								<input type="text" name="mobile_s" id="mobile_s" class="form-control">
								<input type="hidden" name="mobile_q" id="mobile_q" value="EQ">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.m.idcardNo"/></label>
								<input type="text" name="idcardNo_s" id="idcardNo_s" class="form-control">
								<input type="hidden" name="idcardNo_q" id="idcardNo_q" value="EQ">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.qm.createTimeGE"/></label>
								<input type="text" name="createTime_s" id="createTime_s" class="form-control datepick">
								<input type="hidden" name="createTime_q" id="createTime_q" value="GE">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerbankrel.qm.createTime2"/></label>
								<input type="text" name="createTime2_s" id="createTime2_s" class="form-control datepick">
								<input type="hidden" name="createTime2_q" id="createTime2_q" value="LT">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn moresearch btn-primary" data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
						<button class="btn clearMoreSearch" ><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
					</div>
				</div>													
			</div>
		</div>
	</div>
	<!--高级搜索-->
	<!--编辑校验金额-->
	<div id="modal-userBank" class="modal fade y_highserch">
		<form id="validateAmount" action="" method="get" class='form-horizontal form-bordered form-validate2'>
		<input type="hidden" name="uuId" id="uuid" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 id="user-infos"><aebiz:showTitle titleId="customerbankrel.m.enterVolidateMount"/></h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.vilidateMount"/></label>
						<div class="col-sm-10">
								<div class="col-xs-4 ">
									<input type="text" name="vilidateMount" id="vilidateMount" class="form-control" data-rule-required="true" data-rule-float="true">
								</div>
								<div class="col-xs-4">
									<span class="help-block pl_5"><aebiz:showTitle titleId="customerbankrel.m.yuan"/></span>
								</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn editbuttom btn-primary" data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.save"/></button>
					</div>
				</div>													
			</div>
		</div>
		</form>
	</div>
	<!--编辑校验金额-->
</body>
</html>
<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;

	searchParam.push({ "name": "customerUuid", "value": $.trim($("#customerUuid").val())});
	searchParam.push({ "name": "customerUuid_q", "value": $.trim($("#customerUuid_q").val())});
	searchParam.push({ "name": "cardNo_s", "value": $.trim($("#cardNo_s").val())});
	searchParam.push({ "name": "cardNo_q", "value": $.trim($("#cardNo_q").val())});
	searchParam.push({ "name": "openAccountBank_s", "value": $.trim($("#openAccountBank_s").val())});
	searchParam.push({ "name": "openAccountBank_q", "value": $.trim($("#openAccountBank_q").val())});
	searchParam.push({ "name": "openAccountName_s", "value": $.trim($("#openAccountName_s").val())});
	searchParam.push({ "name": "openAccountName_q", "value": $.trim($("#openAccountName_q").val())});
	searchParam.push({ "name": "cardValidateState", "value": $.trim($("#cardValidateState").val())});
	searchParam.push({ "name": "cardValidateState_s", "value": $.trim($("#cardValidateState_s").val())});
	searchParam.push({ "name": "cardValidateState_q", "value": $.trim($("#cardValidateState_q").val())});
	searchParam.push({ "name": "mobile_s", "value": $.trim($("#mobile_s").val())});
	searchParam.push({ "name": "mobile_q", "value": $.trim($("#mobile_q").val())});
	searchParam.push({ "name": "idcardNo_s", "value": $.trim($("#idcardNo_s").val())});
	searchParam.push({ "name": "idcardNo_q", "value": $.trim($("#idcardNo_q").val())});
	searchParam.push({ "name": "createTime_s", "value": $.trim($("#createTime_s").val())});
	searchParam.push({ "name": "createTime_q", "value": $.trim($("#createTime_q").val())});
	searchParam.push({ "name": "createTime2_s", "value": $.trim($("#createTime2_s").val())});
	searchParam.push({ "name": "createTime2_q", "value": $.trim($("#createTime2_q").val())});
	searchParam.push({ "name": "customerUuid_s", "value": $.trim($("#customerUuid_s").val())});
	searchParam.push({ "name": "cardNo", "value": $.trim($("#cardNo").val())});
			
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
				                'aTargets': [0,1,2,3,4,5,6,8,10]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
										  {
										  	"mDataProp":"checkbox" ,
										  	"sDefaultContent":"",
										  	"fnRender": function(obj) {
										      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
										      return sReturn;
										 		}
										  },
											{"mDataProp":"customerNo" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.customerUuid"/>"},
											{"mDataProp":"cardNo" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.cardNo"/>"},
											{"mDataProp":"openAccountBank" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.openAccountBank"/>"},
											{"mDataProp":"openAccountName" ,"sTitle": "<aebiz:showTitle titleId="customerbankrel.m.openAccountName"/>"},
											
											{
													"mDataProp" : "cardValidateState",
													"sDefaultContent":"",
													"sTitle" : "<aebiz:showTitle titleId="customerbankrel.m.cardValidateState"/>",
												  	"fnRender": function(obj) {
													      var sReturn = "";
													      if(obj.aData.cardValidateState == "0"){
													    	  sReturn = "<aebiz:showTitle titleId="customerbankrel.m.nocheck" />";
													      }else if(obj.aData.cardValidateState == "1"){
													    	  sReturn = "<aebiz:showTitle titleId="customerbankrel.m.undercheck" />";
													      }else if(obj.aData.cardValidateState == "2"){
													    	  sReturn = "<aebiz:showTitle titleId="customerbankrel.m.checkSuccess" />";
													      }else if(obj.aData.cardValidateState == "3"){
													    	  sReturn = "<aebiz:showTitle titleId="customerbankrel.m.checkFail" />";
													      }
													      return sReturn;
													 }
												},
												{
													"mDataProp" : "mobile",
													"sTitle" : "<aebiz:showTitle titleId="customerbankrel.m.mobile"/>"
												},
												{
													"mDataProp" : "vilidateMount",
													"sTitle" : "<aebiz:showTitle titleId="customerbankrel.m.vilidateMount"/>"
												},
												{
													"mDataProp" : "idcardNo",
													"sTitle" : "<aebiz:showTitle titleId="customerbankrel.m.idcardNo"/>"
												},
												{
													"mDataProp" : "createTime",
													"sTitle" : "<aebiz:showTitle titleId="customerbankrel.m.createTime"/>"
												},
												{
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = "<a onClick='javascript:getNowUuId(\""
																+ obj.aData.uuid
																+ "\",\""
																+ obj.aData.vilidateMount
																+ "\");' class='btn' rel='tooltip' data-toggle='modal' data-target='#modal-userBank' title='<aebiz:showTitle titleId="customerbankrel.m.enterVolidateMount"/>'><aebiz:showTitle titleId="customerbankrel.m.enterVolidateMount"/></a>";
														sReturn += "<a href='${pageContext.servletContext.contextPath}/sysback/customerbankrel/toInfo/"
																+ obj.aData.uuid
																+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.view"/>'><aebiz:showTitle titleId="basebusiness.showmessage.view"/></a>";
														return sReturn;
													}
												} ],
										//'oColVis': {
										// "buttonText": "Change columns <i class='icon-angle-down'></i>"
										//},
										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //指定从服务器端获取数据
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/customerbankrel/queryList",
										"fnServerData" : retrieveData, //获取数据的处理函数  
									};

									if ($(this).hasClass("dataTable-reorder")) {
										opt.sDom = "R" + opt.sDom;
									}

									oTable = $(this).dataTable(opt);

								});
			}
		}

		//刷新Datatable，会自动激发retrieveData  		
		if ('inittable' != operact) {
			oTable.fnDraw();
		}

	}

	$(".search").click(function() {
		//普通搜索需要清空高级搜索的选项
		clearMoreSearch();
		doSearch('search');
	})

	$(".moresearch").click(function() {
		//高级搜索需要清空普通搜索的选项
		clearSearch();
		doSearch('search');
	})

	function clearSearch() {
		$("#customerUuid").val("");
		$("#cardNo").val("");
		$("#cardValidateState").val("");
	}

	//清空高级搜索的所有选项
	function clearMoreSearch() {
		$("#customerUuid_s").val("");
		$("#cardNo_s").val("");
		$("#openAccountBank_s").val("");
		$("#openAccountName_s").val("");
		$("#cardValidateState_s").val("");
		$("#mobile_s").val("");
		$("#vilidateCode_s").val("");
		$("#vilidateMount_s").val("");
		$("#idCard1_s").val("");
		$("#idcard2_s").val("");
		$("#idcardNo_s").val("");
		$("#createTime_s").val("");
		$("#createTime2_s").val("");

	}

	//获取到UUId和校验金额
	function getNowUuId(uuId, vilidateMount) {
		$("#uuid").val(uuId);
		$("#vilidateMount").val(vilidateMount);
	}

	//更新
	function editVilidateMount() {
		var uuid = $("#uuid").val();
		var vilidateMount = $("#vilidateMount").val();
		var queryUrl = "${pageContext.servletContext.contextPath}/sysback/customerbankrel/updateVilidateMount";
		$
				.get(
						queryUrl,
						{
							uuId : uuid,
							vilidateMount : vilidateMount,
							ranNum : Math.random()
						},
						function(data) {
							if (data == 'success') {
								doSearch('search');
							} else {
								bootbox
										.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
							}
						});
	}

	$(document).ready(function() {

		//初始化表格
		doSearch('inittable');

		//复选框全选
		$("#check_all").click(function(e) {
			$('input', oTable.fnGetNodes()).prop('checked', this.checked);
		});

		$(".clearMoreSearch").click(function(e) {
			clearMoreSearch();
		});
		
		//设置验证金额
		 $(function(){
			    if ($('.form-validate2').length > 0) {
			        $('.form-validate2').each(function() {
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
			                	editVilidateMount(); 
			                	$("#modal-setLedgerRate").modal('hide');//关闭模态框
							        //    form.submit(); //没有这一句表单不会提交
							        }
			            });
			        });
			    }	
			  });
	})
</script>