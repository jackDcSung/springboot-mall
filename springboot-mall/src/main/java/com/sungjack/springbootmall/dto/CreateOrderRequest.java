package com.sungjack.springbootmall.dto;


import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateOrderRequest {


    @NotEmpty//用來加在List 或是Map類型的變數，代表裡面至少要有一個值存在才可以
    private List<BuyItem> buyItemList;


    public List<BuyItem> getBuyItemList() {
        return buyItemList;
    }

    public void setBuyItemList(List<BuyItem> buyItemList) {
        this.buyItemList = buyItemList;
    }
}
