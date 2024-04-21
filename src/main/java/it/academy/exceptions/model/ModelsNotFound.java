package it.academy.exceptions.model;


import static it.academy.utils.constants.Constants.MODELS_NOT_FOUND;

public class ModelsNotFound extends RuntimeException{

    public ModelsNotFound() {
        super(MODELS_NOT_FOUND);
    }

}