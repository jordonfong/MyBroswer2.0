package com.example.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Impl.IloginView;
import com.example.modle.HttpResult;
import com.example.modle.LoginOrRegisterBean;
import com.example.mybrower.User;
import com.example.view.Login;
import com.example.view.MainActivity;
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

public class LoginPresenter {
//全局变量
    private IloginView mloginView;
    private  Context mContext;
    private SharedPreferences sharedPreferences;
    public  LoginPresenter(Context context) {
        mContext=context;
    }



    //为了拿到编辑框的实例，先全局声明

    //gson解析服务器传回来的数据处需要用到的声明，因为在子线程跟主线程通讯的时候也要用到，即传达code来实现登录判断
    //所以在这里全局声明，方便在不同方法【“a（）”称方法】中使用
    private HttpResult<LoginOrRegisterBean> registerBeanHttpResult;
    //子线程跟主线程沟通的桥梁 handler，这里实现，子线程如果code等于0即登录成功，跳转界面
    //重载handleMessage
    //格式：private Handler handler = new Handler(){};
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //子线程发送的变量 what
            if (msg.what == 101) {
                if (registerBeanHttpResult.code == 0) {//这个0是服务器传回来的jaon数据，通过解析后得到0，即是登录成功
                    mloginView.showTips("登录成功!");
                   // Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                    //抛出一句吐司 提示用户登录成功
                    //保存登录状态
                    sharedPreferences = mContext.getSharedPreferences("Login", Context.MODE_PRIVATE
                    );
                    sharedPreferences.edit()
                            .putString("username","ture")
                            .commit();

                    mloginView.loginSeccess();
                } else {//登录失败
                    mloginView.showTips(registerBeanHttpResult.msg.toString());

                }
            }
        }
    };


    public void login(String username ,String pwd ){
        //  MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //   JSONObject json = new JSONObject();
        //   String param = gson.toJson(RequestBody);
        //给按钮实例注册一个监听器OnClickListener ，点击按钮时会执行监听器中的的onclick（）方法

        Log.e("测试", "onClick: 发起网络请求");

        //OkHttpClient网络通道，eg.找同学家，OkHttpClient相当于有线路，出发点
        //1.拿到okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        // Request.Builder builder = new Request.Builder();
        //Request request = builder.get().url("http://www.chyblog.cn/api_server/User/login/").build();
        //请求体    "account"   ： \" " + account + " \"，
        final RequestBody requestBody = new FormBody.Builder()
                .add("params", "{\"account\":\"" + username + "\",\"password\":\"" + pwd + "\"}")
                .add("server", "User")
                .add("method", "login")
                .build();
        Request request = new Request.Builder()
                .url("https://www.chyblog.cn/api_server")
                .post(requestBody)
                .build();
        Log.e("测试", "onClick: " + request.url());
        //3.将Request封装为Call
        Call call = okHttpClient.newCall(request);
        //4.执行cal
        //call.enqueue 队列，也是异步，同步的是Response response = call.execute();同步会阻塞主线程，在安卓6.0之后都用异步
        //异步就是不用一直在等待服务器回应，只要服务器在一定时间内回应就可以
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("测试", "onClick: 请求失败" + e.getMessage());
            }

            //onResponse是回应的意思，即度武器对我们客户端的回应
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //将服务器回应数据转成string？？？？？
                String responseData = response.body().string();
                Log.e("测试", "onResponse: " + responseData);
                pareseJSONwithGSON(responseData);
            }
        });

    }


    //json数据解析，解析服务器传过来的数据
   public void pareseJSONwithGSON(String jsonData) {
        Gson gson = new Gson();
        //TypeToken<HttpResult<LoginOrRegisterBean>> TypeToken<类名<类名>>()().getType()   < 请求服务器类<登录注册类>>
        //下式等同于 HttpResult<LoginOrRegisterBean> registerBeanHttpResult = gson.from... 不过将等号左边放到了全局变量
        registerBeanHttpResult = gson.fromJson(jsonData, new TypeToken<HttpResult<LoginOrRegisterBean>>() {
        }.getType());
        //通知主线程更新ui
        handler.sendEmptyMessage(101);
        //字符比较
        //if("aa".equals("ba"))
        Log.e("测试", "pareseJSONwithGSON: " + registerBeanHttpResult.code);
    }
}
