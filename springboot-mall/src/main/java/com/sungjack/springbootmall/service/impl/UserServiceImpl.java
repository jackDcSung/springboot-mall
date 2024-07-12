package com.sungjack.springbootmall.service.impl;


import com.sungjack.springbootmall.dao.UserDao;
import com.sungjack.springbootmall.dto.UserLoginRequset;
import com.sungjack.springbootmall.dto.UserRegisterRequest;
import com.sungjack.springbootmall.model.User;
import com.sungjack.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
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
        //使用MD5 生成密碼的雜湊值(記得加上.getBytes，才能轉為Byte類型)
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());

        userRegisterRequest.setPassword(hashedPassword);


        //創建帳號
        // 命名createuder因為它裡面單純實作
        return userDao.createUser(userRegisterRequest);

    }

    @Override
    public User login(UserLoginRequset userLoginRequset) {

        User user = userDao.getUserByEmail(userLoginRequset.getEmail());


        //檢查user是否存在
        if (user == null) {
            log.warn("該 email {} 尚未註冊", userLoginRequset.getEmail());

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        //使用MD5生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequset.getPassword().getBytes());

        //切記，比較字串一定要用equals方法，不能用==
        //比較密碼
        if (user.getPassword().equals(hashedPassword)) {

            return user;


        } else {
            log.warn("email {} 的密碼不正確", userLoginRequset.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


    }
}
