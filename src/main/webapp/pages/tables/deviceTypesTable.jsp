<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="it.academy.dto.device.DeviceTypeDTO" %>
<%@ page import="static it.academy.servlets.managers.CommandEnum.SHOW_DEVICE_TYPE" %>
<%@ page import="static it.academy.servlets.managers.CommandEnum.ADD_DEVICE_TYPE" %>
<section>
    <div class="container t-container">

        <%
            ListForPage<DeviceTypeDTO> data = (ListForPage<DeviceTypeDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            List<DeviceTypeDTO> list = data.getList();
        %>

        <table>
            <tr>
                <th>Тип устройства</th>
                <th>Активно</th>
                <th class="menu">Управление</th>
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
                        <input type="hidden" name="<%=DEVICE_TYPE_ID%>" value="<%=deviceType.getId()%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=deviceType.getIsActive()%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>
                </td>
            </tr>
            <% }%>
        </table>

        <div class="add-form">
            <form action="main" method="post" id="addDeviceType">
                <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_DEVICE_TYPE%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">

                <div class="f-input">
                    <label class="form-el">Описание</label>
                    <input class="f-form" type="text" name="<%=DEVICE_TYPE_NAME%>" value="">
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Добавить" form="addDeviceType"/>
                </div>
            </form>
        </div>
    </div>
</section>