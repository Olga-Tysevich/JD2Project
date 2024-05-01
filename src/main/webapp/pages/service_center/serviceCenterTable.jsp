<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.account.ServiceCenterDTO" %>
<%@ page import="it.academy.dto.TablePage" %>
<%@ page import="java.util.List" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_SERVICE_CENTER" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.DELETE_SERVICE_CENTER" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section>
    <div class="container t-container">

        <%
            TablePage<ServiceCenterDTO> data = (TablePage<ServiceCenterDTO>) request.getAttribute(TABLE_PAGE);
            int pageNumber = data.getPageNumber();
            String tablePage = data.getPage();
            List<ServiceCenterDTO> list = data.getList();
        %>

        <table>
            <tr>
                <th>Сервис</th>
                <th>email</th>
                <th>Телефон</th>
                <th>Адрес</th>
                <th>Активно</th>
                <th class="menu">Управление</th>
            </tr>

            <% for (ServiceCenterDTO serviceCenter : list) {
            %>
            <tr class="t-tr">
                <td class="code"><%=serviceCenter.getServiceName()%></td>
                <td class="code"><%=serviceCenter.getEmail()%></td>
                <td class="code"><%=serviceCenter.getPhone()%></td>
                <td class="code"><%=serviceCenter.getActualAddress()%></td>
                <td class="code">
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=serviceCenter.getIsActive()%>"
                           <%if (serviceCenter.getIsActive()) {%>checked<%}%> disabled>
                </td>
                <td class="code">
                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_SERVICE_CENTER%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=serviceCenter.getId()%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>
                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=DELETE_SERVICE_CENTER%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=serviceCenter.getId()%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input class="choose-button order-btn" type="submit" value="Удалить" >
                    </form>
                </td>
            </tr>
            <% }%>
        </table>

    </div>
</section>