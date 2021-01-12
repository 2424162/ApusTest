package com.example.apustest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class SimpleService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("yydsyy","执行oncreate方法");
        jumpWechatMiniProgram();

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
}
