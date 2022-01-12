package com.yiyuankafei.athena.data.jpa.controller;

import com.yiyuankafei.athena.data.jpa.domain.Order;
import com.yiyuankafei.athena.data.jpa.domain.RoomDailyOrder;
import com.yiyuankafei.athena.data.jpa.domain.RoomDailyOrderDetail;
import com.yiyuankafei.athena.data.jpa.repositroy.OrderRepository;
import com.yiyuankafei.athena.data.jpa.repositroy.RoomDailyOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RoomDailyOrderDetailRepository roomDailyOrderDetailRepository;

    @RequestMapping("/getById")
    public Order getById(Long id) {
        return orderRepository.findById(id).get();
    }

    @RequestMapping("/getDetailId")
    public RoomDailyOrderDetail getDetailId(Long id) {
        return roomDailyOrderDetailRepository.findById(id).get();
    }

}
