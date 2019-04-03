package com.sf.appTv.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonParser;
import com.sf.appTv.ResponseUtils;
import com.sf.appTv.entity.AppUser;
import com.sf.appTv.entity.Response;
import com.sf.appTv.service.AppUserService;
import com.sf.appTv.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @PostMapping("/getUserInfo")
    public Response<AppUser> getById(String params) {//params 是key vlues传值 所以只能用字符串接收

        JSONObject o = JSON.parseObject(Utils.decryptDESK(params));
        Integer username = (Integer) o.get("uid");

        return ResponseUtils.success(appUserService.getById(username));
    }

    //    @PostMapping ("/getUserInfo")
//    public Response<AppUser> getById(String encryptString){
//        JSONObject o=JSON.parseObject(encryptString);
//        System.out.println(o.get("username"));
//
//
////        return ResponseUtils.success(appUserService.getById(id));
//        return null;
//    }
//查看用户套餐信息
    @PostMapping("/usernameApp")
    public Response<AppUser> getAppById(String params) {

        JSONObject o=  JSON.parseObject(Utils.decryptDESK(params));
      Integer username=  o.getInteger("username");
        return ResponseUtils.success(appUserService.getById(username));

    }

    @PostMapping("/getUserInfo1")
    public Response<AppUser> getById1(String params) {//params 是key vlues传值 所以只能用字符串接收
//    JSONObject o=JSON.parseObject(params);
        AppUser appUser = JSON.parseObject(Utils.decryptDESK(params), AppUser.class);
        Integer username = appUser.getUsername();
        return ResponseUtils.success(appUserService.getById(username));
    }


    @PostMapping("openVip")
    //更改套餐
    public Response oppenVip(String params) {

        AppUser appUser = JSON.parseObject(Utils.decryptDESK(params), AppUser.class);
        return ResponseUtils.success(appUserService.updateById(appUser));
    }

    @PostMapping("UserInfo")
    public Response<List<AppUser>> getUserInfo() {
        return ResponseUtils.success(appUserService.queryAll());
    }
}
