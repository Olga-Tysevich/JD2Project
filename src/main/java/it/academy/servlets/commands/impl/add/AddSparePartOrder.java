package it.academy.servlets.commands.impl.add;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.academy.dto.spare_part.CreateOrderDTO;
import it.academy.dto.spare_part.OrderItemDTO;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.services.spare_part_order.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowRepairTable;
import it.academy.utils.SparePartForOrder;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class AddSparePartOrder implements ActionCommand {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
    private Gson gson = new Gson();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

            long repairId = Long.parseLong(req.getParameter(ORDER_REPAIR_ID));
            String orderData = req.getParameter(ORDER_DATA);

            List<OrderItemDTO> orderItems = new ArrayList<>();
            if (orderData != null) {
                TypeToken<List<SparePartForOrder>> typeToken = new TypeToken<>() {
                };
                Type type = typeToken.getType();
                List<SparePartForOrder> spareParts = gson.fromJson(orderData, type);
                orderItems = spareParts.stream()
                        .map(sp -> new OrderItemDTO(repairId, sp.getId(), sp.getQuantity()))
                        .collect(Collectors.toList());
            }

            CreateOrderDTO forCreate = CreateOrderDTO.builder()
                    .repairId(repairId)
                    .orderItems(orderItems)
                    .build();
            sparePartOrderService.createSparePartOrder(forCreate);
            return new ShowRepairTable().execute(req, resp);

    }

}
