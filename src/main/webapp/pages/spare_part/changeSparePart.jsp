<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.spare_part.SparePartForChangeDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_SPARE_PART_TABLE" %>
<%@ page import="it.academy.servlets.commands.factory.CommandEnum" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

            <%
                int pageNumber = request.getParameter(PAGE_NUMBER) != null? Integer.parseInt(request.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
                SparePartForChangeDTO sparePart = (SparePartForChangeDTO) request.getAttribute(SPARE_PART);
                List<ModelDTO> models = sparePart.getAllModels();
                List<ModelDTO> relatedModels = sparePart.getRelatedModels();
                CommandEnum command = (CommandEnum) request.getAttribute(COMMAND);
                String tablePage = (String) request.getAttribute(PAGE);
            %>

            <div class="forms-container lf">
                <div class="lr-container">

                    <form action="main" method="post" id="change_spare_part">
                        <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=request.getParameter(PAGE)%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">

                        <% if (sparePart.getId() != null) { %>
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=sparePart.getId()%>">
                        <div class="f-input">
                            <label class="form-el">Активен</label>
                            <label >Активный: </label>
                            <label >да: </label>
                            <input type="radio" name="<%=IS_ACTIVE%>"  value="true" <%if (sparePart.getIsActive()) {%>checked<%}%> />
                            <label >нет: </label>
                            <input type="radio" name="<%=IS_ACTIVE%>"  value="false" <%if (!sparePart.getIsActive()) {%>checked<%}%>/>
                        </div>
                        <% } %>

                        <div class="f-input">
                            <label class="form-el">Название запчасти</label>
                            <input class="f-form" type="text" required name="<%=OBJECT_NAME%>" value="<%=sparePart.getName()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Модели:</label>
                            <div>
                                <div class="model_container">
                                    <%for (ModelDTO model : models) {%>
                                    <div>
                                        <input type="checkbox" name="<%=SPARE_PART_MODEL_ID%>" value="<%=model.getId()%>"
                                               <% if(relatedModels != null && relatedModels.contains(model)) {%>checked<%}%>>
                                        <%=model.getName()%>
                                    </div>
                                    <%}%>
                                </div>
                            </div>
                        </div>
             </form>

                    <div class="f-input">
                        <%
                            String errorMessage = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
                        %>
                        <p class="error" id="error" style="display: none"><%=errorMessage%></p>
                    </div>

            <div class="button-container">
                <input id="submit_id" class="button" type="submit" value="Подтвердить" form="change_spare_part"/>
                <input class="button" type="submit" value="Отмена" form="cancel"/>
            </div>

                    <form action="main" method="post" id="cancel">
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_PAGE%>">
                        <input type="hidden" name="<%=DISPLAY_TABLE_COMMAND%>" value="<%=SHOW_SPARE_PART_TABLE%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                    </form>

        </div>
    </div>
</section>
<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>
</body>