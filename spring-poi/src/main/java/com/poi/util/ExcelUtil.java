package com.poi.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * excel
 *
 * @author yh
 * @date 2022/6/5
 */
public final class ExcelUtil {


    private ExcelUtil() {
    }


    /**
     * 导出 excel  xlsx
     *
     * @param response
     * @param title    标题如: "员工id,员工姓名,创建时间" (逗号分割)
     * @param values   值列表(逗号分割)
     * @param fileName 文件名字
     * @author yh
     */
    public static void export(HttpServletResponse response, String title, List<String> values, String fileName) {

        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        // 设置默认文件名
        if (!StringUtils.hasLength(fileName)) {
            fileName = df.format(new Date());
        } else {
            fileName += df.format(new Date());
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        //在Workbook中，创建一个sheet，对应Excel中的工作薄（sheet）
        XSSFSheet sheet = workbook.createSheet();

        //第一行
        XSSFRow row = sheet.createRow(0);
        //第一行设置标题
        setCell(row, title);

        //设置内容
        for (int i = 0; i < values.size(); i++) {
            //从第二行开始
            XSSFRow contentRow = sheet.createRow(i + 1);
            //填充表格内容
            setCell(contentRow, values.get(i));
        }

        // 设置response头信息
        OutputStream outputStream = null;
        try {
            response.reset();
            // 改成输出excel文件
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xlsx");
            //将文件输出
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 设备行里面的单元格内容
     *
     * @param row     行
     * @param content 内容， 多个单元格以逗号分割
     */
    private static void setCell(XSSFRow row, String content) {
        // 填充表头
        String[] contents = content.split(",");
        for (int i = 0; i < contents.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(contents[i]);
        }
    }
}