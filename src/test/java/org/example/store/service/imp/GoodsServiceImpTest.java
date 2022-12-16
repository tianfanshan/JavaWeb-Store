package org.example.store.service.imp;

import org.example.store.domain.Goods;
import org.example.store.service.GoodsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoodsServiceImpTest {

    GoodsService goodsService;

    @BeforeEach
    void setUp() {
        goodsService = new GoodsServiceImp();
    }

    @AfterEach
    void tearDown() {
        goodsService = null;
    }

    @Test
    void queryAll() {
        List<Goods> goodsList = goodsService.queryAll();
        assertEquals(34, goodsList.size());
        for (Goods goods : goodsList){
            assertNotNull(goods.getName());
            assertNotNull(goods.getDescription());
            assertNotNull(goods.getImage());
        }
        Goods goods = goodsList.get(2);
        assertNotNull(goods);
        assertEquals(3L,goods.getId());
        assertEquals("联想天逸510S",goods.getName());
        assertEquals(3099,goods.getPrice());
        assertEquals("联想（Lenovo）天逸510S商用台式办公电脑整机（i3-7100 4G 1T 集显 WiFi 蓝牙 三年上门 win10）19.5英寸",goods.getDescription());
        assertEquals("联想（Lenovo）",goods.getBrand());
        assertEquals("Intel ",goods.getCpuBrand());
        assertEquals("Intel i3",goods.getCpuType());
        assertEquals("4G",goods.getMemoryCapacity());
        assertEquals("1T",goods.getHdCapacity());
        assertEquals("集成显卡",goods.getCardModel());
        assertEquals("",goods.getDisplaySize());
        assertEquals("5a6e946eNd622e938.jpg",goods.getImage());
    }

    @Test
    void queryGoodsByPage() {
        List<Goods> goodsList = goodsService.queryGoodsByPage(0,10);
        assertEquals(10,goodsList.size());
    }

    @Test
    void queryDetail() {
        Goods goods = goodsService.queryDetail(3L);
        assertNotNull(goods);
        assertEquals(3L,goods.getId());
        assertEquals("联想天逸510S",goods.getName());
        assertEquals(3099,goods.getPrice());
        assertEquals("联想（Lenovo）天逸510S商用台式办公电脑整机（i3-7100 4G 1T 集显 WiFi 蓝牙 三年上门 win10）19.5英寸",goods.getDescription());
        assertEquals("联想（Lenovo）",goods.getBrand());
        assertEquals("Intel ",goods.getCpuBrand());
        assertEquals("Intel i3",goods.getCpuType());
        assertEquals("4G",goods.getMemoryCapacity());
        assertEquals("1T",goods.getHdCapacity());
        assertEquals("集成显卡",goods.getCardModel());
        assertEquals("",goods.getDisplaySize());
        assertEquals("5a6e946eNd622e938.jpg",goods.getImage());
    }
}