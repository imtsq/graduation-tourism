package com.tourism.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class user {
    @TableId
    private  int id;
    private String username;
    private String password;
    private String name;
    private String identityCard;
    private String email;
    private String status;
}
