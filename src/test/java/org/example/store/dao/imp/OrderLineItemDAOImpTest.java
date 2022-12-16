package org.example.store.dao.imp;

import org.example.store.dao.GoodsDAO;
import org.example.store.dao.OrderLineItemDAO;
import org.example.store.dao.OrdersDAO;
import org.example.store.domain.Goods;
import org.example.store.domain.OrderLineItem;
import org.example.store.domain.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineItemDAOImpTest {

    OrderLineItemDAO orderLineItemDAO;
    OrdersDAO ordersDAO;
    GoodsDAO goodsDAO;

    @BeforeEach
    void setUp() {
        orderLineItemDAO = new OrderLineItemDAOImp();
        ordersDAO = new OrdersDAOImp();
        goodsDAO = new GoodsDAOImp();
    }

    @AfterEach
    void tearDown() {
        orderLineItemDAO = null;
        ordersDAO = null;
        goodsDAO = null;
    }

    @Test
    void findByPk() {
        OrderLineItem orderLineItem = orderLineItemDAO.findByPk(1);
        assertNotNull(orderLineItem);
        assertEquals(1, orderLineItem.getId());
        assertEquals(3, orderLineItem.getGoods().getId());
        assertEquals("2", orderLineItem.getOrders().getId());
        assertEquals(3, orderLineItem.getQuantity());
        assertEquals(9297, orderLineItem.getSubTotal());
    }

    @Test
    void findAll() {
        List<OrderLineItem> orderLineItemList = orderLineItemDAO.findAll();
        OrderLineItem orderLineItem = orderLineItemList.get(1);
        assertNotNull(orderLineItem);
        assertEquals(2, orderLineItem.getId());
        assertEquals(4, orderLineItem.getGoods().getId());
        assertEquals("1", orderLineItem.getOrders().getId());
        assertEquals(3, orderLineItem.getQuantity());
        assertEquals(4198, orderLineItem.getSubTotal());
    }

    @Test
    void create() {
        List<Goods> goodsList = goodsDAO.findAll();
        long goodsId = goodsList.get(goodsList.size()-1).getId();
        List<Orders> ordersList = ordersDAO.findAll();
        String ordersId = ordersList.get(ordersList.size()-1).getId();
        int quantity = 4;
        double subTotal = 8888;

        OrderLineItem orderLineItem = new OrderLineItem();
        Goods goods = new Goods();
        goods.setId(goodsId);
        orderLineItem.setGoods(goods);
        Orders orders = new Orders();
        orders.setId(ordersId);
        orderLineItem.setOrders(orders);
        orderLineItem.setQuantity(quantity);
        orderLineItem.setSubTotal(subTotal);
        orderLineItemDAO.create(orderLineItem);

        List<OrderLineItem> orderLineItemList = orderLineItemDAO.findAll();
        long orderLineItemId = orderLineItemList.get(orderLineItemList.size() - 1).getId();

        OrderLineItem orderLineItem1 = orderLineItemDAO.findByPk(orderLineItemId);
        assertEquals(goodsId,orderLineItem1.getGoods().getId());
        assertEquals(ordersId,orderLineItem1.getOrders().getId());
        assertEquals(4,orderLineItem1.getQuantity());
        assertEquals(8888,orderLineItem1.getSubTotal());

        orderLineItemDAO.remove(orderLineItemId);
        OrderLineItem orderLineItem2 = orderLineItemDAO.findByPk(orderLineItemId);
        assertEquals(0, orderLineItem2.getId());
        assertEquals(0, orderLineItem2.getQuantity());
        assertEquals(0.0, orderLineItem2.getSubTotal());
    }

    @Test
    void modify() {
        List<Goods> goodsList = goodsDAO.findAll();
        long goodsId = goodsList.get(goodsList.size()-1).getId();
        List<Orders> ordersList = ordersDAO.findAll();
        String ordersId = ordersList.get(ordersList.size()-1).getId();
        int quantity = 4;
        double subTotal = 8888;

        OrderLineItem orderLineItem = new OrderLineItem();
        Goods goods = new Goods();
        goods.setId(goodsId);
        orderLineItem.setGoods(goods);
        Orders orders = new Orders();
        orders.setId(ordersId);
        orderLineItem.setOrders(orders);
        orderLineItem.setQuantity(quantity);
        orderLineItem.setSubTotal(subTotal);
        orderLineItemDAO.create(orderLineItem);

        List<OrderLineItem> orderLineItemList = orderLineItemDAO.findAll();
        long orderLineItemId = orderLineItemList.get(orderLineItemList.size() - 1).getId();

        //Data to use for modify
        List<Goods> goodsList1 = goodsDAO.findAll();
        long goodsId1 = goodsList1.get(0).getId();
        List<Orders> ordersList1 = ordersDAO.findAll();
        String ordersId1 = ordersList1.get(0).getId();
        int quantity1 = 10;
        double subTotal1 = 88880;

        OrderLineItem orderLineItem1 = orderLineItemDAO.findByPk(orderLineItemId);
        Goods goods1 = new Goods();
        goods1.setId(goodsId1);
        orderLineItem1.setGoods(goods1);
        Orders orders1 = new Orders();
        orders1.setId(ordersId1);
        orderLineItem1.setOrders(orders1);
        orderLineItem1.setQuantity(quantity1);
        orderLineItem1.setSubTotal(subTotal1);
        orderLineItem1.setId(orderLineItem1.getId());
        orderLineItemDAO.modify(orderLineItem1);

        OrderLineItem orderLineItem2 = orderLineItemDAO.findByPk(orderLineItemId);
        assertEquals(goodsId1,orderLineItem2.getGoods().getId());
        assertEquals(ordersId1,orderLineItem2.getOrders().getId());
        assertEquals(quantity1,orderLineItem2.getQuantity());
        assertEquals(subTotal1,orderLineItem2.getSubTotal());

        orderLineItemDAO.remove(orderLineItemId);
        OrderLineItem orderLineItem3 = orderLineItemDAO.findByPk(orderLineItemId);
        assertEquals(0, orderLineItem3.getId());
        assertEquals(0, orderLineItem3.getQuantity());
        assertEquals(0.0, orderLineItem3.getSubTotal());
    }

    @Test
    void remove() {
        List<Goods> goodsList = goodsDAO.findAll();
        long goodsId = goodsList.get(goodsList.size()-1).getId();
        List<Orders> ordersList = ordersDAO.findAll();
        String ordersId = ordersList.get(ordersList.size()-1).getId();
        int quantity = 4;
        double subTotal = 8888;

        OrderLineItem orderLineItem = new OrderLineItem();
        Goods goods = new Goods();
        goods.setId(goodsId);
        orderLineItem.setGoods(goods);

        Orders orders = new Orders();
        orders.setId(ordersId);
        orderLineItem.setOrders(orders);

        orderLineItem.setQuantity(quantity);
        orderLineItem.setSubTotal(subTotal);
        orderLineItemDAO.create(orderLineItem);

        List<OrderLineItem> orderLineItemList = orderLineItemDAO.findAll();
        long orderLineItemId = orderLineItemList.get(orderLineItemList.size()-1).getId();

        orderLineItemDAO.remove(orderLineItemId);

        OrderLineItem orderLineItem1 = orderLineItemDAO.findByPk(orderLineItemId);
        assertEquals(0, orderLineItem1.getId());
        assertEquals(0, orderLineItem1.getQuantity());
        assertEquals(0.0, orderLineItem1.getSubTotal());
    }
}