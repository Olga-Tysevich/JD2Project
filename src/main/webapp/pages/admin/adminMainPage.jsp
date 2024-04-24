<%@ page import="static it.academy.utils.constants.Constants.ACCOUNT" %>
<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.utils.fiterForSearch.EntityFilter" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_NEW_ACCOUNT" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_ACCOUNT_TABLE" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.ACCOUNT_TABLE_PAGE_PATH" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.REPAIR_TABLE_PAGE_PATH" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.*" %>
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
                    String userEmail = accountDTO.getEmail();
                    ListForPage list = request.getAttribute(LIST_FOR_PAGE) != null?
                            (ListForPage) request.getAttribute(LIST_FOR_PAGE) : new ListForPage();
                    int pageNumber = list.getPageNumber() == null ? FIRST_PAGE : list.getPageNumber();
                    int maxPageNumber = list.getMaxPageNumber() == null ? FIRST_PAGE : list.getMaxPageNumber();
                    List<EntityFilter> filters = list.getFiltersForPage();
                    String tablePage = list.getPage();
                    String command = list.getCommand();
                %>

        <div class="header-container">
            <%
                String errorMessage = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
            %>
            <p class="error" id="error" style="display: none"><%=errorMessage%></p>
            <div class="header-el">
                <p><%=userEmail%></p>
            </div>
        </div>

        <% if (tablePage != null) { %>
        <form action="main" method="post" id="search">

            <input type="text" name="<%=COMMAND%>" value="<%=command%>">
            <input type="text" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
            <input type="text" name="<%=PAGE%>" value="<%=tablePage%>">

            <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
            <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
            <input class="search" type="search" name="<%=USER_INPUT%>" placeholder="Поиск">
            <select class="filter" name="<%=FILTER%>" size="1">
                <% if (filters != null) {
                    for (EntityFilter filter: filters) { %>
                <option selected value="<%=filter.getFieldName()%>"><%=filter.getDescription()%></option>
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
                <legend>Компания</legend>
                <form action="account" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_NEW_ACCOUNT%>">
                    <input class="button button-fieldset" type="submit" value="Добавить аккаунт"/>
                </form>

                <form  action="account" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_ACCOUNT_TABLE%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=ACCOUNT_TABLE_PAGE_PATH%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=FIRST_PAGE%>">
                    <input class="button button-fieldset" type="submit" value="Список аккаунтов"/>
                </form>
            </fieldset>

            <fieldset class="f1">
                <legend>Сервисные центры</legend>
                <form  action="account" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_SERVICE_CENTER%>">
                    <input class="button button-fieldset" type="submit" value="Добавить сервисный центр"/>
                </form>
                <form  action="account" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_SERVICE_CENTER_TABLE%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=SERVICE_CENTER_TABLE_PAGE_PATH%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=FIRST_PAGE%>">
                    <input class="button button-fieldset" type="submit" value="Список сервисных центров"/>
                </form>
            </fieldset>

            <fieldset class="f1">
                <legend>Текущий аккаунт</legend>
                <form  action="account" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_SERVICE_CENTER%>">
<%--                    <input type="hidden" name="<%=OBJECT_ID%>>" value="<%=accountDTO.getServiceCenter().getId()%>">--%>
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    <input class="button button-fieldset" type="submit" value="Изменить учетные данные"/>
                </form>

<%--                <button class="button button-fieldset"--%>
<%--                        onclick="location.href='<%=String.format(OPEN_REPAIR_WORKSHOP_PAGE, FIRST_PAGE)%>'"> Изменить учетные данные </button>--%>
            </fieldset>

            <fieldset class="f1">
                <legend>Устройства</legend>
                <form  action="brands" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_NEW_MODEL%>">
                    <input class="button button-fieldset" type="submit" value="Добавить модель"/>
                </form>

                <form  action="brands" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_MODEL_TABLE%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=MODEL_TABLE_PAGE_PATH%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=FIRST_PAGE%>">
                    <input class="button button-fieldset" type="submit" value="Список моделей"/>
                </form>
                <form  action="brands" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_BRAND_TABLE%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=BRAND_TABLE_PAGE_PATH%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    <input class="button button-fieldset" type="submit" value="Список брендов"/>
                </form>
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
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_SPARE_PART_TABLE%>">
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
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_REPAIR_TABLE%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=REPAIR_TABLE_PAGE_PATH%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=FIRST_PAGE%>">
                    <input class="button button-fieldset" type="submit" value="Список ремонтов">
                </form>

                <form  action="brands" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_REPAIR_TYPE_TABLE%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=REPAIR_TYPE_TABLE_PAGE_PATH%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=FIRST_PAGE%>">
                    <input class="button button-fieldset" type="submit" value="Список типов ремонта"/>
                </form>
            </fieldset>

        </div>

        <div class="table-container">
            <% if (tablePage != null) {
                pageContext.include(tablePage);
                if (maxPageNumber > 1) {%>
            <div class="footer">
                <div class="button-container">
                    <%if (pageNumber != FIRST_PAGE) { %>
                    <form action="main" method="post">
                        <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <%int prevPage = pageNumber - 1;%>
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=prevPage%>">
                        <input class="button light" type="submit" name="button" value="Предыдущая">
                    </form>
                    <% } %>

                    <p><%=pageNumber%>
                        из
                        <%=maxPageNumber%>
                    </p>


                    <%if (pageNumber != maxPageNumber) { %>
                    <form action="main" method="post">
                        <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <%int nextPage = pageNumber + 1;%>
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=nextPage%>">
                        <input class="button light" type="submit" name="button" value="Следующая">
                    </form>
                    <% }
                    } %>

                </div>
            </div>
            <% } %>

        </div>

    </div>

</section>
<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>
</body>