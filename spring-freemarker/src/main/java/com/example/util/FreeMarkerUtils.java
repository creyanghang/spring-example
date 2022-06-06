package com.example.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.WritableResource;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Map;

/**
 * ftl模板导出word
 * <br>
 * created date 2020/4/22
 *
 * @author huzhaoyang
 */

@Slf4j
public class FreeMarkerUtils {

    public static String createWord(Map dataMap, String templateName, String fileName, String attachPath) {
        FileInputStream fis = null;
        Writer outWriter = null;
        try {
            // 创建配置实例
            Configuration configuration = new Configuration();

            // 设置编码
            configuration.setDefaultEncoding("UTF-8");

            // ftl模板文件
            configuration.setClassForTemplateLoading(FreeMarkerUtils.class, "/templates");

            // 获取模板
            Template template = configuration.getTemplate(templateName, "UTF-8");

            // 输出文件  file有大坑
         //   outFile = new File(attachPath + fileName);

            WritableResource resource = new FileUrlResource(attachPath + fileName);

            outWriter = new OutputStreamWriter(resource.getOutputStream(),"UTF-8");

            template.process(dataMap, outWriter);
            outWriter.flush();
            return fileName;
        } catch (Exception e) {
            log.error("生成word异常{}:", e.getMessage());
            return "生成word异常"+e.getMessage();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("FileInputStream异常{}:", e.getMessage());
                }
            }
            if (outWriter != null) {
                try {
                    outWriter.close();
                } catch (IOException e) {
                    log.error("outWriter异常{}:", e.getMessage());
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    public static String getImageBase(String src) {
        if (src == null || src == "") {
            return "";
        }
        File file = new File(src);
        if (!file.exists()) {
            return "";
        }
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
