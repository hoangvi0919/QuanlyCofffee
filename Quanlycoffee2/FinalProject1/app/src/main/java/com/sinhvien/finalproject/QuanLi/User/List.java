package com.sinhvien.finalproject.QuanLi.User;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sinhvien.finalproject.DBHelper;
import com.sinhvien.finalproject.R;

public class List extends AppCompatActivity {
    Button btnAdd;
    ListView lvDisplay;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnAdd = findViewById(R.id.btnAddUser);
        lvDisplay = findViewById(R.id.lvDisplayUs);
        dbHelper = new DBHelper(List.this);
        ArrayAdapter arrayAdapter;
        addUsertoList(dbHelper);


        lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long i = -1;
                i = parent.getItemIdAtPosition(position)+1;
                String text = ""+i;
                if (i>0){
                    Intent intent = new Intent(List.this,UserDetail.class);
                    intent.putExtra("id",text);
                    startActivity(intent);
                }
                else
                    Toast.makeText(List.this,"No id found",Toast.LENGTH_SHORT).show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(List.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void addUsertoList(DBHelper dbHelper) {
        ArrayAdapter arrayAdapter;
        arrayAdapter = new ArrayAdapter<User>(List.this, android.R.layout.simple_list_item_1,dbHelper.getAllUser());
        lvDisplay.setAdapter(arrayAdapter);
    }
}