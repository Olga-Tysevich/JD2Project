package it.academy.utils.converters.spare_part;

import it.academy.dto.spare_part.OrderItemDTO;
import it.academy.dto.spare_part.SparePartOrderDTO;
import it.academy.entities.spare_part.SparePartOrder;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SparePartOrderConverter {

    public static SparePartOrderDTO convertToDTO(SparePartOrder sparePartOrder) {
        return SparePartOrderDTO.builder()
                .id(sparePartOrder.getId())
                .repairId(sparePartOrder.getRepair().getId())
                .serviceCenterRepairNumber(sparePartOrder.getRepair().getRepairNumber())
                .orderDate(sparePartOrder.getOrderDate())
                .departureDate(sparePartOrder.getDepartureDate())
                .deliveryDate(sparePartOrder.getDeliveryDate())
                .spareParts(sparePartOrder.getOrderItems().stream()
                .map(sp -> new OrderItemDTO(sp.getId(), sp.getSparePart().getName(), sp.getQuantity()))
                .collect(Collectors.toList()))
                .build();
    }

    public static SparePartOrder convertToEntity(SparePartOrderDTO partOrderDTO) {
        return SparePartOrder.builder()
                .id(partOrderDTO.getId())
                .orderDate(partOrderDTO.getOrderDate())
                .departureDate(partOrderDTO.getDepartureDate())
                .deliveryDate(partOrderDTO.getDeliveryDate())
                .build();
    }

    public static List<SparePartOrderDTO> convertToDTOList(List<SparePartOrder> partsOrders) {
        return partsOrders.stream()
                .map(SparePartOrderConverter::convertToDTO)
                .collect(Collectors.toList());
    }

}
