package org.example.store.dao;

import org.example.store.domain.Goods;

import java.util.List;

public interface GoodsDAO {

    Goods findByPk(long pk);

    List<Goods> findAll();

    /**
     * 提供分页查询
     * @param start 开始索引 索引从0开始
     * @param end 结束索引 索引从0开始
     * @return 商品列表
     */
    List<Goods> findStartEnd(int start, int end);

    void create(Goods goods);

    void modify(Goods goods);

    void remove(long pk);
}
