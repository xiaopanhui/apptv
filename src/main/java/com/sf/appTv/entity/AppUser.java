package com.sf.appTv.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

@Data
public class AppUser implements Serializable {
    //用户名
    private Integer username;
    //vip状态
    private Integer vipState;
    //头像url
    private String avatar;
    //vip到期时间
    private Date expireDate;
    // 套餐选择
    private Integer selectId;


}