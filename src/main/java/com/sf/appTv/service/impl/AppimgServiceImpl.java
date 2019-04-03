package com.sf.appTv.service.impl;

import com.sf.appTv.entity.Appimg;
import com.sf.appTv.dao.AppimgDao;
import com.sf.appTv.service.AppimgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 游戏图片(Appimg)表服务实现类
 *
 * @author makejava
 * @since 2019-03-28 18:00:20
 */
@Service("appimgService")
public class AppimgServiceImpl implements AppimgService {
    @Resource
    private AppimgDao appimgDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Appimg queryById(Integer id) {
        return this.appimgDao.queryById(id);
    }


    @Override
    public List<Appimg> queryAllByLimit(int offset, int limit) {
        return this.appimgDao.queryAllByLimit(offset, limit);
    }


    @Override
    public Appimg insert(Appimg appimg) {
        this.appimgDao.insert(appimg);
        return appimg;
    }


    @Override
    public Appimg update(Appimg appimg) {
        this.appimgDao.update(appimg);
        return this.queryById(appimg.getId());
    }


    @Override
    public boolean deleteById(Integer id) {
        return this.appimgDao.deleteById(id) > 0;
    }
}