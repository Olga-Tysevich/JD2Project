<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="it.academy.dto.device.DeviceTypeDTO" %>
<section>
    <div class="container t-container">

        <%
            ListForPage<DeviceTypeDTO> data = (ListForPage<DeviceTypeDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            int maxPageNumber = data.getMaxPageNumber();
            List<DeviceTypeDTO> list = data.getList();
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
                <th class="menu">Управление</th>
            </tr>

            <% for (DeviceTypeDTO deviceType : list) {
            %>
            <tr class="t-tr">
                <td class="code"><%=deviceType.getName()%></td>
                <td>
                    <div class="button-table-container">
                        <form action="repair" method="post">
                            <input type="hidden" name="command" value="show_device_type_form">
                            <input type="hidden" name="<%=CURRENT_SPARE_PART_ID%>" value="<%=deviceType.getId()%>">
                            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                            <input class="choose-button order-btn" type="submit" value="Изменить">
                        </form>
                        <form action="repair" method="post">
                            <input type="hidden" name="command" value="delete_device_type">
                            <input type="hidden" name="<%=CURRENT_SPARE_PART_ID%>" value="<%=deviceType.getId()%>">
                            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                            <input class="choose-button order-btn" type="submit" value="Удалить">
                        </form>
                    </div>
                </td>
            </tr>
            <% }%>
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
            <form action="main" method="post" id="addDeviceType">
                <input type="hidden" name="command" value="add_device_type">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">

                <div class="f-input">
                    <label class="form-el">Описание</label>
                    <input class="f-form" type="text" name="<%=OBJECT_NAME%>" value="">
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Добавить" form="add_device_type"/>
                </div>
            </form>
        </div>
    </div>
</section>