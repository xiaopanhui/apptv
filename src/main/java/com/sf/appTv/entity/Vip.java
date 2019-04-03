package com.sf.appTv.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Vip implements Serializable {
    private static final long serialVersionUID = 379928458270481570L;
    //id
    private Integer selectId;
    //描述
    private String des;


}