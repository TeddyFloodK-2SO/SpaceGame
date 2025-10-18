package com.spacegame.service;

import com.spacegame.dao.UserDao;
import com.spacegame.entity.User;

public class UserService{
    private final UserDao userDao = new UserDao();

//    Метод для регистрации аккаунта после соответствующих проверок
    public boolean registerUser(String username, String password, String email){

//        Проверки на ввод данных от клиента
        if(username == null || username.isBlank()){
            throw new IllegalArgumentException("Username must by empty");
        }
        if(password == null || password.isBlank()){
            throw new IllegalArgumentException("Password must by empty");
        }
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email must by empty");
        }
        if(userDao.findByUsername(username) != null){
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        userDao.saveUser(user);
        return true;
    }

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }
}
