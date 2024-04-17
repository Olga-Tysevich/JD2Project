package it.academy.utils.interfaces.wrappers;

import lombok.extern.slf4j.Slf4j;

import static it.academy.utils.constants.Constants.ERROR_PATTERN;

@Slf4j
public class ThrowingConsumerWrapper {

    public static <T> void apply(ThrowingConsumer<T> consumer) {
        try {
            consumer.apply();
        } catch (Exception e) {
            log.error(String.format(ERROR_PATTERN, e.getMessage(), consumer));
            throw new RuntimeException(e.getMessage());
        }
    }
}
