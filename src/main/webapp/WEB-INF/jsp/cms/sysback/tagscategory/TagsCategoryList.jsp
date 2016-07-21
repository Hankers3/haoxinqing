<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/chosen/chosen.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/chosen/chosen.jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/tagsinput/jquery.tagsinput.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="tagscategory.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.manager" />
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="tagscategory.menuOne" /></span>
					<i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="tagscategory.menuTwo" /></span>
					<i class="fa fa-angle-right"></i></li>
				<li>
					<span>
						<aebiz:showTitle titleId="tagscategory.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.manager" />
					</span>
				</li>
			</ul>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">

								<div class="form-group">
									<label class="control-label"><aebiz:showTitle
											titleId="tagscategory.qmf.categoryName" />：</label> <input
										type="text" name="categoryName" id="categoryName"
										class="form-control"> <input type="hidden"
										name="categoryName_q" id="categoryName_q" class="form-control"
										value="Like">
								</div>
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="tagscategory.qmf.categoryType" />：</label> 
									<select name="categoryType" id="categoryType" class='form-control'>
										<option value=""><aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose" /></option>
										<c:forEach items="${tagsList}" var="item">
											<option value="${item.value }">${item.name }</option>
										</c:forEach>
									</select>	
									<input type="hidden" name="categoryType_q" id="categoryType_q" class="form-control" value="EQ">
								</div>


								<div class="form-group">
									<button class="btn btn-primary search"title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.query" />
									</button>
									<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.clear" />
									</button>
								</div>
							</div>

							<div class="y_tablebtn">
								<a class="btn"
									href="${pageContext.servletContext.contextPath}/tagscategory/toAdd"
									title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>"
									rel="tooltip"><aebiz:showTitle
										titleId="basebusiness.showmessage.newAdd" /></a>
								<button class="btn" onclick="javascript:removes('');"
									title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>"
									rel="tooltip">
									<aebiz:showTitle titleId="basebusiness.showmessage.delete" />
								</button>
							</div>
						</div>

						<table
							class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="tagscategory.m.categoryName" /></th>
									<th><aebiz:showTitle titleId="tagscategory.m.categoryType" /></th>
									<th><aebiz:showTitle titleId="tagscategory.m.note" /></th>
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
	</div>
	
	<!-- 添加标签 start-->
	<div class="modal fade" id="tagAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 id="user-infos">
						<aebiz:showTitle titleId="basebusiness.showmessage.newAdd" /><aebiz:showTitle titleId="tags.moduleName_CN" />
					</h4>
				</div>
				<div class="modal-body">
						<form id="mainForm" action="" method="get" class='form-horizontal form-bordered form-validate1'>
							<input type="hidden" name="categoryUuid" id="categoryUuid" />
							<div class="form-group y_borderddd" id="tagNameError">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="tags.m.tagName" /></label>
								<div class="col-sm-10" >
									<input type="text" name="tagName" id="tagName" class="tagsinput" value="" data-rule-required="true" data-rule-minlength="11" data-rule-maxlength="200">
									<div id="errorMessage"></div>
								</div>
							</div>
							<div id="successMessage" class="text-success mt_10">
									
							</div>	
		
							<div class="form-actions col-sm-offset-2">
								<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
								<button type="button" class="btn cancel" data-dismiss="modal">
									<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
								</button>
							</div>
						</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加标签 end-->
</body>


</html>

<script>
	function clearSearch() {
		$("#categoryName").val("");
	 	$("#categoryType").val("");
		//$("#categoryType").chosen() ;
		//$("#categoryType").attr('selectedIndex', 0);
		//$("#categoryType option").eq("").attr('selected', 'selected');

	}
	
	function showMessage(){
			setTimeout("$('#successMessage').fadeOut()",2000)
	}
	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();
	
		searchParam.push({
			"name" : "categoryName_q",
			"value" : $.trim($("#categoryName_q").val())
		});
		
		searchParam.push({
			"name" : "categoryType_q",
			"value" : $.trim($("#categoryType_q").val())
		});
		
		searchParam.push({
			"name" : "categoryName",
			"value" : $.trim($("#categoryName").val())
		});
		searchParam.push({
			"name" : "categoryType",
			"value" : $.trim($("#categoryType").val())
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
			if ($('.dataTable').length > 0) {
				$('.dataTable')
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
											'aTargets' : [ 0, 2,3,4 ]
										} ],
										"bSort" : true, //排序功能            
										"aoColumns" : [
												{
													"mDataProp" : "checkbox",
													"sDefaultContent" : "",
													"fnRender" : function(obj) {
														var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";
														return sReturn;
													}
												},
												{
													"mDataProp" : "categoryName",
													"sTitle" : "<aebiz:showTitle titleId="tagscategory.m.categoryName"/>"
												},
												{
													"mDataProp" : "categoryType",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="tagscategory.m.categoryType"/>",
													"fnRender" : function(obj) {
														var sReturn = obj.aData.tagType;
														return sReturn;
													}
												},
												{
													"mDataProp" : "note",
													"sTitle" : "<aebiz:showTitle titleId="tagscategory.m.note"/>"
												},

												{
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = "<a href='${pageContext.servletContext.contextPath}/tagscategory/toUpdate/"
																+ obj.aData.uuid
																+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>";
												
																sReturn += "<a href='javascript:removes(\""
																				+ obj.aData.uuid
																				+ "\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>";
												
																sReturn += "<button class='btn' data-toggle='modal' onclick='javascript:setCategoryUuid(\""+obj.aData.uuid+"\");' data-target='#tagAdd' title='<aebiz:showTitle titleId="tagscategory.showmessage.addrel"/>'><aebiz:showTitle titleId="tagscategory.showmessage.addrel"/></button>";
												
																sReturn += "<a href='${pageContext.servletContext.contextPath}/tagscategoyrel/toRelList/"
																				+ obj.aData.uuid
																				+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="tagscategory.showmessage.list"/>'><aebiz:showTitle titleId="tagscategory.showmessage.list"/></a>";		
														return sReturn

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
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/tagscategory/queryList",
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

	

	//清空高级搜索的所有选项
	function clearMoreSearch() {
		$("#categoryName_s").val("");
		$("#categoryType_s").val("");
		$("#note_s").val("");
		$("#_s").val("");

	}

	//删除
	function removes(delId) {
		var checkIds = "";
		if (delId.trim() != "") {
			checkIds = delId;
		} else {
			$("input[name='check']:checkbox").each(function() {
				if ($(this).is(":checked")) {
					checkIds += $(this).val() + ",";
				}
			})
		}

		if (checkIds.trim() == "") {
			//提示为空
			bootbox
					.alert("<aebiz:showTitle titleId="basebusiness.showmessage.chooseDeleteData"/>");
			return;
		}

		bootbox.confirm("<aebiz:showTitle titleId="basebusiness.showmessage.confirmDelete"/>",
						function(r) {
							if (r) {
								//删除
								$
										.post(
												"${pageContext.servletContext.contextPath}/tagscategory/deletes",
												{
													"selectOne" : checkIds,
													ranNum : Math.random()
												},
												function(data) {
													var result = eval("("
															+ data + ")");
													if (result.rsp) {
														//刷新
														//bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
														doSearch('search');
													} else {
														bootbox
																.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
													}
												});
							}
						});
	}

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
                	doSaveStag() ;  
				        //    form.submit(); //没有这一句表单不会提交
				        }
            });
        });
    }	
  })

	
	function doSaveStag(){
		
		var tagName = $("#tagName").val() ;
		var categoryUuid = $("#categoryUuid").val() ;
		var temp="<span for='tagName' class='help-block has-error'><aebiz:showTitle titleId="tagscategory.m.tagName.empty"/></span>";
		if(""== tagName || "add a tag"== tagName){	
				$("#tagNameError").addClass("has-error") ;
				$("#errorMessage").html(temp);
		}else{
				$("#tagNameError").removeClass("has-error") ;
				$("#errorMessage").html("");
		}
		
		//保存请求地址
		var requestUrl = "${pageContext.servletContext.contextPath}/tags/addRel" ;
		$.getJSON(requestUrl ,{categoryUuid:categoryUuid ,tagName:tagName,ranNum:Math.random()} ,function(data){
			if(data.rsp) {//添加成功
				$("#successMessage").fadeIn().html("<aebiz:showTitle titleId="tagscategory.alert.success"/>");
				showMessage();
				$("#tagName").importTags('');
			}else if("noexist"==data.message){//该标签分类不存在
				 bootbox.alert("<aebiz:showTitle titleId="tagscategory.alert.exist"/>");		
			}else{//添加失败
				 bootbox.alert("<aebiz:showTitle titleId="tagscategory.alert.fail"/>");				
			} 
		})
	 
	}
	
	function setCategoryUuid(categoryUuid){
		$("#categoryUuid").val(categoryUuid);
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
	})
	
	
	$(function() {
		 // Chosen (chosen)
	   //$("#categoryType").chosen({
	   //	placeholder_text_single:"01"	
	   //}); 
	   //tag标签
	   $("#tagName").tagsInput({   //调用插件
	        width: 'auto',  //调用元素input的宽
	        height: 'auto',  //调用元素input的高
	        onChange:changeValue
	   }); 
	    
	})
	
	function changeValue(){
		var tagName = $("#tagName").val() ;
		var categoryUuid = $("#categoryUuid").val() ;
		var temp="<span for='tagName' class='help-block has-error'><aebiz:showTitle titleId="tagscategory.m.tagName.empty"/></span>";
		if(""== tagName || "add a tag"== tagName){		
				$("#tagNameError").addClass("has-error") ;
				$("#errorMessage").html(temp);
				return ;
		}else{
				$("#tagNameError").removeClass("has-error") ;
				$("#errorMessage").html("");
		}
	}
	
	
</script>