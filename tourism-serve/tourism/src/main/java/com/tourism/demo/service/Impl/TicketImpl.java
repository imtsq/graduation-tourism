package com.tourism.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tourism.demo.mapper.ticketMapper;
import com.tourism.demo.pojo.ticket;
import com.tourism.demo.service.Ticket;
import com.tourism.demo.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketImpl implements Ticket {
    @Autowired
    com.tourism.demo.mapper.ticketMapper ticketMapper;
    @Override
    public ResultInfo addticket(ticket ticket) {
        ticket.setBuynum(0);
        QueryWrapper<com.tourism.demo.pojo.ticket> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", ticket.getName());
        com.tourism.demo.pojo.ticket one = ticketMapper.selectOne(queryWrapper);
        if (one != null) {
            return new ResultInfo(500, "重复", null);
        }
        ticketMapper.insert(ticket);
        return new ResultInfo(200, "success", null);
    }

    @Override
    public ResultInfo selectAllTicket(Integer currentPage, Integer currentSize, String name) {
        Page<ticket> page = new Page<>(currentPage, currentSize);
        QueryWrapper<ticket> wrapper = new QueryWrapper<>();
        wrapper.like("name", name).or().like("classify", name).or().like("grade", name);
        wrapper.orderByDesc("id");
        Page<ticket> newsPage = ticketMapper.selectPage(page, wrapper);
        return new ResultInfo(200, "success", newsPage);
    }

    @Override
    public ResultInfo deleteTicketOne(ticket ticket) {
        ticketMapper.deleteById(ticket.getId());
        return new ResultInfo(200, "success", null);
    }

    @Override
    public ResultInfo deleteAllTicket(List<ticket> list) {
       for (ticket item : list) {
           ticketMapper.deleteById(item.getId());
       }
        return new ResultInfo(200, "success", null);
    }

    @Override
    public ResultInfo updateTicket(ticket ticket) {
        System.out.println(ticket);
        com.tourism.demo.pojo.ticket select = ticketMapper.selectById(ticket.getId());
        ticket.setUrl(select.getUrl());
        ticket.setBuynum(select.getBuynum());
        QueryWrapper<com.tourism.demo.pojo.ticket> wrapper = new QueryWrapper<>();
        ticketMapper.updateById(ticket);
        return new ResultInfo(200, "success", null);
    }

    @Override
    public ResultInfo selectByOther(String classify, boolean hotOrNew, String keyword) {
        System.out.println(classify);
        QueryWrapper<ticket> queryWrapper = new QueryWrapper<>();
        if (classify.length() > 0) {
            queryWrapper.eq("classify", classify);
        }
//        true表示最热
        if (hotOrNew) {
            queryWrapper.orderByDesc("buynum");
        }else {
//            表示最新
            queryWrapper.orderByDesc("id");
        }
        queryWrapper.like("name", keyword);
        List<ticket> list = ticketMapper.selectList(queryWrapper);
        return new ResultInfo(200, "success", list);
    }

    @Override
    public ResultInfo selectTicketById(String id) {
        ticket ticket = ticketMapper.selectById(id);
        return new ResultInfo(200, "success", ticket);
    }

    @Override
    public ResultInfo selectfrontFive() {
        QueryWrapper<ticket> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("buynum");
        queryWrapper.last("LIMIT 5");
        List<ticket> list = ticketMapper.selectList(queryWrapper);
        return new ResultInfo(200, "sucess", list);
    }

}
