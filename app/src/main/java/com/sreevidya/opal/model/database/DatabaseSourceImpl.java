package com.sreevidya.opal.model.database;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sreevidya.opal.model.auth.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSourceImpl implements DatabaseSource {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference dbReference;

    DatabaseSourceImpl() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbReference = mFirebaseDatabase.getReference().child("USERS");
    }

    @Override
    public void createMessage(
            final String uid,
            final Message message,
            final DatabaseCallback<Message> dbCallback) {
        dbReference
                .child(uid)
                .child("MESSAGES")
                .push()
                .setValue(message)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    dbCallback.onSuccess(message);
                } else {
                    dbCallback.onFailure(task.getException());
                }
            }
        });

    }

    @Override
    public void addUser(User u, final DatabaseCallback<Void> dbCallback) {
        dbReference
                .child(u.getUid())
                .setValue(u)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            dbCallback.onSuccess(null);
                        } else {
                            Log.e("DBServiceImpl", "onFailure: ", task.getException());
                            dbCallback.onFailure(task.getException());
                        }
                    }
                });
    }

    @Override
    public void getMessages(final User u, final DatabaseCallback<List<Message>> dbCallback) {

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Message> messages = new ArrayList<>();
                if (dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot s : dataSnapshot.getChildren()) {
                        messages.add(s.getValue(Message.class));
                    }
                }
                dbReference.child(u.getUid())
                        .child("MESSAGES").removeEventListener(this);
                dbCallback.onSuccess(messages);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dbCallback.onFailure(databaseError.toException());
            }
        };
        dbReference.child(u.getUid())
                .child("MESSAGES")
                .addValueEventListener(listener);
    }
}
