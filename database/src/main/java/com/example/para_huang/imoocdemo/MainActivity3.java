package com.example.para_huang.imoocdemo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity3 extends Activity {

    private EditText nameEdt , ageEdt , idEdt;
    private RadioGroup genderGp;
    private ListView stuList;
    private RadioButton malerb;
    private String genderStr = "男";
    private StudentDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new StudentDao(this);

        nameEdt = (EditText) findViewById(R.id.name_edt);
        ageEdt = (EditText) findViewById(R.id.age_edt);
        idEdt = (EditText) findViewById(R.id.id_edt);
        malerb = (RadioButton) findViewById(R.id.male);

        genderGp = (RadioGroup) findViewById(R.id.gender_gp);
        genderGp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.male){
                    //“男”
                    genderStr = "男";
                }else{
                    //"女"
                    genderStr = "女";
                }
            }
        });

        stuList = (ListView) findViewById(R.id.stu_list);
    }

    public void operate(View v){

        String nameStr = nameEdt.getText().toString();
        String ageStr = ageEdt.getText().toString();
        String idStr = idEdt.getText().toString();
        switch (v.getId()){
            case R.id.insert_btn:
                Student stu = new Student(nameStr,Integer.parseInt(ageStr),genderStr);
                dao.addStudent(stu);
                Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.select_btn:
                String key="",value="";
                if(!nameStr.equals("")){
                    value = nameStr;
                    key = "name";
                }else if(!ageStr.equals("")){
                    value = ageStr;
                    key = "age";
                }else if(!idStr.equals("")){
                    value = idStr;
                    key = "_id";
                }
                Cursor c;
                if(key.equals("")){
                   c = dao.getStudent();
                }else {
                   c = dao.getStudent(key, value);
                }

                SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                        this, R.layout.item,c,
                        new String[]{"_id","name","age","gender"},
                        new int[]{R.id.id_item,R.id.name_item,R.id.age_item,R.id.gender_item});
                stuList.setAdapter(adapter); /**/
                break;
            case R.id.delete_btn:

                String[] params = getParams(nameStr,ageStr,idStr);

                dao.deleteStudent(params[0],params[1]);
                Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_btn:
                Student stu2 = new Student(Integer.parseInt(idStr),nameStr,Integer.parseInt(ageStr),genderStr);
                dao.updateStudent(stu2);
                Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
            break;
        }
        nameEdt.setText("");
        ageEdt.setText("");
        idEdt.setText("");
        malerb.setChecked(true);
    }

    public String[] getParams(String nameStr,String ageStr,String idStr){
        String[] params = new String[2];
        if(!nameStr.equals("")){
            params[1] = nameStr;
            params[0] = "name";
        }else if(!ageStr.equals("")){
            params[1] = ageStr;
            params[0] = "age";
        }else if(!idStr.equals("")){
            params[1] = idStr;
            params[0] = "_id";
        }
        return params;
    }
}
