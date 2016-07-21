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

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="sysmenu.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="sysmenu.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="sysmenu.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="sysmenu.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/sysmenu/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>								
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sysmenu.m.id"/></label>
							<div class="col-sm-10">
								<form:input path="id"  class="form-control" data-rule-required="true" data-rule-minlength="5" data-rule-maxlength="20" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sysmenu.m.name"/></label>
							<div class="col-sm-10">
								<form:input path="name" class='form-control' data-rule-required="true" data-rule-minlength="2" data-rule-maxlength="20"/>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sysmenu.m.toUrl"/></label>
							<div class="col-sm-10">
								<form:input path="toUrl" class='form-control'  data-rule-maxlength="100"/>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sysmenu.m.showIndex"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="showIndex" class='form-control' data-rule-number="true" data-rule-minlength="1" data-rule-maxlength="3" data-rule-required="true"/>
								</div>		
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sysmenu.m.parentMenuUuid"/></label>							
								<div class="col-sm-10" id="chooseParentMenu">
									
								<div class="col-sm-3" >
									<select name="menuUuidLevel1" id="parentMenuUuid1" class='select2-me form-control'>								
										<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
										<c:forEach items="${topMenus}" var="smm">	
											<option value="${smm.uuid}" <c:if test="${m.menuUuidLevel1==smm.uuid}"> selected </c:if> >${smm.name}</option>
										</c:forEach>											
									</select>	
								</div>	
								
								
								<c:if test="${!empty level2Menu}">										
										<div class="col-sm-3" style="margin-left:5px" id="parentDiv2">
											<select name="menuUuidLevel2" id="parentMenuUuid2" class='select2-me form-control' onChange="javascript:getSubMenus(2)">								
												<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
												<c:forEach items="${level2Menu}" var="smm">	
													<option value="${smm.uuid}" <c:if test="${m.menuUuidLevel2==smm.uuid}"> selected </c:if> >${smm.name}</option>
												</c:forEach>											
											</select>				
										</div>																		
								 </c:if>
								
								<c:if test="${!empty level3Menu}">		
									<div class="col-sm-3" style="margin-left:5px" id="parentDiv3">	
										<select name="menuUuidLevel3" id="parentMenuUuid3" class='select2-me form-control' onChange="javascript:getSubMenus(3)">								
											<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
											<c:forEach items="${level3Menu}" var="smm">	
												<option value="${smm.uuid}" <c:if test="${m.menuUuidLevel3==smm.uuid}"> selected </c:if> >${smm.name}</option>
											</c:forEach>											
										</select>		
									</div>
								</c:if>																											
								</div>							
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sysmenu.m.state"/></label>
							<div class="col-sm-10">
								<div class="y_validatainput y_clear">
									<div class="check-line col-xs-4 col-sm-1">
										<form:radiobutton path="state" class='icheck-me' data-skin="square" data-color="blue" value="1" />
										<label class="inline" for="state"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></label>
									</div>
									<div class="check-line col-xs-4 col-sm-1" >
										<form:radiobutton path="state" value="0" class='icheck-me' data-skin="square" data-color="blue"/>
										<label class="inline" for="state"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></label>
									</div>
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
	
	<script>
    $(document).ready(function() {
			$(".cancel").click(function(){
				history.go(-1) ;
			});	 
			
			$("#parentMenuUuid1").change(function(){
				getSubMenus(1) ;
			});	   	
    });
       
    //异步获得当前切换的菜单的子菜单
    function getSubMenus(showLevel) {
    	var subStr = "<div class='col-sm-3' style='margin-left:5px' id='parentDiv"+(showLevel+1)+"'><select name='menuUuidLevel"+(showLevel+1)+"' id='parentMenuUuid"+(showLevel+1)+"' class='select2-me  form-control' onChange=\"javascript:getSubMenus("+(showLevel+1)+")\">" ;
    	var topMenu = $("#parentMenuUuid"+showLevel).val() ;
    	var queryUrl = "${pageContext.servletContext.contextPath}/sysback/sysmenu/getSubMenus	" ;
    
    	//如果当前菜单为三级切换，则返回，不做处理
    	if(showLevel == 3) {
    		return ;
    	}
    	
    	//切换则清空之前的数据
    	clearSelect(showLevel) ;
    	
    	//如果当前切换为请选择，则清空子集
      if(""==topMenu) {
      	return ;      	
      }
      
     	//异步请求当前菜单的子菜单，列出供选择
    	$.post(queryUrl ,{topMenuUuid:topMenu ,ranNum:Math.random()} ,function(data){      		
    			var result = eval("("+data+")") ;    		
    			if(result.length >0) {
    				subStr = subStr +"<option value=''>--<aebiz:showTitle titleId='basebusiness.showmessage.pleasechoose'/>--</option>" ;
    				for(var i=0 ;i<result.length ;i++) {
    						var smm = result.smmList[i] ;    						
    						subStr = subStr +"<option value='"+smm.uuid+"'>"+smm.name+"</option>" ;
    				}
    				subStr = subStr + "</select></div>" ;        					      				
    				$("#chooseParentMenu").append(subStr) ;	
    			}
    	}) ;    		
    }    
    
    
    //如果是一级菜单，则清空二级三级，如果是二级菜单，清空三级
    function clearSelect(clearLevel) {     	
    	if(clearLevel == 1) {      		
      	if($("#parentMenuUuid2").val() != undefined) {
      		$("#parentDiv2").remove() ;
      	}      		      		
      }
      
      if($("#parentMenuUuid3").val() != undefined) {
      	$("#parentDiv3").remove() ;
      }
    }
	</script>	

	<aebiz:showErrorMsg></aebiz:showErrorMsg>
</body>
</html>


