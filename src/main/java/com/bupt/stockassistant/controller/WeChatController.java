package com.bupt.stockassistant.controller;

import com.bupt.stockassistant.service.WeChatServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Program : StockAssistant
 * Author : llj
 * Create : 2024-10-17 20:16
 **/

@RestController
//@RequestMapping("/we_chat")
public class WeChatController {

    @Resource
    WeChatServer weChatServer;

    @GetMapping("/getAccessToken")
    public String getAccessToken() {
        try {
            String accessToken = weChatServer.getAccessToken();
            return "accessToken" + accessToken;
        } catch (RuntimeException e) {
            return "获取失败，请稍后再试！";
        }
    }
}

