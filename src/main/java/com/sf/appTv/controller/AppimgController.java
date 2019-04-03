package com.sf.appTv.controller;

import com.sf.appTv.ResponseUtils;
import com.sf.appTv.entity.Appimg;
import com.sf.appTv.entity.Response;
import com.sf.appTv.service.AppimgService;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 游戏图片(Appimg)表控制层
 *
 * @author makejava
 * @since 2019-03-28 18:00:20
 */
@RestController

public class AppimgController {
   /*
   多图片上传
    */
    @Resource
    private AppimgService appimgService;

    @GetMapping("selectOne")
    public Appimg selectOne(Integer id) {

        return    this.appimgService.queryById(id);
    }
}