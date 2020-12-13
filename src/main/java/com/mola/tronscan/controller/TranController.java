package com.mola.tronscan.controller;


import com.mola.tronscan.service.TranService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags ="tronscan 平台接口 交易记")
@RestController
@RequestMapping("/trans")
public class TranController {

    Logger log = LoggerFactory.getLogger(TranController.class);


    @Autowired
    private TranService tranService;

    @GetMapping
    @ApiOperation(value = "导出TRON交易记录", notes = "export", produces = "application/octet-stream")
    public void add(
            @RequestParam(value = "address", required = true,defaultValue = "TLztvwwwXZJ7t5gczYmzmvcjV1MjCTVLUn")  String address,
            @RequestParam(value = "token", required = true,defaultValue = "TSNWgunSeGUQqBKK4bM31iLw3bn9SBWWTG") String token,
            HttpServletResponse response
            ) throws IOException {

        tranService.exportExcel(address,token,response);
    }

}
