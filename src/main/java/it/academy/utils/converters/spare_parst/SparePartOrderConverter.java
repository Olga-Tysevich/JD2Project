package it.academy.utils.converters.spare_parst;

import it.academy.dto.repair.spare_parts.SparePartOrderDTO;
import it.academy.entities.spare_parts_order.SparePartsOrder;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SparePartOrderConverter {

    public static SparePartOrderDTO convertToDTO(SparePartsOrder sparePartsOrder) {
//        SparePartOrderDTO order = SparePartOrderDTO.builder()
//                .id(sparePartsOrder.getId())
//                .repairId(sparePartsOrder.getRepair().getId())
//                .serviceCenterRepairNumber(sparePartsOrder.getRepair().getServiceCenterRepairNumber())
//                .orderDate(sparePartsOrder.getOrderDate())
//                .departureDate(sparePartsOrder.getDepartureDate())
//                .deliveryDate(sparePartsOrder.getDeliveryDate())
//                .build();
//        Map<SparePartDTO, Integer> spareParts = sparePartsOrder.getSpareParts()
//                .entrySet().stream()
//                .collect(Collectors.toMap(entry -> SparePartConverter.convertToDTO(entry.getKey()),
//                        Map.Entry::getValue));
//        order.setSpareParts(spareParts);
//        return order;
        return null;
    }

    public static SparePartsOrder convertDTOToEntity(SparePartOrderDTO partOrderDTO) {
        SparePartsOrder order = SparePartsOrder.builder()
                .id(partOrderDTO.getId())
                .orderDate(partOrderDTO.getOrderDate())
                .departureDate(partOrderDTO.getDepartureDate())
                .deliveryDate(partOrderDTO.getDeliveryDate())
                .build();
        partOrderDTO.getSpareParts().forEach((key, value) ->
                order.addSparePart(SparePartConverter.convertToEntity(key), value));
        return order;
    }

    public static List<SparePartOrderDTO> convertListToDTO(List<SparePartsOrder> partsOrders) {
        return partsOrders.stream()
                .map(SparePartOrderConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<SparePartsOrder> convertDTOListToEntityList(List<SparePartOrderDTO> partOrderDTOS) {
        return partOrderDTOS.stream()
                .map(SparePartOrderConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }

}
