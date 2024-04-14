<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.req.ChangeSparePartDTO" %>
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
                List<ChangeSparePartDTO> spareParts = (List<ChangeSparePartDTO>) request.getAttribute(SPARE_PARTS);
            %>


            <div class="form-container r-form">
                <form class="rc-form" action="repair" method="post" id="addSparePartOrder">
                    <input type="hidden" name="command" value="add_spare_part_order">
                    <input type="hidden" name="<%=REPAIR_ID%>" value="<%=request.getAttribute(REPAIR_ID)%>">
                    <input type="hidden" name="<%=DEVICE_TYPE_ID%>" value="<%=request.getAttribute(DEVICE_TYPE_ID)%>">
                    <%--<input type="hidden" name="page" value="<%=pageNumber%>">--%>
                    <div class="f-input">
                        <div class="form-el">Заказ запчастей для ремонта No.<%=REPAIR_NUMBER%></div>
                    </div>

                    <div class="f-input">
                        <label class="form-el">Запчасть:</label>
                        <select class="f-form " name="id" size="0">
                            <%for (ChangeSparePartDTO sparePartDTO : spareParts) { %>
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
                            </tr>
                        </table>
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

<script rel="script" src="${pageContext.request.contextPath}/js/OrderFormBehavior.js"></script>
</body>