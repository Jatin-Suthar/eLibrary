package com.example.theelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theelibrary.data.DataIssue;
import com.google.android.material.textfield.TextInputLayout;

public class ReturnBook extends AppCompatActivity {

    ImageView logo_image;
    TextView return_txt;
    TextInputLayout libraryId1;
    TextInputLayout bookName1;
    TextInputLayout returnDate;
    Button Return;
    Button back_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_return_book);
        getViews();

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateReturnDetails(libraryId1.getEditText().getText().toString(), bookName1.getEditText().getText().toString(), returnDate.getEditText().getText().toString());
            }
        });

        back_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReturnBook.this, MainPageActivity.class));
                finish();
            }
        });
    }

    private void updateReturnDetails(String libId, String bookName, String returnDate) {
        Boolean isUpdated = new DataIssue(this).updateReturnBookDetails(libId, bookName, returnDate);
        if(isUpdated){
            Toast.makeText(ReturnBook.this, "Successfully updated the data..", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(ReturnBook.this, "Failed to update the data", Toast.LENGTH_LONG).show();
        }    }

    private void getViews() {
        logo_image = (ImageView) findViewById(R.id.image_logo);
        return_txt = (TextView) findViewById(R.id.return_text);
        libraryId1 = (TextInputLayout) findViewById(R.id.lib_id1);
        bookName1 = (TextInputLayout) findViewById(R.id.book_name1);
        returnDate = (TextInputLayout) findViewById(R.id.return_date);
        Return = (Button) findViewById(R.id.return_btn);
        back_btn2 = (Button) findViewById(R.id.back_btn2);
    }
}