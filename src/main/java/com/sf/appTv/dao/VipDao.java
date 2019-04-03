package com.sf.appTv.dao;

import com.sf.appTv.entity.Vip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface VipDao {


    Vip queryById(Integer selectId);


    List<Vip> queryAll();


}