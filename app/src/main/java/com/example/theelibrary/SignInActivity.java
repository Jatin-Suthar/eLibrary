package com.example.theelibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    ImageView backgroundImage;
    EditText emailAddress;
    EditText password;
    Button signIn;
    TextView signUpText;
    ImageButton imageBack;
    TextView forgotPasswordText;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_in);
        //views .............................
        backgroundImage = (ImageView) findViewById(R.id.imageView2);
        emailAddress = findViewById(R.id.editTextTextEmailAddress1);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        signIn = (Button) findViewById(R.id.button);
        forgotPasswordText = (TextView) findViewById(R.id.forgotPassword);
        signUpText = (TextView) findViewById(R.id.textView);
        imageBack = (ImageButton) findViewById(R.id.imageButton);
        mAuth = FirebaseAuth.getInstance();

        Intent intent = this.getIntent();
        String user_name = intent.getStringExtra("username");

        //validate....................................
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateText(user_name);
            }
        });
        //go to signup activity.......................
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this , SignUpActivity.class));
                finish();
            }
        });
        //go to front page............................
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this , MainActivity.class));
                finish();
            }
        });
    }

    private void validateText(String extra) {
        String user_email = emailAddress.getText().toString().trim();
        String user_pass = password.getText().toString().trim();
        if(user_email.length() == 0) {
            emailAddress.setError("Enter your email address!");
            emailAddress.requestFocus();
        }else if(user_pass.length() == 0){
            password.setError("Enter your password!");
            password.requestFocus();
        }else{
            check(user_email,user_pass, extra);
        }
    }

    private void check(String user_email, String user_pass, String extra) {
        mAuth.signInWithEmailAndPassword(user_email,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    checkEmailVerification(extra);
                }else{
                    signIn.setEnabled(true);
                    password.setError("Wrong password!");
                    password.requestFocus();
                    password.setText("");
                    Toast.makeText(SignInActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkEmailVerification(String extra) {
        FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
        Boolean emailFlag = firebaseUser.isEmailVerified();
        if(emailFlag){
            Intent in = new Intent(SignInActivity.this , MainPageActivity.class);

            in.putExtra("email", emailAddress.getText().toString());
            in.putExtra("password", password.getText().toString());
            in.putExtra("user_name", extra);
            startActivity(in);
            finish();
        }else{
            Toast.makeText(this , "Verify Your Email" , Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }
    }
}