<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
<html>

<%@ taglib prefix = "cust" uri = "/WEB-INF/toStatusTag" %>

<body>

<%@ include file="/jspf/header.jspf" %>

<div id="main">

        <form id="manageTours" action="controller" method="post">
            <input type="hidden" name="command" value="manageTours"/>

            <table class="command">

            <c:if test="${not empty user}">
                <c:choose>
                    <c:when test="${userRole.name == 'admin' || userRole.name == 'manager' }">

                        <button type="submit" class="button"><span>
                            <fmt:message key="tours.execute"/></span></button>

                        <tr>
                            <td><label class="container"><fmt:message key="tours.set_fire"/></label></td>
                            <td><input type="radio" checked="checked" name="actionType" value="fire"><span class="checkmark"></span></td>
                            <td><select name="isFire" required>
                                <option disabled><fmt:message key="tours.set_fire.select"/>:</option>
                                <option value="true"><fmt:message key="tours.set_fire.select.yes"/></option>
                                <option value="false" selected><fmt:message key="tours.set_fire.select.no"/></option>
                            </select></td>
                        </tr>
                        <tr>
                            <td><label class="container"><fmt:message key="tours.change_status"/><span class="checkmark"></span></label></td>
                            <td><input type="radio" name="actionType" value="status"></td>

                            <td><select name="statusToChange" required>
                                <option disabled><fmt:message key="tours.change_status.select"/>:</option>
                                <option value="0"><fmt:message key="tours.change_status.select.opened"/></option>
                                <option value="1"><fmt:message key="tours.change_status.select.confirmed"/></option>
                                <option value="2"><fmt:message key="tours.change_status.select.paid"/></option>
                                <option value="3" selected><fmt:message key="tours.change_status.select.closed"/></option>
                            </select></td>
                        </tr>
                        <tr>
                            <td><label class="container"><fmt:message key="tours.change_discount"/><span class="checkmark"></span></label></td>
                            <td><input type="radio" name="actionType" value="discount"></td>
                            <td><input type="number" name="discountToSet" max="100"></td>
                        </tr>
                            <c:choose>
                            <c:when test="${userRole.name == 'admin' }">
                                <tr>
                                    <td><label class="container"><fmt:message key="tours.add_tour"/><span class="checkmark"></span></label></td>
                                    <td><input type="radio" name="actionType" value="add"></td>

                                    <td><strong><fmt:message key="tour.type"/>:</strong>
                                    <select name="tourType">
                                        <option disabled><fmt:message key="tours.select_type"/>:</option>
                                        <option value="excursion" selected><fmt:message key="tours.select_type.excursion"/></option>
                                        <option value="relaxation"><fmt:message key="tours.select_type.relaxation"/></option>
                                        <option value="shopping"><fmt:message key="tours.select_type.shopping"/></option>
                                    </select>
                                    <strong><fmt:message key="tour.hotel"/>:</strong>
                                    <input type="text" name="tourHotel">
                                    <p><strong><fmt:message key="tour.price"/>:</strong>
                                    <input type="number" name="tourPrice">
                                    <strong><fmt:message key="tour.human"/>:</strong>
                                        <input type="number" name="tourAmount"></td></p>

                                </tr>
                                <tr>
                                    <td><label class="container"><fmt:message key="tours.delete_tour"/><span class="checkmark"></span></label></td>
                                    <td><input type="radio" name="actionType" value="delete"></td>

                                </tr>
                                <tr>
                                    <td><label class="container"><fmt:message key="tours.change_details"/><span class="checkmark"></span></label></td>
                                    <td><input type="radio" name="actionType" value="change"></td>
                                    <td><strong><fmt:message key="tour.type"/>:</strong>
                                    <select name="tourTypeUpd">
                                        <option disabled><fmt:message key="tours.select_type"/>:</option>
                                        <option value="excursion" selected><fmt:message key="tours.select_type.excursion"/></option>
                                        <option value="relaxation"><fmt:message key="tours.select_type.relaxation"/></option>
                                        <option value="shopping"><fmt:message key="tours.select_type.shopping"/></option>
                                    </select>
                                    <strong><fmt:message key="tour.hotel"/>:</strong>
                                    <input type="text" name="tourHotelUpd">
                                        <p>
                                            <strong><fmt:message key="tour.price"/>:</strong>
                                            <input type="number" name="tourPriceUpd">
                                            <strong><fmt:message key="tour.human"/>:</strong>
                                            <input type="number" name="tourAmountUpd">
                                        </p>
                                    <strong><fmt:message key="tour.fire"/>?:</strong>
                                    <select name="tourIsFireUpd">
                                        <option disabled><fmt:message key="tour.fire"/>?:</option>
                                        <option value="false" selected><fmt:message key="tours.set_fire.select.no"/></option>
                                        <option value="true"><fmt:message key="tours.set_fire.select.yes"/></option>
                                    </select>
                                    <strong><fmt:message key="tour.discount"/>:</strong>
                                    <input type="number" name="tourDiscountUpd">
                                        <p>
                                            <strong><fmt:message key="tour.user_id"/>:</strong>
                                        <input type="number" name="tourUserIdUpd"></td>
                                    </p>
                                </tr>
                            </c:when>
                            </c:choose>
                    </c:when>

                </c:choose>

            </c:if>
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
                    <th><fmt:message key="tour.fire"/></th>
                    <th><fmt:message key="tour.user_id"/></th>
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
                        <td>${tour.discount}%</td>
                        <td>${tour.fire}</td>
                        <td>${tour.userId}</td>
                        <c:if test="${not empty user}">
                            <td><input type="radio" name="tourId" value="${tour.id}"/></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>

        </form>

</div>
<%@ include file="/jspf/footer.jspf" %>
</body>