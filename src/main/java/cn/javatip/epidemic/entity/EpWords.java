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
public class EpWords {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String type;
    private Integer reid;
    private String name;
    private String tel;
    private String content;
    private String rname;
    private String remark;
    private Date createdate;
    private Date redate;
}
