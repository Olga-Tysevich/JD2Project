<%@ page import="static it.academy.utils.constants.Constants.ACCOUNT" %>
<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.REPAIR_TABLE_PAGE_PATH" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.*" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <%@include file="forms/header.jsp"%>

    <div class="container main">

        <div class="menu-container">

                <fieldset class="f1">
                <legend>Текущий аккаунт</legend>
                    <button class="button button-fieldset"
                            onclick="location.href='<%=String.format(OPEN_FORM, ACCOUNT, GET_ACCOUNT, accountDTO.getId())%>'">
                        Изменить данные аккаунта</button>

                    <button class="button button-fieldset"
                            onclick="location.href='<%=String.format(OPEN_FORM, ACCOUNT, GET_SERVICE_CENTER, accountDTO.getServiceCenterId())%>'">
                        Изменить учетные данные</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Устройства</legend>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, GET_MODELS, MODEL_TABLE_PAGE_PATH,
                        pageNumber)%>'">Список моделей</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Запчасти</legend>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, GET_SPARE_PARTS, SPARE_PART_TABLE_PAGE_PATH,
                        pageNumber)%>'">Список запчастей</button>

            </fieldset>

            <fieldset class="f1">
                <legend>Ремонты</legend>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_NEW_OBJECT_FORM_PAGE, REPAIR, GET_NEW_REPAIR)%>'">Создание нового ремонта</button>


                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, GET_REPAIRS, REPAIR_TABLE_PAGE_PATH,
                        FIRST_PAGE)%>'">Список ремонтов</button>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, GET_REPAIR_TYPE_TABLE, REPAIR_TYPE_TABLE_PAGE_PATH,
                        pageNumber)%>'">Список типов ремонта</button>

            </fieldset>

            <fieldset class="f1">
                <legend>Выход</legend>

                <form action="repair" method="get">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=LOGOUT%>">
                    <input class="button button-fieldset" type="submit" value="Выйти из авторизованного режима">
                </form>

            </fieldset>

        </div>


        <div class="table-container">
            <% if (!StringUtils.isBlank(tablePage)) {
                pageContext.include(tablePage);
            }%>
            <%@include file="forms/pagination.jsp"%>
        </div>

    </div>

</section>
<script rel="script" src="${pageContext.request.contextPath}/js/ErrorBlock.js"></script>
</body>