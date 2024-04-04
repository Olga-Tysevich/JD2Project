<%@ page import="static it.academy.utils.Constants.*" %>
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

            RepairPageDataDTO data = (RepairPageDataDTO) request.getAttribute(REPAIR_PAGE_DATA);
            BrandDTO currentBrand = data.getCurrentBrand();

        %>

            <div class="form-container r-form">
                    <form class="rc-form" action="models" method="post" id="findModels">
                        <input type="hidden" name="command" value="show_repair">
<%--                        <input type="hidden" name="page" value="<%=pageNumber%>">--%>
                        <div class="f-input">
                        <label class="form-el">Бренд:</label>
                        <select class="f-form " name="brand_id" size="0">
                            <option value="<%=currentBrand.getId()%>" selected><%=currentBrand.getName()%></option>
                            <%for (BrandDTO brand : data.getBrands()) { %>
                            <option value="<%=brand.getId()%>"><%=brand.getName()%></option>
                            <% } %>
                        </select>
                        <input class="button light" type="submit"  value="Найти модели" form="findModels">
                        </div>
                    </form>

                <form class="rc-form" action="repair" method="post" id="repair">
                <div class="f-input-container">
                        <input type="hidden" name="command" value="add_repair">

                    <div class="f-input">
                        <label class="form-el">Модель:</label>
                        <select class="f-form " name="model" size="0">
                            <option value="" selected>Выберите вариант</option>
                            <%for (ModelDTO model : data.getModels()) { %>
                            <option value="<%=model.getId()%>"><%=model.getName()%></option>
                            <% }%>
                        </select>
                    </div>

                        <div class="f-input">
                            <label class="form-el">Категория ремонта:</label>
                            <select class="f-form " name="category" size="0">
                                <option value=»» selected>Выберите вариант</option>
                                <%for (RepairCategoryDTO category : data.getCategories()) { %>
                                <option value="<%=category.getId()%>"><%=category.getName()%></option>
                                <% } %>
                            </select>
                        </div>
                    </div>


                <div class="f-input-container">
                    <div class="f-input">
                        <label class="form-el" for="sn">Серийный номер:</label>
                        <input class="f-form" required type="text" name="sn" placeholder="Введите серийный номер" value="" id="sn">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="defectDescription">Причина обращения:</label>
                        <input class="f-form" required type="text" name="defectDescription" placeholder="Введите дефект со слов клиента" value="" id="defectDescription">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="serviceNumber">Номер заказ-наряда:</label>
                        <input class="f-form" required type="text" name="serviceNumber" placeholder="Введите номер ремонта АСЦ" value="" id="serviceNumber">
                    </div>

                    <div class="f-input">
                        <p>
                            <label for="saleDate">Дата продажи: </label>
                            <input type="date" id="saleDate" name="saleDate"/>
                        </p>
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="salesman">Продавец:</label>
                        <input class="f-form" required type="text" name="salesman" placeholder="Введите название продавца" value="" id="salesman">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="salesmanPhone">Телефон продавца:</label>
                        <input class="f-form" required type="tel" name="salesmanPhone" placeholder="Введите телефон продавца" value="" id="salesmanPhone">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="buyerName">Имя владельца:</label>
                        <input class="f-form" required type="text" name="buyerName" placeholder="Введите имя покупателя" value="" id="buyerName">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="buyerSurname">Фамилия владельца:</label>
                        <input class="f-form" required type="text" name="buyerSurname" placeholder="Введите фамилию покупателя" value="" id="buyerSurname">
                    </div>

                    <div class="f-input">
                        <label class="form-el" for="buyerPhone">Телефон владельца:</label>
                        <input class="f-form" required type="tel" name="buyerPhone" placeholder="Введите телефон покупателя" value="" id="buyerPhone">
                    </div>

                </div>

<%--                <select class="f-form " name="identifiedDefect" size="0">--%>
<%--                    <%for (DefectDTOReq defect : defects) { %>--%>
<%--                    <option selected value="<%=defect.getId()%>"><%=defect.getDescription()%></option>--%>
<%--                    <% } %>--%>
<%--                </select>--%>

            </div>

            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="repair"/>
                <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
            </div>

        </form>

    </div>
</section>
</body>
<%--<script rel="script" src="${pageContext.request.contextPath}/js/formBehavior.js"></script>--%>
