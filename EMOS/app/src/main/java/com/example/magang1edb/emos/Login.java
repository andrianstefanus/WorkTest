package com.example.magang1edb.emos;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private ConstraintLayout loginLayout;
    private ConstraintLayout registerLayout;
    private TextInputLayout emailLoginLayout;
    private TextInputLayout passwordLoginLayout;
    private TextInputEditText emailLogin;
    private TextInputEditText passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        loginLayout = findViewById(R.id.loginLayout);
        registerLayout = findViewById(R.id.registerLayout);
        emailLoginLayout = findViewById(R.id.email_login_layout);
        passwordLoginLayout = findViewById(R.id.password_login_layout);
        emailLogin = findViewById(R.id.email_login);
        passwordLogin = findViewById(R.id.password_login);
        emailLogin.addTextChangedListener(new AutoCheckerInput(emailLogin));
        passwordLogin.addTextChangedListener(new AutoCheckerInput(passwordLogin));

    }

    private boolean validateEmail(){
        String email = emailLogin.getText().toString();
        if (email.isEmpty() || !isValidEmail(email)) {
            emailLoginLayout.setError(getString(R.string.err_msg_email));
            requestFocus(emailLogin);
            return false;
        } else {
            emailLoginLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword(){
        String password = passwordLogin.getText().toString();
        if (password.isEmpty()){
            passwordLoginLayout.setError(getString(R.string.err_msg_password));
            requestFocus(passwordLogin);
            return false;
        } else {
            passwordLoginLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePasswordConfirm(){
        return true;
    }

    private boolean isValidEmail(String email){
        return  android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view){
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void Login(View view) {
        if (validateEmail()) return;
        if (validatePassword()) return;
        if (validateEmail() && validatePassword()){
            Toast.makeText(this, "login", Toast.LENGTH_LONG).show();
        }
    }

    public void register(View view) {

    }

    public void signIn(View view) {
        loginLayout.setVisibility(View.GONE);
        registerLayout.setVisibility(View.VISIBLE);
    }

    public void createAccount(View view) {
        registerLayout.setVisibility(View.GONE);
        loginLayout.setVisibility(View.VISIBLE);
    }

    private class AutoCheckerInput implements TextWatcher {
        private View view;
        private AutoCheckerInput(View view) { this.view = view; }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.email_login:
                    validateEmail();
                    break;
                case R.id.password_login:
                    validatePassword();
                    break;
            }
        }
    }
}
