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
					<div class="date">${innerMessage.sendTime}</div>
					<div class="user">
						<span class="y_icheckbox"><div><input type="checkbox" name="chk_receive" class='icheck-me' data-skin="square" data-color="blue"/></div><label for="select1"></label></span>
						<span class="news-from"><label><aebiz:showTitle titleId="innermessage.m.sendUser"/></label><i>${innerMessage.sendUser}</i></span>
						<span class="news-type"><strong><aebiz:showTitle titleId="ucinnermessage.messageType"/>：</strong>
						<c:choose>
							<c:when test="${innerMessage.messageType=='0'}">
								<aebiz:showTitle titleId="ucinnermessage.messageType.system"/>
							</c:when>
							<c:when test="${innerMessage.messageType=='1'}">
								<aebiz:showTitle titleId="ucinnermessage.messageType.order"/>
							</c:when>
							<c:when test="${innerMessage.messageType=='2'}">
								<aebiz:showTitle titleId="ucinnermessage.messageType.promotion"/>
							</c:when>
							<c:otherwise>
								<aebiz:showTitle titleId="ucinnermessage.messageType.personal"/>
							</c:otherwise>
						</c:choose>
						</span>
					</div>
					<div>
					  <p class="news-text mb_20">${innerMessage.showContent} <a href="${pageContext.servletContext.contextPath}/store/innermessage/toView/${innerMessage.uuid}"><aebiz:showTitle titleId="ucinnermessage.m.viewMessage"/></a></p>
					  <c:if test="${empty innerMessage.messageType}">
				 	  	<button onclick="javascript:replyMessage('${innerMessage.sendUser}')" class="btn btn-warning btn-news"><aebiz:showTitle titleId="ucinnermessage.modal.replyMessage"/></button> 
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
 	  <span class="y_icheckbox"><div><input type="checkbox" id="chkreceive_all" class='icheck-me' data-skin="square" data-color="blue"/></div><label for="chkreceive_all">全选本页</label></span>
 	  <a href="javascript:removes('')"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>
 	</div>
	</c:if> 
   <aebiz:storeReceiveMsgPage />
</div>

<script>
	function replyMessage(receiveUser){
		$("input[name='receiveUser']").val(receiveUser);
		$("input[name='receiveUser']").prop("readonly",true);
		$(".modal-header").children("h3").text("回复消息");
		
		$("input[name='title']").val("");
	  $("textarea[name='content']").val("");
		//调用回复信息的模态框
		$('.replay-news').modal();
	}	
	
	//删除
	function removes(delId) {
			var checkIds="";
			if( delId.trim() != "") {
				checkIds = delId ;
			}else{
				$("input[name='check']:checkbox").each(function(){				
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
				    	"${pageContext.servletContext.contextPath}/usercenter/innerMessage/removes",
				    	{"selectOne":checkIds,"type":"sx",ranNum:Math.random()},	
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
