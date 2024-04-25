<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_ACCOUNT" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.DELETE_ACCOUNT" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section>
    <div class="container t-container">

        <%
            RoleEnum role = ((AccountDTO) request.getSession().getAttribute(ACCOUNT)).getRole();
            ListForPage<AccountDTO> data = (ListForPage<AccountDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            List<AccountDTO> list = data.getList();
            String tablePage = data.getPage();
        %>

        <table>
            <tr>
                <th>Сервисный центр</th>
                <th>email</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Активно</th>
                <% if (RoleEnum.ADMIN.equals(role)) { %>
                <th class="menu">Управление</th>
                <% } %>
            </tr>

            <% for (AccountDTO account : list) {
                String serviceName = account.getServiceCenterName() != null? account.getServiceCenterName() : "";
            %>
            <tr class="t-tr">
                <td class="code"><%=serviceName%></td>
                <td class="code"><%=account.getEmail()%></td>
                <td class="code"><%=account.getUserName()%></td>
                <td class="code"><%=account.getUserSurname()%></td>
                <td class="code">
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=account.getIsActive()%>"
                           <%if (account.getIsActive()) {%>checked<%}%> disabled>
                </td>
                <td class="code">
                    <% if (RoleEnum.ADMIN.equals(role)) { %>
                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_ACCOUNT%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=account.getId()%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>

                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=DELETE_ACCOUNT%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=account.getId()%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input class="choose-button order-btn" type="submit" value="Удалить" >
                    </form>
                    <% } %>
                </td>
            </tr>
            <% }%>
        </table>
    </div>
</section>