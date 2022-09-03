package cn.javatip.sys.service.impl;

import cn.javatip.sys.service.INoticeService;
import cn.javatip.sys.entity.Notice;
import cn.javatip.sys.mapper.NoticeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}
