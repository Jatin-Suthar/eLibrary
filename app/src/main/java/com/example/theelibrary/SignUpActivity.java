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
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theelibrary.data.MyDbHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    private ImageView registerViewImage;
    private EditText personName;
    private EditText emailAddress1;
    private EditText password1;
    private EditText phoneNumber;
    private Button registerButton;
    private TextView directToSignInPage;
    private FirebaseAuth mAuth;
//    FirebaseDatabase db;
//    DatabaseReference ref;
    MyDbHandler myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        //set views

        registerViewImage = (ImageView) findViewById(R.id.imageView3);
        personName = (EditText) findViewById(R.id.editTextTextPersonName);
        emailAddress1 = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        phoneNumber = (EditText) findViewById(R.id.editTextPhone) ;
        password1 = (EditText) findViewById(R.id.editTextTextPassword2);
        registerButton = (Button) findViewById(R.id.button4);
        directToSignInPage = (TextView) findViewById(R.id.textView2);
        mAuth = FirebaseAuth.getInstance();

        //onclicklistener
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDataAndDoRegister();
            }
        });
    }

    private void validateDataAndDoRegister() {
        String email = emailAddress1.getText().toString().trim();
        String pass = password1.getText().toString().trim();
        String person = personName.getText().toString().trim();
        String phone = phoneNumber.getText().toString().trim();
        if(email.isEmpty()){
            emailAddress1.setError("Enter email address!");
            emailAddress1.requestFocus();
        }else if(person.isEmpty()){
            personName.setError("Enter the person name!");
            personName.requestFocus();
        }else if(pass.isEmpty()){
            password1.setError("Enter the password!");
            password1.requestFocus();
        }else if(email.length() < 10){
            emailAddress1.setError("Please enter the valid email address!");
            emailAddress1.requestFocus();
        }else if(pass.length() < 6){
            password1.setError("Please enter a long and secure password!");
            password1.requestFocus();
            password1.setText("");
        }else if(phone.length() < 10){
            phoneNumber.setError("Please enter valid Phone No.!");
            phoneNumber.requestFocus();
        }else{
//            Users obj = new Users(person, pass, email, phone);
//            db = FirebaseDatabase.getInstance();
//            ref = db.getReference("users");
//
//            ref.child(phone).setValue(obj);

            myDb = new MyDbHandler(this);
            boolean check = myDb.insertDetails(person, pass, email, phone);
            if(check)
                Toast.makeText(SignUpActivity.this, "successfully inserted data", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(SignUpActivity.this, "failed to insert data", Toast.LENGTH_LONG).show();

            createNewUser(email,pass);
        }
    }

    private void createNewUser(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    getVerificationEmail();
                }else{
                    registerButton.setEnabled(true);
                    Toast.makeText(SignUpActivity.this, "Oops! something went wrong error 1", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof FirebaseAuthUserCollisionException){
                    registerButton.setEnabled(true);
                    Toast.makeText(SignUpActivity.this, "User already registered!", Toast.LENGTH_SHORT).show();
                    emailAddress1.setText("");
                }else{
                    registerButton.setEnabled(true);
                    Toast.makeText(SignUpActivity.this, "Oops! something went wrong error 2", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getVerificationEmail() {
        final FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        registerButton.setEnabled(true);
                        Toast.makeText(SignUpActivity.this, "Verification email has been sent to your email address!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                        intent.putExtra("username", personName.getText().toString());
                        startActivity(intent);
                        finish();
                    } else {
                        registerButton.setEnabled(true);
                        Toast.makeText(SignUpActivity.this, "Oops! something went wrong error 3", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}