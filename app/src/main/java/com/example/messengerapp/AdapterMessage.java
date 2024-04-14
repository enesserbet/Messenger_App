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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.HolderMessage> {

    private ChatActivity context1;
    private ArrayList<ModelMessage> messageArrayList;


    private RowMessageOpponentBinding binding;

    public static final int VIEW_TYPE_ SENT = 1;
    public static final int VIEW_TYPE RECEIVED


    public AdapterMessage(ChatActivity context1, ArrayList<ModelMessage> messageArrayList) {
        this.context1 = context1;
        this.messageArrayList = messageArrayList;
    }

    @NonNull
    @Override
    public HolderMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowMessageOpponentBinding.inflate(LayoutInflater.from(context1), parent, false);
        return new HolderMessage(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessage holder, int position) {
        ModelMessage model1 = messageArrayList.get(position);
        String message1 = model1.getMessage();
        String receiverUid = model1.getReceiverUId();
        String senderUid = model1.getReceiverUId();
        String uid = model1.getUid();
        String timestamp = model1.getTimestamp();
        String chatUid = model1.getChatUid();

        holder.messagefirst.setText(message1);


    }


    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    class HolderMessage extends RecyclerView.ViewHolder {

        TextView messagefirst;

        public HolderMessage(@NonNull View itemView) {
            super(itemView);
            messagefirst = binding.messageopponent;
        }
    }
}
