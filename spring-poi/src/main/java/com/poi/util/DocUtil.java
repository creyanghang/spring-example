package com.poi.util;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;

public class DocUtil {
    /**
     * 读取doc文件内容
     *
     * @param fs 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String readDoc(FileInputStream fs) throws IOException {
        StringBuilder result = new StringBuilder();
        WordExtractor re = new WordExtractor(fs);
        result.append(re.getText());
        re.close();
        return result.toString();
    }

    /**
     * 读取docx文件内容
     *
     * @param fs 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String readDocx(FileInputStream fs) throws IOException {
        StringBuilder result = new StringBuilder();
        XWPFDocument docx = new XWPFDocument(fs);
        XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
        result.append(extractor.getText());
        fs.close();
        return result.toString();
    }
}