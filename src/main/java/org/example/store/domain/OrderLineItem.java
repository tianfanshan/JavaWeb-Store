package org.example.store.domain;

public class OrderLineItem {

    private long id;
    private int quantity;
    private double subTotal;
    private Orders orders;
    private Goods goods;

    public OrderLineItem() {

    }

    public OrderLineItem(long id, int quantity, double subTotal, Orders orders, Goods goods) {
        this.id = id;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.orders = orders;
        this.goods = goods;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "OrderLineItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                ", orders=" + orders +
                ", goods=" + goods +
                '}';
    }
}
