package com.tourism.demo.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ticketVo {
    private int id;
    private String name;
    private String classify;
    private String content;
    private double price;
    private int num;
    private String grade;
    private String status;

}
