package com.imooc.demo.app.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.imooc.demo.R;
import com.imooc.demo.model.ChatMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * listview控件
 * 数据 item布局
 * adapter
 */
public class ChatActivity2 extends AppCompatActivity {

    private ListView mChatListView;
    private List<ChatMsg> chatMsgList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        mChatListView = (ListView) findViewById(R.id.chat_list_view);

        //列表 元素 加入列表
        prepareData();
    }

    private void prepareData() {
        chatMsgList = new ArrayList<>();
        ChatMsg ChatMsg = new ChatMsg(1,2,"刘小明","","你好吗","8:20","",true);
        ChatMsg ChatMsg2 = new ChatMsg(2,1,"小军","","我很好","8:21","",false);
        ChatMsg ChatMsg3 = new ChatMsg(1,2,"刘小明","","今天天气怎么样","8:22","",true);
        ChatMsg ChatMsg4 = new ChatMsg(2,1,"小军","","热成狗了","8:23","",false);
        chatMsgList.add(ChatMsg);
        chatMsgList.add(ChatMsg2);
        chatMsgList.add(ChatMsg3);
        chatMsgList.add(ChatMsg4);
    }
}
