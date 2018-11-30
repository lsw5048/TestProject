package com.jd.poi.excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.junit.Test;

import java.io.*;
import java.text.MessageFormat;

public class ExcelTest {

    @Test
    public void testImport() throws IOException, BiffException {
        InputStream is = new FileInputStream("C:\\Users\\lishuaiwei.360BUYAD\\Desktop\\物流单模板.xls");
        Workbook workbook = Workbook.getWorkbook(is);
        Sheet sheet =workbook.getSheet(0);
        System.out.println(sheet.getCell(0,1).getContents());
        System.out.println(sheet.getCell(1,2).getContents());
    }

    @Test
    public void testExport() throws IOException, WriteException {
        OutputStream os = new FileOutputStream("D:\\abc.xls");
        WritableWorkbook wwb = Workbook.createWorkbook(os);
        WritableSheet sheet = wwb.createSheet("sheet名称", 0);
        Label cell = new Label(1,2,"测试数据");
        sheet.addCell(cell);
        wwb.write();
        wwb.close();
    }

    @Test
    public void testMulti(){
        int[] a = new int[4];
        a[1] = 1;
        Double d = 24231186.00;
        System.out.println(d.intValue()*100);
    }

    @Test
    public void testMessageFormat(){
        String message = MessageFormat.format("{0}替换0", "abc");
        System.out.println(message);
        message = MessageFormat.format("替换0", "abc");
        System.out.println(message);
    }
}
