//package com.example.administrator.bmobfirst;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.media.Image;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.ref.WeakReference;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import cn.bmob.v3.Bmob;
//import cn.bmob.v3.datatype.BmobFile;
//
//public class ShowDisplay extends AppCompatActivity {
//    TextView hello_textview;
//    ImageView imageview_display;
//    private static final int LOAD1 = 0x1;
//    private MyHandler handler = new MyHandler(this);
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_display);
//        Bmob.initialize(this, "2c7868d703be32aadae7fcfa39821c5c");
//        hello_textview = (TextView) findViewById(R.id.hello_textview);
//        imageview_display = (ImageView) findViewById(R.id.imageview_display);
//        Intent intent = getIntent();
//        User user = (User) intent.getSerializableExtra("user");
//        hello_textview.setText(user.getUsername()+user.getEmail()+user.getUpdatedAt());
//        BmobFile bf = user.getIcon();
//        String imageURL = bf.getUrl();
//        showImageFromBaidu(imageURL);
//    }
//
//
//    public void showImageFromBaidu(final String imageURL) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL(imageURL);
//                    InputStream in = url.openStream();
//                    Bitmap bitmap = BitmapFactory.decodeStream(in);
//                    Message msg = handler.obtainMessage(LOAD1,bitmap);
//                    handler.sendMessage(msg);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//    private static class MyHandler extends Handler {
//        WeakReference<ShowDisplay> activityWeakReference;
//        public MyHandler(ShowDisplay activity) {
//            activityWeakReference = new WeakReference<ShowDisplay>(activity);
//        }
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            ShowDisplay showDisplayActivity = activityWeakReference.get();
//            if (showDisplayActivity != null) {
//                showDisplayActivity.imageview_display.setImageBitmap((Bitmap) msg.obj);
//            }
//        }
//    }
//
//}
