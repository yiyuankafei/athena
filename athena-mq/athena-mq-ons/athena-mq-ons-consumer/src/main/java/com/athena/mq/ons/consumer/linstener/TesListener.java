package com.athena.mq.ons.consumer.linstener;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class TesListener implements MessageListener {

    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        String s =  new String(message.getBody());
        System.out.println("==========================" + s);
        return Action.CommitMessage;
    }
}
