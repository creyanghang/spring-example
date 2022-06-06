//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.excel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.excel.listener.ExcelListener;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具类，对业务零侵入
 *
 * @author yh
 * @date 2022/6/3
 */

public class ExcelUtil {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    public ExcelUtil() {
    }

    public static void writeExcel(HttpServletResponse response, List<? extends Object> data, String fileName, Class clazz) {
        writeExcel(response, data, fileName, "sheet1", clazz);
    }

    public static void writeExcel(HttpServletResponse response, List<? extends Object> data, String fileName, String sheetName, Class clazz) {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        ((ExcelWriterSheetBuilder) EasyExcel.write(getOutputStream(fileName, response), clazz).excelType(ExcelTypeEnum.XLSX).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy)).doWrite(data);
    }

    public static <T> List<T> readExcel(InputStream inputStream, Class clazz) {
        List<ReadSheet> readSheetList = new ArrayList();
        ReadSheet readSheet = new ReadSheet();
        readSheet.setClazz(clazz);
        readSheetList.add(readSheet);
        return readExcel((InputStream) inputStream, (List) readSheetList);
    }

    public static <T> List<T> readExcel(InputStream excel, List<ReadSheet> readSheetList) {
        ExcelListener<T> excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return null;
        } else {
            reader.read(readSheetList);
            return excelListener.getDatas();
        }
    }

    public static <T> List<T> readExcel(MultipartFile excel, Class clazz, int sheetNo, int headLineNum) {
        List<ReadSheet> readSheetList = new ArrayList();
        ReadSheet readSheet = new ReadSheet();
        readSheet.setSheetNo(sheetNo);
        readSheet.setHeadRowNumber(headLineNum);
        readSheet.setClazz(clazz);
        readSheetList.add(readSheet);
        return readExcel((MultipartFile) excel, (List) readSheetList, clazz);
    }

    public static <T> List<T> readExcel(String filePath, Class clazz, int sheetNo, int headLineNum) {
        List<ReadSheet> readSheetList = new ArrayList();
        ReadSheet readSheet = new ReadSheet();
        readSheet.setSheetNo(sheetNo);
        readSheet.setHeadRowNumber(headLineNum);
        readSheet.setClazz(clazz);
        readSheetList.add(readSheet);
        return readExcel((String) filePath, (List) readSheetList);
    }

    public static <T> List<T> readExcel(MultipartFile excel, Class tClass2) {
        ExcelListener<T> excelListener = new ExcelListener();

        ExcelReader reader = getReader(excel, excelListener, tClass2);
        if (reader == null) {
            return null;
        } else {
            reader.readAll();
            return excelListener.getDatas();
        }
    }

//    private static <T> List<T> readExcel(MultipartFile excel) {
//        ExcelListener<T> excelListener = new ExcelListener();
//        ExcelReader reader = getReader(excel, excelListener);
//        if (reader == null) {
//            return null;
//        } else {
//            reader.readAll();
//            return excelListener.getDatas();
//        }
//    }

    public static <T> List<T> readExcel(String filePath, Class<T> clazz) {
        ExcelListener<T> excelListener = new ExcelListener();
        ExcelReader reader = getReader(filePath, excelListener);
        if (reader == null) {
            return null;
        } else {
            reader.readAll();
            return excelListener.getDatas();
        }
    }

    private static <T> List<T> readExcel(String filePath) {
        ExcelListener<T> excelListener = new ExcelListener();
        ExcelReader reader = getReader(filePath, excelListener);
        if (reader == null) {
            return null;
        } else {
            reader.readAll();
            return excelListener.getDatas();
        }
    }

    public static <T> List<T> readExcel(MultipartFile excel, List<ReadSheet> readSheetList, Class clazz) {
        ExcelListener<T> excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener, clazz);
        if (reader == null) {
            return null;
        } else {
            reader.read(readSheetList);
            return excelListener.getDatas();
        }
    }

    public static <T> List<T> readExcel(String filePath, List<ReadSheet> readSheetList) {
        ExcelListener<T> excelListener = new ExcelListener();
        ExcelReader reader = getReader(filePath, excelListener);
        if (reader == null) {
            return null;
        } else {
            reader.read(readSheetList);
            return excelListener.getDatas();
        }
    }

    private static ExcelReader getReader(InputStream inputStream, ExcelListener excelListener) {
        return EasyExcel.read(inputStream, excelListener).build();
    }

    private static ExcelReader getReader(String filePath, ExcelListener excelListener) {
        if (StringUtils.isBlank(filePath)) {
            throw new RuntimeException("找不到文件！");
        } else if (!filePath.toLowerCase().endsWith(".xls") && !filePath.toLowerCase().endsWith(".xlsx")) {
            throw new RuntimeException("文件格式错误！");
        } else {
            try {
                URL url = new URL(filePath);
                InputStream inputStream = new BufferedInputStream(url.openStream());
                return EasyExcel.read(inputStream, excelListener).build();
            } catch (IOException var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    private static ExcelReader getReader(MultipartFile excel, ExcelListener excelListener, Class clazz) {
        String filename = excel.getOriginalFilename();
        if (filename != null && (filename.toLowerCase().endsWith(".xls") || filename.toLowerCase().endsWith(".xlsx"))) {
            try {
                InputStream inputStream = new BufferedInputStream(excel.getInputStream());
                return EasyExcel.read(inputStream, clazz, excelListener).build();
            } catch (IOException var5) {
                var5.printStackTrace();
                return null;
            }
        } else {
            throw new RuntimeException("文件格式错误！");
        }
    }

    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException var5) {
            log.info("文件名encode失败！");
            var5.printStackTrace();
        }

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        response.addHeader("Access-Control-Expose-Headers", "Content-disposition");
        ServletOutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
        } catch (IOException var4) {
            log.info("IO处理失败！");
            var4.printStackTrace();
        }

        return outputStream;
    }
}
