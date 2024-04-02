package it.academy.utils;

import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static it.academy.utils.Constants.*;
import static javax.servlet.http.HttpServletResponse.*;

@UtilityClass
public class ExceptionManager {

    public static <T> T tryExecute(ThrowingSupplier<T> method) {
        try {
            return method.get();
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> RespListDTO<T> getListSearchResult(ThrowingSupplier<List<T>> method) {
        try {
            List<T> result = method.get();
            return ResponseManager.getDTORespList(SC_OK, MessageManager.getFormattedMessage(OBJECT_FOUND, result.size()), result);
        } catch (Exception e) {
            return ResponseManager.getDTORespList(SC_INTERNAL_SERVER_ERROR, e.getMessage(), new ArrayList<>());
        }
    }

    public static <T> RespDTO<T> getObjectFindResult(ThrowingSupplier<T> method) {
        return getObjectResult(method, SC_OK, OBJECT_FOUND, SC_INTERNAL_SERVER_ERROR);
    }

    public static <T> RespDTO<T> getObjectSaveResult(ThrowingSupplier<T> method) {
        return getObjectResult(method, SC_CREATED, SAVED_SUCCESSFULLY, SC_INTERNAL_SERVER_ERROR);
    }

    public static <T> RespDTO<T> getObjectUpdateResult(ThrowingSupplier<T> method) {
        return getObjectResult(method, SC_OK, UPDATED_SUCCESSFULLY, SC_INTERNAL_SERVER_ERROR);
    }

    private static <T> RespDTO<T> getObjectResult(ThrowingSupplier<T> method, int httpSuccessStatus, String message, int httpErrorStatus) {
        try {
            T result = method.get();
            return ResponseManager.getDTOResp(httpSuccessStatus, MessageManager.getFormattedMessage(message, result));
        } catch (Exception e) {
            return ResponseManager.getDTOResp(httpErrorStatus, e.getMessage());
        }
    }
}
