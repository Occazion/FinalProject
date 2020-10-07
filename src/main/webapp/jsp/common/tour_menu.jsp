<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Menu" scope="page" />
<body>

<%@ include file="/jspf/header.jspf" %>

<div id="main">


        <td class="content">
            <%-- CONTENT --%>

            <form id="make_order" action="controller">
                <input type="hidden" name="command" value="makeOrder"/>

                <c:if test="${not empty user}">
                    <button type="submit">Make an order</button>
                </c:if>

                <table id="tour_menu_table">
                    <thead>
                    <tr>
                        <td>№</td>
                        <td>Type</td>
                        <td>Hotel</td>
                        <td>Price</td>
                        <td>Human Amount</td>
                        <td>Status</td>
                        <td>Discount</td>
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
                            <td>${tour.statusId}</td>
                            <td>${tour.discount}</td>
                            <td><input type="checkbox" name="tourId" value="${tour.id}"/></td>
                        </tr>
                    </c:forEach>
                </table>

            </form>
            <%-- CONTENT --%>
        </td>

</div>
<%@ include file="/jspf/footer.jspf" %>
</body>
