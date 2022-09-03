package cn.javatip.vinfo.service;

import cn.javatip.vinfo.mapper.DataMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javatip.vinfo.bean.DataBean;
import org.springframework.stereotype.Service;


@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, DataBean> implements DataService {
}
