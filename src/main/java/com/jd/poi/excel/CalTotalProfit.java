package com.jd.poi.excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class CalTotalProfit {

    public static void main(String[] args) throws Exception {
        int sheetNum = 4;

        //盈利

        InputStream is = new FileInputStream("D:\\entertain\\everyShot.xls");
        Workbook workbook = Workbook.getWorkbook(is);
        BigDecimal sumProfit = new BigDecimal("0");
        for (int i = 0; i < sheetNum; i++) {

            //算出每个sheet第一列最后一行的数据，即为上次投注的金额
            Sheet sheeti = workbook.getSheet(i);

            for (int j = 0; j < sheeti.getRows(); j++) {
                if(i ==0 && j ==0){
                    //跳过-32000
                    continue;
                }
                double profit = 0;
                try {
                    profit = Double.parseDouble(sheeti.getCell(2, j).getContents());
                } catch (Exception e) {

                }
                sumProfit = sumProfit.add(new BigDecimal(profit+""));

            }
        }
        System.out.println(sumProfit);
    }
}

