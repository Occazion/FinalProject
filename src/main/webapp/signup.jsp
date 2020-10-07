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
<form action="action_page.php">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label>
            <b>Login</b>
        </label>
        <input type="text" placeholder="Enter Login" name="login" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,10}$" required>

        <label>
            <b>Password</b>
        </label>
        <input type="password" id="password" placeholder="Enter Password" name="password"
               pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
               title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
               required>

        <p><input type="checkbox" onclick="showPsw()">Show Password</p>

        <div id="message">
            <h3>Password must contain the following:</h3>
            <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
            <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
            <p id="number" class="invalid">A <b>number</b></p>
            <p id="length" class="invalid">Minimum <b>8 characters</b></p>
        </div>

        <label>
            <b>Name</b>
        </label>
        <input type="text" placeholder="Enter Name" name="name" required>

        <label>
            <b>Surname</b>
        </label>
        <input type="text" placeholder="Enter Surname" name="surname" required>

        <label>
            <b>Gender</b>
        </label>

            <p><select name="gender" required>
                <option disabled>Select gender:</option>
                <option>Male</option>
                <option>Female</option>
                <option selected>Other</option>
            </select></p>

        <label>
            <b>Email</b>
        </label>
        <input type="text" placeholder="Enter Email" name="email" required>

        <label>
            <b>City</b>
        </label>
        <input type="text" placeholder="Enter City" name="city" required>

        <%--<label>
            <b>Repeat Password</b>
        </label>
        <input type="password" placeholder="Repeat Password" name="psw-repeat" required>--%>
        <hr>

        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <button type="submit" class="registerbtn">Register</button>
    </div>

    <div class="container signin">
        <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
    </div>
</form>
</body>

<script src="js/signup.js"></script>

</html>
