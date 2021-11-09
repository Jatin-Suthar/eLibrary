package com.example.theelibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theelibrary.data.MyDbHandler;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DisplayActivity extends AppCompatActivity {

    private ImageView image;
    private TextView txt1, txt2;
    private Button display;
    TextInputLayout password;
    MyDbHandler myDb;
    String mPassword;
    Button back_btn1;
    Button displayButton;
    TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_display);

        image = (ImageView) findViewById(R.id.imageLogo);

        txt1 = (TextView) findViewById(R.id.welcome);
        txt2 = (TextView) findViewById(R.id.admins);
        password = (TextInputLayout) findViewById(R.id.text_password1);
        display = (Button) findViewById(R.id.display);
        back_btn1 = (Button) findViewById(R.id.back_btn1);
        displayButton = (Button) findViewById(R.id.display_btn);
        displayText = (TextView) findViewById(R.id.display_txt);

        back_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplayActivity.this, MainPageActivity.class));
                finish();
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPassword = password.getEditText().getText().toString();
                displayInfo(mPassword);
            }
        });

        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillText();
            }
        });
    }

    private void fillText() {

        Cursor res = new MyDbHandler(this).getAllData();//myDb.getAllData()

        if(res.getCount() == 0) {
            //show error message
            Toast.makeText(DisplayActivity.this, "Nothing Found....", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()){
            buffer.append("Name : " + res.getString(1) + "\n");
        }

        displayText.setText(buffer.toString());
    }

    private void displayInfo(String mPassword) {
        String adminPassword = "JarvisOp";
        if(mPassword.equals(adminPassword)){
            Cursor res = new MyDbHandler(this).getAllData();//myDb.getAllData()
            if(res.getCount() == 0){
                //show error message
                Toast.makeText(DisplayActivity.this, "Error1", Toast.LENGTH_SHORT).show();
                showMessage("Error", "Nothing found...");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
                buffer.append("Id : " + res.getInt(0) + "\n");
                buffer.append("Name : " + res.getString(1) + "\n");
                buffer.append("Password : " + res.getString(2) + "\n");
                buffer.append("Email : " + res.getString(3) + "\n");
                buffer.append("Phone : " + res.getString(4) + "\n\n");
            }
            //show the data
            showMessage("Data", buffer.toString());
        }else{
            Toast.makeText(DisplayActivity.this, "Wrong password...", Toast.LENGTH_SHORT).show();
        }
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}