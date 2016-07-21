<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
	
	<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/ListImport.jsp" %>
	
	<!-- Validation 表单验证插件-->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/validation/jquery.validate.min.js"></script>
	
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/bootbox/jquery.bootbox.js"></script>
	
	
</head>
<body>
	<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp"%>
	<div class="container-fluid" id="content">
		<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp"%>
		<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1><aebiz:showTitle titleId="innermessage.moduleName_CN"/></h1>
					</div>
					<div class="pull-right">
						<ul class="minitiles">
							<li class='grey'>
								<a href="#">
									<i class="fa fa-cogs"></i>
								</a>
							</li>
							<li class='lightgrey'>
								<a href="#">
									<i class="fa fa-globe"></i>
								</a>
							</li>
						</ul>
						<ul class="stats">
							<li class='satgreen'>
								<i class="fa fa-money"></i>
								<div class="details">
									<span class="big">$324,12</span>
									<span>Balance</span>
								</div>
							</li>
							<li class='lightred'>
								<i class="fa fa-calendar"></i>
								<div class="details">
									<span class="big">February 22, 2013</span>
									<span>Wednesday, 13:56</span>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="breadcrumbs">
					<ul>
						<li>
							<span><aebiz:showTitle titleId="storeinnermessage.menuOne"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="storeinnermessage.menuTwo"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="innermessage.moduleName_CN"/></span>							
						</li>
					</ul>
				</div>
							
				
				<div class="row">
					<div class="col-sm-12 mynews">
						<ul class="tabs tabs-inline tabs-top border1-bottom mb_20">
							<li class='active'>
								<a href="#yishouxiaoxi" data-toggle='tab'><i class="fa fa-folder"></i><aebiz:showTitle titleId="ucinnermessage.receiveMessage"/></a>
							</li>
							<li>
								<a href="#yifaxiaoxi" data-toggle='tab'><i class="fa fa-folder-o"></i><aebiz:showTitle titleId="ucinnermessage.sendMessage"/></a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="yishouxiaoxi">
								
								<div class="tab-top clearfix">
								  <form class="form-horizontal">
								   <div class="form-group col-sm-3">
										 <select id="messageType" class="form-control">
										 	<option value=""><aebiz:showTitle titleId="ucinnermessage.messageType.all"/></option>
											<option value="0"><aebiz:showTitle titleId="ucinnermessage.messageType.system"/></option>
											<option value="1"><aebiz:showTitle titleId="ucinnermessage.messageType.order"/></option>
											<option value="2"><aebiz:showTitle titleId="ucinnermessage.messageType.promotion"/></option>
										 </select>
										 
								    </div>
								    <div class="checkbox fr">
								      <span class="y_icheckbox"><div><input type="checkbox" id="checkbox1" name="accountType" class='icheck-me' data-skin="square" data-color="blue"/></div><label for="select1">只显示未读信息</label></span>
								    </div>	    
								  </form>
								</div>
								 <div id="showReceiveMessage">
								 	
								 </div>
								  
							</div>
							
							<div class="tab-pane" id="yifaxiaoxi">
					
						  <div class="tab-top clearfix">
								  <form class="form-horizontal">
								   <div class="form-group col-sm-3">
										 <select id="accountType" class="form-control">
											  <option value=""><aebiz:showTitle titleId="ucinnermessage.accountType.all"/></option>
												<option value="1"><aebiz:showTitle titleId="ucinnermessage.accountType.customer"/></option>
												<option value="2"><aebiz:showTitle titleId="ucinnermessage.accountType.store"/></option>
										 </select>
								    </div>
								  </form>
								</div>
								 <div id="showSendMessage">
								 	
								 </div>
						
							</div>
						</div>
					</div>
				</div>				
			</div>
		</div>
	</div>
</body>

<!-- 发送消息模板star -->
 <div class="modal fade replay-news"> 
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			  <h3><aebiz:showTitle titleId="ucinnermessage.modal.replyMessage"/></h3>
			</div>
			<div class="modal-body">	
				<form class="form-horizontal form-validate"  id="bb">
				  <div class="form-group">
				    <label class="control-label col-sm-2" for="shouxinren"><span class="imp-icon"></span><aebiz:showTitle titleId="innermessage.m.receiveUser"/></label>
				    <div class="col-sm-10">
				    	<div class="input-group">
				    		<span class="input-group-addon"><i class="fa fa-pencil-square-o"></i></span>
				    	  <input type="text" class="form-control" id="shouxinren" data-rule-required="true"/>
				    	  <span class="input-group-addon"><i class="fa fa-plus"></i></span>
				    	</div>
				    </div>
				  </div>

				  <div class="form-group">
				    <label class="control-label col-sm-2" for="xinxibiaoti"><span class="imp-icon"></span><aebiz:showTitle titleId="innermessage.m.title"/></label>
				    <div class="col-sm-10">
				    	<div class="input-group">
				    		<span class="input-group-addon"><i class="fa fa-pencil-square-o"></i></span>
				    	  <input type="text" class="form-control" id="xinxibiaoti" data-rule-required="true"/>
				    	</div>
				    	
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label class="control-label col-sm-2" for="xinxibiaoti"><span class="imp-icon"></span><aebiz:showTitle titleId="innermessage.m.content"/></label>
				    <div class="col-sm-10">
				    	<textarea maxlength="150" cols="110" rows="2" class="form-control" data-rule-required="true"></textarea>
						  <p class="text-right js-textarea-help"><span class="text-muted"></span></p>
				    </div>
				  </div>
				  
				 <div class="form-group">
				    <label class="control-label col-sm-2" for="xinxineirong"><span class="imp-icon"></span></label>
				    <div class="col-sm-10">
				    	<button type="submit" class="btn btn-primary"><aebiz:showTitle titleId="ucinnermessage.modal.sendMessage"/></button>
				    </div>
				  </div>
				</form>						
			
			</div>
		</div>
	</div>
</div>
<!-- 发送消息模板end -->
	
	<script type="text/javascript">
		//列表展示
		function showMessage(mtype, messageType, readStatus, accountType, nowPage, pageShow){
			$.post(
	    	"${pageContext.servletContext.contextPath}/store/innermessage/list",
	    	{"type":mtype, "messageType":messageType, "readStatus":readStatus, "accountType":accountType, nowPage:nowPage, pageShow:pageShow, ranNum:Math.random()},	
		    function(data) {	
		    	if(mtype=="sx"){
		    		$("#showReceiveMessage").html(data);	
		    	}else if(mtype=="fx"){
		    		$("#showSendMessage").html(data);	
		    	}       
		    }
			);		
		}
		
		//已收信息列表带分页
		function receiveMessagePage(mtype, nowPage, pageShow) {
			var messageType = $("#messageType").val();
			var readStatus = "";
			if($("#checkbox1").prop("checked")){
				readStatus = "0";
			}
			
			showMessage(mtype, messageType, readStatus, "", nowPage, pageShow);
		}
		
		//已发信息列表带分页 
		function sendMessagePage(mtype, nowPage, pageShow) {
			var accountType = $("#accountType").val();
			
			showMessage(mtype, "", "", accountType, nowPage, pageShow);
		}
		
		// Validation 调用表单验证插件的js
		$(function(){
		  if ($('.form-validate').length > 0) {
		      $('.form-validate').each(function() {
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
		            	  send(); 
		            	  $(".replay-news").modal('hide');//关闭弹出框
					  			}
		          });
		      });
		  }	
		});
		
		function send(){
			var receiveUser = $("input[name='receiveUser']").val();
	  		var title = $("input[name='title']").val();
	  		var content = $("textarea[name='content']").val();
	  		
	  		$.post(
		    	"${pageContext.servletContext.contextPath}/store/innermessage/send",
		    	{"receiveUser":receiveUser,"title":title,"content":content,ranNum:Math.random()},	
			    function(data) {	 
			       var result = eval("("+data+")") ;	       
			       if(result) {
						 	bootbox.alert('发送成功！') ;
			       }
			    }
				);		
		}
		
		$(document).ready(function(){
			//已收信息
			showMessage("sx", "", "", "", "1", "2");
			//已发信息
			showMessage("fx", "", "", "", "1", "2");
			
			$("#messageType").change(function(){
				receiveMessagePage('sx','1','2');
			});
			
			$("#accountType").change(function(){
				sendMessagePage('fx','1','2');
			});
	  	
	  	// textarea显示出输入文字的多少
	  	 var textMax = 150 
	  	 $('.textarea').each(function(){
	  	 	 $(this).next('.js-textarea-help').find('span').html('您还可以输入' + textMax + '字');
	  	 	 $(this).keyup(function(){
	  	 	 	 var textLength = $(this).val().length;
	  	 	 	 var textRemaining = textMax - textLength ;
	  	 	 	 $('.text-muted').html('您还可以输入' + textRemaining + '字');
	  	 	 	})
	  	 	});
	  	 	
	  	 	$("#checkbox1").click(function(){
	  	 		var messageType = $("#messageType").val();
  	 			if($(this).prop("checked")){
  	 				showMessage("sx", messageType, "0", "", "1", "2");
  	 			}else{
  	 				showMessage("sx", messageType, "", "", "1", "2");
  	 			}
	  	 	});
	  	 	
	  });	
	</script>
</body>
</html>
