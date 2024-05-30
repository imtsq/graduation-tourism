package com.tourism.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ticket")
public class ticket {
    private int id;
    private String name;
    private String classify;
    private String url;
    private String content;
    private double price;
    private int num;
    private String grade;
    private String status;
    private int buynum;
}
