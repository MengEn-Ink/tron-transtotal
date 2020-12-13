package com.mola.tokenview.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mola.tronscan.config.DateTsConverter;
import com.mola.tronscan.config.ValueConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Tokentrans {


    /**
     * index : 20
     * block_no : 25831061
     * token : TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG
     * tokenAddr : TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG
     * tokenSymbol : COLA
     * tokenDecimals : 6
     * time : 1607838723
     * txid : 74b907cd202236034f5f252780950732e281a3661c7aa7a8545bbd3deb5be625
     * tokenInfo : {"h":"TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG","f":"Blockcola Token","s":"COLA","d":"6"}
     * from : TLztvwwwXZJ7t5gczYmzmvcjV1MjCTVLUn
     * to : TKH4HPMPjxR2Q93XBVfQrpGiBpyjBwBG6P
     * value : 20000000
     * conformations : 15280
     */

    @ExcelIgnore
    @JsonProperty("index")
    private Integer index;

    @ExcelIgnore
    @JsonProperty("block_no")
    private Integer blockNo;


    @ExcelIgnore
    @JsonProperty("token")
    private String token;


    @ExcelIgnore
    @JsonProperty("tokenSymbol")
    private String tokenSymbol;

    @ExcelIgnore
    @JsonProperty("tokenDecimals")
    private String tokenDecimals;

    @ColumnWidth(20)
    @ExcelProperty(value = "时间" ,converter = DateTsConverter.class)
    @JsonProperty("time")
    private String time;

    @ColumnWidth(40)
    @ExcelProperty(value = "from" )
    @JsonProperty("from")
    private String from;

    @ColumnWidth(40)
    @ExcelProperty(value = "to" )
    @JsonProperty("to")
    private String to;

    @ColumnWidth(20)
    @ExcelProperty(value = "操作" ,converter = ValueConverter.class)
    @JsonProperty("value")
    private String value;


    @JsonIgnore
    @ExcelIgnore
    private List<TransactionInfo.TokenTransferDTO> transactionInfo;

    @JsonIgnore
    @ColumnWidth(20)
    @ExcelProperty(value = "金额" )
    private String trans;

    public String getTrans() {
        StringBuffer stringBuffer=new StringBuffer();
        transactionInfo.forEach(tokenTransferDTO -> {
            if (tokenTransferDTO.getRemark() != null) {
                stringBuffer.append( tokenTransferDTO.getRemark());
            }
        });
        return stringBuffer.toString();
    }
}
