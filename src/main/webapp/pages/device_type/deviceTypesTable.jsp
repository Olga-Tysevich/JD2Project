<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.resp.ListForPage" %>
<%@ page import="it.academy.dto.req.DeviceTypeDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_DEVICE_TYPE" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.ADD_DEVICE_TYPE" %>
<%@ page import="it.academy.dto.resp.AccountDTO" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<section>
    <div class="container t-container">

        <%
            AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
            RoleEnum role = accountDTO.getRole();
            ListForPage<DeviceTypeDTO> data = (ListForPage<DeviceTypeDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            String tablePage = data.getPage();
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
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_DEVICE_TYPE%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=deviceType.getId()%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=deviceType.getIsActive()%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>
                </td>
            </tr>
            <% }%>
        </table>

        <% if (RoleEnum.ADMIN.equals(role)) {%>
        <div class="add-form">
            <form action="main" method="post" id="addDeviceType">
                <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_DEVICE_TYPE%>">
                <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=true%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">

                <div class="f-input">
                    <label class="form-el">Описание</label>
                    <input class="f-form" type="text" name="<%=OBJECT_NAME%>" value="">
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Добавить" form="addDeviceType"/>
                </div>
            </form>
        </div>
        <% }%>
    </div>
</section>