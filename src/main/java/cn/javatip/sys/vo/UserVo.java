package cn.javatip.sys.vo;

import cn.javatip.sys.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 李寻欢
 * @Date: 2021/12/2 8:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends User {

    private Integer page=1;
    private Integer limit=10;

    /**
     * 验证码
     */
    private String code;
}
