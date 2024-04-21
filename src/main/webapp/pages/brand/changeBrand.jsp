<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.CHANGE_BRAND" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_BRAND_TABLE" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <%
        int pageNumber = (int) request.getAttribute(PAGE_NUMBER);
        String tablePage = (String) request.getAttribute(PAGE);
        BrandDTO brand = (BrandDTO) request.getAttribute(BRAND);
    %>

    <form action="main" method="post">
        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    </form>

    <div class="forms-container lf">
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="change_brand">
                <input type="hidden" name="<%=COMMAND%>" value="<%=CHANGE_BRAND%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=brand.getId()%>">

                <div class="f-input">
                    <label class="form-el">Активен</label>
                    <label >Активный: </label>
                    <label >да: </label>
                    <input type="radio" name="<%=IS_ACTIVE%>"  value="true" <%if (brand.getIsActive()) {%>checked<%}%> />
                    <label >нет: </label>
                    <input type="radio" name="<%=IS_ACTIVE%>"  value="false" <%if (!brand.getIsActive()) {%>checked<%}%>/>
                </div>


                <div class="f-input">
                    <label class="form-el">Название</label>
                    <input class="f-form" required type="text" name="<%=OBJECT_NAME%>" value="<%=brand.getName()%>">
                </div>
            </form>

            <div class="f-input">
                <%
                    String errorMessage = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
                %>
                <p class="error" id="error" style="display: none"><%=errorMessage%></p>
            </div>


            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="change_brand"/>
                <input class="button" type="submit" value="Отмена" form="cancel"/>
            </div>
        </div>

        <form action="main" method="post" id="cancel">
            <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_PAGE%>">
            <input type="hidden" name="<%=DISPLAY_TABLE_COMMAND%>" value="<%=SHOW_BRAND_TABLE%>">
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
            <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
        </form>

    </div>
</section>
<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>
</body>
