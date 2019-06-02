package net.hunau.mygridtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    private EditText et1,et2;
    private Button btn1,btn2,btn4;

    String name;
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        btn4 = (Button)findViewById(R.id.btn4);

        final DBAdapter dbAdepter = new DBAdapter(this);
        dbAdepter.open();

        //按钮注册到同一个监听器
        Button.OnClickListener buttonListener = new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.btn1:
                        //清空用户名和密码
                        et1.setText("");
                        et2.setText("");
                        return;
                    case R.id.btn2:
                        //传递用户名和密码到第二个页面，并进行登录操作
                        Intent intent = new Intent( MainActivity.this,ResultActivity.class);
                        Bundle bundle = new Bundle();
                        final StringBuffer sb = new StringBuffer();
                        name=et1.getText().toString();
                        pwd=et2.getText().toString();

                        if("".equals(name)){
                            Log.i(TAG,"用户名不能为空");
                            Toast.makeText(getApplicationContext(),"用户名不能为空", Toast.LENGTH_SHORT).show();
                        }else if("".equals(pwd)){
                            Log.i(TAG,"密码不能为空");
                            Toast.makeText(getApplicationContext(),"密码不能为空", Toast.LENGTH_SHORT).show();
                        }else{
                            User[] user=dbAdepter.QueryByName(name);
                            if(((user[0].getName()).equals(name) && (user[0].getPwd()).equals(pwd))) {
                                Log.i(TAG,"");
                                bundle.putString("name", user[0].getName());
                                bundle.putString("pwd", user[0].getPwd());
                                bundle.putString("sexy",user[0].getSexy());
                                bundle.putBoolean("isused",user[0].isIsused());
                                intent.putExtras(bundle);
                                Toast.makeText(getApplicationContext(),"登陆成功", Toast.LENGTH_SHORT).show();
                                Log.i(TAG,"开始跳转");
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(),"用户名或密码输入错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                        return;
                    case R.id.btn4:
                        Intent intent1 = new Intent( MainActivity.this,RegisterActivity.class);
                        startActivity(intent1);
                        return;
                }
            }};

        btn1.setOnClickListener(buttonListener);
        btn2.setOnClickListener(buttonListener);
        btn4.setOnClickListener(buttonListener);

    }
}
