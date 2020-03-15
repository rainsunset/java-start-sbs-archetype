#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * Company
 * Copyright (C) 2004-2018 All Rights Reserved.
 */
package ${package}.constant;

import com.rainsunset.common.bean.ErrorInfoInterface;

/**
 * @author ligw
 * @version ${symbol_dollar}Id GlobalErrorInfoEnum.java, v 0.1 2018-08-22 1:03 ligw Exp ${symbol_dollar}${symbol_dollar}
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface {

    /**
     * 200，成功
     * 201，已存在
     * 401，未授权
     * 403，禁止
     * 404，不存在
     * 405，状态异常
     * 412，前提条件失败
     * 415，格式错
     * 500，内部错误
     */

    // region 412000-412099 通用业务逻辑条件异常
    DEMOEC_412000("DEMOEC_412000", "查询对象不存在"),
    // endregion

    // region 415000-415099 请求参数非法
    DEMOEC_415000("DEMOEC_415000", "请求参数有误"),
    // endregion

    // region 500000-500099 内部错误
    DEMOEC_500000("DEMOEC_500000", "系统异常"),
    // endregion

    ;

    private String code;

    private String message;

    GlobalErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode(){
        return this.code;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

    public static GlobalErrorInfoEnum getResponseMsg(String code){
        for(GlobalErrorInfoEnum responseInfo:GlobalErrorInfoEnum.values()){
            if(code.equals(responseInfo.getCode())){
                return responseInfo;
            }
        }
        return DEMOEC_500000;
    }

}
