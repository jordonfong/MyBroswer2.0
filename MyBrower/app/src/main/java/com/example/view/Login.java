package com.example.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Impl.IloginView;
import com.example.modle.HttpResult;
import com.example.modle.LoginOrRegisterBean;
import com.example.mybrower.R;
import com.example.presenter.LoginPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class Login extends AppCompatActivity implements View.OnClickListener, IloginView {

   private Button btn_login,btn_reg;
   private LoginPresenter mLoginPresenter;
   private EditText username, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginPresenter=new LoginPresenter(this) ;
        intiView();
    }
    private void intiView(){
        //实例化控件
        username = findViewById(R.id.et_account);
        pwd = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);//得到按钮实例
        btn_reg = findViewById(R.id.btn_register);
        btn_login.setOnClickListener(this);
        btn_reg.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                Intent reg = new Intent(Login.this, Register.class);
                startActivity(reg);
                break;
            case R.id.btn_login:
                mLoginPresenter.login(username.getText().toString().trim(), pwd.getText().toString().trim());
                break;
            default:
                break;
        }

    }


    @Override
    public void showTips(String tips) {
        Toast.makeText(Login.this, tips.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSeccess() {
        finish();
        //以下是显示intent，登录成功实现页面跳转，隐式intent的用法？
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }
}
