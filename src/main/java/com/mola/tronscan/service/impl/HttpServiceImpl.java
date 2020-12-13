package com.mola.tronscan.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mola.tronscan.entity.Trans;
import com.mola.tronscan.service.HttpService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class HttpServiceImpl implements HttpService {

    private  final String TRANSFERS_API = "https://apilist.tronscan.org/api/token_trc20/transfers";

    @Override
    public List<Trans> getTransfers(HashMap<String, Object> paramMap) {
        String result = HttpRequest.get(TRANSFERS_API)
                .form(paramMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        return  jsonObject.getJSONArray("token_transfers").toList(Trans.class);
    }

    @Override
    public Integer getTotal(HashMap<String, Object> paramMap) {
        String result = HttpRequest.get(TRANSFERS_API)
                .form(paramMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        return JSONUtil.parseObj(result).getInt("total");
    }

    @Override
    public HashMap<String, Object> getparamMap(Integer start, String address,String token) {
        HashMap<String, Object> paramMap = new HashMap();
        paramMap.put("start", start);
        paramMap.put("limit", 50);
        paramMap.put("sort", "-timestamp");
        paramMap.put("count", true);
        paramMap.put("keyword", token);
        paramMap.put("relatedAddress", address);
        paramMap.put("start_timestamp", 0L);
        paramMap.put("end_timestamp", System.currentTimeMillis());
        paramMap.put("confirm", 0);
        paramMap.put("ret", "SUCCESS");
        return paramMap;
    }
}
