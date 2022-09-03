package cn.javatip.epidemic.service.impl;

import cn.javatip.epidemic.service.EpKnowledgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javatip.epidemic.mapper.KnowledgeMapper;
import cn.javatip.epidemic.vo.EpKnowledge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * InnoDB free: 9216 kB 服务实现类
 * </p>
 *
 * @author 李寻欢
 * @since 2021-11-25
 */
@Service
@Transactional
public class EpKnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, EpKnowledge> implements EpKnowledgeService {

}
