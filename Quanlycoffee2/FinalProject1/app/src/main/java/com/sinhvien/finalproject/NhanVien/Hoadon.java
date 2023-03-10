package com.sinhvien.finalproject.NhanVien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sinhvien.finalproject.Login;
import com.sinhvien.finalproject.Menu.Coffee;
import com.sinhvien.finalproject.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Hoadon extends AppCompatActivity {

    TextView txtshow,monan,giatien;
    EditText tenDN;
    Button btnthemmon,btndangxuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);
        txtshow=findViewById(R.id.text_show);
        tenDN= findViewById(R.id.edtendangnhap);
        tenDN= findViewById(R.id.edtendangnhap);
        txtshow.setText("Welcome");
        btnthemmon=findViewById(R.id.btn_themmon);

        btnthemmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Hoadon.this, Coffee.class);
                startActivity(i);
            }
        });

        btndangxuat=(Button)findViewById(R.id.btn_logout);

        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Hoadon.this, Login.class);
                startActivity(i);
            }
        });




    }
}