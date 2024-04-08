<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="it.academy.entities.repair.components.RepairStatus" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.entities.repair.components.RepairCategory" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="it.academy.dto.spare_parts.SparePartOrderDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <div class="forms-container lf">

        <%
            RepairDTO repairDTO = (RepairDTO) request.getAttribute(REPAIR);
            List<RepairStatus> statuses = List.of(RepairStatus.values());
            List<RepairCategory> categoryList = List.of(RepairCategory.values());
            List<BrandDTO> brandDTOList = (List<BrandDTO>) request.getAttribute(BRANDS);
            List<ModelDTO> modelDTOList = (List<ModelDTO>) request.getAttribute(MODELS);
            List<SparePartOrderDTO> orders = (List<SparePartOrderDTO>) request.getAttribute(ORDERS);
        %>

        <div class="lr-container">
        <form class="lr-form" action="main" method="post" id="repair">
                  <input type="hidden" name="command" value="change_repair">
                  <input type="hidden" name="<%=REPAIR_ID%>" value="<%=repairDTO.getId()%>">
                  <input type="hidden" name="<%=BRAND_ID%>" value="<%=repairDTO.getBrandId()%>">
                  <input type="hidden" name="<%=DEVICE_ID%>" value="<%=repairDTO.getDeviceId()%>">
                  <input type="hidden" name="<%=REPAIR_TYPE_ID%>" value="<%=repairDTO.getRepairTypeId()%>">
                    <div class="f-input">
                        <div class="radio-container-rp">
                            <label for="isDeleted">Ремонт удален: </label>
                            <input type="radio" id="isDeleted" name="<%=IS_DELETED%>"  value="<%=repairDTO.isDeleted()%>"/>
                        </div>
                    </div>

                    <div class="f-input">
                        <label class="form-el">Статус ремонта:</label>
                        <select class="f-form " name="<%=REPAIR_STATUS%>" size="1">
                            <%for (RepairStatus status : statuses) {%>
                            <option value="<%=status.name()%>" <%if(status.equals(repairDTO.getStatus())) {%>selected<%}%>>
                                <%=status.getDescription()%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="f-input">
                        <label class="form-el">Категория ремонта:</label>
                        <select class="f-form " name="<%=REPAIR_CATEGORY%>" size="1">
                            <%for (RepairCategory category : categoryList) {%>
                            <option value="<%=category.name()%>" <%if(category.equals(repairDTO.getCategory())) {%>selected<%}%>>
                                <%=category.getDescription()%></option>
                            <%}%>
                        </select>
                    </div>

                    <div class="f-input">
                        <label class="form-el">Бренд:</label>
                        <select class="f-form " name="<%=CURRENT_BRAND_ID%>" size="1" id="select_send">
                            <%for (BrandDTO brandDTO : brandDTOList) {%>
                            <option value="<%=brandDTO.getId()%>"
                                    <%if(brandDTO.getId().equals(repairDTO.getBrandId())) {%>selected<%}%> >
                                <%=brandDTO.getName()%></option>
                            <%}%>
                        </select>
                    </div>

                    <div class="f-input">
                        <label class="form-el">Модель:</label>
                            <select class="f-form " name="<%=MODEL_ID%>" size="1">
                                <%for (ModelDTO modelDTO : modelDTOList) {%>
                                <option value="<%=modelDTO.getId()%>"
                                        <%if(modelDTO.getId().equals(repairDTO.getDeviceId())) {%>selected<%}%> >
                                    <%=modelDTO.getName()%></option>
                                <%}%>
                            </select>
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="sn">Серийный номер:</label>
                        <input class="f-form" required type="text" name="<%=SERIAL_NUMBER%>" value="<%=repairDTO.getSerialNumber()%>" id="sn">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="defectDescription">Причина обращения:</label>
                        <input class="f-form" required type="text" name="<%=DEFECT_DESCRIPTION%>"
                               value="<%=repairDTO.getDefectDescription()%>" id="defectDescription">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="serviceNumber">Номер заказ-наряда:</label>
                        <input class="f-form" required type="text" name="<%=REPAIR_WORKSHOP_REPAIR_NUMBER%>"
                               value="<%=repairDTO.getRepairWorkshopRepairNumber()%>" id="serviceNumber">
                    </div>

                    <div class="f-input">
                        <p>
                            <label class="date-label" for="saleDate">Дата продажи: </label>
                            <div class="date-container">
                                <input class="f-form" required type="date" id="saleDate" name="<%=DATE_OF_SALE%>" value="<%=repairDTO.getDateOfSale()%>"/>
                            </div>
                        </p>
                    </div>

                    <div class="f-input">
                        <p>
                            <label class="date-label" for="startDate">Дата обращения: </label>
                            <div class="date-container">
                                <input class="f-form" type="date" id="startDate" name="<%=START_DATE%>"  value="<%=repairDTO.getStartDate()%>" disabled/>
                            </div>
                        </p>
                    </div>

                    <% if(repairDTO.getStatus().isFinishedStatus()) {%>
                        <div class="f-input">
                            <p>
                                <label class="date-label" for="endDate">Дата завершения: </label>
                                <div class="date-container">
                                    <input class="f-form" required type="date" id="endDate" name="<%=END_DATE%>"  value="<%=repairDTO.getStartDate()%>"/>
                                </div>
                            </p>
                        </div>

                    <div class="f-input">
                        <p>
                            Выполненный ремонт:
                            <label for="repairCode">Код:</label>
                            <input type="text" id="repairCode" name="<%=REPAIR_TYPE_CODE%>"
                                   value="<%=repairDTO.getRepairTypeCode() == null? "" : repairDTO.getRepairTypeCode()%>"/>
                            <label for="repairLevel">Уровень:</label>
                            <input type="text" id="repairLevel" name="<%=REPAIR_TYPE_LEVEL%>"
                                   value="<%=repairDTO.getRepairTypeLevel() == null? "" : repairDTO.getRepairTypeLevel()%>"/>
                            <label for="repairName">Описание:</label>
                            <input type="text" id="repairName" name="<%=REPAIR_TYPE_NAME%>"
                                   value="<%=repairDTO.getRepairTypeName() == null? "" : repairDTO.getRepairTypeName()%>"/>
                        </p>
                    </div>

                        <%if (RepairStatus.DELIVERED.equals(repairDTO.getStatus())) { %>

                            <div class="f-input">
                                <p>
                                    <label for="deliveryDate">Дата завершения: </label>
                                    <input type="date" required id="deliveryDate" name="<%=DELIVERY_DATE%>"  value="<%=repairDTO.getStartDate()%>"/>
                                </p>
                            </div>
                        <% } %>

             <% } %>


                    <div class="f-input">
                        <label class="form-el" for="salesman">Продавец:</label>
                        <input class="f-form" required type="text" name="<%=SALESMAN_NAME%>"
                               value="<%=repairDTO.getSalesmanName()%>" id="salesman">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="salesmanPhone">Телефон продавца:</label>
                        <input class="f-form" required type="tel" name="<%=SALESMAN_PHONE%>"
                               value="<%=repairDTO.getSalesmanPhone()%>" id="salesmanPhone">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="buyerName">Имя владельца:</label>
                        <input class="f-form" required type="text" name="<%=BUYER_NAME%>"
                               value="<%=repairDTO.getBuyerName()%>" id="buyerName">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="buyerSurname">Фамилия владельца:</label>
                        <input class="f-form" required type="text" name="<%=BUYER_SURNAME%>"
                               value="<%=repairDTO.getBuyerSurname()%>" id="buyerSurname">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="buyerPhone">Телефон владельца:</label>
                        <input class="f-form" required type="tel" name="<%=BUYER_PHONE%>"
                               value="<%=repairDTO.getBuyerPhone()%>" id="buyerPhone">
                    </div>

                <% if (orders != null && !orders.isEmpty()) {
                    pageContext.include(CHANGE_SPARE_PART_ORDER_PAGE_PATH);
                }%>

                    <div class="lf-button-container">
                        <input class="choose-button btn-table lf-button" type="submit" value="Заказать запчасти" form="order"/>
                        <input class="choose-button btn-table lf-button" type="submit" value="Сообщить о выполнении" form="repair"/>
                    </div>


            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="repair"/>
                <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
            </div>

        </form>

        <form action="repair" method="post" id="order">
            <input type="hidden" name="command" value="show_order_spare_part">
            <input type="hidden" name="<%=REPAIR_ID%>" value="<%=repairDTO.getId()%>">
            <input type="hidden" name="<%=REPAIR_NUMBER%>" value="<%=repairDTO.getRepairWorkshopRepairNumber()%>">
            <input type="hidden" name="<%=DEVICE_TYPE_ID%>" value="<%=repairDTO.getDeviceTypeId()%>">
        </form>

    </div>
</section>

<script rel="script" src="${pageContext.request.contextPath}/js/repairFrom.js"></script>
<script rel="script" src="${pageContext.request.contextPath}/js/orderFormBehavior.js"></script>

</body>
