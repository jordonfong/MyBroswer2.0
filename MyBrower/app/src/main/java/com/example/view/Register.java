package com.example.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.modle.HttpResult;
import com.example.modle.LoginOrRegisterBean;
import com.example.mybrower.R;
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

public class Register extends AppCompatActivity {
    private EditText user, pwd, cellphone;
    private HttpResult<LoginOrRegisterBean> registerBeanHttpResult;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                if (registerBeanHttpResult.code == 0) {
                    Toast.makeText(Register.this, registerBeanHttpResult.msg, Toast.LENGTH_SHORT);
                    // Intent intent2 = new Intent(Register.this,Login.class);
                    // startActivity(intent2);
                } else {
                    Toast.makeText(Register.this, registerBeanHttpResult.msg, Toast.LENGTH_SHORT);
                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user = findViewById(R.id.et_account);
        pwd = findViewById(R.id.et_pwd2);
        cellphone = findViewById(R.id.et_phone);

        Button button = findViewById(R.id.btn_reg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("测试", "onClick: 发起网络请求");
                String account = user.getText().toString().trim();
                String password = pwd.getText().toString().trim();
                String phone = cellphone.getText().toString().trim();
                //实例化okHttpClient
                OkHttpClient okHttpClient = new OkHttpClient();
                //请求体
                RequestBody requestBody = new FormBody.Builder()
                        .add("params", "{\"account\":\"" + account + "\",\"password\":\"" + password + "\",\"phone\":\"" + phone + "\"}")
                        .add("server", "User")
                        .add("method", "register")
                        .build();
                //post请求，封装请求体
                final Request request = new Request.Builder()
                        .url("https://www.chyblog.cn/api_server")
                        .post(requestBody)
                        .build();
                Log.e("测试", "onClick: " + request.url());
                //call封装request
                Call call = okHttpClient.newCall(request);
                //call   队列 enqueue
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("测试", "onClick: 请求失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        pareseJSONwithGSON2(response.body().string());
                    }
                });
            }
        });
    }

    private void pareseJSONwithGSON2(String jsonData2) {
        Log.e("测试", "pareseJSONwithGSON2: " + jsonData2);
        Gson gson = new Gson();
        registerBeanHttpResult = gson.fromJson(jsonData2, new TypeToken<HttpResult<LoginOrRegisterBean>>() {
        }.getType());
        handler.sendEmptyMessage(100);
    }

}
