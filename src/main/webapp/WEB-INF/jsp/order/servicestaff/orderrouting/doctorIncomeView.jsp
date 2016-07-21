<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script> 

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>医生收入明细</h1>
			</div>
		</div>
		<!--修改-->

		<div class="breadcrumbs">
			<ul>
				<li><span>订单管理</span><i class="fa fa-angle-right"></i></li>
				<li><span>收入管理</span><i class="fa fa-angle-right"></i></li>
				<li><span>医生收入明细</span></li>
			</ul>
		</div>	
		<div class="tab-content y_tabdatable tab-content-bottom">
		
		
		<div class="tab-pane active" id="baseInfo">
					<form:form id="baseInfoForm" action="" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">							

						<!--医生基本信息-->
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-sm-3">
									<i class="fa fa-user"></i>医生基本信息
								</div>
							</div>
						</div>
							<!--流水号-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">医生ID</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${servicestaffModel.doctorNo}
								</div>
							</div>
						</div>
						
						
						<!--真实姓名-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">医生姓名</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${servicestaffModel.realName}
								</div>
							</div>
						</div>
						
						
						
						<!--医院-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">所在医院</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${servicestaffModel.hospitalName}
								</div>
							</div>
						</div>
						
						<!--科室-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">科室</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${servicestaffModel.departmentName}
								</div>
							</div>
						</div>
						
						<!--当前余额-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">当前余额</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${servicestaffModel.accountAmount}
								</div>
							</div>
						</div>						
					
						
						<!--收入明细-->
						<div class="form-group">
							<div class="col-sm-10">
								<div class="col-sm-3">
									<i class="fa fa-lock"></i>收入明细
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-10">
								<div class="col-sm-3">
									
										<!-- 收入明细start -->
										<div class="tab-pane active" id="Product1">
											<div class="y_clear">
												<div class="form-inline table_formnew">
													<div class="form-group">
														<label class="control-label">收入明细：</label>
														<input type="text" name="incomeType" id="incomeType" class="form-control">
														<input type="hidden" name="incomeType_q" id="incomeType_q" class="form-control" value="Like">
														<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
														<button class="btn" onclick="javascript:clearSearch(); return false;" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>			
													</div>
												</div>
											</div>
											<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
												<thead>		
													<tr>
														<th>收入类型</th>
														<th>收入时间</th>
														<th>收入金额</th>
														<th>平台金额</th>
													</tr>
												</thead>
												
												<tbody>
												</tbody>
											</table>
										</div>
										<!-- 收入明细end -->
									
									
									
									
									
									
											<!--
											<table class="table table-bordered">
									      <tbody>
														<tr>
															<th>收入类型</th>
															<th>收入时间</th>
															<th>收入金额</th>
															<th>平台金额</th>
														</tr>
														<c:forEach var="o" items="${orderRoutingList}"> 
															<tr>
																<th>${o.incomeType}</th>
																<th>${o.routTime}</th>
																<th>${o.routPrice}</th>
																<th>${o.oneStaffRoutPrice-o.routPrice}</th>
															</tr>													
														</c:forEach>
																										
													</tbody>
												</table>	
												-->
									
									
									
									
									
									
									
									
									
									
									
									
									
									
								</div>
							</div>
						</div>

						


	

					</div>			 
					</form:form>
				</div>
				
			</div>
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<button type="button" class="btn cancel">返回</button>
					</div>
		</div>	
	</div>
</body>
</html>
<script>
    $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});
    });
  
  
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
				                'aTargets': [0, 1]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
											{"mDataProp":"incomeType" ,"sTitle": "收入类型"},
											{"mDataProp":"routTime" ,"sTitle": "收入时间"},
											{"mDataProp":"orderTotalAmount" ,"sTitle": "收入金额"},
											{"mDataProp":"oneStaffRoutPrice-routPrice" ,"sTitle": "平台金额",
												"fnRender": function(obj) {	
											  		var sReturn ="";
											  		if(obj.aData.oneStaffRoutPrice>obj.aData.routPrice){
											  			sReturn+= obj.aData.oneStaffRoutPrice - obj.aData.routPrice;
											  		}else {
											  			sReturn+="未知";
											  		}
											      return sReturn;
											 }
										],
										
				            //'oColVis': {
				               // "buttonText": "Change columns <i class='icon-angle-down'></i>"
				            //},
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/orderrouting/queryList" ,
										"fnServerData":retrieveData,           //获取数据的处理函数  
				        };
				        
                		if ($(this).hasClass("dataTable-reorder")) {
		                    opt.sDom = "R" + opt.sDom;
		                }
		               
		                oTable = $(this).dataTable(opt);
                
        	});
   		}         
	}
  
  $(".search").click(function(){	
	doSearch('search') ;  
	})
  
  function clearSearch() {
	$("#incomeType").val("") ;
	}
</script>
<aebiz:showErrorMsg></aebiz:showErrorMsg>