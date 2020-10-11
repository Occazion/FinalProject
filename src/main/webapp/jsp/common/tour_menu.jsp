<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
<%@ page import="com.epam.project.db.Status" %>
<html>

<c:set var="title" value="Menu" scope="page" />


    <fmt:setLocale value="en" scope="session" />

<fmt:setBundle basename="resources"/>

<body>

<%@ include file="/jspf/header.jspf" %>

<div id="main">


        <td class="content">
            <%-- CONTENT --%>

            <form id="make_order" action="controller" method="post">
                <input type="hidden" name="command" value="makeOrder"/>

                <c:if test="${not empty user}">
                    <button type="submit">Make an order</button>
                </c:if>
                <fmt:message key="test"/>

                <script src="../../js/table.js"></script>

                <table id="main_table">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th onclick="sortTableWithChars(2)">Type</th>
                        <th onclick="sortTableWithChars(3)">Hotel</th>
                        <th onclick="sortTableWithDigits(4)">Price</th>
                        <th onclick="sortTableWithDigits(5)">Human Amount</th>
                        <th>Status</th>
                        <th onclick="sortTableWithDigits(7)">Discount</th>
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

            </form>
            <%-- CONTENT --%>
        </td>

</div>
<%@ include file="/jspf/footer.jspf" %>
</body>
