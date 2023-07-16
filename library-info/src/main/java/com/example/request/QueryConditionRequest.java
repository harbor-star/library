package com.example.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/4/14 15:01
 */
@Data
public class QueryConditionRequest implements Serializable {
    // 查询条件search
    private String search;

    // 课程编号升序
    private Boolean snoAsc;

    // 上架事件升序
    private Boolean timeAsc;

    // 价格升序
    private Boolean priceAsc;

    // 分页页码序号
    private Integer pageNum;

    // 分页单页数据量
    private Integer pageSize;
}
