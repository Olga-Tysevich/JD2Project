package it.academy.utils.converters;

import it.academy.dto.resp.SparePartForChangeDTO;
import it.academy.dto.resp.SparePartOrderDTO;
import it.academy.entities.SparePartOrder;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class SparePartOrderConverter {

    public static SparePartOrderDTO convertToDTO(SparePartOrder sparePartOrder) {
        SparePartOrderDTO order = SparePartOrderDTO.builder()
                .id(sparePartOrder.getId())
                .repairId(sparePartOrder.getRepair().getId())
                .serviceCenterRepairNumber(sparePartOrder.getRepair().getRepairNumber())
                .orderDate(sparePartOrder.getOrderDate())
                .departureDate(sparePartOrder.getDepartureDate())
                .deliveryDate(sparePartOrder.getDeliveryDate())
                .build();
        Map<SparePartForChangeDTO, Integer> spareParts = sparePartOrder.getSpareParts()
                .entrySet().stream()
                .collect(Collectors.toMap(entry -> SparePartConverter.convertToDTO(entry.getKey(), null),
                        Map.Entry::getValue));
        order.setSpareParts(spareParts);
        return order;
    }

    public static SparePartOrder convertDTOToEntity(SparePartOrderDTO partOrderDTO) {
        SparePartOrder order = SparePartOrder.builder()
                .id(partOrderDTO.getId())
                .orderDate(partOrderDTO.getOrderDate())
                .departureDate(partOrderDTO.getDepartureDate())
                .deliveryDate(partOrderDTO.getDeliveryDate())
                .build();
        partOrderDTO.getSpareParts().forEach((key, value) ->
                order.addSparePart(SparePartConverter.convertToEntity(key), value));
        return order;
    }

    public static List<SparePartOrderDTO> convertListToDTO(List<SparePartOrder> partsOrders) {
        return partsOrders.stream()
                .map(SparePartOrderConverter::convertToDTO)
                .collect(Collectors.toList());
    }

}
