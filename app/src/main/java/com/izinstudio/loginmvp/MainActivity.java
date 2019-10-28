package com.izinstudio.loginmvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.izinstudio.loginmvp.Presenter.ListContact;
import com.izinstudio.loginmvp.Presenter.SignUpPresenter;
import com.izinstudio.loginmvp.db.AppDatabase;
import com.izinstudio.loginmvp.db.User;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener, ListContact.View {
    Button btnSignUp;
    EditText edtUserName, edtEmail, edtPassword;
    TextView txtUserName, txtEmail, txtPassword;
    SwitchCompat stAccpect;
    //  AppDatabase db;
    private ListContact.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        btnSignUp = findViewById(R.id.btnSignUp);
        edtUserName = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtUserName = findViewById(R.id.txtUsername);
        stAccpect = findViewById(R.id.switchCompat);

        edtUserName.setOnFocusChangeListener(this);
        edtPassword.setOnFocusChangeListener(this);
        edtEmail.setOnFocusChangeListener(this);
        btnSignUp.setOnClickListener(this);

        AppDatabase db = AppDatabase.getDatabase(getApplication());
        mPresenter = new SignUpPresenter(this, db.userDAO());
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edtUsername:

                if (hasFocus) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txtUserName.setVisibility(View.INVISIBLE);
                        }
                    }, 100);
                } else {
                    if (edtUserName.getText().length() > 0)
                        txtUserName.setVisibility(View.INVISIBLE);
                    else
                        txtUserName.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.edtEmail:

                if (hasFocus) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txtEmail.setVisibility(View.INVISIBLE);
                        }
                    }, 100);
                } else {
                    if (edtEmail.getText().length() > 0)
                        txtEmail.setVisibility(View.INVISIBLE);
                    else
                        txtEmail.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.edtPassword:

                if (hasFocus) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txtPassword.setVisibility(View.INVISIBLE);
                        }
                    }, 100);
                } else {
                    if (edtPassword.getText().length() > 0)
                        txtPassword.setVisibility(View.INVISIBLE);
                    else
                        txtPassword.setVisibility(View.VISIBLE);
                }
                break;
        }
    }


    @Override
    public void onClick(View v) {
        String userName = edtUserName.getText().toString(), email = edtEmail.getText().toString();
        User user = new User();
        user.setUsername(userName);
        user.setEmail(email);
        user.setPassword(edtPassword.getText().toString());
        boolean valid = mPresenter.validate(user);
        if (!valid) return;
        mPresenter.save(user, userName, email);
    }


    @Override
    public void showErrorMessage(int field) {
        if (field == 0) {
            Toast.makeText(this, "User name :ERROR!!!", Toast.LENGTH_SHORT).show();
        } else if (field == 1) {
            Toast.makeText(this, "Email :ERROR!!!", Toast.LENGTH_SHORT).show();
        } else if (field == 2) {
            Toast.makeText(this, "Password:ERROR!!!", Toast.LENGTH_SHORT).show();
        } else if (field == 3) {
            Toast.makeText(this, "USERNAME : USED", Toast.LENGTH_SHORT).show();
        }else if (field == 4) {
            Toast.makeText(this, "EMAIL : USED", Toast.LENGTH_SHORT).show();
        }else if (field == 5) {
            Toast.makeText(this, "FINISH", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setPresenter(ListContact.Presenter presenter) {
        mPresenter = presenter;
    }
}
