package com.example.administrator.bmobfirst;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.ResetPasswordByEmailListener;
import cn.bmob.v3.listener.UpdateListener;

public class ChangePassword extends AppCompatActivity {
    private EditText email_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Bmob.initialize(this, "2c7868d703be32aadae7fcfa39821c5c");
        email_address = (EditText) findViewById(R.id.email_address);


    }

    public void resetPassword(View view) {
        final String email = email_address.getText().toString();
        BmobUser.resetPasswordByEmail(ChangePassword.this, email, new ResetPasswordByEmailListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
//                toast("重置密码请求成功，请到" + email + "邮箱进行密码重置操作");
                AlertDialog.Builder builder = new AlertDialog.Builder(ChangePassword.this);
                builder.setTitle("邮箱密码重置");
                builder.setMessage("请到邮箱重置密码");
                builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
            }
            @Override
            public void onFailure(int code, String e) {
                // TODO Auto-generated method stub
//                toast("重置密码失败:" + e);
            }
        });
    }
}

