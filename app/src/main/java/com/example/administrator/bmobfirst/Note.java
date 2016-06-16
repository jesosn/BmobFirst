package com.example.administrator.bmobfirst;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 16/06/2016.
 */
public class Note extends BmobObject {
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
