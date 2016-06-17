package com.example.administrator.bmobfirst;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 16/06/2016.
 */
public class Note extends BmobObject {
    String content;
    String user_email;

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
