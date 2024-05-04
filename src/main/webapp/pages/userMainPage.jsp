<%@ page import="static it.academy.utils.constants.Constants.ACCOUNT" %>
<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.utils.fiterForSearch.EntityFilter" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.REPAIR_TABLE_PAGE_PATH" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.*" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="it.academy.utils.fiterForSearch.FilterManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>
    <div class=" container">

                <%
                    AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
                    String serviceCenter = accountDTO.getServiceCenterName();
                    String userEmail = accountDTO.getEmail();
                    int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
                    String tablePage = StringUtils.defaultIfBlank((String) request.getAttribute(PAGE), StringUtils.EMPTY);
                    String command = StringUtils.defaultIfBlank((String) request.getAttribute(COMMAND), SHOW_MAIN_PAGE.name());
                    String lastInput = StringUtils.defaultIfBlank((String) request.getAttribute(USER_INPUT), StringUtils.EMPTY);
                    String lastFilter = StringUtils.defaultIfBlank((String) request.getAttribute(FILTER), StringUtils.EMPTY);
                    String currentPage = String.format(OPEN_TABLE_PAGE_BY_FILTER, command, tablePage, pageNumber, lastFilter, lastInput);
                    request.getSession().setAttribute(LAST_PAGE, currentPage);
                %>

        <div class="header-container">
            <div class="header-el">
                <p><%=serviceCenter%></p>
            </div>
            <div class="header-el">
                <p><%=userEmail%></p>
            </div>
        </div>

        <% if (!tablePage.isBlank()) {
            List<EntityFilter> filters = FilterManager.getFiltersForPage(tablePage);
        %>
        <form action="main" method="post" id="search">
            <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
            <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
            <input class="search" type="search" name="<%=USER_INPUT%>" placeholder="Поиск" <%if (!lastInput.isBlank()) {%>value="<%=lastInput%>"<%}%>>
            <select class="filter" name="<%=FILTER%>" size="1">
                <% if (filters != null) {
                    for (EntityFilter filter: filters) { %>
                <option value="<%=filter.getFieldName()%>" <%if (filter.getFieldName().equals(lastFilter)) {%>selected<%}%>>
                    <%=filter.getDescription()%></option>
                <% }
                } %>
            </select>
            <input class="button light" type="submit" value="Найти" form="search">
        </form>
        <% } %>
    </div>

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
                        onclick="location.href='<%=String.format(OPEN_NEW_OBJECT_FORM_PAGE, GET_NEW_REPAIR)%>'">Создание нового ремонта</button>


                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, GET_REPAIRS, REPAIR_TABLE_PAGE_PATH,
                        FIRST_PAGE)%>'">Список ремонтов</button>

                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_TABLE_PAGE, GET_REPAIR_TYPE_TABLE, REPAIR_TYPE_TABLE_PAGE_PATH,
                        pageNumber)%>'">Список типов ремонта</button>

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
            <% if (!StringUtils.isBlank(tablePage)) {
                pageContext.include(tablePage);
            }%>
            <%@include file="forms/pagination.jsp"%>
        </div>

    </div>

</section>
<script rel="script" src="${pageContext.request.contextPath}/js/ErrorBlock.js"></script>
</body>