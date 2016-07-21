<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="tixianjilu" class="tab-pane active">
<div class="logi-nr">
	<aebiz:showTitle titleId="virtualaccountstorecharge.m.timeType"/>:
   <select class="select2" id="timeType" name="timeType">
		<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
		<option value="1"  <c:if test="${timeType==1}">selected=selected</c:if>><aebiz:showTitle titleId="virtualaccountcustomercharge.m.trimesterin"/></option>
		<option value="2"  <c:if test="${timeType==2}">selected=selected</c:if>><aebiz:showTitle titleId="virtualaccountcustomercharge.m.trimesterout"/></option>
		<option value="3"  <c:if test="${timeType==3}">selected=selected</c:if>><aebiz:showTitle titleId="virtualaccountcustomercharge.m.yearin"/></option>
	</select>
	<aebiz:showTitle titleId="withdrawapply.m.state"/>:
   <select class="select2" id="state" name="state">
		<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
		<option value="0"  <c:if test="${state==0}">selected=selected</c:if>><aebiz:showTitle titleId="withdrawapply.qm.untreated"/></option>
		<option value="1"  <c:if test="${state==1}">selected=selected</c:if>><aebiz:showTitle titleId="withdrawapply.qm.withdrawSuccess"/></option>
		<option value="2"  <c:if test="${state==2}">selected=selected</c:if>><aebiz:showTitle titleId="withdrawapply.qm.withdrawFail"/></option>
	</select>
	 
 </div>
  	<c:if test="${ empty list }">
	  <div class="alert alert-warning">
			<aebiz:showTitle titleId="withdrawapply.m.noWithdrawapplyrecord"/>
		</div>
	</c:if>
	 <c:if test="${ !empty list }">
		 <table class="table table-sorting table-striped table-hover" cellpadding="0" cellspacing="0" width="100%">
		    <thead>
		      <tr>
		         <th><aebiz:showTitle titleId="virtualaccountcustomercharge.m.NO"/></th>
		         <th><aebiz:showTitle titleId="withdrawapply.qm.applyType"/></th>
		         <th><aebiz:showTitle titleId="withdrawapply.qm.applyMoney"/></th>
		         <th><aebiz:showTitle titleId="withdrawapply.m.Whereabouts"/></th>
		         <th><aebiz:showTitle titleId="withdrawapply.m.state"/></th>
		         <th><aebiz:showTitle titleId="withdrawapply.m.withdrawapplySuccessTime"/></th>
		    </tr>
		    </thead>
		   <tbody>
		   	<c:forEach items="${list}" var="c" varStatus="s" >
		     <tr>
		       <td>${s.index+1}</td>
		       <td>${c.applyTime}</td>
		       <td>${c.applyMoney}</td>
		       <td>${c.failReason}</td>
		       <td>
		       <c:if test="${c.state==0}"><aebiz:showTitle titleId="withdrawapply.qm.untreated"/></c:if>
		       <c:if test="${c.state==1}"><aebiz:showTitle titleId="withdrawapply.qm.withdrawSuccess"/></c:if>
		       <c:if test="${c.state==2}"><aebiz:showTitle titleId="withdrawapply.qm.withdrawFail"/></c:if>
		       </td>
		       <td>${c.successTime}</td>
		     </tr>
		    </c:forEach>
		   </tbody>
		 </table>

		 <!--页码 star-->
		 	<aebiz:withdrawapplyPage/>
		 <!--页码 end-->
	</c:if>

</div>
<script>
$("#timeType").change(function(){
	var timeType = $("#timeType").val();
	var state = $("#state").val();
	searchWithapplyDetail(state,timeType,"1","5")
});
$("#state").change(function(){
	var timeType = $("#timeType").val();
	var state = $("#state").val();
	searchWithapplyDetail(state,timeType,"1","5")
});
</script>