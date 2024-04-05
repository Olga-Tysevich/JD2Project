<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="it.academy.entities.repair.components.RepairStatus" %>
<%@ page import="it.academy.entities.repair.components.RepairType" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <div class=" forms-container">

        <%
            RepairDTO repairDTO = (RepairDTO) request.getAttribute(REPAIR);

        %>

        <form class="rc-form" action="repair" method="post" id="repair">

            <div class="f-input-container">
                  <input type="hidden" name="command" value="change_repair">
                  <input type="hidden" name="<%=REPAIR_ID%>" value="<%=repairDTO.getId()%>">
<%--                  <input type="hidden" name="<%=REPAIR_WORKSHOP_ID%>" value="<%=repairDTO.getId()%>">--%>
                  <input type="hidden" name="<%=REPAIR_STATUS%>" value="<%=repairDTO.getStatus()%>">
                  <input type="hidden" name="<%=DEVICE_ID%>" value="<%=repairDTO.getDevice().getId()%>">
                   <%if (repairDTO.getType() != null) {%>
                        <input type="hidden" name="<%=REPAIR_TYPE_ID%>" value="<%=repairDTO.getType().getId()%>">
                   <% } %>
                    <div class="f-input">
                        <p>
                            <label for="isDeleted">Ремонт удален: </label>
                            <input type="checkbox" id="isDeleted" name="<%=IS_DELETED%>"  value="<%=repairDTO.isDeleted()%>"/>
                        </p>
                    </div>


                    <div class="f-input">
                        <label class="form-el" for="category">Категория ремонта:</label>
                        <input type="hidden" name="<%=REPAIR_CATEGORY%>" value="<%=repairDTO.getCategory()%>"/>
                        <input class="f-form" required type="text"
                               value="<%=repairDTO.getCategory().getDescription()%>" id="category">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="brand">Бренд:</label>

                        <input class="f-form" required type="text" name="<%=BRAND_NAME%>"
                               value="<%=repairDTO.getDevice().getModel().getBrandName()%>" id="brand">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="model">Модель:</label>
                        <input class="f-form" required type="text" name="<%=REPAIR_MODEL_NAME%>"
                               value="<%=repairDTO.getDevice().getModel().getName()%>" id="model">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="sn">Серийный номер:</label>
                        <input class="f-form" required type="text" name="<%=SERIAL_NUMBER%>" value="<%=repairDTO.getDevice().getSerialNumber()%>" id="sn">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="defectDescription">Причина обращения:</label>
                        <input class="f-form" required type="text" name="<%=DEFECT_DESCRIPTION%>"
                               value="<%=repairDTO.getDefectDescription()%>" id="defectDescription" disabled>
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="serviceNumber">Номер заказ-наряда:</label>
                        <input class="f-form" required type="text" name="<%=REPAIR_WORKSHOP_REPAIR_NUMBER%>"
                               value="<%=repairDTO.getRepairWorkshopRepairNumber()%>" id="serviceNumber">
                    </div>

                    <div class="f-input">
                        <p>
                            <label for="saleDate">Дата продажи: </label>
                            <input type="date" id="saleDate" name="<%=DATE_OF_SALE%>" value="<%=repairDTO.getDevice().getDateOfSale()%>"/>
                        </p>
                    </div>

                    <div class="f-input">
                        <p>
                            <label for="startDate">Дата обращения: </label>
                            <input type="date" id="startDate" name="<%=START_DATE%>"  value="<%=repairDTO.getStartDate()%>"/>
                        </p>
                    </div>

                    <% if(repairDTO.getStatus().isFinishedStatus()) {%>
                        <div class="f-input">
                            <p>
                                <label for="endDate">Дата завершения: </label>
                                <input type="date" id="endDate" name="<%=END_DATE%>"  value="<%=repairDTO.getStartDate()%>"/>
                            </p>
                        </div>

                    <div class="f-input">
                        <p>
                            Выполненный ремонт:
                            <label for="repairCode">Код:</label>
                            <input type="text" id="repairCode" name="<%=REPAIR_TYPE_CODE%>"  value="<%=repairDTO.getType().getCode()%>"/>
                            <label for="repairLevel">Уровень:</label>
                            <input type="text" id="repairLevel" name="<%=REPAIR_TYPE_LEVEL%>"  value="<%=repairDTO.getType().getLevel()%>"/>
                            <label for="repairName">Описание:</label>
                            <input type="text" id="repairName" name="<%=REPAIR_TYPE_NAME%>"  value="<%=repairDTO.getType().getName()%>"/>
                        </p>
                    </div>

                        <%if (RepairStatus.DELIVERED.equals(repairDTO.getStatus())) { %>

                            <div class="f-input">
                                <p>
                                    <label for="deliveryDate">Дата завершения: </label>
                                    <input type="date" id="deliveryDate" name="<%=DELIVERY_DATE%>"  value="<%=repairDTO.getStartDate()%>"/>
                                </p>
                            </div>
                        <% } %>

                    <% } %>

                    <div class="f-input">
                        <label class="form-el" for="salesman">Продавец:</label>
                        <input class="f-form" required type="text" name="<%=SALESMAN_NAME%>"
                               value="<%=repairDTO.getDevice().getSalesmanName()%>" id="salesman" disabled>
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="salesmanPhone">Телефон продавца:</label>
                        <input class="f-form" required type="tel" name="<%=SALESMAN_PHONE%>"
                               value="<%=repairDTO.getDevice().getSalesmanPhone()%>" id="salesmanPhone" disabled>
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="buyerName">Имя владельца:</label>
                        <input class="f-form" required type="text" name="<%=BUYER_NAME%>"
                               value="<%=repairDTO.getDevice().getBuyerName()%>" id="buyerName" disabled>
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="buyerSurname">Фамилия владельца:</label>
                        <input class="f-form" required type="text" name="<%=BUYER_SURNAME%>"
                               value="<%=repairDTO.getDevice().getBuyerSurname()%>" id="buyerSurname" disabled>
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="buyerPhone">Телефон владельца:</label>
                        <input class="f-form" required type="tel" name="<%=BUYER_PHONE%>"
                               value="<%=repairDTO.getDevice().getBuyerPhone()%>" id="buyerPhone" disabled>
                    </div>

                    <div class="f-input">
<%--                        for spare parts--%>
                    </div>

                    <div class="button-container">
                        <input class="choose-button btn-table" type="submit" value="Заказать запчасти" form="repair"/>
                        <input class="choose-button btn-table" type="submit" value="Сообщить о выполнении" form="repair"/>
                    </div>

                </div>

            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="repair"/>
                <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
            </div>

        </form>

    </div>
</section>
</body>
