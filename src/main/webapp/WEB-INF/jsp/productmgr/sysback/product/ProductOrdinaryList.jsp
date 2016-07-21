<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					药品管理
				</h1>
				   <c:if test="${erroMessage != null}">
             <c:out value="${erroMessage}"/>
             </c:if>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span>基础数据</i></li>
				<li><span>药品管理</span><i class="fa fa-angle-right"></i></li>
				<li><span>药品管理</span></li>
			</ul>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">

								<div class="form-group">
									<label class="control-label">药品名称(通用名称)：</label>
									<input type="text" name="productName" id="productName" class="form-control">
									
								</div>
								
								<div class="form-group">
									<label class="control-label">药品名称(英文名称)：</label>
									<input type="text" name="productEnglishName" id="productEnglishName" class="form-control">
								
								</div>
								
								<div class="form-group">
									<label class="control-label">药品类别：</label>
									<input type="text" name="productTypeq" id="productTypeq" class="form-control">
								
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

						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									
									<th>药品类别</th>
									<th>药品名称(通用名称)</th>
									<th>药品名称(英文名称)</th>
									<th>常用治疗适应状</th>
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

</body>

</html>

<script>
	//下架功能
	function undercarriadge(delId){
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
			bootbox.alert("<aebiz:showTitle titleId="productList.noSelect.Undercarriadge"/>");
			return;
		}
		bootbox.confirm("<aebiz:showTitle titleId="productList.Undercarriadge.ok"/>",
		function(r) {
			if (r) {
				//删除
				$.getJSON(
					"${pageContext.servletContext.contextPath}/sysback/product/updateUnStateShelves",
					{
						"selectOne" : checkIds,
						ranNum : Math.random()
					},
					function(data) {
						if(data.product_error!=null){
							bootbox.alert(data.product_error);
							return;
						}
						
						if (data.success) {
							//刷新
							bootbox.alert('<aebiz:showTitle titleId="productList.Undercarriadge.success"/>') ;
							doSearch('search');
						} else {
							bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
						}
					});
			}
		});			
	}
	
	function retrieveData(sSource, aoData, fnCallback) {
		
		var searchParam = new Array();
		searchParam.push({"name" : "productName","value" : $.trim($("#productName").val())});
	  
	  searchParam.push({"name" : "productTypeq","value" : $.trim($("#productTypeq").val())});
	  
	  searchParam.push({"name" : "productEnglishName","value" : $.trim($("#productEnglishName").val())}); 
	
		

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
				$('.dataTable').each(
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
													"mDataProp" : "productMain.productType",
													"sWidth":"5%",
													"sDefaultContent" : "",
													"sTitle" : "药品类别"
												},
												{
													"mDataProp" : "productMain.productName",
													"sWidth":"10%",
													"sDefaultContent" : "",
													"sTitle" : "药品名称(通用名称)"
												},
												{
													"mDataProp" : "productMain.productEnglishName",
													"sWidth":"10%",
													"sDefaultContent" : "",
													"sTitle" : "药品名称(英文名称）"
												},
										
												{
													"mDataProp" : "productMain.commonremedy",
													"sWidth":"10%",
													"sDefaultContent" : "",
													"sTitle" : "常用治疗适应症"
												},

												{
													"mDataProp" : "operate",
													"sWidth":"5%",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn ="";
														sReturn += "<a href='${pageContext.servletContext.contextPath}/sysback/product/viewByUuid/"
																+ obj.aData.productMain.uuid
																+ "' class='btn' rel='tooltip' title='查看'>查看</a>";
														
															sReturn +="<a href='javascript:removes(\""+obj.aData.productMain.uuid+"\");' class='btn' rel='tooltip' title='删除'>删除</a>";
														return sReturn;
													}
												} ],

										//'oColVis': {
										// "buttonText": "Change columns <i class='icon-angle-down'></i>"
										//},
										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/storeback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //指定从服务器端获取数据
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/product/queryList",
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
		$("#productName").val("");
		$("#productEnglishName").val("");
		$("#productTypeq").val("");
		$("#productNo").val("");
		$("#shopPrice").val("");
		$("#shopPrice1").val("");

	}

	//清空高级搜索的所有选项
	function clearMoreSearch() {
		$("#productName_s").val("");
		$("#productNo_s").val("");
		$("#shopPrice_s").val("");
		$("#shopPrice1_s").val("");
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
	
	
	//批量更新位置
	function bathUpdatePosion(delId) {
		var checkIds = "";
		$("input[name='check']:checkbox").each(function() {
			if ($(this).is(":checked")) {
				var positon =  "position_"+$(this).val() ;
				var postion_value = $("#"+positon).val();
				checkIds += $(this).val() +"-"+postion_value +",";
			}
		})
		if (checkIds.trim() == "") {
			//提示为空
			bootbox.alert('<aebiz:showTitle titleId="productMain.update.chooseproduct"/>');
			return;
		}

		bootbox.confirm("<aebiz:showTitle titleId="productMain.confirm.updateproduct"/>",function(r) {
							if (r) {
								//删除
								$.post(
										"${pageContext.servletContext.contextPath}/sysback/product/updatePosition",
										{
											"selectOne" : checkIds,
											ranNum : Math.random()
										},
										function(data) {
											var result = eval("("+ data + ")");
											if (result.rsp) {
												//刷新
												//bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
												doSearch('search');
											} else {
												bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
											}
										});
								}
						});
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
			    	"${pageContext.servletContext.contextPath}/sysback/product/deletes",
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
</script>