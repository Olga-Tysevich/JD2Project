package it.academy.utils.interfaces;

public interface EntitySupplier<T> {

    T get(Object id);

}
