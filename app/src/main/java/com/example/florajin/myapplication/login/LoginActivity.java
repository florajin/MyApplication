package com.example.florajin.myapplication.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.florajin.myapplication.R;
import com.example.florajin.myapplication.summary.SummaryActivity;

import butterknife.BindString;
import butterknife.ButterKnife;

import javax.inject.Inject;

import butterknife.BindView;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    @BindView(R.id.username)
    EditText mUsernameView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.user_sign_in_button)
    Button mUserSignInButton;

    private String focusViewName = "";
    private LoginPresenterImpl loginPresenter;
    public static final String prefKey="prefKey";
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);

        // Set up the login form.
        mUsernameView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String username = mUsernameView.getText().toString();
                if (!hasFocus) {
                    mUsernameView.setError(null);
                    if(loginPresenter.usernameValidation(username)){
                        //  focusView.requestFocus();
                        focusViewName="username";

                    }else{
                        mUsernameView.setError(null);
                        focusViewName="password";
                    }
                }
            }
        });

        mPasswordView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus && !"username".equals(focusViewName)) {
                    String password = mPasswordView.getText().toString();
                    mPasswordView.setError(null);
                    if(loginPresenter.passwordValidation(password)){
                        focusViewName="password";
                    }else{
                        mPasswordView.setError(null);
                        focusViewName="";
                    }
                }
            }
        });

        //Login
        mUserSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsernameView.getText().toString();
                String password = mPasswordView.getText().toString();
                loginPresenter.passwordValidation(password);
                if(!"password".equals(focusViewName)) {
                    loginPresenter.attemptLogin(username);
                }
            }
        });
    }

    @Override
    public void setUsernameError(int resId) {
        mUsernameView.setError(getString(resId));
    }

    @Override
    public void setPasswordError(int resId) {
        mPasswordView.setError(getString(resId));
    }

    @Override
    public void loginError() {
        Toast.makeText(LoginActivity.this,getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
        mUsernameView.setError(getString(R.string.error_invalid_username));
        //mPasswordView.setText("");
        mUsernameView.requestFocus();
    }

    @Override
    public void loginSuccess() {
        setSharedPreference();
        Toast.makeText(LoginActivity.this,getString(R.string.login_success), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, SummaryActivity.class);
        startActivity(intent);
    }

    private void setSharedPreference(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        editor = preferences.edit();
        editor.putString(prefKey, String.valueOf(mUsernameView.getText()));
        editor.commit();
    }

}

