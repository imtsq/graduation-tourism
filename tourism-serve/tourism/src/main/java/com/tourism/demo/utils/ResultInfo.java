package com.tourism.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultInfo {
    private Integer Code;
    private String Msg;
    private Object Data;
}

