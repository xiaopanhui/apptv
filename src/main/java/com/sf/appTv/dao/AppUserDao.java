package com.sf.appTv.dao;

import com.sf.appTv.entity.AppUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AppUserDao {

    AppUser queryById(Integer username);
    Integer update(AppUser appUser);
    List<AppUser> queryAll();


}