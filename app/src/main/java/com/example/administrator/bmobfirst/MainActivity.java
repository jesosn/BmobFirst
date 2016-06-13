package com.example.administrator.bmobfirst;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        第一：默认初始化
        Bmob.initialize(this, "2c7868d703be32aadae7fcfa39821c5c");

//        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
//        BmobConfig config =new BmobConfig.Builder(this)
//        //设置appkey
//        .setApplicationId("2c7868d703be32aadae7fcfa39821c5c")
//        //请求超时时间（单位为秒）：默认15s
//        .setConnectTimeout(30)
//        //文件分片上传时每片的大小（单位字节），默认512*1024
//        .setUploadBlockSize(1024*1024)
//        //文件的过期时间(单位为秒)：默认1800s
//        .setFileExpiration(2500)
//        .build();
//        Bmob.initialize(config);
    }

    public void addClick(View view) {
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this,"添加失败",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void updateClick(View view) {
        Person p2 = new Person();
        p2.setAddress("北京朝阳");
        p2.update(this, "adbe01f1ba", new UpdateListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deleteClick(View view) {
        Person p2 = new Person();
        p2.setObjectId("adbe01f1ba");
        p2.delete(this, new DeleteListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void queryClick(View view) {
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject(this, "adbe01f1ba", new GetListener<Person>() {
            @Override
            public void onSuccess(Person object) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "查询成功"+object.getName()+object.getAddress(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "查询失败", Toast.LENGTH_LONG).show();
            }
        });
    }

}
