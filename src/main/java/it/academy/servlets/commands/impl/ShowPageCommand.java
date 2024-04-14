package it.academy.servlets.commands.impl;

import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.interfaces.EntitySupplier;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.Constants.*;

public class ShowPageCommand<T> implements ActionCommand {
    private EntitySupplier<T> methodForSearch;
    private String objectKey;
    private String pagePath;

    public ShowPageCommand(EntitySupplier<T> methodForSearch, String objectKey, String pagePath) {
        this.methodForSearch = methodForSearch;
        this.objectKey = objectKey;
        this.pagePath = pagePath;
    }

    @Override
    public String execute(HttpServletRequest req) {

        long objectId = Long.parseLong(req.getParameter(OBJECT_ID));
        String page = req.getParameter(PAGE);
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        T dto = methodForSearch.get(objectId);
        System.out.println("show " + dto);

        req.setAttribute(objectKey, dto);
        req.setAttribute(PAGE, page);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return pagePath;
    }

}
