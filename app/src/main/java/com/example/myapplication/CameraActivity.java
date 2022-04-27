package com.example.myapplication;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class CameraActivity extends AppCompatActivity {

    private ImageView iv;
    private VideoView videoView;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameral);
        iv = (ImageView) findViewById(R.id.iv);
        videoView = (VideoView) findViewById(R.id.video);

    }



    public void doClick(View view) {
        Intent intent = new Intent(CameraActivity.this, resultactivity.class);

        switch (view.getId()) {
            case R.id.btn_pick_image:
                // TODO 选取系统相册图片
                doPickImageFromSystem();
                break;
            case R.id.btn_open_camera_for_video:
                // TODO 打开相机录像
                doOpenCameraForVideo();
                break;
            case R.id.btn_uploading:
                try {
                    Thread.sleep(1000);
                    try {
                        uploadvideo(getPackageCodePath());
                    } catch (IOException e) {
                        Toast.makeText(CameraActivity.this,"上传失败",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    Toast.makeText(CameraActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                    Thread.sleep(10000);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                try {
//                    Thread.sleep(10000);
//                    startActivity(intent);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }

    }


    private void doPickImageFromSystem() {
        /**

         * 参数一:打开系统相册的ACTION

         * 参数二:返回数据的方式(从系统相册的数据库获取)

         */

        CameraUtils.pickImageFromSystem(this);
    }



    private void doOpenCameraForImage() {

        CameraUtils.openCameraForImage(this);

    }



    private void doOpenCameraForVideo() {

        CameraUtils.openCameraForVideo(this);

    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //当结果码为RESULT_OK时,表示用户有效
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CameraUtils.RequestCode.FLAG_REQUEST_SYSTEM_IMAGE:
                    String path = CameraUtils.getImagePathFromSystem(this, data);
                    if (path != null) {
                        System.out.println("选取到图片的回参"+path);
                        iv.setImageBitmap(BitmapFactory.decodeFile(path));   //这里在报open failed: EACCES (Permission denied)   已加权限和            android:requestLegacyExternalStorage="true"    还要获取动态权限  见上一步
                    }
                    break;

                case CameraUtils.RequestCode.FLAG_REQUEST_CAMERA_IMAGE:
                    //TODO 处理从相机返回的图片数据
                    if (data != null) {
                        Bitmap bm = data.getParcelableExtra("data");
                        iv.setImageBitmap(bm);
                    } else {
                        Bitmap bm = BitmapFactory.decodeFile(CameraUtils.CAMERA_IMAGE);
                        iv.setImageBitmap(bm);
                    }
                    break;

                case CameraUtils.RequestCode.FLAG_REQUEST_CAMERA_VIDEO:
                    //TODO 处理从相机返回的视频数据
                    videoView.setVideoURI(data.getData());
                    videoView.start();
                    break;

            }

        }

    }

    private void uploadvideo(String path)throws IOException {
        //1、读取本地文件
        FileInputStream fis = new FileInputStream(path);

        //2、创建一个客户端Socket对象，构造方法中绑定服务器的IP地址和端口号
        Socket socket = new Socket("123.57.49.203",7777);

        //3、使用Socket中的方法getOutputStream，获取网络字节输出流OutputStream
        OutputStream os = socket.getOutputStream();
        //4、使用本地字节输入流FileInoutStream对象中的方法read，读取本地文件
        int len;
        byte [] bytes = new byte [1024];
        while ((len = fis.read(bytes)) != -1){
            //5、使用网络字节输出流OutputStream对象中的方法write，把读取到的文件上传到服务器端
            os.write(bytes,0,len);
        }
        // 6、使用Socket中的方法getInputStream,获取网络字节输入流InputSteam对象

        socket.close();
    }

}
