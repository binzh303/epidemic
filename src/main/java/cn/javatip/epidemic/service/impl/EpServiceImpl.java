package cn.javatip.epidemic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javatip.epidemic.entity.EpGoods;
import cn.javatip.epidemic.mapper.EpGoodsMapper;
import cn.javatip.epidemic.service.EpService;
import org.springframework.stereotype.Service;

/**
 * @Author 李寻欢
 * @Description
 * @Date 2021-10-26 21:26
 */
@Service
public class EpServiceImpl extends ServiceImpl<EpGoodsMapper, EpGoods> implements EpService  {

}
