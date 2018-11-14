package com.eastrise.base;

import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * create by gzq on 2018/11/14 11:25
 */
public class StringArrayConverter implements AttributeConverter<List<String>, String> {
    public StringArrayConverter() {
    }

    public String convertToDatabaseColumn(List<String> strings) {
        return strings != null ? StringUtils.join(strings, ",") : null;
    }

    public List<String> convertToEntityAttribute(String s) {
        return s != null ? Lists.newArrayList(StringUtils.split(s, ",")) : null;
    }
}
