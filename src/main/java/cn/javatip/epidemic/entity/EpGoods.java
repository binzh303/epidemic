package cn.javatip.epidemic.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author 李寻欢
 * @Description 防疫物资管理
 * @Date 2021-10-26 21:07
 */
@Data
public class EpGoods {

    @TableId(value = "id", type = IdType.AUTO)
    @ExcelProperty(value = "ID")
    private Integer id;
    @ExcelProperty(value = "型号")
    private String type;
    @ExcelProperty(value = "名称")
    private String name;
    @ExcelProperty(value = "数量")
    private Integer num;
    @ExcelProperty(value = "生产厂家")
    private String produce;
    @ExcelProperty(value = "厂家地址")
    private String address;
}
