package com.example.modle;

import com.google.gson.annotations.SerializedName;

public class UserInfoBean {
    /**
     * id : 1
     * account : chy
     * password : pbkdf2_sha256$120000$13ylbmPm0018$cYrAwI+tQ8d8Z/3VGp7kHp3xnMuzOqioRaVXmhsNsCo=
     * name : 未填写姓名
     * age : 未知年龄
     * gender : 男
     * phone : 13699726462
     * address : 居住在火星
     * token : FLvFQggSdSzNRJab07PHOjfWPxRsrHIk29ydmZBrivjPtMof16XzxzfIMbiGoCxm33ppVqZMPVqsPVRD12hMSpJdlQbmsxaX2019
     * register_date : 2019-07-29 16:33:12
     * update_date : 2019-07-29 16:33:12
     */

    @SerializedName("id")
    public int id;
    @SerializedName("account")
    public String account;
    @SerializedName("password")
    public String password;
    @SerializedName("name")
    public String name;
    @SerializedName("age")
    public String age;
    @SerializedName("gender")
    public String gender;
    @SerializedName("phone")
    public String phone;
    @SerializedName("address")
    public String address;
    @SerializedName("token")
    public String token;
    @SerializedName("register_date")
    public String registerDate;
    @SerializedName("update_date")
    public String updateDate;
}
