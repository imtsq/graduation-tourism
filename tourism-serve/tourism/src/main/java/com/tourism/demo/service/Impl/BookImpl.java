package com.tourism.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tourism.demo.Vo.UserAndBook;
import com.tourism.demo.mapper.UserMapper;
import com.tourism.demo.mapper.bookMapper;
import com.tourism.demo.pojo.book;
import com.tourism.demo.pojo.ticket;
import com.tourism.demo.pojo.user;
import com.tourism.demo.service.Book;
import com.tourism.demo.utils.IdentityCardUtil;
import com.tourism.demo.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookImpl implements Book {
    @Autowired
    bookMapper bookMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    com.tourism.demo.mapper.ticketMapper ticketMapper;
    @Override
    public ResultInfo addBook(book book) {
        QueryWrapper<com.tourism.demo.pojo.ticket> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", book.getTicketname());
        com.tourism.demo.pojo.ticket one = ticketMapper.selectOne(queryWrapper);
        if (one.getNum() <= 0) {
            return new ResultInfo(200, "门票数量不足", null);
        }
        int i = bookMapper.insert(book);


        one.setBuynum(one.getBuynum() + 1);
        //更新
//        购买并发问题
        one.setNum(one.getNum() - 1);
        ticketMapper.updateById(one);
        return new ResultInfo(200, "预定成功", null);
    }

    @Override
    public ResultInfo selectBookMsg(String number, String username) {
        System.out.println(number + username);
        QueryWrapper<user> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("username", username);
        user one = userMapper.selectOne( queryWrapper1);
        if (one == null) {
            return new ResultInfo(404, "查询失败", null);
        }
        one.setPassword("*****");
        if (one != null) {
            String identityCard = new IdentityCardUtil().IdentityCard(one.getIdentityCard());
            one.setIdentityCard(identityCard);
        }
        QueryWrapper<book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("number",number);
        book book = bookMapper.selectOne(queryWrapper);
        if (one == null || book == null) {
            return new ResultInfo(404, "查询失败", null);
        }
        UserAndBook vo = new UserAndBook(one, book);
        return new ResultInfo(200, "查询成功", vo);
    }

    @Override
    public ResultInfo selectBookByUsername(String username) {
        QueryWrapper<book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.orderByDesc("id");
        List<book> list = bookMapper.selectList(queryWrapper);
        return new ResultInfo(200, "success", list);
    }

    @Override
    public ResultInfo selectAllBook(Integer currentPage, Integer currentSize, String name) {
        Page<book> page = new Page<>(currentPage, currentSize);
        QueryWrapper<book> wrapper = new QueryWrapper<>();
        wrapper.like("username", name).or().like("ticketname", name).or().like("number", name);
        wrapper.orderByDesc("id");
        Page<book> newsPage = bookMapper.selectPage(page, wrapper);
        return new ResultInfo(200, "success", newsPage);
    }

    @Override
    public ResultInfo deleteBookMore(List<book> list) {
        for(book item : list) {
            bookMapper.deleteById(item.getId());
        }
        return new ResultInfo(200, "success", null);
    }
}
