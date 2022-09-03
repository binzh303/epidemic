package cn.javatip.epidemic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.javatip.epidemic.entity.EpAdjust;
import cn.javatip.epidemic.service.EpAdjustService;
import cn.javatip.epidemic.vo.EpAdjustVo;
import cn.javatip.sys.common.DataGridView;
import cn.javatip.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author 李寻欢
 * @Description
 * @Date 2021-10-27 9:28
 */
@RestController
@RequestMapping("epadjust")
public class EpAdjustController {

    @Autowired
    private EpAdjustService epService;

    /**
     * 查询
     * @param epAdjustVo
     * @return
     */
    @RequestMapping("list")
    public DataGridView epGoodsList(EpAdjustVo epAdjustVo){
        //1.声明一个分页page对象
        IPage<EpAdjust> page = new Page(epAdjustVo.getPage(),epAdjustVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<EpAdjust> queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(epAdjustVo.getName()),"name",epAdjustVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(epAdjustVo.getStatus()),"status",epAdjustVo.getStatus());
        queryWrapper.orderByDesc("id");
        epService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加
     * @param epAdjustVo
     * @return
     */
    @RequestMapping("add")
    public ResultObj addCustomer(EpAdjustVo epAdjustVo){
        try {
            epAdjustVo.setJianceDate(new Date());
            epService.save(epAdjustVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改防疫物资
     * @param epAdjustVo
     * @return
     */
    @RequestMapping("update")
    public ResultObj updateCustomer(EpAdjustVo epAdjustVo){
        try {
            epService.updateById(epAdjustVo);
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
