<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="shouzhimingxi" class="tab-pane active">
<div class="logi-nr">
	<aebiz:showTitle titleId="virtualaccountcustomercharge.m.chargeState"/>:
   <select class="select2" id="state" name="state">
		<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
		<option value="0"  <c:if test="${state==0}">selected=selected</c:if>><aebiz:showTitle titleId="virtualaccountstorecharge.m.underPay"/></option>
		<option value="1"  <c:if test="${state==1}">selected=selected</c:if>><aebiz:showTitle titleId="virtualaccountstorecharge.m.paySuccess"/></option>
		<option value="2"  <c:if test="${state==2}">selected=selected</c:if>><aebiz:showTitle titleId="virtualaccountstorecharge.m.payFail"/></option>
	</select>
	 
 </div>
  	<c:if test="${ empty list }">
	  <div class="alert alert-warning">
			<aebiz:showTitle titleId="virtualaccountcustomercharge.m.nochargeRecord"/>
		</div>
	</c:if>
	 <c:if test="${ !empty list }">
		 <table class="table table-sorting table-striped table-hover" cellpadding="0" cellspacing="0" width="100%">
		    <thead>
		      <tr>
		         <th><aebiz:showTitle titleId="virtualaccountcustomercharge.m.NO"/></th>
		         <th><aebiz:showTitle titleId="virtualaccountstorecharge.m.createTime"/></th>
		         <th><aebiz:showTitle titleId="virtualaccountstorecharge.m.chargeNo"/></th>
		         <th><aebiz:showTitle titleId="virtualaccountstorecharge.m.operAmount"/></th>
		         <th><aebiz:showTitle titleId="virtualaccountcustomercharge.m.chargeState"/></th>
		         <th><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
		    </tr>
		    </thead>
		   <tbody>
		   	<c:forEach items="${list}" var="c" varStatus="s">
		     <tr>
		       <td>${s.index+1}</td>
		       <td>${c.createTime}</td>
		       <td>${c.chargeNo}</td>
		       <td><aebiz:showTitle titleId="pub.moneytype"/>${c.operAmount}</td>
		       <td>
		       		<c:if test="${c.chargeState==0}"><aebiz:showTitle titleId="virtualaccountstorecharge.m.underPay"/></c:if>
		       		<c:if test="${c.chargeState==1}"><aebiz:showTitle titleId="virtualaccountstorecharge.m.paySuccess"/></c:if>
		       		<c:if test="${c.chargeState==2}"><aebiz:showTitle titleId="virtualaccountstorecharge.m.payFail"/></c:if>
		       </td>
		       <td>
		       		<c:if test="${c.chargeState==0}"><a class="btn btn-warning" href="#"><aebiz:showTitle titleId="virtualaccountstorecharge.m.gotoPay"/></a></c:if>
		       </td>
		     </tr>
		    </c:forEach>
		   </tbody>
		 </table>

		 <!--页码 star-->
		 	<aebiz:customerChargePage/>
		 <!--页码 end-->
	</c:if>

</div>
<script>
$("#state").change(function(){
	var state = $("#state").val();
	searchChargeDetail(state,"1","5")
});
</script>