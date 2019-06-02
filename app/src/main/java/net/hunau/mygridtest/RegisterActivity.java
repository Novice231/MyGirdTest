package net.hunau.mygridtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    final static String TAG = "RegisterActivity";

    private EditText et1,et2;
    private Button btn5,btn6;
    private RadioButton rb1,rb2,rb3,rb4;

    String name;
    String pwd;
    String sexy;
    Boolean isused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);

        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);

        rb1 = (RadioButton) findViewById(R.id.RadioButton01);
        rb2 = (RadioButton) findViewById(R.id.RadioButton02);
        rb3 = (RadioButton) findViewById(R.id.RadioButton03);
        rb4 = (RadioButton) findViewById(R.id.RadioButton04);

        final DBAdapter dbAdepter = new DBAdapter(this);
        dbAdepter.open();

        //按钮注册到同一个监听器
        Button.OnClickListener buttonListener = new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.btn5:
                        //注册
                        name=et1.getText().toString();
                        pwd=et2.getText().toString();
                        if (rb1.isChecked()) {
                            sexy=rb1.getText().toString();
                        } else {
                            sexy=rb2.getText().toString();
                        }
                        if (rb3.isChecked()) {
                            isused=true;
                        } else {
                            isused=false;
                        }

                        if("".equals(name)){
                            Toast.makeText(getApplicationContext(),"用户名不能为空", Toast.LENGTH_SHORT).show();
                        }else if("".equals(pwd)){
                            Toast.makeText(getApplicationContext(),"密码不能为空", Toast.LENGTH_SHORT).show();
                        }else if("".equals(sexy)) {
                            Toast.makeText(getApplicationContext(),"请选择性别", Toast.LENGTH_SHORT).show();
                        }else if("".equals(isused)) {
                            Toast.makeText(getApplicationContext(),"请选择是否有效", Toast.LENGTH_SHORT).show();
                        }else{
                                User user =new User();
                                user.setName(name);
                                user.setPwd(pwd);
                                user.setSexy(sexy);
                                user.setIsused(isused);
                                if(dbAdepter.insert(user)==-1) {
                                    Toast.makeText(getApplicationContext(),"注册失败！", Toast.LENGTH_SHORT).show();
                                    Log.i(TAG,"注册失败！");
                                }else{
                                    Toast.makeText(getApplicationContext(),"注册成功！", Toast.LENGTH_SHORT).show();
                                    Log.i(TAG,"注册成功！");
                                }
                        }
                        return;
                    case R.id.btn6:
                        //返回
                        Intent intent = new Intent( RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                }
            }};

        btn5.setOnClickListener(buttonListener);
        btn6.setOnClickListener(buttonListener);
    }
}
