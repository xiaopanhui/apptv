package com.sf.appTv.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Appimg implements Serializable {
    private static final long serialVersionUID = 798556018555497295L;
    //主键ID
    private Integer id;
    //游戏id
    private Integer appid;
    //图片url
    private String imgurl;


}