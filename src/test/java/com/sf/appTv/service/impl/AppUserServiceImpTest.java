package com.sf.appTv.service.impl;

import com.sf.appTv.dao.AppUserDao;
import com.sf.appTv.entity.AppUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppUserServiceImpTest {
    @Autowired
    private  AppUserServiceImp appUserServiceImp;
    @Resource
    private AppUserDao appUserDao;
    @Test
    public void updateById() {
//        AppUser user=appUserDao.queryById(1001);
//        System.out.println();
//
//        user.setSelectId(1);
//        appUserServiceImp.updateById(user);

    }
}