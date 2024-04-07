package it.academy.servlets.extractors.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.dto.spare_parts.SparePartOrderDTO;
import it.academy.services.SparePartService;
import it.academy.services.impl.SparePartServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.converters.spare_parst.SparePartConverter;
import it.academy.utils.converters.spare_parst.SparePartForOrder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.*;

public class SparePartOrderExtractor implements Extractor {
    private SparePartService sparePartService = new SparePartServiceImpl();
    private Map<String, Object> reqParameters = new HashMap<>();
    private Gson gson = new Gson();

    @Override
    public void extractValues(HttpServletRequest req) {

        long repairId = Long.parseLong(req.getParameter(REPAIR_ID));
        String orderData = req.getParameter(ORDER_DATA);
        String orderDateString = req.getParameter(ORDER_DATE);
        String departureDateString = req.getParameter(DEPARTURE_DATE);
        String deliveryDateString = req.getParameter(DELIVERY_DATE);
        long deviceTypeId = Long.parseLong(req.getParameter(DEVICE_TYPE_ID));
        String repairNumber = req.getParameter(REPAIR_NUMBER);

        Date orderDate = orderDateString != null? Date.valueOf(orderDateString) : null;
        Date departureDate = departureDateString != null? Date.valueOf(departureDateString) : null;
        Date deliveryDate = deliveryDateString != null? Date.valueOf(deliveryDateString) : null;

        Map<SparePartDTO, Integer> sparePartsMap = null;
        if (orderData != null) {
            TypeToken<List<SparePartForOrder>> typeToken = new TypeToken<>() {};
            Type type = typeToken.getType();
            List<SparePartForOrder> spareParts = gson.fromJson(orderData, type);

            sparePartsMap = spareParts.stream()
                    .collect(Collectors.toMap(SparePartConverter::convertToSparePartDTO,
                            SparePartForOrder::getQuantity));
        }

        SparePartOrderDTO sparePartOrderDTO = SparePartOrderDTO.builder()
                .spareParts(sparePartsMap)
                .repairId(repairId)
                .orderDate(orderDate)
                .deliveryDate(deliveryDate)
                .departureDate(departureDate)
                .build();

        reqParameters.put(REPAIR_ID, repairId);
        reqParameters.put(ORDER_DATA, sparePartsMap);
        reqParameters.put(ORDER_DATE, orderDate);
        reqParameters.put(DEPARTURE_DATE, departureDate);
        reqParameters.put(DELIVERY_DATE, deliveryDate);
        reqParameters.put(DEVICE_TYPE_ID, deviceTypeId);
        reqParameters.put(REPAIR_NUMBER, repairNumber);
        reqParameters.put(SPARE_PART_ORDER, sparePartOrderDTO);
    }

    @Override
    public void insertAttributes(HttpServletRequest req) {

        long repairId = (long) reqParameters.get(REPAIR_ID);
        String repairNumber = (String) reqParameters.get(REPAIR_NUMBER);
        long deviceTypeId = (long) reqParameters.get(DEVICE_TYPE_ID);
        List<SparePartDTO> spareParts = sparePartService.findSparePartsByDeviceTypeId(deviceTypeId);

        req.setAttribute(REPAIR_ID, repairId);
        req.setAttribute(SPARE_PARTS, spareParts);
        req.setAttribute(REPAIR_NUMBER, repairNumber);
        req.setAttribute(DEVICE_TYPE_ID, deviceTypeId);

    }

    @Override
    public void addParameter(String parameterName, Object parameter) {
        reqParameters.put(parameterName, parameter);
    }

    @Override
    public Object getParameter(String parameterName) {
        return reqParameters.get(parameterName);
    }


}
