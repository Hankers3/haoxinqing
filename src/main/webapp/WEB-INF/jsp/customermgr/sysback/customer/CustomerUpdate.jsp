<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.edit"/><aebiz:showTitle titleId="customer.moduleName_CN"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customer.menuOne"/></span><i class="fa fa-angle-"></i></li>
				<li><span><aebiz:showTitle titleId="customer.menuTwo"/></span><i class="fa fa-angle-"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.edit"/><aebiz:showTitle titleId="customer.moduleName_CN"/></span></li>
			</ul>
		</div>
		<div class="tab-content y_tabdatable padding tab-content-inline tab-content-bottom"> 	
			<div class="tab-pane active " id="baseInfo">
				<div class="table table-bordered table-hover table-nomargin">
					<form:form id="baseInfoForm" action="${pageContext.servletContext.contextPath}/sysback/customercomb/updateBaseInfo" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">							
						<form:hidden path="customerInfoModel.uuid"/>
						<form:hidden path="customerInfoModel.zipCode"/>
						<form:hidden path="customerInfoModel.certCode"/>
						<form:hidden path="customerInfoModel.certType"/>
						<form:hidden path="customerInfoModel.image"/>
						<form:hidden path="customerInfoModel.age"/>
						<form:hidden path="customerInfoModel.customerUuid"/>
						<form:hidden path="customerModel.uuid"/>
						<form:hidden path="customerModel.customerId"/>
						<form:hidden path="customerModel.password"/>
						<form:hidden path="customerModel.authState"/>
						<form:hidden path="customerModel.lastWrongLoginTime"/>
						<form:hidden path="customerModel.activeCode"/>
						<form:hidden path="customerModel.loginErrorTimes"/>
						<form:hidden path="customerModel.createTime"/>
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">姓名</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="customerInfoModel.realName" class='form-control' />
								</div>
							</div>
						</div>
							
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="customer.m.customerName"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="customerModel.customerName" class='form-control' />  
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="customer.m.mobile" /></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="customerModel.mobile" class='form-control' /> <!--readonly="true"  只读-->
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="customer.m.email"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="customerModel.email" class='form-control' />
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="customerinfo.m.image"/></label>
							<div class="col-sm-10">
								<div class="fileinput <c:choose><c:when test="${empty m.customerInfoModel.image}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 150px; height: 150px;">
										<c:if test="${!empty m.customerInfoModel.image}"><img src="${m.customerInfoModel.imgUrl}" /></c:if>
									</div>
									<div>
										<span class="btn btn-default btn-file">
											<span class="fileinput-new">Select image</span>
											<span class="fileinput-exists">Change</span>
											<input type="file" name="imgFile"/>
										</span>
										<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
									</div>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="customerinfo.m.sex"/></label>
							<div class="col-sm-10">
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="customerInfoModel.sex" id="c5" value="1" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c5"><aebiz:showTitle titleId="customer.m.male" /></label>
									</div>
								</div>
								
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="customerInfoModel.sex" id="c6" value="2" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c6"><aebiz:showTitle titleId="customer.m.female"/></label>
									</div>
								</div>
								
				
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="customerinfo.m.marryState"/></label>
							<div class="col-sm-10">
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="customerInfoModel.marryState" id="c8" value="1" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c8"><aebiz:showTitle titleId="customer.m.married" /></label>
									</div>
								</div>
								
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="customerInfoModel.marryState" id="c9" value="2" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c9"><aebiz:showTitle titleId="customer.m.unmarried" /></label>
									</div>
								</div>
								

							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="customerinfo.m.birthday"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="customerInfoModel.birthday" class='form-control datepick'/>
								</div>
							</div>
						</div>
						
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="customerinfo.m.province"/></label>	
							<aebiz:area checkProvince="${m.customerInfoModel.province}" checkCity="${m.customerInfoModel.city}" checkRegion="${m.customerInfoModel.region}"></aebiz:area>
						</div>
		
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2 "><aebiz:showTitle titleId="customerinfo.m.address"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:textarea path="customerInfoModel.address" class='form-control'/>
								</div>
							</div>
						</div>
						
						
					  <div class="form-group">
							<label for="textfield" class="control-label col-sm-2 ">紧急联系人</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="customerInfoModel.alternativeName" class='form-control'/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2 ">联系电话</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="customerInfoModel.alternativePhone" class='form-control'  data-rule-mobilezh="true"/>
								</div>
							</div>
						</div>
		
						<div class="form-actions col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
							<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
						</div>
					</form:form>
				</div>
			</div>
		
		</div>
			
		</div>	
	</div>
	<script>
    $(function(){
			if($("input[name='customerModel.frozenState']:checked").val()==1){
				$("#frozenType").show();
			}else{
				$("#frozenType").hide();
			}
			
			$("#c1").on('ifChecked', function(){
				$("#frozenType").show();
				$("textarea[name='customerFrozenLogModel.note']").val("");
			});
			
			$("#c2").on('ifChecked', function(){
				$("#frozenType").hide();
				$("textarea[name='customerFrozenLogModel.note']").val("");
			});
				
			$(".cancel").click(function(){
				history.go(-1) ;
			});		
		 });
		 
		 
		 

	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		searchParam.push({"name" : "customerUuid", "value" : $.trim($("input[name='customerModel.uuid']").val())});
		searchParam.push({"name" : "intergralType","value" : $.trim($("#intergralType").val())});
		searchParam.push({"name" : "minTime","value" : $.trim($("#minTime").val())});
		searchParam.push({"name" : "maxTime","value" : $.trim($("#maxTime").val())});
		
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
				$('.dataTable').each(function() {
									var opt = {
										"sPaginationType" : "full_numbers",
										"oLanguage" : {
											"sProcessing" : "<aebiz:showTitle titleId="basebusiness.showmessage.loading"/>",
											"sSearch" : "<span><aebiz:showTitle titleId="basebusiness.showmessage.query"/>:</span> ",
											"sInfo" : "<aebiz:showTitle titleId="basebusiness.showmessage.pageFrom"/> <span>_START_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageTo"/> <span>_END_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>��<aebiz:showTitle titleId="basebusiness.showmessage.totalCount"/>�� <span>_TOTAL_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>",
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
										"bSort" : true, //������            
										"aoColumns" : [
												{
													"mDataProp" : "customerName",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegralstat.qm.customerName"/>"
												},
												{
													"mDataProp" : "nickName",
													"sTitle" : "<aebiz:showTitle titleId="customertemp.m.nickName"/>"
												},
												{
													"mDataProp" : "intergralCount",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegrallog.m.intergralCount"/>"
												},
												{
													"mDataProp" : "nowIntegral",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegrallog.m.nowIntegral"/>"
												},
												{
													"mDataProp" : "totalIntegral",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegralstat.intergralType.total"/>"
												},
												{
													"mDataProp" : "typeName",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegrallog.m.intergralType"/>"
												},
												{
													"mDataProp" : "createTime",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegrallog.m.createTime"/>"
												},
												{
													"mDataProp" : "description",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegrallog.m.description"/>"
												}
											],

										//'oColVis': {
										// "buttonText": "Change columns <i class='icon-angle-down'></i>"
										//},
										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //ָ���ӷ������˻�ȡ���
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/vipclubintegrallog/queryList",
										"fnServerData" : retrieveData, //��ȡ��ݵĴ��?��  
									};

									if ($(this).hasClass("dataTable-reorder")) {
										opt.sDom = "R" + opt.sDom;
									}

									oTable = $(this).dataTable(opt);

								});
			}
		}

		//ˢ��Datatable�����Զ�����retrieveData  		
		if ('inittable' != operact) {
			oTable.fnDraw();
		}

	}


$(".search").click(function() {
		//��ͨ������Ҫ��ո߼�������ѡ��
		doSearch('search');
	})

	$(".moresearch").click(function() {
		//�߼�������Ҫ�����ͨ������ѡ��
		clearSearch();
		doSearch('search');
	})

	function clearSearch() {
		$("#intergralType").val("");
		$("#minTime").val("");
		$("#maxTime").val("");
	}

		 
$(document).ready(function() {

		//��ʼ�����
		doSearch('inittable');
		//��ѡ��ȫѡ
		$("#check_all").click(function(e) {
			$('input', oTable.fnGetNodes()).prop('checked', this.checked);
		});

		$(".clearMoreSearch").click(function(e) {
			clearMoreSearch();
		});

		if ($('.datepick').length > 0) {
			$('.datepick').datepicker(); //����������
		}
	})		 
		 
/****��ֵ�dataTable���end*****/	 		 

		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	</script>
</body>
</html>

<aebiz:showErrorMsg></aebiz:showErrorMsg>