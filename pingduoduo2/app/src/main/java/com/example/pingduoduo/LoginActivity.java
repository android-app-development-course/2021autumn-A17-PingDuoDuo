package com.example.pingduoduo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pingduoduo.common.Global;
import com.example.pingduoduo.common.ResponseResult;
import com.example.pingduoduo.enity.vo.UserVo;
import com.example.pingduoduo.enums.ResponseEnum;
import com.example.pingduoduo.form.UserLoginForm;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ResponseResult responseResult ;
    private UserVo userVo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText)findViewById(R.id.username);
        passwordEditText = (EditText)findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameEditText.getText().toString().equals("")||passwordEditText.getText().toString().equals("")){
                    return;
                }

                Thread t1 = new Thread(()->{
                    Looper.prepare();//增加部分
                    //线程等待执行
                    LockSupport.park();

                    if(responseResult!=null && responseResult.getStatus().equals(ResponseEnum.SUCCESS.getCode())&&userVo != null){
                        SharedPreferences preferences = getSharedPreferences("userInfo", MODE_PRIVATE);

                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = preferences.edit();
                        String userName = userVo.getName();
                        Long userId = userVo.getId();
                        editor.putLong("userId",userId);
                        editor.putString("userName",userName);
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this,conditionMenuActivity.class);
                        Log.d("loginbtn","onclick");
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }


                    Looper.loop();
                },"t1");
                t1.start();

                Thread t2 = new Thread(()->{
                    try {
                        String username = usernameEditText.getText().toString();
                        String password = passwordEditText.getText().toString();
                        Gson gson = new Gson();
                        String json = gson.toJson(new UserLoginForm(username, password));
                        OkHttpClient client = new OkHttpClient();

                        Request request = new Request.Builder().url(Global.url+"/user/login").post(RequestBody.create(MediaType.parse("application/json"),json)).build();
                        Response response = client.newCall(request).execute();
                         responseResult = gson.fromJson(Objects.requireNonNull(response.body()).string(), ResponseResult.class);
                        userVo = gson.fromJson(gson.toJson(responseResult.getData()),UserVo.class);
                        Log.i("userVo",userVo.toString());
                        Log.i("responseResult",responseResult.toString());



                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        LockSupport.unpark(t1);
                    }
                },"t2");
                t2.start();

            }
        });


    }


}