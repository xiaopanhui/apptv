package com.sf.appTv.service;

import com.sf.appTv.entity.Appgame;
import com.sf.appTv.entity.AppgameIn;
import com.sf.appTv.entity.AppgameOut;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface AppgameService {

    Appgame queryById(Integer id);

    List<AppgameOut> queryAll();

    AppgameIn insert(AppgameIn appgameIn);
    Appgame update(Integer id,String dwonloadUrl);
     Appgame updatedown(Integer id,String dwonloadUrl);




}