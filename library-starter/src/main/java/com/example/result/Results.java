package com.example.result;

import com.example.enumpkg.ReturnCode;
import lombok.Data;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/19 21:36
 */
@Data
public class Results<T> {
    private T data;
    private ReturnCode resultCode;
    private String description;
    public Results (T data, ReturnCode returnCode, String description) {
        this.data = data;
        this.resultCode = returnCode;
        this.description = description;
    }

    public static <T>Results success(T data) {
        return new Results(data, ReturnCode.OK, "调用成功");
    }

    public static <T>Results fail(T data) {
        return new Results(data, ReturnCode.ERROR, "发生异常");
    }

}
