package cn.javatip.epidemic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.javatip.epidemic.entity.EpVaccin;
import cn.javatip.epidemic.service.EpVaccinService;
import cn.javatip.epidemic.vo.EpVaccinVo;
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
 * @Description 疫苗接种预约管理
 * @Date 2021-10-27 11:24
 */
@RestController
@RequestMapping("vaccin")
public class EpVaccinController {

    @Autowired
    private EpVaccinService epService;

    /**
     * 查询
     * @param epVaccinVo
     * @return
     */
    @RequestMapping("list")
    public DataGridView epGoodsList(EpVaccinVo epVaccinVo){
        //1.声明一个分页page对象
        IPage<EpVaccin> page = new Page(epVaccinVo.getPage(),epVaccinVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<EpVaccin> queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(epVaccinVo.getUsername()),"username",epVaccinVo.getUsername());
        queryWrapper.like(StringUtils.isNotBlank(epVaccinVo.getStatus()),"status",epVaccinVo.getStatus());
        queryWrapper.orderByDesc("id");
        epService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加
     * @param epVaccinVo
     * @return
     */
    @RequestMapping("add")
    public ResultObj addCustomer(EpVaccinVo epVaccinVo){
        try {
            User user = (User) WebUtils.getSession().getAttribute("user");
            epVaccinVo.setUsername(user.getName());
            epVaccinVo.setStatus("已预约");
            epService.save(epVaccinVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 接种
     * @param epVaccinVo
     * @return
     */
    @RequestMapping("update")
    public ResultObj updateCustomer(EpVaccinVo epVaccinVo){
        try {
            epVaccinVo.setJiezhongDate(new Date());
            epVaccinVo.setStatus("已接种");
            epService.updateById(epVaccinVo);
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
