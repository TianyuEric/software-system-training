package com.projectpractice.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseEntity {
    private String code;//状态码
    private Object data;//返回数据
    private String message;//状态消息

    public static HttpResponseEntity error(String message){
        return builder().code("0").message(message).build();
    }

    public static HttpResponseEntity success(String message, Object data){
        return builder().code("666").data(data).message(message).build();
    }

    public static HttpResponseEntity success(String message){
        return builder().code("666").message(message).build();
    }

    public static HttpResponseEntity response(boolean is, String message, Object data){
        if (is){
            message = message + "成功";
            return success(message, data);
        }else {
            message = message + "失败";
            return error(message);
        }
    }


}
