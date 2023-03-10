package com.sinhvien.finalproject.QuanLi.Drink;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinhvien.finalproject.DBHelper;
import com.sinhvien.finalproject.R;

public class DrinkDetail extends AppCompatActivity {

    TextView txtInfor;
    ImageView ivImage;
    DBHelper dbHelper;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        txtInfor = findViewById(R.id.txtInfor);
        ivImage = findViewById(R.id.ivImage);
        getInfor();
        getImage();
    }

    private void getImage() {
        Intent getdata = getIntent();
        id= getdata.getStringExtra("id");
        dbHelper = new DBHelper(this);
        String Query = "SELECT * FROM Drink WHERE Id = "+id;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query,null);
        while (cursor.moveToNext()){
            byte[] img = cursor.getBlob(3);
            Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
            ivImage.setImageBitmap(bitmap);
        }
    }

    public void getInfor() {
        Intent getData = getIntent();
        id = getData.getStringExtra("id");
        dbHelper = new DBHelper(this);
        String Query = "SELECT * FROM Drink WHERE Id = "+ id;
        SQLiteDatabase db =dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query,null);
        String infor ="";
        while (cursor.moveToNext()){
            String Id = id;
            String ten = cursor.getString(cursor.getColumnIndex("ten"));
            String type = cursor.getString(cursor.getColumnIndex("typename"));
            long money = cursor.getLong(cursor.getColumnIndex("money"));
            long point = cursor.getLong(cursor.getColumnIndex("point"));
            infor = infor + "Id :   "+Id+"\nTên :   "+ ten + "\nLoại :   "+type+"\nTiền :   "+ money +"\nĐiểm :   "+point;
        }
        txtInfor.setText(infor);
        db.close();
        dbHelper.close();
    }
}