package com.sf.appTv.exception;

import com.sf.appTv.ResponseUtils;
import com.sf.appTv.entity.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import reactor.core.publisher.Mono;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Controller //这个为了实例化吗
public class GlobalExceptionHandler {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    // 图片大小超过指定大小，抛异常
    @ResponseBody
    @ExceptionHandler(value = MultipartException.class)//处理的异常类
    public Map<String, Object> resolveFileUploadException(MultipartException e) {
        logger.info("MultipartException："+e.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 406);
        map.put("msg", "上传错误：文件过大");
        return map;
    }
    @ResponseBody
    @ExceptionHandler(value = UnsupportedEncodingException.class)
    public Map<String, Object> UnsupportedEncodingException( UnsupportedEncodingException e) {
        logger.info("EncodingException："+e.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 405);
        map.put("msg", "不支持的编码");
        return map;
    }
    @ResponseBody
    @ExceptionHandler(value =NoSuchPaddingException.class)
    public Map<String, Object> noSuchPaddingException( NoSuchPaddingException e) {
        logger.info("noSuchPaddingException："+e.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 406);
        map.put("msg",e.getMessage());
        return map;
    }

//    @ResponseBody
//    @ExceptionHandler(value =Exception.class)
//    public Map<String, Object> bigException( Exception e) {
//        logger.info("Exception："+e.getMessage());
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", 409);
//        map.put("msg",e.getMessage());
//        return map;
//    }

//    @ResponseBody
//    @ExceptionHandler(value = BaseException.class)//处理的异常类
//    public Response FileUploadException( BaseException e) {
//        logger.info("BaseException："+e.getMessage());
//        return   new Response(e.getError(),e.getMessage());
//    }
//    @ResponseBody
//    @ExceptionHandler(Throwable.class)
//    public Mono<Response> defaultErrorHandler( Throwable e){
//        logger.error("SystemError",e);
//        if (e instanceof BaseException){
//            return Mono.just(new Response(((BaseException) e).getError(),e.getMessage()));
//        }
//        return Mono.just(ResponseUtils.systemError());
//    }
}
