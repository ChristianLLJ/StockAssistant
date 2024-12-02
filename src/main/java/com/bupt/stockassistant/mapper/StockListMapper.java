package com.bupt.stockassistant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.stockassistant.dto.StockListDTO;
import com.bupt.stockassistant.entity.StockList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Program : StockAssistant
 * Author : llj
 * Create : 2024-09-02 14:30
 **/
@Mapper
public interface StockListMapper extends BaseMapper<StockList> {
    StockList queryStockListByDateMain(@Param("date") String date);
    StockList queryStockListByDateStar(@Param("date") String date);
    StockList queryStockListByDateGem(@Param("date") String date);

}
