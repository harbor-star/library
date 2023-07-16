package com.example.request;

import lombok.Data;

import java.util.List;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/4/10 15:59
 */
@Data
public class InformationRequest {
    private List<Long> infoCollection;
}
