package com.tourism.demo.service;

import com.tourism.demo.pojo.user;
import com.tourism.demo.utils.ResultInfo;

import javax.mail.MessagingException;
import java.util.List;

public interface User {
//    注册
    ResultInfo insertNewUser(user user);

//    登录
    ResultInfo SelectUsernameByPassWord(user user);
//    找回密码
    ResultInfo SendEmail(String email, String username) throws MessagingException;

    ResultInfo UpdatePasswordByUsernmae(String toekn, String password);

    ResultInfo UpdateMail(String username, String email);

    ResultInfo UpdatePasswordByPersonCenter(String username, String nowpassword, String newpassword);

    ResultInfo selectUserByName(Integer currentPage, Integer currentSize, String name);

    ResultInfo deleteUserById(int id);

    ResultInfo deleteUserByIdMore(List<user> list);

    ResultInfo updataUser(user user);
}
