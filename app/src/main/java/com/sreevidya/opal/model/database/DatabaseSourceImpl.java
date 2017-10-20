package com.sreevidya.opal.model.database;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseSourceImpl implements DatabaseSource {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;

    DatabaseSourceImpl() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("messages");
    }

    @Override
    public void createMessage(String messageText, final DatabaseCallback<Void> dbCallback) {
        Message message = new Message("1", messageText, "Vidya", null);
        mMessagesDatabaseReference.push().setValue(message).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    dbCallback.onSuccess(null);
                } else {
                    dbCallback.onFailure(task.getException());
                }
            }
        });

    }

    @Override
    public void getMessage(String messageId, DatabaseCallback<Void> dbCallback) {

    }
}
