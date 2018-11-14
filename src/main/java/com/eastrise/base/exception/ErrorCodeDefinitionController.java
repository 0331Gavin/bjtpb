package com.eastrise.base.exception;

import com.eastrise.base.json.JsonDataResult;
import org.apache.commons.lang.enums.EnumUtils;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping({"/api"})
public class ErrorCodeDefinitionController {
    public ErrorCodeDefinitionController() {
    }

    @RequestMapping(value = "/api-error-codes",method = RequestMethod.GET)
    public JsonDataResult listErrorCode() {
        JsonDataResult jsonDataResult = new JsonDataResult();
        jsonDataResult.setSuccess(true);
        List<ErrorCodeValuedEnum> vEnums = EnumUtils.getEnumList(ErrorCodeValuedEnum.class);
        List<VErrorCode> list = Lists.newArrayList();
        Iterator var4 = vEnums.iterator();

        while(var4.hasNext()) {
            ErrorCodeValuedEnum vEnum = (ErrorCodeValuedEnum)var4.next();
            ErrorCodeDefinitionController.VErrorCode vErrorCode = new ErrorCodeDefinitionController.VErrorCode();
            vErrorCode.setErrorCode(vEnum.getValue());
            vErrorCode.setErrorMessage(vEnum.getName());
            vErrorCode.setDescription(vEnum.getDescription());
            list.add(vErrorCode);
        }

        jsonDataResult.setData(list);
        return jsonDataResult;
    }

    protected class VErrorCode {
        private int errorCode;
        private String errorMessage;
        private String description;

        protected VErrorCode() {
        }

        public int getErrorCode() {
            return this.errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return this.errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
