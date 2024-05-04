<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.GET_ACCOUNT" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.DELETE_ACCOUNT" %>
<%@ page import="it.academy.dto.TablePage2" %>
<%@ page import="it.academy.entities.account.ServiceCenter_" %>
<%@ page import="it.academy.entities.account.Account_" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section>
    <div class="container t-container">

        <%
            TablePage2<AccountDTO> data = (TablePage2<AccountDTO>) request.getAttribute(TABLE_PAGE);
            List<AccountDTO> list = data.getList();
        %>

        <table>
            <tr>
                <th>Сервисный центр</th>
                <th>email</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Активно</th>
                <th class="menu">Управление</th>
            </tr>

            <% for (AccountDTO account : list) {
                String serviceName = StringUtils.defaultIfBlank(account.getServiceCenterName(), StringUtils.EMPTY);
            %>
            <tr class="t-tr">
                <td class="code"><%=serviceName%></td>
                <td class="code"><%=account.getEmail()%></td>
                <td class="code"><%=account.getUserName()%></td>
                <td class="code"><%=account.getUserSurname()%></td>
                <td class="code">
                    <input type="checkbox" value="<%=account.getIsActive()%>"
                           <%if (account.getIsActive()) {%>checked<%}%> disabled>
                </td>
                <td class="code">
                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=GET_ACCOUNT%>">
                        <input type="hidden" name="<%=Account_.ID%>" value="<%=account.getId()%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>

                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=DELETE_ACCOUNT%>">
                        <input type="hidden" name="<%=Account_.ID%>" value="<%=account.getId()%>">
                        <input class="choose-button order-btn" type="submit" value="Удалить" >
                    </form>
                </td>
            </tr>
            <% }%>
        </table>
    </div>
</section>