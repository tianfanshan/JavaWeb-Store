package org.example.store.dao.imp;

import org.example.store.dao.CustomerDAO;
import org.example.store.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOImpTest {

    CustomerDAO dao;

    @BeforeEach
    void setUp() {
        dao = new CustomerDAOImp();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    void findByPk() {
        Customer customer = dao.findByPk("1");
        assertNotNull(customer);
        assertEquals("1",customer.getId());
        assertEquals("pepe",customer.getName());
        assertEquals("123456",customer.getPassword());
        assertEquals("santander1",customer.getAddress());
        assertEquals("123456",customer.getPhone());
        assertEquals(111111L,customer.getBirthday().getTime());
    }

    @Test
    void findAll() {
        List<Customer> customerList = dao.findAll();
        assertEquals(1,customerList.size());
    }

    @Test
    void create() {
        Customer customer = new Customer();
        customer.setId("2");
        customer.setName("juan");
        customer.setPassword("123456");
        customer.setAddress("antonio2");
        customer.setPhone("123456");
        customer.setBirthday(new Date(123456L));
        dao.create(customer);

        Customer newCustomer = dao.findByPk("2");
        assertNotNull(newCustomer);
        assertEquals("2",newCustomer.getId());
        assertEquals("juan",newCustomer.getName());
        assertEquals("123456",newCustomer.getPassword());
        assertEquals("antonio2",newCustomer.getAddress());
        assertEquals("123456",newCustomer.getPhone());
        assertEquals(123456L,newCustomer.getBirthday().getTime());

        dao.remove("2");
    }

    @Test
    void modify() {
        Customer customer = new Customer();
        customer.setId("2");
        customer.setName("juan");
        customer.setPassword("123456");
        customer.setAddress("antonio2");
        customer.setPhone("123456");
        customer.setBirthday(new Date(123456L));
        dao.create(customer);

        Customer customer2 = new Customer();
        customer2.setId("2");
        customer2.setName("juan2");
        customer2.setPassword("654321");
        customer2.setAddress("antonio3");
        customer2.setPhone("654321");
        customer2.setBirthday(new Date(654321L));
        dao.modify(customer2);

        Customer customer3 = dao.findByPk("2");
        assertEquals("2",customer3.getId());
        assertEquals("juan2",customer3.getName());
        assertEquals("654321",customer3.getPassword());
        assertEquals("antonio3",customer3.getAddress());
        assertEquals("654321",customer3.getPhone());
        assertEquals(654321L,customer3.getBirthday().getTime());

        dao.remove("2");
    }

    @Test
    void remove() {

        Customer customer = new Customer();
        customer.setId("2");
        customer.setName("juan");
        customer.setPassword("123456");
        customer.setAddress("antonio2");
        customer.setPhone("123456");
        customer.setBirthday(new Date(123456L));
        dao.create(customer);

        dao.remove("2");
        assertNull(dao.findByPk("2").getId());
        assertNull(dao.findByPk("2").getName());
        assertNull(dao.findByPk("2").getPassword());
        assertNull(dao.findByPk("2").getAddress());
        assertNull(dao.findByPk("2").getPhone());
        assertNull(dao.findByPk("2").getBirthday());
    }
}