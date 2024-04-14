<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.req.SparePartDTO" %>
<%@ page import="it.academy.dto.req.DeviceTypeDTO" %>
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
                List<DeviceTypeDTO> deviceTypes = (List<DeviceTypeDTO>) request.getAttribute(DEVICE_TYPES);
                SparePartDTO currentSparePart = (SparePartDTO) request.getAttribute(CURRENT_SPARE_PART);
                List<DeviceTypeDTO> relatedDeviceTypes = currentSparePart != null? currentSparePart.getRelatedDeviceTypes() : null;
                Long currentSparePartId = currentSparePart != null? currentSparePart.getId() : null;
            %>

            <div class="forms-container lf">
                <div class="lr-container">

                    <form action="main" method="post" id="change_spare_part">
                        <input type="hidden" name="command" value="change_spare_part">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=currentSparePartId%>">

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
                            <input class="f-form" type="text" name="<%=OBJECT_NAME%>" value="<%=currentSparePart != null? currentSparePart.getName() : ""%>">
                        </div>
             </form>

            <div class="button-container">
                <input id="submit_id" class="button" type="submit" value="Подтвердить" form="change_spare_part"/>
                <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
            </div>

        </div>
    </div>
</section>

</body>