package com.sf.appTv.entity;

import com.github.pagehelper.Page;
import lombok.Data;
import net.sf.json.JSONObject;

@Data
public class PageResponse<T> extends Response<Page<JSONObject>> {
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;

    public PageResponse(int code, String msg, Page<JSONObject> data){
        super(code,msg,data);
        this.pages=data.getPages();
        this.total=data.getTotal();
    }
}
