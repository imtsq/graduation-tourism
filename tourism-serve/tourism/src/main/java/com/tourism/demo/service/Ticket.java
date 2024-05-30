package com.tourism.demo.service;

import com.tourism.demo.pojo.ticket;
import com.tourism.demo.utils.ResultInfo;

import java.util.List;

public interface Ticket {
    ResultInfo addticket(ticket ticket);


    ResultInfo selectAllTicket(Integer currentPage, Integer currentSize, String name);

    ResultInfo deleteTicketOne(ticket ticket);

    ResultInfo deleteAllTicket(List<ticket> list);

    ResultInfo updateTicket(ticket ticket);

    ResultInfo selectByOther(String classify, boolean hotOrNew, String keyword);

    ResultInfo selectTicketById(String id);

    ResultInfo selectfrontFive();
}
