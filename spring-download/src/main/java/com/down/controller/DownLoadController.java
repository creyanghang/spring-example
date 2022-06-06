package com.down.controller;

import com.down.controller.utils.FileUtil;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author yh
 * @description
 * @date 2022/6/4
 */
@RestController
@RequestMapping("/download")
public class DownLoadController {

    /**
     * 文件下载
     *
     * @param response
     * @return
     * @author yh
     * @date 2022/6/4
     */

    @GetMapping("/download")
    public String fileDownLoad(HttpServletResponse response) throws Exception {
        File file = ResourceUtils.getFile("classpath:0000.jpg");
//         File file = new File("E:\\系统\\Computer壁纸\\Atrax-Desktop(Bitcoin)\\0000.jpg");
        if (!file.exists()) {
            return "下载文件不存在";
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        // 设置编码格式
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] buff = new byte[1024];
        OutputStream os = response.getOutputStream();
        int i = 0;
        while ((i = bis.read(buff)) != -1) {
            os.write(buff, 0, i);
            os.flush();
        }
        bis.close();
        os.close();
        return "下载成功";
    }

    /**
     * 文件下载
     *
     * @author yh
     * @date 2022/6/4
     */
    @GetMapping(value = "/downloadFileStream")
    public void downloadFileStream(HttpServletRequest request, HttpServletResponse response) {
        // 文件本地位置
        String filePath = "E:\\系统\\Computer壁纸\\Atrax-Desktop(Bitcoin)\\0000.jpg";
        // 文件名称
        String fileName = UUID.randomUUID() + ".jpg";
        File file = new File(filePath);
        FileUtil.downloadFile(file, request, response, fileName);
    }

    /**
     * 网络文件下载
     *
     * @author yh
     * @date 2022/6/4
     */
    @GetMapping(value = "/downloadFileHttpStream")
    public void downloadFileHttpStream(HttpServletRequest request, HttpServletResponse response) {
        // 文件网络地址
        String urlStr = "https://code.jquery.com/jquery-3.6.0.js";
        // 文件名称
        String fileName = "测试文件.js";
        FileUtil.downloadHttpFile(urlStr, request, response, fileName);
    }

    /**
     * 本地文件批量压缩下载
     *
     * @author yh
     * @date 2022/6/4
     */
    @GetMapping(value = "/downZip")
    public void downloadZipStream(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, String>> mapList = new ArrayList<>();
        String basePath = "E:\\系统\\Computer壁纸\\Atrax-Desktop(Bitcoin)\\";
        // 下载本地的5个文件
        Map<String, String> map = new HashMap<>();
        map.put("path", basePath + "0000.jpg");
        map.put("name", UUID.randomUUID() + ".jpg");
        mapList.add(map);

        Map<String, String> map2 = new HashMap<>();
        map2.put("path", basePath + "0Sogou.jpeg");
        map2.put("name", UUID.randomUUID() + ".jpg");
        mapList.add(map2);

        Map<String, String> map3 = new HashMap<>();
        map3.put("path", basePath + "1_21b.jpg");
        map3.put("name", UUID.randomUUID() + ".jpg");
        mapList.add(map3);

        FileUtil.zipDirFileToFile(mapList, request, response);
    }


    /**
     * 网络文件批量压缩下载
     *
     * 通过网络请求下载时，设置的fileName一定要与http资源的后缀一致；
     * @param request
     * @param response
     * @author yh
     * @date 2022/6/4
     * @return
     */
    @GetMapping(value = "/downloadZipHttpByDataStream")
    public void downloadZipHttpByDataStream(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, String>> mapList = new ArrayList<>();
        String urlStr = "https://code.jquery.com/jquery-3.6.0.js";
        // 模拟下载5个网络文件，分别为测试文件1~5
        for (int i = 1; i <= 5; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("path", urlStr);
            map.put("name", "测试文件_" + i + ".js");
            mapList.add(map);
        }
        FileUtil.zipUrlToFile(mapList, request, response);
    }
}
