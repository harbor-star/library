package com.example.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/19 21:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LeaveInfoRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 2874705857966692265L;

    // 操作员名称
    private String operator;

    // 请假原因
    private String leaveReason;

    // 请假类型: 1: 事假, 2: 病假, 3: 年假
    private String leaveType;

    // 备注
    private String extra;

    public static void main(String[] args) throws IOException {
        byte[] raw = Base64.getDecoder().decode("eyJleHAiOjE2MjkyMDg0NjgsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieWppZXdlaSJ9");
        System.out.println(new String(raw));
    }



    private static byte[] parseByte(int[] a) {
        byte[] b = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = (byte) a[i];
        }

        return b;
    }
}
