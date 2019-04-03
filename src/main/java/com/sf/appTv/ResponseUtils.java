package com.sf.appTv;

import com.sf.appTv.entity.Response;
import com.sf.appTv.utils.Utils;
import lombok.Data;

@Data
public class ResponseUtils  {
    public static <T> Response<T> success(T data)  {
        /*data的加密*/
//         data= (T) Utils.encryptDESK( data.toString().trim());
        return new Response<T>(0,"Ok", data);}
//    public static <T> Response<T> success(T data){
//        return new Response<T>(0,"Ok",data);}
    public static <T> Response<T> noUser(){return new Response<T>(100,"No User");}
    public static <T> Response<T> recordExists(){return new Response<T>(102,"Record Exits");}
    public static <T> Response<T> accessDenied(){return new Response<T>(403,"Access Denied");}
    public static <T> Response<T> systemError(){return new Response<T>(500,"System Error");}
    public static <T> Response<T> paramError(){return new Response<T>(501,"Parameter Error");}
    public static <T> Response<T> paramError(T data){return new Response<T>(501,"Parameter Error",data);}
    public static <T> Response<T> notFound(){return new Response<T>(404,"Not Found");}
    public static <T> Response<T> notLogin(){return new Response<T>(101,"Not Login");}
    public static <T> Response<T> upFile(){return new Response<T>(1000,"超出上传文件大小！");}


}
