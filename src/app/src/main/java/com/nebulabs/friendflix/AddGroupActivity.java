package com.nebulabs.friendflix;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class AddGroupActivity extends AppCompatActivity {

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgroup);

        getSupportActionBar().setTitle("Add Group");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Button addgroupbtn = findViewById(R.id.add_group_ok);
        addgroupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewGroup();
            }
        });
    }

    public void addNewGroup() {
        EditText groupName = findViewById(R.id.add_group_name);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Map<String, Object> memberData = new HashMap<>();
        memberData.put("email",user.getEmail());
        memberData.put("uid",user.getUid());

//        Map<String, Object> group = new HashMap<>();
//        group.put("name", groupName.getText().toString());
//        group.put("member",memberData);

        db.collection("groups").document(groupName.getText().toString())
                .set(memberData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("FIRE", "DocumentSnapshot successfully written!");
                        Toasty.success(getApplicationContext(),"Group Added", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FIRE", "Error writing document", e);
                        Toasty.error(getApplicationContext(),"Group Addition Failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
