<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="it.academy.dto.req.ServiceCenterDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="static it.academy.servlets.factory.CommandEnum.ADD_ACCOUNT" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>
    <div class="forms-container lf">

        <%
            List<ServiceCenterDTO> serviceCenters = (List<ServiceCenterDTO>) request.getAttribute(SERVICE_CENTERS);
            int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
        %>
        <%=serviceCenters%>
        <div class="lr-container">
            <form action="main" method="post" id="account">
                <div class="f-input">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_ACCOUNT%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    <input type="hidden" name="<%=ROLE%>" value="<%=RoleEnum.SERVICE_CENTER%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Сервисный центр:</label>
                    <select class="f-form " name="<%=SERVICE_CENTER_ID%>" size="0">
                        <%for (ServiceCenterDTO service : serviceCenters) { %>
                        <option value="<%=service.getId()%>"><%=service.getServiceName()%></option>
                        <% } %>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">email:</label>
                    <input class="f-form" required type="email" name="<%=EMAIL%>" value="olga@mail.ru">
                </div>

                <div class="f-input">
                    <label class="form-el">Имя пользователя:</label>
                    <input class="f-form" required type="text" name="<%=USER_NAME%>" value="User">
                </div>

                <div class="f-input">
                    <label class="form-el">Фамилия пользователя:</label>
                    <input class="f-form" required type="text" name="<%=USER_SURNAME%>" value="User surname">
                </div>

                <div class="f-input">
                    <label class="form-el">Введите пароль:</label>
                    <input class="f-form" required type="password" placeholder="Введите пароль" name="<%=PASSWORD%>" value="Olga8707!"
                           pattern="^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$">
                </div>

                <div class="f-input">
                    <label class="form-el">Введите пароль:</label>
                    <input class="f-form" required type="password" placeholder="Подтвердите пароль" name="<%=PASSWORD_CONFIRM%>" value="Olga8707!"
                           pattern="^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$">
                </div>

                <div class="f-input">
                    <%
                        String errorMessage = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
                    %>
                    <p class="error" id="error" style="display: none"><%=errorMessage%></p>
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Сохранить" form="account"/>
                    <input class="button" type="submit" value="Отмена" form="cancel"/>
                </div>

            </form>

            <form action="main" method="post" id="cancel">
                <input type="hidden" name="command" value="<%=SHOW_START_PAGE%>">
            </form>
        </div>
    </div>
</section>
<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>
</body>
