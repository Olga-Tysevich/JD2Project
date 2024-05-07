<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.GET_NEW_ACCOUNT" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.GET_ACCOUNT_TABLE" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.ACCOUNT_TABLE_PAGE_PATH" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.REPAIR_TABLE_PAGE_PATH" %>
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
                        onclick="location.href='<%=String.format(OPEN_NEW_OBJECT_FORM_PAGE, ACCOUNT, GET_NEW_ACCOUNT)%>'">Добавить аккаунт</button>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, GET_ACCOUNT_TABLE, ACCOUNT_TABLE_PAGE_PATH,
                        FIRST_PAGE)%>'">Список аккаунтов</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Сервисные центры</legend>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_NEW_OBJECT_FORM_PAGE, SERVICE_CENTER, GET_NEW_SERVICE_CENTER)%>'">
                    Добавить сервисный центр</button>
                <form  action="account" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=GET_SERVICE_CENTERS%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=SERVICE_CENTER_TABLE_PAGE_PATH%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=FIRST_PAGE%>">
                    <input class="button button-fieldset" type="submit" value="Список сервисных центров"/>
                </form>
            </fieldset>

            <fieldset class="f1">
                <legend>Текущий аккаунт</legend>
                <form  action="account" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=GET_SERVICE_CENTER%>">
<%--                    <input type="hidden" name="<%=OBJECT_ID%>>" value="<%=accountDTO.getServiceCenter().getId()%>">--%>
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    <input class="button button-fieldset" type="submit" value="Изменить учетные данные"/>
                </form>

<%--                <button class="button button-fieldset"--%>
<%--                        onclick="location.href='<%=String.format(OPEN_REPAIR_WORKSHOP_PAGE, FIRST_PAGE)%>'"> Изменить учетные данные </button>--%>
            </fieldset>

            <fieldset class="f1">
                <legend>Устройства</legend>

                <button class="button button-fieldset" onclick="">Добавить модель</button>


                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, GET_MODELS, MODEL_TABLE_PAGE_PATH, pageNumber)%>'">Список моделей</button>

                <button class="button button-fieldset" onclick="">Добавить бренд</button>

<%--                <%@include file="brand/AddBrand.jsp"%>--%>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, GET_BRANDS, BRAND_TABLE_PAGE_PATH, pageNumber)%>'">Список брендов</button>

                <form  action="brands" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_DEVICE_TYPE_TABLE%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=DEVICE_TYPE_TABLE_PAGE_PATH%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=FIRST_PAGE%>">
                    <input class="button button-fieldset" type="submit" value="Список типов устройств"/>
                </form>
            </fieldset>

            <fieldset class="f1">
                <legend>Запчасти</legend>
                <form  action="brands" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_NEW_SPARE_PART%>">
                    <input class="button button-fieldset" type="submit" value="Добавить запчасть"/>
                </form>
                <form  action="brands" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=GET_SPARE_PARTS%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=SPARE_PART_TABLE_PAGE_PATH%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=FIRST_PAGE%>">
                    <input class="button button-fieldset" type="submit" value="Список запчастей"/>
                </form>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_SPARE_PART_ORDERS_TABLE_PAGE, FIRST_PAGE)%>'"> Список заказов запчатей</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Ремонты</legend>

                <form action="repair" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=GET_REPAIRS%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=REPAIR_TABLE_PAGE_PATH%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=FIRST_PAGE%>">
                    <input class="button button-fieldset" type="submit" value="Список ремонтов">
                </form>

                <form  action="brands" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=GET_REPAIR_TYPE_TABLE%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=REPAIR_TYPE_TABLE_PAGE_PATH%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=FIRST_PAGE%>">
                    <input class="button button-fieldset" type="submit" value="Список типов ремонта"/>
                </form>
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

            <%if (!StringUtils.isBlank(form)) {
                pageContext.include(form);
            }%>

            <% if (!StringUtils.isBlank(tablePage)) {
                pageContext.include(tablePage);
            }%>

            <%@include file="forms/pagination.jsp"%>

        </div>

    </div>

</section>
<script rel="script" src="${pageContext.request.contextPath}/js/ErrorBlock.js"></script>
</body>