<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.spare_parts.SparePartDTO" %>
<%@ page import="java.util.Map" %>
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
                List<SparePartDTO> spareParts = (List<SparePartDTO>) request.getAttribute(SPARE_PARTS);
                Map<SparePartDTO, Integer> orderedSpareParts = (Map<SparePartDTO, Integer>) request.getAttribute(ORDERED_SPARE_PARTS);
//                Long orderId = request.getAttribute(ORDER_ID) == null? null : (long) request.getAttribute(ORDER_ID);
            %>


            <div class="form-container r-form">
                <form class="rc-form" action="repair" method="post" id="addSparePartOrder">
                    <input type="hidden" name="command" value="add_spare_part_order">
<%--                    <input type="hidden" name="<%=ORDER_ID%>" value="<%=orderId%>">--%>
                    <%--<input type="hidden" name="page" value="<%=pageNumber%>">--%>
                    <div class="f-input">
                        <div class="form-el">Заказ запчастей для ремонта No.<%=REPAIR_NUMBER%></div>
                    </div>

                    <div class="f-input">
                        <label class="form-el">Модель:</label>
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
<%--                        <input class="choose-button btn-table" type="submit" value="Добавить" form="addSparePartOrder"/>--%>
                        <input id="add_id" class="choose-button btn-table" type="button" value="Добавить" />
                    </div>


                    <div class="f-input">

                        <table id="order_table">
                            <tr>
                                <th>Наименование</th>
                                <th>Количество</th>
                            </tr>



                            <% if (orderedSpareParts != null) { %>
                            <% for (SparePartDTO partDTO : orderedSpareParts.keySet()) { %>
                            <tr id="data_id" class="spare_part_input">
                                <td><%=partDTO.getName()%></td>
                                <input  type="text" name="<%=SPARE_PART_NAME%>" value="<%=partDTO.getName()%>" disabled>
                                <td>
                                    <input class="quantity" type="number" name="<%=SPARE_PART_QUANTITY%>" value="<%=orderedSpareParts.get(partDTO)%>">
                                </td>
                            </tr>
                            <% }
                            }%>
                    </div>

                </form>
            </div>

            <div class="button-container">
                <input id="submit_id" class="button" type="button" value="Подтвердить" onclick="send()"/>
                <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
            </div>

        </div>

    </div>
</section>

<script rel="script" src="${pageContext.request.contextPath}/js/formBehavior.js"></script>
</body>