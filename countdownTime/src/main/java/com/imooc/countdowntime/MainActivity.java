package com.imooc.countdowntime;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    /**
     * 倒计时标记handle code
     */
    public  static  final  int COUNTDOWN_TIME_CODE = 100001;
    /**
     * 倒计时间隔
     */
    public  static  final  int  DELAY_MILLIS = 1000;
    /**
     * 倒计时最大值
     */
    public  static  final  int  MAX_COUNT = 10;


    private TextView mCountdownTimeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //得到控件
        mCountdownTimeTextView= (TextView)findViewById(R.id.countdownTimeTextView);

        //创建了一个handler
        CountdownTimeHandler handler = new CountdownTimeHandler(this);

        //新建了一个message
        Message message = Message.obtain();
        message.what = COUNTDOWN_TIME_CODE;
        message.arg1 = MAX_COUNT;

        //第一次发送这个message
        handler.sendMessageDelayed(message,DELAY_MILLIS);

    }

    public  static  class  CountdownTimeHandler extends Handler{
          static  final  int  MIN_COUNT = 0;
        final WeakReference<MainActivity> mWeakReference;

         CountdownTimeHandler(MainActivity activity){
            mWeakReference = new WeakReference<> (activity );
//            WeakReference mWeakReference = new WeakReference(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity =mWeakReference.get();

            switch (msg.what){
                case COUNTDOWN_TIME_CODE:
                    int value = msg.arg1;
                    activity.mCountdownTimeTextView.setText(String.valueOf(value --));

                     //循环发的消息控制
                    if (value >= MIN_COUNT) {
                        Message message = Message.obtain();
                        message.what = COUNTDOWN_TIME_CODE;
                        message.arg1 = value;
                        sendMessageDelayed(message, DELAY_MILLIS);
                    }

                    break;
            }
        }
    }
}
