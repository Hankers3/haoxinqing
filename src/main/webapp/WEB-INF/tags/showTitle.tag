<%@tag pageEncoding="UTF-8" description="显示标题信息" %>
<%@ attribute name="titleId" type="java.lang.String" required="true" description="要显示的标题编号" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>
<%out.print(com.aebiz.b2b2c.baseframework.message.MessageHelper.getMessage(titleId));%>