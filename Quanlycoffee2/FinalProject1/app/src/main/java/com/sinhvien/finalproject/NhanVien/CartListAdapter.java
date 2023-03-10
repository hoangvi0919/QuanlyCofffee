package com.sinhvien.finalproject.NhanVien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sinhvien.finalproject.ChangeNumberItemListener;
import com.sinhvien.finalproject.Domain.FoodDomain;
import com.sinhvien.finalproject.R;


import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemListener changeNumberItemsListener;


    public CartListAdapter(ArrayList<FoodDomain> FoodDomains, Context context, ChangeNumberItemListener changeNumberItemsListener) {

        this.foodDomains = FoodDomains;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txttenmonan.setText(foodDomains.get(position).getMon());
        holder.txtgiamonan.setText(String.valueOf(foodDomains.get(position).getGia()));
        holder.txttonggiamonan.setText(String.valueOf(Math.round(foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getGia())));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);


        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood(foodDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.MinusNumerFood(foodDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttenmonan, txtgiamonan;
        ImageView pic, plusItem, minusItem;
        TextView txttonggiamonan, num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenmonan = itemView.findViewById(R.id.txt_tenmon);
            txtgiamonan = itemView.findViewById(R.id.txt_giamonan);
            pic = itemView.findViewById(R.id.pic_card);
            txttonggiamonan= itemView.findViewById(R.id.txt_tonggiamonan);
            num = itemView.findViewById(R.id.txt_soluongmonan);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCardBtn);
        }
    }


}
