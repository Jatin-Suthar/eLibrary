package com.example.theelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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

import com.example.theelibrary.data.DataIssue;
import com.example.theelibrary.data.MyDbHandler;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

public class IssueBook extends AppCompatActivity {
    ImageView logoImage;
    TextView issue_txt;
    TextInputLayout libraryId;
    TextInputLayout bookName;
    TextInputLayout datePicker;
    Button Issue;
    Button back_btn;
    DataIssue myData;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_issue_book);
        addViews();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IssueBook.this, MainPageActivity.class));
                finish();
            }
        });

        Issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lib_id = libraryId.getEditText().getText().toString();
                String book_name = bookName.getEditText().getText().toString();
                String book_date = datePicker.getEditText().getText().toString();
                //just add the following information about issue book details
                addData(lib_id, book_name, book_date);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateIssueDetails(libraryId.getEditText().getText().toString(), bookName.getEditText().getText().toString(), datePicker.getEditText().getText().toString());
            }
        });
    }

    private void updateIssueDetails(String libId, String bookNme, String bookDte) {
        Boolean isUpdated = new DataIssue(this).updateIssueDetails(libId, bookNme, bookDte);
        if(isUpdated){
            Toast.makeText(IssueBook.this, "Successfully updated the data..", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(IssueBook.this, "Failed to update the data", Toast.LENGTH_LONG).show();
        }
    }

    public void addData(String lib_id, String book_name, String book_date){
        myData = new DataIssue(this);
        boolean check = myData.addIssueDetails(lib_id, book_name, book_date);
        if(check)
            Toast.makeText(IssueBook.this, "successfully inserted data", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(IssueBook.this, "failed to insert data", Toast.LENGTH_LONG).show();
    }

    private void addViews() {
        logoImage = (ImageView) findViewById(R.id.imageLogo);
        issue_txt = (TextView) findViewById(R.id.issue_text);
        libraryId = (TextInputLayout) findViewById(R.id.lib_id);
        bookName = (TextInputLayout) findViewById(R.id.book_name);
        datePicker = (TextInputLayout) findViewById(R.id.issue_date);
        Issue = (Button) findViewById(R.id.issue_btn);
        back_btn = (Button) findViewById(R.id.back_btn);
        update = (Button) findViewById(R.id.update);
    }
}