package com.mola.tokenview.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TokenViewTrans {


    @JsonProperty("index")
    private Integer index;
    @JsonProperty("block_no")
    private Integer blockNo;
    @JsonProperty("token")
    private String token;
    @JsonProperty("tokenAddr")
    private String tokenAddr;
    @JsonProperty("tokenSymbol")
    private String tokenSymbol;
    @JsonProperty("tokenDecimals")
    private String tokenDecimals;
    @JsonProperty("time")
    private Integer time;
    @JsonProperty("txid")
    private String txid;

    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;
    @JsonProperty("value")
    private String value;
    @JsonProperty("conformations")
    private Integer conformations;


}
