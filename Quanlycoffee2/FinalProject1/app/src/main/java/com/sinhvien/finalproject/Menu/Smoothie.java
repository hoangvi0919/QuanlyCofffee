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

import com.sinhvien.finalproject.Domain.FoodDomain;
import com.sinhvien.finalproject.NhanVien.Hoadon;
import com.sinhvien.finalproject.NhanVien.ThanhToan;
import com.sinhvien.finalproject.R;

import java.util.ArrayList;

public class Smoothie extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewPopularList;
    Button btncoffee4,btntea4,btnjuice4,btnfood4;
    ImageView imgthanhtoan,imghome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoothie);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Button();
        recyclerViewPopular();
    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewPopularList =findViewById(R.id.recyclerView4);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<FoodDomain> foodList =new ArrayList<>();
        foodList.add(new FoodDomain("Watermelon Smoothie","sinhtoduahau",20000));
        foodList.add(new FoodDomain("Avocado Smoothie","sinhtobo",35000));
        foodList.add(new FoodDomain("Strawberry Smoothie","sinhtodau",30000));
        foodList.add(new FoodDomain("Blueberry Smoothie","sinhtovietquat",40000));
        foodList.add(new FoodDomain("Pineapple Smoothie","sinhtodua",25000));
        foodList.add(new FoodDomain("Mango Smoothie","sinhtoxoai",30000));
        adapter = new PopularAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter);
    }

    private void Button(){
        btncoffee4=(Button)findViewById(R.id.btn_coffee4);
        btncoffee4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Smoothie.this,Coffee.class);
                startActivity(i);
            }
        });
        btntea4=(Button)findViewById(R.id.btn_tea4);
        btntea4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Smoothie.this,Tea.class);
                startActivity(i);
            }
        });
        btnjuice4=(Button)findViewById(R.id.btn_juice4);
        btnjuice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Smoothie.this,Juice.class);
                startActivity(i);
            }
        });
        btnfood4=(Button)findViewById(R.id.btn_Food4);
        btnfood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Smoothie.this, Food.class);
                startActivity(i);
            }
        });

        imgthanhtoan=(ImageView)findViewById(R.id.img_thanhtoan4);
        imgthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Smoothie.this, ThanhToan.class);
                startActivity(i);
            }
        });

        imghome=(ImageView)findViewById(R.id.img_home4);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Smoothie.this, Hoadon.class);
                startActivity(i);
            }
        });

    }
}