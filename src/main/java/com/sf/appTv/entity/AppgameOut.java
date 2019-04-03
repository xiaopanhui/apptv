package com.sf.appTv.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
public class AppgameOut implements Serializable {
    private static final long serialVersionUID = 609817314978900469L;
    private Integer id;
    //游戏名
    private String name;
    //游戏图标 横屏 height:768px  width:1280px
    private String coverHorizontalUrl;
    //游戏图标 竖屏 height:1280px  width:768px
    private String coverVerticalUrl;
    //游戏状态 1:推荐  2:免费  3:vip专享    (推荐最大8个,且为横屏图标)
    private Integer gameState;
    //游戏描述
    private String des;
    //游戏下载次数
    private Integer downNum;
    //游戏大小
    private String fileSize;
    //标签
    private String tag;
    //version_name
    private String versionName;
    //版本号(int)
    private Integer versionCode;
    //游戏包名
    private String applicationId;
    //游戏启动页面
    private String startPackage;
    //下载地址
    private String dwonloadUrl;
    private String icon;
    //详情轮播图
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Appimg> detailImages;
    private  String[]detailImgs;
}
