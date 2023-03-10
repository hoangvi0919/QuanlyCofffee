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

public class Food extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewPopularList;
    Button btncoffee5,btntea5,btnsmoothie5,btnjuice5;
    ImageView imgthanhtoan,imghome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Button();
        recyclerViewPopular();
    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewPopularList =findViewById(R.id.recyclerView5);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<FoodDomain> foodList =new ArrayList<>();
        foodList.add(new FoodDomain("Cheese","phomai",25000));
        foodList.add(new FoodDomain("Fried Chicken","garan",32000));
        foodList.add(new FoodDomain("French Fries","khoaitaychien",22000));
        foodList.add(new FoodDomain("Fried Squid","mucchien",35000));
        adapter = new PopularAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter);
    }

    private void Button(){
        btncoffee5=(Button)findViewById(R.id.btn_coffee5);
        btncoffee5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Food.this, Coffee.class);
                startActivity(i);
            }
        });
        btntea5=(Button)findViewById(R.id.btn_tea5);
        btntea5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Food.this, Tea.class);
                startActivity(i);
            }
        });
        btnsmoothie5=(Button)findViewById(R.id.btn_smoothie5);
        btnsmoothie5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Food.this, Smoothie.class);
                startActivity(i);
            }
        });
        btnjuice5=(Button)findViewById(R.id.btn_juice5);
        btnjuice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Food.this, Juice.class);
                startActivity(i);
            }
        });

        imgthanhtoan=(ImageView)findViewById(R.id.img_thanhtoan5);
        imgthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Food.this, ThanhToan.class);
                startActivity(i);
            }
        });

        imghome=(ImageView)findViewById(R.id.img_home5);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Food.this, Hoadon.class);
                startActivity(i);
            }
        });
    }
}