package com.bupt.stockassistant.service.impl;

/**
 * Program : StockAssistant
 * Author : llj
 * Create : 2024-10-17 21:40
 **/

import com.alibaba.fastjson.JSONObject;
import com.bupt.stockassistant.service.WeChatServer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import utils.HttpUtil;
import utils.WechatConstants;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author: D
 * @since: 2020/9/6
 * @version: 1
 */
@Service
public class WeChatServerImpl implements WeChatServer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * redis中存放access_token的key值
     */
    private final static String ACCESS_TOKEN_KEY = "stockassistant:access_token";

    /**
     * redis中存放获取失败的错误信息的key值
     */
    private final static String ERROR_KEY = "stockassistant:error";

    @Scheduled(initialDelay = 5000,fixedRate = 6600000)
    @Override
    public void creatAccessToken() {

        String json = HttpUtil.httpsGet("https://api.weixin.qq.com/cgi-bin/token" ,"grant_type=client_credential&appid=" + WechatConstants.APP_ID + "&secret=" + WechatConstants.APP_SECRET, WechatConstants.CHARSET);
        System.out.println("json = " + json);

        JSONObject jsonObject = JSONObject.parseObject(json);

        String accessToken = jsonObject.getString("access_token");

        System.out.println(accessToken);
        // accessToken获取成功
        if (accessToken != null) {
            //有效期设置1小时55分钟
            //.set(ACCESS_TOKEN_KEY, accessToken, 115, TimeUnit.MINUTES);
            stringRedisTemplate.opsForValue().set(ACCESS_TOKEN_KEY, accessToken, 115L, TimeUnit.MINUTES);
            System.out.println("缓存access token成功");
        } else {
            //redisUtil.set(ERROR_KEY, json);
            stringRedisTemplate.opsForValue().set(ERROR_KEY, json);
            System.out.println("未找到access token");
        }
    }

    @Scheduled(initialDelay = 10000, fixedRate = 666600000)
    @Override
    public void creatMenu() {
        String params = " {\n" +
                "     \"button\":[\n" +
                "     {\t\n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"主板\",\n" +
                "          \"key\":\"主板\"\n" +
                "      },\n" +
                "     {\t\n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"科创板\",\n" +
                "          \"key\":\"科创板\"\n" +
                "      },\n" +
                "     {\t\n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"创业板\",\n" +
                "          \"key\":\"创业板\"\n" +
                "      }\n" +
                "       ]\n" +
                " }";
//        String params = " {\n" +
//                "     \"button\":[\n" +
//                "     {\t\n" +
//                "          \"type\":\"click\",\n" +
//                "          \"name\":\"今日预测\",\n" +
//                "          \"key\":\"主板\"\n" +
//                "      },\n" +
//                "      {\n" +
//                "           \"name\":\"板块\",\n" +
//                "           \"sub_button\":[\n" +
//                "           {\t\n" +
//                "               \"type\":\"click\",\n" +
//                "               \"name\":\"主板\",\n" +
//                "               \"key\":\"主板\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"type\":\"click\",\n" +
//                "               \"name\":\"创业板\",\n" +
//                "               \"key\":\"创业板\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"type\":\"click\",\n" +
//                "               \"name\":\"科创板\",\n" +
//                "               \"key\":\"科创板\"\n" +
//                "            }]\n" +
//                "       }]\n" +
//                " }";
        //https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
        System.out.println(params);
        String json = HttpUtil.httpsPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + getAccessToken(), params , WechatConstants.CHARSET);
        System.out.println("json = " + json);
    }



    @Override
    public String getAccessToken() {
        //这大概率不会运行 除非redis连接失败或者蹦了
        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(ACCESS_TOKEN_KEY))) {
            throw new RuntimeException("获取失败，请稍后重试");
        }
        //return (String) redisUtil.get(ACCESS_TOKEN_KEY);
        return (String) stringRedisTemplate.opsForValue().get(ACCESS_TOKEN_KEY);
    }
}


