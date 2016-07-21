<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>                        
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>       
<div class="m_integralin" >
	<p><span class="m_use_p"><i class="fa fa-minus-circle"></i>使用积分</span></p>
	<div class="m_use">
		<c:if test="${qm.useIntergral <=0}">
		<div class="m_usein" id="change_intergral">本次使用<span><input value="" id="intergralCount" name="intergralCount" class="form-control"></span>积分支付。<a href="javascript:void(0);" class="btn btn-custom use_intergral">使用</a>(您有积分<label>${customer.intergralCount}</label>，可使用积分<label>${customer.availableIntegral}</label>)</div>
		</c:if>
		<c:if test="${qm.useIntergral >0}">
		<div class="m_usein" id="used_intergral">本次已使用<span><input id="usedInId" readonly="readonly" value="${qm.useIntergral }"  class="form-control"></span>积分支付。<a href="javascript:changeintergral();" class="btn btn-custom">修改积分</a>(您有积分<label>${customer.intergralCount}</label>，可使用积分<label>${customer.availableIntegral}</label>)</div>
		</c:if>
	</div>
</div>
<script>
//使用积分
	$(".use_intergral").click(function (){
		var canUse = ${customer.availableIntegral};
		
		var nowUse = $("#intergralCount").val();
		if(nowUse >canUse){
			alert("您的积分不足");
		}else{
			$.post(
	    	"${pageContext.servletContext.contextPath}/orderfront/order/useIntergral",
	    	{"intergral":nowUse,ranNum:Math.random()},	
		    function(data) {
	    		if("success"==data){
	    			$("#usedInId").val(nowUse);
	      			$("#intergral_use").val("true");
	      			location.reload();
	    		}
		    }
		)
		}
	})
	//修改积分
	function changeintergral(){
		$.post(
	    	"${pageContext.servletContext.contextPath}/orderfront/order/useIntergral",
	    	{"intergral":-1,ranNum:Math.random()},	
		    function(data) {
	    		if("success"==data){
	    		//.location.reload();
	    		}
		    }
		)
	
	}
</script>