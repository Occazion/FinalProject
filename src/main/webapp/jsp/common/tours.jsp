<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
<%@ page import="com.epam.project.db.Status" %>
<html>

<c:set var="title" value="Tours" scope="page" />
<body>

<%@ include file="/jspf/header.jspf" %>

<div id="main">


    <td class="content">
        <%-- CONTENT --%>

        <form id="manageTours" action="controller" method="post">
            <input type="hidden" name="command" value="manageTours"/>

            <c:if test="${not empty user}">
                <button type="submit">Execute</button>

                <p>
                    <label class="container">Set Fire
                        <input type="radio" checked="checked" name="actionType" value="fire">
                        <span class="checkmark"></span>
                    </label>
                    <select name="isFire" required>
                        <option disabled>Select option:</option>
                        <option value="true">yes</option>
                        <option value="false" selected>no</option>

                    </select>
                </p>
                <p>
                    <label class="container">Change Status
                        <input type="radio" name="actionType" value="status">
                        <span class="checkmark"></span>
                    </label>

                    <select name="statusToChange" required>
                        <option disabled>Select status:</option>
                        <option value="0">Opened</option>
                        <option value="1">Confirmed</option>
                        <option value="2">Paid</option>
                        <option value="3" selected>Closed</option>
                    </select>
                </p>
                <p>
                    <label class="container">Change Discount
                        <input type="radio" name="actionType" value="discount">
                        <span class="checkmark"></span>
                    </label>
                    <input type="number" name="discountToSet" max="100">
                </p>
                <p>
                    <label class="container">Add tour
                        <input type="radio" name="actionType" value="add">
                        <span class="checkmark"></span>
                    </label>
                    <b>Type:</b>
                    <select name="tourType" required>
                        <option disabled>Select type:</option>
                        <option value="excursion" selected>Excursion</option>
                        <option value="relaxation">Relaxation</option>
                        <option value="shopping">Shopping</option>
                    </select>
                    <b>Hotel:</b>
                    <input type="text" name="tourHotel">
                    <b>Price:</b>
                    <input type="number" name="tourPrice">
                    <b>Human amount:</b>
                    <input type="number" name="tourAmount">

                </p>
                <p>
                    <label class="container">Delete tour
                        <input type="radio" name="actionType" value="delete">
                        <span class="checkmark"></span>
                    </label>
                </p>
                <p>
                    <label class="container">Change tour details
                        <input type="radio" name="actionType" value="change">
                        <span class="checkmark"></span>
                    </label>
                </p>
            </c:if>

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
                    <th>Fiery?</th>
                    <th>User id</th>
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
                        <td>${tour.fire}</td>
                        <td>${tour.user_id}</td>
                        <c:if test="${not empty user}">
                            <td><input type="radio" name="tourId" value="${tour.id}"/></td>
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