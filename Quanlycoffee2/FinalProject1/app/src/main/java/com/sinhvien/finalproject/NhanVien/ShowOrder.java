package com.sinhvien.finalproject.NhanVien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sinhvien.finalproject.Domain.FoodDomain;
import com.sinhvien.finalproject.R;

public class ShowOrder extends AppCompatActivity {
    private ImageView imgshow,imgremove,imgadd;
    private TextView txtshowmon,txtshowgia,txtsoluong;
    private Button btnthem;
    private FoodDomain object;
    private int soluong = 1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_show_order);
        imgshow = (ImageView)findViewById(R.id.foodPic);
        txtshowmon=(TextView)findViewById(R.id.txt_showmon);
        txtshowgia=(TextView)findViewById(R.id.txt_showgia);
        imgremove = (ImageView)findViewById(R.id.img_remove);
        imgadd = (ImageView)findViewById(R.id.img_add);
        txtsoluong = (TextView)findViewById(R.id.txt_soluong);

        managementCart = new ManagementCart(this);
        btnthem = (Button)findViewById(R.id.btn_them);
        getBundle();
    }

    private void getBundle(){
        object = (FoodDomain)getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(imgshow);
        txtshowmon.setText(object.getMon());
        txtshowgia.setText(""+object.getGia()+"\t VND");
        txtsoluong.setText(String.valueOf(soluong));

        imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluong = soluong + 1;
                txtsoluong.setText(String.valueOf(soluong));
            }
        });

        imgremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soluong>1){
                    soluong = soluong - 1 ;
                }
                txtsoluong.setText(String.valueOf(soluong));
            }
        });



        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(soluong);
                managementCart.insertFood(object);
            }
        });

    }
}