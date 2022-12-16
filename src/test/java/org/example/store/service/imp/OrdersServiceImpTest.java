package org.example.store.service.imp;

import org.example.store.dao.GoodsDAO;
import org.example.store.dao.OrderLineItemDAO;
import org.example.store.dao.OrdersDAO;
import org.example.store.dao.imp.GoodsDAOImp;
import org.example.store.dao.imp.OrderLineItemDAOImp;
import org.example.store.dao.imp.OrdersDAOImp;
import org.example.store.domain.Goods;
import org.example.store.domain.OrderLineItem;
import org.example.store.domain.Orders;
import org.example.store.service.OrdersService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrdersServiceImpTest {

    OrdersService ordersService;
    OrdersDAO ordersDAO;
    OrderLineItemDAO orderLineItemDAO;
    GoodsDAO goodsDAO;

    @BeforeEach
    void setUp() {
        ordersService = new OrdersServiceImp();
        ordersDAO = new OrdersDAOImp();
        orderLineItemDAO = new OrderLineItemDAOImp();
        goodsDAO = new GoodsDAOImp();
    }

    @AfterEach
    void tearDown() {
        ordersService = null;
        ordersDAO = null;
        orderLineItemDAO = null;
        goodsDAO = null;
    }

    @Test
    void submitOrders() {

        List<Map<String,Object>> cart = new ArrayList<>();
        Map<String, Object> item1 = new HashMap<>();
        long item1Id = 3L;
        int item1Quantity = 2;
        item1.put("goodsId", item1Id);
        item1.put("quantity",item1Quantity);
        cart.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        long item2Id = 8L;
        int item2Quantity = 3;
        item2.put("goodsId", item2Id);
        item2.put("quantity",item2Quantity);
        cart.add(item2);

        String ordersId = ordersService.submitOrders(cart);
        System.out.println(ordersId);
        assertNotNull(ordersId);

        Orders orders = ordersDAO.findByPk(ordersId);
        assertNotNull(orders);
        assertEquals(1,orders.getStatus());

        Goods goods = goodsDAO.findByPk(item1Id);
        Goods goods1 = goodsDAO.findByPk(item2Id);
        double subtotal = goods.getPrice() * item1Quantity;
        double subtotal1 = goods1.getPrice() * item2Quantity;
        double total = subtotal + subtotal1;

        assertEquals(total,orders.getTotal());

        List<OrderLineItem> orderLineItemList = orderLineItemDAO.findAll();
        List<OrderLineItem> orderLineItems = new ArrayList<>();
        for (OrderLineItem orderLineItem : orderLineItemList){
            if (orderLineItem.getOrders().getId().equals(ordersId)){
                orderLineItems.add(orderLineItem);
                if (orderLineItem.getGoods().getId() == item1Id){
                    assertEquals(item1Quantity,orderLineItem.getQuantity());
                    assertEquals(subtotal,orderLineItem.getSubTotal());
                }
                if (orderLineItem.getGoods().getId() == item2Id){
                    assertEquals(item2Quantity,orderLineItem.getQuantity());
                    assertEquals(subtotal1,orderLineItem.getSubTotal());
                }
            }
        }

        assertEquals(2,orderLineItems.size());
    }
}