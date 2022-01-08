package com.example.pingduoduo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pingduoduo.enity.bo.CourseQueryBo;
import com.example.pingduoduo.my.MenuMyActivity;
import com.zxl.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CollectActivity extends AppCompatActivity {
//    DropDownMenu mDropDownMenu;
//    private String headers[] = {"类别", "学院", "评课"};
//    private int[] types = new int[]{DropDownMenu.TYPE_LIST_CITY, DropDownMenu.TYPE_LIST_SIMPLE, DropDownMenu.TYPE_LIST_CITY};
//    private String category[] = {"不限", "必修课", "选修课", "公选课"};
//    private String colleges[] = {"不限", "计算机学院", "教育科学学院","政治与行政学院","历史文化学院","外国语言文化学院","美术学院",
//            "教育信息技术学院","数学科学学院","生命科学学院","地理科学学院","心理学院","文学院","经济与管理学院","法学院","公共管理学院","体育科学学院","音乐学院","物理与电信工程学院","化学学院","信息光电子科技学院","旅游管理学院","环境学院"};
//    private String constellations[] = {"不限", "已评", "未评"};
//    private Button button;
//    private Button button_add;
//    private Button button_personal_center;
    private CourseQueryBo courseQueryBo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        courseQueryBo=new CourseQueryBo();
        courseQueryBo.setCategory("");
        courseQueryBo.setCollege("");
        courseQueryBo.setStatus("");
        courseQueryBo.setTeacherName("");
        courseQueryBo.setCourseName("");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
//        mDropDownMenu= (DropDownMenu) findViewById( R.id.dropDownMenu);


//        initView();


        //该监听回调只监听默认类型，如果是自定义view请自行设置，参照demo
//        mDropDownMenu.addMenuSelectListener(new DropDownMenu.OnDefultMenuSelectListener() {
//            @Override
//            public void onSelectDefaultMenu(int index, int pos, String clickstr) {
//                //index:点击的tab索引，pos：单项菜单中点击的位置索引，clickstr：点击位置的字符串
//                Toast.makeText(getBaseContext(), clickstr+pos+index, Toast.LENGTH_SHORT).show();
//                //index是横的，pos是竖的
//                Bundle bundle = new Bundle();
//                if (index==0)
//                {
//                    if ((pos==0))
//                    {
//                        courseQueryBo.setCategory("");
//                    }
//
//                    if(pos>0)
//                    {
//                        courseQueryBo.setCategory(category[pos]);
//
//
//                    }
//                    bundle.putString("category",courseQueryBo.getCategory());
//                    bundle.putString("college",courseQueryBo.getCollege());
//                    bundle.putString("status",courseQueryBo.getStatus());
//                    bundle.putString("coursename",courseQueryBo.getCourseName());
//                    bundle.putString("teachername",courseQueryBo.getTeacherName());
//                    courseItemFragment fragment=new courseItemFragment();
//                    fragment.setArguments(bundle);
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                    fragmentTransaction.replace(R.id.haha,fragment);
//
//                    fragmentTransaction.commit();
//
//                }
//                if(index==1)
//                {
//                    if (pos==0)
//                    {
//                        courseQueryBo.setCollege("");
//
//                    }
//                    if (pos>0)
//                    {
//                        courseQueryBo.setCollege(colleges[pos]);
//
//                    }
//                    bundle.putString("category",courseQueryBo.getCategory());
//                    bundle.putString("college",courseQueryBo.getCollege());
//                    bundle.putString("status",courseQueryBo.getStatus());
//                    bundle.putString("coursename",courseQueryBo.getCourseName());
//                    bundle.putString("teachername",courseQueryBo.getTeacherName());
//                    courseItemFragment fragment=new courseItemFragment();
//                    fragment.setArguments(bundle);
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                    fragmentTransaction.replace(R.id.haha,fragment);
//
//                    fragmentTransaction.commit();
//
//                }
//                if(index==2)
//                {
//                    if (pos==0)
//                    {
//                        courseQueryBo.setStatus("");
//
//                    }
//                    if(pos>0)
//                    {
//                        courseQueryBo.setStatus(constellations[pos]);
//
//
//
//
//                    }
//                    bundle.putString("category",courseQueryBo.getCategory());
//                    bundle.putString("college",courseQueryBo.getCollege());
//                    bundle.putString("status",courseQueryBo.getStatus());
//                    bundle.putString("coursename",courseQueryBo.getCourseName());
//                    bundle.putString("teachername",courseQueryBo.getTeacherName());
//                    CollectFragment fragment=new CollectFragment();
//                    fragment.setArguments(bundle);
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                    fragmentTransaction.replace(R.id.collect,fragment);
//
//                    fragmentTransaction.commit();
//
//                }
//
//
//            }
//        });



        //动态添加按钮图案
        final Bundle bundle = new Bundle();
        bundle.putString("category",courseQueryBo.getCategory());
        bundle.putString("college",courseQueryBo.getCollege());
        bundle.putString("status",courseQueryBo.getStatus());
        bundle.putString("coursename",courseQueryBo.getCourseName());
        bundle.putString("teachername",courseQueryBo.getTeacherName());

        SharedPreferences preferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        long userId = preferences.getLong("userId",0);

        bundle.putString("userId", String.valueOf(userId));
        CollectFragment collectFragment = new CollectFragment();
        collectFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.collect,collectFragment);

        fragmentTransaction.commit();
//        button=new Button(this);
//        button=(Button)findViewById(R.id.home);
//        Drawable icon_home_fill=getResources().getDrawable(R.drawable.icon_home_fill);
//        icon_home_fill.setBounds(0, 0, 70, 70);
//        button.setCompoundDrawables(null,icon_home_fill , null, null);
//
//
//
//        button_add=new Button(this);
//        button_add=(Button)findViewById(R.id.add);
//        Drawable icon_add=getResources().getDrawable(R.drawable.icon_add);
//        icon_add.setBounds(0, 0, 70, 70);
//        button_add.setCompoundDrawables(null,icon_add , null, null);
//
//        button_personal_center=new Button(this);
//        button_personal_center=(Button)findViewById(R.id.personal_center);
//        Drawable icon_personal_center=getResources().getDrawable(R.drawable.icon_personal_center);
//        icon_personal_center.setBounds(0, 0, 70, 70);
//        button_personal_center.setCompoundDrawables(null,icon_personal_center , null, null);


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /*
//                第一个参数:上下文对象this
//                第二个参数:目标文件
//                 */
//
//                Intent intent = new Intent(CollectActivity.this,conditionMenuActivity.class);
//                startActivity(intent);
//
//            }
//        });
//        button_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /*
//                第一个参数:上下文对象this
//                第二个参数:目标文件
//                 */
//                Intent intent = new Intent(CollectActivity.this,teacher_details.class);
//                startActivity(intent);
//
//            }
//        });
//        button_personal_center.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /*
//                第一个参数:上下文对象this
//                第二个参数:目标文件
//                 */
//                Intent intent = new Intent(CollectActivity.this, MenuMyActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//        SearchView viewById = (SearchView) findViewById(R.id.searchView);
//        viewById.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                if(TextUtils.isEmpty(query))
//                {
//                    courseQueryBo.setCourseName("");
//                    courseQueryBo.setTeacherName("");
//                }
//
//                else
//                {
//                    courseQueryBo.setCourseName(query);
//                    courseQueryBo.setTeacherName(query);
//                }
//
//
//                Bundle bundle=new Bundle();
//
//                bundle.putString("category",courseQueryBo.getCategory());
//                bundle.putString("college",courseQueryBo.getCollege());
//                bundle.putString("status",courseQueryBo.getStatus());
//                bundle.putString("coursename",courseQueryBo.getCourseName());
//                bundle.putString("teachername",courseQueryBo.getTeacherName());
//
//                courseItemFragment fragment=new courseItemFragment();
//                fragment.setArguments(bundle);
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                fragmentTransaction.replace(R.id.haha,fragment);
//
//                fragmentTransaction.commit();
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if(TextUtils.isEmpty(newText))
//                {
//                    courseQueryBo.setCourseName("");
//                    courseQueryBo.setTeacherName("");
//                }
//                return true;
//
//            }
//        });
//
//    }
//
//    private void initView() {
//        View contentView = getLayoutInflater().inflate(R.layout.contentview, null);
//        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), initViewData(), contentView);
//
//        //该监听回调只监听默认类型，如果是自定义view请自行设置，参照demo
//        mDropDownMenu.addMenuSelectListener(new DropDownMenu.OnDefultMenuSelectListener() {
//            @Override
//            public void onSelectDefaultMenu(int index, int pos, String clickstr) {
//                //index:点击的tab索引，pos：单项菜单中点击的位置索引，clickstr：点击位置的字符串
//                Toast.makeText(getBaseContext(), clickstr+pos+index, Toast.LENGTH_SHORT).show();
//                //index是横的，pos是竖的
//                if (index==0)
//                {
//                    if(pos>0)
//                    {
//                        courseQueryBo.setCategory(category[pos]);
//                    }
//
//
//                }
//                if(index==1)
//                {
//                    if (pos>0)
//                    {
//                        courseQueryBo.setCollege(colleges[pos]);
//                    }
//
//                }
//                if(index==2)
//                {
//                    if(pos>0)
//                    {
//                        courseQueryBo.setStatus(constellations[pos]);
//                    }
//
//                }
//
//
//            }
//        });







    }

    /**
     * 设置类型和数据源：
     * DropDownMenu.KEY对应类型（DropDownMenu中的常量，参考上述核心源码）
     * DropDownMenu.VALUE对应数据源：key不是TYPE_CUSTOM则传递string[],key是TYPE_CUSTOM类型则传递对应view
     */
//    private List<HashMap<String, Object>> initViewData() {
//        List<HashMap<String, Object>> viewDatas = new ArrayList<>();
//        HashMap<String, Object> map;
//        for (int i = 0; i < headers.length; i++) {
//            map = new HashMap<String, Object>();
//            map.put(DropDownMenu.KEY, types[i]);
//            switch (types[i]) {
//                case DropDownMenu.TYPE_LIST_CITY:
//                    if(i==0)
//                    {
//                        map.put(DropDownMenu.VALUE, category);
//                        map.put(DropDownMenu.SELECT_POSITION,0);
//                    }else
//                    {
//                        map.put(DropDownMenu.VALUE, constellations);
//                        map.put(DropDownMenu.SELECT_POSITION,0);
//
//                    }
//
//                    break;
//                case DropDownMenu.TYPE_LIST_SIMPLE:
//                    map.put(DropDownMenu.VALUE, colleges);
//                    map.put(DropDownMenu.SELECT_POSITION,0);
//                    break;
//
//            }
//            viewDatas.add(map);
//
//        }
//        return viewDatas;
//    }

//    private View getCustomView() {
//        View v = getLayoutInflater().inflate(R.layout.custom, null);
//        TextView btn = (TextView) v.findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                mDropDownMenu.setTabText(2,"自定义");//设置tab标签文字
//                mDropDownMenu.closeMenu();//关闭menu
//            }
//        });
//        return v;
//    }

//    @Override
//    public void onBackPressed() {
//        //退出activity前关闭菜单
//        if (mDropDownMenu.isShowing()) {
//            mDropDownMenu.closeMenu();
//        } else {
//            super.onBackPressed();
//        }
//    }

//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.home:
//                Intent i = new Intent("android.intent.action.conditionMenuActivity");
//                this.startActivity(i);
//                break;
//            case R.id.add:
//                Intent ii = new Intent(conditionMenuActivity.this,teacher_details.class);
//                this.startActivity(ii);
//                break;
//            case R.id.personal_center:
//                break;
//            default:
//                break;
//        }
//    }

}