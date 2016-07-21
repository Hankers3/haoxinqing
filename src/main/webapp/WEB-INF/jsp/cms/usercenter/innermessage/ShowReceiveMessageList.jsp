<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tab-con">
  <ul class="news-list">
  	<c:forEach items="${wm.rows}" var="innerMessage">
			<li>
			 <div class="dotto"><i class="fa fa-circle"></i></div>
			 <div class="newlist-rig">
			 	 <div class="newslist-time">${innerMessage.sendTime}</div>
			 	 <div class="newlist-tr1">
			     <div class="simple-checkbox"><input id="checkbox2" type="checkbox" name="chk_receive" value="${innerMessage.uuid}"><label for="checkbox2"></label></div>
			 	 	 <div class="fa-icon"><i class="fa fa-envelope"></i></div>
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
			 	 <div class="newlist-tr2">
			 	   <p class="news-text">${innerMessage.showContent} <a href="${pageContext.servletContext.contextPath}/usercenter/innermessage/toView/${innerMessage.uuid}"><aebiz:showTitle titleId="ucinnermessage.m.viewMessage"/></a></p>
			 	   <p>
			 	   <c:if test="${empty innerMessage.messageType}">
			 	   	<button onclick="javascript:replyMessage('${innerMessage.sendUser}')" class="btn btn-warning btn-news"><aebiz:showTitle titleId="ucinnermessage.modal.replyMessage"/></button> 
			 	   </c:if>
			 	   
			 	   	<span class="fa-icon" onclick="javascript:removes('${innerMessage.uuid}')"><i class="fa fa-trash-o"></i></span>
			 	   
			 	 	 </p>
			 	 </div>
			 </div>
			</li>
		</c:forEach>
  </ul>
</div>

<div class="page-box news-page clearfix">
 <c:if test="${!empty wm.rows}">
 <div class="page-left">
  <div class="simple-checkbox"><input type="checkbox" id="chkreceive_all"><label for="chkreceive_all"><aebiz:showTitle titleId="ucinnermessage.operate.checkall"/></label></div>
	<a class="news-del" href="javascript:removes('')"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>
 </div>
 </c:if>
</div>

<!--页码 star-->
<aebiz:receiveMessagePage />
<!--页码 end-->

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
			$("input[name='chk_receive']:checkbox").each(function(){				
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
			    	"${pageContext.servletContext.contextPath}/usercenter/innermessage/removes",
			    	{"selectOne":checkIds,"type":"sx",ranNum:Math.random()},	
				    function(data) {	       
				       var result = eval("("+data+")") ;	       
				       if(result.rsp) {
				  			//刷新
				  			showMessage("sx", "", "", "", "1", "2");
								//bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
								    	
				       }else{
				       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }
				    }
				);		
       }   
    });   
	}
	
	 //复选框全选
	 $("#chkreceive_all").click(function(e) {
	 		$("input[name='chk_receive']:checkbox").prop("checked",this.checked);
	 }); 
</script>
