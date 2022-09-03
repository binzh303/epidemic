package cn.javatip.epidemic.vo;

import cn.javatip.epidemic.entity.EpVaccin;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author 李寻欢
 * @Description
 * @Date 2021-10-27 11:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EpVaccinVo extends EpVaccin {
    private Integer page=1;
    private Integer limit=10;
}
