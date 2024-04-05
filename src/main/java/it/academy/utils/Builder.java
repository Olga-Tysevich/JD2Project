package it.academy.utils;

import it.academy.dto.DeviceDTO;
import it.academy.dto.ModelDTO;
import it.academy.dto.RepairDTO;
import it.academy.entities.device.Device;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Builder {

    public static DeviceDTO buildDevice(ModelDTO model) {
        return DeviceDTO.builder()
                .model(model)
                .build();
    }

}
