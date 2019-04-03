package com.sf.appTv.dao;

import com.sf.appTv.entity.Appgame;
import com.sf.appTv.entity.AppgameIn;
import com.sf.appTv.entity.AppgameOut;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppgameDao {

    Appgame queryById(Integer id);

    List<Appgame> queryAll();

    List<AppgameOut> selectAll();

    int insert(Appgame appgame);

    int update(Appgame appgame);
    int updateDownload(@Param("id") Integer id,@Param("downNum") Integer downNum);


}