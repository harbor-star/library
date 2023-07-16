package com.example.enumpkg;

public enum ReturnCode {

    OK("200", "SUCCESS"),
    ERROR("500", "ERROR"),
    TIMEOUT("600","REQUEST TIMEOUT"),
    NOTFOUND("404", "RESOURCE NOT FOUND")
    ;


    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ReturnCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ReturnCode{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(ReturnCode.OK);
    }
}
