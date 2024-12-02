package com.bupt.stockassistant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.stockassistant.entity.StockList;

/**
 * Program : StockAssistant
 * Author : llj
 * Create : 2024-09-02 14:24
 **/

public interface WeChatMessageService extends IService<StockList> {


    String sendDailyPredictionResult(String text);

}
