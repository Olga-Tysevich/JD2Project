package it.academy.exceptions.model;

import static it.academy.utils.Constants.MODELS_NOT_FOUND;

public class ModelNotFound extends RuntimeException{

    public ModelNotFound() {
        super(MODELS_NOT_FOUND);
    }

}