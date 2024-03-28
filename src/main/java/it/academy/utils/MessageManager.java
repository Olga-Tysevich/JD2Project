package it.academy.utils;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

import static it.academy.utils.Constants.MESSAGES;

@UtilityClass
public class MessageManager {
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle(MESSAGES);

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
