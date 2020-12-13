package com.mola.tokenview.service;

import javax.servlet.http.HttpServletResponse;

public interface ViewService {


    void exportExcel(String address, String token, HttpServletResponse response);
}
