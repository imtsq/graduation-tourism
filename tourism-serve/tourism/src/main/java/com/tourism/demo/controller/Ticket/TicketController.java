package com.tourism.demo.controller.Ticket;


import com.tourism.demo.Vo.ticketVo;
import com.tourism.demo.mapper.ticketMapper;
import com.tourism.demo.pojo.classify;
import com.tourism.demo.pojo.ticket;
import com.tourism.demo.service.Ticket;
import com.tourism.demo.utils.PicStore;
import com.tourism.demo.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class TicketController {
    @Autowired
    com.tourism.demo.service.Ticket Ticket;

    @PostMapping("addticket")
    public ResultInfo addticket( @RequestParam("file") MultipartFile file,
                                 @RequestParam("name") String name,
                                 @RequestParam("grade") String grade,
                                 @RequestParam("status") String status,
                                 @RequestParam("content") String content,
                                 @RequestParam("classify") String classify,
                                 @RequestParam("price") double price,
                                 @RequestParam("num") int num) throws Exception {
        String url = PicStore.savePic(file);
        System.out.println(content);
        ticket ticket = new ticket(0,name,classify,url,content,price,num,grade,status, 0);
        return Ticket.addticket(ticket);
    }
    @PostMapping("selectAllTicket")
    public ResultInfo selectAllTicket(@RequestBody Map<String, Object> map) {
        Integer currentPage = (Integer) map.get("currentPage");
        Integer currentSize = (Integer) map.get("currentSize");
        String name = (String) map.get("name");
        return Ticket.selectAllTicket(currentPage, currentSize, name);
    }
//    删除
    @PostMapping("deleteTicketOne")
    public  ResultInfo deleteTicketOne(@RequestBody ticket ticket) {
        // 处理单个实体
        return Ticket.deleteTicketOne(ticket);
    }

    // 接收实体列表的API
    @PostMapping("deleteAllTicket")
    public  ResultInfo deleteAllTicket(@RequestBody List<ticket> list) {
        // 处理实体列表
        return Ticket.deleteAllTicket(list);
    }
//    修改
    @PostMapping("updateTicket")
    public ResultInfo updateTicket(@RequestBody  ticket ticket) {
        return Ticket.updateTicket(ticket);
    }

//    按条件查询
    @GetMapping("selectByOther")
    public ResultInfo selectByOther(@RequestParam("classify") String classify, @RequestParam("HotOrNew") boolean HotOrNew, @RequestParam("keyword") String keyword){
        return Ticket.selectByOther(classify,HotOrNew, keyword);
    }
//    根据id查询
    @GetMapping("selectTicketById")
    public ResultInfo selectTicketById(@RequestParam("id") String id){
        return Ticket.selectTicketById(id);
    }

//    查询热度前五
    @GetMapping("selectfrontFive")
    public ResultInfo selectfrontFive(){
        System.out.println("tsq");
        return Ticket.selectfrontFive();
    }
}
