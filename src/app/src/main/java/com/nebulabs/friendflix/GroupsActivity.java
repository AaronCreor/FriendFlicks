package com.nebulabs.friendflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class GroupsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    List<String> groupsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_list);

        getSupportActionBar().setTitle("My Groups");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        groupsList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(groupsList);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        groupsList.add("The Interstellar Administration Union");
        groupsList.add("Nebulabs");
        groupsList.add("The Dread Syndicate");
        groupsList.add("The First Syndicate");
        groupsList.add("The Sovereign Union");
        groupsList.add("CIS Class of 2021");
        groupsList.add("The Federation of the Pious");
        groupsList.add("The Federation of Industry");
        groupsList.add("The Treaty of Inquisition");
        groupsList.add("The Coalition of Custodians");
        groupsList.add("CS Class of 2022");
        groupsList.add("The Intimidation Concord");
        groupsList.add("The Phoenix League");
        groupsList.add("The Confederated Confederacy");
        groupsList.add("The Paragon Union");
        groupsList.add("The Federation of the Shepherd");
        groupsList.add("The Entente of the Revelation");
        groupsList.add("The Confederation of Self-Defense");
        groupsList.add("The Syndicate of the Faithful");
        groupsList.add("The Bond of Faith");
        groupsList.add("The Allied Bond");
        groupsList.add("The Nations of Liberty");
        groupsList.add("The Accord of Faith");
    }
}
