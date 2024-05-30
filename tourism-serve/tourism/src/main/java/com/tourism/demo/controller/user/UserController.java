package com.tourism.demo.controller.user;

import com.tourism.demo.pojo.user;
import com.tourism.demo.service.User;
import com.tourism.demo.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    User userService;

    @PostMapping("/insertNewUser")
    public  ResultInfo insertNewUser(@RequestBody  user user) {
        return userService.insertNewUser(user);
    }

    @PostMapping("/SelectUsernameByPassWord")
    ResultInfo SelectUsernameByPassWord(@RequestBody  user user) {
        return userService.SelectUsernameByPassWord(user);
    }
//    发送邮箱找password
    @GetMapping("/SendEmail")
    ResultInfo SendEmail(@RequestParam("email") String email, @RequestParam("username") String username) throws Exception {
        return userService.SendEmail(email, username);
    }
    //修改密码,通过邮箱
    @GetMapping("/UpdatePasswordByUsernmae")
    public ResultInfo UpdatePasswordByUsernmae(@RequestParam("token") String token, @RequestParam("password") String password) {
        return userService.UpdatePasswordByUsernmae(token, password);
    }

//    修改邮箱
    @GetMapping("/UpdateMail")
    public ResultInfo UpdateMail(@RequestParam("username") String username, @RequestParam("email") String email){
        return userService.UpdateMail(username, email);
    }
//    个人中心修改密码
    @GetMapping("/UpdatePasswordByPersonCenter")
    public ResultInfo UpdatePasswordByPersonCenter(@RequestParam("username") String username,@RequestParam("nowpassword") String nowpassword, @RequestParam("newpassword") String newpassword){
        return userService.UpdatePasswordByPersonCenter(username,nowpassword, newpassword);
    }

    /**
     * 后台管理
     * @param
     * @return
     */
//    查询用户
    @PostMapping("/selectUserByName")
    public ResultInfo selectUserByName(@RequestBody Map<String, Object> map) {
        Integer currentPage = (Integer) map.get("currentPage");
        Integer currentSize = (Integer) map.get("currentSize");
        String name = (String) map.get("name");
        return userService.selectUserByName(currentPage, currentSize, name);
    }
//    删除一个
    @GetMapping("/deleteUserById")
    public ResultInfo deleteUserByIdOne(@RequestParam("id") int id) {
        return userService.deleteUserById(id);
    }
    @PostMapping("/deleteUserByIdMore")
    public ResultInfo deleteUserByIdMore(@RequestBody List<user> list) {
        return userService.deleteUserByIdMore(list);
    }
//    修改账号信息，管理员
    @PostMapping("/updataUser")
    public ResultInfo updataUser(@RequestBody user user) {
        return userService.updataUser(user);
    }
}
