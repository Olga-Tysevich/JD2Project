<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.req.BrandDTO" %>
<%@ page import="it.academy.dto.req.DeviceTypeDTO" %>
<%@ page import="it.academy.dto.resp.AccountDTO" %>
<%@ page import="it.academy.entities.account.RoleEnum" %>
<%@ page import="it.academy.dto.resp.ModelDTO" %>
<%@ page import="it.academy.dto.resp.ListForPage" %>
<%@ page import="it.academy.dto.resp.ModelListDTO" %>
<%@ page import="static it.academy.servlets.factory.CommandEnum.ADD_MODEL" %>
<%@ page import="static it.academy.servlets.factory.CommandEnum.SHOW_MODEL" %>
<section>
    <div class="container t-container">

        <%
            AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
            RoleEnum role = accountDTO.getRole();
            ListForPage<ModelListDTO> data = (ListForPage<ModelListDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            String currentPage = request.getParameter(PAGE);
            ModelListDTO list = data.getList().get(0);
            List<ModelDTO> models = list.getModels();
            List<BrandDTO> brandList = list.getBrands();
            List<DeviceTypeDTO> deviceTypes = list.getDeviceTypes();
        %>

        <table>
            <tr>
                <th>Название модели</th>
                <th>Бренд</th>
                <th>Тип устройства</th>
                <th>Активно</th>
                <% if (RoleEnum.ADMIN.equals(role)) {%>
                <th class="menu">Управление</th>
                <% } %>
            </tr>

            <% for (ModelDTO model : models) {
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
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=model.getId()%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=currentPage%>">
                        <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=model.getIsActive()%>">
                        <% if (RoleEnum.ADMIN.equals(role)) {%>
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                        <% } %>
                    </form>
                </td>
            </tr>
            <% }%>
        </table>

        <% if (RoleEnum.ADMIN.equals(role)) {%>
        <div class="add-form">
            <form action="main" method="post" id="addModel">
                <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_MODEL%>">
                <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=true%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=currentPage%>">
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
                    <select class="f-form " name="<%=TYPE_ID%>" size="1">
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
        <% }%>
    </div>
</section>