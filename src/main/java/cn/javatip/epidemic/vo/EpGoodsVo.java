package cn.javatip.epidemic.vo;

import cn.javatip.epidemic.entity.EpGoods;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author 李寻欢
 * @Description
 * @Date 2021-10-26 21:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EpGoodsVo extends EpGoods {
    private Integer page=1;
    private Integer limit=10;
}
