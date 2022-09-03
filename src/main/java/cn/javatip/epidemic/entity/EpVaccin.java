package cn.javatip.epidemic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author 李寻欢
 * @Description
 * @Date 2021-10-27 17:16
 */
@Data
public class EpVaccin {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer userid;
    private String username;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date yuyueDate;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jiezhongDate;
    private String gebo;
    private String yimiaoProduce;
}
