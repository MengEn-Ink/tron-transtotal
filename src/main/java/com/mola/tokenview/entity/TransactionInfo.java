package com.mola.tokenview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TransactionInfo {


    /**
     * type : tx
     * network : TRX
     * block_no : 25831061
     * height : 25831061
     * blockHash : 00000000018a2695e13c56b0d231fdcaf6a05093858aa18dc02818baaf7630ad
     * index : 20
     * time : 1607838723
     * txid : 74b907cd202236034f5f252780950732e281a3661c7aa7a8545bbd3deb5be625
     * fee : 3.20636
     * tType : Trigger Smart
     * confirmations : 15367
     * from : TLztvwwwXZJ7t5gczYmzmvcjV1MjCTVLUn
     * to : TKH4HPMPjxR2Q93XBVfQrpGiBpyjBwBG6P
     * value : 0
     * gasFee : 3206360
     * gasUsed : 80159
     * gasLimit : 20000000
     * expiration : 1607838774
     * toIsContract : 1
     * netFee : 0
     * callTransfer : [{"index":20,"from":"TKH4HPMPjxR2Q93XBVfQrpGiBpyjBwBG6P","to":"TQn9Y2khEsLJW1ChVWFMSMeRDow5KcbLSE","value":"117.944224"}]
     * tokenTransfer : [{"index":20,"token":"TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG","tokenAddr":"TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG","tokenSymbol":"COLA","tokenDecimals":"6","tokenInfo":{"h":"TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG","f":"Blockcola Token","s":"COLA","d":"6"},"from":"TLztvwwwXZJ7t5gczYmzmvcjV1MjCTVLUn","to":"TKH4HPMPjxR2Q93XBVfQrpGiBpyjBwBG6P","value":"20000000"},{"index":20,"token":"TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t","tokenAddr":"TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t","tokenSymbol":"USDT","tokenDecimals":"6","tokenInfo":{"h":"TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t","f":"Tether USD","s":"USDT","d":"6"},"from":"TQn9Y2khEsLJW1ChVWFMSMeRDow5KcbLSE","to":"TLztvwwwXZJ7t5gczYmzmvcjV1MjCTVLUn","value":"3358098"}]
     */

    @JsonProperty("type")
    private String type;
    @JsonProperty("network")
    private String network;
    @JsonProperty("block_no")
    private Integer blockNo;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("blockHash")
    private String blockHash;
    @JsonProperty("index")
    private Integer index;
    @JsonProperty("time")
    private Integer time;
    @JsonProperty("txid")
    private String txid;
    @JsonProperty("fee")
    private String fee;
    @JsonProperty("tType")
    private String tType;
    @JsonProperty("confirmations")
    private Integer confirmations;
    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;
    @JsonProperty("value")
    private String value;
    @JsonProperty("gasFee")
    private Integer gasFee;
    @JsonProperty("gasUsed")
    private Integer gasUsed;
    @JsonProperty("gasLimit")
    private Integer gasLimit;
    @JsonProperty("expiration")
    private Integer expiration;
    @JsonProperty("toIsContract")
    private Integer toIsContract;
    @JsonProperty("netFee")
    private Integer netFee;
    @JsonProperty("callTransfer")
    private List<CallTransferDTO> callTransfer;
    @JsonProperty("tokenTransfer")
    private List<TokenTransferDTO> tokenTransfer;

    @NoArgsConstructor
    @Data
    public static class CallTransferDTO {
        /**
         * index : 20
         * from : TKH4HPMPjxR2Q93XBVfQrpGiBpyjBwBG6P
         * to : TQn9Y2khEsLJW1ChVWFMSMeRDow5KcbLSE
         * value : 117.944224
         */

        @JsonProperty("index")
        private Integer index;
        @JsonProperty("from")
        private String from;
        @JsonProperty("to")
        private String to;
        @JsonProperty("value")
        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class TokenTransferDTO {
        /**
         * index : 20
         * token : TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG
         * tokenAddr : TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG
         * tokenSymbol : COLA
         * tokenDecimals : 6
         * tokenInfo : {"h":"TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG","f":"Blockcola Token","s":"COLA","d":"6"}
         * from : TLztvwwwXZJ7t5gczYmzmvcjV1MjCTVLUn
         * to : TKH4HPMPjxR2Q93XBVfQrpGiBpyjBwBG6P
         * value : 20000000
         */

        @JsonProperty("index")
        private Integer index;
        @JsonProperty("token")
        private String token;
        @JsonProperty("tokenAddr")
        private String tokenAddr;
        @JsonProperty("tokenSymbol")
        private String tokenSymbol;
        @JsonProperty("tokenDecimals")
        private String tokenDecimals;

        @JsonProperty("from")
        private String from;
        @JsonProperty("to")
        private String to;
        @JsonProperty("value")
        private String value;
        @JsonIgnore
        private String remark;


    }
}
