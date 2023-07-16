package com.example.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/19 21:45
 */
@Data
public class LeaveOrderActivitiResponse implements Serializable {
    private static final long serialVersionUID = 3823590931166435180L;
    private String leaveReason;
    private String leaveType;
    private String instanceId;
}
