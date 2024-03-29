package it.academy.utils.services;

public interface ThrowingSupplier<T> {

    T get() throws Exception;

}
