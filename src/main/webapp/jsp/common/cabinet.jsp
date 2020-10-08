<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Sign Up" />

<c:set var="info" value="${userInfo}" scope="session" />

<%@ include file="/jspf/header.jspf" %>

<body>
    <h1>Cabinet</h1>
    <p>A short information about user</p>

    <div class="info">
        <label>
            <b>Name:</b>
        </label>

        <p>${info.name}</p>
        <label>
            <b>Surname:</b>
        </label>
        <p>${info.surname}</p>
        <label>
            <b>Gender:</b>
        </label>
        <p>${info.gender}</p>
        <label>
            <b>Email:</b>
        </label>
        <p>${info.email}</p>
        <label>
            <b>City:</b>
        </label>
        <p>${info.city}</p>
    </div>

</body>
</html>
