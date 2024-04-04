<%@ page import="static it.academy.utils.Constants.USER" %>
<%--<%@ page import="it.academy.dto.req.account.AccountDTO" %>--%>
<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
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
                    int maxPageNumber = request.getAttribute(MAX_PAGE) == null ? FIRST_PAGE : (int) request.getAttribute(MAX_PAGE);
                    String pageForDisplay = (String) request.getAttribute(TABLE_PAGE_PATH);
                %>

        <div class="header-container">
            <div class="header-el">
<%--                <h1><%=userName%></h1>--%>
            </div>
            <div class="header-el">
<%--                <h2><%=userEmail%></h2>--%>
            </div>
        </div>

        <form action="list" method="post">
            <input type="hidden" name="command" value="find_students">
            <input type="hidden" name="page" value="<%=pageNumber%>">
            <input class="search" type="search" name="param" placeholder="Поиск">
            <select class="filter" name="filter" size="1">
<%--                <option selected value="<%=NAME%>">Имя</option>--%>
<%--                <option selected value="<%=STUDENT_SURNAME%>">Фамилия</option>--%>
<%--                <option selected value="<%=STUDENT_AGE%>">Возраст</option>--%>
<%--                <option selected value="<%=STUDENT_MARK%>">Оценка</option>--%>
<%--                <option selected value="<%=STUDENT_ADDRESS%>">Адрес</option>--%>
            </select>
            <input class="button light" type="submit" value="Найти">
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
                <button class="button button-fieldset"> Список брэндов </button>
                <button class="button button-fieldset"> Список типов </button>
                <button class="button button-fieldset"> Список дефектов</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Запчасти</legend>
                <button class="button button-fieldset"> Список запчастей</button>
                <button class="button button-fieldset"> Список заказов запчатей</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Ремонты</legend>
                <button class="button button-fieldset"> Список статусов</button>
                <button class="button button-fieldset"> Список категорий</button>
                <button class="button button-fieldset"> Список типов</button>
            </fieldset>

            <fieldset class="f1">
                <legend>Заявки</legend>
                <button class="button button-fieldset" onclick="location.href='<%=OPEN_BRAND_LIST_PAGE%>'"> Создание новой заявки </button>
                <button class="button button-fieldset" onclick="location.href='<%=String.format(OPEN_REQUEST_TABLE_PAGE, pageNumber)%>'"> Список заявок </button>
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