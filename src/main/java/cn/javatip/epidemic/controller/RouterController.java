package cn.javatip.epidemic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 李寻欢
 * @Description
 * @Date 2021-10-26 21:38
 */
@Controller
@RequestMapping("ep")
public class RouterController {

    /**
     * 跳转到防疫物资管理页面
     * @return
     */
    @RequestMapping("toGoodsManager")
    public String toCustomerManager(){
        return "epidemic/epgoods/epGoodsManager";
    }

    /**
     * 跳转到核算监测页面
     * @return
     */
    @RequestMapping("toAdjustManager")
    public String toAdjustManager() {
        return "epidemic/epadjust/epAdjustManager";
    }

    /**
     * 跳转到确诊管理页面
     * @return
     */
    @RequestMapping("toConfirmManager")
    public String toConfirmManager() {
        return "epidemic/epconfirm/epConfirmManager";
    }

    /**
     * 跳转中高风险区页面
     * @return
     */
    @RequestMapping("toRangeManager")
    public String toRangeManager() {
        return "epidemic/eprange/epRangeManager";
    }

    /**
     * 跳转疫苗接种
     * @return
     */
    @RequestMapping("toVaccinManager")
    public String toVaccinManager() {
        return "epidemic/epvaccin/epVaccinManager";
    }

    /**
     * 跳转留言回复
     * @return
     */
    @RequestMapping("toWordsManager")
    public String toWordsManager() {
        return "epidemic/epwords/epWordsManager";
    }

    /**
     * 跳转防疫知识
     * @return
     */
    @RequestMapping("toYiManager")
    public String toYiManager() {
        return "epidemic/epknowledge/epKnowledgeManager";
    }
}
