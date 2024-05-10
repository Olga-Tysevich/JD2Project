<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_ADD_ACCOUNT" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_ACCOUNT_TABLE" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.ACCOUNT_TABLE_PAGE_PATH" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.REPAIR_TABLE_PAGE_PATH" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.*" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <%@include file="forms/header.jsp"%>

    <div class="container main" id="main_container_id">

        <div class="menu-container">

            <fieldset class="f1">
                <legend>Компания</legend>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_NEW_OBJECT_FORM_PAGE, ACCOUNT, SHOW_ADD_ACCOUNT)%>'">Добавить аккаунт</button>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, SHOW_ACCOUNT_TABLE, ACCOUNT_TABLE_PAGE_PATH,
                        FIRST_PAGE)%>'">Список аккаунтов</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Сервисные центры</legend>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_NEW_OBJECT_FORM_PAGE, SERVICE_CENTER, SHOW_ADD_SERVICE_CENTER)%>'">
                    Добавить сервисный центр</button>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, SHOW_SERVICE_CENTER_TABLE, SERVICE_CENTER_TABLE_PAGE_PATH,
                        FIRST_PAGE)%>'">Список сервисных центров</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Текущий аккаунт</legend>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_FORM, ACCOUNT, SHOW_UPDATE_ACCOUNT, accountDTO.getId())%>'">Изменить учетные данные</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Устройства</legend>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_NEW_OBJECT_FORM_PAGE, MODEL, SHOW_ADD_MODEL)%>'">Добавить модель</button>


                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, SHOW_MODEL_TABLE, MODEL_TABLE_PAGE_PATH, pageNumber)%>'">Список моделей</button>


                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_NEW_OBJECT_FORM_PAGE, BRAND, SHOW_ADD_BRAND)%>'">Добавить бренд</button>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, SHOW_BRAND_TABLE, BRAND_TABLE_PAGE_PATH, pageNumber)%>'">Список брендов</button>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_NEW_OBJECT_FORM_PAGE, DEVICE_TYPE, SHOW_ADD_DEVICE_TYPE)%>'">Добавить тип устройства</button>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, SHOW_DEVICE_TYPE_TABLE, DEVICE_TYPE_TABLE_PAGE_PATH, pageNumber)%>'">
                        Список типов устройств</button>

            </fieldset>

            <fieldset class="f1">
                <legend>Запчасти</legend>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_NEW_OBJECT_FORM_PAGE, SPARE_PART, SHOW_ADD_SPARE_PART)%>'">Добавить запчасть</button>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, SHOW_SPARE_PART_TABLE, SPARE_PART_TABLE_PAGE_PATH, pageNumber)%>'">
                    Список запчастей</button>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, SHOW_SPARE_PART_ORDER_TABLE, SPARE_PART_ORDER_TABLE_PAGE_PATH, pageNumber)%>'">
                    Список заказов запчатей</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Ремонты</legend>

                <form action="repair" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_REPAIR_TABLE%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=REPAIR_TABLE_PAGE_PATH%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=FIRST_PAGE%>">
                    <input class="button button-fieldset" type="submit" value="Список ремонтов">
                </form>


                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_NEW_OBJECT_FORM_PAGE, REPAIR_TYPE, SHOW_ADD_REPAIR_TYPE)%>'">Добавить тип ремонта</button>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, SHOW_REPAIR_TYPE_TABLE, REPAIR_TYPE_TABLE_PAGE_PATH, pageNumber)%>'">
                    Список типов ремонта</button>

            </fieldset>

            <fieldset class="f1">
                <legend>Выход</legend>

                <form action="repair" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=LOGOUT%>">
                    <input class="button button-fieldset" type="submit" value="Выйти из авторизованного режима">
                </form>

            </fieldset>

        </div>

        <div class="table-container">

            <%if (StringUtils.isNotBlank(form)) {
                pageContext.include(form);
            }%>

            <% if (StringUtils.isNotBlank(tablePage)) {
                pageContext.include(tablePage);
            }%>

            <%@include file="forms/pagination.jsp"%>

        </div>

    </div>

</section>
<script rel="script" src="${pageContext.request.contextPath}/js/ErrorBlock.js"></script>
</body>