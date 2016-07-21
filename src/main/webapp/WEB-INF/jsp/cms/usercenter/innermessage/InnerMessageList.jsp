<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
	<!--[if IE 9 ]><html class="ie ie9" lang="en" class="no-js"> <![endif]-->
	<!--[if !(IE)]><!--><html lang="en" class="no-js"> <!--<![endif]-->
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp"%>		
	
  <script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery.min.js"></script>	
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/bootstrap.js"></script>
	<!-- Validation 表单验证插件-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery.validate.min.js"></script>
	
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery.bootbox.js"></script>
	
	
</head>
<body>
	<!-- WRAPPER -->
	<div class="wrapper">
		<!-- TOP BAR 头部导航 -->
		<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterHead.jsp"%>
		<!-- /top -->
		

		<!-- BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="bottom">
			<div class="container">
				<div class="row">
					<!-- left sidebar 左侧导航 -->
					<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterLeft.jsp"%>
					<!-- end left sidebar -->

					<!-- content-wrapper 右侧主体 -->
					<div class="col-xs-10 content-wrapper">
						<div class="row">
							<div class="col-sm-4 ">
								<ul class="breadcrumb">
									<li><i class="fa fa-home"></i><a href="#"><aebiz:showTitle titleId="ucinnermessage.menuOne"/></a></li>
									<li><a href="#"><aebiz:showTitle titleId="ucinnermessage.menuTwo"/></a></li> <li class="active"><aebiz:showTitle titleId="ucinnermessage.moduleName_CN"/></li>
								</ul>
							</div>
							<div class="col-sm-8 ">
								<div class="top-content">
									<ul class="list-inline quick-access">
										<li>
											<a href="charts-statistics-interactive.html">
												<span class="quick-access-item bg-color-green">
													<i class="fa fa-bar-chart-o"></i>
													<h5>CHARTS</h5><em>basic, interactive, real-time</em>
												</span>
											</a>
										</li>
										<li>
											<a href="page-inbox.html">
												<span class="quick-access-item bg-color-blue">
													<i class="fa fa-envelope"></i>
													<h5>INBOX</h5><em>inbox with gmail style</em>
												</span>
											</a>
										</li>
										<li>
											<a href="tables-dynamic-table.html">
												<span class="quick-access-item bg-color-orange">
													<i class="fa fa-table"></i>
													<h5>DYNAMIC TABLE</h5><em>tons of features and interactivity</em>
												</span>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						
						<!-- main -->
						<div class="content">
							<div class="main-header">
								<h2><aebiz:showTitle titleId="ucinnermessage.moduleName_CN"/></h2>
							</div>
							
							<div class="main-content">
                 <div class="disct-couB news-box">
                   <ul class="nav nav-tabs">
									  <li class="active"><a data-toggle="tab" href="#yishouxinxi"><i class="fa fa-folder"></i><aebiz:showTitle titleId="ucinnermessage.receiveMessage"/></a></li>
									  <li><a data-toggle="tab" href="#yifaxinxi"><i class="fa fa-folder-o"></i><aebiz:showTitle titleId="ucinnermessage.sendMessage"/></a></li>
								   </ul>
								   
								   <div class="tab-content">
								     <div id="yishouxinxi" class="tab-pane w_tabin active">
								     	 <div class="tab-top  clearfix">  
								     	    <div class="col-sm-3 logi-nr">
								     	    	<i class="fa fa-bars"></i>
								     	    	<select class="select2" id="messageType" name="messageType">
									     	    	<option value=""><aebiz:showTitle titleId="ucinnermessage.messageType.all"/></option>
															<option value="0"><aebiz:showTitle titleId="ucinnermessage.messageType.system"/></option>
															<option value="1"><aebiz:showTitle titleId="ucinnermessage.messageType.order"/></option>
															<option value="2"><aebiz:showTitle titleId="ucinnermessage.messageType.promotion"/></option>
													 	</select>
													</div>
													<div class="simple-checkbox text-right">
														 <input type="checkbox" id="checkbox1" name="readStatus">
														 <label for="checkbox1"><aebiz:showTitle titleId="ucinnermessage.messageUnread.show"/></label><%--<span class="news-num">( 3 )</span> --%>
								     	     </div>   
								     	 </div>
                       <div id="showReceiveMessage">
                       	
                       </div>
								      
								      
								      
								     </div>
								     <div id="yifaxinxi" class="tab-pane">
								     	
								     	<div class="tab-top  clearfix">  
								     	    <div class="col-sm-3 logi-nr">
								     	    	<i class="fa fa-bars"></i>
								     	    	<select class="select2" id="accountType" name="accountType">
								     	    		<option value=""><aebiz:showTitle titleId="ucinnermessage.accountType.all"/></option>
															<option value="1"><aebiz:showTitle titleId="ucinnermessage.accountType.customer"/></option>
															<option value="2"><aebiz:showTitle titleId="ucinnermessage.accountType.store"/></option>
													 </select>
													</div> 
								     	 </div>
                       <div id="showSendMessage">
                       	
                       </div>
								       	 	
								     </div>
								    </div> 
								   </div>
                 </div><!-- /main-content -->
						</div><!-- /main -->
					</div><!-- /content-wrapper -->
				</div><!-- /row -->
			</div><!-- /container -->
		</div>
		<!-- END BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="push-sticky-footer"></div>
	</div><!-- /wrapper -->

	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
	
	<!-- Validation 表单验证插件-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery.validate.min.js"></script>
	<!-- 发送消息模态框 star-->
	<div class="modal fade replay-news"> 
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				  <h3><aebiz:showTitle titleId="ucinnermessage.modal.replyMessage"/></h3>
				</div>
				<div class="modal-body serve-widget serve-detail">							
					<div class="widget-content"> <!-- widget-content star-->	
					 <form id="messageForm" class="form-validate1" action="" method="post">	
						 <div class="row col-bothbB logi-num">
	           	<div class="col-mi-2 col-sm-2"><span class="imp-icon">*</span><aebiz:showTitle titleId="innermessage.m.receiveUser"/></div>
							 <div class="col-mi-10 col-sm-10 col-bothbL">
							   <i class="fa fa-pencil-square-o"></i><input name="receiveUser" type="text"/>
							   <i class="fa fa-plus"></i>
							 </div>
	           </div><!-- 行end -->			
	
	           <div class="row col-bothbB logi-num">
	           	<div class="col-mi-2 col-sm-2"><span class="imp-icon">*</span><aebiz:showTitle titleId="innermessage.m.title"/></div>
							 <div class="col-mi-10 col-sm-10 col-bothbL form-group">
							   <div class="y_validatainput">
								   <i class="fa fa-pencil-square-o"></i><input name="title" type="text" required>
								   
							   </div>
							 </div>
	           </div><!-- 行end -->
	           
	           <div class="row col-bothbB">
	           	<div class="col-mi-2 col-sm-2"><span class="imp-icon">*</span><aebiz:showTitle titleId="innermessage.m.content"/></div>
							 <div class="col-mi-10 col-sm-10 col-bothbL sever-problemR form-group">
							 	<textarea name="content" maxlength="150" cols="110" rows="2" class="form-control textarea" required></textarea>
								<p class="text-right js-textarea-help"><span class="text-muted"></span></p>
							 </div>
	           </div><!-- 行end -->
	
	           <div class="row sub-box">
	           	<div class="col-mi-2 col-sm-2"></div>
							 <div class="col-mi-10 col-sm-10">
							   <input type="submit" class="btn btn-primary subbut" value="<aebiz:showTitle titleId="ucinnermessage.modal.sendMessage"/>">
							 </div>
	           </div><!-- 行end -->
          </form>		
					</div><!-- widget-content end-->
				</div>
			</div>
		</div>
	</div>
  <!-- 回复消息模态框 end-->
	
	<script type="text/javascript">
		//列表展示
		function showMessage(mtype, messageType, readStatus, accountType, nowPage, pageShow){
			$.post(
	    	"${pageContext.servletContext.contextPath}/usercenter/innermessage/list",
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
				readStatus = "1";
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
		    	"${pageContext.servletContext.contextPath}/usercenter/innermessage/send",
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
