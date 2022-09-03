package cn.javatip.epidemic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.javatip.epidemic.entity.EpRange;
import cn.javatip.epidemic.service.EpRangeService;
import cn.javatip.epidemic.vo.EpRangeVo;
import cn.javatip.sys.common.DataGridView;
import cn.javatip.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author 李寻欢
 * @Description 中高风险区
 * @Date 2021-10-27 11:24
 */
@RestController
@RequestMapping("range")
public class EpRangeController {

    @Autowired
    private EpRangeService epService;

    /**
     * 查询
     * @param epRangeVo
     * @return
     */
    @RequestMapping("list")
    public DataGridView epGoodsList(EpRangeVo epRangeVo){
        //1.声明一个分页page对象
        IPage<EpRange> page = new Page(epRangeVo.getPage(),epRangeVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<EpRange> queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(epRangeVo.getName()),"name",epRangeVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(epRangeVo.getRrange()),"rrange",epRangeVo.getRrange());
        queryWrapper.orderByDesc("id");
        epService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加
     * @param epRangeVo
     * @return
     */
    @RequestMapping("add")
    public ResultObj addCustomer(EpRangeVo epRangeVo){
        try {
            epRangeVo.setCreateDate(new Date());
            epService.save(epRangeVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改防疫物资
     * @param epRangeVo
     * @return
     */
    @RequestMapping("update")
    public ResultObj updateCustomer(EpRangeVo epRangeVo){
        try {
            epService.updateById(epRangeVo);
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
