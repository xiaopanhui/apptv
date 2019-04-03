package com.sf.appTv.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sf.appTv.ResponseUtils;
import com.sf.appTv.entity.*;
import com.sf.appTv.service.AppgameService;
import com.sf.appTv.utils.ACEUtil;
import com.sf.appTv.utils.FileUtils;
import com.sf.appTv.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;


@RestController

public class AppgameController {

    @Resource
    private AppgameService appgameService;
    @Autowired
    private FileUtils fileUtils;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("selectOneGame/{id}")
    public Response<Appgame> selectOne(@PathVariable("id") Integer id) {
//        Integer id = (Integer) map.get("id");
        return ResponseUtils.success(appgameService.queryById(id));

    }


    @PostMapping("getGameList")
    public Response<List<AppgameOut>> getGameList2() {
        List<AppgameOut> list = appgameService.queryAll();
        for(int i=0;i<list.size();i++){
            AppgameOut ao = list.get(i);
            List<Appimg> imgs = ao.getDetailImages();
            if(imgs.size()>0){
                int size = imgs.size();
                String[] detailImages2 = new String[size];
                for(int n=0;n<size;n++){
                    Appimg ai = imgs.get(n);
                    detailImages2[n] = ai.getImgurl();
                }
                ao.setDetailImgs(detailImages2);
                ao.setDetailImages(null);

            }

        }



         return ResponseUtils.success(list);
    }


    //上传文件
    @PostMapping("upload")
    public Response uploadGame(@RequestPart("file") MultipartFile file, @RequestParam("file") MultipartFile img) throws Exception {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        //设置上传路径
        File upload = new File(path.getAbsolutePath(), "static/uploadsore/");
        File upImg = new File(path.getAbsolutePath(), "static/imgs/");
//        appgame.setDwonloadUrl(upload.toString());
        if (!upload.exists()) {//没有路径就新建
            upload.mkdirs();
        }
        if (!upImg.exists()) {//没有路径就新建
            upImg.mkdirs();
        }
        String uploadPath = upload + "\\" + file.getOriginalFilename();
        String imgPath = upImg + "\\" + img.getOriginalFilename();
        File upfile = new File(uploadPath);//创建新文件
        File upimg = new File(imgPath);//创建新文件

        try {
            file.transferTo(upfile);
            img.transferTo(upimg);
//            D:\data\appTv\static\"
//            return ResponseUtils.success(appgameService.addOneGame(appgame, file));
            return ResponseUtils.success("ok==========" + img.getOriginalFilename() + "========" + imgPath);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return ResponseUtils.upFile();
        }

    }

    @PostMapping("/addGame")
    public  Response<AppgameIn> addOneGame(@RequestBody @Validated AppgameIn appgameIn){
//        System.out.println("============="+appgameService.insert(appgameIn));
//        appgameIn.setDetailImages(null);
        return ResponseUtils.success(appgameService.insert(appgameIn));
    }
    @PostMapping("/test/upload")
    public Response uploadGameList(@RequestPart("file") MultipartFile file) throws IOException {
        //获取项目路径
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        //设置上传路径
        File upload = new File(path.getAbsolutePath(), "static/upload/img/");

        if (!upload.exists()) {//没有路径就新建
            upload.mkdirs();
        }
        String uploadPath = upload + "\\" + file.getOriginalFilename();

        File upfile = new File(uploadPath);//创建新文件
        try {//写入文件
            file.transferTo(upfile);
            InetAddress address = InetAddress.getLocalHost();
            String sIP = address.getHostAddress();
            System.out.println("服务端图片地址"+"http://"+sIP+":4000/upload/img/"+file.getOriginalFilename());

            return ResponseUtils.success("http://"+sIP+":4000/upload/img/"+file.getOriginalFilename());//返回Url
//            return ResponseUtils.success(upfile.getPath());//返回Url
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseUtils.success("error");
        }
    }
   //上传游戏包
   @PostMapping("/upload/apk")
   public Response uploadpa(@RequestPart("file") MultipartFile file) throws IOException {
       File path = new File(ResourceUtils.getURL("classpath:").getPath());
       File upload = new File(path.getAbsolutePath(), "static/upload/apk/");
       if (!upload.exists()) {
           upload.mkdirs();
       }
       String uploadPath = upload + "\\" + file.getOriginalFilename();
       File upfile = new File(uploadPath);
       try {//写入文件
           file.transferTo(upfile);
           InetAddress address = InetAddress.getLocalHost();
           String sIP = address.getHostAddress();
         long size= file.getSize();
           System.out.println("upload==================" + upload+"size+========"+size);
           return ResponseUtils.success("http://"+sIP+":4000/upload/apk/"+file.getOriginalFilename());//返回Url
//            return ResponseUtils.success(upfile.getPath());//返回Url
       } catch (IOException e) {
           e.printStackTrace();
           return ResponseUtils.success("error");
       }
   }
    //测试图片上传 ok
    @PostMapping("imgs")
    public Response uploadPicture(@RequestParam(value = "file", required = false) MultipartFile file) throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("").getPath());
        //设置上传路径
        File upload = new File(path.getAbsolutePath(), "static/upImg/");

        if (!upload.exists()) {
            upload.mkdirs();
        }
        String uploadPath = upload + "\\" + file.getOriginalFilename();
//        String uploadPath1 = upload + "\\" + file1.getOriginalFilename();
        File upfile = new File(uploadPath);//创建新文件
//        File upfile1 = new File(uploadPath1);//创建新文件
        try {
            //写图片
            file.transferTo(upfile);
//            file1.transferTo(upfile1);
            return ResponseUtils.success("ok,filepath=====" + upfile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseUtils.success("error");
        }
    }
    @PostMapping("downlaod")
    public  Response download(String params)  {
        JSONObject o= JSON.parseObject(Utils.decryptDESK(params));
        Integer id = o.getInteger("id");
        String dwonloadUrl=o.getString("dwonloadUrl");
        return ResponseUtils.success(appgameService.updatedown(id, dwonloadUrl));

    }


}





