<%@ page import="com.wxcommon.Constants" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="aebiz"%>
<%
  request.setAttribute("RESULT_LIST", Constants.SEARCH_RESULT_LIST);
  request.setAttribute("TOTAL_ROWS", Constants.PAGINATION_TOTAL_ROWS);
  request.setAttribute("PAGE_SIZE", Constants.PAGINATION_PAGE_SIZE);
  request.setAttribute("PAGE_NO", Constants.PAGINATION_PAGE_NO);
  request.setAttribute("CONTEXT_PATH", pageContext.getServletContext().getContextPath());
%>
<fmt:setLocale value="zh_CN"/>