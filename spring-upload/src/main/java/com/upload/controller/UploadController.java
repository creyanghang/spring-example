package com.upload.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author yh
 * @description
 * @date 2022/6/4
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${file.upload.path:C:\\}")
    private String path;

    /**
     * @RequestPart 这个注解用在multipart/form-data表单提交请求的方法上。
     * 支持的请求方法的方式MultipartFile，属于Spring的MultipartResolver类
     */

    /**
     * 单文件上传
     *
     * @param file 文件
     * @return result
     * @author yh
     * @date 2022/6/4
     */
    @PostMapping("/upload")
    public String upload(@RequestPart MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = path + fileName;

        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());
        return "Upload file success : " + dest.getAbsolutePath();
    }

    /**
     * 多文件上传
     *
     * @param files 文件
     * @return result
     * @author yh
     * @date 2022/6/4
     */
    @PostMapping("/uploadBatch")
    public String uploadBatch(@RequestPart MultipartFile[] files) throws IOException {
        StringBuffer message = new StringBuffer();

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String filePath = path + fileName;

            File dest = new File(filePath);
            Files.copy(file.getInputStream(), dest.toPath());
            message.append("Upload file success : " + dest.getAbsolutePath()).append("<br>");
        }
        return message.toString();
    }
}
