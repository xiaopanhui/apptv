package com.sf.appTv.service.impl;

import com.sf.appTv.dao.AppUserDao;
import com.sf.appTv.entity.AppUser;
import com.sf.appTv.exception.NoFondException;
import com.sf.appTv.exception.NotUserException;
import com.sf.appTv.service.AppUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AppUserServiceImp implements AppUserService {
    @Resource
    private AppUserDao appUserDao;

    @Override
    public AppUser getById(Integer username) {
       if( appUserDao.queryById(username)==null) throw  new NoFondException();
        return appUserDao.queryById(username);
    }

    @Override
    public AppUser updateById(AppUser appUser)  {

        AppUser appUser1 = appUserDao.queryById(appUser.getUsername());
        if(appUser1==null){
            throw new NotUserException();
        }
        Calendar cal = Calendar.getInstance();

        Date date =   appUser1.getExpireDate();
        cal.setTime( date);
        if (appUser.getSelectId() == 1) {
            //设置过期时间
            cal.add(Calendar.MONTH, appUser.getSelectId());//增加一个月
            date = cal.getTime();


        }
        if (appUser.getSelectId() == 2) {
            //设置过期时间
            cal.add(Calendar.MONTH, appUser.getSelectId());
            date = cal.getTime();

        }
        if (appUser.getSelectId() ==3) {
            //设置过期时间
            cal.add(Calendar.MONTH, appUser.getSelectId());
            date = cal.getTime();

        }
        appUser1.setExpireDate(date);
        //设置过期时间
        if (appUser1.getExpireDate().after( new Date())) {
            appUser1.setVipState(1);
        } else {
            appUser1.setVipState(0);

        }
        appUserDao.update(appUser);
        return appUserDao.queryById(appUser.getUsername()) ;
    }

    @Override
    public List<AppUser> queryAll() {
        return appUserDao.queryAll();
    }
}
