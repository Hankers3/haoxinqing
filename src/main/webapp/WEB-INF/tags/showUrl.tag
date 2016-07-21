<%@tag pageEncoding="UTF-8" description="获取各模块的地址" %>
<%@ attribute name="modulesName" type="java.lang.String" required="true" description="要获取的模块名称" %>
<%@ attribute name="showId" type="java.lang.String" description="要获取的模块名称" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>
<%out.print("http://"+modulesName);%>