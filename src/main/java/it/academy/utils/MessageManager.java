package it.academy.utils;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

import static it.academy.utils.Constants.MESSAGES;

@UtilityClass
public class MessageManager {
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle(MESSAGES);

    public static String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    public static<T> String getFormattedMessage(String key, T parameter) {
        return String.format(resourceBundle.getString(key), parameter);
    }
}
