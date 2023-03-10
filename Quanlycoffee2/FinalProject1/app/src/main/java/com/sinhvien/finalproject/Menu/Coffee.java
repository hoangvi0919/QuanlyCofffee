package com.sinhvien.finalproject.Menu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sinhvien.finalproject.NhanVien.Hoadon;
import com.sinhvien.finalproject.NhanVien.ThanhToan;
import com.sinhvien.finalproject.Domain.FoodDomain;
import com.sinhvien.finalproject.R;

import java.util.ArrayList;

public class Coffee extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewPopularList;
    Button btntea,btnsmoothie,btnjuice,btnfood;
    ImageView imgthanhtoan,imghome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        recyclerViewPopular();
        Button();
    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewPopularList =findViewById(R.id.recyclerView);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<FoodDomain>foodList =new ArrayList<>();
        foodList.add(new FoodDomain("Cappuccino","capuchino",60000));
        foodList.add(new FoodDomain("Latte","latte",33000));
        foodList.add(new FoodDomain("Americano","americano",50000));
        foodList.add(new FoodDomain("Espresso","espresso",50000));
        foodList.add(new FoodDomain("Latte Macchiato","lattemacchiato",30000));
        foodList.add(new FoodDomain("Mocha","mocha",70000));
        adapter = new PopularAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter);

    }

    private void Button(){
        btntea=(Button)findViewById(R.id.btn_tea);
        btntea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Coffee.this,Tea.class);
                startActivity(i);
            }
        });
        btnsmoothie=(Button)findViewById(R.id.btn_smoothie);
        btnsmoothie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Coffee.this,Smoothie.class);
                startActivity(i);
            }
        });
        btnjuice=(Button)findViewById(R.id.btn_juice);
        btnjuice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Coffee.this,Juice.class);
                startActivity(i);
            }
        });
        btnfood=(Button)findViewById(R.id.btn_Food);
        btnfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Coffee.this, Food.class);
                startActivity(i);
            }
        });

        imgthanhtoan=(ImageView)findViewById(R.id.img_thanhtoan);
        imgthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Coffee.this,ThanhToan.class);
                startActivity(i);
            }
        });

        imghome=(ImageView)findViewById(R.id.img_home);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Coffee.this,Hoadon.class);
                startActivity(i);
            }
        });

    }
}