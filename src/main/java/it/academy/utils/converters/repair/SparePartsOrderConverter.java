package it.academy.utils.converters.repair;

import it.academy.dto.req.device.SparePartDTOReq;
import it.academy.dto.req.repair.SparePartsOrderDTOReq;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.entities.spare_parts_order.SparePartsOrder;
import it.academy.utils.converters.device.SparePartConverter;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class SparePartsOrderConverter {

    public static SparePartsOrderDTOReq convertToDTOReq(SparePartsOrder partsOrder) {
        return SparePartsOrderDTOReq.builder()
                .id(partsOrder.getId())
                .repair(RepairConverter.convertToDTOReq(partsOrder.getRepair()))
                .orderDate(partsOrder.getOrderDate())
                .departureDate(partsOrder.getDepartureDate())
                .deliveryDate(partsOrder.getDeliveryDate())
                .spareParts(partsOrder.getSpareParts().entrySet().stream()
                        .map(sp -> {
                            SparePartDTOReq sparePartDTOReq = SparePartConverter.convertToDTOReq(sp.getKey());
                            return Map.entry(sparePartDTOReq, sp.getValue());
                        })
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .build();
    }

    public static SparePartsOrder convertDTOReqToEntity(SparePartsOrderDTOReq req) {
        return SparePartsOrder.builder()
                .id(req.getId())
                .repair(RepairConverter.convertDTOReqToEntity(req.getRepair()))
                .departureDate(req.getDepartureDate())
                .deliveryDate(req.getDeliveryDate())
                .spareParts(req.getSpareParts().entrySet().stream()
                        .map(sp -> {
                            SparePart sparePart = SparePartConverter.convertDTOReqToEntity(sp.getKey());
                            return Map.entry(sparePart, sp.getValue());
                        })
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .build();
    }

    public static List<SparePartsOrderDTOReq> convertListToDTOReq(List<SparePartsOrder> partsOrders) {
        return partsOrders.stream()
                .map(SparePartsOrderConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static List<SparePartsOrder> convertDTOReqListToEntityList(List<SparePartsOrderDTOReq> reqList) {
        return reqList.stream()
                .map(SparePartsOrderConverter::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}