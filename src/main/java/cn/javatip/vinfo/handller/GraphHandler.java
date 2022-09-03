package cn.javatip.vinfo.handller;

import com.google.gson.Gson;
import cn.javatip.vinfo.bean.GraphAddBean;
import cn.javatip.vinfo.bean.GraphBean;
import cn.javatip.vinfo.bean.GraphColumnarBean;
import cn.javatip.vinfo.bean.GraphPieBean;
import cn.javatip.vinfo.util.HttpClientUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 获取图像信息网站的内容
 */
public class GraphHandler {


    public static String urlStr = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_other";

    public static String getData(){
        return HttpClientUtil.doGet(urlStr);
    }

    public static List<GraphBean> getGraphData() {
        return getGraphData(getData());
    }

    public static List<GraphBean> getGraphData(String str) {
        List<GraphBean> result = new ArrayList<>();

        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);

        ArrayList list = (ArrayList) subMap.get("chinaDayList");

        for (int i = 0; i < list.size(); i++) {
            Map tmp = (Map) list.get(i);

            String date = (String) tmp.get("date");
            double nowConfirm = (Double) tmp.get("nowConfirm");
            GraphBean graphBean = new GraphBean(date, (int) nowConfirm);
            result.add(graphBean);
        }

        return result;
    }


    public static List<GraphAddBean> getGraphAddData() {
        return getGraphAddData(getData());
    }

    public static List<GraphAddBean> getGraphAddData(String str) {

        List<GraphAddBean> result = new ArrayList<>();

        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);

        ArrayList list = (ArrayList) subMap.get("chinaDayAddList");

        for (int i = 0; i < list.size(); i++) {
            Map tmp = (Map) list.get(i);
            String date = (String) tmp.get("date");
            double addConfirm = (Double) tmp.get("confirm");
            double addSuspect = (Double) tmp.get("suspect");

            GraphAddBean graphAddBean = new GraphAddBean(date,
                    (int) addConfirm, (int) addSuspect);
            result.add(graphAddBean);
        }

        return result;
    }


//    public static String urlStrAll = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    public static String urlStrAll = "https://interface.sina.cn/news/wap/fymap2020_data.d.json";

    public static List<GraphColumnarBean> getGraphColumnarData() {

        List<GraphColumnarBean> result = new ArrayList<>();

        String respJson = HttpClientUtil.doGet(urlStrAll);

        Gson gson = new Gson();
        Map map = gson.fromJson(respJson, Map.class);
        // 此时增加了一层处理  而且data对应的数据格式是string
        String subStr = gson.toJson(map.get("data"));
        Map subMap = gson.fromJson(subStr, Map.class);

        ArrayList childrenList = (ArrayList) subMap.get("list");

        for (int i = 0; i < childrenList.size(); i++) {

            Map tmp = (Map) childrenList.get(i);
            String name = (String) tmp.get("name");
            double fromAbroad = Double.parseDouble(tmp.get("jwsrNum")+"");
            GraphColumnarBean bean = new GraphColumnarBean(name, (int)fromAbroad);
            result.add(bean);
        }

        return result;

    }

    public static List<GraphPieBean> getGraphPieData() {
        return getGraphPieData(getData());
    }

    public static List<GraphPieBean> getGraphPieData(String str) {

        List<GraphPieBean> result = new ArrayList<>();

        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        String subStr = (String) map.get("data");
        Map subMap = gson.fromJson(subStr, Map.class);

        Map dataMap = (Map) subMap.get("nowConfirmStatis");

        for (Object o : dataMap.keySet()) {
            String name = (String) o;
            switch (name) {
                case "gat":
                    name = "港澳台病例";
                    break;
                case "import":
                    name = "境外输入病例";
                    break;
                case "province":
                    name = "31省本土病例";
                    break;
            }

            double value = (Double) dataMap.get(o);
            name += ":" + (int) value + "例";

            GraphPieBean bean = new GraphPieBean(name, (int) value);
            result.add(bean);
        }

        return result;
    }


    public static void main(String[] args) {
        getGraphData();

        List<GraphColumnarBean> list = getGraphColumnarData();
        Collections.sort(list);
        System.out.println();
    }
}
