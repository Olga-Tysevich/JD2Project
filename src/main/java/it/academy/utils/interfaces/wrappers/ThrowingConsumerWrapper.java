package it.academy.utils.interfaces.wrappers;

import it.academy.utils.interfaces.wrappers.ThrowingConsumer;

public class ThrowingConsumerWrapper {

    public static<T> void apply(ThrowingConsumer<T> consumer){
        try{
            consumer.apply();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
