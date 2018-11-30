package com.jd.poi.excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.biff.WritableWorkbookImpl;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class EntertainTest {

    public static void main(String[] args) throws Exception {
        int sheetNum = 4;
        Scanner scanner = new Scanner(System.in);
        //输入上次投注的金额
        System.out.println("Input the money you bid last time:");
        double lastBid = scanner.nextDouble();
        System.out.println("You win or not?");
        int win = scanner.nextInt();

        //盈利
        if (win > 0) {
            /*//输入盈利金额
            System.out.println("Input the money you win last time:");
            double lastProfit = scanner.nextDouble();*/


            //找到是第几个sheet的投注
            InputStream is = new FileInputStream("D:\\entertain\\everyShot.xls");
            Workbook workbook = Workbook.getWorkbook(is);
            for (int i = 0; i < sheetNum; i++) {

                //算出每个sheet第一列最后一行的数据，即为上次投注的金额
                Sheet sheeti = workbook.getSheet(i);
                double lastBidOfSheeti = Double.parseDouble(sheeti.getCell(0, sheeti.getRows() - 1).getContents());
                if(lastBid==lastBidOfSheeti){
                    //写入盈利，计算本轮总盈利
                    OutputStream outputStream = new FileOutputStream("D:\\entertain\\everyShot.xls");
                    WritableWorkbook wwb = Workbook.createWorkbook(new File("D:\\entertain\\everyShot.xls"),workbook);
                    WritableSheet writeSheeti = wwb.getSheet(i);
                    //读取excel中对应投注的盈利
                    String lastProfit = null;
                    for (int j = 1; j <= 6; j++) {

                        if(Double.parseDouble(sheeti.getCell(3,j).getContents())==lastBid){
                            lastProfit = sheeti.getCell(4,j).getContents();
                            break;
                        }
                    }

                    Label cell = new Label(1, sheeti.getRows() - 1, lastProfit);
                    writeSheeti.addCell(cell);

                    double sumProfit = 0;
                    for (int j = 0; j < sheeti.getRows()-1; j++) {
                        double profit = Double.parseDouble(sheeti.getCell(1, j).getContents());
                        sumProfit += profit;
                        if (profit > 0) {
                            sumProfit = 0;
                        }
                    }

                    cell = new Label(2, sheeti.getRows()-1, new BigDecimal(lastProfit).add(new BigDecimal(sumProfit+"")).subtract(new BigDecimal(lastBid+""))+ "");
                    writeSheeti.addCell(cell);
                    //写入下次投注的初始值
                    String firstPay = sheeti.getCell(0, 0).getContents();
                    cell = new Label(0, sheeti.getRows(), firstPay);
                    writeSheeti.addCell(cell);
                    wwb.write();
                    wwb.close();
                    System.out.println("You need pay " + firstPay + " this time");
                    return;
                }
            }


        } else {
            double alreadyLose = calAlreadyLoseAndWrite(lastBid, sheetNum);

            //InputStream is = new FileInputStream("D:\\entertain\\everyShot.xls");

        }
    }

    private static double calAlreadyLoseAndWrite(double lastBid, int sheetNum) throws Exception {
        double interestRate = 0.9;
        double expectRate = 0.1;
        /*System.out.println("Do you want to change the interest rate (default 0.9)?");
        Scanner scanner = new Scanner(System.in);
        int change = scanner.nextInt();
        if (change > 0) {
            interestRate = scanner.nextDouble();
        }*/
        InputStream is = new FileInputStream("D:\\entertain\\everyShot.xls");
        Workbook workbook = Workbook.getWorkbook(is);
        //算出每个sheet第一列最后一行的数据，即为上次投注的金额
        for (int i = 0; i < sheetNum; i++) {
            Sheet sheeti = workbook.getSheet(i);
            double lastBidOfSheeti = Double.parseDouble(sheeti.getCell(0, sheeti.getRows() - 1).getContents());
            if (lastBid == lastBidOfSheeti) {
                OutputStream outputStream = new FileOutputStream("D:\\entertain\\everyShot.xls");
                WritableWorkbook wwb = Workbook.createWorkbook(new File("D:\\entertain\\everyShot.xls"),workbook);
                WritableSheet writeSheeti = wwb.getSheet(i);
                Label cell = new Label(1, sheeti.getRows() - 1, "-"+lastBid);
                writeSheeti.addCell(cell);

                double sumLose = 0;
                for (int j = 0; j < sheeti.getRows()-1; j++) {
                    double profit = Double.parseDouble(sheeti.getCell(1, j).getContents());
                    sumLose += profit;
                    if (profit > 0) {
                        sumLose = 0;
                    }

                }
                double alreadyLose = lastBid-sumLose;
                double needPay = alreadyLose * (1 + expectRate) / (interestRate - expectRate);
                int realNeedPay = (int)needPay+1;

                System.out.println("You need pay " + realNeedPay + " this time");
                cell = new Label(0, sheeti.getRows(), realNeedPay+"");
                writeSheeti.addCell(cell);
                wwb.write();
                wwb.close();
                workbook.close();
                return sumLose;
            }

        }


        return 0;
    }

    private void init(double first) throws Exception {
        OutputStream outputStream = new FileOutputStream("D:\\entertain\\everyShot.xls");
        WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
        WritableSheet sheet0 = wwb.createSheet("first_shot", 0);
        WritableSheet sheet1 = wwb.createSheet("second_shot", 1);
        //新创建excel
        Label cell = new Label(0, 0, first + "");
        Label cell2 = new Label(0, 0, first + "");
        sheet0.addCell(cell);
        sheet1.addCell(cell2);

    }
}
