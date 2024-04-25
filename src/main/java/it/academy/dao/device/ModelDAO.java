package it.academy.dao.device;

import it.academy.dao.DAO;
import it.academy.entities.device.Model;

import java.util.List;

public interface ModelDAO extends DAO<Model, Long> {

    List<Model> findAllByBrandId(long brandId);

    List<Model> findActiveByBrandId(long brandId);

    boolean checkIfExist(Model model);

    List<Model> findAccountsByComponent(String componentName, String input,int pageNumber, int listSize);

    long getNumberOfEntriesByComponent(String componentName, String input);

}
