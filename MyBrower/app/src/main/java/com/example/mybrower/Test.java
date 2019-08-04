package com.example.mybrower;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adapter.TestAdapter;
import com.example.modle.UserInfoBean;

import java.util.ArrayList;
import java.util.List;

public class Test  extends AppCompatActivity {

    private String[] strings=new String[10];
    private List<UserInfoBean> userInfoBeans=new ArrayList<>();
    private TestAdapter testAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        testAdapter=new TestAdapter(strings);
        testAdapter=new TestAdapter(userInfoBeans);
    }
}
