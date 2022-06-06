package com.code.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author yh
 * @description
 * @date 2022/6/5
 */
@RestController
@RequestMapping("/code")
public class CodeController {

    /**
     * 生产二维码
     *
     * @param response response
     * @author yh
     * @date 2022/6/5
     */
    @RequestMapping("/createCode")
    public void createCode(HttpServletResponse response) throws IOException {
        // 生成指定url对应的二维码到文件，宽和高都是300像素
//        QrCodeUtil.generate("www.baidu.com", 300, 300, FileUtil.file("E:\\data\\file\\qrcode.jpg"));

        // 返回文件流
        OutputStream out = response.getOutputStream();
        BufferedImage generate = QrCodeUtil.generate("www.baidu.com", 300, 300);
        ImageIO.write(generate, "jpg", out);
        generate.flush();
        out.flush();
        out.close();
    }

    /**
     * 生产彩色二维码
     *
     * @author yh
     * @date 2022/6/5
     */
    @RequestMapping("/createCode2")
    public void createCode2() {
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.CYAN.getRGB());
        // 设置背景色（灰色）
        config.setBackColor(Color.GRAY.getRGB());

        // 生成二维码到文件，也可以到流
        QrCodeUtil.generate("www.baidu.com", config, FileUtil.file("E:\\data\\file\\qrcode.jpg"));
    }

    /**
     * 生产带logo二维码
     *
     * @author yh
     * @date 2022/6/5
     */
    @RequestMapping("/createLogoCode")
    public void createLogoCode() {
        QrCodeUtil.generate(//
                "www.baidu.com", //二维码内容
                QrConfig.create().setImg("E:\\系统\\Computer壁纸\\Atrax-Desktop(Bitcoin)\\0000.jpg"), //附带logo
                FileUtil.file("E:\\data\\file\\qrcodeWithLogo.jpg")//写出到的文件
        );
    }

    /**
     * 低级别的像素块更大，可以远距离识别，但是遮挡就会造成无法识别。高级别则相反，像素块小，允许遮挡一定范围，但是像素块更密集。
     *
     * @author yh
     * @date 2022/6/5
     */
    @RequestMapping("/createPixel")
    public void createPixel() {
        QrConfig config = new QrConfig();
        // 高纠错级别，L、M、Q、H几个参数，由低到高
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        QrCodeUtil.generate("www.baidu.com", config, FileUtil.file("E:\\data\\file\\qrcodeCustom.jpg"));
    }

    /**
     * 识别二维码
     *
     * @author yh
     * @date 2022/6/5
     */
    @RequestMapping("/decodeQrCode")
    public void decodeQrCode() {
        // decode -> "http://hutool.cn/"
        String decode = QrCodeUtil.decode(FileUtil.file("E:\\data\\file\\qrcodeCustom.jpg"));
        System.out.println("二维码内容：" + decode);
    }
}
