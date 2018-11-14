package com.eastrise.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 王博文 on 2017/8/11.
 * 将字符串在驼峰和大写下划线之间切换
 */
public class ChangeNameForm {
    //用来匹配带下划线的字符串
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    //用来匹配驼峰式的字符串
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     * @param str 原字符串
     * @return 结果字符串
     */
    public static String lineToHump(String str){
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     * @param str 原字符串
     * @return 结果字符串
     */
    public static String humpToLine(String str){
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString().toUpperCase();
    }

    /**
     * 对通过sql语句查询到的结果进行名字格式转换
     * @param origin 源数据
     * @return 改变形式后的数据
     */
    public static List<Map<String, Object>> changeSqlResult(List<Map<String, Object>> origin) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> signleMap: origin) {
            Map<String, Object> newMap = new HashMap<>();
            for (Map.Entry<String, Object> tmp: signleMap.entrySet()) {
                newMap.put(ChangeNameForm.lineToHump(tmp.getKey()), tmp.getValue());
            }
            result.add(newMap);
        }
        return result;
    }

    /**
     * 对通过sql语句查询到的结果进行名字格式转换
     * @param origin 源数据 Map<String,Object>格式
     * @return 改变形式后的数据
     */
    public static Map<String, Object> changeSqlResultMap(Map<String, Object> origin) {
        Map<String, Object> newMap = new HashMap<>();
        for (Map.Entry<String, Object> tmp: origin.entrySet()) {
            newMap.put(ChangeNameForm.lineToHump(tmp.getKey()), tmp.getValue());
        }
        return newMap;
    }
}

