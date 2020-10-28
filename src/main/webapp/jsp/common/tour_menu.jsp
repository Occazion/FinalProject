<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>

<%@ taglib prefix = "cust" uri = "/WEB-INF/toStatusTag" %>

<html>



<body>

<%@ include file="/jspf/header.jspf" %>

<div id="main">
            <form id="make_order" action="controller" method="post">
                <input type="hidden" name="command" value="makeOrder"/>

                <c:if test="${not empty user}">
                    <button type="submit" class="button"><span>
                        <fmt:message key="tour_table.make_an_order"/></span></button>
                </c:if>

                <script src="../../js/table.js"></script>

                <table id="main_table">
                    <thead>
                    <tr>
                        <th onclick="sortTableWithDigits(0)">№</th>
                        <th onclick="sortTableWithChars(1)"><fmt:message key="tour.type"/></th>
                        <th onclick="sortTableWithChars(2)"><fmt:message key="tour.hotel"/></th>
                        <th onclick="sortTableWithDigits(3)"><fmt:message key="tour.price"/></th>
                        <th onclick="sortTableWithDigits(4)"><fmt:message key="tour.human"/></th>
                        <th><fmt:message key="tour.status"/></th>
                        <th onclick="sortTableWithDigits(6)"><fmt:message key="tour.discount"/> %</th>
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
                            <td>${tour.humanAmount}</td>
                            <td><cust:toStatus>${tour.statusId}</cust:toStatus></td>
                            <td>${tour.discount}</td>
                            <c:if test="${not empty user}">
                            <td><input type="checkbox" name="tourId" value="${tour.id}"/></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>

            </form>

    <div class="pagination">
        <a href="/controller?command=tourMenu&p=${p - 1}"><b>&laquo;</b></a>
        <a class="active"><b>${p}</b></a>
        <a href="/controller?command=tourMenu&p=${p + 1}"><b>&raquo;</b></a>
    </div>

</div>
<%@ include file="/jspf/footer.jspf" %>
</body>
