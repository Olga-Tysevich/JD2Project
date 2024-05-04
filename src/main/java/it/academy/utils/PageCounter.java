package it.academy.utils;

import lombok.experimental.UtilityClass;

import static it.academy.utils.constants.Constants.FIRST_PAGE;
import static it.academy.utils.constants.Constants.LIST_SIZE;

@UtilityClass
public class PageCounter {

    public static int countPageNumber(int pageNumber, long numberOfEntries) {
        int maxPageNumber = (int) Math.ceil(((double) numberOfEntries) / LIST_SIZE);
        return maxPageNumber == 0? FIRST_PAGE: Math.min(maxPageNumber, pageNumber);

    }
}
