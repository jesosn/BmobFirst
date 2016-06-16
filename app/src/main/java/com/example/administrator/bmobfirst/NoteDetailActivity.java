package com.example.administrator.bmobfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import cn.bmob.v3.listener.UpdateListener;

public class NoteDetailActivity extends AppCompatActivity {

    private EditText editText_content;
    private String objectid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        editText_content = (EditText) findViewById(R.id.editText_detail);
        objectid = getIntent().getStringExtra("objectid");
        editText_content.setText(getIntent().getStringExtra("content"));


    }

    //按返回应该自动保存
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //执行update的操作

            String content = editText_content.getText().toString();
            if (!TextUtils.isEmpty(content)) {
                Note note = new Note();
                note.setContent(content);
                note.update(this, objectid, new UpdateListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });
            }

        }

        return super.onKeyDown(keyCode, event);
    }
}
