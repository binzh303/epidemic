package cn.javatip.epidemic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author 李寻欢
 * @Description 核算监测结果录入
 * @Date 2021-10-27 9:25
 */
@Data
public class EpAdjust {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String tel;
    private String idcard;
    private String address;
    private String status;
    private Date jianceDate;
}
