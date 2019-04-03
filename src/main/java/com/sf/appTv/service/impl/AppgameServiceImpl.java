package com.sf.appTv.service.impl;

import com.sf.appTv.ResponseUtils;
import com.sf.appTv.dao.AppimgDao;
import com.sf.appTv.entity.Appgame;
import com.sf.appTv.dao.AppgameDao;
import com.sf.appTv.entity.AppgameIn;
import com.sf.appTv.entity.AppgameOut;
import com.sf.appTv.entity.Appimg;
import com.sf.appTv.service.AppgameService;
import com.sf.appTv.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class AppgameServiceImpl implements AppgameService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private AppgameDao appgameDao;
    @Autowired
    private FileUtils fileUtils;

    @Resource
    private AppimgDao appimgDao;

    @Override
    public Appgame queryById(Integer id) {
        return this.appgameDao.queryById(id);
    }

    @Override
    public List<AppgameOut> queryAll() {
        return appgameDao.selectAll();
    }

    @Override
    @Transactional
    public AppgameIn insert(AppgameIn appgameIn) {


        Appgame appgame = new Appgame();
        appgame.setDownNum(0);
        BeanUtils.copyProperties(appgameIn, appgame);
      this.appgameDao.insert(appgame);//这个能返回主键不
        String[] url = appgameIn.getDetailImages().split(",");
        appimgDao.insertList(appgame.getId(), url);
        appgameIn.setUrls(url);
        appgameIn.setId(appgame.getId());
        return appgameIn;
    }


    public Appgame addOneGame(Appgame appgame) {
        Integer id = appgameDao.insert(appgame);
        return appgameDao.queryById(id);
    }

    @Override
    public Appgame update(Integer id,String dwonloadUrl) {
      Appgame appgame=  appgameDao.queryById(id);
        if( dwonloadUrl!=null){
            appgame.setDownNum(appgame.getDownNum()+1);
        }

        this.appgameDao.update(appgame);
        return this.queryById(id);
    }
    @Override
    public Appgame updatedown(Integer id,String dwonloadUrl) {
        Appgame localappgame=  appgameDao.queryById(id);
        Integer downNum=localappgame.getDownNum();
        if( dwonloadUrl.trim() .equals(localappgame.getDwonloadUrl().trim())){
         downNum=  downNum+1;
        }
        this.appgameDao.updateDownload(id,downNum);
        return this.queryById(id);
    }


}