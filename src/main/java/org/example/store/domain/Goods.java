package org.example.store.domain;

import java.util.List;

public class Goods {

    private long id;
    private String name;
    private double price;
    private String description;
    private String brand;
    private String cpuBrand;
    private String cpuType;
    private String memoryCapacity;
    private String hdCapacity;
    private String cardModel;
    private String displaySize;
    private String image;

//    private List<OrderLineItem> orderLineItemList;

    public Goods() {

    }

    public Goods(int id, String name, double price, String description, String brand, String cpuBrand, String cpuType, String memoryCapacity, String hdCapacity, String cardModel, String displaySize, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.cpuBrand = cpuBrand;
        this.cpuType = cpuType;
        this.memoryCapacity = memoryCapacity;
        this.hdCapacity = hdCapacity;
        this.cardModel = cardModel;
        this.displaySize = displaySize;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCpuBrand() {
        return cpuBrand;
    }

    public void setCpuBrand(String cpuBrand) {
        this.cpuBrand = cpuBrand;
    }

    public String getCpuType() {
        return cpuType;
    }

    public void setCpuType(String cpuType) {
        this.cpuType = cpuType;
    }

    public String getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(String memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public String getHdCapacity() {
        return hdCapacity;
    }

    public void setHdCapacity(String hdCapacity) {
        this.hdCapacity = hdCapacity;
    }

    public String getCardModel() {
        return cardModel;
    }

    public void setCardModel(String cardModel) {
        this.cardModel = cardModel;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", cpuBrand='" + cpuBrand + '\'' +
                ", cpuType='" + cpuType + '\'' +
                ", memoryCapacity='" + memoryCapacity + '\'' +
                ", cardModel='" + cardModel + '\'' +
                ", displaySize='" + displaySize + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
