<%@ tag import="com.epam.project.db.Role" %>
<%@ include file="/jspf/directive/taglib.jspf" %>

<%@ attribute name="attr" fragment="true" %>
<%@ variable name-given="title" %>

<%
    jspContext.setAttribute("title", "Login");
%>

<jsp:invoke fragment="attr"/>