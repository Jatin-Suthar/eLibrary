package com.example.theelibrary;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theelibrary.data.DataIssue;
import com.example.theelibrary.data.MyDbHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.invoke.ConstantCallSite;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView profilePhoto, libraryImageView, emailImageView, phoneImageView;
    TextView name;
    TextView libraryId, emailId, phoneNumber;
    ImageView backButton;
    TextView profileText ;
    ImageView logout;
    TextView issueText;
    TextView returnText;
    TextView numberIssueText;
    TextView numberReturnText;
    ImageView greenBook;
    ImageView redBook;
    TextView information;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);

        showView(v);

        Intent in1 = getActivity().getIntent();
        String dataEmail = in1.getStringExtra("email");
        String dataPass = in1.getStringExtra("password");
        String user_name = in1.getStringExtra("user_name");

        updateData(dataPass);
        updateIssueData();
        updateReturnData();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainPageActivity.class));
                getActivity().finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SignInActivity.class));
                getActivity().finish();
            }
        });

        return v;
    }

    public void showView(View v) {

        backButton = v.findViewById(R.id.backButton);
        profileText = v.findViewById(R.id.profile1);
        logout = v.findViewById(R.id.logout_btn);
        profilePhoto = v.findViewById(R.id.profile1ImageView);

        libraryImageView = v.findViewById(R.id.libraryId);
        emailImageView = v.findViewById(R.id.EmailId);
        phoneImageView = v.findViewById(R.id.PhoneId);
        //TextViews :
        name = v.findViewById(R.id.username);

        issueText = v.findViewById(R.id.issueBooksText);
        numberIssueText = v.findViewById(R.id.numberIssue);
        returnText = v.findViewById(R.id.returnBooksText);
        numberReturnText = v.findViewById(R.id.numberReturn);


        libraryId = v.findViewById(R.id.libraryText);
        emailId = v.findViewById(R.id.emailText);
        phoneNumber = v.findViewById(R.id.PhoneText);
        greenBook = v.findViewById(R.id.greenBook);
        redBook = v.findViewById(R.id.redBook);
        information = v.findViewById(R.id.textInformation);

    }

    private void updateReturnData() {
        Cursor res = new DataIssue(getActivity()).getLastIssueDate();

        if(res.getCount() == 0) {
            //show error message
            return;
        }

        StringBuffer buffer7 = new StringBuffer();
        StringBuffer buffer8 = new StringBuffer();

        while(res.moveToNext()){
            buffer7.append("" + res.getString(4) + "\n");
            buffer8.append("" + res.getString(5) + "\n");
        }

        returnText.setText(buffer7.toString());

        numberReturnText.setText(buffer8.toString());
    }

    private void updateIssueData() {
        Cursor res = new DataIssue(getActivity()).getLastIssueDate();

        if(res.getCount() == 0) {
            //show error message
            return;
        }

        StringBuffer buffer5 = new StringBuffer();
        StringBuffer buffer6 = new StringBuffer();

        while(res.moveToNext()){
            buffer5.append("" + res.getString(2) + "\n");
            buffer6.append("" + res.getString(3) + "\n");
        }

        issueText.setText(buffer5.toString());

        numberIssueText.setText(buffer6.toString());

        information.setText("You Profile's  looking amazing..");
    }

    private void updateData(String Permian) {
        Cursor res = new MyDbHandler(getActivity()).getOnlyOneUserData(Permian);

        if(res.getCount() == 0) {
            //show error message
            return;
        }

        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer buffer3 = new StringBuffer();
        StringBuffer buffer4 = new StringBuffer();

        while(res.moveToNext()){
            buffer.append("" + res.getString(1) + "\n");
            buffer1.append("" + res.getString(1) + "\n");
            buffer3.append("" + res.getString(3) + "\n");
            buffer4.append("" + res.getString(4) + "\n");
        }

        name.setText(buffer.toString());
        libraryId.setText(buffer1.toString().trim());
        emailId.setText(buffer3.toString());
        phoneNumber.setText(buffer4.toString());

        Toast.makeText(getActivity(), "Data Successfully Displayed", Toast.LENGTH_SHORT).show();
    }
}