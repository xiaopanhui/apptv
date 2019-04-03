package com.sf.appTv.dao;

import com.sf.appTv.entity.Appimg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 游戏图片(Appimg)表数据库访问层
 *
 * @author makejava
 * @since 2019-03-28 18:00:20
 */
@Mapper
public interface AppimgDao {

    Appimg queryById(Integer id);



    List<Appimg> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);



    List<Appimg> queryAll();


    int insert(Appimg appimg);

    int insertList(@Param("appid") Integer appid,@Param("urls") String[] urls);

    /**
     * 修改数据
     *
     * @param appimg 实例对象
     * @return 影响行数
     */
    int update(Appimg appimg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}