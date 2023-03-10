package com.sinhvien.finalproject.QuanLi.Department;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.sinhvien.finalproject.DBHelper;
import com.sinhvien.finalproject.R;

public class List extends AppCompatActivity {
    Button btnAdd;
    ListView lvDisplay;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_dep_list);
        btnAdd = findViewById(R.id.btnAdd);
        lvDisplay = findViewById(R.id.lvDisplay);
        dbHelper = new DBHelper(List.this);

        ArrayAdapter arrayAdapter;
        addDeptoList(dbHelper);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(List.this,Add.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addDeptoList(DBHelper dbHelper) {
        ArrayAdapter arrayAdapter;
        arrayAdapter = new ArrayAdapter<Department>(List.this, android.R.layout.simple_list_item_1,dbHelper.getAllDepartment());
        lvDisplay.setAdapter(arrayAdapter);
    }
}