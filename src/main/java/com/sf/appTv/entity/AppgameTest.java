package com.sf.appTv.entity;

import java.io.Serializable;

/**
 * 游戏详情(AppgameTest)实体类
 *
 * @author makejava
 * @since 2019-03-29 11:58:17
 */
public class AppgameTest implements Serializable {
    private static final long serialVersionUID = -74288531785859071L;
    //id
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
    //0正常，1删除
    private Integer isdel;
    
    private String detailimages;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverHorizontalUrl() {
        return coverHorizontalUrl;
    }

    public void setCoverHorizontalUrl(String coverHorizontalUrl) {
        this.coverHorizontalUrl = coverHorizontalUrl;
    }

    public String getCoverVerticalUrl() {
        return coverVerticalUrl;
    }

    public void setCoverVerticalUrl(String coverVerticalUrl) {
        this.coverVerticalUrl = coverVerticalUrl;
    }

    public Integer getGameState() {
        return gameState;
    }

    public void setGameState(Integer gameState) {
        this.gameState = gameState;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getDownNum() {
        return downNum;
    }

    public void setDownNum(Integer downNum) {
        this.downNum = downNum;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getStartPackage() {
        return startPackage;
    }

    public void setStartPackage(String startPackage) {
        this.startPackage = startPackage;
    }

    public String getDwonloadUrl() {
        return dwonloadUrl;
    }

    public void setDwonloadUrl(String dwonloadUrl) {
        this.dwonloadUrl = dwonloadUrl;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getDetailimages() {
        return detailimages;
    }

    public void setDetailimages(String detailimages) {
        this.detailimages = detailimages;
    }

}