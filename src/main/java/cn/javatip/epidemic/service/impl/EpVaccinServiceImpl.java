package cn.javatip.epidemic.service.impl;

import cn.javatip.epidemic.service.EpVaccinService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javatip.epidemic.entity.EpVaccin;
import cn.javatip.epidemic.mapper.EpVaccinMapper;
import org.springframework.stereotype.Service;

/**
 * @Author 李寻欢
 * @Description
 * @Date 2021-10-27 11:24
 */
@Service
public class EpVaccinServiceImpl extends ServiceImpl<EpVaccinMapper, EpVaccin> implements EpVaccinService {
}
