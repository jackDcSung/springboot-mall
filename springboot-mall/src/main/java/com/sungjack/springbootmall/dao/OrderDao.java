package com.sungjack.springbootmall.dao;

import com.sungjack.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {



Integer createOrder(Integer userId,Integer totalAmout);

void  createOrderItems(Integer orderId, List<OrderItem> orderItemList);




}
