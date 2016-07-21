<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
	<!--[if IE 9 ]><html class="ie ie9" lang="en" class="no-js"> <![endif]-->
	<!--[if !(IE)]><!--><html lang="en" class="no-js"> <!--<![endif]-->
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp"%>		
	
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
								<h2><aebiz:showTitle titleId="innermessage.moduleName_CN"/></h2>
							</div>

							<div class="main-content news-all-detial">
								  <h2>${m.title}</h2> 
								  <ul class="row">
								   <li class="col-mi-3 col-xs-3 col-sm-3 text-right"><aebiz:showTitle titleId="innermessage.m.sendUser"/>：<b>${m.sendUser}</b></li>
								   <li class="col-mi-4 col-xs-4 col-sm-4 text-center"><aebiz:showTitle titleId="ucinnermessageview.messageType"/>：
								   	<b>
								   	<c:choose>
											<c:when test="${m.messageType=='0'}">
												<aebiz:showTitle titleId="ucinnermessage.messageType.system"/>
											</c:when>
											<c:when test="${m.messageType=='1'}">
												<aebiz:showTitle titleId="ucinnermessage.messageType.order"/>
											</c:when>
											<c:when test="${m.messageType=='2'}">
												<aebiz:showTitle titleId="ucinnermessage.messageType.promotion"/>
											</c:when>
											<c:otherwise>
												<aebiz:showTitle titleId="ucinnermessage.messageType.personal"/>
											</c:otherwise>
										</c:choose>	
								   	</b>
								   </li>
								   <li class="col-mi-5 col-xs-5 col-sm-5 text-left"><aebiz:showTitle titleId="ucinnermessageview.sendTime"/>：<b>${m.sendTime}</b></li>
								  </ul>	 
								  <p class="newsall-text">${m.content}</p> 	   	  
								  <p class="btn-box">
								  	<button class="btn btn-warning btn-news" onclick="javascript:replyMessage('${m.sendUser}')"><aebiz:showTitle titleId="ucinnermessage.modal.replyMessage"/></button> 
								  	<a id="returnList" class="btn btn-primary" href="javascript:history.go(-1);"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></a>
								  	<%--<span class="fa-icon fr"><i class="fa fa-trash-o"></i></span>--%>
								  </p>	 
              </div>  
							</div><!-- /main-content -->
					</div><!-- /content-wrapper -->
				</div><!-- /row -->
			</div><!-- /container -->
		</div>
		<!-- END BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="push-sticky-footer"></div>
	</div><!-- /wrapper -->

	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>
	
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
							   <i class="fa fa-pencil-square-o"></i><input name="receiveUser" type="text" readOnly="readOnly"/>
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
		function replyMessage(receiveUser){
			$("input[name='receiveUser']").val(receiveUser);
			$("input[name='title']").val("");
	 		$("input[name='content']").val("");
			$('.replay-news').modal();
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
	  	 	
	  });	
	</script>
</body>
</html>
