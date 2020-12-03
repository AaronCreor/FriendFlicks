package com.nebulabs.friendflix;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class GroupFragment extends Fragment {

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public GroupFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_groups,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton addGroup_fab = view.findViewById(R.id.addgroupfab);
        addGroup_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toasty.info(getContext(),"ADD GROUP",Toasty.LENGTH_SHORT).show();
                addNewGroup();
            }
        });
    }

    public void addNewGroup(){
        final FlatDialog flatDialog = new FlatDialog(getContext());
        flatDialog.setTitle("ADD GROUP")
                .setSubtitle("Enter details for new group")
                .setFirstTextFieldHint("name")
                .setFirstButtonText("CREATE")
                .setSecondButtonText("CANCEL")
                .withFirstButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (flatDialog.getFirstTextField().isEmpty()){
                            Toasty.error(getContext(),"Enter a group name", Toasty.LENGTH_SHORT).show();
                        }
                        else {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            Map<String, Object> memberData = new HashMap<>();
                            memberData.put("email",user.getEmail());
                            memberData.put("uid",user.getUid());
                            memberData.put("admin",true);

                            ProgressDialog dialog = new ProgressDialog(getContext());
                            dialog.setMessage("Creating group, please wait...");
                            dialog.show();

                            db.collection("groups").document(flatDialog.getFirstTextField())
                                    .set(memberData)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("FIRE", "DocumentSnapshot successfully written!");
                                            dialog.dismiss();
                                            Toasty.success(getContext(), flatDialog.getFirstTextField() + " created!", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("FIRE", "Error writing document", e);
                                            Toasty.error(getContext(),"Group Creation Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            flatDialog.dismiss();
                        }
                    }
                })
                .withSecondButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flatDialog.dismiss();
                    }
                })
                .show();
    }

}
