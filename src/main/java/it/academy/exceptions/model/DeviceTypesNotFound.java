package it.academy.exceptions.model;


import static it.academy.utils.Constants.DEVICE_TYPES_NOT_FOUND;

public class DeviceTypesNotFound extends RuntimeException{

    public DeviceTypesNotFound() {
        super(DEVICE_TYPES_NOT_FOUND);
    }

}