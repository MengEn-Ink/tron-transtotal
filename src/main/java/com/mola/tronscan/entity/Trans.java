package com.mola.tronscan.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mola.tronscan.config.DateTsConverter;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Trans {


    @ColumnWidth(68)
    @ExcelProperty(value = "transaction_id" )
    @JsonProperty("transaction_id")
    private String transactionId;

    @ColumnWidth(20)
    @ExcelProperty(value = "block_ts",converter = DateTsConverter.class)
    @JsonProperty("block_ts")
    private String blockTs;

    @ColumnWidth(40)
    @ExcelProperty(value = "from_address")
    @JsonProperty("from_address")
    private String fromAddress;

    @ColumnWidth(40)
    @ExcelProperty(value = "to_address")
    @JsonProperty("to_address")
    private String toAddress;

    @ExcelProperty(value = "block")
    @JsonProperty("block")
    private Integer block;

    @ColumnWidth(40)
    @ExcelProperty(value = "contract_address")
    @JsonProperty("contract_address")
    private String contractAddress;

    @ColumnWidth(20)
    @ExcelProperty(value = "quant")
    @JsonProperty("quant")
    private BigDecimal quant;



}
