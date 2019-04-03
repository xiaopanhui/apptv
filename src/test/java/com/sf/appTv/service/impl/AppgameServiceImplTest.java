package com.sf.appTv.service.impl;

import com.sf.appTv.entity.Appgame;
import com.sf.appTv.service.AppgameService;
import com.sf.appTv.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppgameServiceImplTest {
    @Autowired
    private AppgameServiceImpl appgameService;

    @Autowired
    private FileUtils fileUtils;

    @Test
    public void down() {

        System.out.println(appgameService.updatedown(1,"http://192.168.131.108:4000/upload/apk/com.example.tv01"));
    }
// @Test
//    public void uploadFile() {
//        // FilePart：用来上传文件的类,file即要上传的文件
//        FilePart filePart = null;
//        File file = fileUtils.getNewFile(1, 2);
//        filePart.transferTo(file).block();
//        System.out.println(file);
//    }
}