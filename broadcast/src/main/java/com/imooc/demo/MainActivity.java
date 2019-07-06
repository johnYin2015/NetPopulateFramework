package com.imooc.demo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MY_ACTION = "com.imooc.demo.afdsabfdaslj";
    public static final String BROADCAST_CONTENT = "broadcast_content";
    private ImoocBroadcastReceiver mBroadcastReceiver;
    private EditText mInputEditText;
    private Button mSendBroadcastButton;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 用包名做title
        setTitle(getPackageName());

        mInputEditText = findViewById(R.id.inputEditText);
        mSendBroadcastButton = findViewById(R.id.sendBroadcastButton);
        mResultTextView = findViewById(R.id.resultTextView);



        // 新建广播接收器
        mBroadcastReceiver = new ImoocBroadcastReceiver(mResultTextView);

        // 注册广播接收器

        // 为广播接收器添加Action
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction(MY_ACTION);

        // 注册广播接收器
        registerReceiver(mBroadcastReceiver, intentFilter);


        mSendBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 新建广播
                Intent intent = new Intent(MY_ACTION);
                // 放入广播要携带的数据
                intent.putExtra(BROADCAST_CONTENT, mInputEditText.getText().toString());
                sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册广播接收器，不然会导致内存泄露
        if(mBroadcastReceiver != null){
            unregisterReceiver(mBroadcastReceiver);
        }
    }
}
