package org.example.store.domain;

import java.util.Date;
import java.util.List;

public class Orders {

    private String id;
    private Date orderDate;
    private int status = 1;
    private double total;

//    private List<OrderLineItem> orderLineItemList;

    public Orders() {

    }

    public Orders(String id, Date orderDate, int status, double total) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", total=" + total +
                '}';
    }
}
