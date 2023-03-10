package com.sinhvien.finalproject.QuanLi.Drink;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sinhvien.finalproject.DBHelper;
import com.sinhvien.finalproject.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Add extends AppCompatActivity {

    EditText edName,edMoney,edPoint;
    Spinner spType;
    ImageView ivImage;
    Button btnAdd,btnImage;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        edName= findViewById(R.id.edName);
        edMoney = findViewById(R.id.edMoney);
        edPoint = findViewById(R.id.edPoint);
        spType = findViewById(R.id.spType);
        ivImage = findViewById(R.id.ivImage);
        btnAdd = findViewById(R.id.btnAdd);
        btnImage = findViewById(R.id.btnImage);

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Coffee");
        arrayList.add("Food");
        arrayList.add("Juice");
        arrayList.add("Smoothie");
        arrayList.add("Tea");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,arrayList);
        spType.setAdapter(arrayAdapter);

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        dbHelper = new DBHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivImage.invalidate();
                BitmapDrawable drawable = (BitmapDrawable) ivImage.getDrawable();
                Bitmap bitmap;
                Drink drink = new Drink();
                try{
                    drink = new Drink(-1,
                            edName.getText().toString(),
                            spType.getSelectedItem().toString(),
                            bitmap = drawable.getBitmap(),
                            Long.parseLong(edMoney.getText().toString()),
                            Long.parseLong(edPoint.getText().toString()));

                }catch (Exception e){
                    Log.d("Error click",e.toString());
                }
                dbHelper = new DBHelper(Add.this);
                dbHelper.addDrink(drink);
                Intent intent = new Intent(Add.this,List.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
    public void onActivityResult(int requestcode, int resultCode, Intent data) {
        super.onActivityResult(requestcode, resultCode, data);
        Context context = getApplicationContext();
        if (requestcode == 1&& resultCode == Activity.RESULT_OK){
            if (data == null){
                return;
            }
            Uri uri = data.getData();

            InputStream inputStream;
            try {
                inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivImage.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
                Toast.makeText(this,"Unable to open",Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(context,uri.getPath(),Toast.LENGTH_SHORT).show();
        }
    }
}