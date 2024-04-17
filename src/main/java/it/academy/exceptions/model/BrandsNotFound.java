package it.academy.exceptions.model;

import static it.academy.utils.constants.Constants.BRANDS_NOT_FOUND;

public class BrandsNotFound extends RuntimeException {

    public BrandsNotFound() {
        super(BRANDS_NOT_FOUND);
    }

}
