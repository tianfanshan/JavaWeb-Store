package org.example.store.dao.imp;

import org.example.db.core.JdbcTemplate;
import org.example.store.dao.OrdersDAO;
import org.example.store.domain.Orders;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersDAOImp implements OrdersDAO {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public Orders findByPk(String pk) {
        String query = "SELECT * FROM orders WHERE id = ?";
        Orders orders = new Orders();
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,pk);
            return ps;
        },rs -> {
            orders.setId(rs.getString("id"));
            orders.setOrderDate(new Date(rs.getLong("order_date")));
            orders.setStatus(rs.getInt("status"));
            orders.setTotal(rs.getDouble("total"));
        });
        return orders;
    }

    @Override
    public List<Orders> findAll() {
        String query = "SELECT * FROM orders";
        List<Orders> ordersList = new ArrayList<>();
        jdbcTemplate.query(conn -> conn.prepareStatement(query), rs -> {
            Orders orders = new Orders();
            orders.setId(rs.getString("id"));
            orders.setOrderDate(new Date(rs.getLong("order_date")));
            orders.setStatus(rs.getInt("status"));
            orders.setTotal(rs.getDouble("total"));
            ordersList.add(orders);
        });
        return ordersList;
    }

    @Override
    public void create(Orders orders) {
        String query = "INSERT INTO orders (id,order_date,status,total) VALUES (?,?,?,?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,orders.getId());
            ps.setLong(2,orders.getOrderDate().getTime());
            ps.setInt(3,orders.getStatus());
            ps.setDouble(4,orders.getTotal());
            return ps;
        });
    }

    @Override
    public void modify(Orders orders) {
        String query = "UPDATE orders SET order_date=?,status=?,total=? WHERE id = ?";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1,orders.getOrderDate().getTime());
            ps.setInt(2,orders.getStatus());
            ps.setDouble(3,orders.getTotal());
            ps.setString(4,orders.getId());
            return ps;
        });
    }

    @Override
    public void remove(String pk) {
        String query = "DELETE FROM orders WHERE id = ?";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,pk);
            return ps;
        });
    }
}
