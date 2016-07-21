<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
	
	<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/ListImport.jsp" %>
	
	<!-- Validation 表单验证插件-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery.validate.min.js"></script>
	
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery.bootbox.js"></script>
	
	
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
				<div class="news-all-detial">
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
					  	<button class="btn btn-primary btn-replaynews" onclick="javascript:replyMessage('${m.sendUser}')"><aebiz:showTitle titleId="ucinnermessage.modal.replyMessage"/></button> 
					  	<a class="btn btn-deflaut" href="javascript:history.go(-1);"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></a>
					  	<%--<span class="fa-icon fr"><i class="fa fa-trash-o"></i></span>--%>
					  </p>	 
        </div>  		
			</div>
		</div>
	</div>
	
	<!-- 发送消息模板star -->
 <div class="modal fade replay-news"> 
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			  <h3><aebiz:showTitle titleId="ucinnermessage.modal.replyMessage"/></h3>
			</div>
			<div class="modal-body">	
				<form class="form-horizontal form-validate" id="bb">
				  <div class="form-group">
				    <label class="control-label col-sm-2" for="shouxinren"><span class="imp-icon"></span><aebiz:showTitle titleId="innermessage.m.receiveUser"/></label>
				    <div class="col-sm-10">
				    	<div class="input-group">
				    		<span class="input-group-addon"><i class="fa fa-pencil-square-o"></i></span>
				    	  <input type="text" name="receiveUser" class="form-control" id="shouxinren" data-rule-required="true"/>
				    	  <span class="input-group-addon"><i class="fa fa-plus"></i></span>
				    	</div>
				    </div>
				  </div>

				  <div class="form-group">
				    <label class="control-label col-sm-2" for="xinxibiaoti"><span class="imp-icon"></span><aebiz:showTitle titleId="innermessage.m.title"/></label>
				    <div class="col-sm-10">
				    	<div class="input-group">
				    		<span class="input-group-addon"><i class="fa fa-pencil-square-o"></i></span>
				    	  <input type="text" name="title" class="form-control" id="xinxibiaoti" data-rule-required="true"/>
				    	</div>
				    	
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label class="control-label col-sm-2" for="xinxibiaoti"><span class="imp-icon"></span><aebiz:showTitle titleId="innermessage.m.content"/></label>
				    <div class="col-sm-10">
				    	<textarea name="content" maxlength="150" cols="110" rows="2" class="form-control" data-rule-required="true"></textarea>
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
		function replyMessage(receiveUser){
			$("input[name='receiveUser']").val(receiveUser);
			$("input[name='title']").val("");
	 		$("input[name='content']").val("");
			$('.replay-news').modal();
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
