package com.sungjack.springbootmall.service.impl;


import com.sungjack.springbootmall.dao.UserDao;
import com.sungjack.springbootmall.dto.UserRegisterRequest;
import com.sungjack.springbootmall.model.User;
import com.sungjack.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@Component
public class UserServiceImpl implements UserService {


    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;


    @Override
    public User getUserById(Integer userId) {


        return userDao.getUserById(userId);

    }

    //下節講為什麼用register
    @Override

    public Integer register(UserRegisterRequest userRegisterRequest) {
        //除了創建帳號還會增加一些實作，所以才叫register


        //檢查註冊的email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null) {
            log.warn("該 email {} 已經被註冊了", userRegisterRequest.getEmail());//用{}表示變數
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }

//創建帳
// 命名createuder因為它裡面單純實作
        return userDao.createUser(userRegisterRequest);

    }
}
