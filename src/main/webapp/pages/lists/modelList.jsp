<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.BrandListDTO" %>
<%@ page import="it.academy.dto.BrandDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.ModelDTO" %>
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
                List<ModelDTO> models = (List<ModelDTO>) request.getAttribute(MODELS);

            %>

            <div class="form-container r-form">
                <form class="rc-form" action="models" method="post" id="findModels">
                    <input type="hidden" name="command" value="show_repair">
                    <%--                        <input type="hidden" name="page" value="<%=pageNumber%>">--%>
                    <div class="f-input">
                        <label class="form-el">Модель:</label>
                        <select class="f-form " name="brand_id" size="0">
                            <%for (ModelDTO model : models) { %>
                            <option value="<%=model.getId()%>"><%=model.getName()%></option>
                            <% } %>
                        </select>
                    </div>
                </form>
            </div>

            <div class="button-container">
                <input class="button" type="submit" value="Подтвердить" form="findModels"/>
                <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
            </div>

        </div>

    </div>
</section>
</body>
<%--<script rel="script" src="${pageContext.request.contextPath}/js/formBehavior.js"></script>--%>
