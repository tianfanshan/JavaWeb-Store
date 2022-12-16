package org.example.store.dao.imp;

import org.example.db.core.JdbcTemplate;
import org.example.store.dao.GoodsDAO;
import org.example.store.domain.Goods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoodsDAOImpTest {

    GoodsDAO goodsDAO;

    @BeforeEach
    void setUp() {
        goodsDAO = new GoodsDAOImp();
    }

    @AfterEach
    void tearDown() {
        goodsDAO = null;
    }

    @Test
    void findByPk() {
        Goods goods = goodsDAO.findByPk(1L);
        System.out.println(goods);
        assertNotNull(goods);
        assertEquals(1L,goods.getId());
        assertEquals("戴尔(DELL)成就3470高性能商用办公台式电脑整机",goods.getName());
        assertEquals(3399,goods.getPrice());
        assertEquals("戴尔(DELL)成就3470高性能商用办公台式电脑整机(八代i3-8100 8G 1T 四年上门 有线键鼠 FHD宽屏)21.5英寸 ",goods.getDescription());
        assertEquals("",goods.getBrand());
        assertEquals("",goods.getCpuBrand());
        assertEquals("",goods.getCpuType());
        assertEquals("",goods.getMemoryCapacity());
        assertEquals("",goods.getHdCapacity());
        assertEquals("",goods.getCardModel());
        assertEquals("",goods.getDisplaySize());
        assertEquals("5ae00211N25afad2c.jpg",goods.getImage());
    }

    @Test
    void findAll() {
        List<Goods> goodsList = goodsDAO.findAll();
        assertEquals(34,goodsList.size());
        for (Goods goods : goodsList){
            assertNotNull(goods);
        }
        Goods goods = goodsList.get(0);
        assertNotNull(goods);
        assertEquals(1L,goods.getId());
        assertEquals("戴尔(DELL)成就3470高性能商用办公台式电脑整机",goods.getName());
        assertEquals(3399,goods.getPrice());
        assertEquals("戴尔(DELL)成就3470高性能商用办公台式电脑整机(八代i3-8100 8G 1T 四年上门 有线键鼠 FHD宽屏)21.5英寸 ",goods.getDescription());
        assertEquals("",goods.getBrand());
        assertEquals("",goods.getCpuBrand());
        assertEquals("",goods.getCpuType());
        assertEquals("",goods.getMemoryCapacity());
        assertEquals("",goods.getHdCapacity());
        assertEquals("",goods.getCardModel());
        assertEquals("",goods.getDisplaySize());
        assertEquals("5ae00211N25afad2c.jpg",goods.getImage());
    }

    @Test
    void findStartEnd() {
        List<Goods> goodsList = goodsDAO.findStartEnd(0,10);
        assertEquals(10,goodsList.size());
        long i = 0;
        for (Goods goods : goodsList){
            i++;
            assertEquals(i,goods.getId());
        }
        Goods goods = goodsList.get(0);
        assertNotNull(goods);
        assertEquals(1L,goods.getId());
        assertEquals("戴尔(DELL)成就3470高性能商用办公台式电脑整机",goods.getName());
        assertEquals(3399,goods.getPrice());
        assertEquals("戴尔(DELL)成就3470高性能商用办公台式电脑整机(八代i3-8100 8G 1T 四年上门 有线键鼠 FHD宽屏)21.5英寸 ",goods.getDescription());
        assertEquals("",goods.getBrand());
        assertEquals("",goods.getCpuBrand());
        assertEquals("",goods.getCpuType());
        assertEquals("",goods.getMemoryCapacity());
        assertEquals("",goods.getHdCapacity());
        assertEquals("",goods.getCardModel());
        assertEquals("",goods.getDisplaySize());
        assertEquals("5ae00211N25afad2c.jpg",goods.getImage());
    }

    @Test
    void create() {
        Goods goods = new Goods();
        goods.setId(9999);
        goods.setName("苹果Mac Mini");
        goods.setPrice(5000);
        goods.setDescription("苹果Mac Mini 2018年初");
        goods.setBrand("苹果");
        goods.setCpuBrand("Intel");
        goods.setCpuType("i5");
        goods.setMemoryCapacity("8G");
        goods.setHdCapacity("500G");
        goods.setCardModel("GTX 9系/7系");
        goods.setDisplaySize("无");
        goods.setImage("aaa.jpg");

        goodsDAO.create(goods);
        Goods goods1 = goodsDAO.findByPk(9999);
        assertNotNull(goods1);

        assertEquals(9999L,goods1.getId());
        assertEquals("苹果Mac Mini",goods1.getName());
        assertEquals(5000,goods1.getPrice());
        assertEquals("苹果Mac Mini 2018年初",goods1.getDescription());
        assertEquals("苹果",goods1.getBrand());
        assertEquals("Intel",goods1.getCpuBrand());
        assertEquals("i5",goods1.getCpuType());
        assertEquals("8G",goods1.getMemoryCapacity());
        assertEquals("500G",goods1.getHdCapacity());
        assertEquals("GTX 9系/7系",goods1.getCardModel());
        assertEquals("无",goods1.getDisplaySize());
        assertEquals("aaa.jpg",goods1.getImage());

        assertEquals(35,goodsDAO.findAll().size());

        goodsDAO.remove(9999);
    }

    @Test
    void modify() {
        Goods goods = new Goods();
        goods.setId(9999);
        goods.setName("苹果Mac Mini");
        goods.setPrice(5000);
        goods.setDescription("苹果Mac Mini 2018年初");
        goods.setBrand("苹果");
        goods.setCpuBrand("Intel");
        goods.setCpuType("i5");
        goods.setMemoryCapacity("8G");
        goods.setHdCapacity("500G");
        goods.setCardModel("GTX 9系/7系");
        goods.setDisplaySize("无");
        goods.setImage("aaa.jpg");

        goodsDAO.create(goods);

        Goods goods1 = new Goods();
        goods1.setId(9999);
        goods1.setName("苹果Mac Pro");
        goods1.setPrice(12000);
        goods1.setDescription("苹果Mac Pro笔记本 2018年初");
        goods1.setBrand("苹果");
        goods1.setCpuBrand("Intel");
        goods1.setCpuType("i7");
        goods1.setMemoryCapacity("8G");
        goods1.setHdCapacity("500G固态硬盘");
        goods1.setCardModel("GTX");
        goods1.setDisplaySize("15寸");
        goods1.setImage("ab.jpg");

        goodsDAO.modify(goods1);

        assertEquals(9999L,goods1.getId());
        assertEquals("苹果Mac Pro",goods1.getName());
        assertEquals(12000,goods1.getPrice());
        assertEquals("苹果Mac Pro笔记本 2018年初",goods1.getDescription());
        assertEquals("苹果",goods1.getBrand());
        assertEquals("Intel",goods1.getCpuBrand());
        assertEquals("i7",goods1.getCpuType());
        assertEquals("8G",goods1.getMemoryCapacity());
        assertEquals("500G固态硬盘",goods1.getHdCapacity());
        assertEquals("GTX",goods1.getCardModel());
        assertEquals("15寸",goods1.getDisplaySize());
        assertEquals("ab.jpg",goods1.getImage());

        goodsDAO.remove(9999);
    }

    @Test
    void remove() {
        Goods goods = new Goods();
        goods.setId(9999);
        goods.setName("苹果Mac Mini");
        goods.setPrice(5000);
        goods.setDescription("苹果Mac Mini 2018年初");
        goods.setBrand("苹果");
        goods.setCpuBrand("Intel");
        goods.setCpuType("i5");
        goods.setMemoryCapacity("8G");
        goods.setHdCapacity("500G");
        goods.setCardModel("GTX 9系/7系");
        goods.setDisplaySize("无");
        goods.setImage("aaa.jpg");

        goodsDAO.create(goods);

        Goods goods1 = goodsDAO.findByPk(9999);

        assertNotNull(goods1);

        goodsDAO.remove(9999);

        goods1 = goodsDAO.findByPk(9999);

        assertEquals(0,goods1.getId());
        assertNull(goods1.getName());
        assertEquals(0.0,goods1.getPrice());
        assertNull(goods1.getDescription());
        assertNull(goods1.getBrand());
        assertNull(goods1.getCpuBrand());
        assertNull(goods1.getCpuType());
        assertNull(goods1.getMemoryCapacity());
        assertNull(goods1.getHdCapacity());
        assertNull(goods1.getCardModel());
        assertNull(goods1.getDisplaySize());
        assertNull(goods1.getImage());
    }
}