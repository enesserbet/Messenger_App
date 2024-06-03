package com.example.messengerapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.messengerapp.databinding.RowCategoryUserBinding;
import com.example.messengerapp.databinding.RowMessageBinding;
import com.example.messengerapp.databinding.RowMessageOpponentBinding;

import java.util.ArrayList;

public class AdapterMessage2 extends RecyclerView.Adapter<AdapterMessage2.HolderMessage2> {
    private ChatActivity context;
    private ArrayList<ModelMessage2> message2ArrayList;
    private RowMessageBinding binding;


    public AdapterMessage2(ChatActivity context, ArrayList<ModelMessage2> message2ArrayList) {
        this.context = context;
        this.message2ArrayList = message2ArrayList;
    }

    @NonNull
    @Override
    public HolderMessage2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowMessageBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderMessage2(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessage2 holder, int position) {
        ModelMessage2 model = message2ArrayList.get(position);
        String message = model.getMessage();
        String receiverUid = model.getReceiverUId();
        String senderUid = model.getSenderUid();
        String uid = model.getUid();
        String timestamp = model.getTimestamp();
        String chatUid = model.getChatUid();
        String formattedDate = ChatActivity.formatTimestamp(Long.parseLong(timestamp));

        holder.messageopponent.setText(message);
        holder.date.setText(formattedDate);
        holder.name.setText(senderUid);



    }

    @Override
    public int getItemCount() {
        return message2ArrayList.size();
    }


    class HolderMessage2 extends RecyclerView.ViewHolder {

        TextView messageopponent;
        TextView date;
        TextView name;

        public HolderMessage2(@NonNull View itemView) {
            super(itemView);
            messageopponent = binding.messagefirst;
            date = binding.date01;
            name = binding.name01;
        }
    }
}


