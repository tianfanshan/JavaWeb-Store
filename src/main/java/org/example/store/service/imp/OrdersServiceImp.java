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

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrdersServiceImp implements OrdersService {

    GoodsDAO goodsDAO = new GoodsDAOImp();
    OrdersDAO ordersDAO = new OrdersDAOImp();
    OrderLineItemDAO orderLineItemDAO = new OrderLineItemDAOImp();

    @Override
    public String submitOrders(List<Map<String, Object>> cart) {
        Orders orders = new Orders();
        Date date = new Date();
        // Set orders id
        String ordersId = String.valueOf(date.getTime()) + String.valueOf((int) (Math.random() * 100));
        orders.setId(ordersId);
        orders.setOrderDate(date);
        orders.setStatus(1);
        orders.setTotal(0.0f);
        // 将订单插入到数据库中
        ordersDAO.create(orders);

        double total = 0;

        for (Map<String, Object> item : cart) {
            // item结构 [商品id，数量]
            long goodsId = (long) item.get("goodsId");
            int goodsQuantity = (int) item.get("quantity");
            Goods goods = goodsDAO.findByPk(goodsId);
            // 小计
            double subtotal = goods.getPrice() * goodsQuantity;
            total += subtotal;

            // 创建订单详情并插入到数据库
            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setQuantity(goodsQuantity);
            orderLineItem.setGoods(goods);
            orderLineItem.setOrders(orders);
            orderLineItem.setSubTotal(subtotal);
            orderLineItemDAO.create(orderLineItem);
        }

        orders.setTotal(total);
        ordersDAO.modify(orders);

        return ordersId;
    }
}
