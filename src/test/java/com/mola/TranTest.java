package com.mola;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.mola.tronscan.entity.Trans;

import java.util.LinkedHashMap;
import java.util.List;

public class TranTest {

    public static void main(String[] args) {
        LinkedHashMap<String, Object> link = new LinkedHashMap<>();

        Integer start = 0;
        Integer limit = 20;

        String url = "https://apilist.tronscan.org/api/token_trc20/transfers" +
                "?limit=20" +
                "&start=0" +
                "&sort=-timestamp" +
                "&count=true" +
                "&keyword=TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG" +
                "&relatedAddress=TLztvwwwXZJ7t5gczYmzmvcjV1MjCTVLUn" +
                "&confirm=0";
        String result = HttpUtil.get(url);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        Object total = jsonObject.getByPath("total");
        List<Trans> transfers = jsonObject.getJSONArray("token_transfers").toList(Trans.class);
//        transfers.stream().forEach(trans -> {
//            System.out.println(trans);
//
//        });

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeBeanTest.xlsx");
// 合并单元格后的标题行，使用默认标题样式
//        writer.merge(9, "一班成绩单");
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(transfers, true);
// 关闭writer，释放内存
        writer.close();
    }

}
