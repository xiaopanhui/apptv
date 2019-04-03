package com.sf.appTv.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileUtils {
    private final String localFileFormat ="/data/file/%d/%d".replace("/" ,File.separator);

    // %d,  整数类型（十进制）
    private final String workDirFormat ="/data/work/%d/".replace("/" ,File.separator);
    private final String baseWorkDir="/data/work".replace("/" ,File.separator);
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    public String getFilePath(long fileId,int version) {
        //在路径做了处理
        localFileFormat.replace("/" ,File.separator);
        return String.format(localFileFormat,fileId,version);
    }

    public File getNewFile(long fileId,int version){
        return getNewFile(getFilePath(fileId,version));
    }
    public File getNewFile(String path){
        File file;
        try {
            file=new File(path);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            file.createNewFile();

            return file;
        }catch (Exception e){
            logger.error("create file",e);
        }
        return null;
    }

    public File getPidFile(long serviceId){
        try {
            //添加了路径转换
            String path= String.format(workDirFormat,serviceId);
            File file=new File(path,"pid");
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if (!file.exists()){
                file.createNewFile();
            }
            return file;
        }catch (Exception e){
            logger.error("getPidFile",e);
        }
        return null;
    }

    public String getWordDirPath(long serviceId){

        return String.format(workDirFormat,serviceId);
    }

    public String getBaseWorkDir() {

        return baseWorkDir;
    }
}
