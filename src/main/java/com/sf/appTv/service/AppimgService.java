package com.sf.appTv.service;

import com.sf.appTv.entity.Appimg;
import java.util.List;

/**
 * 游戏图片(Appimg)表服务接口
 *
 * @author makejava
 * @since 2019-03-28 18:00:20
 */
public interface AppimgService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Appimg queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Appimg> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param appimg 实例对象
     * @return 实例对象
     */
    Appimg insert(Appimg appimg);

    /**
     * 修改数据
     *
     * @param appimg 实例对象
     * @return 实例对象
     */
    Appimg update(Appimg appimg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}