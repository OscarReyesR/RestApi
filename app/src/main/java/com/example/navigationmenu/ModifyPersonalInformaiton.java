package com.example.navigationmenu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationmenu.Retrofit.ApiService;
import com.example.navigationmenu.Retrofit.RetrofitSingleton;
import com.example.navigationmenu.Retrofit.UserUpdateRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyPersonalInformaiton extends AppCompatActivity {
EditText newEmail,newName;
String userName,email;
Button btnSaveData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userName=getIntent().getExtras().getString("userName");
        email=getIntent().getExtras().getString("email");
        setContentView(R.layout.modify_information_layout);
        newEmail=findViewById(R.id.txtChangeEmail);
        newName=findViewById(R.id.txtChangeUserName);
        btnSaveData=findViewById(R.id.saveData);
        newEmail.setText(email);
        newName.setText(userName);
        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Files file=new Files();
                ApiService service= RetrofitSingleton.getInstance().create(ApiService.class);
                Call<UserUpdateRequest.Response> request=service.changeInfo(new UserUpdateRequest(file.readCache(view.getContext(),"token"),newEmail.getText().toString(),newName.getText().toString()));
                request.enqueue(new Callback<UserUpdateRequest.Response>() {
                    @Override
                    public void onResponse(Call<UserUpdateRequest.Response> call, Response<UserUpdateRequest.Response> response) {
                        Log.i("onResponse","Funcionó! :D "+response.body());
                        if(response.isSuccessful())
                        {
                            Toast.makeText(ModifyPersonalInformaiton.this,"Cambio de datos realziado",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserUpdateRequest.Response> call, Throwable t) {
                        Log.e("onFailure",t.getMessage());
                    }
                });
            }
        });
    }
}
