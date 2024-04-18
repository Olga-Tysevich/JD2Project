package it.academy.utils.interfaces;


public interface FilteredEntitySupplier<T> {

    T get(int pageNumber, String filter, String input);

}
