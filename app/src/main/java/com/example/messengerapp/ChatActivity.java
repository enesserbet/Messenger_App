package com.example.messengerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.example.messengerapp.databinding.ActivityChatBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.lang.Math;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;


    private ArrayList<ModelMessage> messageArrayList;
    private ArrayList<ModelMessage2> message2ArrayList;
    private String receiveruid, name, date;


    private AdapterMessage2 adapterMessage2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        checkUser();
        //loadMessages();
        loadMessages2();


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkUser();
            }
        });

        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }



    public static final String formatTimestamp(long timestamp) {

        Calendar cal = Calendar.getInstance(Locale.GERMAN);
        cal.setTimeInMillis(timestamp);
        String date = DateFormat.format("dd/MM/yyyy HH:mm:ss", cal).toString();


        return date;
    }

    private String text = "";

    private void validateData() {
        text = binding.textEt.getText().toString().trim();

        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "Texting Please...", Toast.LENGTH_SHORT).show();
        } else {
            uploadMessage();
            clearText();
        }
    }



    private void clearText() {
        binding.textEt.setText(null);
    }


    private void uploadMessage() {
        progressDialog.setMessage("Sendind your message...");
        progressDialog.show();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String senderuid = firebaseUser.getUid();
        Intent intent = getIntent();
        receiveruid = intent.getStringExtra("uid");
        name = intent.getStringExtra("name");

        FirebaseUser firebaseUsere = firebaseAuth.getCurrentUser();
        if (firebaseUsere == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            String email = firebaseUser.getEmail();

            //DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            long timestamp = System.currentTimeMillis();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("message", "" + text);
            hashMap.put("uid", "" + firebaseAuth.getUid());
            hashMap.put("senderUid", "" +email);
            hashMap.put("timestamp", "" + timestamp);
            hashMap.put("receiverUid", "" + receiveruid);
            hashMap.put("chatUid", "" + receiveruid + senderuid);
            //ref.child("Chats").push().setValue(hashMap);

            //int a = (int) (Math.random()*100)+1;
            //System.out.println(a);

            //loadMessages();
            loadMessages2();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Messages");

            ref.child("" + timestamp)

                    .setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            progressDialog.dismiss();
                            Toast.makeText(ChatActivity.this, "", Toast.LENGTH_SHORT).show();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ChatActivity.this, "Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


        }

    }

    private void checkUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            String email = firebaseUser.getEmail();

            binding.subTitleTV.setText(email);

        }
    }




    private void loadMessages2() {//burası çalııyor sorun yok
        message2ArrayList = new ArrayList<>();


        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String senderuid = firebaseUser.getUid();
        Intent intent = getIntent();
        receiveruid = intent.getStringExtra("uid");
        name = intent.getStringExtra("name");
        String chatUid = senderuid + receiveruid;

        System.out.println(chatUid);
        System.out.println("Burası chatuid2");


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Messages");
        //ref.orderByChild("chatUid").equalTo(chatUid)
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        message2ArrayList.clear();
                        for (DataSnapshot ds1 : snapshot.getChildren()) {
                            ModelMessage2 modelMessage2 = ds1.getValue(ModelMessage2.class);


                            message2ArrayList.add(modelMessage2);


                            adapterMessage2 = new AdapterMessage2(ChatActivity.this, message2ArrayList);

                            if ((modelMessage2.getChatUid().equals(receiveruid+senderuid)) || (modelMessage2.getChatUid().equals(senderuid+receiveruid))){
                                binding.categoryiesRv.setAdapter(adapterMessage2);
                            }


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void loadMessages() {//burası çalııyor sorun yok
        message2ArrayList = new ArrayList<>();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String senderuid = firebaseUser.getUid();
        Intent intent = getIntent();
        receiveruid = intent.getStringExtra("uid");
        name = intent.getStringExtra("name");
        String chatUid = receiveruid + senderuid;

        System.out.println(chatUid);
        System.out.println("Burası chatuid2");


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Messages");
        ref.orderByChild("chatUid").equalTo(chatUid)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        message2ArrayList.clear();
                        for (DataSnapshot ds1 : snapshot.getChildren()) {
                            ModelMessage2 modelMessage2 = ds1.getValue(ModelMessage2.class);


                            message2ArrayList.add(modelMessage2);


                            adapterMessage2 = new AdapterMessage2(ChatActivity.this, message2ArrayList);
                            binding.categoryiesRv.setAdapter(adapterMessage2);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }






    /*private void loadMessages() {
        messageArrayList = new ArrayList<>();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String senderuid = firebaseUser.getUid();
        Intent intent = getIntent();
        receiveruid = intent.getStringExtra("uid");
        name = intent.getStringExtra("name");
        String chatUid = senderuid + receiveruid;

        System.out.println(chatUid);
        System.out.println("Burası chatuid");


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Messages");
        ref.orderByChild("chatUid").equalTo(chatUid)
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messageArrayList.clear();
                        //burada path be eşit olduğu yeri her mesajda aynı olan bir şey yapmam gerek ona göre önüme getirecek mesajları
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelMessage modelMessage = ds.getValue(ModelMessage.class);


                            System.out.println(modelMessage.getSenderUid());

                            adapterMessage = new AdapterMessage(ChatActivity.this, messageArrayList);
                            System.out.println(modelMessage.getChatUid());
                            System.out.println("model buraya kadar");
                            binding.categoryRv2.setAdapter(adapterMessage);
                            /*if ((modelMessage.getSenderUid().equals(senderuid)) && modelMessage.getReceiverUId().equals(receiveruid)) {


                            }




                            //adapterMessage = new AdapterMessage(ChatActivity.this, messageArrayList);
                            //binding.categoryiesRv.setAdapter(adapterMessage);
                            //adapterMessage2 = new AdapterMessage2(ChatActivity.this, message2ArrayList);
                            //binding.categoryiesRv.setAdapter(adapterMessage2);
                        }

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }

     */

}