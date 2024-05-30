package com.tourism.demo.Vo;

import com.tourism.demo.pojo.book;
import com.tourism.demo.pojo.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndBook {
    com.tourism.demo.pojo.user user;
    com.tourism.demo.pojo.book book;
}
