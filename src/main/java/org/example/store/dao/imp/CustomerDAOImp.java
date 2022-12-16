package org.example.store.dao.imp;

import org.example.db.core.JdbcTemplate;
import org.example.store.dao.CustomerDAO;
import org.example.store.domain.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDAOImp implements CustomerDAO {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public Customer findByPk(String pk) {
        String query = "SELECT * FROM customers WHERE id = ?";
        Customer customer = new Customer();
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,pk);
            return ps;
        }, rs -> {
            createSingleCustomerObject(rs, customer);
        });
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        String query = "SELECT * FROM customers";
        List<Customer> customerList = new ArrayList<>();
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            return ps;
        },rs -> {
            Customer customer = new Customer();
            createSingleCustomerObject(rs, customer);
            customerList.add(customer);
        });
        return customerList;
    }

    @Override
    public void create(Customer customer) {
        String query = "INSERT INTO customers (id,name,password,address,phone,birthday) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,customer.getId());
            ps.setString(2,customer.getName());
            ps.setString(3,customer.getPassword());
            ps.setString(4,customer.getAddress());
            ps.setString(5,customer.getPhone());
            ps.setLong(6,customer.getBirthday().getTime());
            return ps;
        });
    }

    @Override
    public void modify(Customer customer) {
        String query = "UPDATE customers SET name=?,password=?,address=?,phone=?,birthday=? WHERE id = ?";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getPassword());
            ps.setString(3,customer.getAddress());
            ps.setString(4,customer.getPhone());
            ps.setLong(5,customer.getBirthday().getTime());
            ps.setString(6,customer.getId());
            return ps;
        });
    }

    @Override
    public void remove(String pk) {
        String query = "DELETE FROM customers WHERE id = ?";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,pk);
            return ps;
        });
    }

    private void createSingleCustomerObject(ResultSet rs, Customer customer) throws SQLException {
        customer.setId(rs.getString("id"));
        customer.setName(rs.getString("name"));
        customer.setPassword(rs.getString("password"));
        customer.setAddress(rs.getString("address"));
        customer.setPhone(rs.getString("phone"));
        customer.setBirthday(new Date(rs.getLong("birthday")));
    }
}
