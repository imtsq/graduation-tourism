package com.tourism.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("book")
public class book {
    private int id;
    private String username;
    private String ticketname;
    private double price;
    private String number;
}
