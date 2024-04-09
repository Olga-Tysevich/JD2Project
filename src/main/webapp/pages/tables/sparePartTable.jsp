<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="it.academy.dto.spare_parts.SparePartDTO" %>
<%@ page import="it.academy.dto.device.DeviceTypeDTO" %>
<section>
    <div class="container t-container">

        <%
            ListForPage<SparePartDTO> data = (ListForPage<SparePartDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            int maxPageNumber = data.getMaxPageNumber();
            List<SparePartDTO> list = data.getList();
            List<DeviceTypeDTO> deviceTypes = (List<DeviceTypeDTO>) request.getAttribute(DEVICE_TYPES);
        %>


        <div class="radio-container">
            <form class="status-form" action="main" method="post" id="find_spare_parts">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="command" value="show_spare_part_table">
            </form>
        </div>


        <table>
            <tr>
                <th>Тип устройства</th>
                <th>Наименование запчасти</th>
                <th class="menu">Управление</th>
            </tr>

            <% for (SparePartDTO sparePart : list) {
                for (DeviceTypeDTO deviceType : sparePart.getRelatedDeviceTypes()) {
            %>
            <tr class="t-tr">
                <td class="code"><%=deviceType.getName()%></td>
                <td class="level"><%=sparePart.getName()%></td>
                <td>
                    <div class="button-table-container">
                        <form action="repair" method="post">
                            <input type="hidden" name="command" value="show_spare_part_form">
                            <input type="hidden" name="<%=CURRENT_SPARE_PART_ID%>" value="<%=sparePart.getId()%>">
                            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                            <input class="choose-button order-btn" type="submit" value="Изменить">
                        </form>
                        <form action="repair" method="post">
                            <input type="hidden" name="command" value="delete_spare_part">
                            <input type="hidden" name="<%=CURRENT_SPARE_PART_ID%>" value="<%=sparePart.getId()%>">
                            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                            <input class="choose-button order-btn" type="submit" value="Удалить">
                        </form>
                    </div>
                </td>
            </tr>
            <% } }%>
        </table>

<%--        <jsp:include page="/pages/changePageButtons.jsp"/>--%>

        <%if (data.getMaxPageNumber() != 1) {%>
        <div class="footer">
            <div class="button-container">
                <%if (pageNumber != FIRST_PAGE) { %>
                <form action="main" method="post">
                    <input type="hidden" name="command" value="show_spare_part_table">
                    <%int prevPage = pageNumber - 1;%>
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=prevPage%>">
                    <input class="button light" type="submit" name="button" value="Предыдущая">
                </form>
                <% } %>

                <p><%=pageNumber%>
                    из
                    <%=maxPageNumber%>
                </p>


                <%if (pageNumber != maxPageNumber) { %>
                <form action="main" method="post">
                    <input type="hidden" name="command" value="show_spare_part_table">
                    <%int nextPage = pageNumber + 1;%>
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=nextPage%>">
                    <input class="button light" type="submit" name="button" value="Следующая">
                </form>
                <% } %>

            </div>
        </div>
        <% } %>

        <div class="add-form">
            <form action="main" method="post" id="addSparePart">
                <input type="hidden" name="command" value="add_spare_part">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">

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
    </div>
</section>