<%@tag pageEncoding="UTF-8" description="显示错误信息" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>

<script type="text/javascript">
$().ready(function() {
	<c:forEach items="${ShowMsgs.keySet() }" var="msgKey">		
		<c:if test="${msgKey=='PageErrorMsg'}">
			$("<span><font color='red'>${ShowMsgs[msgKey]}</font></span>").appendTo("td[name='PageErrorMsgTD']");
		</c:if>
		<c:if test="${msgKey!='PageErrorMsg'}">
			$("<span><font color='red'>${ShowMsgs[msgKey]}</font></span>").insertAfter("*[name='${msgKey}']");
		</c:if>
	</c:forEach>
});
</script>
