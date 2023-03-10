package com.sinhvien.finalproject.QuanLi.Department;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sinhvien.finalproject.DBHelper;
import com.sinhvien.finalproject.R;

public class Add extends AppCompatActivity {
    EditText edName;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_dep_add);
        edName = findViewById(R.id.edName);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Department department = new Department();
                try {
                    department = new Department(-1,
                            edName.getText().toString());
                    Toast.makeText(Add.this, "Success: " + department.toString(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.d("Error in on click", e.toString());
                }
                DBHelper dbHelper = new DBHelper(Add.this);
                dbHelper.addDepartment(department);
                Intent intent = new Intent(Add.this,List.class);
                startActivity(intent);
                finish();
            }
        });
    }
}