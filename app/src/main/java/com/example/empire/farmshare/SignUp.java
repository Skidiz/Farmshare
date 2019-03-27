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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    private EditText firstNameEt, lastNameEt, emilAddressEt, passwordEt;
    private Button signUpBtn;
    private CheckBox agreementCheckBox;

    private TextView loginTv;

    private String firstName,lastName,email,password;

    private Dialog messageDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstNameEt = (EditText) findViewById(R.id.firsNameEt);
        lastNameEt = (EditText) findViewById(R.id.lastNameEt);
        emilAddressEt = (EditText) findViewById(R.id.emailAddressEt);
        passwordEt = (EditText) findViewById(R.id.passwordEt);


        loginTv = (TextView) findViewById(R.id.loginTv);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);

        agreementCheckBox = (CheckBox) findViewById(R.id.agreementCheck);

        signUp();
        loginOpt();
    }

    public void signUp(){
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFeilds();
            }
        });
    }

    public void checkFeilds(){

        firstName = firstNameEt.getText().toString().trim();
        lastName = lastNameEt.getText().toString().trim();
        email = emilAddressEt.getText().toString().trim();
        password = passwordEt.getText().toString().trim();

        if (firstName.isEmpty() || equals(null)){
            stateMessage("First name is empty");
            firstNameEt.requestFocus();
        }else if(lastName.isEmpty() || equals(null)){
            stateMessage("Last name is empty");
            lastNameEt.requestFocus();
        }else if(email.isEmpty() || equals(null)){
            stateMessage("Email is empty");
            emilAddressEt.requestFocus();
        }else if(!email.contains("@")){
            stateMessage("Invalid email address");
            emilAddressEt.requestFocus();
        }else if(password.isEmpty() || equals(null)){
            stateMessage("Password is empty");
            passwordEt.requestFocus();
        }else{
            trySignUp();
        }

    }

    public void trySignUp(){
        navToNext(Home2.class);
    }

    public void loginOpt(){
        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navToNext(Splash.class);
            }
        });
    }

    @Override
    public void onBackPressed() {
        navToNext(Splash.class);
    }


    public void navToNext(Class newIntent) {
        Intent intent = new Intent(SignUp.this, newIntent);
        startActivity(intent);
    }

    public void stateMessage(final String message) {
        messageDialog = new Dialog(SignUp.this);
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
