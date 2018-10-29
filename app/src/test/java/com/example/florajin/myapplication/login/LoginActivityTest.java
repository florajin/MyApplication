package com.example.florajin.myapplication.login;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.florajin.myapplication.BuildConfig;
import com.example.florajin.myapplication.R;
import com.example.florajin.myapplication.summary.SummaryActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 24)
public class LoginActivityTest {
    LoginActivity activity;
    Button button;
    EditText mUsernameView;
    EditText mPasswordView;
    LoginPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(LoginActivity.class)
                .create()
                .resume()
                .get();

        button = (Button) activity.findViewById(R.id.user_sign_in_button);
        mUsernameView = (EditText) activity.getUsernameView();
        mPasswordView = (EditText) activity.getPasswordView();
        presenter = new LoginPresenterImpl(activity);

    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void clickingLogin_shouldStartSummaryActivity() {
        mUsernameView.setText("flora.jin");
        mPasswordView.setText("Password666");
        button.performClick();

        //Intent expectedIntent = new Intent(activity, SummaryActivity.class);
        //Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        //assertEquals(expectedIntent.getComponent(), actual.getComponent());
        //List<Toast> toasts = shadowOf(RuntimeEnvironment.application).getShownToasts();

        assertEquals(ShadowToast.getTextOfLatestToast(), "Login successfully");

    }

}
