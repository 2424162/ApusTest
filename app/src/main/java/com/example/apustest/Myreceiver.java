package com.example.apustest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Myreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("book",context.getPackageName());
        String str =intent.getStringExtra("haha");
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();

    }
}
