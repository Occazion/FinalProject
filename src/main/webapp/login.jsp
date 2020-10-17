<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<html>

<head>
    <tf:tagfile>
    <jsp:attribute name="attr">
        <title>${title}</title>
    </jsp:attribute>
    </tf:tagfile>
    <%--===========================================================================
    Bind CSS document.
    ===========================================================================--%>
    <link rel="stylesheet" type="text/css" media="screen" href="/stylesheets/signin.css"/>

    <%--===========================================================================
    If you define http-equiv attribute, set the content type and the charset the same
    as you set them in a page directive.
    ===========================================================================--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body>

        <form action="controller" method="post">

            <div class="container">

                <input type="hidden" name="command" value="login"/>

                <label><b>
                    <fmt:message key="user.login"/>
                </b></label>
                <input type="text" placeholder="Enter Username" name="login"  required>

                <label><b>
                    <fmt:message key="user.password"/>
                </b></label>
                <input type="password" placeholder="Enter Password" name="password"  required>

                <button type="submit">
                    <fmt:message key="user.login"/>
                </button>
                <%--<label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </label>--%>
                <input type="hidden" name="command" value="login"/>

                <div class="signup">
                    <p><fmt:message key="login_jsp.form.question"/>
                        <a href="signup.jsp"><fmt:message key="login_jsp.form.signup"/></a></p>
                </div>

            </div>

            <%--<div class="container" style="background-color:#f1f1f1">
                <button type="button" class="cancelbtn">Cancel</button>
            </div>--%>
        </form>
</body>
</html>