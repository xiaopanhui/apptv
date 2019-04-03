package com.sf.appTv.service;

import com.sf.appTv.entity.AppUser;

import java.util.List;

public interface AppUserService {
    AppUser getById(Integer username);

    AppUser updateById(AppUser appUser);
    List<AppUser> queryAll();
}
