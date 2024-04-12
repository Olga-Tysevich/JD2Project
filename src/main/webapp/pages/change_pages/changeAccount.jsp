<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.account.resp.AccountDTO" %>
<%@ page import="it.academy.entities.account.RoleEnum" %>
<%@ page import="it.academy.dto.service_center.ServiceCenterDTO" %>
<%@ page import="java.util.List" %>
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
            int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
            AccountDTO account = (AccountDTO) request.getAttribute(ACCOUNT);
            Long id = account.getId();
            ServiceCenterDTO serviceCenter = account.getServiceCenter();
            String command = (String) request.getAttribute(COMMAND);
            List<ServiceCenterDTO> serviceCenters = (List<ServiceCenterDTO>) request.getAttribute(SERVICE_CENTERS);
        %>
        <div class="lr-container">
            <form action="main" method="post" id="account">
                <div class="f-input">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                </div>
                <%if (id != null) { %>
                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=account.getId()%>">
                <% } %>
                <div class="f-input">
                    <div class="radio-container-rp">
                        <label for="isActive">Активный: </label>
                        <input type="checkbox" id="isActive" name="<%=IS_ACTIVE%>"  value="<%=account.getIsActive()%>"
                               <%if (account.getIsActive()) {%>checked<%}%> />
                    </div>
                </div>

                <div class="f-input">
                    <label class="form-el">Тип аккаунта:</label>
                    <select class="f-form " name="<%=ROLE%>" size="0">
                        <%for (RoleEnum role : RoleEnum.values()) { %>
                        <option value="<%=role%>"
                                <%if (account.getRole().equals(role)) {%>selected<%}%>>
                            <%=role.getDescription()%></option>
                        <% } %>
                    </select>
                </div>

                <% if (RoleEnum.SERVICE_CENTER.equals(account.getRole()) && serviceCenter != null) { %>
                <div class="f-input">
                    <label class="form-el">Сервисный центр:</label>
                    <select class="f-form " name="<%=SERVICE_CENTER_ID%>" size="0">
                        <%for (ServiceCenterDTO service : serviceCenters) { %>
                        <option value="<%=service.getId()%>"
                                <%if (serviceCenter != null && service.getId().equals(account.getServiceCenter().getId())) {%>selected<%}%>>
                            <%=service.getServiceName()%></option>
                        <% } %>
                    </select>
                </div>
                <% } %>

                <div class="f-input">
                    <label class="form-el">email:</label>
                    <input class="f-form" type="text" name="<%=EMAIL%>" value="<%=account.getEmail()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Имя пользователя:</label>
                    <input class="f-form" type="text" name="<%=USER_NAME%>" value="<%=account.getUserName()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Фамилия пользователя:</label>
                    <input class="f-form" type="text" name="<%=USER_SURNAME%>" value="<%=account.getUserSurname()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Установить новый пароль:</label>
                    <input class="f-form" type="password" placeholder="Введите пароль" name="<%=PASSWORD%>" value=""
                           pattern="^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$">
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Сохранить" form="account"/>
                    <input class="button" type="submit" value="Отмена" form="cancel"/>
                </div>

            </form>

            <form action="main" method="post" id="cancel">
                <input type="hidden" name="command" value="<%=SHOW_START_PAGE%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
            </form>
        </div>
    </div>
</section>

</body>
