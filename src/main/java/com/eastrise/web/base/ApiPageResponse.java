package com.eastrise.web.base;

import java.util.List;
import java.util.Map;

/**
 * create by gzq on 2018/11/13 15:44
 */
public class ApiPageResponse {

    private int total;
    private List<Map<String, Object>> rows;

    public ApiPageResponse(int total, List<Map<String, Object>> rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }

    public static ApiPageResponse of(int total,List<Map<String, Object>> rows){
        return new ApiPageResponse(total,rows);
    }
}
