//package it.academy.services.device.impl;
//
//import it.academy.dao.DAO;
//import it.academy.dto.account.resp.AccountDTO;
//import it.academy.dto.device.ModelDTO;
//import it.academy.dto.device.resp.ChangeModelDTO;
//import it.academy.dto.table.resp.ListForPage;
//import it.academy.entities.account.RoleEnum;
//import it.academy.entities.device.components.Model;
//import it.academy.exceptions.common.AccessDenied;
//import it.academy.utils.Builder;
//import it.academy.utils.ServiceHelper;
//import it.academy.utils.converters.device.ModelConverter;
//import it.academy.utils.dao.TransactionManger;
//import it.academy.utils.fiterForSearch.EntityFilter;
//import it.academy.utils.fiterForSearch.FilterManager;
//
//import java.util.List;
//import java.util.function.Function;
//import java.util.function.Supplier;
//
//import static it.academy.utils.Constants.LIST_SIZE;
//import static it.academy.utils.Constants.MODEL_ALREADY_EXIST;
//
//public class ServiceManager<T, R, I, D extends DAO<R, I>> {
//    private TransactionManger transactionManger;
//    private D dao;
//    private Function<R, T> convertToDTOMethod;
//    private Function<T, R> convertToEntityMethod;
//    private String alreadyExistMessage;
//
//    public void create(AccountDTO accountDTO, T object, I objectId, ) throws AccessDenied {
//        ServiceHelper.checkCurrentAccount(accountDTO);
//
//        R result = convertToEntityMethod.apply(object);
//
//        if (dao.find(objectId) != null) {
//            transactionManger.commit();
//            throw new IllegalArgumentException(MODEL_ALREADY_EXIST);
//        }
//
//        dao.create(result);
//    }
//
//    public void update(AccountDTO accountDTO, T object, I objectId) throws AccessDenied {
//        ServiceHelper.checkCurrentAccount(accountDTO);
//
//        R result = convertToEntityMethod.apply(object);
//
//        R temp = dao.find(objectId);
//
//        if (temp != null && !temp.getId().equals(result.getId())) {
//            transactionManger.commit();
//            throw new IllegalArgumentException(MODEL_ALREADY_EXIST);
//        }
//
//        modelDAO.update(result);
//    }
//
//    @Override
//    public ChangeModelDTO findModel(AccountDTO accountDTO, long id) {
//        Model deviceType = transactionManger.execute(() -> modelDAO.find(id));
//        return ModelConverter.convertToChangeModelDTO(deviceType);
//    }
//
//    @Override
//    public List<ModelDTO> findModels(AccountDTO accountDTO) {
//        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
//            return ModelConverter.convertToDTOList(modelDAO.findActiveObjects(true));
//        }
//
//        List<Model> models = transactionManger.execute(() -> modelDAO.findAll());
//        return ModelConverter.convertToDTOList(models);
//    }
//
//    @Override
//    public ListForPage<ModelDTO> findModels(AccountDTO accountDTO, int pageNumber) {
//
//        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
//            return getModelList(() -> modelDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE), pageNumber,
//                    ModelConverter::convertToDTOList);
//        }
//
//        return getModelList(() -> modelDAO.findForPage(pageNumber, LIST_SIZE), pageNumber,
//                ModelConverter::convertToDTOList);
//    }
//
//    @Override
//    public ListForPage<ModelDTO> findModels(AccountDTO accountDTO, int pageNumber, String filter, String input) {
//        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
//            return getModelList(() -> modelDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input), pageNumber,
//                    ModelConverter::convertToDTOList);
//        }
//        return getModelList(() -> modelDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber,
//                ModelConverter::convertToDTOList);
//    }
//
//    private ListForPage<ModelDTO> getModelList(Supplier<List<Model>> method, int pageNumber,
//                                               Function<List<Model>, List<ModelDTO>> converter) {
//        List<EntityFilter> filters = FilterManager.getFiltersForServiceCenter();
//
//        Supplier<ListForPage<ModelDTO>> find = () -> {
//            List<Model> models = method.get();
//            int maxPageNumber = (int) Math.ceil(((double) modelDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
//            List<ModelDTO> list = converter.apply(models);
//            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
//        };
//
//        return transactionManger.execute(find);
//    }
//}
