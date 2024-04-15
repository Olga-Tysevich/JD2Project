<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.req.DeviceTypeDTO" %>
<%@ page import="it.academy.dto.resp.SparePartDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.CHANGE_SPARE_PART" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_SPARE_PART_TABLE" %>
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
                SparePartDTO sparePart = (SparePartDTO) request.getAttribute(SPARE_PART);
                List<DeviceTypeDTO> deviceTypes = sparePart.getAllDeviceTypes();
                List<DeviceTypeDTO> relatedDeviceTypes = sparePart.getRelatedDeviceTypes();
            %>

            <div class="forms-container lf">
                <div class="lr-container">

                    <form action="main" method="post" id="change_spare_part">
                        <input type="hidden" name="<%=COMMAND%>" value="<%=CHANGE_SPARE_PART%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=request.getParameter(PAGE)%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=sparePart.getId()%>">
                        <input type="hidden" name="<%=SPARE_PART_ID%>" value="<%=sparePart.getId()%>">

                        <div class="f-input">
                            <label class="form-el">Активен</label>
                            <label >Активный: </label>
                            <label >да: </label>
                            <input type="radio" name="<%=IS_ACTIVE%>"  value="true" <%if (sparePart.getIsActive()) {%>checked<%}%> />
                            <label >нет: </label>
                            <input type="radio" name="<%=IS_ACTIVE%>"  value="false" <%if (!sparePart.getIsActive()) {%>checked<%}%>/>
                        </div>

                        <div class="f-input">
                            <label class="form-el">Тип устройства:</label>
                        <div>
                        <%for (DeviceTypeDTO deviceType : deviceTypes) {%>
                            <div>
                                <input type="checkbox" name="<%=DEVICE_TYPE_ID%>" value="<%=deviceType.getId()%>"
                                       <% if(relatedDeviceTypes != null && relatedDeviceTypes.contains(deviceType)) {%>checked<%}%>>
                                <%=deviceType.getName()%>
                            </div>
                        <%}%>
                        </div>
                        </div>


                        <div class="f-input">
                            <label class="form-el">Название запчасти</label>
                            <input class="f-form" type="text" name="<%=OBJECT_NAME%>" value="<%=sparePart.getName()%>">
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
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_SPARE_PART_TABLE%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=request.getParameter(PAGE)%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    </form>

        </div>
    </div>
</section>
<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>
</body>