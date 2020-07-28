package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BranchesActivity extends AppCompatActivity {

    private Button csButton;
    private Button itButton;
    private Button signOutButton;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private TextView welcomeUser;
    private ProgressDialog progressDialog;

    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(BranchesActivity.this);
        progressDialog.setTitle("Welcome !!");
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        setContentView(R.layout.activity_branches);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);

        csButton = (Button)findViewById(R.id.csButton);
        itButton= (Button)findViewById(R.id.itButton);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        signOutButton = (Button) findViewById(R.id.signOutButton);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");
        welcomeUser=(TextView)findViewById(R.id.welcomeUserTextView);


        String userid= FirebaseAuth.getInstance().getCurrentUser().getUid();

            databaseReference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    progressDialog.show();
                    String userName = dataSnapshot.child("name").getValue().toString();
                    welcomeUser.setText("Welcome " + userName + " !");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        csButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CsActivity.class);
                startActivity(intent);
            }
        });

        itButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent(getApplicationContext(),ItActivity.class);
                startActivity(intent);
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new androidx.appcompat.app.AlertDialog.Builder(BranchesActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Sign Out")
                        .setMessage("Do you want to sign out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                FirebaseAuth auth = FirebaseAuth.getInstance();
                                auth.signOut();
                                Intent intent = new Intent(BranchesActivity.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            }
                        })
                            .setNegativeButton("No",null)
                        .show();

            }
        });

    }

}
