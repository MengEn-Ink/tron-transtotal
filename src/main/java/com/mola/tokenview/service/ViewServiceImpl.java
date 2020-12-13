package com.mola.tokenview.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.mola.tokenview.entity.Tokenbalance;
import com.mola.tokenview.entity.Tokentrans;
import com.mola.tokenview.entity.TransactionInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ViewServiceImpl implements  ViewService{

    //流动池LP地址
    static String lp = "TKH4HPMPjxR2Q93XBVfQrpGiBpyjBwBG6P";

    @Override
    public void exportExcel(String address, String token, HttpServletResponse response) {
        List<Tokentrans> data = getData(address, token);
//        List<Tokentrans> data = JSONUtil.parseArray(JSONTEMPLATE.JSON_TEMPLATE()).toList(Tokentrans.class);
        response.setCharacterEncoding("utf8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + address + ".xlsx"); //文件名
        response.setHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "max-age=0");
        try {
            EasyExcel.write(response.getOutputStream(), Tokentrans.class)
                    .sheet("交易记录") //sheet名
                    .doWrite(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public List<Tokentrans> getData(String address, String token) {
        List<Tokentrans> tokentrans = new ArrayList<>();
        String sst = "https://trx.tokenview.com/api/trx/address/tokenbalance/" + address;
        JSONObject total = getJoData(sst);

        List<Tokenbalance> TokenbalanceJo = total.getJSONArray("data").toList(Tokenbalance.class);

        TokenbalanceJo.stream().forEach(tokenbalance -> {
            if (token.equals(tokenbalance.getHash())) {
                List<Tokentrans> t1 = getTokentrans(address, token, tokenbalance.getTransferCnt());
                tokentrans.addAll(t1);
            }
        });
        return tokentrans;
    }


    public static JSONObject getJoData(String url) {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = HttpRequest.get(url)
                .execute().body();
        if(result==null){
            getJoData(url);
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

            JSONObject TokentransJo = getJoData(ss);
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

        JSONObject TokentransJo = getJoData(api);
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
                        tokenTransferDTO.setRemark( "-"+divide + " " + tokenSymbol);

                    }
                    //转入
                    if (address.equalsIgnoreCase(tokenTransferDTO.getTo())) {
                        if(size==1){
                            tokenTransferDTO.setRemark("+" + divide + " " + tokenSymbol);
                        }
                    }
                });

                tokenTransferList.addAll(tokenTransfer);
            }
        });


        return tokenTransferList;
    }
}
