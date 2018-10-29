package com.example.florajin.myapplication.login;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.example.florajin.myapplication.R;
import com.example.florajin.myapplication.login.retrofit.RetrofitFactory;
import com.example.florajin.myapplication.login.service.LoginService;
import com.example.florajin.myapplication.modal.UserEntity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenterImpl implements LoginContract.LoginPresenter {
    private LoginContract.LoginView loginView;
    private static final String TAG = "LoginActivity";

    public LoginPresenterImpl(LoginContract.LoginView loginView){
        this.loginView = loginView;
        //this.loginService = loginService;
    }

    @Override
    public boolean usernameValidation(String username){
        // Store values at the time of the login attempt.
        boolean cancel = false;
        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            loginView.setUsernameError(R.string.error_field_required);
            cancel = true;
        } else if (!isUsernameValid(username)) {
            loginView.setUsernameError(R.string.error_invalid_username);
            cancel = true;
        }
        return cancel;
    }

    @Override
    public boolean passwordValidation(String password){
        boolean cancel = false;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            loginView.setPasswordError(R.string.error_password_too_short);
            cancel = true;
        }

        if (!"Password666".equals(password)) {
            loginView.setPasswordError(R.string.error_invalid_password);
            cancel = true;
        }
        return cancel;
    }

    private boolean isUsernameValid(String username) {
        return username.contains(".");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    @Override
    public void attemptLogin(String username){
        RetrofitFactory.getInstance().getService().login(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserEntity>() {

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                        loginView.loginError();
                    }

                    @Override
                    public void onComplete() {
                        loginView.loginSuccess();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserEntity user) {
                        Log.e("TAG", "response user name== " + user.getRole());
                    }
                });
    }
}
