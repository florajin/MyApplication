package com.example.florajin.myapplication.login.retrofit;

import com.example.florajin.myapplication.constants.HttpConfig;
import com.example.florajin.myapplication.login.service.LoginService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static RetrofitFactory retrofitFactory;
    private static LoginService loginService;
    private RetrofitFactory(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .build();
        Retrofit loginRetrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                //.addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(okHttpClient)
                .build();
        loginService = loginRetrofit.create(LoginService.class);
    }

    public static RetrofitFactory getInstance(){
        if (retrofitFactory==null){
            synchronized (RetrofitFactory.class) {
                if (retrofitFactory == null)
                    retrofitFactory = new RetrofitFactory();
                }
        }
        return retrofitFactory;
    }

    public  LoginService getService(){
            return loginService;
    }

}
