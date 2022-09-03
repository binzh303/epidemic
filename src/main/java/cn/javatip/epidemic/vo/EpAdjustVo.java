package cn.javatip.epidemic.vo;

import cn.javatip.epidemic.entity.EpAdjust;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author 李寻欢
 * @Description
 * @Date 2021-10-27 9:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EpAdjustVo extends EpAdjust {
    private Integer page=1;
    private Integer limit=10;
}
