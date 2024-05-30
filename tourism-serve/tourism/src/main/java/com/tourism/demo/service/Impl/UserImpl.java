package com.tourism.demo.service.Impl;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tourism.demo.mapper.UserMapper;
import com.tourism.demo.pojo.ticket;
import com.tourism.demo.pojo.user;
import com.tourism.demo.service.User;
import com.tourism.demo.utils.EmailContent;
import com.tourism.demo.utils.JwtUtils;
import com.tourism.demo.utils.JwtUtilsUser;
import com.tourism.demo.utils.ResultInfo;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserImpl implements User {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public ResultInfo insertNewUser(user user) {
        log.info(user.toString());
        QueryWrapper<com.tourism.demo.pojo.user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        com.tourism.demo.pojo.user one = userMapper.selectOne(queryWrapper);
        if (one != null) {
            return new ResultInfo(500, "用户已注册", null);
        }
        int i = userMapper.insert(user);
        return i >0 ? new ResultInfo(200, "注册成功", null) : new ResultInfo(500, "注册失败", null);
    }

    @Override
    public ResultInfo SelectUsernameByPassWord(user user) {
        QueryWrapper<user> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        user user1 = userMapper.selectOne(wrapper);
        if (user1 == null) {
            return new ResultInfo(500, "用户未注册", null);
        }
        if (user1.getStatus().equals("封禁")) {
            return new ResultInfo(500, "登录失败：账号被封禁", null);
        }

        if (user1.getPassword().equals(user.getPassword())){
            Map<String, Object> map = new HashMap<>();
            map.put(user.getUsername(), user.getPassword());
            String token = JwtUtils.generateJwt(map);
            log.info(token);
            return new ResultInfo(200, "登录成功", token);
        }
        return new ResultInfo(500, "密码错误", null);
    }

    @Override
    public ResultInfo SendEmail(String email, String username) throws MessagingException {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        queryWrapper.eq("username", username);
        user user1 = userMapper.selectOne(queryWrapper);
        if (user1 == null) {
            log.info("查询失败");
            return new ResultInfo(404, "邮箱错误", null);

        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("1902323313@qq.com");
        helper.setTo(email);
        helper.setSubject("账号找回");
        helper.setText(EmailContent.getEmailUrl(username), true);

        mailSender.send(message);
        log.info("发送邮箱成功");
        return new ResultInfo(200, "发送邮箱成功", null);
    }

    @Override
    public ResultInfo UpdatePasswordByUsernmae(String token, String password) {
        Claims claims = JwtUtilsUser.parseJWT(token);
        if (claims == null) {
            return new ResultInfo(500, "错误", null);
        }
        Object username = claims.get("username");
        UpdateWrapper<user> wrapper = new UpdateWrapper<>();
        wrapper.eq("username", username);
        wrapper.set("password", password);
        int  i = userMapper.update(null, wrapper);
        return i > 0 ? new ResultInfo(200, "修改成功", null) : new ResultInfo(500, "修改失败", null);
    }

    @Override
    public ResultInfo UpdateMail(String username, String email) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        user one = userMapper.selectOne(queryWrapper);
        one.setEmail(email);
        userMapper.updateById(one);
        return new ResultInfo(200, "success", null);
    }

    @Override
    public ResultInfo UpdatePasswordByPersonCenter(String username,String nowpassword, String newpassword) {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        user one = userMapper.selectOne(queryWrapper);
        if (!nowpassword.equals(one.getPassword())) {
            return new ResultInfo(500, "密码错误", null);
        }
        one.setPassword(newpassword);
        userMapper.updateById(one);
        return new ResultInfo(200, "success", null);
    }

    /***
     * 后台管理
        查询用户
     */
    @Override
    public ResultInfo selectUserByName(Integer currentPage, Integer currentSize, String name) {
        Page<user> page = new Page<>(currentPage, currentSize);
        QueryWrapper<user> wrapper = new QueryWrapper<>();
        wrapper.like("username", name).or().like("email", name).or().like("status", name);
        wrapper.orderByDesc("id");
        Page<user> newsPage = userMapper.selectPage(page, wrapper);
        return new ResultInfo(200, "success", newsPage);
    }

    @Override
    public ResultInfo deleteUserById(int id) {
        int i  = userMapper.deleteById(id);

        return i > 0 ? new ResultInfo(200, "success",null): new ResultInfo(500, "fail",null);
    }

    @Override
    public ResultInfo deleteUserByIdMore(List<user> list) {
        for(user user : list) {
            userMapper.deleteById(user.getId());
        }
        return new ResultInfo(200, "success", null);
    }

    @Override
    public ResultInfo updataUser(user user) {
        userMapper.updateById(user);
        return new ResultInfo(200, "success", null);
    }


}
