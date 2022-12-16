package org.example.store.dao.imp;

import org.example.store.dao.OrdersDAO;
import org.example.store.domain.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrdersDAOImpTest {

    OrdersDAO ordersDAO;

    @BeforeEach
    void setUp() {
        ordersDAO = new OrdersDAOImp();
    }

    @AfterEach
    void tearDown() {
        ordersDAO = null;
    }

    @Test
    void findByPk() {
        Orders orders = ordersDAO.findByPk("1");
        assertNotNull(orders);
        assertEquals("1",orders.getId());
        assertEquals(1111111L,orders.getOrderDate().getTime());
        assertEquals(1,orders.getStatus());
        assertEquals(5000,orders.getTotal());
    }

    @Test
    void findAll() {
        List<Orders> ordersList = ordersDAO.findAll();
        assertNotNull(ordersList);
        for (Orders orders : ordersList){
            assertNotNull(orders.getId());
            assertNotNull(orders.getOrderDate());
        }
        assertEquals(2,ordersList.size());
        Orders orders = ordersList.get(0);
        assertEquals("1",orders.getId());
        assertEquals(1111111L,orders.getOrderDate().getTime());
        assertEquals(1,orders.getStatus());
        assertEquals(5000,orders.getTotal());
    }

    @Test
    void create() {
        Orders orders = new Orders();
        orders.setId("3");
        orders.setOrderDate(new Date(2222222L));
        orders.setStatus(0);
        orders.setTotal(15000);

        ordersDAO.create(orders);

        Orders orders1 = ordersDAO.findByPk("3");

        assertNotNull(orders1);
        assertEquals("3",orders1.getId());
        assertEquals(2222222L,orders1.getOrderDate().getTime());
        assertEquals(0,orders1.getStatus());
        assertEquals(15000,orders1.getTotal());

        ordersDAO.remove("3");

        Orders orders2 = ordersDAO.findByPk("3");

        assertNull(orders2.getId());
        assertNull(orders2.getOrderDate());
        assertEquals(1,orders2.getStatus());
        assertEquals(0.0,orders2.getTotal());
    }

    @Test
    void modify() {
        Orders orders = new Orders();
        orders.setId("3");
        orders.setOrderDate(new Date(2222222L));
        orders.setStatus(0);
        orders.setTotal(15000);

        ordersDAO.create(orders);

        Orders orders1 = new Orders();
        orders1.setId("3");
        orders1.setOrderDate(new Date(33333L));
        orders1.setStatus(1);
        orders1.setTotal(8000);

        ordersDAO.modify(orders1);

        Orders orders2 = ordersDAO.findByPk("3");
        assertEquals("3",orders2.getId());
        assertEquals(33333L,orders2.getOrderDate().getTime());
        assertEquals(1,orders2.getStatus());
        assertEquals(8000,orders2.getTotal());

        ordersDAO.remove("3");

        Orders orders3 = ordersDAO.findByPk("3");

        assertNull(orders3.getId());
        assertNull(orders3.getOrderDate());
        assertEquals(1,orders3.getStatus());
        assertEquals(0.0,orders3.getTotal());
    }

    @Test
    void remove() {
        Orders orders = new Orders();
        orders.setId("3");
        orders.setOrderDate(new Date(2222222L));
        orders.setStatus(0);
        orders.setTotal(15000);

        ordersDAO.create(orders);

        Orders orders1 = ordersDAO.findByPk("3");
        assertNotNull(orders1);
        assertEquals("3",orders1.getId());
        assertEquals(2222222L,orders1.getOrderDate().getTime());
        assertEquals(0,orders1.getStatus());
        assertEquals(15000,orders1.getTotal());

        ordersDAO.remove("3");
        Orders orders2 = ordersDAO.findByPk("3");

        assertNull(orders2.getId());
        assertNull(orders2.getOrderDate());
        assertEquals(1,orders2.getStatus());
        assertEquals(0.0,orders2.getTotal());
    }
}