package com.example.utils;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/21 17:44
 */
public class IpAddrConverter {

    public static String getHostIpAddr(String currentIp, String maskCode) {
        String[] currentIpStr = currentIp.split("\\.");
        StringBuilder result = new StringBuilder();
        String[] maskStr = maskCode.split("\\.");
        for (int i = 0; i < currentIpStr.length; i++) {
            int l = Integer.parseInt(currentIpStr[i]) & Integer.parseInt(maskStr[i]);
            if (i != currentIpStr.length - 1) {
                result.append(l);
                result.append(".");
            } else {
                result.append(l);
            }
        }
        return result.toString();
    }

    // keytool -genkey -alias library -keypass 123456 -keyalg RSA -keysize 1024 -validity 365 -keystore library.p12 -storepass 123456

    // 172.16.18.254

    // 172.16.22.150
    public static void main(String[] args) {
        String hostIpAddr = getHostIpAddr("172.16.18.254", "255.255.248.0");
        System.out.println("hostAddr: "+hostIpAddr);

    }
}
