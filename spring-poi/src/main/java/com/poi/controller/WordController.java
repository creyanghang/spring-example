package com.poi.controller;

import com.poi.util.DocUtil;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author yh
 * @description
 * @date 2022/6/5
 */
@RestController
@RequestMapping("/word")
public class WordController {

    /**
     * 读取doc
     *
     * @author yh
     * @date 2022/6/5
     */
    @RequestMapping("/readDoc")
    public void readDoc() throws IOException {
        File file = ResourceUtils.getFile("classpath:hello.doc");
        String s = DocUtil.readDoc(new FileInputStream(file));
        System.out.println("read doc: " + s);
    }

    /**
     * 读取docx
     *
     * @author yh
     * @date 2022/6/5
     */
    @RequestMapping("/readDocx")
    public void readDocx() throws Exception {
        File file = ResourceUtils.getFile("classpath:hello.docx");
        String s = DocUtil.readDocx(new FileInputStream(file));
        System.out.println("read docx: " + s);
    }

    /**
     * word转pdf, 待调试 todo
     *
     * @author yh
     * @date 2022/6/5
     */
//    @RequestMapping("/wordToPdf")
//    public void wordToPdf() throws Exception {
//        //读取word文档
//        XWPFDocument document = null;
//        File file = ResourceUtils.getFile("classpath:hello.docx");
//        try (InputStream in = new FileInputStream(file)) {
//            document = new XWPFDocument(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //将word转成pdf
//        PdfOptions options = PdfOptions.create();
//        OutputStream outPDF = Files.newOutputStream(Paths.get("E:\\data\\file\\test.pdf"));
//        PdfConverter.getInstance().convert(document, outPDF, options);
//    }
}
