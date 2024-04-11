<%@ page import="static it.academy.utils.Constants.ACCOUNT" %>
<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.utils.EntityFilter" %>
<%@ page import="it.academy.dto.AccountDTO" %>
<%@ page import="it.academy.entities.RoleEnum" %>
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
                    RoleEnum role = accountDTO.getRole();
                    String userEmail = accountDTO.getEmail();
                    int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
                    int maxPageNumber = request.getAttribute(MAX_PAGE) == null ? FIRST_PAGE : (int) request.getAttribute(MAX_PAGE);
                    List<EntityFilter> filters = (List<EntityFilter>) request.getAttribute(FILTERS);
                    String pageForDisplay = (String) request.getAttribute(PAGE);
                    String command = (String) request.getAttribute(COMMAND);
                %>

        <div class="header-container">
            <div class="header-el">
                <p><%=userEmail%></p>
            </div>
        </div>

        <% if (pageForDisplay != null) { %>
        <form action="main" method="post" id="search">
            <input type="hidden" name="command" value="<%=command%>">
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
            <input type="hidden" name="<%=PAGE%>" value="<%=pageForDisplay%>">
            <input class="search" type="search" name="<%=USER_INPUT%>" placeholder="Поиск">
            <select class="filter" name="filter" size="1">
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

            <% if (RoleEnum.ADMIN.equals(role)) {%>
            <fieldset class="f1">
                <legend>Компания</legend>
                <form  action="account" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_ACCOUNT%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    <input class="button button-fieldset" type="submit" value="Добавить аккаунт"/>
                </form>

                <form  action="account" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_ACCOUNT_TABLE%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    <input class="button button-fieldset" type="submit" value="Список аккаунтов"/>
                </form>
            </fieldset>

            <fieldset class="f1">
                <legend>Сервисные центры</legend>
<%--                <button class="button button-fieldset"--%>
<%--                        onclick="location.href='<%=String.format(OPEN_REPAIR_WORKSHOP_PAGE, FIRST_PAGE)%>'"> Добавить сервисный центр </button>--%>
                <form  action="account" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_SERVICE_CENTER%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    <input class="button button-fieldset" type="submit" value="Добавить сервисный центр"/>
                </form>
                <form  action="account" method="post">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_SERVICE_CENTER_TABLE%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    <input class="button button-fieldset" type="submit" value="Список сервисных центров"/>
                </form>
<%--                <button class="button button-fieldset"--%>
<%--                        onclick="location.href='<%=String.format(OPEN_REPAIR_WORKSHOP_TABLE_PAGE, FIRST_PAGE)%>'"> Список сервисных центров </button>--%>
            </fieldset>

            <% } else { %>
            <fieldset class="f1">
                <legend>Сервисный центр</legend>
                <form  action="account" method="post">
                    <input type="hidden" name="command" value="<%=SHOW_SERVICE_CENTER%>">
                    <input type="hidden" name="<%=OBJECT_ID%>>" value="<%=accountDTO.getServiceCenter().getId()%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    <input class="button button-fieldset" type="submit" value="Изменить учетные данные"/>
                </form>

<%--                <button class="button button-fieldset"--%>
<%--                        onclick="location.href='<%=String.format(OPEN_REPAIR_WORKSHOP_PAGE, FIRST_PAGE)%>'"> Изменить учетные данные </button>--%>
            </fieldset>
            <% } %>

            <fieldset class="f1">
                <legend>Устройства</legend>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_MODEL_TABLE_PAGE, FIRST_PAGE)%>'"> Список моделей </button>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_BRAND_TABLE_PAGE, FIRST_PAGE)%>'"> Список брэндов </button>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_DEVICE_TYPE_TABLE_PAGE, FIRST_PAGE)%>'"> Список типов</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Запчасти</legend>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_SPARE_PART_TABLE_PAGE, FIRST_PAGE)%>'"> Список запчастей</button>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_SPARE_PART_ORDERS_TABLE_PAGE, FIRST_PAGE)%>'"> Список заказов запчатей</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Ремонты</legend>
                <form action="repair" method="post">
                    <input type="hidden" name="command" value="show_repair">
                    <input class="button button-fieldset" type="submit" value="Создание нового ремонта">
                </form>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_REPAIR_TABLE_PAGE, FIRST_PAGE)%>'"> Список ремонтов </button>
                <button class="button button-fieldset"
                        onclick="location.href='<%=String.format(OPEN_REPAIR_TYPE_TABLE_PAGE, FIRST_PAGE)%>'">Список типов ремонта</button>
            </fieldset>

        </div>

        <div class="table-container">
            <%=pageForDisplay%>
            <%=maxPageNumber%>
            <%=command%>
            <% if (pageForDisplay != null) {
                pageContext.include(pageForDisplay);
                if (maxPageNumber > 1) {%>
            <div class="footer">
                <div class="button-container">
                    <%if (pageNumber != FIRST_PAGE) { %>
                    <form action="main" method="post">
                        <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
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
                        <input type="text" name="<%=COMMAND%>" value="<%=command%>">
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
</body>