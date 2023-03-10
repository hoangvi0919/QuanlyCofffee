package com.sinhvien.finalproject.Menu;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sinhvien.finalproject.Domain.FoodDomain;
import com.sinhvien.finalproject.NhanVien.ShowOrder;
import com.sinhvien.finalproject.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<FoodDomain> foodDomains;
    public PopularAdapter(ArrayList<FoodDomain>FoodDomains)
    {
        this.foodDomains=FoodDomains;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        holder.mon.setText(foodDomains.get(position).getMon());
        holder.gia.setText(String.valueOf(foodDomains.get(position).getGia()));
        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(holder.itemView.getContext(), ShowOrder.class);
                intent.putExtra("object",foodDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mon,gia;
        ImageView pic;
        TextView addBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            mon=itemView.findViewById(R.id.txt_mon);
            gia=itemView.findViewById(R.id.txt_gia);
            pic=itemView.findViewById(R.id.pic);
            addBtn=itemView.findViewById(R.id.addbtn);
        }
    }
}
