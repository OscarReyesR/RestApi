package com.example.navigationmenu.Retrofit;

import com.google.gson.annotations.SerializedName;

public class ChangePassworsRequest {

    @SerializedName("token")
    public String token;

    @SerializedName("password")
    public String password;

    public ChangePassworsRequest(String token, String password)
    {
        this.token=token;
        this.password=password;
    }


    public class Response {
        @SerializedName("token")
        public String token;

        @SerializedName("name")
        public String name;

        @SerializedName("error")
        public String error;

        public Response(String token, String name, String error) {
            this.token = token;
            this.name = name;
            this.error=error;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "nombre='" + name + '\'' +
                    ", token='" + token + '\'' +
                    ", error='" + error + '\'' +
                    '}';
        }
    }
}
