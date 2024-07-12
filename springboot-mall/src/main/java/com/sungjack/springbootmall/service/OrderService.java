package com.sungjack.springbootmall.service;


import com.sungjack.springbootmall.dto.CreateOrderRequest;

public interface OrderService {

Integer createOrder(Integer id, CreateOrderRequest createOrderRequest);


}
