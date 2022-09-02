package com.example.entity;

public class Product {
    private Integer id;
    private String productNo;
    private String productName;

    public Product() {
    }

    public Product(Integer id, String productNo, String productName) {
        this.id = id;
        this.productNo = productNo;
        this.productName = productName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productNo='" + productNo + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}