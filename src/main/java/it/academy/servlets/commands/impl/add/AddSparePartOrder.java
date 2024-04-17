package it.academy.servlets.commands.impl.add;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.academy.dto.resp.SparePartDTO;
import it.academy.dto.resp.SparePartOrderDTO;
import it.academy.services.SparePartOrderService;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowConfirmedRepair;
import it.academy.utils.converters.SparePartConverter;
import it.academy.utils.SparePartForOrder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class AddSparePartOrder implements ActionCommand {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
    private Gson gson = new Gson();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            long repairId = Long.parseLong(req.getParameter(OBJECT_ID));
            String orderData = req.getParameter(ORDER_DATA);
            String orderDateString = req.getParameter(ORDER_DATE);
            String departureDateString = req.getParameter(DEPARTURE_DATE);
            String deliveryDateString = req.getParameter(DELIVERY_DATE);
            Date orderDate = orderDateString != null ? Date.valueOf(orderDateString) : null;
            Date departureDate = departureDateString != null ? Date.valueOf(departureDateString) : null;
            Date deliveryDate = deliveryDateString != null ? Date.valueOf(deliveryDateString) : null;

            Map<SparePartDTO, Integer> sparePartsMap = null;
            if (orderData != null) {
                TypeToken<List<SparePartForOrder>> typeToken = new TypeToken<>() {
                };
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

            sparePartOrderService.addSparePartOrder(sparePartOrderDTO);

            return new ShowConfirmedRepair().execute(req);
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }

    }

}
