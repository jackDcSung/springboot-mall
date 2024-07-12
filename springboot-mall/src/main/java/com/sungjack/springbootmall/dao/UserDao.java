package com.sungjack.springbootmall.dao;


import com.sungjack.springbootmall.dto.UserRegisterRequest;
import com.sungjack.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);





}
