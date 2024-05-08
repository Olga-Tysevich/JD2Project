<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.device.DeviceTypeDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_UPDATE_DEVICE_TYPE" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="it.academy.dto.TablePage2" %>
<section>
    <div class="container t-container">

        <%
            AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
            RoleEnum role = accountDTO.getRole();
            TablePage2<DeviceTypeDTO> data = (TablePage2<DeviceTypeDTO>) request.getAttribute(TABLE_PAGE);
            List<DeviceTypeDTO> list = data.getList();
        %>

        <table>
            <tr>
                <th>Тип устройства</th>
                <th>Активно</th>
                <% if (RoleEnum.ADMIN.equals(role)) {%>
                <th class="menu">Управление</th>
                <% } %>
            </tr>

            <% for (DeviceTypeDTO deviceType : list) {
            %>
            <tr class="t-tr">
                <td class="code"><%=deviceType.getName()%></td>
                <td class="code">
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=deviceType.getIsActive()%>"
                           <%if (deviceType.getIsActive()) {%>checked<%}%> disabled>
                </td>
                <td class="code">
                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_UPDATE_DEVICE_TYPE%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=deviceType.getId()%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>
                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=DELETE_DEVICE_TYPE%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=deviceType.getId()%>">
                        <input class="choose-button order-btn" type="submit" value="Удалить" >
                    </form>
                </td>
            </tr>
            <% }%>
        </table>
    </div>
</section>