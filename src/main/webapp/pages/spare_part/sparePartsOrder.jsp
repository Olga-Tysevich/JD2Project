<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.resp.SparePartDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.ADD_SPARE_PART_ORDER" %>
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

                int pageNumber = (int) request.getAttribute(PAGE_NUMBER);
                List<SparePartDTO> spareParts = (List<SparePartDTO>) request.getAttribute(SPARE_PARTS);
            %>


            <div class="form-container r-form">
                <form class="rc-form" action="repair" method="post" id="addSparePartOrder">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_SPARE_PART_ORDER%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    <input type="hidden" name="<%=OBJECT_ID%>" value="<%=request.getAttribute(OBJECT_ID)%>">
                    <input type="hidden" name="<%=DEVICE_TYPE_ID%>" value="<%=request.getAttribute(DEVICE_TYPE_ID)%>">
                    <input type="hidden" name="page" value="<%=pageNumber%>">
                    <div class="f-input">
                        <div class="form-el">Заказ запчастей для ремонта No.<%=REPAIR_NUMBER%></div>
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