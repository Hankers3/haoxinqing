<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<!-- jQuery UI -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.core.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.widget.min.js"></script>
<!-- Filetree -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/dynatree/jquery.dynatree.js"></script>

<!-- dynatree CSS -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/dynatree/ui.dynatree.css">
<!-- Filetree -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/dynatree/jquery.dynatree.js"></script>
</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="ledgerbystore.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="ledgerbystore.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="ledgerbystore.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="ledgerbystore.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
				</li>						
			</ul>				
		</div>				
							
		<div class="row">
					<div class="col-sm-12">
						<div class="box ">
							<div class="box-content nopadding y_tableser">
		
								<div class="tab-content padding tab-content-inline tab-content-bottom y_tabdatable">
									<!--商品分类管理-->
									<div class="row y_classmage">
										<div class="col-sm-3 y_classtre">
											<div class="box">
												<div class="box-title"><h3><i class="fa fa-file"></i><aebiz:showTitle titleId="ledgerbystore.m.categorynames"/></h3></div>
												<div class="box-content">
													 <div id="tree"> </div>
												</div>
											</div>
											<input type="hidden" name="selectUuid" id="selectUuid" />
											
										</div>
										<div class="col-sm-9 y_classedit" id="editPage">
											
										</div>
									</div>
									<!--商品分类管理 end -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

<script>
$(function(){
	$("#tree").dynatree({
		title: "Lazy Tree",
		rootVisible: true,
		fx: { height: "toggle", duration: 300 },
		initAjax: {
			type: 'GET',
			url: "${pageContext.servletContext.contextPath}/sysback/ledgerbystorecategory/getReledNodes/${accountUuid}",
			data: {key:""},
			dataType: "json",
			contentType: 'application/json; charset=utf-8'
		
		},
		
		onActivate: function (dtnode) {
			$("#selectUuid").val(dtnode.data.key);
			
			//加载右侧页面
			$("#editPage").load("${pageContext.servletContext.contextPath}/sysback/ledgerbystorecategory/toUpdateLedgerRate/${accountUuid}/"+dtnode.data.key+"?time="+Math.random());
		}
		
		, onLazyRead: function (dtnode) {
			var key=dtnode.data.key;
			dtnode.appendAjax({
				type: 'GET',
				url: "${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/getNodes",
				data: {key:key},
				dataType: "json",
				contentType: 'application/json; charset=utf-8'
			});
		}
	
	});
});
</script>
			
</body>

</html>