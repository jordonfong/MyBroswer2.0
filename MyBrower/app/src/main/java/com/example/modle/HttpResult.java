package com.example.modle;

import com.google.gson.annotations.SerializedName;

public class HttpResult<B> {
    /**
     * code : 0
     * msg : 登录成功,这是GET请求
     * entity : {"account":"chy","phone":"13699726462","token":"FLvFQggSdSzNRJab07PHOjfWPxRsrHIk29ydmZBrivjPtMof16XzxzfIMbiGoCxm33ppVqZMPVqsPVRD12hMSpJdlQbmsxaX2019"}
     */

    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("entity")
    public B entity;
}