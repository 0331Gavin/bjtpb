package com.eastrise.base.exception;

import com.eastrise.base.json.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@ComponentScan(basePackageClasses = {ErrorCodeConfiguration.class})
@ControllerAdvice
public class ErrorCodeConfiguration {
    private Logger logger = LoggerFactory.getLogger(ErrorCodeConfiguration.class);

    public ErrorCodeConfiguration() {
    }

    @ExceptionHandler({NRAPException.class})
    @ResponseBody
    public JsonResult resolveException(Object exceptionObject, Exception e) {
        if (e instanceof NRAPException) {
            this.logger.error("系统发生异常: \n", e);
            NRAPException ex = (NRAPException)e;
            JsonResult jsonResult = new JsonResult(false);
            jsonResult.setMessage(ex.getMessage());
            jsonResult.setErrorCode(ex.getErrorCode());
            return jsonResult;
        } else {
            return null;
        }
    }
}
