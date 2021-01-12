package com.example.apustest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import 	androidx.activity.result.contract.ActivityResultContract;
import androidx.core.view.KeyEventDispatcher;

import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private Button btWrite;
    private int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;
    public String tag="book";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.view1);
        //initView();
        //initEvent();
//        Intent intent = new Intent();
//        Intent intent1 = new Intent();
//        intent1.setAction("com.jjff");
//        intent1.putExtra("haha","lala");
        //intent1.setComponent(new ComponentName(this,Myreceiver.class));
        //sendBroadcast(intent1);
        //systemRedio();
        Context mContext = this;
        //jumpWechatMiniProgram();



        Log.d(tag,"发完广播");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 通知渠道的id
            String id = " ";
            // 用户可以看到的通知渠道的名字.
            CharSequence name = " ";
            // 用户可以看到的通知渠道的描述
            //String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            //注意Name和description不能为null或者""
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // 配置通知渠道的属性
            mChannel.setDescription("haha");
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(false);
            mChannel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(false);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //最后在notificationmanager中创建该通知渠道
            notificationManager.createNotificationChannel(mChannel);
            builder.setChannelId(id);
        }
        builder.setContentInfo("补充内容");
        builder.setContentText("通知标题");
        builder.setContentTitle("通知内容");
        builder.setSmallIcon(R.drawable.lala);
        builder.setTicker("新消息");
        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        Intent intent = new Intent(this,SimpleService.class);
        Log.d("yydsyy",intent.toString());

        //intent.setAction("android.intent.action.VIEW");
        //intent.setData(Uri.parse("taobao://" + "shop.m.taobao.com/shop/shop_index.htm?shop_id=458028920"));

        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        notificationManager.notify(1, notification);
//        Intent mResultIntent  = new Intent();
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        createNotification(textView);



    }

    private void jumpWechatMiniProgram(){

            String appId;
            if (BuildConfig.DEBUG) {
                appId = "wxd930ea5d5a258f4f";
            }else{
                appId = "wxd930ea5d5a258f4f";
            }
            IWXAPI api = WXAPIFactory.createWXAPI(this, appId);
            //                    api.registerApp(appId);
            WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
            req.userName = "gh_22d5ffc592b1"; // 填小程序原始id
			//req.path = "/yzy/game/dafeiji?id=22";                  //拉起小程序页面的可带参路径，不填默认拉起小程序首页
				req.miniprogramType = WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_TEST;// 可选打开 开发版，体验版和正式版
            api.sendReq(req);

    }
    public static void gotoShop(Activity activity, String url) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(url));
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createNotification(View view){
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        NotificationManager  manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("通知的标题")
                .setContentText("通知的正文内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)//只能使用纯alpha图层的图片进行设置
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.haha))//下拉系统状态栏时，就可以看到大图片
                .setContentIntent(pi)//设置一个延迟意图，点击可执行
                .setAutoCancel(true)//点击后，自动消失
                .build();
        manager.notify(1,notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannela(String id, String name, int importance, String desc) {
        //if (mNotificationManager.getNotificationChannel(id) != null) return;

        NotificationChannel notificationChannel = new NotificationChannel(id, name, importance);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);

        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notificationChannel.setShowBadge(true);
        notificationChannel.setBypassDnd(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400});
        notificationChannel.setDescription(desc);

        //mNotificationManager.createNotificationChannel(notificationChannel);
    }

    public void systemRedio(){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SCREEN_OFF");
        sendBroadcast(intent);
        Log.d(tag,"发完广播");
    }

    private void initEvent() {
        btWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PermissionChecker.checkSelfPermission()

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    Log.d(tag,"没有直接权限");

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                    //requestMutltplePermissions
                } else {
                    btWrite.setText("授权成功123");
                }
            }
        });
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        for(String str:permissions){
//            Log.d(tag,str);
//        }
//
//
//        for(int in:grantResults){
//        Log.d(tag,in+"");
//        }
//
//        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission Granted
//                Toast.makeText(getApplicationContext(),"授权成功",Toast.LENGTH_SHORT).show();
//                btWrite.setText("授权成功");
//            } else {
//                // Permission Denied
//                Toast.makeText(getApplicationContext(),"授权拒绝",Toast.LENGTH_SHORT).show();
//                btWrite.setText("授权拒绝");
//            }
//        }
//    }

    private void initView() {

        btWrite = (Button) findViewById(R.id.write);
    }

    public void onDestroy() {
        super.onDestroy();
    }
}