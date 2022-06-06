package com.poi.controller;

import com.poi.entity.UserVO;
import com.poi.util.ExcelUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yh
 * @description
 * @date 2022/6/5
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    /**
     * 导出excel
     *
     * @param response response
     * @author yh
     * @date 2022/6/5
     */
    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        //创建模拟数据
        List<UserVO> users = mockUsers();
        //设置导出excel头
        String userExportTitle = "员工id,员工姓名,创建时间";
        //设置导出内容，注意我们全部转成String； 逗号分割； 和标题一一对应，如果空值也需要设置空值;
        List<String> userExportContents = new ArrayList<>();
        users.forEach(v -> {
            userExportContents.add(v.getId() + "," + v.getName() + "," + v.getCreateTime());
        });

        ExcelUtil.export(response, userExportTitle, userExportContents, "员工列表");
    }

    private List<UserVO> mockUsers() {
        List<UserVO> userVos = new ArrayList<>();
        userVos.add(new UserVO("张三", System.currentTimeMillis(), "2022-05-11 18:00:00"));
        userVos.add(new UserVO("张三张三", System.currentTimeMillis(), "2022-05-11 18:10:00"));
        userVos.add(new UserVO("张三张三张三", System.currentTimeMillis(), "2022-05-11 18:20:00"));
        return userVos;
    }

}
