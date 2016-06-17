package com.example.administrator.bmobfirst;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

public class Register extends AppCompatActivity {
    private EditText editText_reg_username,editText_reg_passwd,editText_reg_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editText_reg_username = (EditText) findViewById(R.id.editText_reg_username);
        editText_reg_passwd = (EditText) findViewById(R.id.editText_reg_passwd);
        editText_reg_email = (EditText) findViewById(R.id.editText_reg_email);

        Bmob.initialize(this, "2c7868d703be32aadae7fcfa39821c5c");
    }


    public void buttonRegYesClick(View view) {
        final String username = editText_reg_username.getText().toString();
        final String password = editText_reg_passwd.getText().toString();
        final String email = editText_reg_email.getText().toString();
//        Toast.makeText(this,username+password+email,Toast.LENGTH_LONG).show();
//        String icon = editText_icon.getText().toString();
//        获取图片路劲

//        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/1.jpg";
//        final BmobFile bmobFile = new BmobFile(new File(path));
//        bmobFile.upload(this, new UploadFileListener() {
//            @Override
//            public void onSuccess() {
                //上传成功，把用户的信息存到用户表里面
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
//                user.setIcon(bmobFile);
                //注册
                user.signUp(Register.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                        builder.setTitle("注册成功");
                        builder.setMessage("请牢记用户名和密码并去邮箱激活当前用户");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        builder.show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(Register.this, "注册失败", Toast.LENGTH_LONG);
                    }
                });
//            }

//            @Override
//            public void onFailure(int i, String s) {
//
//            }
//        });

    }
}
