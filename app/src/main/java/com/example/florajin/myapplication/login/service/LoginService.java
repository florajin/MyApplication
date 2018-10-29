package com.example.florajin.myapplication.login.service;

import com.example.florajin.myapplication.modal.UserEntity;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;


public interface LoginService {

        @GET("employees/{screenName}")
        Observable<UserEntity> login(
                @Path("screenName") String userId
        );


}
