package org.example.store.dao.imp;

import org.example.db.core.JdbcTemplate;
import org.example.store.dao.GoodsDAO;
import org.example.store.domain.Goods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAOImp implements GoodsDAO {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public Goods findByPk(long pk) {
        String query = "SELECT * FROM goods WHERE id = ?";
        Goods goods = new Goods();
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1, pk);
            return ps;
        }, rs -> {
            createSingleGoodsObject(rs, goods);
        });
        return goods;
    }

    @Override
    public List<Goods> findAll() {
        String query = "SELECT * FROM goods";
        List<Goods> goodsList = new ArrayList<>();
        jdbcTemplate.query(conn -> conn.prepareStatement(query), rs -> {
            Goods goods = new Goods();
            createSingleGoodsObject(rs, goods);
            goodsList.add(goods);
        });
        return goodsList;
    }

    @Override
    public List<Goods> findStartEnd(int start, int end) {
        StringBuffer query = new StringBuffer("SELECT * FROM goods");
        query.append(" LIMIT ").append(end - start);
        query.append(" OFFSET ").append(start);
        List<Goods> goodsList = new ArrayList<>();
        jdbcTemplate.query(conn -> conn.prepareStatement(query.toString()), rs -> {
            Goods goods = new Goods();
            createSingleGoodsObject(rs,goods);
            goodsList.add(goods);
        });
        return goodsList;
    }

    @Override
    public void create(Goods goods) {
        String query =
                "INSERT INTO goods (id,name,price,description,brand,cpu_brand,cpu_type,memory_capacity,hd_capacity,card_model,displaysize,image)" +
                        " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1,goods.getId());
            ps.setString(2,goods.getName());
            ps.setDouble(3,goods.getPrice());
            ps.setString(4,goods.getDescription());
            ps.setString(5,goods.getBrand());
            ps.setString(6,goods.getCpuBrand());
            ps.setString(7,goods.getCpuType());
            ps.setString(8,goods.getMemoryCapacity());
            ps.setString(9,goods.getHdCapacity());
            ps.setString(10,goods.getCardModel());
            ps.setString(11,goods.getDisplaySize());
            ps.setString(12,goods.getImage());
            return ps;
        });
    }

    @Override
    public void modify(Goods goods) {
        String query =
                "UPDATE goods SET name=?,price=?,description=?,brand=?,cpu_brand=?,cpu_type=?,memory_capacity=?,hd_capacity=?,card_model=?,displaysize=?,image=?" +
                        " WHERE id = ?";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,goods.getName());
            ps.setDouble(2,goods.getPrice());
            ps.setString(3,goods.getDescription());
            ps.setString(4,goods.getBrand());
            ps.setString(5,goods.getCpuBrand());
            ps.setString(6,goods.getCpuType());
            ps.setString(7,goods.getMemoryCapacity());
            ps.setString(8,goods.getHdCapacity());
            ps.setString(9,goods.getCardModel());
            ps.setString(10,goods.getDisplaySize());
            ps.setString(11,goods.getImage());
            ps.setLong(12,goods.getId());
            return ps;
        });
    }

    @Override
    public void remove(long pk) {
        String query = "DELETE FROM goods WHERE id = ?";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1,pk);
            return ps;
        });
    }

    private void createSingleGoodsObject(ResultSet rs, Goods goods) throws SQLException {
        goods.setId(rs.getLong("id"));
        goods.setName(rs.getString("name"));
        goods.setPrice(rs.getDouble("price"));
        goods.setDescription(rs.getString("description"));
        goods.setBrand(rs.getString("brand"));
        goods.setCpuBrand(rs.getString("cpu_brand"));
        goods.setCpuType(rs.getString("cpu_type"));
        goods.setMemoryCapacity(rs.getString("memory_capacity"));
        goods.setHdCapacity(rs.getString("hd_capacity"));
        goods.setCardModel(rs.getString("card_model"));
        goods.setDisplaySize(rs.getString("displaysize"));
        goods.setImage(rs.getString("image"));
    }
}
