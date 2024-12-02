package com.bupt.stockassistant.service;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * Program : StockAssistant
 * Author : llj
 * Create : 2024-10-17 21:39
 **/
public interface WeChatServer {
    @Scheduled(initialDelay = 10000,fixedRate = 6600000)
    void creatAccessToken();

    @Scheduled(initialDelay = 20000,fixedRate = 666600000)
    void creatMenu();

    String getAccessToken();
}
