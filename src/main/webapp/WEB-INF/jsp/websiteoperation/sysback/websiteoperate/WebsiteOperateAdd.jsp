<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="websiteoperate.moduleName_CN"/></h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="websiteoperate.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="websiteoperate.menuTwo"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="websiteoperate.moduleName_CN"/></span></li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/websiteoperate/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="websiteoperate.m.moduleName"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<select name="moduleName" class='select2-me form-control' data-rule-required="true">								
									<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
									<c:forEach items="${moduleList}" var="dpp">	
										<option value="${dpp.name}">${dpp.value}</option>
									</c:forEach>										
								</select>
							</div>
							<div class="col-sm-4">
								<span class="help-block pl_5" id="moduleNameTip" style="color:red"></span>
							</div>	
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="websiteoperate.m.resourceKey"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<input type="hidden" name="keyHidden" value="true">
								<input type="text" name="resourceKey" class="form-control" data-rule-required="true" data-rule-maxlength="30">
							</div>
							<div class="col-sm-4">
								<span class="help-block pl_5" id="resourceKeyTip" style="color:red"></span>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="websiteoperate.m.resourceName"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<input type="text" name="resourceName" class="form-control" data-rule-required="true" data-rule-maxlength="30" >
							</div>
							<div class="col-sm-4">
								<span class="help-block pl_5" id="resourceNameTip" style="color:red"></span>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="websiteoperate.m.paramType"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<select name="paramType" class='select2-me form-control' data-rule-required="true">								
									<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
									<c:forEach items="${paramTypeList}" var="dpp">	
										<option value="${dpp.name}">${dpp.value}</option>
									</c:forEach>										
								</select>
							</div>
							<div class="col-sm-4">
								<span class="help-block pl_5" id="paramTypeTip" style="color:red"></span>
							</div>
						</div>
					</div>
					
					<div class="form-group" id="contentDiv">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="websiteoperate.m.paramContent"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<textarea rows="4" name="paramContent" class="form-control" data-rule-required="true" data-rule-maxlength="200"></textarea>
							</div>
							<div class="col-sm-4">
								<span class="help-block pl_5" id="paramContentTip" style="color:red">
									<aebiz:showTitle titleId="websiteoperate.note.paramContentTip"/>
								</span>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="websiteoperate.m.paramValue"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<input type="text" name="paramValue" class="form-control" data-rule-maxlength="40">
							</div>
							<div class="col-sm-4">
								<span class="help-block pl_5" id="paramValueTip" style="color:red"></span>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="websiteoperate.m.note"/></label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<textarea name="note" rows="3" class="form-control" data-rule-maxlength="200"></textarea>
							</div>
							<div class="col-sm-4">
								<span class="help-block pl_5" id="noteTip" style="color:red"></span>
							</div>
						</div>
					</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="button" class="btn submit btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>						
				</form>
			</div>
		</div>
	</div>
	
<script>
    $(function(){
		$("select[name='moduleName']").bind({
			focus:function(){
				$("#moduleNameTip").append("<aebiz:showTitle titleId="websiteoperate.note.moduleNameTip"/>");
			},
			blur:function(){
				$("#moduleNameTip").empty();
			}
		});
		
		$("input[name='resourceKey']").bind({
			focus:function(){
				$("#resourceKeyTip").append("<aebiz:showTitle titleId="websiteoperate.note.resourceKeyTip"/>");
			},
			blur:function(){
				$("#resourceKeyTip").empty();
			}
		});

		$("input[name='resourceName']").bind({
			focus:function(){
				$("#resourceNameTip").append("<aebiz:showTitle titleId="websiteoperate.note.resourceNameTip"/>");
			},
			blur:function(){
				$("#resourceNameTip").empty();
			}
		});
		
		$("select[name='paramType']").bind({
			focus:function(){
				$("#paramTypeTip").append("<aebiz:showTitle titleId="websiteoperate.note.paramTypeTip"/>");
			},
			blur:function(){
				$("#paramTypeTip").empty();
			},
			change:function(){
				if($(this).val()=="1"){
	    			$("#contentDiv").hide();
	    			$("[name='paramContent']").val("");
	    		}else{
	    			$("#contentDiv").show();
	    		}
			}
		});
		
		$("input[name='paramValue']").bind({
			focus:function(){
				$("#paramValueTip").append("<aebiz:showTitle titleId="websiteoperate.note.paramValueTip"/>");
			},
			blur:function(){
				$("#paramValueTip").empty();
			}
		});
		
		$("textarea[name='note']").bind({
			focus:function(){
				$("#noteTip").append("<aebiz:showTitle titleId="websiteoperate.note.noteTip"/>");
			},
			blur:function(){
				$("#noteTip").empty();
			}
		});
        
    	$(".submit").click(function(){
    		var key = $("input[name='keyHidden']").val();
    		if("true"==key){
    			$("#mainForm").submit();
    		}
    	});
    
    	$(".cancel").click(function(){
			history.go(-1) ;
		});
    });
    
    //校验编号是否重复
   	$("input[name='resourceKey']").blur(function(){
   		$("#keyError").remove();
   		var key=$(this).val();
   		var url="${pageContext.servletContext.contextPath}/sysback/websiteoperate/checkKeyExist";
   		$.get(url,{"resourceKey":key,"ranNum":Math.random()},function(data){
   			//data为true表示编号已经存在
			if(data=="true"){
				$("input[name='resourceKey']").after("<span id='keyError'><font color='red'><aebiz:showTitle titleId="websiteoperate.note.resourceKeyExist"/></font></span>");
				$("input[name='keyHidden']").val("false");
			}else{
				$("#kerError").remove();
				$("input[name='keyHidden']").val("true");
			}
   		});
   	});
</script>
	
</body>
</html>
<aebiz:showErrorMsg></aebiz:showErrorMsg>