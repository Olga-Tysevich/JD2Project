package it.academy.utils.helpers;

public class ThrowingConsumerWrapper {

    public static<T> void apply(ThrowingConsumer<T> consumer){
        try{
            consumer.apply();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
