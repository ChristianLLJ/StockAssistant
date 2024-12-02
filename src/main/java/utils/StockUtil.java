package utils;

/**
 * Program : StockAssistant
 * Author : llj
 * Create : 2024-09-02 17:36
 **/
import com.bupt.stockassistant.StockAssistantApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static cn.hutool.core.text.CharSequenceUtil.str;
//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;

public class StockUtil {
    // 假设这是我们股票代码到股票名称的映射表
    private static final Map<String, String> stockCodeToNameMap = new HashMap<>();

//    static {
//        stockCodeToNameMap.put("600573.SH", "惠泉啤酒");
//        stockCodeToNameMap.put("000507.SZ", "珠海港");
//        stockCodeToNameMap.put("603098.SH", "森特股份");
//        stockCodeToNameMap.put("600361.SH", "华联综超");
//        stockCodeToNameMap.put("300671.SZ", "富满电子");
//        stockCodeToNameMap.put("002617.SZ", "露笑科技");
//        stockCodeToNameMap.put("688198.SH", "佰仁医疗");
//        stockCodeToNameMap.put("300184.SZ", "力源信息");
//        stockCodeToNameMap.put("603032.SH", "德新交运");
//        stockCodeToNameMap.put("603738.SH", "泰晶科技");
//        stockCodeToNameMap.put("000582.SZ", "北部湾港");
//        stockCodeToNameMap.put("600279.SH", "重庆港九");
//        stockCodeToNameMap.put("000545.SZ", "金浦钛业");
//        stockCodeToNameMap.put("600862.SH", "中航高科");
//        stockCodeToNameMap.put("688116.SH", "天奈科技");
//        stockCodeToNameMap.put("300648.SZ", "星云股份");
//        stockCodeToNameMap.put("300870.SZ", "欧陆通");
//        stockCodeToNameMap.put("300568.SZ", "星源材质");
//        stockCodeToNameMap.put("000823.SZ", "超声电子");
//        stockCodeToNameMap.put("600063.SH", "皖维高新");
//        stockCodeToNameMap.put("600338.SH", "西藏珠峰");
//        stockCodeToNameMap.put("300696.SZ", "爱乐达");
//        stockCodeToNameMap.put("300627.SZ", "华测导航");
//        stockCodeToNameMap.put("000564.SZ", "供销大集");
//        stockCodeToNameMap.put("688301.SH", "奕瑞科技");
//        stockCodeToNameMap.put("000729.SZ", "燕京啤酒");
//        stockCodeToNameMap.put("600600.SH", "青岛啤酒");
//        stockCodeToNameMap.put("600150.SH", "中国船舶");
//        stockCodeToNameMap.put("688009.SH", "中国数码");
//        stockCodeToNameMap.put("000099.SZ", "中信海直");
//        stockCodeToNameMap.put("001965.SZ", "招商公路");
//        stockCodeToNameMap.put("600875.SH", "东方电气");
//        stockCodeToNameMap.put("600373.SH", "中文传媒");
//        stockCodeToNameMap.put("601187.SH", "厦门信达");
//        stockCodeToNameMap.put("600482.SH", "中国电建");
//        stockCodeToNameMap.put("601857.SH", "中国石油");
//        stockCodeToNameMap.put("600511.SH", "国药股份");
//        stockCodeToNameMap.put("601838.SH", "成都银行");
//        stockCodeToNameMap.put("600096.SH", "云天化");
//        stockCodeToNameMap.put("601019.SH", "东风汽车");
//        stockCodeToNameMap.put("000028.SZ", "国药一致");
//        stockCodeToNameMap.put("601600.SH", "中国铝业");
//        stockCodeToNameMap.put("600820.SH", "隧道股份");
//        stockCodeToNameMap.put("600894.SH", "广日股份");
//        stockCodeToNameMap.put("600350.SH", "山东高速");
//        stockCodeToNameMap.put("601058.SH", "赛轮轮胎");
//        stockCodeToNameMap.put("601117.SH", "中国化学");
//        stockCodeToNameMap.put("601077.SH", "渤海轮渡");
//        stockCodeToNameMap.put("600050.SH", "中国联通");
//        stockCodeToNameMap.put("002839.SZ", "张家港行");
//        stockCodeToNameMap.put("601989.SH", "中国重工");
//        stockCodeToNameMap.put("000680.SZ", "山推股份");
//        stockCodeToNameMap.put("600642.SH", "申能股份");
//        stockCodeToNameMap.put("688408.SH", "中信博");
//        stockCodeToNameMap.put("000423.SZ", "东阿阿胶");
//        stockCodeToNameMap.put("002039.SZ", "黔源电力");
//        stockCodeToNameMap.put("600801.SH", "华新水泥");
//        stockCodeToNameMap.put("600285.SH", "羚锐制药");
//        stockCodeToNameMap.put("000429.SZ", "粤高速");
//        stockCodeToNameMap.put("000651.SZ", "格力电器");
//        stockCodeToNameMap.put("601567.SH", "三星医疗");
//        stockCodeToNameMap.put("601390.SH", "中国中铁");
//        stockCodeToNameMap.put("600012.SH", "皖通高速");
//        stockCodeToNameMap.put("000528.SZ", "柳工");
//        stockCodeToNameMap.put("600886.SH", "国投电力");
//        stockCodeToNameMap.put("601766.SH", "中国中车");
//        stockCodeToNameMap.put("601169.SH", "北京银行");
//        stockCodeToNameMap.put("601611.SH", "中国核建");
//        stockCodeToNameMap.put("603619.SH", "中曼石油");
//        stockCodeToNameMap.put("600132.SH", "重庆啤酒");
//        stockCodeToNameMap.put("003816.SZ", "中国广核");
//        stockCodeToNameMap.put("600028.SH", "中国石化");
//        stockCodeToNameMap.put("688155.SH", "先导智能");
//        stockCodeToNameMap.put("601021.SH", "春秋航空");
//        stockCodeToNameMap.put("601333.SH", "广深铁路");
//        stockCodeToNameMap.put("600136.SH", "ST明诚");
//        stockCodeToNameMap.put("002424.SZ", "贵州百灵");
//        stockCodeToNameMap.put("600289.SH", "亿阳信通");
//        stockCodeToNameMap.put("600381.SH", "青海春天");
//        stockCodeToNameMap.put("000669.SZ", "金鸿控股");
//        stockCodeToNameMap.put("603869.SH", "北部湾港");
//        stockCodeToNameMap.put("002427.SZ", "尤夫股份");
//        stockCodeToNameMap.put("002431.SZ", "棕榈股份");
//        stockCodeToNameMap.put("300301.SZ", "长方集团");
//        stockCodeToNameMap.put("603616.SH", "韩建河山");
//        stockCodeToNameMap.put("003039.SZ", "顺控发展");
//        stockCodeToNameMap.put("603012.SH", "创力集团");
//        stockCodeToNameMap.put("000622.SZ", "恒立实业");
//        stockCodeToNameMap.put("603318.SH", "派思股份");
//        stockCodeToNameMap.put("002485.SZ", "希努尔");
//        stockCodeToNameMap.put("603022.SH", "新通联");
//        stockCodeToNameMap.put("002752.SZ", "昇兴股份");
//        stockCodeToNameMap.put("002200.SZ", "云投生态");
//        stockCodeToNameMap.put("688068.SH", "热景生物");
//        stockCodeToNameMap.put("603116.SH", "红蜻蜓");
//        stockCodeToNameMap.put("603976.SH", "正川股份");
//        stockCodeToNameMap.put("603159.SH", "上海亚虹");
//        stockCodeToNameMap.put("300869.SZ", "康泰医学");
//        stockCodeToNameMap.put("300676.SZ", "华大基因");
//        stockCodeToNameMap.put("600917.SH", "重庆燃气");
//        stockCodeToNameMap.put("002425.SZ", "凯撒文化");
//        stockCodeToNameMap.put("300437.SZ", "清水源");
//        stockCodeToNameMap.put("300683.SZ", "海特生物");
//        stockCodeToNameMap.put("601619.SH", "嘉泽新能");
//        stockCodeToNameMap.put("002838.SZ", "道恩股份");
//        stockCodeToNameMap.put("605300.SH", "佳禾食品");
//        stockCodeToNameMap.put("688277.SH", "天智航");
//        stockCodeToNameMap.put("603003.SH", "龙宇燃油");
//        stockCodeToNameMap.put("000609.SZ", "绵世股份");
//        stockCodeToNameMap.put("603377.SH", "东方时尚");
//        stockCodeToNameMap.put("603388.SH", "元成股份");
//        stockCodeToNameMap.put("002197.SZ", "证通电子");
//        stockCodeToNameMap.put("600083.SH", "博信股份");
//        stockCodeToNameMap.put("002564.SZ", "天沃科技");
//        stockCodeToNameMap.put("300126.SZ", "锐奇股份");
//        stockCodeToNameMap.put("002289.SZ", "宇顺电子");
//        stockCodeToNameMap.put("688051.SH", "佳华科技");
//        stockCodeToNameMap.put("300020.SZ", "银江股份");
//        stockCodeToNameMap.put("603879.SH", "永悦科技");
//        stockCodeToNameMap.put("000040.SZ", "东旭蓝天");
//        stockCodeToNameMap.put("600800.SH", "天津磁卡");
//        stockCodeToNameMap.put("605366.SH", "博汇股份");
//        stockCodeToNameMap.put("601010.SH", "文峰股份");
//        stockCodeToNameMap.put("605389.SH", "长龄液压");
//        stockCodeToNameMap.put("002366.SZ", "台海核电");
//        stockCodeToNameMap.put("000796.SZ", "凯撒旅业");
//        stockCodeToNameMap.put("002656.SZ", "摩登大道");
//        stockCodeToNameMap.put("688345.SH", "博众精工");
//        stockCodeToNameMap.put("002569.SZ", "步森股份");
//        stockCodeToNameMap.put("688010.SH", "福光股份");
//        stockCodeToNameMap.put("000767.SZ", "漳泽电力");
//        stockCodeToNameMap.put("300899.SZ", "未来电器");
//        stockCodeToNameMap.put("600608.SH", "沪电股份");
//        stockCodeToNameMap.put("002748.SZ", "世龙实业");
//        stockCodeToNameMap.put("603630.SH", "拉芳家化");
//        stockCodeToNameMap.put("300137.SZ", "先河环保");
//        stockCodeToNameMap.put("600307.SH", "酒钢宏兴");
//        stockCodeToNameMap.put("603226.SH", "菲林格尔");
//        stockCodeToNameMap.put("002251.SZ", "步步高");
//        stockCodeToNameMap.put("002750.SZ", "龙津药业");
//        stockCodeToNameMap.put("603557.SH", "起帆电缆");
//        stockCodeToNameMap.put("688558.SH", "国盛智科");
//        stockCodeToNameMap.put("300300.SZ", "汉鼎宇佑");
//        stockCodeToNameMap.put("002476.SZ", "宝莫股份");
//        stockCodeToNameMap.put("002482.SZ", "广田集团");
//        stockCodeToNameMap.put("688607.SH", "联赢激光");
//        stockCodeToNameMap.put("002047.SZ", "宝鹰股份");
//        stockCodeToNameMap.put("603535.SH", "嘉诚国际");
//        stockCodeToNameMap.put("002471.SZ", "中超控股");
//        stockCodeToNameMap.put("300029.SZ", "天龙光电");
//        stockCodeToNameMap.put("300055.SZ", "万邦达");
//        stockCodeToNameMap.put("300798.SZ", "锦鸡股份");
//        stockCodeToNameMap.put("002872.SZ", "天圣制药");
//        stockCodeToNameMap.put("002069.SZ", "獐子岛");
//        stockCodeToNameMap.put("000584.SZ", "哈工智能");
//        stockCodeToNameMap.put("600654.SH", "中安消");
//        stockCodeToNameMap.put("688529.SH", "倍轻松");
//        stockCodeToNameMap.put("002316.SZ", "键桥通讯");
//        stockCodeToNameMap.put("002513.SZ", "蓝丰生化");
//        stockCodeToNameMap.put("002798.SZ", "帝欧家居");
//        stockCodeToNameMap.put("002589.SZ", "瑞康医药");
//        stockCodeToNameMap.put("600408.SH", "安泰集团");
//        stockCodeToNameMap.put("002592.SZ", "八菱科技");
//        stockCodeToNameMap.put("300623.SZ", "捷捷微电");
//        stockCodeToNameMap.put("300458.SZ", "全志科技");
//        stockCodeToNameMap.put("300487.SZ", "三孚股份");
//        stockCodeToNameMap.put("300613.SZ", "富瀚微");
//        stockCodeToNameMap.put("300053.SZ", "欧比特");
//        stockCodeToNameMap.put("300077.SZ", "国民技术");
//        stockCodeToNameMap.put("300505.SZ", "川金诺");
//        stockCodeToNameMap.put("300377.SZ", "赢时胜");
//        stockCodeToNameMap.put("300925.SZ", "法本信息");
//        stockCodeToNameMap.put("300866.SZ", "安克创新");
//        stockCodeToNameMap.put("300465.SZ", "高伟达");
//        stockCodeToNameMap.put("300085.SZ", "银之杰");
//        stockCodeToNameMap.put("300831.SZ", "派瑞股份");
//        stockCodeToNameMap.put("300046.SZ", "台基股份");
//        stockCodeToNameMap.put("300327.SZ", "中颖电子");
//        stockCodeToNameMap.put("300661.SZ", "圣邦股份");
//        stockCodeToNameMap.put("300746.SZ", "汉嘉设计");
//        stockCodeToNameMap.put("300663.SZ", "科蓝软件");
//        stockCodeToNameMap.put("300609.SZ", "汇纳科技");
//        stockCodeToNameMap.put("300598.SZ", "诚迈科技");
//        stockCodeToNameMap.put("300071.SZ", "华谊嘉信");
//        stockCodeToNameMap.put("300927.SZ", "江天化学");
//        stockCodeToNameMap.put("300200.SZ", "高盟新材");
//        stockCodeToNameMap.put("300561.SZ", "汇金科技");
//        stockCodeToNameMap.put("300597.SZ", "吉大通信");
//        stockCodeToNameMap.put("300260.SZ", "新莱应材");
//        stockCodeToNameMap.put("300709.SZ", "精研科技");
//        stockCodeToNameMap.put("300047.SZ", "天源迪科");
//        stockCodeToNameMap.put("300281.SZ", "华安智能");
//        stockCodeToNameMap.put("300075.SZ", "数字政通");
//        stockCodeToNameMap.put("300672.SZ", "国科微");
//        stockCodeToNameMap.put("300348.SZ", "长亮科技");
//        stockCodeToNameMap.put("300226.SZ", "上海钢联");
//        stockCodeToNameMap.put("300472.SZ", "新元科技");
//        stockCodeToNameMap.put("300560.SZ", "中富通");
//        stockCodeToNameMap.put("300271.SZ", "华宇软件");
//        stockCodeToNameMap.put("300605.SZ", "恒锋信息");
//        stockCodeToNameMap.put("300921.SZ", "南凌科技");
//        stockCodeToNameMap.put("300735.SZ", "光弘科技");
//        stockCodeToNameMap.put("300768.SZ", "迪普科技");
//        stockCodeToNameMap.put("300951.SZ", "博硕科技");
//        stockCodeToNameMap.put("300678.SZ", "中科信息");
//        stockCodeToNameMap.put("300212.SZ", "易华录");
//        stockCodeToNameMap.put("300682.SZ", "朗新科技");
//        stockCodeToNameMap.put("300811.SZ", "铂科新材");
//        stockCodeToNameMap.put("300564.SZ", "筑博设计");
//        stockCodeToNameMap.put("300253.SZ", "卫宁健康");
//        stockCodeToNameMap.put("300266.SZ", "兴源环境");
//        stockCodeToNameMap.put("300352.SZ", "北信源");
//        stockCodeToNameMap.put("688311.SH", "盟科药业");
//        stockCodeToNameMap.put("688528.SH", "秦川物联");
//        stockCodeToNameMap.put("688511.SH", "天微电子");
//        stockCodeToNameMap.put("688639.SH", "华恒生物");
//        stockCodeToNameMap.put("688690.SH", "纳微科技");
//        stockCodeToNameMap.put("688600.SH", "皖仪科技");
//        stockCodeToNameMap.put("688287.SH", "观想科技");
//        stockCodeToNameMap.put("688222.SH", "成都先导");
//        stockCodeToNameMap.put("688800.SH", "瑞可达");
//        stockCodeToNameMap.put("688058.SH", "宝兰德");
//        stockCodeToNameMap.put("688283.SH", "赛赫智能");
//        stockCodeToNameMap.put("688070.SH", "纵横股份");
//        stockCodeToNameMap.put("688125.SH", "安路科技");
//        stockCodeToNameMap.put("688701.SH", "光庭信息");
//        stockCodeToNameMap.put("688636.SH", "智明达");
//        stockCodeToNameMap.put("688319.SH", "欧林生物");
//        stockCodeToNameMap.put("688237.SH", "超卓航科");
//        stockCodeToNameMap.put("688083.SH", "中芯国际");
//        stockCodeToNameMap.put("688055.SH", "龙腾光电");
//        stockCodeToNameMap.put("688087.SH", "英科再生");
//        stockCodeToNameMap.put("688456.SH", "有研粉材");
//        stockCodeToNameMap.put("688137.SH", "近岸蛋白");
//        stockCodeToNameMap.put("688733.SH", "壹石通");
//        stockCodeToNameMap.put("688633.SH", "星球石墨");
//        stockCodeToNameMap.put("688081.SH", "兴图新科");
//        stockCodeToNameMap.put("688776.SH", "国光电气");
//        stockCodeToNameMap.put("688368.SH", "晶丰明源");
//        stockCodeToNameMap.put("688053.SH", "思科瑞");
//        stockCodeToNameMap.put("688687.SH", "凯因科技");
//        stockCodeToNameMap.put("688367.SH", "工大高科");
//        stockCodeToNameMap.put("688689.SH", "银河微电");
//        stockCodeToNameMap.put("688318.SH", "财富趋势");
//        stockCodeToNameMap.put("688589.SH", "力合微");
//        stockCodeToNameMap.put("688661.SH", "和林微纳");
//        stockCodeToNameMap.put("688375.SH", "国博电子");
//        stockCodeToNameMap.put("688279.SH", "峰岹科技");
//        stockCodeToNameMap.put("688625.SH", "呈和科技");
//        stockCodeToNameMap.put("688798.SH", "安旭生物");
//        stockCodeToNameMap.put("688138.SH", "清溢光电");
//        stockCodeToNameMap.put("688336.SH", "三生国健");
//        stockCodeToNameMap.put("688156.SH", "华强科技");
//        stockCodeToNameMap.put("688786.SH", "悦康药业");
//        stockCodeToNameMap.put("688696.SH", "极米科技");
//        stockCodeToNameMap.put("688079.SH", "美迪西");
//        stockCodeToNameMap.put("688521.SH", "芯原股份");
//        stockCodeToNameMap.put("688569.SH", "铁科轨道");
//        stockCodeToNameMap.put("688335.SH", "复洁环保");
//        stockCodeToNameMap.put("688211.SH", "中科星图");
//        stockCodeToNameMap.put("688315.SH", "诺禾致源");
//
//        // 继续添加更多的映射...
//    }

//    private static String[] getRealTimeData(final String id) throws Exception {
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
//    }


    public static String processStockData(String stockDatas) {
        String[] stockCodes = stockDatas.split(",");
        StringBuilder result = new StringBuilder();

        int count = 1; // 计数器
        ClassLoader classLoader = StockAssistantApplication.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("stock_code.csv"); // 文件路径相对于resources目录

        // 检查文件是否存在
        if (inputStream == null) {
            System.out.println("文件未找到");
            return "文件未找到";
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            String cvsSplitBy = ",";

            // 跳过CSV文件的表头
            br.readLine();

            // 逐行读取CSV文件，将股票代码和名称存入Map
            while ((line = br.readLine()) != null) {
                String[] stockData = line.split(cvsSplitBy);

                // 获取ts_code和name列的值
                String ts_code = stockData[0].trim();
                String stockName = stockData[2].trim();

                // 存入Map
                stockCodeToNameMap.put(ts_code, stockName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String code : stockCodes) {
            if (count >= 21) {
                break; // 如果已经输出了10个，退出循环
            }
            String name = stockCodeToNameMap.get(code.trim());
            if(name != null && name.contains("ST"))
                continue;
            //result.append("预测排名：").append(count + 1).append(" "); // 预测排名
            //result.append("股票代码：").append(code.trim().replace(".SH","").replace(".SZ","")).append("\n股票名称：");
            //result.append(count).append("、 ").append(name).append(" (").append(code.trim().replace(".SH","").replace(".SZ","")).append(")\n")

            if (name != null) {
                result.append(count).append(".").append(name).append(" ").append(code.trim().replace(".SH","").replace(".SZ","")).append("\n");
            } else {
                result.append("未知\n");
            }

            count++; // 增加计数器
        }

        if(count<20)
        {
            result.append("说明：只推荐预测收益为正的股票。\n");
        }
        return result.toString();
    }
}


