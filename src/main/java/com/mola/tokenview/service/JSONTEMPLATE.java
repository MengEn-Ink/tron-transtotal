package com.mola.tokenview.service;

import cn.hutool.core.io.file.FileReader;

public class JSONTEMPLATE {

   public static void main(String[] args) {
      FileReader fileReader = new FileReader("info.json");
      String result = fileReader.readString();
      System.out.println(result);
   }


   public static String JSON_TEMPLATE() {
      FileReader fileReader = new FileReader("info.json");
      return fileReader.readString();
   }
}
