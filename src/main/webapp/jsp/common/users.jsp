<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
<%@ page import="com.epam.project.db.Role" %>
<html>
<c:set var="title" value="Users panel" scope="page" />
<body>

<%@ include file="/jspf/header.jspf" %>
<div id="main">


    <td class="content">
        <%-- CONTENT --%>

        <form id="users" action="controller">
            <input type="hidden" name="command" value="manageUsers"/>

            <c:if test="${not empty user}">
                <button type="submit">Execute</button>

                <p>
                    <label class="container">Block
                    <input type="radio" checked="checked" name="actionType" value="block">
                    <span class="checkmark"></span>
                    </label>
                </p>
                <p>
                    <label class="container">Unblock
                        <input type="radio" name="actionType" value="unblock">
                        <span class="checkmark"></span>
                    </label>
                </p>

            </c:if>

            <table id="users_panel_table">
                <thead>
                <tr>
                    <th>№</th>
                    <th>Login</th>
                    <th>Role</th>
                    <th>Locale</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>City</th>
                    <th>Blocked?</th>
                </tr>
                </thead>

                <c:set var="k" value="0"/>
                <c:forEach var="account" items="${accountBeans}">
                    <c:set var="k" value="${k+1}"/>
                    <tr>
                        <td><c:out value="${k}"/></td>
                        <td>${account.login}</td>
                        <td>${Role.getRole(account.roleId)}</td>
                        <td>${account.locale}</td>
                        <td>${account.name}</td>
                        <td>${account.surname}</td>
                        <td>${account.gender}</td>
                        <td>${account.email}</td>
                        <td>${account.city}</td>
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
