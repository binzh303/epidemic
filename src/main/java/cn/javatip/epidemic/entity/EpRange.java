package cn.javatip.epidemic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Author 李寻欢
 * @Description
 * @Date 2021-10-27 17:16
 */
@Data
public class EpRange {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String tel;
    private String idcard;
    private String address;
    private String rrange;
    private String tujing;
    private Date createDate;
}
