package cn.javatip.epidemic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.javatip.epidemic.entity.EpWords;
import cn.javatip.epidemic.service.EpWordsService;
import cn.javatip.epidemic.vo.EpWordsVo;
import cn.javatip.sys.common.DataGridView;
import cn.javatip.sys.common.ResultObj;
import cn.javatip.sys.common.WebUtils;
import cn.javatip.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author 李寻欢
 * @Description 留言回复管理
 * @Date 2021-10-27 11:24
 */
@RestController
@RequestMapping("words")
public class EpWordsController {

    @Autowired
    private EpWordsService epService;

    /**
     * 查询
     * @param epWordsVo
     * @return
     */
    @RequestMapping("list")
    public DataGridView epGoodsList(EpWordsVo epWordsVo){
        //1.声明一个分页page对象
        IPage<EpWords> page = new Page(epWordsVo.getPage(),epWordsVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<EpWords> queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(epWordsVo.getName()),"name",epWordsVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(epWordsVo.getRname()),"rname",epWordsVo.getRname());
        queryWrapper.orderByDesc("id");
        epService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }


    /**
     * 留言
     * @param epWordsVo
     * @return
     */
    @RequestMapping("add")
    public ResultObj addCustomer(EpWordsVo epWordsVo){
        try {
            epWordsVo.setCreatedate(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            epWordsVo.setName(user.getName());
            epService.save(epWordsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 回复
     * @param epWordsVo
     * @return
     */
    @RequestMapping("update")
    public ResultObj updateCustomer(EpWordsVo epWordsVo){
        try {
            EpWords byId = epService.getById(epWordsVo.getId());
            epWordsVo.setRedate(new Date());
            epWordsVo.setRemark(epWordsVo.getContent());
            epWordsVo.setContent(byId.getContent());
            User user = (User) WebUtils.getSession().getAttribute("user");
            epWordsVo.setRname(user.getName());
            epService.updateById(epWordsVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "delete")
    public ResultObj deleteCustomer(Integer id){
        try {
            epService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
