package com.eastrise.config;


import com.eastrise.base.exception.ErrorCode;
import com.eastrise.base.exception.ErrorCodeDefinition;
import com.eastrise.base.exception.ErrorCodeDescription;
import com.eastrise.base.exception.ErrorCodeValuedEnum;
import org.apache.commons.lang.enums.EnumUtils;
import org.apache.commons.lang.enums.ValuedEnum;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ErrorCodeAutoConfiguration implements BeanPostProcessor {

    public static final int ORDER = -101;
    private final Logger logger = LoggerFactory.getLogger(ErrorCodeAutoConfiguration.class);
    private final Map<Integer, String> initiedThousandObjects = new ConcurrentHashMap();
    private final List<Integer> initiedErrorCode = Lists.newArrayList();

    public ErrorCodeAutoConfiguration() {
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (AnnotationUtils.isAnnotationDeclaredLocally(ErrorCodeDefinition.class, bean.getClass())) {
            this.logger.info("start to process {}", bean.getClass().getName());
            ErrorCodeDefinition definition = (ErrorCodeDefinition) AnnotationUtils.getAnnotation(bean.getClass(), ErrorCodeDefinition.class);
            int thousands = definition.value();
            if (this.initiedThousandObjects.containsKey(thousands)) {
                String v = bean.getClass().getName();
                String kv = (String)this.initiedThousandObjects.get(thousands);
                if (!v.equals(kv)) {
                    throw new FatalBeanException("存在已经重复的Thousands定义 " + kv + "|" + v);
                }
            } else {
                this.initiedThousandObjects.put(thousands, bean.getClass().getName());
            }

            this.logger.debug("basic errCode :  {}", thousands);
            Field[] fields = bean.getClass().getFields();
            Field[] var19 = fields;
            int var7 = fields.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Field field = var19[var8];
                if (field.getType().isAssignableFrom(ErrorCodeValuedEnum.class)) {
                    this.logger.info("start to process field: {}", field.getName());
                    ErrorCode errCode = (ErrorCode) AnnotationUtils.getAnnotation(field, ErrorCode.class);
                    if (errCode != null) {
                        int caculatedErrorCode = thousands * 1000 + errCode.value();
                        ValuedEnum vEnum = EnumUtils.getEnum(ErrorCodeValuedEnum.class, caculatedErrorCode);
                        if (vEnum == null) {
                            this.logger.debug("create an new ErrorCodeValuedEnum : {}", caculatedErrorCode);
                            ErrorCodeDescription errorCodeDescription = (ErrorCodeDescription)field.getAnnotation(ErrorCodeDescription.class);
                            String combineNameAndErrorCode = "[" + caculatedErrorCode + "]" + errCode.msg();
                            ErrorCodeValuedEnum fieldValue = new ErrorCodeValuedEnum(caculatedErrorCode, combineNameAndErrorCode);
                            if (errorCodeDescription != null) {
                                this.logger.debug("errorCodeDescription : {}", errorCodeDescription.value());
                                fieldValue.setDescription(errorCodeDescription.value());
                            }

                            try {
                                this.logger.debug("field setting");
                                field.set(bean, fieldValue);
                                this.initiedErrorCode.add(caculatedErrorCode);
                                this.logger.info("field setting successful {}", caculatedErrorCode);
                            } catch (IllegalAccessException var17) {
                                this.logger.error("", var17);
                            }
                        } else if (this.initiedErrorCode.contains(caculatedErrorCode)) {
                            throw new FatalBeanException("存在已经重复的ErrorCode 定义 " + caculatedErrorCode + " in " + bean.getClass().getName() + "." + field.getName());
                        }
                    }
                }
            }

            this.logger.info("end process {}", bean.getClass().getName());
        }

        return bean;
    }
}
