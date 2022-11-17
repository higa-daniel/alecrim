package br.com.alecrimapp.alecrimdelivery.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.alecrimapp.alecrimdelivery.BuildConfig;
import br.com.alecrimapp.alecrimdelivery.R;
import br.com.alecrimapp.alecrimdelivery.api.AuthenticationService;
import br.com.alecrimapp.alecrimdelivery.api.ServiceGenerator;
import br.com.alecrimapp.alecrimdelivery.models.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends BaseActivity {

    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface font = null;

        font = Typeface.createFromAsset(getAssets(), "fonts/gotham_book.otf");

        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        loginButton = (Button) findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = null;
                String password = null;

                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();

                authenticateUser(email, password);
            }
        });

        emailEditText.setTypeface(font);
        passwordEditText.setTypeface(font);
        loginButton.setTypeface(font);
    }

    private void authenticateUser(final String email, final String password) {
        if (!isValidEmail(email)) {
            showToast(R.string.login_email_error);

            return;
        }

        if (!isValidPassword(password)) {
            showToast(R.string.login_password_error);

            return;
        }

        final String masterEmail = "mobile@alecrimapp.com.br";
        final String masterPassword = "pass@word1";

        showThrobber();

        final AuthenticationService authenticationService = ServiceGenerator.createService(AuthenticationService.class);

        authenticationService.authenticate("password", masterEmail, masterPassword, "",
                new Callback<User>() {
                    @Override
                    public void success(User user, Response response) {
                        final AuthenticationService authenticationService =
                                ServiceGenerator.createService(AuthenticationService.class, user.getToken());
                        authenticationService.authenticate("password", email, password, "",
                                new Callback<User>() {
                                    @Override
                                    public void success(User user, Response response) {
                                        saveUser(user, email, password);

                                        showToast(R.string.login_success);

                                        hideThrobber();

                                        startMainActivity();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        showToast(R.string.login_error);

                                        hideThrobber();
                                    }
                                });
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        showToast(R.string.login_error);

                        hideThrobber();
                    }
                });
    }

    private void startMainActivity() {
        Intent intent = null;

        intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    private void saveUser(User user, String email, String password) {
        // Local variables
        SharedPreferences.Editor editor = null;

        editor = getSharedPreferences(BuildConfig.PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("token", user.getToken());
        editor.putInt("tokenExpiry", user.getTokenExpiry());
        editor.putString("customerId", user.getCustomerId());
        editor.putString("userEmail", email);
        editor.putString("userPassword", password);

        editor.apply();
    }

    private boolean isValidEmail(CharSequence email) {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 3;
    }

}
