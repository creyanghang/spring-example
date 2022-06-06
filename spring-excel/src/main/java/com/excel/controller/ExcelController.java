package com.excel.controller;

import com.excel.entity.DemoData;
import com.excel.util.ExcelUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yh
 * @description
 * @date 2022/6/3
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    /**
     * 导出excel(写)
     *
     * @param response response
     * @author yh
     * @date 2022/6/3
     */

    @RequestMapping("/download")
    public void download(HttpServletResponse response) {
        List<DemoData> list = new ArrayList<>();

        list.add(new DemoData("title1", new Date(), 10.1, null));
        list.add(new DemoData("title2", new Date(), 11.1, null));
        list.add(new DemoData("title3", new Date(), 12.2, null));

        ExcelUtil.writeExcel(response, list, "hello-world", DemoData.class);
    }

    /**
     * 导入excel（读）
     * 测试文件： resources/demo/demo.xlsx
     *
     * @param file file
     * @author yh
     * @date 2022/6/3
     */
    @PostMapping("/readExcel")
    public void readExcel(MultipartFile file) throws IOException {
        List<DemoData> list = ExcelUtil.readExcel(file, DemoData.class);

        for (DemoData item : list) {
            System.out.println(item);
        }
    }
}
