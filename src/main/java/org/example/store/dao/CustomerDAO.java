package org.example.store.dao;

import org.example.store.domain.Customer;

import java.util.List;

public interface CustomerDAO {

    Customer findByPk(String pk);

    List<Customer> findAll();

    void create(Customer customer);

    void modify(Customer customer);

    void remove(String pk);

}
