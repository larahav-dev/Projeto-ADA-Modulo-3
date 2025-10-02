package com.example.ecommerce.model;

import java.time.LocalDateTime; import java.util.*;

public class Order {
    private Long id; private Long customerId; private LocalDateTime createdAt; private OrderStatus status; private PaymentStatus paymentStatus; private List<OrderItem> items=new ArrayList<>();
    public Order(){} public Order(Long id,Long customerId){this.id=id;this.customerId=customerId;this.createdAt=LocalDateTime.now();this.status=OrderStatus.OPEN;this.paymentStatus=PaymentStatus.NONE;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getCustomerId(){return customerId;} public void setCustomerId(Long customerId){this.customerId=customerId;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
    public OrderStatus getStatus(){return status;} public void setStatus(OrderStatus status){this.status=status;}
    public PaymentStatus getPaymentStatus(){return paymentStatus;} public void setPaymentStatus(PaymentStatus paymentStatus){this.paymentStatus=paymentStatus;}
    public List<OrderItem> getItems(){return items;} public void setItems(List<OrderItem> items){this.items=items;}
    public double getTotal(){return items.stream().mapToDouble(OrderItem::getSubtotal).sum();}
}