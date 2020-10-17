<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
<%@ page import="com.epam.project.db.Status" %>
<html>
<c:set var="title" value="Sign Up" />

<c:set var="info" value="${userInfo}" scope="session" />

<%@ include file="/jspf/header.jspf" %>

<body>
    <table>
        <tr>
        <th><h1><fmt:message key="cabinet_jsp.label"/></h1></th>
        </tr>
        <tr>
        <th><fmt:message key="cabinet_jsp.info"/></th>
        </tr>
    </table>
    <div class="info">
        <table>
            <tr>
                <th>
                    <fmt:message key="user.name"/>:
                </th>
                <td>${info.name}</td>
            </tr>
            <tr>
                <th>
                    <b><fmt:message key="user.surname"/>:</b>
                </th>
                <td>${info.surname}</td>
            </tr>
            <tr>
                <th>
                    <b><fmt:message key="user.gender"/>:</b>
                </th>
                <td>${info.gender}</td>
            </tr>
            <tr>
                <th>
                    <b><fmt:message key="user.email"/>:</b>
                </th>
                <td>${info.email}</td>
            </tr>
            <tr>
                <th>
                    <b><fmt:message key="user.city"/>:</b>
                </th>
                <td>${info.city}</td>
            </tr>
        </table>

        <table id="tour_menu_table">
            <thead>
            <tr>
                <th>№</th>
                <th><fmt:message key="tour.type"/></th>
                <th><fmt:message key="tour.hotel"/></th>
                <th><fmt:message key="tour.price"/></th>
                <th><fmt:message key="tour.human"/></th>
                <th><fmt:message key="tour.status"/></th>
                <th><fmt:message key="tour.discount"/></th>
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
