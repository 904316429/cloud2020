package com.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-10 19:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T date;

    public CommonResult(Integer code, String message){
        this(code, message, null);
    }

}
