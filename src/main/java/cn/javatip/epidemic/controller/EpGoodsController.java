package cn.javatip.epidemic.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.javatip.epidemic.entity.EpGoods;
import cn.javatip.epidemic.service.EpService;
import cn.javatip.epidemic.vo.EpGoodsVo;
import cn.javatip.sys.common.DataGridView;
import cn.javatip.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author 李寻欢
 * @Description
 * @Date 2021-10-26 21:09
 */
@RestController
@RequestMapping("/epgoods")
public class EpGoodsController {

    @Autowired
    private EpService epService;

    /**
     * 查询防疫物资列表
     * @param epGoodsVo
     * @return
     */
    @RequestMapping("epGoodsList")
    public DataGridView epGoodsList(EpGoodsVo epGoodsVo){
        //1.声明一个分页page对象
        IPage<EpGoods> page = new Page(epGoodsVo.getPage(),epGoodsVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<EpGoods> queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(epGoodsVo.getName()),"name",epGoodsVo.getName());
        queryWrapper.orderByDesc("id");
        epService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加防疫物资
     * @param epGoodsVo
     * @return
     */
    @RequestMapping("add")
    public ResultObj addCustomer(EpGoodsVo epGoodsVo){
        try {
            epService.save(epGoodsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改防疫物资
     * @param epGoodsVo
     * @return
     */
    @RequestMapping("update")
    public ResultObj updateCustomer(EpGoodsVo epGoodsVo){
        try {
            epService.updateById(epGoodsVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除防疫物资
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

    //导入Excel
    @RequestMapping("/import")
    @ResponseBody
    public ResultObj importexcel(@RequestParam(value = "file") MultipartFile file) throws IOException{
        EasyExcel.read(file.getInputStream(), EpGoods.class, new ExcelListener(epService)).sheet().doRead();
        return ResultObj.DELETE_SUCCESS;
    }

    /**
     * 导出
     * @param response
     * @throws IOException
     */
    @RequestMapping("export")
    public void download(HttpServletResponse response) throws Exception {
        List<EpGoods> list = epService.list();
        download(response,EpGoods.class,list);
    }

    public static void download(HttpServletResponse response, Class t, List list) throws IOException, IllegalAccessException,InstantiationException {
        response.setContentType("application/vnd.ms-excel");// 设置文本内省
        response.setCharacterEncoding("utf-8");// 设置字符编码
        response.setHeader("Content-disposition", "attachment;filename=防疫物资"); // 设置响应头
        EasyExcel.write(response.getOutputStream(), t).sheet("防疫物资清单").doWrite(list); //用io流来写入数据
    }
}
