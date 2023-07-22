package com.example.navigationmenu.Retrofit;

import com.google.gson.annotations.SerializedName;

public class UserUpdateRequest {
    @SerializedName("email")
    public String email;

    @SerializedName("token")
    public String token;

    @SerializedName("name")
    public String name;

public UserUpdateRequest(String token, String email, String name){
    this.token=token;
    this.email=email;
    this.name=name;
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
