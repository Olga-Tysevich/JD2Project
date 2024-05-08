package it.academy.dao.spare_part.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.spare_part.OrderItemDAO;
import it.academy.entities.spare_part.OrderItem;
import it.academy.utils.TransactionManger;

public class OrderItemDAOImpl extends DAOImpl<OrderItem, Long> implements OrderItemDAO {

    public OrderItemDAOImpl(TransactionManger manger) {
        super(manger, OrderItem.class);
    }
}
