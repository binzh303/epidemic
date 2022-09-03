package cn.javatip.epidemic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;

@Data
public class EpConfirm {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String tel;
    private String idcard;
    private String address;
    private String type;
    private String fenxiang;
    private String tujing;
    private Date confirmDate;
}
