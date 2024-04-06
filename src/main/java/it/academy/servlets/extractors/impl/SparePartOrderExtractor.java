package it.academy.servlets.extractors.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.converters.spare_parst.SparePartForOrder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.List;

import static it.academy.utils.Constants.ORDER_DATA;

public class SparePartOrderExtractor implements Extractor {
    private Gson gson = new Gson();

    @Override
    public void extractValues(HttpServletRequest req) {

        String orderData = req.getParameter(ORDER_DATA);
        System.out.println(orderData);
        TypeToken<List<SparePartForOrder>> typeToken = new TypeToken<List<SparePartForOrder>>() {};
        Type type = typeToken.getType();

        List<SparePartForOrder> spareParts = new Gson().fromJson(orderData, type);
        spareParts.forEach(System.out::println);

    }

}
