package com.example.pingduoduo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pingduoduo.common.Global;
import com.example.pingduoduo.enity.CourseDetail;
import com.example.pingduoduo.enity.TeacherDetail;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class teacher_details extends AppCompatActivity {
    private Button back_button;
    private Button back_confirm;
    public static MediaType JSON = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);
        back_button =(Button) findViewById(R.id.button2);
        back_confirm =(Button) findViewById(R.id.button3);
        JSON = MediaType.parse("application/json; charset=utf-8");

    }
    public void onClick_confirm(View view) {
//        private String courseName;//课程名字
//        private String teacherName;//教师名字
//        private String category;//类别
//        private String credits;//学分
//        private String  college;//开设学院
        final String[] res = {"添加课程成功"};
        final TeacherDetail entity = new TeacherDetail();
        //读值
        MultiAutoCompleteTextView courseName=(MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView2);
        entity.setCourseName((String)courseName.getText().toString());

        MultiAutoCompleteTextView teacherName=(MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView3);
        entity.setTeacherName((String)teacherName.getText().toString());

        Spinner category=(Spinner)findViewById(R.id.spinner3);
        entity.setCategory((String)category.getSelectedItem());

        Spinner credits=(Spinner)findViewById(R.id.spinner2);//学分
        entity.setCredits((String)credits.getSelectedItem());

        Spinner college=(Spinner)findViewById(R.id.spinner4);//开课学院
        entity.setCollege((String)college.getSelectedItem());


        Gson gson = new Gson();
        String json = gson.toJson(entity);
        RequestBody requestBody = RequestBody.create(JSON,json);

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(Global.url+"/course/addCourse")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue( new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                res[0] =request.body().toString();

            }

            @Override
            public void onResponse(Response response) throws IOException {
                 try{
                    JSONArray jsonArray=new JSONArray(response.body().string());
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                         res[0]=jsonObject.getString("data");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Toast toast = Toast.makeText(teacher_details.this, res[0] , Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(teacher_details.this,conditionMenuActivity.class);
        startActivity(intent);
    }
    public void onClick_back(View view){
        finish();
    }
}
