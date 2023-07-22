package com.example.navigationmenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;

    FirebaseUser user;

    FirebaseDatabase database;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    TextView textViewUserName,textViewEmailUser;
    Button btnChangeInfo,btnNewPaswd;

    Files file=new Files();

    @Override
    protected void onStart() {
        if(auth.getCurrentUser()!=null)
        if(!Objects.equals(user.getEmail(), "")) {
            Log.i("user info",(user.getEmail()));
            Toast.makeText(this,"bienvenido "+user.getEmail(),Toast.LENGTH_SHORT).show();
        }
        else {
            loginFirebase();
        }
        else
            loginFirebase();
        super.onStart();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(file.readCache(this,"token")==null)
        {
            Intent intent=new Intent(this,LoginClass.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        //database=FirebaseDatabase.getInstance();
        //getSeasons();
        //setSeason("8");
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        View headerView=navigationView.getHeaderView(0);
        textViewUserName=headerView.findViewById(R.id.tvUserNameHeaderMenu);
        textViewEmailUser=headerView.findViewById(R.id.tvEmailHeaderMenu);
        btnChangeInfo=headerView.findViewById(R.id.btnChangeInfo);
        btnNewPaswd=headerView.findViewById(R.id.btnChangePassword);
        textViewUserName.setText(file.readCache(this,"userName"));
        if(auth.getCurrentUser()!=null)
            textViewEmailUser.setText(user.getEmail());
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.openNav,R.string.closeNav);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                openActivity(item.getItemId());
                return false;
            }
        });

        btnChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("debuger","modificar");
                Intent intent=new Intent(view.getContext(),ModifyPersonalInformaiton.class);
                intent.putExtra("email",user.getEmail());
                intent.putExtra("userName",file.readCache(view.getContext(),"userName"));
                startActivity(intent);
            }
        });
        btnNewPaswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),NewPassword.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void openActivity(int idActivityToStart)
    {
        Intent intent;
        switch (idActivityToStart)
        {
            case R.id.courseSummary:
            {
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.rules:
            {
                intent=new Intent(this,Rules.class);
                startActivity(intent);
                break;
            }
            case R.id.classAgenda:
            {
                intent=new Intent(this,ClassAgenda.class);
                startActivity(intent);
                break;
            }
            case R.id.sessions:
            {
                intent=new Intent(this,ClassSessions.class);
                startActivity(intent);
                break;
            }
            case R.id.logOut:
            {
                if(file.readCache(MainActivity.this,"token")!=null)
                file.writeCache(MainActivity.this,"token",null);

                if(file.readCache(MainActivity.this,"userName")!=null)
                file.writeCache(MainActivity.this,"userName",null);

                intent=new Intent(this,LoginClass.class);
                startActivity(intent);
                break;
            }
        }

    }//end function openActivity

    private void loginFirebase(){

            //TODO inicio de sesión con firebase c:


            String email="minino99israel@gmail.com";
            String passwd="muchosidad";
            auth.signInWithEmailAndPassword(email,passwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isComplete())
                                Toast.makeText(MainActivity.this,"logeado! ",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this,"K pasó master .-. "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });

        }

    private void loginAnonimusFireBase(){
        auth.signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                            Toast.makeText(MainActivity.this,"Logeado anónimamente",Toast.LENGTH_LONG).show();
                        else {
                            Toast.makeText(MainActivity.this,"Error al logearse",Toast.LENGTH_LONG).show();
                            Log.i("error al logearse","Error al iniciar sesión"+task.getException().getMessage() );
                        }
                    }
                });
    }

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


    private void setSeason(String idSeason)
    {
        DatabaseReference references=database.getReference("season");
        Season season=new Season("Descripcioón Prueba","Descripción prueba",false);
        references.child(idSeason).setValue(season).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i("database","nuevo hijo wee!! :D");
            }
        });
    }
}
