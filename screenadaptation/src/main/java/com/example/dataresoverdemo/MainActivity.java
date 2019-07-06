package com.example.dataresoverdemo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ContentResolver resolver;
    private EditText nameEdt , ageEdt , idEdt;
    private RadioGroup rg;
    private ListView stuList;
    private String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取ContentResovler对象
        resolver = getContentResolver();
//        resolver.query();
           // resolver.insert()
//            resolver.delete()
//            resolver.update()

        nameEdt = (EditText) findViewById(R.id.name_edt);
        ageEdt = (EditText) findViewById(R.id.age_edt);
        idEdt = (EditText) findViewById(R.id.id_edt);

        stuList= (ListView) findViewById(R.id.stu_list);

        rg = (RadioGroup) findViewById(R.id.gender_rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.male){
                    gender = "男";
                }else{
                    gender = "女";
                }
            }
        });
    }

    public void operate(View v ){
        Uri uri = Uri.parse("content://com.imooc.myprovider");

        String name = nameEdt.getText().toString();
        String age = ageEdt.getText().toString();
        String _id = idEdt.getText().toString();
        switch (v.getId()){
            case R.id.insert_btn:
                //参数1：URI（Uniform Resource Identifier,同一资源定位符）对象，content://authorities[/path]
                //参数2：
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("age",age);
                values.put("gender",gender);
                Uri uri2 = resolver.insert(uri,values);
                long id = ContentUris.parseId(uri2);
                Toast.makeText(this,"添加成功，新学生的学号是：" + id , Toast.LENGTH_SHORT).show();
                break;
            case R.id.query_btn:
                //查询所有。
                //参数2：查询列，为null代表查询所有
                Cursor c = resolver.query(uri,null,null,null,null);
                //参数2：每一个学员信息对象所显示的样式布局
                //参数3：数据源
                //参数4：查询结果中所有列的列名
                //参数5：数据未来所要加载到的对应控件的id数组
                //
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this ,
                        R.layout.item,
                        c,
                        new String[]{"_id","name","age","gender"},
                        new int[]{R.id.id_txt,R.id.name_txt,R.id.age_txt,R.id.gender_txt},
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

                stuList.setAdapter(adapter);
                break;
            case R.id.delete_btn:
                int result = resolver.delete(uri , "_id=?",new String[]{_id});
                if(result > 0){
                    Toast.makeText(this , "删除成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "删除失败",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.update_btn:

                ContentValues values2 = new ContentValues();
                values2.put("name",name);
                values2.put("age",age);
                values2.put("gender",gender);
                int result2 = resolver.update(uri ,values2,"_id=?", new String[]{_id} );
                if(result2 > 0){
                    Toast.makeText(this , "修改成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mather_btn:
                resolver.delete(Uri.parse("content://com.imooc.myprovider/helloworld"),null,null);
                resolver.delete(Uri.parse("content://com.imooc.myprovider/helloworld/abc"),null,null);
                resolver.delete(Uri.parse("content://com.imooc.myprovider/helloworld/123"),null,null);
                resolver.delete(Uri.parse("content://com.imooc.myprovider/helloworld/090"),null,null);
                resolver.delete(Uri.parse("content://com.imooc.myprovider/helloworld/89ii"),null,null);
                resolver.delete(Uri.parse("content://com.imooc.myprovider/nihaoshijie/ab90"),null,null);
                break;
            case R.id.uri_btn:
                Uri uri1 = resolver.insert(Uri.parse("content://com.imooc.myprovider/whatever?name=张三&age=23&gender=男"),
                        new ContentValues());
                long id2 = ContentUris.parseId(uri1);
                Toast.makeText(this , "添加成功，也意味着uri解析成功，新学员学号是：" + id2 , Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
