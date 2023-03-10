package com.sinhvien.finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sinhvien.finalproject.NhanVien.Hoadon;
import com.sinhvien.finalproject.QuanLi.QuanLiNhanVien;

public class Login extends AppCompatActivity {
    EditText edtendangnhap;
    EditText edmatkhau;
    Button btn_dangnhap;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_login);

        edmatkhau = (EditText) findViewById(R.id.edmatkhau);
        edtendangnhap = (EditText) findViewById(R.id.edtendangnhap);
        DB = new DBHelper(this);

        btn_dangnhap = (Button) findViewById(R.id.btn_dangnhap);
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtendangnhap.getText().toString();
                String pass = edmatkhau.getText().toString();
                if (user.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkAdmin = DB.checkAdmin(user, pass);
                    Boolean checkStaff = DB.checkStaff(user, pass);
                    if ((user.equals("nhom04")&& pass.equals("123456")) || checkAdmin == true) {
                        Toast.makeText(Login.this, "Login as Admin", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, QuanLiNhanVien.class);
                        intent.putExtra("Username", user);
                        startActivity(intent);
                        finish();
                    }
                    if (checkStaff == true) {
                        Toast.makeText(Login.this, "Login as Staff", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Hoadon.class);
                        //intent.putExtra("Username", user);
                        startActivity(intent);
                        finish();
                    } else
                        Toast.makeText(Login.this, "Invallid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}