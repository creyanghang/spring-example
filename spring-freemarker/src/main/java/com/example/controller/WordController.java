package com.example.controller;

import com.example.util.FreeMarkerUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yh
 * @description
 * @date 2022/6/4
 */
@RestController
@RequestMapping("/word")
public class WordController {

    @Value("${file.path:E:\\data\\file\\}")
    private String filePath;


    /**
     * 根据模板导出word
     *
     * @return 生成文件路径
     * @author yh
     * @date 2022/6/4
     */
    @GetMapping("/crateWord")
    @ResponseBody
    public String crateWord() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("value1", "hello");
        params.put("value2", "world");
        params.put("value3", "工作文档");

        String fileName = UUID.randomUUID() + ".doc";
        String fileSrc = filePath;

        File realfile = new File(filePath);
        if (!realfile.exists()) {
            realfile.mkdirs();
        }
        return filePath + FreeMarkerUtils.createWord(params, "template.ftl", fileName, fileSrc);

    }


}
