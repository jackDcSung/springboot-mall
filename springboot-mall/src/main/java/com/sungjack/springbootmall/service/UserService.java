package com.sungjack.springbootmall.service;


import com.sungjack.springbootmall.dto.UserRegisterRequest;
import com.sungjack.springbootmall.model.User;

public interface UserService {


    User getUserById(Integer userId);


    Integer register(UserRegisterRequest userRegisterRequest);





}
