package com.bupt.stockassistant.controller;

import cn.hutool.json.JSONObject;
import com.bupt.stockassistant.service.WeChatMessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Program : StockAssistant
 * Author : llj
 * Create : 2024-09-02 14:20
 **/
@RestController
@RequestMapping("/send")
public class WechatMessageController {

    @Resource
    private WeChatMessageService weChatMessageService;


    @RequestMapping("/main")
    public String daliyPredictionResultMain() {
        try {
            return weChatMessageService.sendDailyPredictionResult("主板");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "查询失败!";
    }

    @RequestMapping("/Gem")
    public String daliyPredictionResultGem() {
        try {
            return weChatMessageService.sendDailyPredictionResult("创业板");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "查询失败!";
    }

    @RequestMapping("/Star")
    public String daliyPredictionResultStar() {
        try {
            return weChatMessageService.sendDailyPredictionResult("科创板");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "查询失败!";
    }

}
