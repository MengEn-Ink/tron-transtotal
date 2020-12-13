package com.mola.tronscan.service;

import com.mola.tronscan.entity.Trans;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface TranService {


    void exportExcel(String address, String token, HttpServletResponse response) throws IOException;


    List<Trans> getTransList(String address, String token);


}
