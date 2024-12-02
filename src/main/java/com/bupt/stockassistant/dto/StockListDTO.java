package com.bupt.stockassistant.dto;

import lombok.Data;

/**
 * Program : StockAssistant
 * Author : llj
 * Create : 2024-09-02 18:20
 **/

@Data
public class StockListDTO {
    private Integer id;
    private String list;
    private String predictData;
}
