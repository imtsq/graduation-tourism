package com.tourism.demo.service;

import com.tourism.demo.pojo.book;
import com.tourism.demo.utils.ResultInfo;

import java.util.List;

public interface Book {
    ResultInfo addBook(book book);

    ResultInfo selectBookMsg(String number, String username);

    ResultInfo selectBookByUsername(String username);

    ResultInfo selectAllBook(Integer currentPage, Integer currentSize, String name);

    ResultInfo deleteBookMore(List<book> list);
}
