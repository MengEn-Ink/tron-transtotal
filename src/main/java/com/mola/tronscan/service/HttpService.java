package com.mola.tronscan.service;

import com.mola.tronscan.entity.Trans;

import java.util.HashMap;
import java.util.List;

public interface HttpService {

    List<Trans> getTransfers(HashMap<String, Object> paramMap);


    Integer getTotal(HashMap<String, Object> paramMap);


    HashMap<String, Object> getparamMap(Integer start, String address, String token);

}
