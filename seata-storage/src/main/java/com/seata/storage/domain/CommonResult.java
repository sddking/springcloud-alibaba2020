package com.seata.storage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 180465
 * @date 2020/7/7 15:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private  Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}
