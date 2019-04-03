package com.sf.appTv.controller;

import com.alibaba.fastjson.JSON;
import com.sf.appTv.ResponseUtils;
import com.sf.appTv.entity.Response;
import com.sf.appTv.entity.Vip;
import com.sf.appTv.service.impl.VipServiceImp;
import com.sf.appTv.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VipController {
    @Autowired
    private VipServiceImp vipService;
    //查看vip套餐
    @PostMapping("/vip")
    public Response<Vip> getById(String params){

        Integer id=JSON.parseObject(Utils.decryptDESK(params)).getInteger("id");
        return ResponseUtils.success(vipService.queryById(id));
    }
    @PostMapping ("getVipSelect")
    public Response<List<Vip>> getAllList(){
        return  ResponseUtils.success(vipService.queryAllVip());
    }
}
