package com.mola;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mola.tokenview.entity.Tokenbalance;
import com.mola.tokenview.entity.Tokentrans;
import com.mola.tokenview.entity.TransactionInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TronViewTest {
    //流动池LP地址
    static String lp = "TKH4HPMPjxR2Q93XBVfQrpGiBpyjBwBG6P";
    //锁仓挖矿地址
    String sc = "TTGgtz99iYEDc69xVkYR4yR5fQYr9hUKNv";
    //trx usdt 交换地址
    String usdt = "TQn9Y2khEsLJW1ChVWFMSMeRDow5KcbLSE";

    public static void main(String[] args) {

        String address = "TLztvwwwXZJ7t5gczYmzmvcjV1MjCTVLUn";
        String token = "TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG";

        String sst = "https://trx.tokenview.com/api/trx/address/tokenbalance/" + address;
        JSONObject total = getData(sst);

        List<Tokenbalance> TokenbalanceJo = total.getJSONArray("data").toList(Tokenbalance.class);


        TokenbalanceJo.stream().forEach(tokenbalance -> {
            if (token.equals(tokenbalance.getHash())) {

                List<Tokentrans> tokentrans = getTokentrans(address, token, tokenbalance.getTransferCnt());
                System.out.println(JSONUtil.toJsonPrettyStr(tokentrans));
            }
        });


    }


    public static JSONObject getData(String url) {
        String result = HttpRequest.get(url)
                .timeout(20000)//超时，毫秒
                .execute().body();
        if(result==null){
            getData(url);
        }
        return JSONUtil.parseObj(result);
    }

    /**
     * 详细交易
     *
     * @param address
     * @param token
     * @param transferCnt
     * @return
     */
    public static List<Tokentrans> getTokentrans(String address, String token, Integer transferCnt) {
        List<Tokentrans> TokentransListTotal = new ArrayList<>();
        int page = (transferCnt / 50) + 1;


        for (int i = 1; i <= page; i++) {
            String ss = "https://trx.tokenview.com/api/trx/address/tokentrans" +
                    "/" + address + "" +
                    "/" + token + "" +
                    "/" + i +
                    "/50";

            JSONObject TokentransJo = getData(ss);
            List<Tokentrans> TokentransList = TokentransJo.getJSONArray("data").toList(Tokentrans.class);
            TokentransList.stream().forEach(tokentrans -> {
                if (tokentrans.getFrom() != null && tokentrans.getTo() != null) {
                    if (lp.equalsIgnoreCase(tokentrans.getFrom()) || lp.equalsIgnoreCase(tokentrans.getTo())) {
                        List<TransactionInfo.TokenTransferDTO> transactionInfo = getTransactionInfo(address, tokentrans.getBlockNo(), tokentrans.getIndex());
                        tokentrans.setTransactionInfo(transactionInfo);
                        TokentransListTotal.add(tokentrans);
                    }
                }
            });


        }

        return TokentransListTotal;
    }


    public static List<TransactionInfo.TokenTransferDTO> getTransactionInfo(String address, Integer blockNo, Integer index) {
        List<TransactionInfo.TokenTransferDTO> tokenTransferList = new ArrayList<>();
        String api = "https://trx.tokenview.com/api/tx/trx" +
                "/" + blockNo +
                "/" + index;

        JSONObject TokentransJo = getData(api);
        List<TransactionInfo> TransactionInfoList = TokentransJo.getJSONArray("data").toList(TransactionInfo.class);
        TransactionInfoList.stream().forEach(transactionInfo -> {
            List<TransactionInfo.TokenTransferDTO> tokenTransfer = transactionInfo.getTokenTransfer();
            if (tokenTransfer != null) {

                tokenTransfer.stream().forEach(tokenTransferDTO -> {

                    int size = tokenTransfer.size();

                    String value = tokenTransferDTO.getValue();
                    String tokenDecimals = tokenTransferDTO.getTokenDecimals();
                    String tokenSymbol = tokenTransferDTO.getTokenSymbol();
                    BigDecimal v = new BigDecimal(value);
                    Double d = new Double(tokenDecimals);
                    double pow = Math.pow(10, d);
                    BigDecimal divide = v.divide(BigDecimal.valueOf(pow), 2, BigDecimal.ROUND_HALF_UP);

                    //转出
                    if (address.equalsIgnoreCase(tokenTransferDTO.getFrom())) {
                        tokenTransferDTO.setRemark("转出" + divide + " " + tokenSymbol);
                    }
                    //转入
                    if (address.equalsIgnoreCase(tokenTransferDTO.getTo())) {
                        tokenTransferDTO.setRemark("转入" + divide + " " + tokenSymbol);
                    }
                });
                tokenTransferList.addAll(tokenTransfer);
            }
        });


        return tokenTransferList;
    }
}
