<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.FIRST_PAGE" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.req.repair.RepairCategoryDTOReq" %>
<%@ page import="it.academy.dto.req.device.BrandDTOReq" %>
<%@ page import="it.academy.dto.req.device.ModelDTOReq" %>
<%@ page import="it.academy.dto.req.device.DefectDTOReq" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <div class="container main">

<%--        <div class="menu-container">--%>

<%--    <div class=" forms-container">--%>

        <%
            int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
            int maxPageNumber = request.getAttribute(MAX_PAGE) == null ? FIRST_PAGE : (int) request.getAttribute(MAX_PAGE);
            List<RepairCategoryDTOReq> categories = (List<RepairCategoryDTOReq>) request.getAttribute(REPAIR_CATEGORIES);
            List<BrandDTOReq> brands = (List<BrandDTOReq>) request.getAttribute(BRANDS);
            List<ModelDTOReq> models = (List<ModelDTOReq>) request.getAttribute(MODELS);
//            List<DefectDTOReq> defects = (List<DefectDTOReq>) request.getAttribute(DEFECTS);

        %>

        <form action="repair" method="post" id="repair">

            <div class="form-container r-form">
                <div class="f-input-container">
                        <input type="hidden" name="command" value="add_repair">

                    <div class="f-input">
                        <label class="form-el">Категория ремонта:</label>
                        <select class="f-form " name="category" size="0">
                            <option value=»» selected>Выберите вариант</option>
                            <%for (RepairCategoryDTOReq category : categories) { %>
                                <option selected value="<%=category.getId()%>"><%=category.getName()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="f-input">
                        <form action="repair" method="post" id="findBrand">
                        <label class="form-el" for="brand">Брэнд:</label>
                        <input class="f-form" type="submit"  name="sn" placeholder="Введите серийный номер" value="" id="brand">
                        </form>
                    </div>

<%--                    <div class="f-input">--%>
<%--                        <label class="form-el">Брэнд:</label>--%>
<%--                        <select class="f-form " name="brand" size="0">--%>
<%--                            <%for (BrandDTOReq brand : brands) { %>--%>
<%--                            <option selected value="<%=brand.getId()%>"><%=brand.getName()%></option>--%>
<%--                            <% } %>--%>
<%--                        </select>--%>
<%--                    </div>--%>

<%--                    <div class="f-input">--%>
<%--                        <label class="form-el">Модель:</label>--%>
<%--                        <select class="f-form " name="model" size="0">--%>
<%--                            <%for (ModelDTOReq model : models) { %>--%>
<%--                            <option selected value="<%=model.getId()%>"><%=model.getName()%></option>--%>
<%--                            <% } %>--%>
<%--                        </select>--%>
<%--                    </div>--%>

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

                </div>

<%--                <select class="f-form " name="identifiedDefect" size="0">--%>
<%--                    <%for (DefectDTOReq defect : defects) { %>--%>
<%--                    <option selected value="<%=defect.getId()%>"><%=defect.getDescription()%></option>--%>
<%--                    <% } %>--%>
<%--                </select>--%>


            </div>

            <div class="button-container">
                <input class="button" type="submit" value="Сохранить"/>
                <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
            </div>

        </form>

<%--    </div>--%>

<%--        </div>--%>

<%--        <div class="table-container">--%>
<%--&lt;%&ndash;            <%if (pageForDisplay != null) {&ndash;%&gt;--%>
<%--&lt;%&ndash;                pageContext.include(pageForDisplay);&ndash;%&gt;--%>
<%--&lt;%&ndash;            }&ndash;%&gt;--%>
<%--&lt;%&ndash;            %>&ndash;%&gt;--%>
<%--        </div>--%>

    </div>
</section>
</body>
<%--<script rel="script" src="${pageContext.request.contextPath}/js/formBehavior.js"></script>--%>
