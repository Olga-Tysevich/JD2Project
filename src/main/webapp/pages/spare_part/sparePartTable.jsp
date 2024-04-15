<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.resp.ListForPage" %>
<%@ page import="it.academy.dto.req.DeviceTypeDTO" %>
<%@ page import="it.academy.dto.resp.AccountDTO" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="it.academy.dto.resp.SparePartDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_SPARE_PART_FORM" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.ADD_SPARE_PART" %>
<section>
    <div class="container t-container">

        <%
            AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
            RoleEnum role = accountDTO.getRole();
            ListForPage<SparePartDTO> data = (ListForPage<SparePartDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            List<SparePartDTO> list = data.getList();
            List<DeviceTypeDTO> deviceTypes = list.get(0).getAllDeviceTypes();
            String currentPage = request.getParameter(PAGE);
        %>


        <table>
            <tr>
                <th>Тип устройства</th>
                <th>Наименование запчасти</th>
                <th>Активно</th>
                <% if (RoleEnum.ADMIN.equals(role)) {%>
                <th class="menu">Управление</th>
                <% } %>
            </tr>

            <%
                for (SparePartDTO sparePart : list) {
                for (DeviceTypeDTO deviceType : sparePart.getRelatedDeviceTypes()) {
            %>
            <tr class="t-tr">
                <td class="code"><%=deviceType.getName()%></td>
                <td class="level"><%=sparePart.getName()%></td> <td class="code">
                <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=sparePart.getIsActive()%>"
                       <%if (sparePart.getIsActive()) {%>checked<%}%> disabled>
            </td>
                <td>
                    <div class="button-table-container">
                        <form action="repair" method="post">
                            <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_SPARE_PART_FORM%>">
                            <input type="hidden" name="<%=OBJECT_ID%>" value="<%=sparePart.getId()%>">
                            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                            <input type="hidden" name="<%=PAGE%>" value="<%=request.getParameter(PAGE)%>">
                            <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=sparePart.getIsActive()%>">
                            <input class="choose-button order-btn" type="submit" value="Изменить">
                        </form>
                    </div>
                </td>
            </tr>
            <% } }%>
        </table>


    <% if (RoleEnum.ADMIN.equals(role)) {%>
        <div class="add-form">
            <form action="main" method="post" id="addSparePart">
                <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_SPARE_PART%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=true%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=currentPage%>">

                <div class="f-input">
                    <label class="form-el">Название запчасти</label>
                    <input class="f-form" type="text" name="<%=OBJECT_NAME%>" value="">
                </div>

                <div class="f-input">
                    <label class="form-el">Тип устройства:</label>
                <div>
                    <%for (DeviceTypeDTO deviceType : deviceTypes) {%>
                    <div>
                        <input type="checkbox" name="<%=DEVICE_TYPE_ID%>" value="<%=deviceType.getId()%>">
                        <%=deviceType.getName()%>
                    </div>
                    <%}%>
                </div>
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Добавить" form="addSparePart"/>
                </div>
            </form>
        </div>
    <% }%>
    </div>
</section>