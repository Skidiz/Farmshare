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
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    EditText emailEd, passEd;
    TextView forgotPassTv, registerTv;
    Button loginBtn;

    private String email, pass;

    private Dialog messageDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loginBtn = (Button) findViewById(R.id.loginUpBtn);
        registerTv = (TextView) findViewById(R.id.signUpTv);
        emailEd = (EditText) findViewById(R.id.loginEmailEd);
        passEd = (EditText) findViewById(R.id.loginPasswordEd);
        forgotPassTv = (TextView) findViewById(R.id.lostPassTv);

        signUp();
        login();
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
        navToNext(Home2.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void signUp() {
        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navToNext(SignUp.class);
            }
        });
    }

    public void login() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFeilds();
            }
        });
    }

    public void navToNext(Class newIntent) {
        Intent intent = new Intent(Splash.this, newIntent);
        startActivity(intent);
    }

    public void stateMessage(final String message) {
        messageDialog = new Dialog(Splash.this);
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

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}
