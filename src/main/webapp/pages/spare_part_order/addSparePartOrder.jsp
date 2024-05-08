<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.ADD_SPARE_PART_ORDER" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="it.academy.dto.spare_part.SparePartDTO" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.OPEN_FORM" %>
<%@ page import="it.academy.dto.spare_part_order.CreateOrderDTO" %>
<%@ page import="it.academy.dto.spare_part_order.OrderItemDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <div class=" container">

        <div class=" forms-container">

            <%
                long repairId = (long) request.getAttribute(OBJECT_ID);
                long modelId = (long) request.getAttribute(MODEL_ID);
                List<SparePartDTO> spareParts = (List<SparePartDTO>) request.getAttribute(SPARE_PARTS);
                String repairNumber = (String) request.getAttribute(REPAIR_NUMBER);
                CreateOrderDTO createOrderDTO = (CreateOrderDTO) request.getAttribute(ORDER_DATA);
            %>


            <div class="form-container r-form">
                <form class="rc-form" action="repair" method="post" id="sparePartOrder">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_SPARE_PART_ORDER%>">
                    <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairId%>" id="repairId">
                    <input type="hidden" name="<%=MODEL_ID%>" value="<%=modelId%>">
                    <div class="f-input">
                        <div class="form-el">Заказ запчастей для ремонта No.<%=repairNumber%></div>
                    </div>

                    <div class="f-input">
                        <label class="form-el">Запчасть:</label>
                        <select class="f-form " name="id" size="0">
                            <%for (SparePartDTO sparePartDTO : spareParts) { %>
                            <option id="spare_part_id" value="<%=sparePartDTO.getId()%>"><%=sparePartDTO.getName()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="f-input">
                        <label class="form-el">Количество:</label>
                        <input id="quantity_id" type="number" name="input_quantity" value="1">
                    </div>

                    <div class="f-input">
                        <input id="add_id" class="choose-button btn-table" type="button" value="Добавить" />
                    </div>


                    <div class="f-input">

                        <table id="order_table">
                            <tr>
                                <th>Наименование</th>
                                <th>Количество</th>
                                <th>Управление</th>
                            </tr>
                            <%  if (createOrderDTO != null && !createOrderDTO.getOrderItems().isEmpty()) {
                                for (OrderItemDTO orderItem: createOrderDTO.getOrderItems()) {%>
                                <tr>
                                    <th><%=orderItem.getName()%></th>
                                    <th><%=orderItem.getQuantity()%></th>
                                    <th>
                                        <input id="delete_btn" type="button" value="-" />
                                    </th>
                                </tr>
                            <%}
                            }%>

                         </table>
                    </div>

                </form>
            </div>

            <%@include file="../forms/errorContainer.jsp"%>

            <div class="button-container">
                <input id="submit_id" class="button" type="button" value="Подтвердить" onclick="send()"/>
                <button class="button"
                        onclick="location.href='<%=String.format(OPEN_FORM, REPAIR, SHOW_UPDATE_REPAIR, repairId)%>'">Отмена</button>
            </div>

        </div>

    </div>
</section>

<script rel="script" src="${pageContext.request.contextPath}/js/OrderFormBehavior.js"></script>
</body>