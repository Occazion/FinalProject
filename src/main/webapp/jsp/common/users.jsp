<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
<%@ page import="com.epam.project.db.Role" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

<html>

<tf:tagfile>
    <jsp:attribute name="attr">
        <c:set var="title" value="${title}" scope="page" />
    </jsp:attribute>
</tf:tagfile>

<body>

<%@ include file="/jspf/header.jspf" %>
<div id="main">


    <td class="content">
        <%-- CONTENT --%>

        <form id="users" action="controller" method="post">
            <input type="hidden" name="command" value="manageUsers"/>

            <c:if test="${not empty user}">
                <button type="submit" class="button"><span>
                    <fmt:message key="users.execute"/></span></button>
                <table>
                <tr>
                    <td><label class="container"><fmt:message key="users.block"/>
                        <span class="checkmark"></span></label>
                    </td>
                        <td><input type="radio" checked="checked" name="actionType" value="block"></td>

                </tr>
                <tr>
                    <td><label class="container"><fmt:message key="users.unblock"/>
                        <span class="checkmark"></span></label>
                    </td>
                    <td><input type="radio" name="actionType" value="unblock"></td>

                </tr>
                </table>

            </c:if>

            <table id="users_panel_table">
                <thead>
                <tr>
                    <th>№</th>
                    <th><fmt:message key="user.login"/></th>
                    <th><fmt:message key="user.role"/></th>
                    <th><fmt:message key="user.locale"/></th>
                    <th><fmt:message key="user.name"/></th>
                    <th><fmt:message key="user.surname"/></th>
                    <th><fmt:message key="user.gender"/></th>
                    <th><fmt:message key="user.email"/></th>
                    <th><fmt:message key="user.city"/></th>
                    <th>Orders Count</th>
                    <th>Orders Price</th>
                    <th><fmt:message key="user.blocked"/></th>
                </tr>
                </thead>

                <c:set var="k" value="0"/>
                <c:forEach var="account" items="${accountBeans}">
                    <c:set var="k" value="${k+1}"/>
                    <tr>
                        <td><c:out value="${k}"/></td>
                        <td>${account.login}</td>
                        <td>${Role.getRole(account.roleId)}</td>
                        <%--<td><tf:tagfile>${account}</tf:tagfile></td>--%>
                        <td>${account.locale}</td>
                        <td>${account.name}</td>
                        <td>${account.surname}</td>
                        <td>${account.gender}</td>
                        <td>${account.email}</td>
                        <td>${account.city}</td>
                        <td>${account.ordersCount}</td>
                        <td>${account.ordersPrice}</td>
                        <td>${account.isBlocked}</td>

                        <c:if test="${not empty user}">
                            <td><input type="checkbox" name="login" value="${account.login}"/></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>

        </form>
        <%-- CONTENT --%>
    </td>

</div>
<%@ include file="/jspf/footer.jspf" %>
</body>
</html>
