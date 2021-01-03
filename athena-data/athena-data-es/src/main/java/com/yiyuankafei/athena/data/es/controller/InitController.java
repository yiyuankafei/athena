package com.yiyuankafei.athena.data.es.controller;

import com.yiyuankafei.athena.data.es.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    HotelService hotelService;

    @RequestMapping("/hotel")
    public String initHotel() throws Exception {
        hotelService.downloadBaseInfo();
        return "init success";
    }

}
