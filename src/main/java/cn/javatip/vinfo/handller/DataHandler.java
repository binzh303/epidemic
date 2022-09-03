package cn.javatip.vinfo.handller;

import cn.javatip.vinfo.service.DataService;
import com.google.gson.Gson;
import cn.javatip.vinfo.bean.DataBean;
import cn.javatip.vinfo.util.HttpURLConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 使用HttpURLConnection实时的从网站获取最新数据内容
 */

@Component
public class DataHandler {

    @Autowired
    private DataService dataService;

//    public static String urlStr = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    public static String urlStr = "https://interface.sina.cn/news/wap/fymap2020_data.d.json";

    public static void main(String[] args) throws Exception {
        getData();
    }


    public void saveData() {
        List<DataBean> dataBeans = getData();
        // 先将数据清空  然后存储数据
        dataService.remove(null);
        dataService.saveBatch(dataBeans);

    }

    // 配置定时执行的注解  支持cron表达式
    @Scheduled(cron = "0 0/1 * * * ?")
    public void updateData() {
        System.out.println("更新数据");
        saveData();
    }

//    public static List<DataBean> getData() {
//
//        /**
//         * 分析json字符串对数据进行筛选和提取
//         */
//        // 实时获取数据
//        String respJson = HttpURLConnectionUtil.doGet(urlStr);
//        respJson = respJson.split("雅安")[0];
//        respJson = respJson.substring(0,respJson.length()-13)+'"'+"}";
//        Gson gson = new Gson();
//        Map map = gson.fromJson(respJson, Map.class);
//
//        // 此时增加了一层处理  而且data对应的数据格式是string
//        String subStr = (String) map.get("data")+"]}]}]}";
//        Map subMap = gson.fromJson(subStr, Map.class);
//
////        System.out.println(map);
//
//        ArrayList areaList = (ArrayList) subMap.get("areaTree");
//        Map dataMap = (Map) areaList.get(0);
//        ArrayList childrenList = (ArrayList) dataMap.get("children");
//
//        // 遍历然后转化
//        List<DataBean> result = new ArrayList<>();
//
//        for (int i = 0; i < childrenList.size(); i++) {
//            Map tmp = (Map) childrenList.get(i);
//            String name = (String) tmp.get("name");
//
//            Map totalMap = (Map) tmp.get("total");
//            double nowConfirm = (Double) totalMap.get("nowConfirm");
//            double confirm = (Double) totalMap.get("confirm");
//            double heal = (Double) totalMap.get("heal");
//            double dead = (Double) totalMap.get("dead");
//
//            DataBean dataBean = new DataBean(name, (int) nowConfirm, (int) confirm,
//                    (int) heal, (int) dead);
//
//            result.add(dataBean);
//        }
//
//        return result;
//    }

    public static List<DataBean> getData() {

        /**
         * 分析json字符串对数据进行筛选和提取
         */
        // 实时获取数据
        String respJson = HttpURLConnectionUtil.doGet(urlStr);
        Gson gson = new Gson();
        Map map = gson.fromJson(respJson, Map.class);

        // 此时增加了一层处理  而且data对应的数据格式是string
        String subStr = gson.toJson(map.get("data"));
        Map subMap = gson.fromJson(subStr, Map.class);

        ArrayList childrenList = (ArrayList) subMap.get("list");

        // 遍历然后转化
        List<DataBean> result = new ArrayList<>();

        for (int i = 0; i < childrenList.size(); i++) {
            Map tmp = (Map) childrenList.get(i);
            String name = (String) tmp.get("name");
            int nowConfirm = Integer.parseInt(tmp.get("econNum")+"");
            int confirm =  Integer.parseInt(tmp.get("value")+"");
            int heal =  Integer.parseInt(tmp.get("cureNum")+"");
            int dead =  Integer.parseInt(tmp.get("deathNum")+"");

            DataBean dataBean = new DataBean(name,  nowConfirm,  confirm,
                     heal,  dead);

            result.add(dataBean);
        }

        return result;
    }
}