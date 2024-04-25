<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="it.academy.dto.repair.RepairFormDTO" %>
<%@ page import="it.academy.utils.enums.RepairCategory" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <%
            AccountDTO currentAccount = (AccountDTO) request.getSession().getAttribute(ACCOUNT);
            RepairFormDTO repairForm = (RepairFormDTO) request.getAttribute(REPAIR_FORM);
            List<BrandDTO> brands = repairForm.getBrands();
            List<ModelDTO> models = repairForm.getModels();
            List<RepairCategory> categoryList = List.of(RepairCategory.values());
            Long lastBrandId = (long) request.getAttribute(BRAND_ID);
        %>

        <input type="hidden" name="<%=BRAND_ID%>" value="<%=lastBrandId%>">

        <input type="hidden" name="<%=SERVICE_CENTER_ID%>"
               value="<%=currentAccount.getServiceCenterId()%>">

        <div class="f-input">
            <label class="form-el">Категория ремонта:</label>
            <select class="f-form " name="<%=REPAIR_CATEGORY%>" size="1">
                <%for (RepairCategory category : categoryList) {%>
                <option value="<%=category.name()%>">
                    <%=category.getDescription()%></option>
                <%}%>
            </select>
        </div>

        <div class="f-input">
            <label class="form-el">Бренд:</label>
            <select class="f-form " name="<%=SELECTED_BRAND_ID%>" size="1" id="select_send">
                <%for (BrandDTO brandDTO : brands) {%>
                <option value="<%=brandDTO.getId()%>"
                        <%if(brandDTO.getId().equals(lastBrandId)) {%>selected<%}%>>
                    <%=brandDTO.getName()%></option>
                <%}%>
            </select>
        </div>

        <div class="f-input">
            <label class="form-el">Модель:</label>
            <select class="f-form " name="<%=MODEL_ID%>" size="1">
                <%for (ModelDTO modelDTO : models) {%>
                <option value="<%=modelDTO.getId()%>">
                    <%=modelDTO.getName()%></option>
                <%}%>
            </select>
        </div>

        <div class="f-input">
            <label class="form-el" for="sn">Серийный номер:</label>
            <input class="f-form" required type="text" name="<%=SERIAL_NUMBER%>"
                   value="" id="sn">
        </div>

        <div class="f-input">
            <label class="form-el" for="defectDescription">Причина обращения:</label>
            <input class="f-form" required type="text" name="<%=DEFECT_DESCRIPTION%>"
                   value="" id="defectDescription">
        </div>

        <div class="f-input">
            <label class="form-el" for="serviceNumber">Номер заказ-наряда:</label>
            <input class="f-form" required type="text" name="<%=SERVICE_CENTER_REPAIR_NUMBER%>"
                   value="" id="serviceNumber">
        </div>

        <div class="f-input">
            <label class="date-label" for="saleDate">Дата продажи: </label>
            <div class="date-container">
                <input class="f-form" required type="date" id="saleDate" name="<%=DATE_OF_SALE%>"
                       value=""/>
            </div>
        </div>


        <div class="f-input">
            <label class="form-el" for="salesman">Продавец:</label>
            <input class="f-form" required type="text" name="<%=SALESMAN_NAME%>"
                   value="" id="salesman">
        </div>

        <div class="f-input">
            <label class="form-el" for="salesmanPhone">Телефон продавца:</label>
            <input class="f-form" required type="tel" name="<%=SALESMAN_PHONE%>"
                   value="" id="salesmanPhone">
        </div>

        <div class="f-input">
            <label class="form-el" for="buyerName">Имя владельца:</label>
            <input class="f-form" required type="text" name="<%=BUYER_NAME%>"
                   value="" id="buyerName">
        </div>

        <div class="f-input">
            <label class="form-el" for="buyerSurname">Фамилия владельца:</label>
            <input class="f-form" required type="text" name="<%=BUYER_SURNAME%>"
                   value="" id="buyerSurname">
        </div>

        <div class="f-input">
            <label class="form-el" for="buyerPhone">Телефон владельца:</label>
            <input class="f-form" required type="tel" name="<%=BUYER_PHONE%>"
                   value="" id="buyerPhone">
        </div>

<script rel="script" src="${pageContext.request.contextPath}/js/RepairForm.js"></script>