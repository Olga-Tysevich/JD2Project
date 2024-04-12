<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.table.resp.ListForPage" %>
<%@ page import="it.academy.dto.device.req.BrandDTO" %>
<%@ page import="it.academy.dto.device.req.ModelDTO" %>
<%@ page import="it.academy.dto.device.req.DeviceTypeDTO" %>
<section>
    <div class="container t-container">

        <%
            ListForPage<ModelDTO> data = (ListForPage<ModelDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            List<ModelDTO> list = data.getList();
            List<BrandDTO> brandList = (List<BrandDTO>) request.getAttribute(BRANDS);
            List<DeviceTypeDTO> deviceTypes = (List<DeviceTypeDTO>) request.getAttribute(DEVICE_TYPES);
        %>

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
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_MODEL%>">
                        <input type="hidden" name="<%=MODEL_ID%>" value="<%=model.getId()%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=model.getIsActive()%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>
                </td>
            </tr>
            <% }%>
        </table>

        <div class="add-form">
            <form action="main" method="post" id="addModel">
                <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_MODEL%>">
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