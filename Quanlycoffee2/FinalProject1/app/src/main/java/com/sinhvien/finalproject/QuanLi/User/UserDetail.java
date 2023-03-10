package com.sinhvien.finalproject.QuanLi.User;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.sinhvien.finalproject.DBHelper;
import com.sinhvien.finalproject.R;

public class UserDetail extends AppCompatActivity {
    TextView txtInfor;
    DBHelper dbHelper;
    String infor = "";
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_user_detail);
        dbHelper = new DBHelper(this);
        txtInfor = findViewById(R.id.txtInforUser);
        getUser();

    }
    public void getUser(){
        Intent getData = getIntent();
        id = getData.getStringExtra("id");
        dbHelper= new DBHelper(this);
        String Query = "SELECT * FROM User WHERE Id = "+id;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query,null);


        while (cursor.moveToNext()){
            String Id = id;
            String Dep = cursor.getString(cursor.getColumnIndex("DepName"));
            String user = cursor.getString(cursor.getColumnIndex("user"));
            String pass = cursor.getString(cursor.getColumnIndex("pass"));
            String ten = cursor.getString(cursor.getColumnIndex("ten"));
            String sdt = cursor.getString(cursor.getColumnIndex("sdt"));
            String diachi = cursor.getString(cursor.getColumnIndex("diachi"));
            infor = infor + "Id :  "+Id+"\nDepartment :  "+Dep+"\nUsername :  "+user+"\nPassword :  "+pass+"\nTên :  "+ten+"\nSố Điện thoại :  "+sdt+"\nĐịa Chỉ :  "+diachi;
        }
        txtInfor.setText(infor);
        db.close();
        dbHelper.close();
    }
}