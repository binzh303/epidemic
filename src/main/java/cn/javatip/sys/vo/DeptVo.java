package cn.javatip.sys.vo;

import cn.javatip.sys.entity.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 李寻欢
 * @Date: 2021/11/26 11:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends Dept {

    private Integer page=1;
    private Integer limit=10;

}
