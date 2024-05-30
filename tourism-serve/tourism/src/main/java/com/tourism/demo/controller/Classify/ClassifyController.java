package com.tourism.demo.controller.Classify;


import com.tourism.demo.pojo.classify;
import com.tourism.demo.service.Classify;
import com.tourism.demo.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClassifyController {
    @Autowired
    com.tourism.demo.service.Classify Classify;
    @PostMapping("addNeweClassify")
    public ResultInfo addNeweClassify(@RequestBody  classify classify){
        return Classify.addNeweClassify(classify);
    }

    @GetMapping("selectAllClassify")
    public ResultInfo selectAllClassify(){
        return Classify.selectAllClassify();
    }
//    删除
    @PostMapping("deleteClassifyOne")
    public  ResultInfo deleteClassifyOne(@RequestBody classify classify) {
        // 处理单个实体
        return Classify.deleteClassifyOne(classify);
    }

    // 接收实体列表的API
    @PostMapping("deleteAllClassify")
    public  ResultInfo deleteAllClassify(@RequestBody List<classify> list) {
        // 处理实体列表
        return Classify.deleteAllClassifyOne(list);
    }
    @PostMapping("updateClassify")
    public ResultInfo updateClassify(@RequestBody classify classify) {
        return Classify.updateClassify(classify);
    }

}
