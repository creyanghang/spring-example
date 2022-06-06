package com.mail.controller;

import com.mail.service.MailService;
import com.mail.util.FtlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yh
 * @description
 * @date 2022/6/4
 */
@RestController
@RequestMapping("/send")
public class SendMail {
    @Autowired
    private MailService mailService;

    /**
     * 发送文本邮件
     * @author  yh
     * @date  2022/6/4
     */
    @GetMapping("/sendMail")
    public String sendMail() {
        String subject = "hello world";
        String content = "这是一封邮件";
        List<String> toWhoList = new ArrayList<>();
        toWhoList.add("creyanghang@126.com");

        mailService.sendSimpleTextMailActual(subject, content, toWhoList.toArray(new String[0]), null, null, null);
        return "send success";
    }

    /**
     * 发送文本附件邮件
     * @author  yh
     * @date  2022/6/4
     */
    @GetMapping("/sendMailFile")
    public String sendMailFile() {
        String subject = "hello world";
        String content = "带附件的邮件";
        List<String> toWhoList = new ArrayList<>();
        toWhoList.add("creyanghang@126.com");

        List<String> fileList = new ArrayList<>();
        fileList.add("E:\\系统\\Computer壁纸\\Atrax-Desktop(Bitcoin)\\0000.jpg");

        mailService.sendSimpleTextMailActual(subject, content, toWhoList.toArray(new String[0]), null, null, fileList.toArray(new String[0]));
        return "send success";
    }

    /**
     * 根据模板发送邮件
     * @author yh
     * @date 2022/6/4
     * @return result
     */
    @GetMapping("/sendHtmlMail")
    public String sendHtmlMail(){
        String subject = "飞人网络平台验证码";
        String content = FtlUtil.buildContent("948917");
        List<String> toWhoList = new ArrayList<>();
        toWhoList.add("creyanghang@126.com");

        mailService.sendHtmlMail(subject, content, toWhoList.toArray(new String[0]));
        return "send success";
    }
}
