package com.example.florajin.myapplication.login;

import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;

public interface LoginContract {
    interface LoginView{
        void setUsernameError(int resId);
        void setPasswordError(int resId);
        void loginError();
        void loginSuccess();
    }

    interface LoginPresenter{
        boolean usernameValidation(String username);
        boolean passwordValidation(String password);
        void attemptLogin(String username);
    }
}
