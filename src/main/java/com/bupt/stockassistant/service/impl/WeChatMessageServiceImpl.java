package com.bupt.stockassistant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.stockassistant.entity.StockList;
import com.bupt.stockassistant.mapper.StockListMapper;
import com.bupt.stockassistant.service.WeChatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import utils.DateUtil;
import utils.StockUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Program : StockAssistant
 * Author : llj
 * Create : 2024-09-02 14:24
 **/
@Slf4j
@Service
public class WeChatMessageServiceImpl extends ServiceImpl<StockListMapper, StockList> implements WeChatMessageService {

    @Override
    public String sendDailyPredictionResult(String text) {
        //获取当日日期
        //String todayDate = "20241021";
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String todayDate = today.format(formatter);


        StockList stockList;
        if (text.equals("科创板")){
            stockList = getBaseMapper().queryStockListByDateStar(DateUtil.getTomorrowDate());
            if(stockList==null)
                stockList = getBaseMapper().queryStockListByDateStar(todayDate);
        } else if (text.equals("创业板")) {
            stockList = getBaseMapper().queryStockListByDateGem(DateUtil.getTomorrowDate());
            if(stockList==null)
                stockList = getBaseMapper().queryStockListByDateGem(todayDate);
        }
        else{
            stockList = getBaseMapper().queryStockListByDateMain(DateUtil.getTomorrowDate());
            if(stockList==null)
                stockList = getBaseMapper().queryStockListByDateMain(todayDate);
        }

        if(stockList==null)
            return "暂无今日数据。";

        System.out.println("stockList = " + stockList.toString());

        String result = DateUtil.processDate(stockList.getPredictDate()) + "推荐股票组合：\n";

        //System.out.println("result = " + result);

        return result + StockUtil.processStockData(stockList.getRankedStocks());

    }

}
