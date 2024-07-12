package com.sungjack.springbootmall.dao;


import com.sungjack.springbootmall.dto.UserRegisterRequest;
import com.sungjack.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);


    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);





}
