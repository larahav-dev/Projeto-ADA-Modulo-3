package com.example.ecommerce.model;

public class OrderItem {
    private Long productId; private String productName; private int quantity; private double salePrice;
    public OrderItem() {} public OrderItem(Long productId,String productName,int quantity,double salePrice){this.productId=productId;this.productName=productName;this.quantity=quantity;this.salePrice=salePrice;}
    public Long getProductId(){return productId;} public void setProductId(Long productId){this.productId=productId;}
    public String getProductName(){return productName;} public void setProductName(String productName){this.productName=productName;}
    public int getQuantity(){return quantity;} public void setQuantity(int quantity){this.quantity=quantity;}
    public double getSalePrice(){return salePrice;} public void setSalePrice(double salePrice){this.salePrice=salePrice;}
    public double getSubtotal(){return salePrice*quantity;}
}