package com.nanbei.sports;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

public class MainActivity extends Activity {

    private Button btMusic;
    private Button btMap;
    private Button btDynamic;
    private Button btMusicDemo;
    private Button btStaticDemo;
    private Button btNotification;
    private Button btSharePictrue;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.context = context;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMusic = (Button) findViewById(R.id.music);
        btMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicWebActivity.class);
                startActivity(intent);
            }
        });

        btMap = (Button) findViewById(R.id.map);
        btMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        btDynamic = (Button) findViewById(R.id.Dynamic);
        btDynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DynamicDemo.class);
                startActivity(intent);
            }
        });

        btMusicDemo = (Button) findViewById(R.id.musicdemo);
        btMusicDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicDemo.class);
                startActivity(intent);
            }
        });

        btStaticDemo = (Button) findViewById(R.id.staticdemo);
        btStaticDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StaticDemo.class);
                startActivity(intent);
            }
        });

        btNotification = (Button) findViewById(R.id.notification);
        btNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotificationDemo.class);
                startActivity(intent);
            }
        });

        btSharePictrue = (Button) findViewById(R.id.buttonsharepictrue);
        btSharePictrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showLocationShare(2);
                File file = null;
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator;
                file = new File(path);
                sharePictrue(file);
            }
        });
    }

    private void sharePictrue(File file){
        Intent shareIntent = new Intent();
        //解决android.os.FileUriExposedException问题

        //由文件得到uri
        Uri imageUri;
        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // "net.csdn.blog.ruancoder.fileprovider"即是在清单文件中配置的authorities
            imageUri = FileProvider.getUriForFile(context, "net.csdn.blog.ruancoder.fileprovider", file);
            // 给目标应用一个临时授权
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            imageUri = Uri.fromFile(file);
        }
        shareIntent.setDataAndType(imageUri, "application/vnd.android.package-archive");

        //ComponentName comp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.tools.ShareToTimeLineUI");
        //intent.setComponent(comp);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, "分享到"));
    }
}

