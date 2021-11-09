package com.example.theelibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainPageActivity extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        bottomNavigation = (MeowBottomNavigation) findViewById(R.id.bottom_navigation);

        // add components of bottom navigation bar of app

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_add));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_person));
        String[] id = new String[4];
        id[0] = "";
        id[1] = "";
        id[2] = "";
        id[3] = "";
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // add fragments
                Fragment fragment = null;
                switch (item.getId()) {
                    case 1:
                        // when id is 1
                        id[1] = "Home";
                        fragment = new HomeFragment();
                        break;

                    case 2:
                        // when id is 2
                        id[2] = "Add";
                        fragment = new AddFragment();
                        break;

                    case 3:
                        // when id is 3
                        id[3] = "Profile";
                        fragment = new ProfileFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });
        bottomNavigation.show(1, true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), id[item.getId()] , Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), id[item.getId()], Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}