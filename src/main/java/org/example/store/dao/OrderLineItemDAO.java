package org.example.store.dao;

import org.example.store.domain.OrderLineItem;

import java.util.List;

public interface OrderLineItemDAO {

    OrderLineItem findByPk(long pk);

    List<OrderLineItem> findAll();

    void create(OrderLineItem orderLineItem);

    void modify(OrderLineItem orderLineItem);

    void remove(long pk);
}
