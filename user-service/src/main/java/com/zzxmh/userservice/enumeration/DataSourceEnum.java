package com.zzxmh.userservice.enumeration;

public enum  DataSourceEnum {
    SUCCESS(0,"success"), FAIL(-1,"error"),EXCEPTION(-2,"exception"),
    NOFILE(-3,"no file");

    private final Integer code;

    private final String msg;

    private DataSourceEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }

}

