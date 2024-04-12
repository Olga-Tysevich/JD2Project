<%@ page import="static it.academy.utils.Constants.OPEN_START_PAGE" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.device.req.BrandDTO" %>
<%@ page import="static it.academy.servlets.factory.CommandEnum.CHANGE_BRAND" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <%
        int pageNumber = (int) request.getAttribute(PAGE_NUMBER);
        BrandDTO brand = (BrandDTO) request.getAttribute(BRAND);
    %>

    <form action="main" method="post">
        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    </form>

    <div class="forms-container lf">
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="change_brand">
                <input type="hidden" name="<%=COMMAND%>" value="<%=CHANGE_BRAND%>>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="<%=BRAND_ID%>" value="<%=brand.getId()%>">

                <div class="f-input">
                    <label class="form-el">Активен</label>
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=brand.getIsActive()%>"
                           <%if (brand.getIsActive()) {%>checked<%}%>>
                </div>


                <div class="f-input">
                    <label class="form-el">Название</label>
                    <input class="f-form" type="text" name="<%=BRAND_NAME%>" value="<%=brand.getName()%>">
                </div>
            </form>

            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="change_brand"/>
                <input class="button" type="submit" value="Отмена" form="cancel"/>
            </div>
        </div>

        <form action="main" method="post" id="cancel">
            <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_BRAND_TABLE%>">
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
        </form>

    </div>
</section>

</body>
