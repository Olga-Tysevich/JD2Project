package it.academy.servlets.commands.impl;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.account.ShowAccountTable;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.EntitySupplier;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import javax.servlet.http.HttpServletRequest;
import java.util.function.Consumer;

import static it.academy.utils.Constants.*;

public class ChangeCommand<T, R> implements ActionCommand {
    private EntitySupplier<T> methodForSearch;
    private Consumer<T> methodForUpdate;
    private Class<T> objectClass;
    private String objectKey;
    private String pagePath;

    public ChangeCommand(Consumer<T> methodForUpdate, EntitySupplier<T> methodForSearch,
                         Class<T> objectClass, String objectKey, String pagePath) {
        this.methodForSearch = methodForSearch;
        this.methodForUpdate = methodForUpdate;
        this.objectClass = objectClass;
        this.objectKey = objectKey;
        this.pagePath = pagePath;
    }

    @Override
    public String execute(HttpServletRequest req) {
        try {
            return FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> methodForUpdate.accept(objectClass.cast(a))),
                            (id) -> methodForSearch.get(id),
                            objectClass,
                            objectKey,
                            pagePath,
                            () -> new ShowAccountTable().execute(req));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }
    }
}