package org.example.store.service;

import org.example.store.domain.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> queryAll();

    List<Goods> queryGoodsByPage(int start,int end);

    Goods queryDetail(long id);
}
