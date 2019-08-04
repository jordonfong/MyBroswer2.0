package com.example.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybrower.MainContent;
import com.example.mybrower.R;
import com.example.mybrower.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    private List<MainContent> mainContentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        if(TextUtils.isEmpty(username)){
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
        Log.e("测试" ,"onCreate: "+username );
        //初始化
        initMainContent();
        //获取RecyclerView控件实例
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        //recyclerview到底是要以流式布局的方式、格子布局的方式还是以瀑布流的方式来展示界面，就是Manager要做的事
        //LinearLayoutManager 是以流式布局的的方式来展示界面
        // 瀑布式布局
        // StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        //你现在写法的用法
        SimpleAdapter simpleAdapter = new SimpleAdapter(mainContentList);
        //旧写法的用法
        //SimpleAdapter simpleAdapter = new SimpleAdapter(mainContentList,this);
        recyclerView.setAdapter(simpleAdapter);

    }

    private void initMainContent(){
    for(int i = 0; i < 2 ; i++){
        MainContent apple = new MainContent("Apple",R.mipmap.apple_pic);
        mainContentList.add(apple);
        MainContent banana = new MainContent("Apple",R.mipmap.banana_pic);
        mainContentList.add(banana);
        MainContent orange = new MainContent("Apple",R.mipmap.orange_pic);
        mainContentList.add(orange);
        MainContent watermelon = new MainContent("Apple",R.mipmap.watermelon_pic);
        mainContentList.add(watermelon);
        MainContent pear = new MainContent("Apple",R.mipmap.pear_pic);
        mainContentList.add(pear);
        MainContent grape = new MainContent("Apple",R.mipmap.grape_pic);
        mainContentList.add(grape);
        MainContent pineapple = new MainContent("Apple",R.mipmap.pineapple_pic);
        mainContentList.add(pineapple);
    }

    }
}
