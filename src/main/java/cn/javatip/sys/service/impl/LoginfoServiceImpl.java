package cn.javatip.sys.service.impl;

import cn.javatip.sys.entity.Loginfo;
import cn.javatip.sys.mapper.LoginfoMapper;
import cn.javatip.sys.service.ILoginfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * InnoDB free: 9216 kB 服务实现类
 * </p>
 *
 * @author 李寻欢
 * @since 2021-11-23
 */
@Service
@Transactional
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements ILoginfoService {

}
