package com.eastrise.web.base;


import com.eastrise.base.exception.ErrorCode;
import com.eastrise.base.exception.ErrorCodeDefinition;
import com.eastrise.base.exception.ErrorCodeValuedEnum;

/**
 * Created by shenfu on 2017/4/14.
 */
@ErrorCodeDefinition(thousands = 4)
public class SystemErrorCodeType {
    @ErrorCode(msg = "获取数据失败！")
    public static ErrorCodeValuedEnum E_GET_DATA_FALED;

    @ErrorCode(code = 2, msg = "{0}失败！")
    public static ErrorCodeValuedEnum E_ACTION_FALED;

    @ErrorCode(code = 3, msg = "参数错误：{0}！")
    public static ErrorCodeValuedEnum E_PARAM_ERR;

    @ErrorCode(code = 4, msg = "权限不足，请联系管理员！")
    public static ErrorCodeValuedEnum E_PERMISSION_DENY_ERR;

    @ErrorCode(code = 5, msg = "{0}不存在！")
    public static ErrorCodeValuedEnum E_NOT_EXIST;

    @ErrorCode(code = 6, msg = "{0}不能为空！")
    public static ErrorCodeValuedEnum E_NOT_NULL;

    @ErrorCode(code=101,msg = "{0}为空！")
    public static ErrorCodeValuedEnum E_IS_NULL;

    @ErrorCode(code=103,msg = "{0}最多输入{1}个字符！")
    public static ErrorCodeValuedEnum E_EXTREME_LENGTH;

    @ErrorCode(code=104,msg = "创建时ID不可设值！")
    public static ErrorCodeValuedEnum E_ID_NOT_NULL;

    @ErrorCode(code=105,msg = "更新时ID不可为空！")
    public static ErrorCodeValuedEnum E_ID_NULL;

    @ErrorCode(code=106,msg = "{0}已存在！")
    public static ErrorCodeValuedEnum E_IS_EXISTS;

    @ErrorCode(code=107,msg = "{0}不唯一！")
    public static ErrorCodeValuedEnum E_NOT_UNIQUE;

    //自定义错误内容
    @ErrorCode(code=108,msg = "{0}")
    public static ErrorCodeValuedEnum CUSTOM;
    //不可重复
    @ErrorCode(code=110,msg = "{0}重复！")
    public static ErrorCodeValuedEnum E_IS_REPEAT;
    //未登录
    @ErrorCode(code=109,msg = "请先登录")
    public static ErrorCodeValuedEnum NOT_LOGIN;

}
