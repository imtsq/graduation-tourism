package com.tourism.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tourism.demo.mapper.classifyMapper;
import com.tourism.demo.pojo.classify;
import com.tourism.demo.service.Classify;
import com.tourism.demo.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class classifyImpl implements Classify {
    @Autowired
    com.tourism.demo.mapper.classifyMapper classifyMapper;


    @Override
    public ResultInfo addNeweClassify(classify classify) {
        System.out.println(classify);
        QueryWrapper<com.tourism.demo.pojo.classify> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", classify.getName());
        com.tourism.demo.pojo.classify classify1 = classifyMapper.selectOne(queryWrapper);
        if (classify1 != null) {
            return new ResultInfo(200, "重复", null);
        }
        classifyMapper.insert(classify);

        return new ResultInfo(200, "success", null);
    }

    @Override
    public ResultInfo selectAllClassify() {
        List<classify> list = classifyMapper.selectList(null);
        return new ResultInfo(200, "success", list);
    }

    @Override
    public ResultInfo deleteAllClassifyOne(List<classify> list) {
        for (classify item : list) {
            classifyMapper.deleteById(item.getId());
        }
        return new ResultInfo(200, "success", null);
    }

    @Override
    public ResultInfo deleteClassifyOne(classify classify) {
        int i = classifyMapper.deleteById(classify.getId());
        return new ResultInfo(200, "success", null);

    }

    @Override
    public ResultInfo updateClassify(classify classify) {
        QueryWrapper<com.tourism.demo.pojo.classify> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", classify.getName());
        com.tourism.demo.pojo.classify classify1 = classifyMapper.selectOne(queryWrapper);
        if (classify1 != null) {
            return new ResultInfo(200, "重复", null);
        }
        classifyMapper.updateById(classify);
        return new ResultInfo(200, "success", null);
    }


}
