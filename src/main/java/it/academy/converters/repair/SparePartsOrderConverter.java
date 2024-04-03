package it.academy.converters.repair;

import it.academy.dto.req.device.SparePartDTOReq;
import it.academy.dto.req.repair.SparePartsOrderDTOReq;
import it.academy.entities.device.components.SparePart;
import it.academy.entities.repair.spare_parts_order.SparePartsOrder;
import it.academy.converters.Converter;
import it.academy.converters.device.SparePartConverter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SparePartsOrderConverter implements Converter<SparePartsOrder, SparePartsOrderDTOReq> {
    private RepairConverter repairConverter = new RepairConverter();
    private SparePartConverter sparePartConverter = new SparePartConverter();

    @Override
    public SparePartsOrderDTOReq convertToDTOReq(SparePartsOrder partsOrder) {
        return SparePartsOrderDTOReq.builder()
                .id(partsOrder.getId())
                .repair(repairConverter.convertToDTOReq(partsOrder.getRepair()))
                .orderDate(partsOrder.getOrderDate())
                .departureDate(partsOrder.getDepartureDate())
                .deliveryDate(partsOrder.getDeliveryDate())
                .spareParts(partsOrder.getSpareParts().entrySet().stream()
                        .map(sp -> {
                            SparePartDTOReq sparePartDTOReq = sparePartConverter.convertToDTOReq(sp.getKey());
                            return Map.entry(sparePartDTOReq, sp.getValue());
                        })
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .build();
    }

    @Override
    public SparePartsOrder convertDTOReqToEntity(SparePartsOrderDTOReq req) {
        return SparePartsOrder.builder()
                .id(req.getId())
                .repair(repairConverter.convertDTOReqToEntity(req.getRepair()))
                .departureDate(req.getDepartureDate())
                .deliveryDate(req.getDeliveryDate())
                .spareParts(req.getSpareParts().entrySet().stream()
                        .map(sp -> {
                            SparePart sparePart = sparePartConverter.convertDTOReqToEntity(sp.getKey());
                            return Map.entry(sparePart, sp.getValue());
                        })
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .build();
    }

    @Override
    public List<SparePartsOrderDTOReq> convertListToDTOReq(List<SparePartsOrder> partsOrders) {
        return partsOrders.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<SparePartsOrder> convertDTOReqListToEntityList(List<SparePartsOrderDTOReq> reqList) {
        return reqList.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
