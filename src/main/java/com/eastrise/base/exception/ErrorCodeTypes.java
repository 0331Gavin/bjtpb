package com.eastrise.base.exception;

@ErrorCodeDefinition(thousands = 2)
public class ErrorCodeTypes {

    @ErrorCode(code = 400,msg = "Bad Request")
    @ErrorCodeDescription("Bad Request")
    public static ErrorCodeValuedEnum BAS_REQUEST;


    @ErrorCode(code = 404,msg = "NOT FOUNT")
    @ErrorCodeDescription("NOT_FOUND")
    public static ErrorCodeValuedEnum NOT_FOUND;

    @ErrorCode(code = 500,msg = "Unknown Internal Error")
    @ErrorCodeDescription("Unknown Internal Error")
    public static ErrorCodeValuedEnum INTERNAL_SERVER_ERROR;


}
