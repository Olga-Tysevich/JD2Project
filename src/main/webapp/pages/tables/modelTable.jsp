<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="it.academy.dto.device.DeviceTypeDTO" %>
<section>
    <div class="container t-container">

        <%
            ListForPage<ModelDTO> data = (ListForPage<ModelDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            int maxPageNumber = data.getMaxPageNumber();
            List<ModelDTO> list = data.getList();
            List<BrandDTO> brandList = (List<BrandDTO>) request.getAttribute(BRANDS);
            List<DeviceTypeDTO> deviceTypes = (List<DeviceTypeDTO>) request.getAttribute(DEVICE_TYPES);
        %>


<%--        <div class="radio-container">--%>
<%--            <form class="status-form" action="main" method="post" id="find_spare_parts">--%>
<%--                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">--%>
<%--                <input type="hidden" name="command" value="show_spare_part_table">--%>
<%--            </form>--%>
<%--        </div>--%>


        <table>
            <tr>
                <th>Название модели</th>
                <th>Бренд</th>
                <th>Тип устройства</th>
                <th>Активно</th>
                <th class="menu">Управление</th>
            </tr>

            <% for (ModelDTO model : list) {
            %>
            <tr class="t-tr">
                <td class="code"><%=model.getName()%></td>
                <td class="code"><%=model.getBrandName()%></td>
                <td class="code"><%=model.getDeviceTypeName()%></td>
                <td class="code">
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=model.getIsActive()%>"
                           <%if (model.getIsActive()) {%>checked<%}%> disabled>
                </td>
                <td class="code">
                    <form action="repair" method="post" >
                        <input type="hidden" name="command" value="show_model">
                        <input type="hidden" name="<%=MODEL_ID%>" value="<%=model.getId()%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=model.getIsActive()%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>
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
                    <input type="hidden" name="command" value="show_model_table">
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
                    <input type="hidden" name="command" value="show_model_table">
                    <%int nextPage = pageNumber + 1;%>
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=nextPage%>">
                    <input class="button light" type="submit" name="button" value="Следующая">
                </form>
                <% } %>

            </div>
        </div>
        <% } %>

        <div class="add-form">
            <form action="main" method="post" id="addModel">
                <input type="hidden" name="command" value="add_model">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">

                <div class="f-input">
                    <label class="form-el">Бренд:</label>
                    <select class="f-form " name="<%=BRAND_ID%>" size="1">
                        <%for (BrandDTO brandDTO : brandList) {%>
                        <option value="<%=brandDTO.getId()%>"><%=brandDTO.getName()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">Тип устройства:</label>
                    <select class="f-form " name="<%=DEVICE_TYPE_ID%>" size="1">
                        <%for (DeviceTypeDTO deviceTypeDTO : deviceTypes) {%>
                        <option value="<%=deviceTypeDTO.getId()%>"><%=deviceTypeDTO.getName()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">Описание</label>
                    <input class="f-form" type="text" name="<%=MODEL_NAME%>" value="">
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Добавить" form="addModel"/>
                </div>
            </form>
        </div>
    </div>
</section>