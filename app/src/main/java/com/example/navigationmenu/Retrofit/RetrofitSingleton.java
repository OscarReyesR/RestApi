package com.example.navigationmenu.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitSingleton {
        private static Retrofit retrofit=null;
        private static final String BASE_URL="https://chemacruzp.pythonanywhere.com/";
        public static Retrofit getInstance(){
            if(retrofit==null)
            {
            /*
            OkHttpClient okHttpClient=new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(new RequestInterceptor())
                    .build();*/

                retrofit=new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        //.client(okHttpClient)
                        .build();
            }
            return retrofit;
        }

}
