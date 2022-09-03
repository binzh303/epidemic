package cn.javatip.epidemic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javatip.epidemic.entity.EpWords;
import cn.javatip.epidemic.mapper.EpWordsMapper;
import cn.javatip.epidemic.service.EpWordsService;
import org.springframework.stereotype.Service;

/**
 * @Author 李寻欢
 * @Description
 * @Date 2021-10-27 11:24
 */
@Service
public class EpWordsServiceImpl extends ServiceImpl<EpWordsMapper, EpWords> implements EpWordsService {
}
