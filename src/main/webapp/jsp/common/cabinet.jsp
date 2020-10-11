<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
<%@ page import="com.epam.project.db.Status" %>
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


        <table id="tour_menu_table">
            <thead>
            <tr>
                <th>№</th>
                <th>Type</th>
                <th>Hotel</th>
                <th>Price</th>
                <th>Human Amount</th>
                <th>Status</th>
                <th>Discount</th>
            </tr>
            </thead>

            <c:set var="k" value="0"/>
            <c:forEach var="tour" items="${tourList}">
                <c:set var="k" value="${k+1}"/>
                <tr>
                    <td><c:out value="${k}"/></td>
                    <td>${tour.type}</td>
                    <td>${tour.hotel}</td>
                    <td>${tour.price}</td>
                    <td>${tour.human_amount}</td>
                    <td>${Status.getStatus(tour.statusId)}</td>
                    <td>${tour.discount}%</td>
                    <c:if test="${not empty user}">
                        <td><input type="checkbox" name="tourId" value="${tour.id}"/></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
