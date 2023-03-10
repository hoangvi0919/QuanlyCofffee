package com.sinhvien.finalproject.QuanLi.User;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sinhvien.finalproject.DBHelper;
import com.sinhvien.finalproject.R;

import java.util.List;

public class Register extends AppCompatActivity {
    EditText edUser,edPass,edName,edPhone,edAddress;
    Spinner spDep;
    Button btnAdd;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_user_register);

        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
        edName = findViewById(R.id.edName);
        edPhone = findViewById(R.id.edPhone);
        edAddress = findViewById(R.id.edAddress);
        spDep = findViewById(R.id.spDep);
        loadSpinnerData();
        btnAdd = findViewById(R.id.btnAdd);
        DBHelper dbHelper = new DBHelper(Register.this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String users = edUser.getText().toString();
                String pass = edPass.getText().toString();
                if (users.equals("")||pass.equals(""))
                    Toast.makeText(Register.this, "Please fill in username and password", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUsername = dbHelper.checkUsername(users);
                    if (checkUsername==false) {
                        User user = new User();
                        try {
                            user = new User(-1,
                                    spDep.getSelectedItem().toString(),
                                    edUser.getText().toString(),
                                    edPass.getText().toString(),
                                    edName.getText().toString(),
                                    edPhone.getText().toString(),
                                    edAddress.getText().toString());
                            Toast.makeText(Register.this, "Success: " + user.toString(), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Log.d("Error on click ", e.toString());
                        }
                        dbHelper.addUser(user);
                        Intent intent = new Intent(Register.this, com.sinhvien.finalproject.QuanLi.User.List.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                        Toast.makeText(Register.this,"Similer username try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void loadSpinnerData(){
        DBHelper db1 = new DBHelper(getApplicationContext());
        List<String> labels = db1.getDepartment();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDep.setAdapter(dataAdapter);
    }
}