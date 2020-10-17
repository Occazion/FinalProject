<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
<%@ page import="com.epam.project.db.Status" %>
<html>

<c:set var="info" value="${userInfo}" scope="session" />

<head>

    <c:set var="title" value="${pageTitle}" scope="request" />

    <title>
        ${title}
    </title>

    <%--===========================================================================
    Bind CSS document.
    ===========================================================================--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" type="text/css" media="screen" href="/stylesheets/style.css"/>

    <link rel="stylesheet" type="text/css" media="screen" href="/stylesheets/modal.css"/>

    <%--===========================================================================
    If you define http-equiv attribute, set the content type and the charset the same
    as you set them in a page directive.
    ===========================================================================--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body>
<%@ include file="/jspf/header.jspf" %>

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
        <c:if test="${not empty tourList}">
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
                                <%--<c:if test="${not empty user}">
                                    <td><input type="checkbox" name="tourId" value="${tour.id}"/></td>
                                </c:if>--%>
                            </tr>
                        </c:forEach>
                </table>
            </div>
                <!-- Button to open the modal login form -->
                <button class="button" onclick="document.getElementById('id01').style.display='block'">
                    <span>
                        <fmt:message key="pay.button"/>
                    </span>
                </button>
        </c:if>
        <!-- The Modal -->
        <div id="id01" class="modal">
            <span onclick="document.getElementById('id01').style.display='none'"
                class="close" title="Close Modal">&times;</span>

            <!-- Modal Content -->
            <form class="modal-content animate" action="controller" method="post">

                <input type="hidden" name="command" value="payForTours"/>

                <div class="container">
                        <div class="row">
                            <div class="col-50">
                                <h3><fmt:message key="pay.form.billing_address"/></h3>
                                <label><i class="fa fa-user"></i>
                                    <fmt:message key="pay.form.full_name"/></label>
                                <input type="text" id="fname" name="firstname" placeholder="John M. Doe" required>
                                <label><i class="fa fa-envelope"></i>
                                    <fmt:message key="user.email"/></label>
                                <input type="text" id="email" name="email" placeholder="john@example.com" required>
                                <label><i class="fa fa-address-card-o"></i>
                                    <fmt:message key="pay.form.address"/></label>
                                <input type="text" id="adr" name="address" placeholder="542 W. 15th Street" required>
                                <label><i class="fa fa-institution"></i>
                                    <fmt:message key="user.city"/></label>
                                <input type="text" id="city" name="city" placeholder="New York">

                                <div class="row">
                                    <div class="col-50">
                                        <label for="state"><fmt:message key="pay.form.state"/></label>
                                        <input type="text" id="state" name="state" placeholder="NY" required>
                                    </div>
                                    <div class="col-50">
                                        <label for="zip"><fmt:message key="pay.form.zip"/></label>
                                        <input type="text" id="zip" name="zip" placeholder="10001" required>
                                    </div>
                                </div>
                            </div>

                            <div class="col-50">
                                <h3><fmt:message key="pay.form.payment"/></h3>
                                <label for="fname"><fmt:message key="pay.form.cards"/></label>
                                <div class="icon-container">
                                    <i class="fa fa-cc-visa" style="color:navy;"></i>
                                    <i class="fa fa-cc-amex" style="color:blue;"></i>
                                    <i class="fa fa-cc-mastercard" style="color:red;"></i>
                                    <i class="fa fa-cc-discover" style="color:orange;"></i>
                                </div>
                                <label for="cname"><fmt:message key="pay.form.name_on_card"/></label>
                                <input type="text" id="cname" name="cardname" placeholder="John More Doe" required>
                                <label for="ccnum"><fmt:message key="pay.form.card_number"/></label>
                                <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444" required>
                                <label for="expmonth"><fmt:message key="pay.form.exp_month"/></label>
                                <input type="text" id="expmonth" name="expmonth" placeholder="September" required>

                                <div class="row">
                                    <div class="col-50">
                                        <label for="expyear"><fmt:message key="pay.form.exp_year"/></label>
                                        <input type="text" id="expyear" name="expyear" placeholder="2018" required>
                                    </div>
                                    <div class="col-50">
                                        <label for="cvv"><fmt:message key="pay.form.cvv"/></label>
                                        <input type="text" id="cvv" name="cvv" placeholder="352" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn"><span><fmt:message key="pay.button"/> </span></button>
                </div>
            </form>
        </div>
</body>

<script>
    // Get the modal
    var modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

</html>
