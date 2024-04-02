package it.academy.utils;

public interface ThrowingSupplier<T> {

    T get() throws Exception;

}
