package cn.javatip.epidemic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.javatip.epidemic.entity.EpConfirm;
import cn.javatip.epidemic.service.EpConfirmService;
import cn.javatip.epidemic.vo.EpConfirmVo;
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
 * @Date 2021-10-27 11:24
 */
@RestController
@RequestMapping("confirm")
public class EpConfirmController {

    @Autowired
    private EpConfirmService epService;

    /**
     * 查询
     * @param epConfirmVo
     * @return
     */
    @RequestMapping("list")
    public DataGridView epGoodsList(EpConfirmVo epConfirmVo){
        //1.声明一个分页page对象
        IPage<EpConfirm> page = new Page(epConfirmVo.getPage(),epConfirmVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<EpConfirm> queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(epConfirmVo.getName()),"name",epConfirmVo.getName());
        queryWrapper.orderByDesc("id");
        epService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加
     * @param epConfirmVo
     * @return
     */
    @RequestMapping("add")
    public ResultObj addCustomer(EpConfirmVo epConfirmVo){
        try {
            epConfirmVo.setConfirmDate(new Date());
            epService.save(epConfirmVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改防疫物资
     * @param epConfirmVo
     * @return
     */
    @RequestMapping("update")
    public ResultObj updateCustomer(EpConfirmVo epConfirmVo){
        try {
            epService.updateById(epConfirmVo);
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
