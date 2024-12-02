package com.bupt.stockassistant.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Program : StockAssistant
 * Author : llj
 * Create : 2024-09-02 14:21
 **/
@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("yc_Rank_STAN")
public class StockList implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("predict_date")
    private String predictDate;

    @TableField("model_name")
    private String modelName;

    @TableField("ranked_stocks")
    private String rankedStocks;

    @TableField("predict_time")
    private String predictTime;

//    @TableField(exist = false)
//    private String result;


}
