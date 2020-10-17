<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
<html>
<head>
    <c:set var="title" value="Sign Up" />
    <title>
        ${title}
    </title>

    <%--===========================================================================
    Bind CSS document.
    ===========================================================================--%>
    <link rel="stylesheet" type="text/css" media="screen" href="/stylesheets/signup.css"/>

    <%--===========================================================================
    If you define http-equiv attribute, set the content type and the charset the same
    as you set them in a page directive.
    ===========================================================================--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<form action="controller" method="post">
    <div class="container">

        <input type="hidden" name="command" value="signup"/>

        <h1><fmt:message key="signup_jsp.register"/></h1>
        <p><fmt:message key="signup_jsp.info"/></p>
        <hr>

        <label>
            <b><fmt:message key="user.login"/></b>
        </label>
        <input type="text" placeholder="Enter Login" name="login" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,10}$" required>

        <label>
            <b><fmt:message key="user.password"/></b>
        </label>
        <input type="password" id="password" placeholder="Enter Password" name="password"
               pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
               title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
               required>

        <p><input type="checkbox" onclick="showPsw()">
            <fmt:message key="signup_jsp.show_password"/></p>

        <div id="message">
            <h3>Password must contain the following:</h3>
            <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
            <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
            <p id="number" class="invalid">A <b>number</b></p>
            <p id="length" class="invalid">Minimum <b>8 characters</b></p>
        </div>

        <label>
            <b><fmt:message key="user.name"/></b>
        </label>
        <input type="text" placeholder="Enter Name" name="name" pattern="[A-Z][a-z]+{1,10}" required>

        <label>
            <b><fmt:message key="user.surname"/></b>
        </label>
        <input type="text" placeholder="Enter Surname" name="surname" pattern="[A-Z][a-z]+{1,20}" required>

        <label>
            <b><fmt:message key="user.gender"/></b>
        </label>

            <p>
                <select name="gender" required>
                <option disabled><fmt:message key="user.select_gender"/>:</option>
                <option value="male"><fmt:message key="user.gender.male"/></option>
                <option value="female"><fmt:message key="user.gender.female"/></option>
                <option value="other" selected><fmt:message key="user.gender.other"/></option>
                </select>
            </p>

        <label>
            <b><fmt:message key="user.email"/></b>
        </label>
        <input type="text" placeholder="Enter Email" name="email" required>

        <label>
            <b><fmt:message key="user.city"/></b>
        </label>
        <input type="text" placeholder="Enter City" name="city" required>

        <label>
            <b><fmt:message key="user.locale"/></b>
        </label>

        <p><select name="locale" required>
            <option disabled><fmt:message key="user.select_locale"/>:</option>
            <option value="ru"><fmt:message key="user.locale.russian"/></option>
            <option value="en" selected><fmt:message key="user.locale.english"/></option>
        </select></p>


    <%--<label>
            <b>Repeat Password</b>
        </label>
        <input type="password" placeholder="Repeat Password" name="psw-repeat" required>--%>
        <hr>

       <%-- <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>--%>
        <button type="submit" class="registerbtn">
            <fmt:message key="signup_jsp.register"/></button>
    </div>

    <div class="container signin">
        <p><fmt:message key="signup_jsp.question"/><a href="login.jsp">
            <fmt:message key="signup_jsp.signin"/></a>.</p>
    </div>
</form>
</body>

<script src="js/signup.js"></script>

</html>
