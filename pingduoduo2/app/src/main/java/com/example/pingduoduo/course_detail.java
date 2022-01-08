package com.example.pingduoduo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pingduoduo.common.Global;
import com.example.pingduoduo.enity.CourseDetail;
 import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.annotations.NonNull;

public class course_detail extends AppCompatActivity {
    public static MediaType JSON = null;
    private Button back_button;
    private Button back_confirm;
    final String[] res = {"添加评价成功"};
    String cid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
//        back_button =(Button) findViewById(R.id.button2);
//        back_button =(Button) findViewById(R.id.button3);


        JSON = MediaType.parse("application/json; charset=utf-8");

        Intent t=getIntent();

        cid=t.getStringExtra("cid");
        Log.i("cid",cid);


    }
    public void onClick_confirm(View view) {

        final CourseDetail entity = new CourseDetail();
        //读值
        entity.setcId(cid);

        Spinner Eva_level=(Spinner)findViewById(R.id.spinner);//评价教师
        entity.setEvaLevel((String) Eva_level.getSelectedItem());

        Spinner checkIn=(Spinner)findViewById(R.id.spinner1);//频率
        entity.setCheckIn((String)checkIn.getSelectedItem());

        Spinner mark=(Spinner)findViewById(R.id.spinner2);
        entity.setMark((String)mark.getSelectedItem());

        Spinner evaMode=(Spinner)findViewById(R.id.spinner3);
        entity.setEvaMode((String)evaMode.getSelectedItem());

        MultiAutoCompleteTextView comment=(MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView);
        entity.setComment((String)comment.getText().toString());


        Gson gson = new Gson();

        String json = gson.toJson(entity);
        Log.i("111",json);
        RequestBody requestBody = RequestBody.create(JSON,json);

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(Global.url+"/evaluation/addOne")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue( new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
//                try{
//                    JSONArray jsonArray=new JSONArray(response.body().string());
//                    for(int i=0;i<jsonArray.length();i++)
//                    {
//                        JSONObject jsonObject=jsonArray.getJSONObject(i);
//                        r es[0]=jsonObject.getString("data");
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                Toast toast = Toast.makeText(course_detail.this, res[0] , Toast.LENGTH_SHORT);
//                toast.show();
//        Intent intent = new Intent(course_detail.this,courseContentActivity2.class);
                Log.i("11111111111111","哈哈哈");
                courseContentActivity2.Companion.actionStart(course_detail.this,Global.course.getCourseName(),Global.course.getTeacherName(),Global.course.getCategory(),Global.course.getCredits(),Global.course.getCollege(),Long.valueOf(entity.getcId()));
//               .actionStart(parent.context,course.courseName,course.teacherName,course.category,course.credits,course.college,course.id)
//        startActivity(intent);
            }
        });


    }
    public void onClick_back(View view){
//        Intent intent = new Intent(course_detail.this,courseContentActivity2.class);
//        startActivity(intent);
        finish();
    }
}
