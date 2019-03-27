package com.example.empire.farmshare;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    EditText emailEd, passEd;
    TextView forgotPassTv;
    Button loginBtn,signUpBtn;

    private String email, pass;

    private Dialog messageDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEd = (EditText) findViewById(R.id.loginEmailEd);
        passEd = (EditText) findViewById(R.id.loginPasswordEd);
        forgotPassTv = (TextView) findViewById(R.id.lostPassTv);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        loginBtn = (Button) findViewById(R.id.loginUpBtn);

        login();
        signUp();

    }

    public void login() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFeilds();
            }
        });
    }
    public void signUp(){
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navToNext(SignUp.class);
            }
        });
    }

    @Override
    public void onBackPressed() {
        navToNext(Splash.class);
    }

    public void checkFeilds() {
        email = emailEd.getText().toString().trim();
        pass = passEd.getText().toString().trim();

        if (email.isEmpty() || equals(null)) {
            stateMessage("Email is empty");
            emailEd.requestFocus();
        } else if (pass.isEmpty() || equals(null)) {
            stateMessage("Password is empty");
            passEd.requestFocus();
        } else {
            tryLogin();
        }
    }

    public void tryLogin() {
//        navToNext();
    }


    public void navToNext(Class newIntent) {
        Intent intent = new Intent(Login.this, newIntent);
        startActivity(intent);
    }

    public void stateMessage(final String message) {
        messageDialog = new Dialog(Login.this);
        messageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        messageDialog.setContentView(R.layout.message_layout);

        messageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//        messageDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        messageDialog.getWindow().setGravity(Gravity.TOP);
        messageDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        messageDialog.setCanceledOnTouchOutside(true);
        messageDialog.show();

        TextView messageTV = (TextView) messageDialog.findViewById(R.id.messageTV);
        TextView closeBtn = (TextView) messageDialog.findViewById(R.id.dialogCloseTv);

        messageTV.setText(message);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageDialog.cancel();
            }
        });

    }
}
