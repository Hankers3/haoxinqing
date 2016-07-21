<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="hidden" name="fieldName" id="fieldName" value="${m.fieldValue}" />
<input type="hidden" name="auditReason" id="auditReason" value="" />
<input type="hidden" name="auditType" id="auditType" value="${auditType}" />
<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
	<thead>		
		<tr>
			<th class='with-checkbox'>
				选择
			</th>
			<th><aebiz:showTitle titleId="productManagerList.m.reason" /></th>
		</tr>
	</thead>
	
	<tbody>
			<c:forEach items="${list}" var="data" varStatus="num">
				<tr>
					<td class="with-checkbox">
						<input type="checkbox" name="check" value="${data.name}" ${data.value}>
					</td>
					<td>${data.name}</td>
				</tr>
			</c:forEach>
	</tbody>
	
</table>
