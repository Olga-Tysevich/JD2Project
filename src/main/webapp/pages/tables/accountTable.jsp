<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.AccountDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section>
    <div class="container t-container">

        <%
            ListForPage<AccountDTO> data = (ListForPage<AccountDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            List<AccountDTO> list = data.getList();
        %>

        <table>
            <tr>
                <th>email</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Сервисный центр</th>
                <th>Активно</th>
                <th class="menu">Управление</th>
            </tr>

            <% for (AccountDTO account : list) {
                String repairWorkshopName = account.getRepairWorkshop() != null? account.getRepairWorkshop().getServiceName() : "";
            %>
            <tr class="t-tr">
                <td class="code"><%=account.getEmail()%></td>
                <td class="code"><%=account.getUserName()%></td>
                <td class="code"><%=account.getUserSurname()%></td>
                <td class="code"><%=repairWorkshopName%></td>
                <td class="code">
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=account.getIsActive()%>"
                           <%if (account.getIsActive()) {%>checked<%}%> disabled>
                </td>
                <td class="code">
                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_ACCOUNT%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=account.getId()%>">
                        <input type="hidden" name="<%=ACCOUNT_REPAIR_WORKSHOP%>" value="<%=account.getRepairWorkshop()%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>
                </td>
            </tr>
            <% }%>
        </table>
    </div>
</section>