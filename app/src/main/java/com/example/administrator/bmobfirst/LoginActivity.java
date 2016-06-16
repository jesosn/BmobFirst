package com.example.administrator.bmobfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    EditText editText_username,editText_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);
        Bmob.initialize(this, "2c7868d703be32aadae7fcfa39821c5c");

    }


    public void registerClick(View view) {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    public void loginClick(View view) {
        String username = editText_username.getText().toString();
        String password = editText_password.getText().toString();
        final BmobUser bu2 = new BmobUser();
        bu2.setUsername(username);
        bu2.setPassword(password);
        bu2.login(this, new SaveListener() {
            @Override
            public void onSuccess() {
                //User.class泛型，自己创建的user对象
                User myuser = BmobUser.getCurrentUser(LoginActivity.this,User.class);
//                toast("登录成功:");
                if (myuser.getEmailVerified()) {
                    Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,NoteListActivity.class);
                    intent.putExtra("user",myuser);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this,"用户未激活",Toast.LENGTH_LONG).show();
                }


                //通过BmobUser user = BmobUser.getCurrentUser(context)获取登录成功后的本地用户信息
                //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(context,MyUser.class)获取自定义用户信息
            }
            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(LoginActivity.this,"登陆失败"+msg,Toast.LENGTH_LONG).show();
            }
        });
    }

}
