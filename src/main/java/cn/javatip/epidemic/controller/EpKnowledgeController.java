package cn.javatip.epidemic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.javatip.epidemic.service.EpKnowledgeService;
import cn.javatip.epidemic.vo.EpKnowledge;
import cn.javatip.epidemic.vo.EpKnowledgeVo;
import cn.javatip.sys.common.DataGridView;
import cn.javatip.sys.common.ResultObj;
import cn.javatip.sys.common.WebUtils;
import cn.javatip.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * <p>
 * InnoDB free: 9216 kB 防疫知识管理
 * </p>
 *
 * @author 李寻欢
 * @since 2021-11-25
 */
@RestController
@RequestMapping("knowledge")
public class EpKnowledgeController {

    @Autowired
    private EpKnowledgeService noticeService;

    /**
     * 公告的查询
     * @param epKnowledgeVo
     * @return
     */
    @RequestMapping("loadAllNotice")
    public DataGridView loadAllNotice(EpKnowledgeVo epKnowledgeVo){
        IPage<EpKnowledge> page = new Page(epKnowledgeVo.getPage(),epKnowledgeVo.getLimit());
        QueryWrapper<EpKnowledge> queryWrapper = new QueryWrapper();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(epKnowledgeVo.getTitle()),"title",epKnowledgeVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(epKnowledgeVo.getOpername()),"opername",epKnowledgeVo.getOpername());
        //公告创建时间应该大于搜索开始时间小于搜索结束时间
        queryWrapper.ge(epKnowledgeVo.getStartTime()!=null,"createtime",epKnowledgeVo.getStartTime());
        queryWrapper.le(epKnowledgeVo.getEndTime()!=null,"createtime",epKnowledgeVo.getEndTime());
        //根据公告创建时间进行排序
        queryWrapper.orderByDesc("createtime");
        noticeService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 根据公告ID查询一条公告
     * @param id    公告ID
     * @return
     */
    @RequestMapping("loadNoticeById")
    public DataGridView loadNoticeById(Integer id){
        EpKnowledge notice = noticeService.getById(id);
        return new DataGridView(notice);
    }

    /**
     * 添加公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("addNotice")
    public ResultObj addNotice(EpKnowledgeVo noticeVo){
        try {
            noticeVo.setCreatetime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            noticeVo.setOpername(user.getName());
            noticeService.save(noticeVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("updateNotice")
    public ResultObj updateNotice(EpKnowledgeVo noticeVo){
        try {
            noticeService.updateById(noticeVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("deleteNotice")
    public ResultObj deleteNotice(EpKnowledgeVo noticeVo){
        try {
            noticeService.removeById(noticeVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("batchDeleteNotice")
    public ResultObj batchDeleteNotice(EpKnowledgeVo noticeVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : noticeVo.getIds()) {
                idList.add(id);
            }
            noticeService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

