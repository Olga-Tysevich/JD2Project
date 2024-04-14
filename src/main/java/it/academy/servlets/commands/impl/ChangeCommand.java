package it.academy.servlets.commands.impl;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.account.ShowAccountTable;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.EntitySupplier;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import javax.servlet.http.HttpServletRequest;
import java.util.function.Consumer;

import static it.academy.utils.Constants.*;

public class ChangeCommand<T> implements ActionCommand {
    private EntitySupplier<T> methodForSearch;
    private Consumer<T> methodForUpdate;
    private Class<T> objectClass;

    public ChangeCommand(EntitySupplier<T> methodForSearch, Consumer<T> methodForUpdate) {
        this.methodForSearch = methodForSearch;
        this.methodForUpdate = methodForUpdate;
    }


    @Override
    public String execute(HttpServletRequest req) {
        try {
//            return FormExtractor.extract(req,
//                    (a) -> ThrowingConsumerWrapper.apply(() -> methodForUpdate.accept(objectClass.cast(a)),
//                            (id) -> methodForSearch.get(id),
//                            objectClass,
//                            ACCOUNT,
//                            ACCOUNT_PAGE_PATH,
//                            () -> new ShowAccountTable().execute(req)));
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }
    }
}