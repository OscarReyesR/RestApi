package com.example.navigationmenu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationmenu.Retrofit.ApiService;
import com.example.navigationmenu.Retrofit.LoginRquest;
import com.example.navigationmenu.Retrofit.RetrofitSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginClass extends AppCompatActivity {

    private EditText txtUserName,txtPasswd;
    private String userName=null,passwd=null;
    private Button btnLogin;
    private TextView tvNewPasswd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        txtUserName=findViewById(R.id.txtInputUserName);
        txtPasswd=findViewById(R.id.txtInputPasswd);
        btnLogin=findViewById(R.id.btnLogin);
        tvNewPasswd=findViewById(R.id.textViewRecoverPasswd);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName=txtUserName.getText().toString();
                passwd=txtPasswd.getText().toString();
                ApiService service= RetrofitSingleton.getInstance().create(ApiService.class);
                Call<LoginRquest.Response> request=service.login(new LoginRquest(txtUserName.getText().toString(),txtPasswd.getText().toString()));
                request.enqueue(new Callback<LoginRquest.Response>() {
                    @Override
                    public void onResponse(Call<LoginRquest.Response> call, Response<LoginRquest.Response> response) {
                        Log.i("onResponse","Funcionó! :D "+response.body());

                        Files file=new Files();
                        if(response.body().token!=null)
                        {
                            file.writeCache(view.getContext(),"token",response.body().token);
                            file.writeCache(view.getContext(),"userName",response.body().name);
                            logIn();
                        }
                        else if(response.body().error!=null)
                        {
                            Log.i("credenciales",(txtUserName.getText().toString()+","+txtPasswd.getText().toString()));
                            Toast.makeText(LoginClass.this,"Credenciales incorrectas",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginRquest.Response> call, Throwable t) {
                        Log.e("onFailure",t.getMessage());
                    }
                });

            }
        });
        tvNewPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setData(Uri.parse("https://ieca.conectatalentomx.com"));
                startActivity(intent);
            }
        });

    }




    private void logIn(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }//end function logIn
}
