package com.sungjack.springbootmall.service.impl;


import com.sungjack.springbootmall.dao.UserDao;
import com.sungjack.springbootmall.dto.UserRegisterRequest;
import com.sungjack.springbootmall.model.User;
import com.sungjack.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl  implements UserService {


    @Autowired
    private UserDao userDao;


    @Override
    public User getUserById(Integer userId) {







        return  userDao.getUserById(userId);

    }

    //下節講為什麼用register
    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {



        return userDao.createUser(userRegisterRequest);

    }
}
