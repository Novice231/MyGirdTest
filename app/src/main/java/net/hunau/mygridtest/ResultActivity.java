package net.hunau.mygridtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

import android.util.Log;

public class ResultActivity extends Activity {
    final static String TAG = "ResultActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button btn3=(Button)findViewById(R.id.btn3);

        TextView displayView=(TextView)findViewById(R.id.display);

        final DBAdapter dbAdepter = new DBAdapter(this);
        dbAdepter.open();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Log.i(TAG,"跳转成功");
        displayView.setText(String.format("恭喜你登陆成功！\n用户名："+bundle.getString("name")+"\n 密码："+ bundle.getString("pwd") +" \n 性别："+bundle.getString("sexy")+" \n 是否有效："+bundle.getBoolean("isused")));



        btn3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent( ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
