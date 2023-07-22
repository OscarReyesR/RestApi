package com.example.navigationmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationmenu.Retrofit.ApiService;
import com.example.navigationmenu.Retrofit.ChangePassworsRequest;
import com.example.navigationmenu.Retrofit.RetrofitSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPassword extends AppCompatActivity {
Button btnSaveData;
TextView txtNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Files file=new Files();
        setContentView(R.layout.new_password_layout);

        btnSaveData=findViewById(R.id.btnChangePassword);
        txtNewPassword=findViewById(R.id.txtNewPassword);
        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("nueva contraseña ",txtNewPassword.getEditableText().toString());
                ApiService service= RetrofitSingleton.getInstance().create(ApiService.class);
                Call<ChangePassworsRequest.Response> request=service.changePassword(new ChangePassworsRequest(file.readCache(NewPassword.this,"token"),txtNewPassword.getEditableText().toString()));
                request.enqueue(new Callback<ChangePassworsRequest.Response>() {
                    @Override
                    public void onResponse(Call<ChangePassworsRequest.Response> call, Response<ChangePassworsRequest.Response> response) {
                        Log.i("onResponse","Funcionó! :D "+response.body());
                        if(response.isSuccessful())
                        {
                            Toast.makeText(NewPassword.this,"Cambio de contraseña realizado",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangePassworsRequest.Response> call, Throwable t) {
                        Log.e("onFailure",t.getMessage());
                    }
                });
            }
        });
    }
}