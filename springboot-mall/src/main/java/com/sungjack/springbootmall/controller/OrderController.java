package com.sungjack.springbootmall.controller;


import com.sungjack.springbootmall.dto.CreateOrderRequest;
import com.sungjack.springbootmall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;

    //記得去思考此功能是否是其他功能的附屬品
    //為什麼要這樣設計是因為使用者一定要先有個帳號，才能去有一筆訂單(此處應用了Restful的url路徑設計)
    @PostMapping("/users/{userId}/orders")//一定要加上valid註解
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest) {

        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }


}
