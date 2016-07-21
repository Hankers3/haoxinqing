<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>
	
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp"%>

	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>  
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/jquery.dataTables.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/ColReorderWithResize.js"></script>
	
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="questionnaire.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.edit" /></h1>
			</div>
		</div>

		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="questionnaire.menuOne" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="questionnaire.menuTwo" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="questionnaire.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.edit" /></span></li>
			</ul>
		</div>
		

		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/questionnaire/qupdate" method="get" commandName="m" class='form-horizontal form-validate form-bordered'>
					<form:hidden path="uuid"/>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="questionnaire.m.title" /></label>
						<div class="col-sm-10"><form:input path="title" class='form-control' data-rule-required="true" data-rule-maxlength="50"/></div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="questionnaire.m.state" /></label>
						<div class="col-sm-10">
							<div class="col-sm-1">
								<div class="check-line">
									<form:radiobutton path="state" id="c1" class="icheck-me" value="1" data-skin="square" data-color="blue"/>
									<label class='inline' for="c1"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></label>
								</div>
							</div>
								
							<div class="col-sm-1">
								<div class="check-line">
									<form:radiobutton path="state" id="c2" class="icheck-me" value="0" data-skin="square" data-color="blue"/>	
									<label class='inline' for="c2"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="questionnaire.m.introduce" /></label>
						<div class="col-sm-10">
							<form:textarea path="introduce" rows="5" id="introduce" class="form-control" data-rule-minlength="0" data-rule-maxlength="100"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="questionnaire.m.note" /></label>
						<div class="col-sm-10"><form:textarea path="note" rows="3" class="form-control" data-rule-minlength="0" data-rule-maxlength="100"/></div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="questionnaire.m.selectQuestions" /></label>
						<div class="col-sm-10 y_formminhei">
							<div class="mb_10">
								<a class="btn" id="searchshow" title="<aebiz:showTitle titleId="questionnaire.m.selectQuestions"/>" rel="tooltip" data-toggle="modal" data-target="#modal-select">
									<aebiz:showTitle titleId="questionnaire.m.selectQuestions" />
								</a>
							</div>
							
							<div class="col-xs-12 y_formminhei">
								<table class="table table-bordered table-hover table-nomargin table-striped">
									<thead>		
										<tr>
											<th width="15%"><aebiz:showTitle titleId="questionnaire.m.position" /></th>
											<th width="70%"><aebiz:showTitle titleId="questionnaire.m.questions" /></th>
											<th width="15%"><aebiz:showTitle titleId="questionnaire.m.deleteQuestions" /></th>
										</tr>
									</thead>
									
									<tbody id="addRows">
										<c:if test="${!empty qmList}">
											<c:forEach items="${qmList}" var="qm" varStatus="num">    
												<tr id="${qm.uuid}">	
													<input type="hidden" name="questionIds" value="${qm.uuid}">
													<td width="15%"><input name="positionValues" class='form-control' type="text" value="<c:out value='${qm.position}'/>"></td>
													<td width="70%"><c:out value="${qm.title}"/></td>
													<td width="15%">
														<span class="input-group-btn">
															<a class="btn" rel="tooltip" onclick="javascript:removeQuestion('${qm.uuid}');">
																<i class="fa fa-trash-o"></i>
															</a>
														</span>
													</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
							</div>
						</div>
					</div>

					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel" /></button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	
	<!--问卷选择题目-->
	<div id="modal-select" class="modal fade y_highserch">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 id="user-infos">
						<aebiz:showTitle titleId="questions.moduleName_CN" />
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all" ></th>
									<th><aebiz:showTitle titleId="questions.m.title" /></th>
									<th><aebiz:showTitle titleId="questions.m.state" /></th>
									<th><aebiz:showTitle titleId="questions.m.questionType" /></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				
				<div class="modal-footer">
					<button class="btn moresearch btn-primary" data-dismiss="modal" id="chooseQuestions">
						<aebiz:showTitle titleId="basebusiness.showmessage.confirm" />
					</button>
					<button class="btn" data-dismiss="modal" aria-hidden="true">
						<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<!--问卷选择题目-->
	
<script>
	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		
		searchParam.push({
			"name" : "uuid",
			"value" : $.trim($("#uuid").val())
		});
		
		var questionIds = "" ;	
		$("input[name='questionIds']").each(function() {
				questionIds += $(this).val() + ",";
		})
	
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : sSource,
			"data" : {
				aoData : JSON.stringify(aoData),
				searchParam : JSON.stringify(searchParam),
				choosedIds:questionIds
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

					'sDom' : "rtlip",
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
									var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"'/>";
									return sReturn;
								}
							},
						
							{
								"mDataProp" : "title",
								"sTitle" : "<aebiz:showTitle titleId="questions.m.title"/>"
							},
							
							{
								"mDataProp" : "state",
								"sDefaultContent" : "",
								"sTitle" : "<aebiz:showTitle titleId="questions.m.state"/>",
								"fnRender" : function(obj) {
									var sReturn = "";
									if (obj.aData.state == "1") {
										sReturn = "<aebiz:showTitle titleId="basebusiness.showmessage.yes"/>";
									} else {
										sReturn = "<aebiz:showTitle titleId="basebusiness.showmessage.no"/>";
									}
									return sReturn;
								}
							},
							{
								"mDataProp" : "questionType",
								"sDefaultContent" : "",
								"sTitle" : "<aebiz:showTitle titleId="questions.m.questionType"/>",
								"fnRender" : function(obj) {
									var sReturn = "";
									if (obj.aData.questionType == "1") {
										sReturn = "<aebiz:showTitle titleId="questionnaire.m.singleSelection"/>";
									}else if(obj.aData.questionType == "2"){
										sReturn = "<aebiz:showTitle titleId="questionnaire.m.multiSelection"/>";
									}else if(obj.aData.questionType == "3"){
										sReturn="<aebiz:showTitle titleId="questionnaire.m.essayQuestion"/>";
									}else if(obj.aData.questionType == "4"){
										sReturn="<aebiz:showTitle titleId="questionnaire.m.complexQuestion"/>";
									}else {
										sReturn="";
									}
									return sReturn;
								}
							} ],
					'oTableTools' : {
						"sSwfPath" : "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
					},
					"bRetrieve" : true,
					"bProcessing" : true,
					"bServerSide" : true, //指定从服务器端获取数据
					"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/questions/questionLists",
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
		if ('inittable' == operact) {
			oTable.fnDraw();
		}
	}
	
			//选择题目
	$("#chooseQuestions").click(function(e) {
		var checkIds = "" ;
		$("input[name='check']:checkbox").each(function() {
			if ($(this).is(":checked")) {
				checkIds += $(this).val() + ",";
			}
		})
		
		var checkUrl = "${pageContext.servletContext.contextPath}/sysback/questionnaire/chooseQuestion" ;
		$.getJSON(checkUrl,{checkIds:checkIds ,ranNum:Math.random()} ,function(result){
	    	var list = result ;
	    	
	    	for(var i=0 ;i<list.length ;i++) {
	    		var title=list[i].title;
	    		
	    		var appendStr = "<tr id='rows"+list[i].uuid+"'>" ;
	    		appendStr+="<td><input type='hidden' name='questionIds' value='"+list[i].uuid+"'><input type='text' placeholder='Enter number' class='form-control' name='positionValues' data-rule-required='true' data-rule-digits='true' data-rule-maxlength='3'></td>";		    		
	    		appendStr+="<td>"+title+"</td>";		    		
	    		appendStr+="<td><span class='input-group-btn'><a class='btn' onclick='javascript:removeQuestion(\"rows"+list[i].uuid+"\");' rel='tooltip'><i class='fa fa-trash-o'></i></a></span></td>" ;		    		
	    		appendStr += "</tr>" ;
	    				    				    		
	    		$("#addRows").append(appendStr) ;
	    	}
	  });
	});
	
	function removeQuestion(id){
		$("#"+id).remove();
	}

	$(document).ready(function() {
		$(".cancel").click(function() {
			history.go(-1);
		});
		
		//复选框全选
		$("#check_all").click(function(e) {
		$('input', oTable.fnGetNodes()).prop('checked', this.checked);
		});
		
		//选择题目
		$("#searchshow").click(function(e) {		
			doSearch('inittable');
		});
	});
</script>

</body>
</html>
<aebiz:showErrorMsg></aebiz:showErrorMsg>