<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="/jspf/head.jspf" %>

<body>
        <form class="login" action="/login" method="POST">
            <h1>Login</h1>
            <input type="text" name="username" placeholder=<fmt:message key="login_jsp.form.login_placeholder"/> autocomplete="off" maxlength="10">
            <input type="password" name="password" placeholder=<fmt:message key="login_jsp.form.login_placeholder"/> maxlength="20">
            <input type="submit" name="" value="Login">
        </form>

    <%@ include file="/jspf/footer.jspf"%>

</body>
</html>