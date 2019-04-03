package com.sf.appTv.service.impl;

import com.sf.appTv.dao.VipDao;
import com.sf.appTv.entity.Vip;
import com.sf.appTv.exception.NoFondException;
import com.sf.appTv.service.VipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VipServiceImp  implements VipService {
    @Resource
    private VipDao vipDao;
    @Override
    public Vip queryById(Integer id) {
        if (vipDao.queryById(id)==null){
            throw  new NoFondException();
        }
        return vipDao.queryById(id);
    }
    public List<Vip> queryAllVip(){
        return vipDao.queryAll();
    }
}
