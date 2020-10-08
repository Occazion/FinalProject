<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
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
                </tr>
                </thead>

                <c:set var="k" value="0"/>
                <c:forEach var="user" items="${userList}">
                    <c:set var="k" value="${k+1}"/>
                    <tr>
                        <td><c:out value="${k}"/></td>
                        <td>${user.login}</td>
                        <td>${user.roleId}</td>
                        <td>${user.locale}</td>

                        <c:if test="${not empty user}">
                            <td><input type="checkbox" name="login" value="${user.login}"/></td>
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
