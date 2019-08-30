package com.example.web.system.event.listener;

import com.example.web.system.event.LoginOutEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListeners {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @EventListener
    public void onApplicationEvent(LoginOutEvent loginOutEvent) {
        String msg = loginOutEvent.getMsg();
        logger.info("LoginOutListener 接受到 loginOutEvent 发送的消息:" + msg);
    }
}
