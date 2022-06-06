package com.word.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.word.Word07Writer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * @author yh
 * @description
 * @date 2022/6/5
 */
@RestController
@RequestMapping("/word")
public class WordController {

    @RequestMapping("/createWord")
    public void createWord() {
        Word07Writer writer = new Word07Writer();

        // 添加段落（标题）
        writer.addText(new Font("方正小标宋简体", Font.PLAIN, 24), "hello", "word");
        // 添加段落（正文）
        writer.addText(new Font("宋体", Font.PLAIN, 22), "hello", "word");
        // 写出到文件
        writer.flush(FileUtil.file("E:\\data\\file\\wordWrite.docx"));
        // 关闭
        writer.close();
    }
}
