package it.academy.utils.interfaces.wrappers;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThrowingConsumerWrapper {

    public static <T> void apply(ThrowingConsumer<T> consumer) {
        try {
            consumer.apply();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
