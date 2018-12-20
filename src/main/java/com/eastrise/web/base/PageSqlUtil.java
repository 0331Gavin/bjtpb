package com.eastrise.web.base;

public class PageSqlUtil {
    public static String getPageSql( int pageSize, int pageNumber,String sql){
        StringBuffer bf = new StringBuffer("");
        bf.append("SELECT * FROM (SELECT T.*, ROWNUM RN FROM (");
        bf.append(sql);
        bf.append(") T WHERE ROWNUM <=");
        bf.append(pageSize * (pageNumber));
        bf.append(") WHERE RN > ").append(pageSize * (pageNumber - 1));
        return bf.toString();
    }

    public static String getMysqlPageSql( int pageSize, int pageNumber,String sql){
        StringBuffer bf = new StringBuffer("");
        bf.append(sql+" limit "+(pageNumber-1)*pageSize+","+pageSize);
        return bf.toString();
    }

}
