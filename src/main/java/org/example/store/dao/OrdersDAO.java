package org.example.store.dao;

import org.example.store.domain.Orders;

import java.util.List;

public interface OrdersDAO {

    Orders findByPk(String pk);

    List<Orders> findAll();

    void create(Orders orders);

    void modify(Orders orders);

    void remove(String pk);
}
