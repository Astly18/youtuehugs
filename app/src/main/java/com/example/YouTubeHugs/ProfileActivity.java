package com.example.YouTubeHugs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    FirebaseAuth mAuth;
    private DatabaseReference RootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        RootRef = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R. string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new CreateQuizFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_create);}

        BottomNavigationView bottonNav = findViewById(R.id.bottom_navigationv);
        bottonNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new ReviewFragment()).commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_create:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CreateQuizFragment()).commit();
                break;


            case R.id.nav_share:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ShareFragment()).commit();
                break;


            case R.id.nav_message:
                Toast.makeText(this, "Message Us", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_rate:
                Toast.makeText(this, "Please, rate this App! Thank you!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_about:
                Toast.makeText(this, "About this App", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new ReviewFragment();
                            break;
                        case R.id.nav_chart:
                            selectedFragment = new QuizFragment();
                            break;
                        case R.id.nav_friend_List:
                            selectedFragment = new SearchFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.logout_option) {
            mAuth.signOut();
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.settings_option) {
        }
        if (item.getItemId() == R.id.add_item_option)
        {
            RequestNewGroupv();
        }

        return true;

    }

    private void RequestNewGroupv()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this, R.style.AlertDialog);
        builder.setTitle("Add New Group");

        final EditText insertItem = new EditText(ProfileActivity.this);
        insertItem.setHint("   Input group name                                         ");
        builder.setView(insertItem);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which)
            {
                String itemSubject = insertItem.getText().toString();

                if (TextUtils.isEmpty(itemSubject))
                {
                    Toast.makeText(ProfileActivity.this, "Please write group name", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    InsertNewItem (itemSubject);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which)
            {
            dialogInterface.cancel();
            }
        });

            builder.show();



        }

    private void InsertNewItem(final String itemSubject)
    {
        RootRef.child("ItemSubject").child(itemSubject).setValue("")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(ProfileActivity.this,  itemSubject +"subject is created Successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}