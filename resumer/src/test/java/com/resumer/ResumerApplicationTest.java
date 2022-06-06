package com.resumer;

import com.resumer.mapper.user.PicVerifyMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description
 * @auth yh
 * @date 2022/5/24
 */
@Slf4j
@SpringBootTest
public class ResumerApplicationTest {

    @Autowired
    private PicVerifyMapper picVerifyMapper;

    @Test
    public void test1() {



//        //定义图形验证码的长和宽
//        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
//
////图形验证码写出，可以写出到文件，也可以写出到流
//        for (int i = 0; i < 1000; i++) {
//            lineCaptcha.createCode();
//
//            String picName = UUID.randomUUID() + ".png";
//            lineCaptcha.write("E:\\Java\\pic_verify\\" + picName);
//            String code = lineCaptcha.getCode();
//
//
//            PicVerify picVerify = new PicVerify();
//            picVerify.setVerifyCode(code)
//                    .setVerifyFileName(picName);
//            int insert = picVerifyMapper.insert(picVerify);
//            System.out.println(insert + "___" + code);
//        }
    }
}