package com.mola.tokenview.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Tokenbalance {


    /**
     * network : TRX
     * hash : TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG
     * tokenInfo : {"h":"TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG","f":"Blockcola Token","s":"COLA","d":"6"}
     * transferCnt : 98
     * balance : 45490733
     */

    @JsonProperty("network")
    private String network;
    @JsonProperty("hash")
    private String hash;
    @JsonProperty("tokenInfo")
    private TokenInfoDTO tokenInfo;
    @JsonProperty("transferCnt")
    private Integer transferCnt;
    @JsonProperty("balance")
    private String balance;

    @NoArgsConstructor
    @Data
    public static class TokenInfoDTO {
        /**
         * h : TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG
         * f : Blockcola Token
         * s : COLA
         * d : 6
         */

        @JsonProperty("h")
        private String h;
        @JsonProperty("f")
        private String f;
        @JsonProperty("s")
        private String s;
        @JsonProperty("d")
        private String d;
    }
}
