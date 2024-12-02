package com.bupt.stockassistant.controller;

import cn.hutool.core.util.XmlUtil;
import com.bupt.stockassistant.service.WeChatMessageService;
import com.bupt.stockassistant.service.WeChatServer;
import com.bupt.stockassistant.weixin.mp.aes.AesException;
import com.bupt.stockassistant.weixin.mp.aes.SHA1;
import com.bupt.stockassistant.weixin.mp.aes.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import utils.WechatConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static utils.MessageUtil.*;

/**
 * Program : StockAssistant
 * Author : llj
 * Create : 2024-09-18 19:37
 * docker build -t sa .
 * docker run -d -p 80:8081 --name sa sa
 **/

@RestController
public class WechatMsgController {

    @Autowired
    private WeChatMessageService weChatMessageService;

    @Autowired
    private WeChatServer weChatServer;

    @RequestMapping("/index.html")
    public void sendBackMessage(HttpServletRequest request, HttpServletResponse response) {

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            while( (line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
            String xml = stringBuilder.toString();
            System.out.println("xml = " + xml);
            Document document = XmlUtil.parseXml(xml);

            //显示发送内容
            System.out.println("msgType = " + document.getElementsByTagName("MsgType").item(0).getTextContent());
            //System.out.println("content = " + document.getElementsByTagName("Content").item(0).getTextContent());
            System.out.println("FromUserName = " + document.getElementsByTagName("FromUserName").item(0).getTextContent());
            System.out.println("ToUserName = " + document.getElementsByTagName("ToUserName").item(0).getTextContent());
            System.out.println("MsgType = " + document.getElementsByTagName("MsgType").item(0).getTextContent());

            response.setContentType("text/xml;charset=utf-8");
            PrintWriter writer = response.getWriter();

            //System.out.println(weChatMessageService.sendDailyPredictionResult());




            if(document.getElementsByTagName("MsgType").item(0).getTextContent().equals(MESSAGE_EVENT))
            {
                if(document.getElementsByTagName("Event").item(0).getTextContent().equals(EVENT_SUB)){
                    String subscribeText = "欢迎关注BYR智能投顾公众号！点击\"主板\"、\"科创板\"、\"创业板\"可查询对应板块模型最新预测结果！\n系统每日15：00更新，15：00后可推荐明日股票，15：00前推荐结果为当日股票，请关注股票推荐日期！";
                    writer.write(
                            "<xml>\n" +
                                    "  <ToUserName><![CDATA[" + document.getElementsByTagName("FromUserName").item(0).getTextContent() + "]]></ToUserName>\n" +
                                    "  <FromUserName><![CDATA[" + document.getElementsByTagName("ToUserName").item(0).getTextContent() + "]]></FromUserName>\n" +
                                    "  <CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                                    "  <Content><![CDATA[" + subscribeText + "]]></Content>\n" +
                                    "</xml>"
                    );
                    System.out.println("关注成功！");
                }

                if(document.getElementsByTagName("Event").item(0).getTextContent().equals(EVENT_CLICK)){
                    String key = document.getElementsByTagName("EventKey").item(0).getTextContent();
                    System.out.println("key = " + key);
                    writer.write(
                            "<xml>\n" +
                                    "  <ToUserName><![CDATA[" + document.getElementsByTagName("FromUserName").item(0).getTextContent() + "]]></ToUserName>\n" +
                                    "  <FromUserName><![CDATA[" + document.getElementsByTagName("ToUserName").item(0).getTextContent() + "]]></FromUserName>\n" +
                                    "  <CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                                    "  <Content><![CDATA[" + "查询板块：" + key + "\n" + weChatMessageService.sendDailyPredictionResult(key) + "]]></Content>\n" +
                                    "</xml>"
                    );
                    System.out.println("关注成功！");
                }

            }
            else{
                String text = document.getElementsByTagName("Content").item(0).getTextContent();
                if(text.equals("股票推荐")||text.equals("科创板")||text.equals("创业板")||text.equals("主板")){
                    writer.write(
                            "<xml>\n" +
                                    "  <ToUserName><![CDATA[" + document.getElementsByTagName("FromUserName").item(0).getTextContent() + "]]></ToUserName>\n" +
                                    "  <FromUserName><![CDATA[" + document.getElementsByTagName("ToUserName").item(0).getTextContent() + "]]></FromUserName>\n" +
                                    "  <CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                                    "  <Content><![CDATA[" + "查询板块：" + text + "\n" + weChatMessageService.sendDailyPredictionResult(text) + "]]></Content>\n" +
                                    "</xml>"
                    );
                    System.out.println("已回复");
                }
                else {
                    //String txText = "查询推荐股票请回复板块名称！回复\"主板\"、\"科创板\"、\"创业板\"可查询对应板块！";
                    String txText = "查询推荐股票请点击下方板块名称！";
                    writer.write(
                            "<xml>\n" +
                                    "  <ToUserName><![CDATA[" + document.getElementsByTagName("FromUserName").item(0).getTextContent() + "]]></ToUserName>\n" +
                                    "  <FromUserName><![CDATA[" + document.getElementsByTagName("ToUserName").item(0).getTextContent() + "]]></FromUserName>\n" +
                                    "  <CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                                    "  <Content><![CDATA[" + txText + "]]></Content>\n" +
                                    "</xml>"
                    );
                    System.out.println("已读乱回！");
                }

            }

            writer.flush();
            writer.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 加密 正式
//    @RequestMapping("/index.html")
//    public void check(String msg_Signature, String timestamp, String nonce,HttpServletRequest request)  {
//
//        System.out.println("signature = " + msg_Signature);
//        System.out.println("timestamp = " + timestamp);
//        System.out.println("nonce = " + nonce);
//
//        try{
//            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//            String line = null;
//            StringBuilder stringBuilder = new StringBuilder();
//            while( (line = reader.readLine()) != null){
//                stringBuilder.append(line);
//            }
//            String xml = stringBuilder.toString();
//            System.out.println("xml = " + xml);
//            Document document = XmlUtil.parseXml(xml);
//
//
//            //解密
//            WXBizMsgCrypt pc = new WXBizMsgCrypt(WechatConstants.TOKEN, WechatConstants.EncodingAESKey, WechatConstants.APP_ID);
//            String result2 = pc.decryptMsg(msg_Signature, timestamp, nonce, xml);
//            System.out.println("解密后明文: " + result2);
//
//            //显示发送内容
//            Node msgType = document.getElementsByTagName("MsgType").item(0);
//            System.out.println("msgType = " + msgType.getTextContent());
//            Node content = document.getElementsByTagName("Content").item(0);
//            System.out.println("content = " + content.getTextContent());
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    //@RequestMapping("/index.html")
    public String index(String signature, String timestamp, String nonce, String echostr){
        System.out.println("signature = " + signature);
        System.out.println("timestamp = " + timestamp);
        System.out.println("nonce = " + nonce);
        System.out.println("echostr = " + echostr);
        String sign = null;
        try {
            sign = SHA1.getSHA1(WechatConstants.TOKEN, timestamp, nonce, "");
            if (sign.equals(signature)) {
                return echostr;
            }
        } catch (AesException e) {

            throw new RuntimeException(e);

        }
        return "";
    }



}
