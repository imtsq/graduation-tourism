package com.tourism.demo.controller.Book;

import com.tourism.demo.pojo.book;
import com.tourism.demo.service.Book;
import com.tourism.demo.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    com.tourism.demo.service.Book Book;
//    购买门票
    @PostMapping("addBook")
    public ResultInfo addBook(@RequestBody  book book) {
        System.out.println(book);
        return Book.addBook(book);
    }
//    打印门票
    @PostMapping("selectBookMsg")
    public ResultInfo selectBookMsg(@RequestBody Map<String, Object> map) {
        String number = (String) map.get("number");
        String username = (String) map.get("username");
        System.out.println(number);
        return Book.selectBookMsg(number, username);
    }
//    按照查询历史记录
    @GetMapping("selectBookByUsername")
    public ResultInfo selectBookByUsername(@RequestParam("username") String username) {
        return Book.selectBookByUsername(username);
    }
//    查询所有的
    @PostMapping("selectAllBook")
    public ResultInfo selectAllBook (@RequestBody Map<String, Object> map) {
        Integer currentPage = (Integer) map.get("currentPage");
        Integer currentSize = (Integer) map.get("currentSize");
        String name = (String) map.get("name");
        return Book.selectAllBook(currentPage, currentSize, name);
    }
    @PostMapping("deleteBookMore")
    public ResultInfo deleteBookMore (@RequestBody List<book> list) {
        return Book.deleteBookMore(list);
    }
}
