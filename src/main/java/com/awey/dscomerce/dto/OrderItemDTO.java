package com.awey.dscomerce.dto;

import com.awey.dscomerce.entities.OrderItem;

public class OrderItemDTO {
    private Long id;
    private String name;
    private double price;
    private Integer quantity;



    public OrderItemDTO(Long id, String name, double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItemDTO(OrderItem entity) {
        id = entity.getProduct().getId();
        name = entity.getProduct().getName();
        price = entity.getPrice();
        quantity = entity.getQuantity();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public double getSubTotal(){
        return price * quantity;
    }


}
