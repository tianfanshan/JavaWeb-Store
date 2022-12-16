package org.example.store.service.imp;

import org.example.store.dao.GoodsDAO;
import org.example.store.dao.imp.GoodsDAOImp;
import org.example.store.domain.Goods;
import org.example.store.service.GoodsService;

import java.util.List;

public class GoodsServiceImp implements GoodsService {

    GoodsDAO goodsDAO = new GoodsDAOImp();

    @Override
    public List<Goods> queryAll() {
        return goodsDAO.findAll();
    }

    @Override
    public List<Goods> queryGoodsByPage(int start, int end) {
        return goodsDAO.findStartEnd(start,end);
    }

    @Override
    public Goods queryDetail(long id) {

        return goodsDAO.findByPk(id);
    }

}
