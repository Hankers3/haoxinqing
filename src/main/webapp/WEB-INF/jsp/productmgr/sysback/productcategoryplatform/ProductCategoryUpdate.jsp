<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!-- 设置浮动按钮的js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>
	<!-- Validation 表单验证插件-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
	<!-- 美化单选、复选框样式 -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
	<!-- 单选、复选框的美化js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
	<!-- 单选复选框美化样式的调用js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/jquery.dataTables.min.js"></script><!--基本的动态数据表插件-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/ColReorderWithResize.js"></script><!--动态数据表格列可拖动的插件-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/bootbox/jquery.bootbox.js"></script>
<div class="box">
	<div class="box-title"><h3><i class="fa fa-edit"></i><aebiz:showTitle titleId="productcategoryplatform.title.add" /></h3></div>
	<div class="box-content">
		<form:form class="form-horizontal form-validate1" method="POST" action="#" commandName="m" id="mainForm">
			<input type="hidden" name="uuid" id="uuid" value="${m.uuid}" />
			<input type="hidden" name="parentUuid" id="parentUuid" value="${m.parentUuid}"/>
			<input type="hidden" name="stateValue" id="stateValue" value="${m.state}" />
			<div class="form-group y_clear">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryName" />：</label>
				<div class="col-sm-8">
					<input type="text" name="categoryName" id="categoryName" class="form-control " value="${m.categoryName}"  data-rule-required="true" data-rule-maxlength="50">
					<span class="star-red"><aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" /></span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNo" />：</label>
				<div class="col-sm-8">
					<input type="text" name="categoryNo" id="categoryNo" class="form-control " value="${m.categoryNo}" data-rule-required="true" data-rule-maxlength="50">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.position" />：</label>
				<div class="col-sm-2">
					<input type="text" name="position" id="position" class="form-control text-center" value="${m.position}" data-rule-required="true" data-rule-positivenum="true" data-rule-maxlength="4">
				</div>
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.state" />：</label>
				<div class="col-sm-4">
					<div class="check-line y_mhtwo">
						<input type="radio"  name="state" class='icheck-me' data-skin="square" data-color="blue" value="0" <c:if test="${'0'==m.state }">checked</c:if>    >
						<label class='inline' for="showyes"><aebiz:showTitle titleId="basebusiness.showmessage.yes" /></label>
					</div>
					<div class="check-line y_mhtwo">
						<input type="radio"  name="state" class='icheck-me' data-skin="square" data-color="blue" value="1" <c:if test="${'1'==m.state }">checked</c:if> >
						<label class='inline' for="showno"><aebiz:showTitle titleId="basebusiness.showmessage.no" /></label>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNote" />：</label>
				<div class="col-sm-8">
					 <textarea name="categoryNote" id="categoryNote" rows="5" class="form-control" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryNote.note" />"  data-rule-required="true" data-rule-maxlength="200">${m.categoryNote}</textarea>
					 <span class="star-red"><aebiz:showTitle titleId="productcategoryplatform.categoryNote.hint" /></span>
				</div>
			</div>
			<!--<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.relBrand" />：</label>
				<div class="col-sm-8">
					<div class="input-group">
						<input type="text" name="additionalfield" id="additionalfield" class="form-control">
						<div class="input-group-btn"><a class="btn btn-primary" data-toggle="modal" data-target="#myModal1">选择</a></div>
					</div>
				</div>
			</div>-->
			<c:if test="${'1'==web.buttonRelTemplate }">
			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.relTemplate" />：</label>
				<div class="col-sm-8">
					<div class="input-group">
						<input type="text" name="templateName" id="templateName" class="form-control" disabled value="${web.templateName}">
						<div class="input-group-btn"><a class="btn btn-primary" data-toggle="modal" data-target="#myModal3" id="selectTable">选择</a></div>
					</div>
				</div>
			</div>
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryUrl" />：</label>
				<div class="col-sm-8">
					<input type="text" name="categoryUrl" id="categoryUrl" class="form-control" data-rule-url="true">
				</div>
			</div>
			<div class="y_fixedbtn" data-height="no">
				<button type="submit" class="btn btn-primary btn-large a_size_1" id="updateData"><aebiz:showTitle titleId="basebusiness.showmessage.save" /></button>
				<button type="button" class="btn  btn-large a_size_1" id="deleteData"><aebiz:showTitle titleId="basebusiness.showmessage.delete" /></button>
			</div>
		</form:form >
	</div>
</div>

<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">

		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 id="user-infos">
					<aebiz:showTitle titleId="productcategoryplatform.templateRel.title" />
				</h4>
			</div>

			<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content y_tableser">
							<div class="y_clear">
								<div class="form-inline table_formnew">

									<div class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="producttemplate.qmf.name" />：</label>
										<input type="text" name="name" id="name" class="form-control">
										<input type="hidden" name="name_q" id="name_q" class="form-control" value="EQ">
									</div>


									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip">
											<aebiz:showTitle titleId="basebusiness.showmessage.query" />
										</button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip">
											<aebiz:showTitle titleId="basebusiness.showmessage.clear" />
										</button>
									</div>
								</div>

							</div>

							<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder" id="templateTable">
								<thead>
									<tr>
										<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
										<th><aebiz:showTitle titleId="producttemplate.m.name" /></th>
										<th><aebiz:showTitle titleId="producttemplate.m.canUse" /></th>
										<th><aebiz:showTitle titleId="producttemplate.m.note" /></th>
										<th><aebiz:showTitle titleId="producttemplate.m.createTime" /></th>
										<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate" /></th>
									</tr>
								</thead>

								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<button class="btn moresearch btn-primary"  id="batchRel">
					<aebiz:showTitle titleId="productattrandvaluerel.button.batchRel" />
				</button>
				<button class="btn cancel" data-dismiss="modal" id="refreshUpdate">
					<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
				</button>
			</div>
		</div>
	</div>
</div>

<script>

	
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
                	updateCategory() ;  
				        //    form.submit(); //没有这一句表单不会提交
				        }
            });
        });
    }	
  })
	
	
	function updateCategory(){
			var dataParam = new Array();
			var uuid=$.trim($("#selectUuid").val());
			if(uuid==""){
				bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.selectCategory" />');
			}

			dataParam.push({
				"name" : "uuid",
				"value" : uuid
			});

			dataParam.push({
				"name" : "categoryName",
				"value" : $.trim($("#categoryName").val())
			});

			dataParam.push({
				"name" : "categoryNo",
				"value" : $.trim($("#categoryNo").val())
			});
			
			dataParam.push({
				"name" : "position",
				"value" : $.trim($("#position").val())
			});
			
			dataParam.push({
				"name" : "categoryNote",
				"value" : $.trim($("#categoryNote").val())
			});
			
			dataParam.push({
				"name" : "categoryUrl",
				"value" : $.trim($("#categoryUrl").val())
			});
	 
			
			dataParam.push({
				"name" : "parentUuid",
				"value" : $.trim($("#parentUuid").val())
			});
			
			var stateValue=$('input[name="state"]:checked').val()
			
			dataParam.push({
				"name" : "state",
				"value" : stateValue
			});

			$.getJSON("${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/updateCategory",
								{aoData : JSON.stringify(dataParam)},
								function(data) {
									if (data.rsp) {
										bootbox.alert("<aebiz:showTitle titleId="productcategoryplatform.alert.success" />");
										//刷新
										var tree=$("#tree").dynatree("getTree");
										if($("#parentUuid").val()!=""){
											var dtnode=tree.getNodeByKey($("#parentUuid").val());
											dtnode.appendAjax({
												type: 'GET',
												url: "${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/getNodes",
												data: {key:$("#parentUuid").val()},
												dataType: "json",
												contentType: 'application/json; charset=utf-8'
											});
										}else{
											tree.reload();
										}

										$("#editPage").load("${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/toUpdate/"+$.trim($("#uuid").val())+"?time="+Math.random());
									} else {
										bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.fail"/>');
									}
			});

	}
	
		//删除分类
		$("#deleteData").click(function(){
			var dataParam = new Array();
			var uuid=$.trim($("#selectUuid").val())
			if(uuid==""){
				bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.selectCategory"/>');
			}
			dataParam.push({
				"name" : "uuid",
				"value" : uuid
			});
			
			bootbox.confirm("<aebiz:showTitle titleId="productcategoryplatform.alert.deleteHint"/>",
						function(r) {
							if (r) {
								$.post("${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/deletes",
										{
											"selectOne" : uuid,
											ranNum : Math.random()
										},
										function(data) {
											var result = eval("("
													+ data + ")");
											if (result.rsp) {
						
												var tree=$("#tree").dynatree("getTree");
												var dtnode=tree.getNodeByKey($("#parentUuid").val());
												dtnode.appendAjax({
													type: 'GET',
													url: "${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/getNodes",
													data: {key:$("#parentUuid").val()},
													dataType: "json",
													contentType: 'application/json; charset=utf-8'
												});
												
													var node=tree.getActiveNode();
													node.deactivate();
													$("#selectUuid").val("");
						
												$("#editPage").load("${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/toSuccess");
						
											} else {
												bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
											}
										});
								
							}
						});
		});

	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();

		searchParam.push({
			"name" : "name_q",
			"value" : $.trim($("#name_q").val())
		});

		searchParam.push({
			"name" : "name",
			"value" : $.trim($("#name").val())
		});

		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : sSource,
			"data" : {
				aoData : JSON.stringify(aoData),
				searchParam : JSON.stringify(searchParam)
			},
			"success" : fnCallback
		});
	}

	var oTable = null;
	function doSearch(operact) {
		if (oTable == null) {
			if ($('#templateTable').length > 0) {
				$('#templateTable')
						.each(
								function() {
									var opt = {
										"sPaginationType" : "full_numbers",
										"oLanguage" : {
											"sProcessing" : "<aebiz:showTitle titleId="basebusiness.showmessage.loading"/>",
											"sSearch" : "<span><aebiz:showTitle titleId="basebusiness.showmessage.query"/>:</span> ",
											"sInfo" : "<aebiz:showTitle titleId="basebusiness.showmessage.pageFrom"/> <span>_START_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageTo"/> <span>_END_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>，<aebiz:showTitle titleId="basebusiness.showmessage.totalCount"/>： <span>_TOTAL_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>",
											"sLengthMenu" : "_MENU_ <span><aebiz:showTitle titleId="basebusiness.showmessage.pageShow"/></span>",
											"oPaginate" : {
												"sFirst" : "<aebiz:showTitle titleId="basebusiness.showmessage.firstPage"/>",
												"sPrevious" : "<aebiz:showTitle titleId="basebusiness.showmessage.prePage"/>",
												"sNext" : "<aebiz:showTitle titleId="basebusiness.showmessage.nextPage"/>",
												"sLast" : "<aebiz:showTitle titleId="basebusiness.showmessage.lastPage"/>"
											}
										},

										//'sDom': "lfrtip",
										'sDom' : "rtlip",
										//"sDom": '<"top"l>rt<"bottom"ip><"clear">',
										'aoColumnDefs' : [ {
											'bSortable' : false,
											'aTargets' : [ 0, 1 ]
										} ],
										"bSort" : true, //排序功能
										"aoColumns" : [
												{
													"mDataProp" : "checkbox",
													"sDefaultContent" : "",
													"fnRender" : function(obj) {
														var sReturn = "<input type='checkbox' name='valueCheck' value='"+obj.aData.uuid+"' />";
														return sReturn;
													}
												},
												{
													"mDataProp" : "name",
													"sWidth":"35%",
													"sTitle" : "<aebiz:showTitle titleId="producttemplate.m.name"/>"
												},

												{
													"mDataProp" : "canUse",
													"sDefaultContent" : "",
													"sWidth":"10%",
													"sTitle" : "<aebiz:showTitle titleId="producttemplate.m.canUse"/>",
													"fnRender" : function(obj) {
														var sReturn = "";
														if(obj.aData.canUse=="1"){
															sReturn = "是";
														}else{
															sReturn = "否";
														}
														return sReturn;
													}
												},
												{
													"mDataProp" : "note",
													"sWidth":"25%",
													"sTitle" : "<aebiz:showTitle titleId="producttemplate.m.note"/>"
												},
												{
													"mDataProp" : "createTime",
													"sWidth":"15%",
													"sTitle" : "<aebiz:showTitle titleId="producttemplate.m.createTime"/>"
												},

												{
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sWidth":"15%",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = "";
													 		if(obj.aData.isRel=="1"){
														 		sReturn += "<a href='javascript:removeRel(\""
																+ obj.aData.uuid
																+ "\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="system.button.deleteRel"/>'><aebiz:showTitle titleId="system.button.deleteRel"/></a>";
															}else{
																sReturn += "<a href='javascript:relation(\""
																+ obj.aData.uuid
																+ "\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="system.button.Rel"/>'><aebiz:showTitle titleId="system.button.Rel"/></a>";
															}
														return sReturn;
													}
												} ],

										//'oColVis': {
										// "buttonText": "Change columns <i class='icon-angle-down'></i>"
										//},
										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //指定从服务器端获取数据
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/producttemplate/noExistList/${m.uuid}",
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

	//取消关联
	function removeRel(delId) {
		var checkIds = "";
		if (delId.trim() != "") {
			checkIds = delId;
		} else {
			$("input[name='valueCheck']:checkbox").each(function() {
				if ($(this).is(":checked")) {
					checkIds += $(this).val() + ",";
				}
			})
		}

		if (checkIds.trim() == "") {
			//提示为空
			bootbox.alert("<aebiz:showTitle titleId="productcategoryplatform.templateRel.deleteNoSelect"/>");
			return;
		}

		bootbox.confirm(
						"<aebiz:showTitle titleId="productcategoryplatform.templateRel.deleteRel"/>",
						function(r) {
							if (r) {
								//删除
								$.getJSON(
												"${pageContext.servletContext.contextPath}/productcatetemprel/deleteRel",
												{
													"selectOne" : checkIds,
													"categoryUuid" : "${m.uuid}",
													ranNum : Math.random()
												},
												function(data) {
													if (data.rsp) {
														//刷新
														doSearch('search');
														$("#templateName").val(data.templateName);
													} else {
														bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.templateRel.deleteFail"/>');
													}
												});
							}
						});
	}

	$("#refreshUpdate").click(function(){
		
			
	})

	//批量关联
	$("#batchRel").click(function(){
		relation('');
	})

	//关联标签
	function relation(uuid) {
			var checkIds="";
			if( uuid.trim() != "") {
				checkIds = uuid ;
			}else{
				$("input[name='valueCheck']:checkbox").each(function(){
	        if($(this).is(":checked")){
	            checkIds += $(this).val()+"," ;
	        }
	    	})
			}

			if(checkIds.trim() == "") {
					//提示为空
					bootbox.alert("<aebiz:showTitle titleId="productcategoryplatform.templateRel.relNoSelect"/>") ;
					return ;
			}

			bootbox.confirm("<aebiz:showTitle titleId="productcategoryplatform.templateRel.relOk"/>", function(r){
	        	if(r) {
							$.getJSON(
						    	"${pageContext.servletContext.contextPath}/productcatetemprel/relation",
						    	{"selectOne":checkIds,"categoryUuid":"${m.uuid}",ranNum:Math.random()},
							    function(data) {
							       if(data.rsp) {
							  			//刷新
											doSearch('search') ;
											$("#templateName").val(data.templateName);
											bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.relSuccess"/>');
							       }else{
							       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.relFail"/>') ;
							       }
							    }
							);
	        	}
	    });
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

	$("#selectTable").click(function(){
		doSearch('search');
	})

	function clearSearch() {
		$("#name").val("");

	}

	//清空高级搜索的所有选项
	function clearMoreSearch() {
		$("#name_s").val("");
		$("input[name=canUse_s]:eq(0)").trigger("onclick");
		$("#note_s").val("");
		$("#createTime1_s").val("");
		$("#createTime2_s").val("");

	}
	//初始化表格
	$(function(){
		//复选框全选
		$("#check_all").click(function(e) {
			$('input', oTable.fnGetNodes()).prop('checked', this.checked);
		});
	})

</script>