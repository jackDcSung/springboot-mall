package com.sungjack.springbootmall.service.impl;


import com.sungjack.springbootmall.dao.OrderDao;
import com.sungjack.springbootmall.dao.ProductDao;
import com.sungjack.springbootmall.dto.BuyItem;
import com.sungjack.springbootmall.dto.CreateOrderRequest;
import com.sungjack.springbootmall.model.OrderItem;
import com.sungjack.springbootmall.model.Product;
import com.sungjack.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional//一定要加這個!!!!
    //實作createOrder的方法上分為兩個部分
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmout = 0;

        List<OrderItem> orderItemsList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById((buyItem.getProductId()));

            int amount = buyItem.getProductId() * product.getPrice();

            totalAmout = totalAmout + amount;


            //轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemsList.add(orderItem);
            //這樣就可以把前端傳過來的buyItem資訊，去轉換成orderItem
            //並且同時計算每一個品項的價格




        }


        //創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmout);
        //加s表示插入多筆數據
        orderDao.createOrderItems(orderId,orderItemsList);//表示這些orderItem是對應在哪一張訂單上面


        return orderId;

    }
}
