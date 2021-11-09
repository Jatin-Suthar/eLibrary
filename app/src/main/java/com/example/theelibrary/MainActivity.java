 package com.example.theelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.renderscript.Byte4;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView backrectangle;
//    private ImageView logo;
//    private ImageView logoname;
    private Button enter;
    private Button esehi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        showMyToast();
        setViews();

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , SignInActivity.class));
                finish();
            }
        });

    }

    private void showMyToast() {
        LayoutInflater li = getLayoutInflater();
        View layout = li.inflate(R.layout.toastlayout,(ViewGroup) findViewById(R.id.toast_layout));
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }

    private void setViews(){
          backrectangle = (ImageView) findViewById(R.id.imageView4);
          enter = (Button) findViewById(R.id.button6);
          esehi = (Button) findViewById(R.id.button5);
    }
}