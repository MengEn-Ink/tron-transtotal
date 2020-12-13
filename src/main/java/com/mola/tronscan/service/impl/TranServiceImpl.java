package com.mola.tronscan.service.impl;


import com.alibaba.excel.EasyExcel;
import com.mola.tronscan.entity.Trans;
import com.mola.tronscan.service.HttpService;
import com.mola.tronscan.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TranServiceImpl implements TranService {


    @Autowired
    private HttpService httpService;

    @Override
    public void exportExcel(String address, String token, HttpServletResponse response) throws IOException {
        List<Trans> transList = getTransList(address, token);
        response.setCharacterEncoding("utf8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" +URLEncoder.encode(address + ".xlsx", "UTF-8")); //文件名
        response.setHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "max-age=0");
        EasyExcel.write(response.getOutputStream(), Trans.class)
                .sheet("交易记录") //sheet名
                .doWrite(transList);

    }

    @Override
    public List<Trans> getTransList(String address, String token) {
        List<Trans> transList = new ArrayList<>();

        HashMap<String, Object> stringObjectHashMap = httpService.getparamMap(0, address, token);

        Integer total = httpService.getTotal(stringObjectHashMap);
        int page = (total / 50) + 1;
        for (int i = 0; i < page; i++) {
            HashMap<String, Object> stringObjectHashMap1 = httpService.getparamMap(i * 50, address, token);
            List<Trans> transfers = httpService.getTransfers(stringObjectHashMap1);
            transList.addAll(transfers);
        }
        return transList;
    }



}
