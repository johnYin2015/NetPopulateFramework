package com.imooc.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by renkangke .
 */
public class ImoocBroadcastReceiver extends BroadcastReceiver {

    TextView mTextView;
    public ImoocBroadcastReceiver() {
    }

    public ImoocBroadcastReceiver(TextView textView) {
        mTextView = textView;
    }

    private static final String TAG = "ImoocBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        // 接收广播
        if(intent != null){
            // 接收到的是什么广播
            String action  = intent.getAction();
            Log.d(TAG, "onReceive: " + action);

            // 判断是什么广播（是不是我们自己发送的自定义广播）
            if(TextUtils.equals(action, MainActivity.MY_ACTION)){
                // 获取广播携带的内容， 可自定义的数据
                String content = intent.getStringExtra(MainActivity.BROADCAST_CONTENT);
                if(mTextView != null){
                    mTextView.setText("接收到的action是："+ action + "\n接收到的内容是：\n" + content);
                }
            }
        }
    }
}
