package com.application.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.provider.TicketSender;


@RestController
public class SendController {
 
    @Autowired
    private TicketSender ticketSender;
 
    @GetMapping("/send/unpay")
    public String sendUnpay(String message){
        String uuid = UUID.randomUUID().toString();
        ticketSender.sendTicketUnpay(uuid,message);
        return uuid;
    }
    
    @GetMapping("/send/uncheck")
    public String sendUncheck(String message){
        String uuid = UUID.randomUUID().toString();
        ticketSender.sendTicketUncheck(uuid, message);
        return uuid;
    }
    
    @GetMapping("/send/fanout")
    public String sendFanout(String message){
        String uuid = UUID.randomUUID().toString();
        ticketSender.sendFanout(uuid, message);
        return uuid;
    }
    
    @GetMapping("/send/no")
    public String sendNo(String message){
        String uuid = UUID.randomUUID().toString();
        ticketSender.sendNo(uuid, message);
        return uuid;
    }
}
