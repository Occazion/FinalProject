<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/directive/page.jspf" %>
<%@ include file="/jspf/directive/taglib.jspf" %>
<html>

<body>

    <%@ include file="/jspf/header.jspf" %>
            <form id="settings_form" action="controller" method="get">
                <input type="hidden" name="command" value="updateSettings" />
                <table>
                <tr>
                    <th>
                        <fmt:message key="settings.change_locale"/>
                    </th>
                    <td>
                        <select name="localeToSet">
                            <c:choose>
                                <c:when test="${not empty defaultLocale}">
                                    <option value="">${defaultLocale}[Default]</option>
                                </c:when>
                                <c:otherwise>
                                    <option value=""/>
                                </c:otherwise>
                            </c:choose>

                            <c:forEach var="localeName" items="${locales}">
                                <option value="${localeName}">${localeName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <th>
                        <fmt:message key="user.name"/>
                    </th>
                    <td>
                        <input type="text" name="name">
                    </td>
                </tr>

                <tr>
                    <th>
                        <fmt:message key="user.surname"/>
                    </th>
                    <td>
                    <input type="text" name="surname">
                    </td>
                </tr>
                </table>
            <button type="submit" class="button"><span>
                            <fmt:message key="settings.update"/></span></button>
            <%@ include file="/jspf/footer.jspf" %>
        </form>
</body>
</html>