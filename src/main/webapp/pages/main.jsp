<%@ page import="static it.academy.utils.Constants.USER" %>
<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.utils.EntityFilter" %>
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

//                    AccountDTO accountDTO = ((AccountDTO) session.getAttribute(USER));
//                    String userEmail = accountDTO.getEmail();
//                    String userName = accountDTO.getRepairWorkshop() != null? accountDTO.getRepairWorkshop().getServiceName()
//                            : "Компания";
                    int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
                    List<EntityFilter> filters = (List<EntityFilter>) request.getAttribute(FILTERS);
                    String pageForDisplay = (String) request.getAttribute(PAGE);
                    String showCommand = (String) request.getAttribute(SHOW_COMMAND);
                %>

        <div class="header-container">
            <div class="header-el">
<%--                <h1><%=userName%></h1>--%>
            </div>
            <div class="header-el">
<%--                <h2><%=userEmail%></h2>--%>
            </div>
        </div>


        <form action="main" method="post" id="search">
            <input type="hidden" name="command" value="<%=showCommand%>">
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

    </div>

    <div class="container main">

        <div class="menu-container">
            <fieldset class="f1">
                <legend>Компания</legend>
                <button class="button button-fieldset"> Добавить аккаунт </button>
                <button class="button button-fieldset"> Список текущих администраторов </button>
                <button class="button button-fieldset"> Список заблокированных администраторов </button>
            </fieldset>

            <fieldset class="f1">
                <legend>Сервисные центры</legend>
                <button class="button button-fieldset"> Список текущих сервисных центров </button>
                <button class="button button-fieldset"> Список заблокированных сервисных центров </button>
            </fieldset>

            <fieldset class="f1">
                <legend>Устройства</legend>
                <button class="button button-fieldset"> Список моделей </button>
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
            <%if (pageForDisplay != null) {
                pageContext.include(pageForDisplay);
            }
            %>
        </div>

    </div>

</section>
</body>