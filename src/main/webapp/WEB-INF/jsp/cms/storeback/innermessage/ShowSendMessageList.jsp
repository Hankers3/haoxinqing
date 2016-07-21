<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="timeline">
	<c:forEach items="${wm.rows}" var="innerMessage">
		<li>
			<div class="timeline-content">
				<div class="left">
					<div class="icon red">
						<i class="fa fa-envelope"></i>
					</div>
				</div>
				<div class="activity">
					<div class="date">${innerMessage.createTime}</div>
					<div class="user">
						<span class="y_icheckbox"><div><input type="checkbox" name="chk_send" class='icheck-me' data-skin="square" data-color="blue"/></div><label for="select1"></label></span>
						<span class="news-from"><label><aebiz:showTitle titleId="innermessage.m.receiveUser"/></label><i>${innerMessage.receiveUser}</i></span>
						<span class="news-type"><strong><aebiz:showTitle titleId="ucinnermessage.m.title"/>：</strong>${innerMessage.title}</span>
					</div>
					<div>
					  <p class="news-text mb_20">${innerMessage.showContent} <a href="${pageContext.servletContext.contextPath}/store/innermessage/toView/${innerMessage.uuid}"><aebiz:showTitle titleId="ucinnermessage.m.viewMessage"/></a></p>
					  
						<c:if test="${empty innerMessage.messageType}">
			 	   		<button type="button" onclick="javascript:sendMessage('${innerMessage.receiveUser}')" class="btn btn-primary btn-replaynews"><aebiz:showTitle titleId="ucinnermessage.m.sendAgain"/></button>
			 	   	</c:if>
			 	    <c:if test="${innerMessage.messageType != '0'}">
			 	   		<span class="fa-icon" title="<aebiz:showTitle titleId="ucinnermessage.m.deleteTitle"/>" onclick="javascript:removes('${innerMessage.uuid}')"><i class="fa fa-trash-o"></i></span>
			 	    </c:if>
					</div>	
				</div>
			</div>
			<div class="line"></div>
		</li>
  </c:forEach>
</ul>	 

<div class="tab-btm mt_20">
	<c:if test="${!empty wm.rows}">								 	
	<div class="fl">
	  <span class="y_icheckbox"><div><input type="checkbox" id="chksend_all" class='icheck-me' data-skin="square" data-color="blue"/></div><label for="chksend_all">全选本页</label></span>
	  <a href="javascript:removes('')"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>
	</div>
	</c:if> 
	 <aebiz:storeReceiveMsgPage />
</div>

<script>
	function sendMessage(receiveUser){
		$("input[name='receiveUser']").val(receiveUser);
		$("input[name='receiveUser']").prop("readonly",true);
		$(".modal-header").children("h3").text("发送消息");
		
		$("input[name='title']").val("");
	  $("textarea[name='content']").val("");
		//调用发送信息的模态框
		$('.replay-news').modal();
	}	
	
	//删除
	function removes(delId) {
			var checkIds="";
			if( delId.trim() != "") {
				checkIds = delId ;
			}else{
				$("input[name='chk_send']:checkbox").each(function(){				
	        if($(this).is(":checked")){        	
	            checkIds += $(this).val()+"," ;  
	        }
	    	})	
			}
			
			if(checkIds.trim() == "") {
					//提示为空
					bootbox.alert("<aebiz:showTitle titleId="basebusiness.showmessage.chooseDeleteData"/>") ;
					return ;
			} 
			
			bootbox.confirm("<aebiz:showTitle titleId="basebusiness.showmessage.confirmDelete"/>", function(r){
	    	if(r) {
	    		//删除
					$.post(
				    	"${pageContext.servletContext.contextPath}/store/innerMessage/removes",
				    	{"selectOne":checkIds,"type":"fx",ranNum:Math.random()},	
					    function(data) {	       
					       var result = eval("("+data+")") ;	       
					       if(result.rsp) {
					  			//刷新
									//bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
									    	
					       }else{
					       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
					       }
					    }
					);		
	       }   
	    });   
	}
</script>
