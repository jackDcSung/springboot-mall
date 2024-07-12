package com.sungjack.springbootmall.dto;


import jakarta.validation.constraints.NotNull;

public class BuyItem {



    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantiy(Integer quantiy) {
        this.quantity = quantiy;
    }
}
