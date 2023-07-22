package com.example.navigationmenu;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationmenu.Models.ListElementMain;
import com.example.navigationmenu.adapters.ListAdapterMain;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClassSessions extends AppCompatActivity {
    List<ListElementMain> elements;

    FirebaseDatabase database;
    Season season;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database=FirebaseDatabase.getInstance();
        setContentView(R.layout.sessions_layout);
        //getSeasons();
        initRecyclerView();
    }

    public void initRecyclerView(){
        elements=new ArrayList<>();
        DatabaseReference references=database.getReference("season");
        references.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    season=dataSnapshot.getValue(Season.class);
                    elements.add(new ListElementMain(season.name,season.description));
                }

                ListAdapterMain listAdapter=new ListAdapterMain(elements,ClassSessions.this);
                RecyclerView recyclerView=findViewById(R.id.recycleMainActivity);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(ClassSessions.this));
                recyclerView.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ClassSessions.this,"Ocurrió un error al obtener la información.",Toast.LENGTH_SHORT).show();
            }
        });

    } //end function initRecyclerView


    private void getSeasons(){
        DatabaseReference references=database.getReference("season");
        references.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Season season=dataSnapshot.getValue(Season.class);
                    Log.i("database",String.valueOf(season.name+" "+season.description+" "+season.status));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("Error al leer los datos ",error.getMessage());
            }
        });
    }
}
