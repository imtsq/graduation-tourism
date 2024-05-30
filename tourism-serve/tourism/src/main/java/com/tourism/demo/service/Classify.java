package com.tourism.demo.service;

import com.tourism.demo.pojo.classify;
import com.tourism.demo.utils.ResultInfo;

import java.util.List;

public interface Classify {
    ResultInfo addNeweClassify(classify classify);

    ResultInfo selectAllClassify();


    ResultInfo deleteAllClassifyOne(List<classify> list);

    ResultInfo deleteClassifyOne(classify classify);

    ResultInfo updateClassify(classify classify);
}
