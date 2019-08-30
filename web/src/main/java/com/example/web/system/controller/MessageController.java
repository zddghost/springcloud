package com.example.web.system.controller;

import com.common.core.basebean.ResponseBean;
import com.example.web.common.basecontroller.BaseController;
import com.example.web.system.websocket.WebSocketServer;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Api(description = "消息接口")
@RestController
@RequestMapping("message")
public class MessageController extends BaseController {

    //推送数据接口
    @RequestMapping("push/{cid}")
    public ResponseBean pushToWeb(@PathVariable String cid, String message) {
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
            return failure(cid + "#" + e.getMessage());
        }
        return success(cid);
    }

}
