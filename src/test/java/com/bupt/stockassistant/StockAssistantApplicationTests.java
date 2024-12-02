package com.bupt.stockassistant;

import cn.hutool.json.JSONObject;
import com.bupt.stockassistant.entity.StockList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import utils.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class StockAssistantApplicationTests {


    @Test
    public void accessToken(){
        String json = HttpUtil.httpsGet("https://api.weixin.qq.com/cgi-bin/token" ,"grant_type=client_credential&appid=" + WechatConstants.APP_ID + "&secret=" + WechatConstants.APP_SECRET, WechatConstants.CHARSET);
        System.out.println("json = " + json);
    }


    //POST https://api.weixin.qq.com/cgi-bin/stable_token
    @Test
    public void stableToken(){
        String params ="{\n" +
                "    \"grant_type\": \"client_credential\",\n" +
                "    \"appid\": \"" + WechatConstants.APP_ID +"\",\n" +
                "    \"secret\": \"" + WechatConstants.APP_SECRET +"\",\n" +
                "    \"force_refresh\": false\n" +
                "}";

        String json = HttpUtil.httpsPost("https://api.weixin.qq.com/cgi-bin/stable_token", params , WechatConstants.CHARSET);
        System.out.println("json = " + json);
    }


    public String access_token = "84_1EZNcPYKf9NTGOVWFRglpRJ2vqGcABkPttQLU8gFHHRmezMGk0sQiCh7LWGMNVW0ShAPdma5_Ikyep8NaNG-96P0oiuXga0BRW8YPaDkb1hpr5dRyOD3ElfW1_4QOLcAEAUSP";

    @Test
    public void menu(){
        String params = " {\n" +
                "     \"button\":[\n" +
                "     {\t\n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"今日预测\",\n" +
                "          \"key\":\"V1001_TODAY_MUSIC\"\n" +
                "      },\n" +
                "      {\n" +
                "           \"name\":\"菜单\",\n" +
                "           \"sub_button\":[\n" +
                "           {\t\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"搜索股票号\",\n" +
                "               \"url\":\"http://www.soso.com/\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"click\",\n" +
                "               \"name\":\"赞一下我们\",\n" +
                "               \"key\":\"V1001_GOOD\"\n" +
                "            }]\n" +
                "       }]\n" +
                " }";
        //https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
        String json = HttpUtil.httpsPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token, params , WechatConstants.CHARSET);
        System.out.println("json = " + json);
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void redisTest(){
        String access_token = "84_OAnrVw9vaHlme8Usp6TA9uso63BO_jIBnj5be-roWEIVFl1R4wWHpHXmXb_b06HIq9Rcn7FE525aqPth0XRHyFWIVX0kCzLbaComdNEI14UMf0SnvfcMFWE1woIRIEbAGAVMI";

        stringRedisTemplate.opsForValue().set("stockassistant:access_token", access_token, 60L, TimeUnit.SECONDS);
        //stringRedisTemplate.expire("access_token:" + access_token, 60L, TimeUnit.SECONDS);


    }

    @Test
    public void searchTest() {
        String todayData = "20241018";
        //StockList stockList = getBaseMapper().queryStockListByDate(DateUtil.getYesterdayDate(todayData));
        //System.out.println("stockList = " + stockList.toString());

//        String result = DateUtil.processDate(stockList.getDate()) + "\n今日推荐股票组合：\n";
//
//        //System.out.println("result = " + result);
//
//        String back = result + StockUtil.processStockData(stockList.getTopStocks());

    }

    @Test
    public void searchStock() {
        String todayData = "300799.SZ,300760.SZ,300374.SZ,300896.SZ,300061.SZ,301076.SZ,300527.SZ,300265.SZ,300086.SZ,300300.SZ,300425.SZ,301000.SZ,301156.SZ,300808.SZ,300855.SZ,300084.SZ,300933.SZ,300556.SZ,300189.SZ,300311.SZ,300494.SZ,300215.SZ,300021.SZ,300198.SZ,300169.SZ,301215.SZ,300641.SZ,300530.SZ,300234.SZ,300835.SZ,300205.SZ,300111.SZ,301137.SZ,300599.SZ,300690.SZ,300052.SZ,300509.SZ,300635.SZ,300468.SZ,301091.SZ,300127.SZ,300883.SZ,300032.SZ,300611.SZ,300040.SZ,300385.SZ,300407.SZ,300126.SZ,300155.SZ,300137.SZ";
        String result = StockUtil.processStockData(todayData);
        System.out.println("result = " + result);
        //StockList stockList = getBaseMapper().queryStockListByDate(DateUtil.getYesterdayDate(todayData));
        //System.out.println("stockList = " + stockList.toString());

//        String result = DateUtil.processDate(stockList.getDate()) + "\n今日推荐股票组合：\n";
//
//        //System.out.println("result = " + result);
//
//        String back = result + StockUtil.processStockData(stockList.getTopStocks());

    }

//    @Test
//    public void searchCode(){
//        String endpoint = "http://webservice.webxml.com.cn/WebServices/ChinaStockWebService.asmx";
//
//        Service service = new Service();
//
//        Call call = (Call)service.createCall();
//
//        call.setTargetEndpointAddress(new URL(endpoint));
//        call.setOperationName(new QName("http://WebXml.com.cn/", "getStockInfoByCode"));
//
//        call.setUseSOAPAction(true);
//        call.setSOAPActionURI("http://WebXml.com.cn/getStockInfoByCode");
//
//        call.addParameter(new QName("http://WebXml.com.cn/","theStockCode"), XMLType.XSD_STRING, ParameterMode.IN);
//        call.setReturnType(new QName("<A href="http://WebXml.com.cn/","getStockInfoByCodeResponse"),String[].class">http://WebXml.com.cn/","getStockInfoByCodeResponse"),String[].class);
//
//        return (String[])call.invoke(new Object[]{id});
//
//    }


}
