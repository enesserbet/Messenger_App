package com.example.messengerapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messengerapp.databinding.RowCategoryUserBinding;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.HolderUsers> {
    //private DashboardUserActivity context;
    private DashboardUserActivity context;
    private ArrayList<ModelUsers> usersArrayList;
    private static RowCategoryUserBinding binding;

    public AdapterUser(DashboardUserActivity context, ArrayList<ModelUsers> usersArrayList) {
        this.context = context;
        this.usersArrayList = usersArrayList;
    }

    @NonNull
    @Override
    public HolderUsers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowCategoryUserBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderUsers(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderUsers holder, int position) {
        ModelUsers model = usersArrayList.get(position);
        String email = model.getEmail();
        String name = model.getName();
        String profilImage = model.getProfilImage();
        String timestamp = model.getTimestamp();
        String uid = model.getUid();
        String userType = model.getUserTye();

        holder.categoryTv.setText(name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("uid", uid);
                intent.putExtra("name", name);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    static class HolderUsers extends RecyclerView.ViewHolder {

        TextView categoryTv;

        public HolderUsers(@NonNull View itemView) {
            super(itemView);
            categoryTv = binding.categoryTv;
        }
    }
}
