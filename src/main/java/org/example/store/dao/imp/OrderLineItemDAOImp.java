package org.example.store.dao.imp;

import org.example.db.core.JdbcTemplate;
import org.example.store.dao.OrderLineItemDAO;
import org.example.store.domain.Goods;
import org.example.store.domain.OrderLineItem;
import org.example.store.domain.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineItemDAOImp implements OrderLineItemDAO {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public OrderLineItem findByPk(long pk) {
        String query = "SELECT * FROM orderlineitems WHERE id = ?";
        OrderLineItem orderLineItem = new OrderLineItem();
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1,pk);
            return ps;
        },rs -> {
            createSingleOrderLineItem(rs, orderLineItem);
        });
        return orderLineItem;
    }

    @Override
    public List<OrderLineItem> findAll() {
        String query = "SELECT * FROM orderlineitems";
        List<OrderLineItem> orderLineItemList = new ArrayList<>();
        jdbcTemplate.query(conn -> conn.prepareStatement(query), rs -> {
            OrderLineItem orderLineItem = new OrderLineItem();
            createSingleOrderLineItem(rs, orderLineItem);
            orderLineItemList.add(orderLineItem);
        });
        return orderLineItemList;
    }

    @Override
    public void create(OrderLineItem orderLineItem) {
        String query = "INSERT INTO orderlineitems (goodsid,orderid,quantity,sub_total) VALUES (?,?,?,?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1,orderLineItem.getGoods().getId());
            ps.setString(2,orderLineItem.getOrders().getId());
            ps.setInt(3,orderLineItem.getQuantity());
            ps.setDouble(4,orderLineItem.getSubTotal());
            return ps;
        });
    }

    @Override
    public void modify(OrderLineItem orderLineItem) {
        String query = "UPDATE orderlineitems SET goodsid=?,orderid=?,quantity=?,sub_total=? WHERE id = ?";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1,orderLineItem.getGoods().getId());
            ps.setString(2,orderLineItem.getOrders().getId());
            ps.setInt(3,orderLineItem.getQuantity());
            ps.setDouble(4,orderLineItem.getSubTotal());
            ps.setLong(5,orderLineItem.getId());
            return ps;
        });
    }

    @Override
    public void remove(long pk) {
        String query = "DELETE FROM orderlineitems WHERE id = ?";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1,pk);
            return ps;
        });
    }

    private void createSingleOrderLineItem(ResultSet rs, OrderLineItem orderLineItem) throws SQLException {
        orderLineItem.setId(rs.getLong("id"));
        orderLineItem.setQuantity(rs.getInt("quantity"));
        orderLineItem.setSubTotal(rs.getDouble("sub_total"));

        Orders orders = new Orders();
        orders.setId(rs.getString("orderid"));
        orderLineItem.setOrders(orders);

        Goods goods = new Goods();
        goods.setId(rs.getLong("goodsid"));
        orderLineItem.setGoods(goods);
    }
}
