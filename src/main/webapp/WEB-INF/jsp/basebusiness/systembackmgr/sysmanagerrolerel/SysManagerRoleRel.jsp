<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>

<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/multiselect/multi-select.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/multiselect/jquery.multi-select.js"></script>  

</head>

<body>	
	<div class="container-fluid" >
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="sysmanagerrolerel.m.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="sysmenu.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="sysmanager.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="sysmanagerrolerel.m.choooserole"/></span>
				</li>
			</ul>
		</div>
		
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">	
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/sysmanagerrolerel/saveRelatedRole/${m.uuid}" method="get" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">
							<aebiz:showTitle titleId="sysmanagerrolerel.m.choooserole"/>								
						</label>
							<div class="col-sm-10">
								<div class="container-fluid">
									<div style="float:left;margin-right:20px;">
										
										<select multiple="multiple" id="my-select" name="roleUuids" class='multiselect' data-selectionheader="<aebiz:showTitle titleId="sysmanagerrolerel.m.choooseed"/>" data-selectableheader="<aebiz:showTitle titleId="sysmanagerrolerel.m.unchoooseed"/>">	
											<c:forEach items="${allRoles}" var="srm">	
													<option value='${srm.uuid}' <c:if test="${srm.checked=='1'}"> selected </c:if> >${srm.name}</option>
											</c:forEach>											
										</select>
										
									</div>
								</div>
							</div>
					</div>	
					
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
					</div>					
				</form>		
						
			</div>
		</div>
	</div>

	
	
	<script>	
		 $(document).ready(function() {
			$(".cancel").click(function(){
				//history.go(-1) ;
				window.location="${pageContext.servletContext.contextPath}/sysback/sysmanager/toList" ;
			});

    	
	    if ($('.multiselect').length > 0) {
	      $(".multiselect").each(function() {
	        var $el = $(this);
	        var selectableHeader = $el.attr('data-selectableheader'),
	          	selectionHeader = $el.attr('data-selectionheader');
	        
	        if (selectableHeader != undefined) {
	          selectableHeader = "<div class='multi-custom-header'>" + selectableHeader + "</div>"; //设置左侧选择框标题的结构和内容
	        }
	        if (selectionHeader != undefined) {
	          selectionHeader = "<div class='multi-custom-header'>" + selectionHeader + "</div>"; //设置右侧选择框标题的结构和内容
	        }
	        
	        $el.multiSelect({  //带参数的调用插件
	          selectionHeader: selectionHeader,
	          selectableHeader: selectableHeader
	        });
	      });
	    }	
    
     });
	</script>
    
</body>
</html>


